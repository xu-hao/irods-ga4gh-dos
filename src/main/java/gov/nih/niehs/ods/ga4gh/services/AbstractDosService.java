package gov.nih.niehs.ods.ga4gh.services;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.service.AbstractJargonService;

import gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface;
import gov.nih.niehs.ods.ga4gh.rest.configuration.PropsBasedDosConfiguration;

/**
 * Abstract superclass for a service layer object within DOS
 * 
 * @author Mike Conway - NIEHS
 *
 */
public abstract class AbstractDosService extends AbstractJargonService {

	/**
	 * {@link PropsBasedDosConfiguration} with site-specific config parameters
	 */
	private final DosConfigInterface dosConfiguration;

	/**
	 * Base constructor
	 * 
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory} to produce jargon service objects
	 * @param irodsAccount
	 *            {@link IRODSAccount} associated with this user
	 * @param dosConfiguration
	 *            {@link PropsBasedDosConfiguration} that sets site-specific properties
	 */
	public AbstractDosService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosConfigInterface dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount);
		if (dosConfiguration == null) {
			throw new IllegalArgumentException("null dosConfiguration");
		}
		this.dosConfiguration = dosConfiguration;
	}

	public DosConfigInterface getDosConfiguration() {
		return dosConfiguration;
	}

}