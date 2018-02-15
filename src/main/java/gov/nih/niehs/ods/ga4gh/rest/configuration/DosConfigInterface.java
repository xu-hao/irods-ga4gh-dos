package gov.nih.niehs.ods.ga4gh.rest.configuration;

import org.irods.jargon.core.connection.AuthScheme;

public interface DosConfigInterface {

	String getIrodsHost();

	String getIrodsZone();

	String getDefaultStorageResource();

	int getIrodsPort();

	String getRealm();

	String getUrlPrefix();

	boolean isUsePackingStreams();

	boolean isComputeChecksum();

	AuthScheme getAuthScheme();

	String getSslNegotiationPolicy();

}