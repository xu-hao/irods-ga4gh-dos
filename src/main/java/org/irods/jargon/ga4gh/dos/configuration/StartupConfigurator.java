/**
 * 
 */
package org.irods.jargon.ga4gh.dos.configuration;

import org.irods.jargon.core.connection.ClientServerNegotiationPolicy;
import org.irods.jargon.core.connection.ClientServerNegotiationPolicy.SslNegotiationPolicy;
import org.irods.jargon.core.connection.IRODSSession;
import org.irods.jargon.core.connection.SettableJargonProperties;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Mike Conway Wired-in class that takes configuration and core jargon
 *         components and injects appropriate configuration into the underlying
 *         jargon properties system
 *
 */
public class StartupConfigurator {

	private DosConfiguration dosConfiguration;
	private IRODSSession irodsSession;
	private IRODSAccessObjectFactory irodsAccessObjectFactory;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public StartupConfigurator() {

	}

	/**
	 * @return the irodsSession
	 */
	public IRODSSession getIrodsSession() {
		return irodsSession;
	}

	/**
	 * @param irodsSession
	 *            the irodsSession to set
	 */
	public void setIrodsSession(IRODSSession irodsSession) {
		this.irodsSession = irodsSession;
	}

	/**
	 * this method is wired into the spring config after the injection of the props
	 * and <code>IRODSSession</code> so that property configuration can be
	 * accomplished
	 */
	public void init() {
		log.info("init()");

		if (dosConfiguration == null) {
			log.error("null dosConfiguration");
			throw new IllegalStateException("null dosConfiguration");
		}

		if (irodsSession == null) {
			log.error("null irodsSession");
			throw new IllegalStateException("null irodsSession");
		}

		log.info("configuration with:{}", dosConfiguration);

		SettableJargonProperties props = new SettableJargonProperties(irodsSession.getJargonProperties());
		props.setComputeChecksumAfterTransfer(dosConfiguration.isComputeChecksum());
		log.info("set checksum policy to:{}", dosConfiguration.isComputeChecksum());

		SslNegotiationPolicy policyToSet = ClientServerNegotiationPolicy
				.findSslNegotiationPolicyFromString(dosConfiguration.getSslNegotiationPolicy());

		log.info("policyToSet:{}", policyToSet);

		props.setNegotiationPolicy(policyToSet);
		log.info("negotiation policy set to:{}", props.getNegotiationPolicy());

		getIrodsSession().setJargonProperties(props);
		log.info("config of jargon props complete");

	}

	/**
	 * @return the irodsAccessObjectFactory
	 */
	public IRODSAccessObjectFactory getIrodsAccessObjectFactory() {
		return irodsAccessObjectFactory;
	}

	/**
	 * @param irodsAccessObjectFactory
	 *            the irodsAccessObjectFactory to set
	 */
	public void setIrodsAccessObjectFactory(IRODSAccessObjectFactory irodsAccessObjectFactory) {
		this.irodsAccessObjectFactory = irodsAccessObjectFactory;
	}

	public DosConfiguration getDosConfiguration() {
		return dosConfiguration;
	}

	public void setDosConfiguration(DosConfiguration dosConfiguration) {
		this.dosConfiguration = dosConfiguration;
	}

}
