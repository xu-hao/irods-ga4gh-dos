/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundlemgmnt.impl;

import java.util.UUID;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.query.GenQueryBuilderException;
import org.irods.jargon.core.query.IRODSGenQueryBuilder;
import org.irods.jargon.core.query.IRODSGenQueryFromBuilder;
import org.irods.jargon.core.query.IRODSQueryResultSetInterface;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.query.QueryConditionOperators;
import org.irods.jargon.core.query.RodsGenQueryEnum;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.core.utils.MiscIRODSUtils;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.DosBundleManagementService;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.exception.BundleNotFoundException;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.exception.DuplicateBundleException;
import org.irods.jargon.ga4gh.dos.utils.ExplodedBundleMetadataUtils;
import org.irods.jargon.mdquery.exception.MetadataQueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of a bundle management service
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class ExplodedDosBundleManagementServiceImpl extends AbstractJargonService
		implements DosBundleManagementService {

	public static final Logger log = LoggerFactory.getLogger(ExplodedDosBundleManagementServiceImpl.class);

	/**
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory}
	 * @param irodsAccount
	 *            {@link IRODSAccount}
	 */
	public ExplodedDosBundleManagementServiceImpl(IRODSAccessObjectFactory irodsAccessObjectFactory,
			IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
	}

	@Override
	public String createDataBundle(String bundleRootAbsolutePath)
			throws DataNotFoundException, DuplicateBundleException, JargonException {

		log.info("createDataBundle()");

		if (bundleRootAbsolutePath == null || bundleRootAbsolutePath.isEmpty()) {
			throw new IllegalArgumentException("null or empty bundleRootAbsolutePath");
		}

		log.info("bundleRootPath:{}", bundleRootAbsolutePath);

		// look for any bundles in children
		if (areThereExistingBundles(bundleRootAbsolutePath)) {
			log.warn("existing bundles under this parent");
			throw new DuplicateBundleException("A bundle already exists");
		}

		// assign bundle marker avu with guid

		String bundleId = UUID.randomUUID().toString();

		AvuData bundleMarkerAvu = ExplodedBundleMetadataUtils.createBundleMarkerAvu(bundleId);
		CollectionAO collectionAO = this.getIrodsAccessObjectFactory().getCollectionAO(getIrodsAccount());

		collectionAO.addAVUMetadata(bundleRootAbsolutePath, bundleMarkerAvu);

		// assign guid to each child data object and compute checksum + master checksum
		// via visitor

		// mark parent with master checksum

		// done!

	}

	/**
	 * Check for an already-existing bundle identifier in this or any subcollections
	 * 
	 * @param bundleParentPath
	 *            {@code String} with the parent path to begin the bundle search
	 * @return {@code boolean} that indicates whether an existing bundle was found
	 * @throws JargonException
	 *             {@link JargonException}
	 */
	private boolean areThereExistingBundles(final String bundleParentPath) throws JargonException {
		log.info("areThereExistingBundles()");
		if (bundleParentPath == null || bundleParentPath.isEmpty()) {
			throw new IllegalArgumentException("null or empty bundleParentPath");
		}

		log.info("bundleParentPath:{}", bundleParentPath);

		IRODSGenQueryBuilder builder = new IRODSGenQueryBuilder(true, null);
		IRODSQueryResultSetInterface resultSet = null;

		try {
			builder.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_COLL_PARENT_NAME)
					.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_COLL_NAME)
					.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_COLL_OWNER_ZONE);
		} catch (GenQueryBuilderException e) {
			log.error("error building query for collections:{}", bundleParentPath, e);
			throw new MetadataQueryException("gen query error", e);
		}

		builder.addConditionAsGenQueryField(RodsGenQueryEnum.COL_COLL_NAME, QueryConditionOperators.LIKE,
				bundleParentPath.trim() + "%");

		builder.addConditionAsGenQueryField(RodsGenQueryEnum.COL_META_COLL_ATTR_NAME, QueryConditionOperators.EQUAL,
				ExplodedBundleMetadataUtils.GA4GH_BUNDLE_ID_ATTRIBUTE);

		IRODSGenQueryFromBuilder irodsQuery;
		try {
			irodsQuery = builder.exportIRODSQueryFromBuilder(
					getIrodsAccessObjectFactory().getJargonProperties().getMaxFilesAndDirsQueryMax());
			String targetZone = MiscIRODSUtils.getZoneInPath(bundleParentPath);

			resultSet = getIrodsAccessObjectFactory().getIRODSGenQueryExecutor(getIrodsAccount())
					.executeIRODSQueryAndCloseResultInZone(irodsQuery, 0, targetZone);
		} catch (GenQueryBuilderException | JargonQueryException e) {
			log.error("error in query for bundles", e);
			throw new JargonException("cannot query for bundles", e);
		}

		return resultSet.getTotalRecords() > 0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.irods.jargon.ga4gh.dos.bundlemgmnt.DosBundleManagementService#
	 * deleteDataBundle(java.lang.String)
	 */
	@Override
	public void deleteDataBundle(String dataBundleId) throws BundleNotFoundException, JargonException {
		// TODO Auto-generated method stub

	}

}
