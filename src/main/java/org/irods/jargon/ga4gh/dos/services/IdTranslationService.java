/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;

/**
 * Abstract definition of a service to bi-directionally resolve object and other
 * identifiers used by the API
 * 
 * @author Mike Conway - NIEHS
 *
 */
public abstract class IdTranslationService extends AbstractDosService {

	/**
	 * Base constructor
	 * 
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory} to produce jargon service objects
	 * @param irodsAccount
	 *            {@link IRODSAccount} associated with this user
	 * @param dosConfiguration
	 *            {@link DosConfiguration} that sets site-specific properties
	 */
	public IdTranslationService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount, dosConfiguration);
	}

	/**
	 * Resolve an iRODS absolute path from a given identifier
	 * 
	 * @param identifier
	 *            {@link String} at the DOS level
	 * @return {@code String} with the iRODS absolute path given the identifier
	 * @throws DosDataNotFoundException
	 */
	public abstract String irodsPathFromIdentifier(final String identifier) throws DosDataNotFoundException;

}
