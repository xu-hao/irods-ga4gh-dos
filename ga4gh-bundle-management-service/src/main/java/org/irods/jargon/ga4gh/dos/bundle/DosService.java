package org.irods.jargon.ga4gh.dos.bundle;

import java.util.List;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataBundle;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataObject;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.exception.BundleNotFoundException;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;

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
	 * Retrieve a list of data objects for a given bundle id (GUID)
	 * 
	 * @param bundleId {@code String} with the bundle id (GUID)
	 * @return {@code List} of {@link IrodsDataObject} with the data object ids
	 * @throws BundleNotFoundException {@link BundleNotFoundException}
	 * @throws JargonException         {@link JargonException}
	 */
	List<IrodsDataObject> retrieveDataObjectsInBundle(final String bundleId)
			throws BundleNotFoundException, JargonException;

	/**
	 * Retrieve a data bundle (in an intermediate data transfer object) based on a
	 * GA4GH bundle id
	 * 
	 * @param bundleId {@code String} with the bundle id
	 * @return {@link IrodsDataBundle} that is an intermediate representation of the
	 *         data bundle without the JSON cruft
	 * @throws DosDataNotFoundException {@link DosDataNotFoundException} for missing
	 *                                  bundle
	 */
	IrodsDataBundle retrieveDataBundle(final String bundleId) throws DosDataNotFoundException;

}