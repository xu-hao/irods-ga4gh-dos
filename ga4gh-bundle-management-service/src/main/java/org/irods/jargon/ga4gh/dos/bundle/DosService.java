package org.irods.jargon.ga4gh.dos.bundle;

import java.util.List;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.exception.BundleNotFoundException;

/**
 * Interface for a backing service that serves data bundles and data objects out
 * of iRODS. This can be in a packaged format such as BDBag, or as an 'exploded'
 * format mapped to iRODS collections.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public interface DosService {

	/**
	 * Retrieve a list of data object ids (GUIDs) for a given bundle id (GUID)
	 * 
	 * @param bundleId {@code String} with the bundle id (GUID)
	 * @return {@code List} of {@code String} with the data object ids
	 * @throws BundleNotFoundException {@link BundleNotFoundException}
	 * @throws JargonException         {@link JargonException}
	 */
	List<String> retrieveDataObjectsInBundle(final String bundleId) throws BundleNotFoundException, JargonException;

}