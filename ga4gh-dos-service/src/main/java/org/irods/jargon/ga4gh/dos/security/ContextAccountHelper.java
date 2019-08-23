/**
 * 
 */
package org.irods.jargon.ga4gh.dos.security;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.ga4gh.dos.JargonDosConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * @author Mike Conway - NIEHS
 *
 */
@Component
public class ContextAccountHelper {
	@Autowired
	private JargonDosConfiguration jargonDosConfiguration;

	private static final Logger log = LoggerFactory.getLogger(ContextAccountHelper.class);

	/**
	 * Build an account from an authentication that can be found in the
	 * {@link SecurityContext}
	 * 
	 * @param authentication {@link UsernamePasswordAuthenticationToken}
	 * @return {@link IRODSAccount}
	 */
	public IRODSAccount irodsAccountFromAuthentication(final UsernamePasswordAuthenticationToken authentication) {
		log.info("irodsAccountFromAuthentication()");
		if (authentication == null) {
			throw new IllegalArgumentException("Null authentication");
		}

		IRODSAccount irodsAccount = IRODSAccount.instanceWithProxy(jargonDosConfiguration.getIrodsHost(),
				jargonDosConfiguration.getIrodsPort(), jargonDosConfiguration.getProxyUser(),
				jargonDosConfiguration.getProxyPassword(), "", jargonDosConfiguration.getIrodsZone(), "",
				authentication.getName(), "");

		log.debug("formulated iRODS account from auth:{}", irodsAccount);
		return irodsAccount;

	}

	public JargonDosConfiguration getJargonDosConfiguration() {
		return jargonDosConfiguration;
	}

	public void setJargonDosConfiguration(JargonDosConfiguration jargonDosConfiguration) {
		this.jargonDosConfiguration = jargonDosConfiguration;
	}
}
