package org.irods.jargon.ga4gh.dos.bundle.impl;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.pub.domain.Collection;
import org.irods.jargon.core.query.GenQueryBuilderException;
import org.irods.jargon.core.query.IRODSGenQueryBuilder;
import org.irods.jargon.core.query.IRODSGenQueryFromBuilder;
import org.irods.jargon.core.query.IRODSQueryResultRow;
import org.irods.jargon.core.query.IRODSQueryResultSetInterface;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.core.query.QueryConditionOperators;
import org.irods.jargon.core.query.RodsGenQueryEnum;
import org.irods.jargon.core.utils.MiscIRODSUtils;
import org.irods.jargon.ga4gh.dos.bundle.AbstractDosService;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataBundle;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.DosBundleManagementService;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.exception.BundleNotFoundException;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.exception.DosSystemException;
import org.irods.jargon.ga4gh.dos.utils.ExplodedBundleMetadataUtils;
import org.irods.jargon.mdquery.exception.MetadataQueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A backing service behind the DOS Swagger API that translates the underlying
 * iRODS 'exploded' arrangement where bundles are mapped to iRODS collections
 * and data objects in-place, marked with metadata to denote bundles, member
 * objects, etc.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class ExplodedDosServiceImpl extends AbstractDosService implements DosService {

	private static final Logger log = LoggerFactory.getLogger(ExplodedDosServiceImpl.class);
	public static final String BUNDLE_QUERY_ALIAS = "ga4ghBundleQuery";

	public ExplodedDosServiceImpl(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosServiceFactory dosServiceFactory, DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount, dosServiceFactory, dosConfiguration);
	}

	@Override
	public IrodsDataBundle retrieveDataBundle(final String bundleId) throws DosDataNotFoundException {
		log.info("retrieveDataBundle()");
		if (bundleId == null || bundleId.isEmpty()) {
			throw new IllegalArgumentException("null or empty bundleId");
		}
		log.info("bundleId:{}", bundleId);
		DosBundleManagementService dosBundleManagementService = this.getDosServiceFactory()
				.instanceDosBundleManagementService(getIrodsAccount());

		try {
			String irodsPath = dosBundleManagementService.bundleIdToIrodsPath(bundleId);
			log.info("resolved to path:{}", irodsPath);

			CollectionAO collectionAO = this.getIrodsAccessObjectFactory().getCollectionAO(getIrodsAccount());
			Collection collection = collectionAO.findByAbsolutePath(irodsPath);

			log.info("getting rollup of objects in this bundle");
			List<String> objects = retrieveDataObjectsInBundle(irodsPath);

			IrodsDataBundle irodsDataBundle = new IrodsDataBundle();

			irodsDataBundle.setDataObjects(objects);

			List<MetaDataAndDomainData> metadata = collectionAO
					.findMetadataValuesForCollection(collection.getAbsolutePath(), 0);

			// find the bundle checksum in these avus

			AvuData avuData;

			for (MetaDataAndDomainData metaVal : metadata) {
				if (metaVal.getAvuAttribute().equals(ExplodedBundleMetadataUtils.GA4GH_BUNDLE_CHECKSUM_ATTRIBUTE)) {
					irodsDataBundle.setBundleChecksum(metaVal.getAvuValue());
				} else if (metaVal.getAvuAttribute().equals(ExplodedBundleMetadataUtils.GA4GH_BUNDLE_ID_ATTRIBUTE)) {
					irodsDataBundle.setBundleUuid(metaVal.getAvuValue());
				} else {
					avuData = AvuData.instance(metaVal.getAvuAttribute(), metaVal.getAvuValue(), metaVal.getAvuUnit());
					irodsDataBundle.getAvus().add(avuData);
				}
			}

			irodsDataBundle.setCreateDate(collection.getCreatedAt());
			irodsDataBundle.setDescription("iRODS exploded bundle collection"); // TODO: add special description avu?
			irodsDataBundle.setIrodsAbsolutePath(collection.getAbsolutePath());
			irodsDataBundle.setUpdatedDate(collection.getModifiedAt());
			irodsDataBundle.setVersion("0"); // TODO: add special version avu?

			log.info("data bundle ready:{}", irodsDataBundle);
			return irodsDataBundle;

		} catch (JargonException | JargonQueryException e) {
			log.error("error accessing iRODS", e);
			throw new DosSystemException("exception connecting to iRODS", e);
		}

	}

	@Override
	public List<String> retrieveDataObjectsInBundle(final String bundleId)
			throws BundleNotFoundException, JargonException {

		log.info("listBundleIds()");
		if (bundleId == null || bundleId.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsAbsolutePath");
		}
		log.info("bundleId:{}", bundleId);

		DosBundleManagementService dosBundleManagementService = this.getDosServiceFactory()
				.instanceDosBundleManagementService(getIrodsAccount());
		String irodsAbsolutePath = dosBundleManagementService.bundleIdToIrodsPath(bundleId);

		IRODSGenQueryBuilder builder = new IRODSGenQueryBuilder(true, null);
		IRODSQueryResultSetInterface resultSet = null;

		List<String> dataObjectIds = new ArrayList<>();

		try {
			builder.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_META_DATA_ATTR_VALUE);
		} catch (GenQueryBuilderException e) {
			log.error("error building query for collections:{}", irodsAbsolutePath, e);
			throw new MetadataQueryException("gen query error", e);
		}

		builder.addConditionAsGenQueryField(RodsGenQueryEnum.COL_COLL_NAME, QueryConditionOperators.LIKE,
				irodsAbsolutePath.trim() + "%");

		builder.addConditionAsGenQueryField(RodsGenQueryEnum.COL_META_DATA_ATTR_NAME, QueryConditionOperators.EQUAL,
				ExplodedBundleMetadataUtils.GA4GH_DATA_OBJECT_ID_ATTRIBUTE);

		IRODSGenQueryFromBuilder irodsQuery;
		try {
			irodsQuery = builder.exportIRODSQueryFromBuilder(
					getIrodsAccessObjectFactory().getJargonProperties().getMaxFilesAndDirsQueryMax());
			String targetZone = MiscIRODSUtils.getZoneInPath(irodsAbsolutePath);

			resultSet = getIrodsAccessObjectFactory().getIRODSGenQueryExecutor(getIrodsAccount())
					.executeIRODSQueryAndCloseResultInZone(irodsQuery, 0, targetZone);

			for (IRODSQueryResultRow row : resultSet.getResults()) {
				dataObjectIds.add(row.getColumn(0));
			}
		} catch (GenQueryBuilderException | JargonQueryException e) {
			log.error("error in query for bundles", e);
			throw new JargonException("cannot query for bundles", e);
		}

		log.debug("dataObjectIds:{}", dataObjectIds);
		return dataObjectIds;
	}

}
