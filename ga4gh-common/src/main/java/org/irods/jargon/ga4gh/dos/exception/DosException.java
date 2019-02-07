package org.irods.jargon.ga4gh.dos.exception;

import org.irods.jargon.core.exception.JargonException;

public class DosException extends JargonException {

	private static final long serialVersionUID = 445933707941534964L;

	public DosException(String message, int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

	public DosException(String message, Throwable cause, int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	public DosException(String message, Throwable cause) {
		super(message, cause);
	}

	public DosException(String message) {
		super(message);
	}

	public DosException(Throwable cause, int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	public DosException(Throwable cause) {
		super(cause);
	}

}
