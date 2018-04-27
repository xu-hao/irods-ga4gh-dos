/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.query.AVUQueryElement;
import org.irods.jargon.core.query.JargonQueryException;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.core.query.QueryConditionOperators;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.ga4gh.dos.services.SystemDescriptiveMetadataService;
import org.irods.jargon.ga4gh.dos.services.metadata.SystemDescriptiveMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to get fixed descriptive metadata for the GA4GH Dos service, adds
 * fixed values to the system metadata fields of collections based on a set of
 * AVUs
 * <p/>
 * These system AVUS are placed on a visible collection with the provided AVU
 * unit and placed as KVPs in the GA4GH system metadata
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class SystemDescriptiveMetadataServiceImpl extends AbstractJargonService implements SystemDescriptiveMetadataService {

	public static final Logger log = LoggerFactory.getLogger(SystemDescriptiveMetadataServiceImpl.class);

	/**
	 * @param irodsAccessObjectFactory
	 * @param irodsAccount
	 */
	public SystemDescriptiveMetadataServiceImpl(IRODSAccessObjectFactory irodsAccessObjectFactory,
			IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
	}

	/* (non-Javadoc)
	 * @see org.irods.jargon.ga4gh.dos.services.impl.SystemDescriptiveMetadataService#initializeSystemDescriptiveMetadata()
	 */
	@Override
	public SystemDescriptiveMetadata initializeSystemDescriptiveMetadata() throws JargonException {
		log.info("initializeSystemDescriptiveMetadata()");

		SystemDescriptiveMetadata systemDescriptiveMetadata = new SystemDescriptiveMetadata();

		CollectionAO collectionAO = this.getIrodsAccessObjectFactory().getCollectionAO(getIrodsAccount());

		List<AVUQueryElement> queryElements = new ArrayList<AVUQueryElement>();

		try {
			queryElements.add(AVUQueryElement.instanceForValueQuery(AVUQueryElement.AVUQueryPart.UNITS,
					QueryConditionOperators.LIKE, SYSTEM_AVU_UNIT + "%"));
			List<MetaDataAndDomainData> result = collectionAO.findMetadataValuesByMetadataQuery(queryElements);

			for (MetaDataAndDomainData resultRow : result) {
				systemDescriptiveMetadata.addTerm(resultRow.getAvuAttribute(), resultRow.getAvuValue());
			}

			return systemDescriptiveMetadata;

		} catch (JargonQueryException e) {
			log.error("error creating metadata query", e);
			throw new JargonException("error querying for system metadata", e);
		}

	}

}
