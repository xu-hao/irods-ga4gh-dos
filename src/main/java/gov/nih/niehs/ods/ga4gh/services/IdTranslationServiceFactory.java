/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.rest.configuration.DosConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Mike Conway - NIEHS
 *
 */
public abstract class IdTranslationServiceFactory {

	/**
	 * Required factory for Jargon service objects {@link IRODSAccessObjectFactory}
	 */
	@Autowired
	private IRODSAccessObjectFactory irodsAccessObjectFactory;

	/**
	 * Required {@link DosConfiguration} with properties and settings
	 */
	@Autowired
	private DosConfiguration dosConfiguration;

	/**
	 * 
	 */
	public IdTranslationServiceFactory() {
	}

	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		return irodsAccessObjectFactory;
	}

	public void setIrodsAccessObjectFactory(IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}

	public DosConfiguration getDosConfiguration() {
		return dosConfiguration;
	}

	public void setDosConfiguration(DosConfiguration dosConfiguration) {
		this.dosConfiguration = dosConfiguration;
	}

	/**
	 * Create an instance of the underlying service
	 * 
	 * @param irodsAccount
	 *            {@link IRODSAccount} for the authenticated user
	 * @return {@link IdTranslationService} instance
	 */
	public abstract IdTranslationService insance(final IRODSAccount irodsAccount);

}
