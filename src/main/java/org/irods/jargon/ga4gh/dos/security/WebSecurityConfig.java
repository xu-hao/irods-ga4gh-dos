/**
 * 
 */
package org.irods.jargon.ga4gh.dos.security;

import org.irods.jargon.core.connection.IRODSSession;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

/**
 * Spring security configurer
 * 
 * @author mconway
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DosConfiguration dosConfiguration;
	@Autowired
	private IRODSSession irodsSession;
	@Autowired
	private IRODSAccessObjectFactory irodsAccessObjectFactory;
	@Autowired
	private IrodsAuthenticationProvider irodsAuthenticationProvider;
	@Autowired
	private IrodsBasicAuthEntryPoint irodsBasicAuthEntryPoint;
	@Autowired
	private ConnectionCloseFilter connectionCloseFilter;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter #configure(org.springframework.security.config
	 * .annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("configure()");

		http.authenticationProvider(irodsAuthenticationProvider).authorizeRequests().anyRequest().authenticated().and()
				.httpBasic().realmName(dosConfiguration.getRealm()).authenticationEntryPoint(irodsBasicAuthEntryPoint)
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(connectionCloseFilter, SecurityContextPersistenceFilter.class);
	}

	/**
	 * @return the irodsAuthenticationProvider
	 */
	public IrodsAuthenticationProvider getIrodsAuthenticationProvider() {
		return irodsAuthenticationProvider;
	}

	/**
	 * @param irodsAuthenticationProvider
	 *            the irodsAuthenticationProvider to set
	 */
	public void setIrodsAuthenticationProvider(IrodsAuthenticationProvider irodsAuthenticationProvider) {
		this.irodsAuthenticationProvider = irodsAuthenticationProvider;
	}

	/**
	 * @return the irodsBasicAuthEntryPoint
	 */
	public IrodsBasicAuthEntryPoint getIrodsBasicAuthEntryPoint() {
		return irodsBasicAuthEntryPoint;
	}

	/**
	 * @param irodsBasicAuthEntryPoint
	 *            the irodsBasicAuthEntryPoint to set
	 */
	public void setIrodsBasicAuthEntryPoint(IrodsBasicAuthEntryPoint irodsBasicAuthEntryPoint) {
		this.irodsBasicAuthEntryPoint = irodsBasicAuthEntryPoint;
	}

	public DosConfiguration getDosConfiguration() {
		return dosConfiguration;
	}

	public void setDosConfiguration(DosConfiguration dosConfiguration) {
		this.dosConfiguration = dosConfiguration;
	}

}
