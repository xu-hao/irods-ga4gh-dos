/**
 * 
 */
package org.irods.jargon.ga4gh.dos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Security configuration
 * 
 * @author Mike Conway - NIEHS
 *
 */
@Configuration
@EnableWebSecurity
public class DosSecurityConfig {

	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("user1Pass"))
				.authorities("ROLE_USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/securityNone").permitAll().anyRequest().authenticated().and().httpBasic()
				.authenticationEntryPoint(authenticationEntryPoint);

		http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
	}

	public DosSecurityConfig() {
	}

}
