/**
 * 
 */
package org.irods.jargon.ga4gh.dos.utils;

import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;

/**
 * Various utils for handling REST configuration information
 * 
 * @author Mike Conway - DICE (www.irods.org) see http://code.renci.org for
 *         trackers, access info, and documentation
 * 
 */
public class ConfigurationUtils {

	/**
	 * Build an exemplar .irodsEnv file based on the configuration for a given user
	 * name
	 * 
	 * @param restConfiguration
	 * @param userName
	 * @return
	 */
	public static String buildIrodsEnvForConfigAndUser(final DosConfiguration dosConfiguration, final String userName) {

		if (dosConfiguration == null) {
			throw new IllegalArgumentException("null restConfiguration");
		}

		if (userName == null || userName.isEmpty()) {
			throw new IllegalArgumentException("null or empty userName");
		}

		StringBuilder sb = new StringBuilder();

		sb.append("irodsHost=");
		sb.append(dosConfiguration.getIrodsHost());
		sb.append("\n");
		sb.append("irodsPort=");
		sb.append(dosConfiguration.getPort());
		sb.append("\n");
		// sb.append("irodsDefResource=");
		// sb.append(dosConfiguration.getDefaultStorageResource());
		sb.append("\n");
		sb.append("irodsHome=/");
		sb.append(dosConfiguration.getIrodsZone());
		sb.append("/home/");
		sb.append(userName);
		sb.append("\n");
		sb.append("irodsCwd=");
		sb.append("/");
		sb.append(dosConfiguration.getIrodsZone());
		sb.append("/home/");
		sb.append(userName);
		sb.append("\n");
		sb.append("irodsUserName=");
		sb.append(userName);
		sb.append("\n");
		sb.append("irodsZone=");
		sb.append(dosConfiguration.getIrodsZone());

		return sb.toString();

	}

}
