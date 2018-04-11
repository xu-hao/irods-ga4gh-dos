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
import org.irods.jargon.core.query.PagingAwareCollectionListing;
import org.irods.jargon.core.query.QueryConditionOperators;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionService;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.exception.DosSystemException;
import org.irods.jargon.ga4gh.dos.model.Ga4ghChecksum;
import org.irods.jargon.ga4gh.dos.model.Ga4ghDataBundle;
import org.irods.jargon.ga4gh.dos.model.Ga4ghDataObject;
import org.irods.jargon.ga4gh.dos.model.Ga4ghURL;
import org.irods.jargon.ga4gh.dos.services.DataObjectService;
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
public class IrodsDataObjectService extends DataObjectService {

	public static final Logger log = LoggerFactory.getLogger(IrodsDataObjectService.class);

	private final DataTypeResolutionService dataTypeResolutionService;

	/**
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory} to produce jargon service objects
	 * @param irodsAccount
	 *            {@link IRODSAccount} associated with this user
	 * @param dosConfiguration
	 *            {@link DosConfiguration} that sets site-specific properties
	 **/
	public IrodsDataObjectService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosConfiguration dosConfiguration, DataTypeResolutionService dataTypeResolutionService) {
		super(irodsAccessObjectFactory, irodsAccount, dosConfiguration);
		if (dataTypeResolutionService == null) {
			throw new IllegalArgumentException("null dataTypeResolutionService");
		}

		this.dataTypeResolutionService = dataTypeResolutionService;
	}

	@Override
	public Ga4ghDataBundle retrieveDataBundleFromId(String id) throws DosDataNotFoundException {
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
			Ga4ghChecksum ga4ghChecksum;
			Ga4ghDataBundle dataBundle = new Ga4ghDataBundle();

			// create a checksum of all the checksums in the object
			StringBuffer checksumBuffer = new StringBuffer();

			for (BundleObjectRollup bundleObj : objects) {
				checksumBuffer.append(bundleObj.getChecksumValue().getChecksumStringValue());
				dataBundle.addDataObjectIdsItem(bundleObj.getGuid());
				dataBundle.addAliasesItem(bundleObj.getIrodsPath());
			}

			String hdigest = DigestUtils.md5Hex(checksumBuffer.toString());
			ga4ghChecksum = new Ga4ghChecksum();
			ga4ghChecksum.setChecksum(hdigest);
			ga4ghChecksum.setType("MD5");

			dataBundle.addChecksumsItem(ga4ghChecksum);
			dataBundle.setCreated(new DateTime(collection.getCreatedAt()));
			dataBundle.setId(id);
			dataBundle.setUpdated(new DateTime(collection.getModifiedAt()));
			dataBundle.setVersion("1.0"); // TODO: what to do here
			log.info("data bundle ready:{}", dataBundle);
			return dataBundle;

		} catch (JargonException e) {
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
	public Ga4ghDataObject retrieveDataObjectFromId(String id) throws DosDataNotFoundException {
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
			return ga4ghDataObjectFromIrodsDataObject(dataObject, id);
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
	 * @return {@link Ga4ghDataObject}
	 * @throws DataNotFoundException,
	 *             JargonException
	 */
	private Ga4ghDataObject ga4ghDataObjectFromIrodsDataObject(final DataObject irodsDataObject, final String id)
			throws DataNotFoundException, JargonException {
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
		Ga4ghChecksum ga4ghChecksum = new Ga4ghChecksum(); // TODO: might need to update checksum to give hex encoded
															// value in jargon
		ga4ghChecksum.setChecksum(checksumValue.getChecksumStringValue());
		ga4ghChecksum.setType(checksumValue.getChecksumEncoding().toString());
		List<Ga4ghChecksum> checksums = new ArrayList<Ga4ghChecksum>();
		checksums.add(ga4ghChecksum);

		Ga4ghDataObject ga4ghDataObject = new Ga4ghDataObject();
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

	protected List<Ga4ghURL> determineUrls(final String irodsPath) {
		log.info("determineUrls()");

		if (irodsPath == null || irodsPath.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsPath");
		}

		log.info("irodsPath:{}", irodsPath);
		List<Ga4ghURL> urls = new ArrayList<Ga4ghURL>();

		if (this.getDosConfiguration().getUrlPrefix() == null || this.getDosConfiguration().getUrlPrefix().isEmpty()) {
			log.info("no url base configured");
		} else {

			Ga4ghURL ga4ghUrl = new Ga4ghURL();
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
