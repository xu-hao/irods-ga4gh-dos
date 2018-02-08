/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gov.nih.niehs.ods.ga4gh.dos.exception.DosDataNotFoundException;
import gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfiguration;
import gov.nih.niehs.ods.ga4gh.services.IdTranslationService;

/**
 * Base translation service assumes identifiers == irods logical path with a
 * notion to plug in other types of identifiers in a different implementation
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class IrodsIdTranslationService extends IdTranslationService {

	public static final Logger log = LoggerFactory.getLogger(IrodsIdTranslationService.class);

	/**
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory} to produce jargon service objects
	 * @param irodsAccount
	 *            {@link IRODSAccount} associated with this user
	 * @param dosConfiguration
	 *            {@link DosConfiguration} that sets site-specific properties
	 **/
	public IrodsIdTranslationService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount, dosConfiguration);
	}

	@Override
	public String irodsPathFromIdentifier(final String identifier) throws DosDataNotFoundException {
		log.info("irodsPathFromIdentifier()");

		if (identifier == null || identifier.isEmpty()) {
			throw new IllegalArgumentException("null or empty identifier");
		}

		log.info("identifier:{}", identifier);

		return identifier;

	}

}
