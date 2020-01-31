/**
 * 
 */
package org.irods.jargon.ga4gh.dos.console.context;

import org.irods.jargon.core.connection.AuthScheme;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.connection.IRODSServerProperties;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.exception.JargonRuntimeException;
import org.irods.jargon.core.pub.EnvironmentalInfoAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.io.IRODSFileFactory;
import org.irods.jargon.core.utils.MiscIRODSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Central context for console, including connection and configuration artifacts
 * 
 * @author Mike Conway - NIEHS
 *
 */
@Component
public class DrsConsoleContext {

	public static final Logger log = LoggerFactory.getLogger(DrsConsoleContext.class);

	private IRODSAccount irodsAccount;
	private IRODSFileSystem irodsFileSystem;
	private String cwd = "";
	private boolean initd = false;

	/**
	 * 
	 */
	public DrsConsoleContext() {
		try {
			this.irodsFileSystem = IRODSFileSystem.instance();
		} catch (JargonException e) {
			log.error("error creating console context", e);
			throw new JargonRuntimeException("unable to create context", e);
		}
	}

	/**
	 * Initialize a connection based on gathered properties, respond with a block
	 * describing the characteristics of the
	 * 
	 * @param hostName {@code String}
	 * @param zone     {@code String}
	 * @param port     {@code int}
	 * @param userName {@code String}
	 * @param password {@code String}
	 * @param authType {@code String}
	 * @return {@link IRODSServerProperties}
	 * @throws JargonException {@link JargonException}
	 */
	public IRODSServerProperties init(String hostName, String zone, int port, String userName, String password,
			String authType) throws JargonException {
		log.info("init()");
		this.irodsAccount = IRODSAccount.instance(hostName, port, userName, password, "", zone, "",
				AuthScheme.findTypeByString(authType));
		this.cwd = MiscIRODSUtils.buildIRODSUserHomeForAccountUsingDefaultScheme(irodsAccount);
		try {
			log.debug("logging in to validate and get server props");
			EnvironmentalInfoAO environmentalInfoAO = irodsFileSystem.getIRODSAccessObjectFactory()
					.getEnvironmentalInfoAO(irodsAccount);
			IRODSServerProperties irodsServerProps = environmentalInfoAO.getIRODSServerProperties();
			log.debug("IRODSServerProperties:{}", irodsServerProps);
			this.initd = true;
			return irodsServerProps;
		} finally {
			irodsFileSystem.closeAndEatExceptions();
		}
	}

	public IRODSAccount getIrodsAccount() {
		return irodsAccount;
	}

	/**
	 * Get the factory for Jargon objects, this requires the init() to have been
	 * called beforehand
	 * 
	 * @return {@link IRODSAccessObjectFactory}
	 */
	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		if (!initd) {
			throw new JargonRuntimeException("Not inited");
		}
		try {
			return irodsFileSystem.getIRODSAccessObjectFactory();
		} catch (JargonException e) {
			log.error("unable to get iRODSAccessObjectFactory");
			throw new JargonRuntimeException("unable to get IRDOSAccessObjectFactory", e);
		}
	}

	/**
	 * Return the {@link IRODSFileFactory} for the currently logged in user. init()
	 * must have been called
	 * 
	 * @return {@link IRODSFileFactory}
	 */
	public IRODSFileFactory getIrodsFileFactory() {
		try {
			return getIrodsAccessObjectFactory().getIRODSFileFactory(getIrodsAccount());
		} catch (JargonException e) {
			log.error("unable to get iRODSAccessObjectFactory");
			throw new JargonRuntimeException("unable to get IRDOSAccessObjectFactory", e);
		}
	}

	public void setIrodsAccount(IRODSAccount irodsAccount) {
		this.irodsAccount = irodsAccount;
	}

	public String getCwd() {
		if (!initd) {
			throw new JargonRuntimeException("Not inited");
		}
		return cwd;
	}

	public void setCwd(String cwd) {
		if (!initd) {
			throw new JargonRuntimeException("Not inited");
		}

		this.cwd = cwd;
	}

	public boolean isInitd() {
		return initd;
	}

}
