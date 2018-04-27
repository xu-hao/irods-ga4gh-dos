/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services.metadata;

import java.util.HashMap;
import java.util.Map;

/**
 * Optional GA4GH descriptive metadata to be included in the system metadata
 * payload
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class SystemDescriptiveMetadata {

	/**
	 * Arbitrary KVPs to be placed into GA4GH system metadata
	 * 
	 */
	private Map<String, String> systemMetadata = new HashMap<String, String>();

	/**
	 * 
	 */
	public SystemDescriptiveMetadata() {
	}

	public void addTerm(String key, String value) {
		systemMetadata.put(key, value);
	}

	public Map<String, String> getKvps() {
		return systemMetadata;
	}

}
