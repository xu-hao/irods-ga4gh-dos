/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundle.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.DosBundleManagementService;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of a service factory that can produce DOS implementation
 * class. This allows injection via Spring and late resolution of the
 * {@link IRODSAccount}
 * 
 * @author Mike Conway - NIEHS
 *
 */
@Component
public class ExplodedDosServiceFactoryImpl implements DosServiceFactory {

	@Autowired
	private DosConfiguration dosConfiguration;

	/**
	 * {@link IRODSAccessObjectFactory} that can produce service objects
	 */
	private IRODSAccessObjectFactory irodsAccessObjectFactory;

	public ExplodedDosServiceFactoryImpl(IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}

	public ExplodedDosServiceFactoryImpl() {
	}

	@Override
	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		return irodsAccessObjectFactory;
	}

	@Override
	public void setIrodsAccessObjectFactory(IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}

	@Override
	public DosService instanceDosService(IRODSAccount irodsAccount) {
		return new ExplodedDosServiceImpl(this.irodsAccessObjectFactory, irodsAccount, this, dosConfiguration);
	}

	@Override
	public DosConfiguration getDosConfiguration() {
		return dosConfiguration;
	}

	@Override
	public void setDosConfiguration(DosConfiguration dosConfiguration) {
		this.dosConfiguration = dosConfiguration;
	}

	@Override
	public DosBundleManagementService instanceDosBundleManagementService(IRODSAccount irodsAccount) {
		return new ExplodedDosBundleManagementServiceImpl(this.irodsAccessObjectFactory, irodsAccount, this,
				dosConfiguration);

	}

}
