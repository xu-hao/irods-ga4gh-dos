package org.irods.jargon.ga4gh.dos.bundlemgmnt.exception;

import org.irods.jargon.core.exception.JargonException;

/**
 * A bundle cannot be located via its identifier
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class BundleNotFoundException extends JargonException {

	private static final long serialVersionUID = 1136431576001642669L;

	public BundleNotFoundException(String message) {
		super(message);
	}

	public BundleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BundleNotFoundException(Throwable cause) {
		super(cause);
	}

	public BundleNotFoundException(String message, Throwable cause, int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	public BundleNotFoundException(Throwable cause, int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	public BundleNotFoundException(String message, int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
