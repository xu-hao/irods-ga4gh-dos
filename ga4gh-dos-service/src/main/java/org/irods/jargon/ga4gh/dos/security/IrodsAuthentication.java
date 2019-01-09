/**
 * 
 */
package org.irods.jargon.ga4gh.dos.security;

import java.util.Collection;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.connection.auth.AuthResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Authentication containing iRODS credentials information
 * 
 * @author mconway
 *
 */
public class IrodsAuthentication implements Authentication {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2505172295435781501L;
	private final IRODSAccount irodsAccount;
	private final AuthResponse authResponse;

	/**
	 * @param irodsAccount
	 * @param authResponse
	 */
	public IrodsAuthentication(IRODSAccount irodsAccount,
			AuthResponse authResponse) {
		super();
		if (irodsAccount == null) {
			throw new IllegalArgumentException("null irodsAccount");
		}
		if (authResponse == null) {
			throw new IllegalArgumentException("null authResponse");
		}
		this.irodsAccount = irodsAccount;
		this.authResponse = authResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.Principal#getName()
	 */
	@Override
	public String getName() {
		return irodsAccount.getUserName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO: add iRODS groups?
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getCredentials()
	 */
	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return authResponse.getAuthenticatedIRODSAccount().getPassword();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getDetails()
	 */
	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return authResponse.getAuthenticatedIRODSAccount().getUserName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.Authentication#isAuthenticated()
	 */
	@Override
	public boolean isAuthenticated() {
		return authResponse.isSuccessful();
	}

	/**
	 * @return the authResponse
	 */
	public AuthResponse getAuthResponse() {
		return authResponse;
	}

	/**
	 * @return the irodsAccount
	 */
	public IRODSAccount getIrodsAccount() {
		return irodsAccount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IrodsAuthentication [");
		if (irodsAccount != null) {
			builder.append("irodsAccount=").append(irodsAccount).append(", ");
		}
		if (authResponse != null) {
			builder.append("authResponse=").append(authResponse);
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
		// unused, will get authenticated via the enclosed iRODS AuthResponse
	}

}
