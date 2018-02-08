/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.FileNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.DataObjectChecksumUtilitiesAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.DataObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.nih.niehs.ods.ga4gh.dos.exception.DosDataNotFoundException;
import gov.nih.niehs.ods.ga4gh.dos.exception.DosSystemException;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghDataObject;
import gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfiguration;
import gov.nih.niehs.ods.ga4gh.services.DataObjectService;

/**
 * @author Mike Conway - NIEHS
 *
 */
public class IrodsDataObjectService extends DataObjectService {

	public static final Logger log = LoggerFactory.getLogger(IrodsDataObjectService.class);

	/**
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory} to produce jargon service objects
	 * @param irodsAccount
	 *            {@link IRODSAccount} associated with this user
	 * @param dosConfiguration
	 *            {@link DosConfiguration} that sets site-specific properties
	 **/
	public IrodsDataObjectService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount, dosConfiguration);
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
	 */
	protected Ga4ghDataObject ga4ghDataObjectFromIrodsDataObject(final DataObject irodsDataObject) {
		log.info("ga4ghDataObjectFromIrodsDataObject()");
		if (irodsDataObject == null) {
			throw new IllegalArgumentException("null irodsDataObject");
		}

		log.info("dataObject:{}", irodsDataObject);
		
		DataObjectChecksumUtilitiesAO dataObjectChecksumUtilitiesAO = irodsAccessObjectFactory.getDataObjectChecksumUtilitiesAO(irodsAccount);
		compute checksum stuff here

		Ga4ghDataObject ga4ghDataObject = new Ga4ghDataObject();
		ga4ghDataObject.setId(null); // TODO: add id txltn service to reverse map
		ga4ghDataObject.set

	}

}
