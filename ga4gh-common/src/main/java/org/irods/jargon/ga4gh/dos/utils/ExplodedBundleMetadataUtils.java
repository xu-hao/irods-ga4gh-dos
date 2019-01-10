/**
 * 
 */
package org.irods.jargon.ga4gh.dos.utils;

import org.irods.jargon.core.pub.domain.AvuData;

/**
 * Utilities to produce AVU structures used in exploded bundle deployments
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class ExplodedBundleMetadataUtils {

	public static final String GA4GH_BUNDLE_UNIT_PREFIX = "irods:ga4gh:dos";
	public static final String GA4GH_BUNDLE_ID_ATTRIBUTE = "ga4gh:bundleId";
	public static final String GA4GH_DATA_OBJECT_ID_ATTRIBUTE = "ga4gh:dataObjectId";

	/**
	 * Create an irods AVU that marks a bundle with the resolvable bundle id
	 * 
	 * @param bundleId
	 *            {@code String} with the bundle id
	 * @return {@link AvuData} with an iRODS AVU that marks a bundle
	 */
	public static AvuData createBundleMarkerAvu(final String bundleId) {
		if (bundleId == null || bundleId.isEmpty()) {
			throw new IllegalArgumentException("null or empty bundleId");
		}

		return AvuData.instance(GA4GH_BUNDLE_ID_ATTRIBUTE, bundleId, GA4GH_BUNDLE_UNIT_PREFIX);

	}

	/**
	 * Create an irods AVU that marks a data object with a resolvable id
	 * 
	 * @param dataObjectId
	 *            {@code String} with the data object id
	 * @return {@link AvuData} with an iRODS AVU that marks a data object
	 */
	public static AvuData createBundleDataObjectMarkerAvu(final String dataObjectId) {
		if (dataObjectId == null || dataObjectId.isEmpty()) {
			throw new IllegalArgumentException("null or empty dataObjectId");
		}

		return AvuData.instance(GA4GH_DATA_OBJECT_ID_ATTRIBUTE, dataObjectId, GA4GH_BUNDLE_UNIT_PREFIX);

	}
}
