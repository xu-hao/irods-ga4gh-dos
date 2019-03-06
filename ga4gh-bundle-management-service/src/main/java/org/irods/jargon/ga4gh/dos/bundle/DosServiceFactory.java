package org.irods.jargon.ga4gh.dos.bundle;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.DosBundleManagementService;

/**
 * Describes a factory to produce appropriate implementations for the dos
 * service
 * 
 * @author Mike Conway - NIEHS
 *
 */
public interface DosServiceFactory {

	/**
	 * Create a {@link DosService} implementation that can map GA4GH DOS entities to
	 * an underlying iRODS server
	 * 
	 * @param irodsAccount
	 *            {@link IRODSAccount}
	 * @return {@link DosService} instance
	 */
	DosService instanceDosService(IRODSAccount irodsAccount);

	/**
	 * Create a {@link DosBundleManagementService} implementation
	 * 
	 * @param irodsAccount
	 *            {@link IRODSAccount}
	 * @return {@link DosBundleManagementService
	 */
	DosBundleManagementService instanceDosBundleManagementService(IRODSAccount irodsAccount);

}