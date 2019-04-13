/**
 * 
 */
package org.irods.jargon.ga4gh.dos.utils;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Misc conversion utils to create JSON response objects
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class ConversionUtils {

	private ConversionUtils() {
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

}
