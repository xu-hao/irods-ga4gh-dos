/**
 * 
 */
package org.irods.jargon.ga4gh.dos.utils;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * Misc conversion utils
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class ServiceUtils {

	private ServiceUtils() {
	}

	/**
	 * Transform a {@link Date} to an {@link OffsetDateTime}
	 * 
	 * @param date {@link Date}
	 * @return {@link OffsetDateTime}
	 */
	public static OffsetDateTime offsetDateTimeFromDate(final Date date) {
		if (date == null) {
			throw new IllegalArgumentException("null date");
		}
		return date.toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime();
	}

	/**
	 * Creates a server URL in the following format:
	 *
	 * <p>
	 * <code>scheme://serverName:serverPort/contextPath/resourcePath</code>
	 * </p>
	 *
	 * <p>
	 * If the scheme is 'http' and the server port is the standard for HTTP, which
	 * is port 80, the port will not be included in the resulting URL. If the scheme
	 * is 'https' and the server port is the standard for HTTPS, which is 443, the
	 * port will not be included in the resulting URL. If the port is non-standard
	 * for the scheme, the port will be included in the resulting URL.
	 * </p>
	 *
	 * @param request      - a javax.servlet.http.HttpServletRequest from which the
	 *                     scheme, server name, port, and context path are derived.
	 * @param resourcePath - path to append to the end of server URL after
	 *                     scheme://serverName:serverPort/contextPath. If the path
	 *                     to append does not start with a forward slash, the method
	 *                     will add one to make the resulting URL proper.
	 *
	 * @return the generated URL string
	 * @author Cody Burleson (cody at base22 dot com), Base22, LLC.
	 *
	 */
	protected static String createURL(HttpServletRequest request, String resourcePath) {

		int port = request.getServerPort();
		StringBuilder result = new StringBuilder();
		result.append(request.getScheme()).append("://").append(request.getServerName());

		if ((request.getScheme().equals("http") && port != 80)
				|| (request.getScheme().equals("https") && port != 443)) {
			result.append(':').append(port);
		}

		result.append(request.getContextPath());

		if (resourcePath != null && resourcePath.length() > 0) {
			if (!resourcePath.startsWith("/")) {
				result.append("/");
			}
			result.append(resourcePath);
		}

		return result.toString();

	}

}
