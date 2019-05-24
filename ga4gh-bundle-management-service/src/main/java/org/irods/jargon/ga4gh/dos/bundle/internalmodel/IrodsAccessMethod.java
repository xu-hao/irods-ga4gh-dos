/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundle.internalmodel;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.ga4gh.dos.model.AccessMethod.TypeEnum;

/**
 * Intermediate representation of an access method for a data object
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class IrodsAccessMethod {

	/**
	 * Optional headers for URL
	 */
	private List<String> headers = new ArrayList<>();

	/**
	 * A url used to access the data
	 */
	private String url = "";

	/**
	 * An identifier used for the given access type
	 */
	private String accessId = "";

	/**
	 * An optional region to use for access
	 */
	private String region = "";

	/**
	 * The access mode
	 */
	private TypeEnum type;

	/**
	 * 
	 */
	public IrodsAccessMethod() {

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("IrodsAccessMethod [headers=")
				.append(headers != null ? headers.subList(0, Math.min(headers.size(), maxLen)) : null).append(", url=")
				.append(url).append(", accessId=").append(accessId).append(", region=").append(region).append(", type=")
				.append(type).append("]");
		return builder.toString();
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

}
