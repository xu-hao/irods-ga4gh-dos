/**
 * 
 */
package org.irods.jargon.ga4gh.dos.exception;

/**
 * Requested data cannot be resolved from the underlying data store
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class DosDataNotFoundException extends DosException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 873925347616212695L;

	/**
	 * @param message
	 * @param underlyingIRODSExceptionCode
	 */
	public DosDataNotFoundException(String message, int underlyingIRODSExceptionCode) {
		super(message, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public DosDataNotFoundException(String message, Throwable cause, int underlyingIRODSExceptionCode) {
		super(message, cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DosDataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DosDataNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 * @param underlyingIRODSExceptionCode
	 */
	public DosDataNotFoundException(Throwable cause, int underlyingIRODSExceptionCode) {
		super(cause, underlyingIRODSExceptionCode);
	}

	/**
	 * @param cause
	 */
	public DosDataNotFoundException(Throwable cause) {
		super(cause);
	}

}
