package org.irods.jargon.ga4gh.dos.bundle.impl;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.core.checksum.ChecksumManager;
import org.irods.jargon.core.checksum.ChecksumManagerImpl;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.SpecificQueryAO;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.pub.domain.Collection;
import org.irods.jargon.core.query.IRODSQueryResultRow;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.core.query.SpecificQuery;
import org.irods.jargon.core.query.SpecificQueryResultSet;
import org.irods.jargon.ga4gh.dos.bundle.AbstractDosService;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.BundleObjectRollup;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataBundle;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.DosBundleManagementService;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.exception.DosSystemException;
import org.irods.jargon.ga4gh.dos.utils.ExplodedBundleMetadataUtils;
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

	private DosConfiguration dosConfiguration;

	public ExplodedDosServiceImpl(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosServiceFactory dosServiceFactory, DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount, dosServiceFactory, dosConfiguration);
	}

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
			List<BundleObjectRollup> objects = retrieveDataObjectsInBundle(irodsPath);

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

	protected List<BundleObjectRollup> retrieveDataObjectsInBundle(final String irodsCollectionPath)
			throws DataNotFoundException, JargonException {

		// FIXME: refactor this next

		log.info("retrieveDataObjectsInBundle()");
		if (irodsCollectionPath == null || irodsCollectionPath.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsCollectionPath");
		}

		log.info("irodsCollectionPath:{}", irodsCollectionPath);

		List<BundleObjectRollup> objects = new ArrayList<BundleObjectRollup>();
		StringBuilder sb = new StringBuilder();
		sb.append(irodsCollectionPath.trim());
		sb.append('%');

		log.info("checking for existence of specific query...");
		SpecificQueryAO specificQueryAO = this.getIrodsAccessObjectFactory().getSpecificQueryAO(getIrodsAccount());
		SpecificQuery specificQuery = SpecificQuery.instanceWithOneArgument(BUNDLE_QUERY_ALIAS, sb.toString(), 0, "");
		BundleObjectRollup bundleObjectRollup;
		StringBuilder dataPath;
		ChecksumManager checksumManager = new ChecksumManagerImpl(this.getIrodsAccount(),
				this.getIrodsAccessObjectFactory());
		try {
			SpecificQueryResultSet result = specificQueryAO.executeSpecificQueryUsingAlias(specificQuery,
					this.getIrodsAccessObjectFactory().getJargonProperties().getMaxFilesAndDirsQueryMax(), 0);

			for (IRODSQueryResultRow row : result.getResults()) {
				log.debug("result row:{}", row);
				bundleObjectRollup = new BundleObjectRollup();
				dataPath = new StringBuilder();
				dataPath.append(row.getColumn(2));
				dataPath.append("/");
				dataPath.append(row.getColumn(3));
				bundleObjectRollup.setIrodsPath(dataPath.toString());
				bundleObjectRollup
						.setChecksumValue(checksumManager.determineChecksumEncodingFromIrodsData(row.getColumn(4)));
				bundleObjectRollup.setGuid(row.getColumn(7));
				log.info("added object to bundle:{}", bundleObjectRollup);
				objects.add(bundleObjectRollup);
			}

		} catch (JargonQueryException e) {
			log.error("error running query to find bundle objects");
			throw new JargonException(e);
		}

		return objects;

	}

}
