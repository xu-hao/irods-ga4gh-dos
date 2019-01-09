/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services;

import org.irods.jargon.core.connection.IRODSAccount;

/**
 * Factory for the data object service
 * 
 * @author Mike Conway - NIEHS
 *
 */
public abstract class IrodsDataObjectServiceFactory extends ServiceFactory {

	/**
	 * Get an instance given the account information
	 * 
	 * @param irodsAccount
	 *            {@link IRODSAccount}
	 * @return {@link IrodsDataObjectService}
	 */
	public abstract IrodsDataObjectService instance(IRODSAccount irodsAccount);

}
