/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.rest.configuration;

import org.irods.jargon.core.connection.AuthScheme;

/**
 * Bean impl of {@link DosConfigInterface}
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class DosConfig implements DosConfigInterface {

	private String irodsHost = "";
	private String irodsZone = "";
	private String defaultStorageResource = "";
	private int irodsPort = 1247;
	private String realm = "irods-ga4gh";
	private String urlPrefix = "";
	private boolean usePackingStreams = true;
	private boolean computeChecksum = true;
	private AuthScheme authScheme = AuthScheme.STANDARD;
	private String sslNegotiationPolicy = "CS_NEG_DONT_CARE";

	@Override
	public String getIrodsHost() {
		return irodsHost;
	}

	public void setIrodsHost(String irodsHost) {
		this.irodsHost = irodsHost;
	}

	@Override
	public String getIrodsZone() {
		return irodsZone;
	}

	public void setIrodsZone(String irodsZone) {
		this.irodsZone = irodsZone;
	}

	@Override
	public String getDefaultStorageResource() {
		return defaultStorageResource;
	}

	public void setDefaultStorageResource(String defaultStorageResource) {
		this.defaultStorageResource = defaultStorageResource;
	}

	@Override
	public int getIrodsPort() {
		return irodsPort;
	}

	public void setIrodsPort(int irodsPort) {
		this.irodsPort = irodsPort;
	}

	@Override
	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	@Override
	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	@Override
	public boolean isUsePackingStreams() {
		return usePackingStreams;
	}

	public void setUsePackingStreams(boolean usePackingStreams) {
		this.usePackingStreams = usePackingStreams;
	}

	@Override
	public boolean isComputeChecksum() {
		return computeChecksum;
	}

	public void setComputeChecksum(boolean computeChecksum) {
		this.computeChecksum = computeChecksum;
	}

	@Override
	public AuthScheme getAuthScheme() {
		return authScheme;
	}

	public void setAuthScheme(AuthScheme authScheme) {
		this.authScheme = authScheme;
	}

	@Override
	public String getSslNegotiationPolicy() {
		return sslNegotiationPolicy;
	}

	public void setSslNegotiationPolicy(String sslNegotiationPolicy) {
		this.sslNegotiationPolicy = sslNegotiationPolicy;
	}

}
