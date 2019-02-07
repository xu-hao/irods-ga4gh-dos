/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundlemgmnt.impl;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;
import org.irods.jargon.core.checksum.ChecksumManager;
import org.irods.jargon.core.checksum.ChecksumManagerImpl;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.exception.JargonRuntimeException;
import org.irods.jargon.core.protovalues.ChecksumEncodingEnum;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.pub.domain.Collection;
import org.irods.jargon.core.pub.io.IRODSFileImpl;
import org.irods.jargon.core.query.AVUQueryElement;
import org.irods.jargon.core.query.AVUQueryElement.AVUQueryPart;
import org.irods.jargon.core.query.GenQueryBuilderException;
import org.irods.jargon.core.query.IRODSGenQueryBuilder;
import org.irods.jargon.core.query.IRODSGenQueryFromBuilder;
import org.irods.jargon.core.query.IRODSQueryResultSetInterface;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.core.query.QueryConditionOperators;
import org.irods.jargon.core.query.RodsGenQueryEnum;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.core.utils.MiscIRODSUtils;
import org.irods.jargon.datautils.visitor.IrodsVisitedComposite;
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
	private final CollectionAO collectionAO;

	/**
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory}
	 * @param irodsAccount
	 *            {@link IRODSAccount}
	 */
	public ExplodedDosBundleManagementServiceImpl(IRODSAccessObjectFactory irodsAccessObjectFactory,
			IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
		try {
			this.collectionAO = irodsAccessObjectFactory.getCollectionAO(irodsAccount);
		} catch (JargonException e) {
			log.error("cannot initialize collectionAO for class", e);
			throw new JargonRuntimeException("collectionAO");
		}
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

		checksumAndMarkObjectsInBundle(bundleRootAbsolutePath);

		log.info("successfully created bundle with id:{}", bundleId);
		return bundleId;

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
	protected boolean areThereExistingBundles(final String bundleParentPath) throws JargonException {
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
	public void deleteDataBundle(final String dataBundleId) throws JargonException {
		log.info("deleteDataBundle");
		// resolve the path and then go thru and remove all of the metadata values
		log.info("dataBundleId:{}", dataBundleId);
		String irodsPath = this.bundleIdToIrodsPath(dataBundleId);
		log.info("irodsPath:{}", irodsPath);

		DataBundleUnwindVisitor visitor = new DataBundleUnwindVisitor(this.getIrodsAccessObjectFactory(),
				this.getIrodsAccount());

		log.info("beginning the recursive prep of the data bundle...");

		IRODSFileImpl startingPoint;
		try {
			startingPoint = (IRODSFileImpl) getIrodsAccessObjectFactory().getIRODSFileFactory(getIrodsAccount())
					.instanceIRODSFile(irodsPath);
			if (!startingPoint.isDirectory()) {
				log.info("starting point is not a leaf node:{}", startingPoint);
				throw new JargonException("cannot start a crawl on a leaf node!");
			}

			IrodsVisitedComposite startingComposite = new IrodsVisitedComposite(startingPoint);
			startingComposite.accept(visitor);
			log.info("....crawl complete!");

			/*
			 * Find and remove the collection bundle id and checksum
			 */

			CollectionAO collectionAO = this.getIrodsAccessObjectFactory().getCollectionAO(getIrodsAccount());
			List<AVUQueryElement> avuQuery = new ArrayList<AVUQueryElement>();

			try {
				avuQuery.add(AVUQueryElement.instanceForValueQuery(AVUQueryPart.UNITS, QueryConditionOperators.EQUAL,
						ExplodedBundleMetadataUtils.GA4GH_BUNDLE_UNIT_PREFIX));

				/**
				 * Search by unit and delete and ga4gh bundle avus, that should get checksum and
				 * id
				 */
				List<MetaDataAndDomainData> metadata = collectionAO
						.findMetadataValuesByMetadataQueryForCollection(avuQuery, irodsPath);
				log.debug("located collection metadata:{}", metadata);

				for (MetaDataAndDomainData metadataValue : metadata) {
					collectionAO.deleteAVUMetadata(irodsPath, AvuData.instance(metadataValue.getAvuAttribute(),
							metadataValue.getAvuValue(), metadataValue.getAvuUnit()));
				}

			} catch (JargonQueryException e) {
				log.error("Error creating query for bundles", e);
				throw new JargonException("bundle query error", e);
			}

			log.info("bundle and checksumming complete");
		} catch (JargonException e) {
			log.error("error in removal of a data bundle", e);
			throw new JargonRuntimeException("error lauching visitor", e);
		}

	}

	@Override
	public String bundleIdToIrodsPath(final String dataBundleId) throws BundleNotFoundException, JargonException {
		log.info("bundleIdToIrodsPath()");
		if (dataBundleId == null || dataBundleId.isEmpty()) {
			throw new IllegalArgumentException("null or empty dataBundleId");
		}
		log.info("dataBundleId to resolve:{}", dataBundleId);

		CollectionAO collectionAO = this.getIrodsAccessObjectFactory().getCollectionAO(getIrodsAccount());
		List<AVUQueryElement> avuQuery = new ArrayList<AVUQueryElement>();

		try {
			avuQuery.add(AVUQueryElement.instanceForValueQuery(AVUQueryPart.ATTRIBUTE, QueryConditionOperators.EQUAL,
					ExplodedBundleMetadataUtils.GA4GH_BUNDLE_ID_ATTRIBUTE));
			avuQuery.add(AVUQueryElement.instanceForValueQuery(AVUQueryPart.VALUE, QueryConditionOperators.EQUAL,
					dataBundleId.trim()));
			List<Collection> collections = collectionAO.findDomainByMetadataQuery(avuQuery);
			if (collections.isEmpty()) {
				log.info("no bundle found via query:{}", avuQuery);
				throw new BundleNotFoundException("bundle was not found");
			}

			if (collections.size() > 1) {
				log.warn("more than one collection returned with same id. Will return first:{}", collections);
			}

			log.info("found collection:{}", collections.get(0));
			return collections.get(0).getAbsolutePath();

		} catch (JargonQueryException e) {
			log.error("Error creating query for bundles", e);
			throw new JargonException("bundle query error", e);
		}

	}

	/**
	 * Return the {@link MessageDigest} format that matches the iRODS default value
	 * 
	 * @return {@link MessageDigest} that matches the iRODS default algorithm
	 * 
	 */
	@Override
	public MessageDigest determineMessageDigestFromIrods() {
		log.info("determineMessageDigestFromIrods()");
		ChecksumManager checksumManager = new ChecksumManagerImpl(this.getIrodsAccount(),
				this.getIrodsAccessObjectFactory());
		ChecksumEncodingEnum checksumEncoding;
		try {
			checksumEncoding = checksumManager.determineChecksumEncodingForTargetServer();
		} catch (JargonException e) {
			log.error("exception determining checksum encoding on iRODS", e);
			throw new JargonRuntimeException("could not determine iRODS checksum encoding", e);
		}

		try {
			switch (checksumEncoding) {
			case MD5:
				return MessageDigest.getInstance("MD5");
			case SHA256:
				return MessageDigest.getInstance("SHA-256");
			default:
				log.error("cannot find a MessageDigest encoding for iRODS checksum encoding:{}", checksumEncoding);
				throw new JargonRuntimeException("cannot find checksum messagedigest format for iRODS encoding");
			}
		} catch (NoSuchAlgorithmException e) {
			log.error("No algorithm for iRODS checksum encoding:{}", checksumEncoding, e);
			throw new JargonRuntimeException("No algorithm for iRODS checksum encoding", e);
		}

	}

	/**
	 * Recursive utility method will generate bundle and data object GUIDS in place,
	 * mark with the appropriate metadata, set checksums on each data object, and
	 * mark the bundle with a 'checksum of checksums' in hex string format.
	 * 
	 * @param startingCollectionPath
	 *            {@code String} with the iRODS path that is the top of the data
	 *            bundle
	 */
	public void checksumAndMarkObjectsInBundle(final String startingCollectionPath) {
		log.info("checksumAndMarkObjectsInBundle()");
		if (startingCollectionPath == null || startingCollectionPath.isEmpty()) {
			throw new IllegalArgumentException("null or empty startingCollectionPath");

		}
		log.info("startingCollectionPath:{}", startingCollectionPath);

		DataBundleChecksumVisitor visitor = new DataBundleChecksumVisitor(this.getIrodsAccessObjectFactory(),
				this.getIrodsAccount(), this.determineMessageDigestFromIrods());

		log.info("beginning the recursive prep of the data bundle...");

		IRODSFileImpl startingPoint;
		try {
			startingPoint = (IRODSFileImpl) getIrodsAccessObjectFactory().getIRODSFileFactory(getIrodsAccount())
					.instanceIRODSFile(startingCollectionPath);
			if (!startingPoint.isDirectory()) {
				log.info("starting point is not a leaf node:{}", startingPoint);
				throw new JargonException("cannot start a crawl on a leaf node!");
			}

			IrodsVisitedComposite startingComposite = new IrodsVisitedComposite(startingPoint);
			startingComposite.accept(visitor);
			log.info("....crawl complete!");

			// compute the master checksum in hex format

			visitor.getChecksumAccumulator().close();
			MessageDigest digest = visitor.getChecksumAccumulator().getMessageDigest();
			String hexDigest = Hex.encodeHexString(digest.digest());
			log.info("final digest:{}", hexDigest);
			AvuData masterChecksumAvu = ExplodedBundleMetadataUtils.createBundleMasterChecksumAvu(hexDigest);
			collectionAO.addAVUMetadata(startingCollectionPath, masterChecksumAvu);
			log.info("bundle and checksumming complete");
		} catch (JargonException | IOException e) {
			log.error("error in setup of data bundle", e);
			throw new JargonRuntimeException("error lauching visitor", e);
		}

	}

}
