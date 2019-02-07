/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundle.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;

/**
 * Implementation of a service factory that can produce a @{link DosService}
 * implementation class. This allows injection via Spring and late resolution of
 * the {@link IRODSAccount}
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class ExplodedDosServiceFactoryImpl implements DosServiceFactory {

	/**
	 * {@link IRODSAccessObjectFactory} that can produce service objects
	 */
	private IRODSAccessObjectFactory irodsAccessObjectFactory;

	public ExplodedDosServiceFactoryImpl(IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}

	public ExplodedDosServiceFactoryImpl() {
	}

	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		return irodsAccessObjectFactory;
	}

	public void setIrodsAccessObjectFactory(IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}

	/* (non-Javadoc)
	 * @see org.irods.jargon.ga4gh.dos.bundle.impl.DosServiceFactory#instance(org.irods.jargon.core.connection.IRODSAccount)
	 */
	@Override
	public DosService instance(IRODSAccount irodsAccount) throws JargonException {
		return new ExplodedDosServiceImpl(this.irodsAccessObjectFactory, irodsAccount);
	}

}
