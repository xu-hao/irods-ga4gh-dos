/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.model.DataBundle;
import org.irods.jargon.ga4gh.dos.model.DataObject;

/**
 * @author Mike Conway - NIEHS
 *
 */
public abstract class IrodsDataObjectService extends AbstractDosService {

	/**
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory} to produce jargon service objects
	 * @param irodsAccount
	 *            {@link IRODSAccount} associated with this user
	 * @param dosConfiguration
	 *            {@link DosConfiguration} that sets site-specific properties
	 */
	public IrodsDataObjectService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount, dosConfiguration);
	}

	/**
	 * Given an id, return the Ga4ghDataObject
	 * 
	 * @param id
	 *            {@link String} with the id for the data object
	 * @return {@link DataObject} corresponding to that id
	 * @throws DosDataNotFoundException
	 */
	public abstract DataObject retrieveDataObjectFromId(final String id) throws DosDataNotFoundException;

	/**
	 * Given an id, return the Ga4ghDataBundle
	 * 
	 * @param id
	 *            {@link String} with the id for the collection that represents the
	 *            bundle
	 * @return {@link DataBundle} corresponding to that id, with the contents
	 * @throws DosDataNotFoundException
	 */
	public abstract DataBundle retrieveDataBundleFromId(String id) throws DosDataNotFoundException;

}
