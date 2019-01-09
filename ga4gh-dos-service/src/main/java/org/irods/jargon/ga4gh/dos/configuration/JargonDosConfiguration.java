/**
 * 
 */
package org.irods.jargon.ga4gh.dos.configuration;

import org.irods.jargon.core.connection.IRODSProtocolManager;
import org.irods.jargon.core.connection.IRODSSession;
import org.irods.jargon.core.connection.IRODSSimpleProtocolManager;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.IRODSAccessObjectFactoryImpl;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionServiceFactory;
import org.irods.jargon.extensions.datatyper.DataTyperSettings;
import org.irodsext.datatyper.IrodsextDataTypeResolutionServiceFactoryImpl;
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
}
