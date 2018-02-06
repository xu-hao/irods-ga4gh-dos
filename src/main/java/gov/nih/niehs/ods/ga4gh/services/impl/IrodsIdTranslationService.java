/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.rest.configuration.DosConfiguration;

import gov.nih.niehs.ods.ga4gh.dos.exception.DosDataNotFoundException;
import gov.nih.niehs.ods.ga4gh.services.IdTranslationService;

/**
 * @author Mike Conway - NIEHS
 *
 */
public class IrodsIdTranslationService extends IdTranslationService {

	/**
	 * @param irodsAccessObjectFactory
	 * @param irodsAccount
	 * @param dosConfiguration
	 */
	public IrodsIdTranslationService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount, dosConfiguration);
	}

	@Override
	public String irodsPathFromIdentifier(String identifier) throws DosDataNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
