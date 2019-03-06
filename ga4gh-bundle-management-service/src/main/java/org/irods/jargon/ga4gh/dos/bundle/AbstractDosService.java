/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundle;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;

/**
 * Superclass for a DOS service implementation that extends the base Jargon
 * {@link AbstractJargonService} and adds factory and config dependencies
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class AbstractDosService extends AbstractJargonService {

	private final DosConfiguration dosConfiguration;

	private final DosServiceFactory dosServiceFactory;

	/**
	 * Constructor with necessary dependencies
	 * 
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory}
	 * @param irodsAccount
	 *            {@link IRODSAccount}
	 * @param dosServiceFactory
	 *            {@link DosServiceFactory}
	 * @param dosConfiguration
	 *            {@link DosConfiguration}
	 */
	public AbstractDosService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosServiceFactory dosServiceFactory, DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount);
		if (dosServiceFactory == null) {
			throw new IllegalArgumentException("null dosServiceFactory");
		}
		if (dosConfiguration == null) {
			throw new IllegalArgumentException("null dosConfiguration");
		}

		this.dosConfiguration = dosConfiguration;
		this.dosServiceFactory = dosServiceFactory;

	}

	public DosConfiguration getDosConfiguration() {
		return dosConfiguration;
	}

	public DosServiceFactory getDosServiceFactory() {
		return dosServiceFactory;
	}

}
