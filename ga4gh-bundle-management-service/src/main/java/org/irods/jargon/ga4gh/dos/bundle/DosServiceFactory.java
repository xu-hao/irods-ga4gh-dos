package org.irods.jargon.ga4gh.dos.bundle;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;

/**
 * Describes a factory to produce appropriate {@link DosService} implementations
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
	 * @throws JargonException
	 *             {@link JargonException}
	 */
	DosService instance(IRODSAccount irodsAccount) throws JargonException;

}