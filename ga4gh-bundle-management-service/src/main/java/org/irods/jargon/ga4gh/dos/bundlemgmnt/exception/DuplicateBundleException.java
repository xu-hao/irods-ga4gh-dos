package org.irods.jargon.ga4gh.dos.bundlemgmnt.exception;

import org.irods.jargon.core.exception.JargonException;

public class DuplicateBundleException extends JargonException {

	private static final long serialVersionUID = 582709547015691016L;

	public DuplicateBundleException(String message) {
		super(message);
	}

	public DuplicateBundleException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicateBundleException(Throwable cause) {
		super(cause);
	}

	public DuplicateBundleException(String message, Throwable cause, int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	public DuplicateBundleException(Throwable cause, int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	public DuplicateBundleException(String message, int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

}
