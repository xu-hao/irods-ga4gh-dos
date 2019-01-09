/**
 * 
 */
package org.irods.jargon.ga4gh.dos.utils;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.datautils.visitor.AbstractIrodsVisitorComponent;
import org.irods.jargon.datautils.visitor.HierComposite;
import org.irods.jargon.datautils.visitor.HierLeaf;
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

	public String computeHexEncodedChecksumOverCollectionChecksums(final String dataBundleParentAbsolutePath)
			throws DataNotFoundException, JargonException {

		log.info("computeHexEncodedChecksumOverCollectionChecksums");
		if (dataBundleParentAbsolutePath == null || dataBundleParentAbsolutePath.isEmpty()) {
			throw new IllegalArgumentException("null or empty dataBundleParentAbsolutePath");
		}

		AbstractIrodsVisitorComponent checksumVisitor = new AbstractIrodsVisitorComponent() {

			@Override
			public boolean visitEnter(HierComposite node) {
				return true;
			}

			@Override
			public boolean visitLeave(HierComposite node, boolean visitorEntered) {
				return true;
			}

			@Override
			public boolean visit(HierLeaf node) {
				// TODO: checksum compute stuff
				return false;
			}

		};

	}

}
