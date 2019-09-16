/**
 * 
 */
package org.irods.jargon.ga4gh.dos.configuration;

import org.irods.jargon.core.connection.AuthScheme;
import org.irods.jargon.ga4gh.dos.exception.DosSystemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

/**
 * Bean impl of {@link DosConfigInterface}
 * 
 * @author Mike Conway - NIEHS
 *
 */

@PropertySources({ @PropertySource(value = "classpath:/test.dos.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:/etc/irods-ext/ga4gh.properties", ignoreResourceNotFound = true) })

@Component
public class DosConfiguration {

	@Value("${irods.host}")
	private String irodsHost;

	@Value("${irods.zone}")
	private String irodsZone;

	@Value("${proxy.user}")
	private String proxyUser;

	@Value("${proxy.password}")
	private String proxyPassword;

	@Value("${shared.jwt.key}")
	private String sharedJwtKey;

	@Value("${jwt.algo}")
	private String jwtAlgo;

	@Value("${irods.port}")
	private int port;

	@Value("${irods.realm}")
	private String realm;

	@Value("${drs.server.name}")
	private String drsServerName;

	@Value("${auth.type}")
	private String authScheme;

	@Value("${ssl.negotiation.policy}")
	private String sslNegotiationPolicy;

	@Value("${irodsext.datatyper.persist.data.types}")
	private boolean persistDataTypes;

	@Value("${irodsext.datatyper.detailed.determination}")
	private boolean detailedDataTypeDetermination;

	@Value("${shared.jwt.key}")
	private String jwtKey = "";

	/**
	 * {@code String} property 'drs.rest.url.endpoint'. If not blank, represents the
	 * complete url prefix to the REST endpoint that will provide a link to the
	 * data. This is the standard iRODS REST endpoint in the form XXX (TODO: build
	 * rest endpoint)
	 */
	@Value("${drs.rest.url.endpoint}")
	private String drsRestUrlEndpoint = "";

	/**
	 * {@code boolean} indicating whether 'irods://' form urls are provided as an
	 * access method for data objects
	 */
	@Value("${drs.provide.irods.urls}")
	private boolean drsProvideIrodsUrls = true;

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

	public String getDrsServerUrl() {
		return drsServerName;
	}

	public void setDrsServerUrl(String drsServerUrl) {
		this.drsServerName = drsServerUrl;
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

	public String getDrsRestUrlEndpoint() {
		return drsRestUrlEndpoint;
	}

	public void setDrsRestUrlEndpoint(String drsRestUrlEndpoint) {
		this.drsRestUrlEndpoint = drsRestUrlEndpoint;
	}

	public boolean isDrsProvideIrodsUrls() {
		return drsProvideIrodsUrls;
	}

	public void setDrsProvideIrodsUrls(boolean drsProvideIrodsUrls) {
		this.drsProvideIrodsUrls = drsProvideIrodsUrls;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DosConfiguration [irodsHost=").append(irodsHost).append(", irodsZone=").append(irodsZone)
				.append(", proxyUser=").append(proxyUser).append(", jwtAlgo=").append(jwtAlgo).append(", port=")
				.append(port).append(", realm=").append(realm).append(", drsServerName=").append(drsServerName)
				.append(", authScheme=").append(authScheme).append(", sslNegotiationPolicy=")
				.append(sslNegotiationPolicy).append(", persistDataTypes=").append(persistDataTypes)
				.append(", detailedDataTypeDetermination=").append(detailedDataTypeDetermination).append(", jwtKey=")
				.append(jwtKey).append(", drsRestUrlEndpoint=").append(drsRestUrlEndpoint)
				.append(", drsProvideIrodsUrls=").append(drsProvideIrodsUrls).append("]");
		return builder.toString();
	}

	public String getProxyUser() {
		return proxyUser;
	}

	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
	}

	public String getProxyPassword() {
		return proxyPassword;
	}

	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}

	public String getSharedJwtKey() {
		return sharedJwtKey;
	}

	public void setSharedJwtKey(String sharedJwtKey) {
		this.sharedJwtKey = sharedJwtKey;
	}

	public String getJwtAlgo() {
		return jwtAlgo;
	}

	public void setJwtAlgo(String jwtAlgo) {
		this.jwtAlgo = jwtAlgo;
	}

	public String getJwtKey() {
		return jwtKey;
	}

	public void setJwtKey(String jwtKey) {
		this.jwtKey = jwtKey;
	}

}
