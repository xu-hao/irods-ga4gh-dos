package gov.nih.niehs.ods.ga4gh.services;

import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface;
import gov.nih.niehs.ods.ga4gh.rest.configuration.PropsBasedDosConfiguration;

public class ServiceFactory {

	/**
	 * Required factory for Jargon service objects {@link IRODSAccessObjectFactory}
	 */
	@Autowired
	private IRODSAccessObjectFactory irodsAccessObjectFactory;
	/**
	 * Required {@link PropsBasedDosConfiguration} with properties and settings
	 */
	@Autowired
	private DosConfigInterface dosConfiguration;

	public ServiceFactory() {
		super();
	}

	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		return irodsAccessObjectFactory;
	}

	public void setIrodsAccessObjectFactory(IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}

	public DosConfigInterface getDosConfiguration() {
		return dosConfiguration;
	}

	public void setDosConfiguration(DosConfigInterface dosConfiguration) {
		this.dosConfiguration = dosConfiguration;
	}

}