/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.core.checksum.ChecksumValue;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.FileNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.DataObjectChecksumUtilitiesAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.DataObject;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.nih.niehs.ods.ga4gh.dos.exception.DosDataNotFoundException;
import gov.nih.niehs.ods.ga4gh.dos.exception.DosSystemException;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghChecksum;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghDataObject;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghURL;
import gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfiguration;
import gov.nih.niehs.ods.ga4gh.services.DataObjectService;

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
	public Ga4ghDataObject retrieveDataObjectFromIrodsPath(String irodsAbsolutePath) throws DosDataNotFoundException {
		log.info("retrieveDataObjectFromIrodsPath()");
		if (irodsAbsolutePath == null || irodsAbsolutePath.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsAbsolutePath");
		}

		log.info("irodsAbsolutePath:{}", irodsAbsolutePath);

		try {
			DataObjectAO dataObjectAO = irodsAccessObjectFactory.getDataObjectAO(irodsAccount);
			DataObject dataObject = dataObjectAO.findByAbsolutePath(irodsAbsolutePath);
			return ga4ghDataObjectFromIrodsDataObject(dataObject);
		} catch (FileNotFoundException fnf) {
			log.warn("file not found:{}", irodsAbsolutePath, fnf);
			throw new DosDataNotFoundException(fnf);
		} catch (JargonException e) {
			log.error("error accessing iRODS", e);
			throw new DosSystemException("exception connecting to iRODS", e);
		}

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
	protected Ga4ghDataObject ga4ghDataObjectFromIrodsDataObject(final DataObject irodsDataObject)
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
		ga4ghDataObject.setId(null); // TODO: add id txltn service to reverse map
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
