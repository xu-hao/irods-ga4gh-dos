/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundlemgmnt.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.service.AbstractJargonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities for data bundle creation
 * 
 * @author conwaymc
 *
 */
public class DataBundleUtilsService extends AbstractJargonService {

	public static final Logger log = LoggerFactory.getLogger(DataBundleUtilsService.class);

	/**
	 * @param irodsAccessObjectFactory
	 *            {@link IRODSAccessObjectFactory}
	 * @param irodsAccount
	 *            {@link IRODSAccount}
	 */
	public DataBundleUtilsService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
	}

	/**
	 * 
	 */
	public DataBundleUtilsService() {
	}

}
