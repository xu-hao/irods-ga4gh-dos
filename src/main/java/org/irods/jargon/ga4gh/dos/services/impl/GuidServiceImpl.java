/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services.impl;

import java.util.UUID;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.service.AbstractJargonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provisional service to maintain a GUID on iRODS files and collections, sure
 * to be factored out laterß
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class GuidServiceImpl extends AbstractJargonService implements GuidService {

	public static final Logger log = LoggerFactory.getLogger(GuidServiceImpl.class);

	/**
	 * @param irodsAccessObjectFactory
	 * @param irodsAccount
	 */
	public GuidServiceImpl(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
	}

	/* (non-Javadoc)
	 * @see org.irods.jargon.ga4gh.dos.services.impl.GuidService#createGuidOnDataObject(java.lang.String)
	 */
	@Override
	public String createGuidOnDataObject(final String irodsPath) throws DataNotFoundException, JargonException {
		log.info("determineMimeType()");

		if (irodsPath == null || irodsPath.isEmpty()) {
			throw new IllegalArgumentException("null or empty irodsPath");
		}

		log.info("irodsPath:{}", irodsPath);

		UUID uuid = UUID.randomUUID();
		log.info("created uuid:{}", uuid);
		AvuData avu = new AvuData(GUID_ATTRIBUTE, uuid.toString(), GUID_UNIT);

		DataObjectAO dataObjectAO = this.irodsAccessObjectFactory.getDataObjectAO(irodsAccount);
		dataObjectAO.addAVUMetadata(irodsPath, avu);
		log.info("guid created");
		return uuid.toString();
	}
}
