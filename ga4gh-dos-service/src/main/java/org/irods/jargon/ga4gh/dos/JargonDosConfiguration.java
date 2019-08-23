/**
 * 
 */
package org.irods.jargon.ga4gh.dos;

import org.irods.jargon.core.connection.IRODSProtocolManager;
import org.irods.jargon.core.connection.IRODSSession;
import org.irods.jargon.core.connection.IRODSSimpleProtocolManager;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.IRODSAccessObjectFactoryImpl;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionServiceFactory;
import org.irods.jargon.extensions.datatyper.DataTyperSettings;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.configuration.StartupConfigurator;
import org.irodsext.datatyper.IrodsextDataTypeResolutionServiceFactoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for main iRODS/Jargon infrastructure
 * 
 * @author Mike Conway - NIEHS
 *
 */
@Configuration
public class JargonDosConfiguration {

	@Value("${irods.host}")
	private String irodsHost = "";

	@Value("${irods.port}")
	private int irodsPort = 1247;

	@Value("${irods.zone}")
	private String irodsZone = "";

	@Value("${proxy.user}")
	private String proxyUser = "";

	@Value("${proxy.password}")
	private String proxyPassword = "";

	@Value("${shared.jwt.key}")
	private String jwtKey = "";

	@Value("${jwt.algo}")
	private String jwtAlgo = "";

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		return factory;
	}

	@Bean(destroyMethod = "destroy")
	public IRODSProtocolManager irodsConnectionManager() {
		return new IRODSSimpleProtocolManager();
	}

	@Bean
	public IRODSSession irodsSession(IRODSProtocolManager irodsProtocolManager) {
		IRODSSession irodsSession = new IRODSSession();
		irodsSession.setIrodsProtocolManager(irodsProtocolManager);
		return irodsSession;
	}

	@Bean
	public IRODSAccessObjectFactory irodsAccessObjectFactory(IRODSSession irodsSession) {
		IRODSAccessObjectFactory irodsAccessObjectFactory = new IRODSAccessObjectFactoryImpl(irodsSession);
		return irodsAccessObjectFactory;
	}

	@Bean
	public StartupConfigurator startupConfigurator(IRODSSession irodsSession,
			IRODSAccessObjectFactory irodsAccessObjectFactory, DosConfiguration dosConfiguration) {

		StartupConfigurator startupConfigurator = new StartupConfigurator();
		startupConfigurator.setDosConfiguration(dosConfiguration);
		startupConfigurator.setIrodsAccessObjectFactory(irodsAccessObjectFactory);
		startupConfigurator.setIrodsSession(irodsSession);
		startupConfigurator.init();
		return startupConfigurator;

	}

	@Bean
	public DataTyperSettings dataTyperSettings(DosConfiguration dosConfiguration) {
		DataTyperSettings dataTyperSettings = new DataTyperSettings();
		dataTyperSettings.setDetailedDetermination(dosConfiguration.isDetailedDataTypeDetermination());
		dataTyperSettings.setPersistDataTypes(dosConfiguration.isPersistDataTypes());
		return dataTyperSettings;
	}

	@Bean
	public DataTypeResolutionServiceFactory dataTypeResolutionServiceFactory(
			IRODSAccessObjectFactory irodsAccessObjectFactory, DataTyperSettings dataTyperSettings) {
		IrodsextDataTypeResolutionServiceFactoryImpl dataTypeResolutionServiceFactory = new IrodsextDataTypeResolutionServiceFactoryImpl();
		dataTypeResolutionServiceFactory.setDataTyperSettings(dataTyperSettings);
		dataTypeResolutionServiceFactory.setIrodsAccessObjectFactory(irodsAccessObjectFactory);
		return dataTypeResolutionServiceFactory;

	}

	public String getIrodsHost() {
		return irodsHost;
	}

	public void setIrodsHost(String irodsHost) {
		this.irodsHost = irodsHost;
	}

	public int getIrodsPort() {
		return irodsPort;
	}

	public void setIrodsPort(int irodsPort) {
		this.irodsPort = irodsPort;
	}

	public String getIrodsZone() {
		return irodsZone;
	}

	public void setIrodsZone(String irodsZone) {
		this.irodsZone = irodsZone;
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

	public String getJwtKey() {
		return jwtKey;
	}

	public void setJwtKey(String jwtKey) {
		this.jwtKey = jwtKey;
	}

	public String getJwtAlgo() {
		return jwtAlgo;
	}

	public void setJwtAlgo(String jwtAlgo) {
		this.jwtAlgo = jwtAlgo;
	}
}
