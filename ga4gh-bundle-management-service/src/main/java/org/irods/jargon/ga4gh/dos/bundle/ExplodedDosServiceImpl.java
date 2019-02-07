package org.irods.jargon.ga4gh.dos.bundle;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.service.AbstractJargonService;

/**
 * A backing service behind the DOS Swagger API that translates the underlying
 * iRODS 'exploded' arrangement where bundles are mapped to iRODS collections
 * and data objects in-place, marked with metadata to denote bundles, member
 * objects, etc.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class ExplodedDosServiceImpl extends AbstractJargonService {

	public ExplodedDosServiceImpl(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount) {
		super(irodsAccessObjectFactory, irodsAccount);
	}

	public ExplodedDosServiceImpl() {

	}

}
