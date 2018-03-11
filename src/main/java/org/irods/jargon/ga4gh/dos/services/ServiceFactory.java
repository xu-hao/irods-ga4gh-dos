package org.irods.jargon.ga4gh.dos.services;

import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;

public class ServiceFactory {

	/**
	 * Required factory for Jargon service objects {@link IRODSAccessObjectFactory}
	 */
	private IRODSAccessObjectFactory irodsAccessObjectFactory;
	/**
	 * Required {@link PropsBasedDosConfiguration} with properties and settings
	 */

	private DosConfiguration dosConfiguration;

	public ServiceFactory() {
		super();
	}

	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		return irodsAccessObjectFactory;
	}

	public void setIrodsAccessObjectFactory(IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}

	/**
	 * @return the dosConfiguration
	 */
	public DosConfiguration getDosConfiguration() {
		return dosConfiguration;
	}

	/**
	 * @param dosConfiguration the dosConfiguration to set
	 */
	public void setDosConfiguration(DosConfiguration dosConfiguration) {
		this.dosConfiguration = dosConfiguration;
	}

}