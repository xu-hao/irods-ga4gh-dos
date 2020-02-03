/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundlemgmnt;

import java.security.MessageDigest;
import java.util.List;

import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.BundleInfoAndPath;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.exception.BundleNotFoundException;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.exception.DuplicateBundleException;

/**
 * Interface to a service to create a GA4GH Data Bundle on an existing iRODS
 * collection. A bundle is marked with various metadata values, checksums are
 * created for the components of a bundle, etc.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public interface DosBundleManagementService {

	/**
	 * Enum of bundle types, currently an 'exploded' in-place bundle type is
	 * supported, BDBAG or other formats are planned for the future.
	 * 
	 * @author Mike Conway - NIEHS
	 *
	 */
	public enum BundleType {
		EXPLODED
	}

	/**
	 * Create a data bundle based on the provided source root path. Based on the
	 * bundle type the bundle will be created based on the existing policy.
	 * Depending on the underlying implementation either the bundle is overlaid in
	 * place, or the bundle may be created as a separate archive, such as a BDBag.
	 * 
	 * @param bundleRootAbsolutePath {@code String} with the path to the parent root
	 *                               of the bundle
	 * 
	 * @return {@code String} with the identifier of the bundle (e.g. the GUID)
	 * @throws DataNotFoundException    {@link
	 * @throws DuplicateBundleException {@link DuplicateBundleException}
	 * @throws JargonException          {@link JargonException}
	 */
	public String createDataBundle(final String bundleRootAbsolutePath)
			throws DataNotFoundException, DuplicateBundleException, JargonException;

	/**
	 * Delete a data bundle. Based on the bundle type this may delete an archive, or
	 * simply strip metadata from iRODS files and collections.
	 * <p>
	 * It is important to remember that the underlyng implementation may or may not
	 * remove the data objects from iRODS. The deletion of a data bundle may or may
	 * not map directly to a deletion of data in iRODS.
	 * <p>
	 * This is an idempotent method so deleting a non-existent bundle silently
	 * ignores the request.
	 * 
	 * @param dataBundleId {@code String} with a bundle identifier
	 * @throws JargonException {@link JargonException}
	 */
	public void deleteDataBundle(final String dataBundleId) throws JargonException;

	/**
	 * Utility method to determine the configured checksum algorithm on the iRODS
	 * server
	 * 
	 * @return {@link MessageDigest} with the iRODS checksum type
	 */
	public MessageDigest determineMessageDigestFromIrods();

	/**
	 * Resolve the iRODS path to the object or collection that represents the bundle
	 * 
	 * @param dataBundleId {@code String} with the data bundle UUID
	 * @return {@code String} with the iRODS absolute path to the data bundle
	 * @throws BundleNotFoundException {@link BundleNotFoundException}
	 * @throws JargonException         {@link JargonException}
	 */
	public String bundleIdToIrodsPath(final String dataBundleId) throws BundleNotFoundException, JargonException;

	/**
	 * List all the bundles on the given iRODS grid
	 * 
	 * @return {@code List} of {@link BundleInfoAndPath}
	 * @throws JargonException {@link JargonException}
	 */
	List<BundleInfoAndPath> listAllBundles() throws JargonException;

}
