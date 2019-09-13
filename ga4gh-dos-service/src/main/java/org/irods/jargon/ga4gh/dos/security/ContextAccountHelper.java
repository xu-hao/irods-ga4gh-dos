/**
 * 
 */
package org.irods.jargon.ga4gh.dos.security;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mike Conway - NIEHS
 *
 */
@Component
public class ContextAccountHelper {
	@Autowired
	private DosConfiguration dosConfiguration;

	private static final Logger log = LoggerFactory.getLogger(ContextAccountHelper.class);

	/**
	 * Build an account from an authentication that can be found in the
	 * {@link SecurityContext}
	 * 
	 * @param userName {@code String} with user name
	 * @return {@link IRODSAccount}
	 */
	public IRODSAccount irodsAccountFromAuthentication(final String userName) {
		log.info("irodsAccountFromAuthentication()");
		if (userName == null) {
			throw new IllegalArgumentException("Null authentication");
		}

		IRODSAccount irodsAccount = IRODSAccount.instanceWithProxy(dosConfiguration.getIrodsHost(),
				dosConfiguration.getPort(), userName, dosConfiguration.getProxyPassword(), "",
				dosConfiguration.getIrodsZone(), "", dosConfiguration.getProxyUser(), "");

		log.debug("formulated iRODS account from auth:{}", irodsAccount);
		return irodsAccount;

	}

	public DosConfiguration getDosConfiguration() {
		return dosConfiguration;
	}

	public void setDosConfiguration(DosConfiguration dosConfiguration) {
		this.dosConfiguration = dosConfiguration;
	}

}
