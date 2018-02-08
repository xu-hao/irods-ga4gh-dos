/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.dos.exception;

/**
 * Reflects an unchecked exception arising from the low level jargon/irods layer
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class DosSystemException extends DosRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4783721633950493596L;

	/**
	 * @param message
	 */
	public DosSystemException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DosSystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DosSystemException(String message, Throwable cause) {
		super(message, cause);
	}

}
