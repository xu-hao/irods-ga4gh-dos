/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;

import gov.nih.niehs.ods.ga4gh.dos.exception.DosDataNotFoundException;
import gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface;
import gov.nih.niehs.ods.ga4gh.rest.configuration.PropsBasedDosConfiguration;

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
	 *            {@link PropsBasedDosConfiguration} that sets site-specific properties
	 */
	public IdTranslationService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosConfigInterface dosConfiguration) {
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
