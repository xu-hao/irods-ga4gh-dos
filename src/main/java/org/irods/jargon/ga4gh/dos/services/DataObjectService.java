/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.model.Ga4ghDataObject;

/**
 * @author Mike Conway - NIEHS
 *
 */
public abstract class DataObjectService extends AbstractDosService {

	/**
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory} to produce jargon service objects
	 * @param irodsAccount
	 *            {@link IRODSAccount} associated with this user
	 * @param dosConfiguration
	 *            {@link DosConfiguration} that sets site-specific properties
	 */
	public DataObjectService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount, dosConfiguration);
	}

	/**
	 * Given a resolved iRODS absolute path, return a ga4gh data object
	 * 
	 * @param irodsAbsolutePath
	 *            {@link String} with the absolute path
	 * @return {@link Ga4ghDataObject} corresponding to that path
	 * @throws DosDataNotFoundException
	 */
	public abstract Ga4ghDataObject retrieveDataObjectFromIrodsPath(final String irodsAbsolutePath)
			throws DosDataNotFoundException;

}
