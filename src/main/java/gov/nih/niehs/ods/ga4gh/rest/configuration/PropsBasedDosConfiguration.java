/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.rest.configuration;

import org.irods.jargon.core.connection.AuthScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import gov.nih.niehs.ods.ga4gh.dos.exception.DosSystemException;

/**
 * specific configuration for ga4gh services
 *
 * @author mconway
 *
 */
@Component // FIXME: make source a prop that can be in test/resources
@PropertySource("file:///Users/conwaymc/Documents/docker/ga4gh/etc/irods-ext/ga4gh.properties")
public class PropsBasedDosConfiguration implements DosConfigInterface {

	@Autowired
	Environment env;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface#getIrodsHost()
	 */
	@Override
	public String getIrodsHost() {
		return env.getProperty("irods.host");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface#getIrodsZone()
	 */
	@Override
	public String getIrodsZone() {
		return env.getProperty("irods.zone");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface#
	 * getDefaultStorageResource()
	 */
	@Override
	public String getDefaultStorageResource() {
		return env.getProperty("default.storage.resource");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface#getIrodsPort()
	 */
	@Override
	public int getIrodsPort() {
		return Integer.parseInt(env.getProperty("irods.port"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface#getRealm()
	 */
	@Override
	public String getRealm() {
		return env.getProperty("irods-ga4gh");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface#getUrlPrefix()
	 */
	@Override
	public String getUrlPrefix() {
		return env.getProperty("web.interface.url");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface#
	 * isUsePackingStreams()
	 */
	@Override
	public boolean isUsePackingStreams() {
		return Boolean.parseBoolean(env.getProperty("utilize.packing.streams"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface#
	 * isComputeChecksum()
	 */
	@Override
	public boolean isComputeChecksum() {
		return Boolean.parseBoolean(env.getProperty("compute.checksum"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface#getAuthScheme()
	 */
	@Override
	public AuthScheme getAuthScheme() {
		String authSchemeStr = env.getProperty("auth.type");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface#
	 * getSslNegotiationPolicy()
	 */
	@Override
	public String getSslNegotiationPolicy() {
		return env.getProperty("ssl.negotiation.policy");
	}

}