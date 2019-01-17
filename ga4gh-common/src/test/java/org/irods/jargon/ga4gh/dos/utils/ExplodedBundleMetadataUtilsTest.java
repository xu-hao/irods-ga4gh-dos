package org.irods.jargon.ga4gh.dos.utils;

import org.irods.jargon.core.pub.domain.AvuData;
import org.junit.Assert;
import org.junit.Test;

public class ExplodedBundleMetadataUtilsTest extends ExplodedBundleMetadataUtils {

	@Test
	public void testCreateBundleMarkerAvu() {
		String markerId = "markerid";
		AvuData actual = ExplodedBundleMetadataUtils.createBundleMarkerAvu(markerId);
		Assert.assertEquals(ExplodedBundleMetadataUtils.GA4GH_BUNDLE_ID_ATTRIBUTE, actual.getAttribute());
		Assert.assertEquals(markerId, actual.getValue());
		Assert.assertEquals(ExplodedBundleMetadataUtils.GA4GH_BUNDLE_UNIT_PREFIX, actual.getUnit());
	}

	@Test
	public void testCreateBundleDataObjectMarkerAvu() {
		String markerId = "markerid";
		AvuData actual = ExplodedBundleMetadataUtils.createBundleDataObjectMarkerAvu(markerId);
		Assert.assertEquals(ExplodedBundleMetadataUtils.GA4GH_DATA_OBJECT_ID_ATTRIBUTE, actual.getAttribute());
		Assert.assertEquals(markerId, actual.getValue());
		Assert.assertEquals(ExplodedBundleMetadataUtils.GA4GH_BUNDLE_UNIT_PREFIX, actual.getUnit());
	}

	@Test
	public void testCreateBundleMasterChecksumAvu() {
		String hexString = "hexString";
		AvuData actual = ExplodedBundleMetadataUtils.createBundleMasterChecksumAvu(hexString);
		Assert.assertEquals(ExplodedBundleMetadataUtils.GA4GH_BUNDLE_CHECKSUM_ATTRIBUTE, actual.getAttribute());
		Assert.assertEquals(hexString, actual.getValue());
		Assert.assertEquals(ExplodedBundleMetadataUtils.GA4GH_BUNDLE_UNIT_PREFIX, actual.getUnit());
	}

}
