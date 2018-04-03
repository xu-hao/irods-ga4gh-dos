/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.IRODSGenQueryExecutor;
import org.irods.jargon.core.query.GenQueryBuilderException;
import org.irods.jargon.core.query.IRODSGenQueryBuilder;
import org.irods.jargon.core.query.IRODSGenQueryFromBuilder;
import org.irods.jargon.core.query.IRODSQueryResultRow;
import org.irods.jargon.core.query.IRODSQueryResultSet;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.query.PagingAwareCollectionListing;
import org.irods.jargon.core.query.QueryConditionOperators;
import org.irods.jargon.core.query.RodsGenQueryEnum;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.mdquery.MetadataQuery;
import org.irods.jargon.mdquery.MetadataQuery.QueryType;
import org.irods.jargon.mdquery.MetadataQueryElement;
import org.irods.jargon.mdquery.service.MetadataQueryService;
import org.irods.jargon.mdquery.service.MetadataQueryServiceImpl;
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
		Map<String, String> pathToGuidMap = new HashMap<String, String>();

		/*
		 * Get a list of data objects/checksums under this parent, for now treat as flat
		 * (no recursion). Then do a query of GUIDs under the same path and put into a
		 * map. This avoids specific queries or direct queries and reduces the number of
		 * metadata queries. TODO: consider nested collections
		 */
		IRODSGenQueryBuilder builder = new IRODSGenQueryBuilder(true, null);
		IRODSQueryResultSet resultSet = null;
		IRODSGenQueryExecutor irodsGenQueryExecutor = getIrodsAccessObjectFactory()
				.getIRODSGenQueryExecutor(irodsAccount);

		try {
			builder.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_COLL_NAME)
					.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_DATA_NAME)
					.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_D_DATA_CHECKSUM)
					.addSelectAsGenQueryValue(RodsGenQueryEnum.COL_D_COLL_ID).addConditionAsGenQueryField(
							RodsGenQueryEnum.COL_COLL_NAME, QueryConditionOperators.EQUAL, irodsCollectionPath);

			IRODSGenQueryFromBuilder irodsQuery = builder.exportIRODSQueryFromBuilder(
					this.getIrodsAccessObjectFactory().getJargonProperties().getMaxFilesAndDirsQueryMax());
			resultSet = irodsGenQueryExecutor.executeIRODSQueryAndCloseResult(irodsQuery, 0);

			if (resultSet.getResults().isEmpty()) {
				log.warn("no data objects found in collection, return an empty set");
				return objects;
			}

			/*
			 * This is just a provisional technique of defining a bundle as a collection of
			 * data objects under a parent collection. This is only for initial prototyping,
			 * and obviously something more production ready would be needed (e.g. BDBag)
			 * 
			 * Here I've found the direct children and checksums for each data object, store
			 * in a map to merge with the AVU based GUIDs just to be a bit more efficient.
			 *
			 * 
			 * coll name - 0
			 * 
			 * data name - 1
			 * 
			 * checksum - 2
			 */
			StringBuilder sb;
			Map<String, IRODSQueryResultRow> dataMap = new HashMap<String, IRODSQueryResultRow>();

			for (IRODSQueryResultRow row : resultSet.getResults()) {
				sb = new StringBuilder(row.getColumn(0));
				sb.append("/");
				sb.append(row.getColumn(1));
				dataMap.put(sb.toString(), row);
			}

			log.info("found data objects, link with GUIDS");

			MetadataQuery metadataQuery = new MetadataQuery();
			metadataQuery.setQueryType(QueryType.DATA);
			metadataQuery.setPathHint(irodsCollectionPath);

			MetadataQueryElement element = new MetadataQueryElement();
			element.setAttributeName(GuidService.GUID_ATTRIBUTE);
			element.setOperator(QueryConditionOperators.LIKE);
			String vals[] = { "%" };
			element.setAttributeValue(Arrays.asList(vals));
			List<MetadataQueryElement> elements = new ArrayList<MetadataQueryElement>();
			elements.add(element);
			MetadataQueryService mdQueryService = new MetadataQueryServiceImpl(this.getIrodsAccessObjectFactory(),
					this.getIrodsAccount());
			PagingAwareCollectionListing listing = mdQueryService.executeQuery(metadataQuery);
			if (listing.getCollectionAndDataObjectListingEntries().isEmpty()) {
				log.warn("no guids found in collection, return an empty set");
				return objects;
			}

		} catch (GenQueryBuilderException | JargonQueryException e) {
			log.error("error in query", e);
			throw new JargonException(e);
		}

		return null;

	}

}
