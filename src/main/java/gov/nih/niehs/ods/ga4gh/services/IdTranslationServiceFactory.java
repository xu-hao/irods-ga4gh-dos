/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services;

import org.irods.jargon.core.connection.IRODSAccount;

/**
 * @author Mike Conway - NIEHS
 *
 */
public abstract class IdTranslationServiceFactory extends ServiceFactory {

	/**
	 * Create an instance of the underlying service
	 * 
	 * @param irodsAccount
	 *            {@link IRODSAccount} for the authenticated user
	 * @return {@link IdTranslationService} instance
	 */
	public abstract IdTranslationService instance(final IRODSAccount irodsAccount);

}
