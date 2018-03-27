/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.query.GenQueryBuilderException;
import org.irods.jargon.core.query.IRODSGenQueryBuilder;
import org.irods.jargon.core.query.QueryConditionOperators;
import org.irods.jargon.core.query.RodsGenQueryEnum;
import org.irods.jargon.core.service.AbstractJargonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Services to manage iRODS bundles for a collection based bundle implementation
 * 
 * @author Mike Conway - NIEHS
 *
 */
class BundleManagementService extends AbstractJargonService {

	public static final Logger log = LoggerFactory.getLogger(BundleManagementService.class);

	/**
	 * @param irodsAccessObjectFactory
	 * @param irodsAccount
	 */
	public BundleManagementService(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
	}

	/**
	 * 
	 */
	public BundleManagementService() {
	}

	public List<BundleObjectRollup> retrieveDataObjectsInBundle(final String irodsCollectionPath)
			throws DataNotFoundException, JargonException {

		log.info("retrieveDataObjectsInBundle()");
		if (irodsCollectionPath == null || irodsCollectionPath.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsCollectionPath");
		}

		log.info("irodsCollectionPath:{}", irodsCollectionPath);

		List<BundleObjectRollup> objects = new ArrayList<BundleObjectRollup>();
		Map<String, String> pathToGuidMap = new HashMap<String, String>();

		/*
		 * Get a list of data objects/checksums under this parent, for now treat as flat
		 * (no recursion). Then do a query of GUIDs under the same path and put into a
		 * map. This avoids specific queries or direct queries and reduces the number of
		 * metadata queries. TODO: consider nested collections
		 */
		IRODSGenQueryBuilder builder = new IRODSGenQueryBuilder(true, null);

		try {
			builder.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_COLL_NAME)
					.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_DATA_NAME)
					.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_D_DATA_CHECKSUM).addConditionAsGenQueryField(
							RodsGenQueryEnum.COL_COLL_NAME, QueryConditionOperators.EQUAL, irodsCollectionPath);

		} catch (GenQueryBuilderException e) {
			log.error("error building query for data objects", e);
			throw new JargonException(e);
		}

		return null;

	}

}
