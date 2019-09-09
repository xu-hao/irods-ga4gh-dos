package org.irods.jargon.ga4gh.dos.bundle.impl;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.core.checksum.ChecksumValue;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.DataObjectChecksumUtilitiesAO;
import org.irods.jargon.core.pub.EnvironmentalInfoAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.pub.domain.Collection;
import org.irods.jargon.core.pub.domain.DataObject;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.query.AVUQueryElement;
import org.irods.jargon.core.query.AVUQueryElement.AVUQueryPart;
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
import org.irods.jargon.extensions.datatyper.DataTypeResolutionServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.AbstractDosService;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.BundleInfoAndPath;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsAccessMethod;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataBundle;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataObject;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.exception.BundleNotFoundException;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.exception.DosSystemException;
import org.irods.jargon.ga4gh.dos.model.AccessMethod;
import org.irods.jargon.ga4gh.dos.utils.ExplodedBundleMetadataUtils;
import org.irods.jargon.ticket.TicketAdminService;
import org.irods.jargon.ticket.TicketServiceFactory;
import org.irods.jargon.ticket.TicketServiceFactoryImpl;
import org.irods.jargon.ticket.packinstr.TicketCreateModeEnum;
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
	public static final String BASIC_AUTH_HEADER_PREFIX = "Authorization: ";

	private final TicketServiceFactory ticketServiceFactory;
	private DataTypeResolutionServiceFactory dataTypeResolutionServiceFactory;

	public ExplodedDosServiceImpl(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosServiceFactory dosServiceFactory, DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount, dosServiceFactory, dosConfiguration);
		this.ticketServiceFactory = new TicketServiceFactoryImpl(irodsAccessObjectFactory);
	}

	@Override
	public BundleInfoAndPath resolveId(final String id) throws DosDataNotFoundException, DosSystemException {
		log.info("resolveIdToObjStat()");
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("null or empty id");
		}

		log.info("id:{}", id);

		BundleInfoAndPath bundleInfoAndPath = null;

		/*
		 * Try and resolve as a bundle id
		 */

		try {
			bundleInfoAndPath = this.bundleIdToIrodsPath(id);
			log.info("resolved to:{}", bundleInfoAndPath);
			return bundleInfoAndPath;
		} catch (DosDataNotFoundException dnf) {
			log.info("id is not a bundle");
		} catch (JargonException e) {
			log.error("jargon exception", e);
			throw new DosSystemException(e);
		}

		/*
		 * Try and resolve as data object, or will return not found
		 */

		log.info("attempting to find as a data object");

		try {
			bundleInfoAndPath = this.dataObjectIdToIrodsPath(id);
		} catch (DosDataNotFoundException dnf) {
			log.warn("not found for:{}", id);
			throw new DosDataNotFoundException("object not found");
		} catch (JargonException e) {
			log.error("jargon exception", e);
			throw new DosSystemException(e);
		}
		log.info("resolved as data object:{}", bundleInfoAndPath);
		return bundleInfoAndPath;

	}

	@Override
	public IrodsDataBundle retrieveDataBundle(final BundleInfoAndPath bundleInfoAndPath)
			throws DosDataNotFoundException {
		log.info("retrieveDataBundle()");

		if (bundleInfoAndPath == null) {
			throw new IllegalArgumentException("null or empty bundleInfoAndPath");
		}

		if (!bundleInfoAndPath.isCollection()) {
			throw new IllegalArgumentException("id is not for a collection");
		}

		log.info("bundleInfoAndPath:{}", bundleInfoAndPath);

		try {

			CollectionAO collectionAO = this.getIrodsAccessObjectFactory().getCollectionAO(getIrodsAccount());
			Collection collection = collectionAO.findByAbsolutePath(bundleInfoAndPath.getIrodsPath());

			log.info("getting rollup of objects in this bundle");
			List<IrodsDataObject> objects = retrieveDataObjectsInBundle(bundleInfoAndPath.getId(),
					this.getDosConfiguration().getDrsRestUrlEndpoint());

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

			EnvironmentalInfoAO environmentalInfoAO = this.getIrodsAccessObjectFactory()
					.getEnvironmentalInfoAO(getIrodsAccount());

			irodsDataBundle.setCreateDate(collection.getCreatedAt());
			irodsDataBundle.setDescription("iRODS exploded bundle collection"); // TODO: add special description avu?
			irodsDataBundle.setIrodsAbsolutePath(collection.getAbsolutePath());
			irodsDataBundle.setUpdatedDate(collection.getModifiedAt());
			irodsDataBundle.setVersion("0"); // TODO: add special version avu?
			irodsDataBundle.setBundleChecksumType(
					environmentalInfoAO.retrieveClientHints(false).getHashScheme().toLowerCase());

			log.info("data bundle ready:{}", irodsDataBundle);
			return irodsDataBundle;

		} catch (JargonException | JargonQueryException e) {
			log.error("error accessing iRODS", e);
			throw new DosSystemException("exception connecting to iRODS", e);
		}

	}

	@Override
	public IrodsDataObject retrieveDataObject(final BundleInfoAndPath bundleInfoAndPath)
			throws DosDataNotFoundException, DosSystemException {
		log.info("retrieveDataObject()");
		if (bundleInfoAndPath == null) {
			throw new IllegalArgumentException("null or empty bundleInfoAndPath");
		}

		if (bundleInfoAndPath.isCollection()) {
			throw new IllegalArgumentException("bundleInfoAndPath is for a collection");
		}

		log.info("bundleInfoAndPath:{}", bundleInfoAndPath);

		try {
			DataObjectAO dataObjectAO = this.getIrodsAccessObjectFactory().getDataObjectAO(getIrodsAccount());
			DataObjectChecksumUtilitiesAO dataObjectChecksumUtilitiesAO = this.getIrodsAccessObjectFactory()
					.getDataObjectChecksumUtilitiesAO(getIrodsAccount());
			DataObject dataObject = dataObjectAO.findByAbsolutePath(bundleInfoAndPath.getIrodsPath());
			dataObjectAO.findMetadataValuesForDataObject(bundleInfoAndPath.getIrodsPath());

			IrodsDataObject irodsDataObject;
			irodsDataObject = new IrodsDataObject();
			irodsDataObject.setFileName(dataObject.getDataName());
			irodsDataObject.setGuid(bundleInfoAndPath.getId());
			irodsDataObject.setAbsolutePath(dataObject.getAbsolutePath());
			irodsDataObject.setSize(dataObject.getDataSize());
			if (dataTypeResolutionServiceFactory == null) {
				log.error("no data type service factory");
				throw new IllegalStateException("Missing dataTypeResolutionServiceFactory");
			}
			org.irods.jargon.extensions.datatyper.DataTypeResolutionService dataTypeResolutionService = dataTypeResolutionServiceFactory
					.instanceDataTypeResolutionService(getIrodsAccount());
			irodsDataObject.setMimeType(dataTypeResolutionService.quickMimeType(dataObject.getAbsolutePath()));
			irodsDataObject.setModifyDate(dataObject.getUpdatedAt());
			irodsDataObject.setCreateDate(dataObject.getCreatedAt());
			ChecksumValue checksumValue = dataObjectChecksumUtilitiesAO
					.retrieveExistingChecksumForDataObject(dataObject.getAbsolutePath());
			irodsDataObject.setChecksum(checksumValue.getBase64ChecksumValue());
			irodsDataObject.setChecksumType(checksumValue.getChecksumEncoding().getTextValue());

			EnvironmentalInfoAO environmentalInfoAO = this.getIrodsAccessObjectFactory()
					.getEnvironmentalInfoAO(getIrodsAccount());
			irodsDataObject.setChecksumType(environmentalInfoAO.retrieveClientHints(false).getHashScheme());

			IRODSFile irodsFile = this.getIrodsAccessObjectFactory().getIRODSFileFactory(getIrodsAccount())
					.instanceIRODSFile(bundleInfoAndPath.getIrodsPath());

			addAccessUrls(irodsDataObject, irodsFile);
			log.info("irodsDataObject:{}", irodsDataObject);
			return irodsDataObject;
		} catch (DataNotFoundException dnf) {
			log.warn("bundle not found:{}", bundleInfoAndPath);
			throw new DosDataNotFoundException("Data not found");
		} catch (JargonException e) {
			log.error("error accessing iRODS", e);
			throw new DosSystemException("exception connecting to iRODS", e);
		}

	}

	private void addAccessUrlsForBundleObjectList(IrodsDataObject irodsDataObject, IRODSFile irodsFile, String drsUrl) {
		IrodsAccessMethod irodsAccessMethod;
		StringBuilder sb = new StringBuilder(drsUrl);
		sb.append("/");
		sb.append(irodsDataObject.getGuid());
		irodsAccessMethod = new IrodsAccessMethod();
		irodsAccessMethod.setUrl(sb.toString());
		log.info("qdded access url:{}", irodsAccessMethod);
		irodsDataObject.getIrodsAccessMethods().add(irodsAccessMethod);
	}

	/**
	 * Add access urls to a data object
	 * 
	 * @param irodsDataObject {@link IrodsDataObject}
	 * @param irodsFile       {@link IRODSFile}
	 */
	private void addAccessUrls(IrodsDataObject irodsDataObject, IRODSFile irodsFile) {
		IrodsAccessMethod irodsAccessMethod;
		if (this.getDosConfiguration().isDrsProvideIrodsUrls()) {
			irodsAccessMethod = new IrodsAccessMethod();
			irodsAccessMethod.setHeaders(new ArrayList<String>());
			irodsAccessMethod.setRegion("");
			irodsAccessMethod.setAccessId(DosService.ACCESS_IRODS);
			irodsAccessMethod.setUrl(irodsFile.toURI().toString());
			irodsAccessMethod.setType(org.irods.jargon.ga4gh.dos.model.AccessMethod.TypeEnum.FILE);
			irodsDataObject.getIrodsAccessMethods().add(irodsAccessMethod);
		}

		if (!this.getDosConfiguration().getDrsRestUrlEndpoint().isEmpty()) {
			irodsAccessMethod = new IrodsAccessMethod();
			irodsAccessMethod.setHeaders(new ArrayList<String>());
			irodsAccessMethod.setRegion("");
			irodsAccessMethod.setUrl("");
			irodsAccessMethod.setAccessId(DosService.ACCESS_REST);
			irodsAccessMethod.setType(org.irods.jargon.ga4gh.dos.model.AccessMethod.TypeEnum.HTTPS);
			irodsDataObject.getIrodsAccessMethods().add(irodsAccessMethod);
		}
	}

	@Override
	public IrodsAccessMethod createAccessUrlForDataObject(final String dataObjectId, final String accessId)
			throws DosDataNotFoundException, DosSystemException {

		log.info("createAccessUrlForDataObject()");
		if (dataObjectId == null || dataObjectId.isEmpty()) {
			throw new IllegalArgumentException("null or empty dataObjectId");
		}

		log.info("dataObjectId:{}", dataObjectId);

		if (accessId == null || accessId.isEmpty()) {
			throw new IllegalArgumentException("null or empty accessId");
		}

		log.info("accessId:{}", accessId);

		log.debug("looking up data object first");
		BundleInfoAndPath bundleInfoAndPath = this.resolveId(dataObjectId);
		IrodsDataObject irodsDataObject = this.retrieveDataObject(bundleInfoAndPath);

		// data object is found

		if (accessId.equals(DosService.ACCESS_REST)) {

			if (this.getDosConfiguration().getDrsRestUrlEndpoint().isEmpty()) {
				throw new DosDataNotFoundException("access method not found");
			}

			IrodsAccessMethod irodsAccessMethod = new IrodsAccessMethod();
			/*
			 * Right now create a ticket and assume rest, for illustrative purposes, this
			 * would be a first release of the new REST api
			 */

			try {
				IRODSFile ticketFile = this.getIrodsAccessObjectFactory().getIRODSFileFactory(getIrodsAccount())
						.instanceIRODSFile(irodsDataObject.getAbsolutePath());

				// TODO: single use ticket?
				TicketAdminService ticketAdminService = this.ticketServiceFactory
						.instanceTicketAdminService(getIrodsAccount());
				String ticketId = ticketAdminService.createTicket(TicketCreateModeEnum.READ, ticketFile, "");
				StringBuilder sb = new StringBuilder();
				sb.append(this.getDosConfiguration().getDrsRestUrlEndpoint());
				sb.append(ticketFile.getAbsolutePath());
				irodsAccessMethod.setAccessId(accessId);
				irodsAccessMethod.setType(AccessMethod.TypeEnum.HTTPS);
				irodsAccessMethod.setUrl(sb.toString());
				sb = new StringBuilder();
				// TODO: some pluggable method of creating a path builder?
				sb.append("X-API-KEY ");
				sb.append(ticketId);
				irodsAccessMethod.setHeaders(new ArrayList<String>());
				irodsAccessMethod.getHeaders().add(sb.toString());
				log.info("irodsAccessMethod:{}", irodsAccessMethod);
				return irodsAccessMethod;
			} catch (JargonException e) {
				log.error("jargon exception in operation", e);
				throw new DosSystemException(e);
			}

		} else {
			log.error("access method not supported:{}", accessId);
			throw new DosDataNotFoundException("unable to find object or access id");
		}

	}

	@Override
	public List<IrodsDataObject> retrieveDataObjectsInBundle(final String bundleId, final String urlPrefix)
			throws BundleNotFoundException, DosSystemException {

		/*
		 * Note that not all values are obtained from iRODS to build the individual
		 * IrodsDataObjects, this is to streamline the operation and save unnecessary
		 * iRODS calls for data not used by the controller that obtains the object list.
		 * 
		 * More extensive information is gathered when retrieving an individual
		 * dataObject by id.
		 */

		log.info("listBundleIds()");
		if (bundleId == null || bundleId.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsAbsolutePath");
		}
		log.info("bundleId:{}", bundleId);

		BundleInfoAndPath bundleInfoAndPath = null;
		try {
			bundleInfoAndPath = this.bundleIdToIrodsPath(bundleId);
		} catch (JargonException e1) {
			log.error("Jargon exception", e1);
			throw new DosSystemException(e1);
		}

		IRODSGenQueryBuilder builder = new IRODSGenQueryBuilder(true, null);
		IRODSQueryResultSetInterface resultSet = null;

		List<IrodsDataObject> dataObjects = new ArrayList<>();

		try {
			builder.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_COLL_NAME)
					.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_DATA_NAME)
					.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_META_DATA_ATTR_VALUE);
		} catch (GenQueryBuilderException e) {
			log.error("error building query for collections:{}", bundleInfoAndPath, e);
			throw new DosSystemException("gen query error", e);
		}

		builder.addConditionAsGenQueryField(RodsGenQueryEnum.COL_COLL_NAME, QueryConditionOperators.LIKE,
				bundleInfoAndPath.getIrodsPath().trim() + "%");

		builder.addConditionAsGenQueryField(RodsGenQueryEnum.COL_META_DATA_ATTR_NAME, QueryConditionOperators.EQUAL,
				ExplodedBundleMetadataUtils.GA4GH_DATA_OBJECT_ID_ATTRIBUTE);

		IRODSGenQueryFromBuilder irodsQuery;
		try {
			irodsQuery = builder.exportIRODSQueryFromBuilder(
					getIrodsAccessObjectFactory().getJargonProperties().getMaxFilesAndDirsQueryMax());
			String targetZone = MiscIRODSUtils.getZoneInPath(bundleInfoAndPath.getIrodsPath());

			resultSet = getIrodsAccessObjectFactory().getIRODSGenQueryExecutor(getIrodsAccount())
					.executeIRODSQueryAndCloseResultInZone(irodsQuery, 0, targetZone);

			IRODSFile irodsFile;
			IrodsDataObject irodsDataObject;
			for (IRODSQueryResultRow row : resultSet.getResults()) {
				irodsFile = this.getIrodsAccessObjectFactory().getIRODSFileFactory(getIrodsAccount())
						.instanceIRODSFile(row.getColumn(0), row.getColumn(1));
				irodsDataObject = new IrodsDataObject();
				irodsDataObject.setFileName(irodsFile.getName());
				irodsDataObject.setGuid(row.getColumn(2));
				irodsDataObject.setAbsolutePath(irodsFile.getAbsolutePath());
				StringBuilder sb = new StringBuilder();
				sb.append(this.getDosConfiguration().getAccessUrl());
				sb.append("/objects");

				addAccessUrlsForBundleObjectList(irodsDataObject, irodsFile, sb.toString());

				log.info("adding data object:{}", irodsDataObject);
				dataObjects.add(irodsDataObject);

			}
		} catch (GenQueryBuilderException | JargonQueryException | JargonException e) {
			log.error("error in query for bundles", e);
			throw new DosSystemException("cannot query for bundles", e);
		}

		return dataObjects;
	}

	public BundleInfoAndPath dataObjectIdToIrodsPath(final String dataObjectId)
			throws DosDataNotFoundException, JargonException {
		log.info("dataObjectIdToIrodsPath()");
		if (dataObjectId == null || dataObjectId.isEmpty()) {
			throw new IllegalArgumentException("null or empty dataObjectId");
		}
		log.info("dataObjectId to resolve:{}", dataObjectId);

		DataObjectAO dataObjectAO = this.getIrodsAccessObjectFactory().getDataObjectAO(getIrodsAccount());
		List<AVUQueryElement> avuQuery = new ArrayList<AVUQueryElement>();

		try {
			avuQuery.add(AVUQueryElement.instanceForValueQuery(AVUQueryPart.ATTRIBUTE, QueryConditionOperators.EQUAL,
					ExplodedBundleMetadataUtils.GA4GH_DATA_OBJECT_ID_ATTRIBUTE));
			avuQuery.add(AVUQueryElement.instanceForValueQuery(AVUQueryPart.VALUE, QueryConditionOperators.EQUAL,
					dataObjectId.trim()));
			List<DataObject> dataObjects = dataObjectAO.findDomainByMetadataQuery(avuQuery);
			if (dataObjects.isEmpty()) {
				log.warn("no record found for dataObjectId:{}", dataObjectId);
				throw new DosDataNotFoundException("data object not found");
			}
			log.info("found dataObjects:{}", dataObjects);
			DataObject dataObject = dataObjects.get(0);
			BundleInfoAndPath bundleInfoAndPath = new BundleInfoAndPath();
			bundleInfoAndPath.setCollection(false);
			bundleInfoAndPath.setId(dataObjectId);
			bundleInfoAndPath.setIrodsPath(dataObject.getAbsolutePath());
			return bundleInfoAndPath;

		} catch (JargonQueryException e) {
			log.error("Error creating query for bundles", e);
			throw new JargonException("bundle query error", e);
		}

	}

	public BundleInfoAndPath bundleIdToIrodsPath(final String bundleId)
			throws DosDataNotFoundException, JargonException {
		log.info("bundleIdToIrodsPath()");
		if (bundleId == null || bundleId.isEmpty()) {
			throw new IllegalArgumentException("null or empty bundleId");
		}
		log.info("bundleId to resolve:{}", bundleId);

		CollectionAO collectionAO = this.getIrodsAccessObjectFactory().getCollectionAO(getIrodsAccount());
		List<AVUQueryElement> avuQuery = new ArrayList<AVUQueryElement>();

		try {
			avuQuery.add(AVUQueryElement.instanceForValueQuery(AVUQueryPart.ATTRIBUTE, QueryConditionOperators.EQUAL,
					ExplodedBundleMetadataUtils.GA4GH_BUNDLE_ID_ATTRIBUTE));
			avuQuery.add(AVUQueryElement.instanceForValueQuery(AVUQueryPart.VALUE, QueryConditionOperators.EQUAL,
					bundleId.trim()));
			List<Collection> collections = collectionAO.findDomainByMetadataQuery(avuQuery);
			if (collections.isEmpty()) {
				log.warn("no record found for bundleId:{}", bundleId);
				throw new DosDataNotFoundException("bundle not found");
			}
			log.info("found collections:{}", collections);
			Collection collection = collections.get(0);
			BundleInfoAndPath bundleInfoAndPath = new BundleInfoAndPath();
			bundleInfoAndPath.setCollection(true);
			bundleInfoAndPath.setId(bundleId);
			bundleInfoAndPath.setIrodsPath(collection.getAbsolutePath());
			return bundleInfoAndPath;
		} catch (JargonQueryException e) {
			log.error("Error creating query for bundles", e);
			throw new JargonException("bundle query error", e);
		}

	}

	/**
	 * @return the dataTypeResolutionServiceFactory
	 */
	public DataTypeResolutionServiceFactory getDataTypeResolutionServiceFactory() {
		return dataTypeResolutionServiceFactory;
	}

	/**
	 * @param dataTypeResolutionServiceFactory the dataTypeResolutionServiceFactory
	 *                                         to set
	 */
	public void setDataTypeResolutionServiceFactory(DataTypeResolutionServiceFactory dataTypeResolutionServiceFactory) {
		this.dataTypeResolutionServiceFactory = dataTypeResolutionServiceFactory;
	}

}
