package gov.nih.niehs.ods.ga4gh.dos.exception;

import org.irods.jargon.core.exception.JargonRuntimeException;

/**
 * General exception in rest processing
 * 
 * @author Mike Conway - DICE (www.irods.org)
 * 
 */
public class DosRuntimeException extends JargonRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9017853086935903803L;

	/**
	 * @param message
	 */
	public DosRuntimeException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DosRuntimeException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DosRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
