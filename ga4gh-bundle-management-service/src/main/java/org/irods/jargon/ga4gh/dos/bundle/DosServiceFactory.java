package org.irods.jargon.ga4gh.dos.bundle;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.DosBundleManagementService;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;

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
	 * @param irodsAccount {@link IRODSAccount}
	 * @return {@link DosService} instance
	 */
	DosService instanceDosService(IRODSAccount irodsAccount);

	/**
	 * Create a {@link DosBundleManagementService} implementation
	 * 
	 * @param irodsAccount {@link IRODSAccount}
	 * @return {@link DosBundleManagementService
	 */
	DosBundleManagementService instanceDosBundleManagementService(IRODSAccount irodsAccount);

	/**
	 * Set the configuration for the service
	 * 
	 * @param dosConfiguration {@link DosConfiguration}
	 */
	void setDosConfiguration(DosConfiguration dosConfiguration);

	/**
	 * Get the configuration for the service
	 * 
	 * @return {@link DosConfiguration}
	 */
	DosConfiguration getDosConfiguration();

	/**
	 * Set the irodsAccessObjectFactory used to create iRODS components
	 * 
	 * @param irodsAccessObjectFactory {@link IRODSAccessObjectFactory}
	 */
	void setIrodsAccessObjectFactory(IRODSAccessObjectFactory irodsAccessObjectFactory);

	/**
	 * Get the factory for iRODS components
	 * 
	 * @return {@link IRODSAccessObjectFactory}
	 */
	IRODSAccessObjectFactory getIrodsAccessObjectFactory();

}