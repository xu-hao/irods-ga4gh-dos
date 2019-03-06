package org.irods.jargon.ga4gh.dos.bundle.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.ga4gh.dos.bundle.AbstractDosService;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataBundle;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A backing service behind the DOS Swagger API that translates the underlying
 * iRODS 'exploded' arrangement where bundles are mapped to iRODS collections
 * and data objects in-place, marked with metadata to denote bundles, member
 * objects, etc.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class ExplodedDosServiceImpl extends AbstractDosService implements DosService {

	public static final Logger log = LoggerFactory.getLogger(ExplodedDosServiceImpl.class);

	private DosConfiguration dosConfiguration;

	public ExplodedDosServiceImpl(IRODSAccessObjectFactory irodsAccessObjectFactory, IRODSAccount irodsAccount,
			DosServiceFactory dosServiceFactory, DosConfiguration dosConfiguration) {
		super(irodsAccessObjectFactory, irodsAccount, dosServiceFactory, dosConfiguration);
	}

	public IrodsDataBundle retrieveDataBundle(final String bundleId) throws DosDataNotFoundException {
		log.info("retrieveDataBundle()");
		if (bundleId == null || bundleId.isEmpty()) {
			throw new IllegalArgumentException("null or empty bundleId");
		}
		log.info("bundleId:{}", bundleId);

		return null; // FIXME: implement

	}

}
