/**
 * 
 */
package org.irods.jargon.ga4gh.dos.configuration;

import org.irods.jargon.core.connection.AuthScheme;
import org.irods.jargon.ga4gh.dos.exception.DosSystemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Bean impl of {@link DosConfigInterface}
 * 
 * @author Mike Conway - NIEHS
 *
 */
@PropertySource("file:///etc/irods-ext/ga4gh.properties")
// @PropertySource("file:///Users/conwaymc/Documents/docker/ga4gh/etc/irods-ext/ga4gh.properties")

@Component
public class DosConfiguration {

	@Value("${irods.host}")
	private String irodsHost;

	@Value("${irods.zone}")
	private String irodsZone;

	@Value("${default.storage.resource}")
	private String defaultStorageResource;

	@Value("${irods.port}")
	private int port;

	@Value("${irods.realm}")
	private String realm;

	@Value("${web.interface.url}")
	private String urlPrefix;

	@Value("${utilize.packing.streams}")
	private boolean usePackingStreams;

	@Value("${compute.checksum}")
	private boolean computeChecksum;

	@Value("${auth.type}")
	private String authScheme;

	@Value("${ssl.negotiation.policy}")
	private String sslNegotiationPolicy;

	@Value("${irodsext.datatyper.persist.data.types}")
	private boolean persistDataTypes;

	@Value("${irodsext.datatyper.detailed.determination}")
	private boolean detailedDataTypeDetermination;

	public AuthScheme translateAuthSchemeToEnum() {
		String authSchemeStr = authScheme;
		if (authSchemeStr == null || authSchemeStr.isEmpty()) {
			return AuthScheme.STANDARD;
		} else if (authSchemeStr.equals(AuthScheme.STANDARD.toString())) {
			return AuthScheme.STANDARD;
		} else if (authSchemeStr.equals(AuthScheme.PAM.toString())) {
			return AuthScheme.PAM;
		} else {
			throw new DosSystemException("unknown authscheme");
		}
	}

	public String getIrodsHost() {
		return irodsHost;
	}

	public void setIrodsHost(String irodsHost) {
		this.irodsHost = irodsHost;
	}

	public String getIrodsZone() {
		return irodsZone;
	}

	public void setIrodsZone(String irodsZone) {
		this.irodsZone = irodsZone;
	}

	public String getDefaultStorageResource() {
		return defaultStorageResource;
	}

	public void setDefaultStorageResource(String defaultStorageResource) {
		this.defaultStorageResource = defaultStorageResource;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	public boolean isUsePackingStreams() {
		return usePackingStreams;
	}

	public void setUsePackingStreams(boolean usePackingStreams) {
		this.usePackingStreams = usePackingStreams;
	}

	public boolean isComputeChecksum() {
		return computeChecksum;
	}

	public void setComputeChecksum(boolean computeChecksum) {
		this.computeChecksum = computeChecksum;
	}

	public String getAuthScheme() {
		return authScheme;
	}

	public void setAuthScheme(String authScheme) {
		this.authScheme = authScheme;
	}

	public boolean isPersistDataTypes() {
		return persistDataTypes;
	}

	public void setPersistDataTypes(boolean persistDataTypes) {
		this.persistDataTypes = persistDataTypes;
	}

	public boolean isDetailedDataTypeDetermination() {
		return detailedDataTypeDetermination;
	}

	public void setDetailedDataTypeDetermination(boolean detailedDataTypeDetermination) {
		this.detailedDataTypeDetermination = detailedDataTypeDetermination;
	}

	public void setSslNegotiationPolicy(String sslNegotiationPolicy) {
		this.sslNegotiationPolicy = sslNegotiationPolicy;
	}

	public String getSslNegotiationPolicy() {
		return sslNegotiationPolicy;
	}
}
