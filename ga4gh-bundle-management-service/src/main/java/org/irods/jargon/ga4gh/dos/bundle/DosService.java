package org.irods.jargon.ga4gh.dos.bundle;

import java.util.List;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsAccessMethod;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataBundle;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataObject;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.exception.BundleNotFoundException;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.exception.DosSystemException;

/**
 * Interface for a backing service that serves data bundles and data objects out
 * of iRODS. This can be in a packaged format such as BDBag, or as an 'exploded'
 * format mapped to iRODS collections.
 * 
 * @author Mike Conway - NIEHS
 *
 */
public interface DosService {

	public static final String ACCESS_IRODS = "irods";
	public static final String ACCESS_REST = "irods-rest";

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

	/**
	 * Retrieve the irods absolute path for the data object id
	 * 
	 * @param dataObjectId {@code String} with the ga4gh data object id
	 * @return {@code String} with the iRODS absolute path
	 * @throws DosDataNotFoundException {@link DosDataNotFoundException}
	 * @throws JargonException          {@link JargonException}
	 */
	String dataObjectIdToIrodsPath(final String dataObjectId) throws DosDataNotFoundException, JargonException;

	/**
	 * Retrieve a data object in a bundle based on its GUID
	 * 
	 * @param objectId {@code String} with the GUID of the data objecct
	 * @return {@link IrodsDataObject}
	 * @throws DosDataNotFoundException {@link DosDataNotFoundException}
	 * @throws JargonException          {@link JargonException}
	 */
	IrodsDataObject retrieveDataObject(final String objectId) throws DosDataNotFoundException, DosSystemException;

	/**
	 * Retrieve a list of data objects in a bundle
	 * 
	 * @param bundleId  {@code String} with the ga4gh data bundle id
	 * @param urlPrefix {@code String} with the url prefix for obtaining access
	 *                  methods for an individual data object
	 * @return {@code List} of {@link IrodsDataObject} that can be used to build the
	 *         bundle list in a GA4GH data bundle
	 * @throws BundleNotFoundException {@link BundleNotFoundException}
	 * @throws JargonException         {@link JargonException}
	 */
	List<IrodsDataObject> retrieveDataObjectsInBundle(String bundleId, String urlPrefix)
			throws BundleNotFoundException, JargonException;

	/**
	 * Create an access url for a data object for a specific access type
	 * 
	 * @param dataObjectId {@code String} with the ga4gh data object id
	 * @param accessId     {@code String} with the access id for the given data
	 *                     object
	 * @return {@link IrodsAccessMethod}
	 * @throws DosDataNotFoundException {@link DosDataNotFoundException} when data
	 *                                  object or access method is not found
	 * @throws JargonException          {@link JargonException}
	 */
	IrodsAccessMethod createAccessUrlForDataObject(final String dataObjectId, final String accessId)
			throws DosDataNotFoundException, JargonException;

}