/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.irods.jargon.core.checksum.ChecksumValue;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.FileNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.DataObjectChecksumUtilitiesAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.Collection;
import org.irods.jargon.core.pub.domain.DataObject;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.core.query.PagingAwareCollectionListing;
import org.irods.jargon.core.query.QueryConditionOperators;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionService;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.exception.DosSystemException;
import org.irods.jargon.ga4gh.dos.model.Checksum;
import org.irods.jargon.ga4gh.dos.model.DataBundle;
import org.irods.jargon.ga4gh.dos.model.URL;
import org.irods.jargon.ga4gh.dos.services.IrodsDataObjectService;
import org.irods.jargon.mdquery.MetadataQuery;
import org.irods.jargon.mdquery.MetadataQuery.QueryType;
import org.irods.jargon.mdquery.MetadataQueryElement;
import org.irods.jargon.mdquery.exception.MetadataQueryException;
import org.irods.jargon.mdquery.service.MetadataQueryService;
import org.irods.jargon.mdquery.service.MetadataQueryServiceImpl;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mike Conway - NIEHS
 *
 */
public class IrodsDataObjectServiceImpl extends IrodsDataObjectService {

	public static final Logger log = LoggerFactory.getLogger(IrodsDataObjectServiceImpl.class);

	private final DataTypeResolutionService dataTypeResolutionService;

	/**
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory} to produce jargon service objects
	 * @param irodsAccount
	 *            {@link IRODSAccount} associated with this user
	 * @param dosConfiguration
	 *            {@link DosConfiguration} that sets site-specific properties
	 **/
	public IrodsDataObjectServiceImpl(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosConfiguration dosConfiguration, DataTypeResolutionService dataTypeResolutionService) {
		super(irodsAccessObjectFactory, irodsAccount, dosConfiguration);
		if (dataTypeResolutionService == null) {
			throw new IllegalArgumentException("null dataTypeResolutionService");
		}

		this.dataTypeResolutionService = dataTypeResolutionService;
	}

	@Override
	public DataBundle retrieveDataBundleFromId(String id) throws DosDataNotFoundException {
		log.info("retrieveDataBundleFromId()");
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("null or empty id");
		}

		log.info("id:{}", id);

		try {
			String irodsPath = irodsPathFromCollectionId(id);
			log.info("resolved to path:{}", irodsPath);

			CollectionAO collectionAO = this.getIrodsAccessObjectFactory().getCollectionAO(getIrodsAccount());
			Collection collection = collectionAO.findByAbsolutePath(irodsPath);

			IrodsBundleManagementService bundleManagementService = new IrodsBundleManagementService(
					this.getIrodsAccessObjectFactory(), this.getIrodsAccount());

			log.info("getting rollup of objects in this bundle");
			List<BundleObjectRollup> objects = bundleManagementService.retrieveDataObjectsInBundle(irodsPath);
			Checksum ga4ghChecksum;
			DataBundle dataBundle = new DataBundle();

			// create a checksum of all the checksums in the object
			StringBuffer checksumBuffer = new StringBuffer();

			for (BundleObjectRollup bundleObj : objects) {
				checksumBuffer.append(bundleObj.getChecksumValue().getChecksumStringValue());
				dataBundle.addDataObjectIdsItem(bundleObj.getGuid());
				dataBundle.addAliasesItem(bundleObj.getIrodsPath());
			}

			String hdigest = DigestUtils.md5Hex(checksumBuffer.toString());
			ga4ghChecksum = new Checksum();
			ga4ghChecksum.setChecksum(hdigest);
			ga4ghChecksum.setType("MD5");

			dataBundle.addChecksumsItem(ga4ghChecksum);
			dataBundle.(new DateTime(collection.getCreatedAt()));
			dataBundle.setId(id);
			dataBundle.setUpdated(new DateTime(collection.getModifiedAt()));
			dataBundle.setVersion("1.0"); // TODO: what to do here

			/*
			 * System metadata
			 */

			dataBundle.getSystemMetadata().put("OwnerZone", collection.getCollectionOwnerZone());
			dataBundle.getSystemMetadata().put("OwnerName", collection.getCollectionOwnerName());
			dataBundle.getSystemMetadata().put("SpecColltype", collection.getSpecColType().name());

			/*
			 * User metadata, right now raw avus
			 */

			List<MetaDataAndDomainData> metadata = collectionAO
					.findMetadataValuesForCollection(collection.getAbsolutePath(), 0);
			for (MetaDataAndDomainData metaVal : metadata) {
				dataBundle.getUserMetadata().put(metaVal.getAvuAttribute(), metaVal.getAvuValue());
			}

			log.info("data bundle ready:{}", dataBundle);
			return dataBundle;

		} catch (JargonException | JargonQueryException e) {
			log.error("error accessing iRODS", e);
			throw new DosSystemException("exception connecting to iRODS", e);
		}

	}

	private String irodsPathFromCollectionId(String id) throws DataNotFoundException {
		log.info("irodsPathFromCollectionId()");
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("null or empty id");
		}

		log.info("id:{}", id);

		/* do a metadata query on the collection id AVU */

		return executeMetadataQueryForPath(id, QueryType.COLLECTIONS);

	}

	/**
	 * @param id
	 * @return
	 * @throws DataNotFoundException
	 */
	private String executeMetadataQueryForPath(String id, QueryType queryType) throws DataNotFoundException {
		MetadataQueryService metadataQueryService = new MetadataQueryServiceImpl(this.getIrodsAccessObjectFactory(),
				irodsAccount);

		MetadataQuery metadataQuery = new MetadataQuery();
		MetadataQueryElement element = new MetadataQueryElement();
		element.setAttributeName(GuidService.GUID_ATTRIBUTE);
		element.setOperator(QueryConditionOperators.EQUAL);
		@SuppressWarnings("serial")
		List<String> vals = new ArrayList<String>() {
			{
				add(id);
			}
		};
		element.setAttributeValue(vals);

		metadataQuery.setQueryType(queryType);
		metadataQuery.getMetadataQueryElements().add(element);

		try {
			PagingAwareCollectionListing result = metadataQueryService.executeQuery(metadataQuery);
			if (result.getCollectionAndDataObjectListingEntries().isEmpty()) {
				log.warn("no result in query for id:{}", id);
				throw new DataNotFoundException("no results");
			}

			return result.getCollectionAndDataObjectListingEntries().get(0).getFormattedAbsolutePath();

		} catch (MetadataQueryException e) {
			log.error("metadata query exception finding id for data object", e);
			throw new DosSystemException("exception in metadata query", e);
		}
	}

	@Override
	public org.irods.jargon.ga4gh.dos.model.DataObject retrieveDataObjectFromId(String id)
			throws DosDataNotFoundException {
		log.info("retrieveDataObjectFromIrodsPath()");
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("null or empty id");
		}

		log.info("id:{}", id);

		try {
			String irodsPath = irodsPathFromDataObjectId(id);
			log.info("resolved to path:{}", irodsPath);
			DataObjectAO dataObjectAO = irodsAccessObjectFactory.getDataObjectAO(irodsAccount);
			DataObject dataObject = dataObjectAO.findByAbsolutePath(irodsPath);
			return dataObjectFromIrodsDataObject(dataObject, id);
		} catch (FileNotFoundException fnf) {
			log.warn("file not found:{}", id, fnf);
			throw new DosDataNotFoundException(fnf);
		} catch (JargonException e) {
			log.error("error accessing iRODS", e);
			throw new DosSystemException("exception connecting to iRODS", e);
		}

	}

	private String irodsPathFromDataObjectId(final String id) throws DataNotFoundException {

		log.info("irodsPathFromDataObjectId()");

		return executeMetadataQueryForPath(id, QueryType.DATA);

	}

	/**
	 * Translate an iRODS {@link DataObject} to a ga4gh {@link Ga4ghDataObject}
	 * 
	 * @param irodsDataObject
	 *            {@link DataObject} that will be translated
	 * @return {@link org.irods.jargon.ga4gh.dos.model.DataObject}
	 * @throws DataNotFoundException,
	 *             JargonException
	 */
	private org.irods.jargon.ga4gh.dos.model.DataObject dataObjectFromIrodsDataObject(final DataObject irodsDataObject,
			final String id) throws DataNotFoundException, JargonException {
		log.info("ga4ghDataObjectFromIrodsDataObject()");
		if (irodsDataObject == null) {
			throw new IllegalArgumentException("null irodsDataObject");
		}

		log.info("dataObject:{}", irodsDataObject);
		log.debug("getting checksum");
		DataObjectChecksumUtilitiesAO dataObjectChecksumUtilitiesAO = irodsAccessObjectFactory
				.getDataObjectChecksumUtilitiesAO(irodsAccount);
		IRODSFile irodsFile = this.getIrodsAccessObjectFactory().getIRODSFileFactory(getIrodsAccount())
				.instanceIRODSFile(irodsDataObject.getAbsolutePath());

		ChecksumValue checksumValue = dataObjectChecksumUtilitiesAO.computeChecksumOnDataObject(irodsFile);
		Checksum ga4ghChecksum = new Checksum(); // TODO: might need to update checksum to give hex encoded
													// value in jargon
		ga4ghChecksum.setChecksum(checksumValue.getChecksumStringValue());
		ga4ghChecksum.setType(checksumValue.getChecksumEncoding().toString());
		List<Checksum> checksums = new ArrayList<Checksum>();
		checksums.add(ga4ghChecksum);

		org.irods.jargon.ga4gh.dos.model.DataObject ga4ghDataObject = new org.irods.jargon.ga4gh.dos.model.DataObject.DataObject();
		ga4ghDataObject.setId(id);
		ga4ghDataObject.setChecksums(checksums);
		ga4ghDataObject.setCreated(new DateTime(irodsDataObject.getCreatedAt()));
		ga4ghDataObject.setDescription("irods data object"); // TODO: consider other metadata value?
		ga4ghDataObject.setMimeType(determineMimeType(irodsDataObject.getAbsolutePath()));
		ga4ghDataObject.setName(irodsDataObject.getAbsolutePath());
		ga4ghDataObject.setSize(String.valueOf(irodsDataObject.getDataSize()));
		ga4ghDataObject.setUpdated(new DateTime(irodsDataObject.getUpdatedAt()));
		ga4ghDataObject.setUrls(determineUrls(irodsDataObject.getAbsolutePath()));
		ga4ghDataObject.setVersion("0");
		return ga4ghDataObject;
	}

	protected String determineMimeType(final String irodsPath) throws DataNotFoundException, JargonException {
		log.info("determineMimeType()");

		if (irodsPath == null || irodsPath.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsPath");
		}

		log.info("irodsPath:{}", irodsPath);
		return dataTypeResolutionService.quickMimeType(irodsPath);

	}

	protected List<URL> determineUrls(final String irodsPath) {
		log.info("determineUrls()");

		if (irodsPath == null || irodsPath.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsPath");
		}

		log.info("irodsPath:{}", irodsPath);
		List<URL> urls = new ArrayList<URL>();

		if (this.getDosConfiguration().getUrlPrefix() == null || this.getDosConfiguration().getUrlPrefix().isEmpty()) {
			log.info("no url base configured");
		} else {

			URL ga4ghUrl = new URL();
			StringBuilder sb = new StringBuilder();
			sb.append(this.getDosConfiguration().getUrlPrefix());
			sb.append(irodsPath);
			ga4ghUrl.setUrl(sb.toString());
			urls.add(ga4ghUrl);
			// TODO: add system and user metadata?
		}

		return urls;

	}

}
