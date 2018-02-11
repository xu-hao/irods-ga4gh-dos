/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services;

import org.irods.jargon.core.connection.IRODSAccount;

/**
 * Factory for the data object service
 * 
 * @author Mike Conway - NIEHS
 *
 */
public abstract class DataObjectServiceFactory extends ServiceFactory {

	/**
	 * Get an instance given the account information
	 * 
	 * @param irodsAccount
	 *            {@link IRODSAccount}
	 * @return {@link DataObjectService}
	 */
	public abstract DataObjectService instance(IRODSAccount irodsAccount);

}
