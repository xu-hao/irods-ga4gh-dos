/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.core.checksum.ChecksumManager;
import org.irods.jargon.core.checksum.ChecksumManagerImpl;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.SpecificQueryAO;
import org.irods.jargon.core.query.IRODSQueryResultRow;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.query.SpecificQuery;
import org.irods.jargon.core.query.SpecificQueryResultSet;
import org.irods.jargon.core.service.AbstractJargonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Services to manage iRODS bundles for a collection based bundle implementation
 * 
 * @author Mike Conway - NIEHS
 *
 */
class IrodsBundleManagementService extends AbstractJargonService {

	public static final Logger log = LoggerFactory.getLogger(IrodsBundleManagementService.class);
	public static final String BUNDLE_QUERY_ALIAS = "ga4ghBundleQuery";

	/**
	 * @param irodsAccessObjectFactory
	 * @param irodsAccount
	 */
	public IrodsBundleManagementService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);

	}

	/**
	 * 
	 */
	public IrodsBundleManagementService() {
	}

	public List<BundleObjectRollup> retrieveDataObjectsInBundle(final String irodsCollectionPath)
			throws DataNotFoundException, JargonException {

		log.info("retrieveDataObjectsInBundle()");
		if (irodsCollectionPath == null || irodsCollectionPath.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsCollectionPath");
		}

		log.info("irodsCollectionPath:{}", irodsCollectionPath);

		List<BundleObjectRollup> objects = new ArrayList<BundleObjectRollup>();
		StringBuilder sb = new StringBuilder();
		sb.append(irodsCollectionPath.trim());
		sb.append('%');

		log.info("checking for existence of specific query...");
		SpecificQueryAO specificQueryAO = this.getIrodsAccessObjectFactory().getSpecificQueryAO(getIrodsAccount());
		SpecificQuery specificQuery = SpecificQuery.instanceWithOneArgument(BUNDLE_QUERY_ALIAS, sb.toString(), 0, "");
		BundleObjectRollup bundleObjectRollup;
		StringBuilder dataPath;
		ChecksumManager checksumManager = new ChecksumManagerImpl(this.getIrodsAccount(),
				this.getIrodsAccessObjectFactory());
		try {
			SpecificQueryResultSet result = specificQueryAO.executeSpecificQueryUsingAlias(specificQuery,
					this.getIrodsAccessObjectFactory().getJargonProperties().getMaxFilesAndDirsQueryMax(), 0);

			for (IRODSQueryResultRow row : result.getResults()) {
				log.debug("result row:{}", row);
				bundleObjectRollup = new BundleObjectRollup();
				dataPath = new StringBuilder();
				dataPath.append(row.getColumn(2));
				dataPath.append("/");
				dataPath.append(row.getColumn(3));
				bundleObjectRollup.setIrodsPath(dataPath.toString());
				// FIXME: handle no checksum avail
				bundleObjectRollup
						.setChecksumValue(checksumManager.determineChecksumEncodingFromIrodsData(row.getColumn(4)));
				bundleObjectRollup.setGuid(row.getColumn(7));
				log.info("added object to bundle:{}", bundleObjectRollup);
				objects.add(bundleObjectRollup);
			}

		} catch (JargonQueryException e) {
			log.error("error running query to find bundle objects");
			throw new JargonException(e);
		}

		return objects;

	}

}
