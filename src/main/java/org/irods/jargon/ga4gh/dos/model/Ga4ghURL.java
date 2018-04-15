package org.irods.jargon.ga4gh.dos.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Ga4ghURL
 */
@javax.annotation.Generated(value = "org.irods.jargon.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghURL {
	@JsonProperty("url")
	private String url = null;

	@JsonProperty("system_metadata")
	private Map<String, String> systemMetadata = new HashMap<String, String>();

	@JsonProperty("user_metadata")
	private Map<String, String> userMetadata = new HashMap<String, String>();

	public Ga4ghURL url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * REQUIRED A URL that can be used to access the file.
	 * 
	 * @return url
	 **/
	@ApiModelProperty(value = "REQUIRED A URL that can be used to access the file.")

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Ga4ghURL systemMetadata(Map<String, String> systemMetadata) {
		this.systemMetadata = systemMetadata;
		return this;
	}

	/**
	 * OPTIONAL These values are reported by the underlying object store. A set of
	 * key-value pairs that represent system metadata about the object.
	 * 
	 * @return systemMetadata
	 **/
	@ApiModelProperty(value = "OPTIONAL These values are reported by the underlying object store. A set of key-value pairs that represent system metadata about the object.")

	@Valid

	public Map<String, String> getSystemMetadata() {
		return systemMetadata;
	}

	public void setSystemMetadata(Map<String, String> systemMetadata) {
		this.systemMetadata = systemMetadata;
	}

	public Ga4ghURL userMetadata(Map<String, String> userMetadata) {
		this.userMetadata = userMetadata;
		return this;
	}

	/**
	 * OPTIONAL These values are reported by the underlying object store. A set of
	 * key-value pairs that represent metadata provided by the uploader.
	 * 
	 * @return userMetadata
	 **/
	@ApiModelProperty(value = "OPTIONAL These values are reported by the underlying object store. A set of key-value pairs that represent metadata provided by the uploader.")

	@Valid

	public Map<String, String> getUserMetadata() {
		return userMetadata;
	}

	public void setUserMetadata(Map<String, String> userMetadata) {
		this.userMetadata = userMetadata;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Ga4ghURL ga4ghURL = (Ga4ghURL) o;
		return Objects.equals(this.url, ga4ghURL.url) && Objects.equals(this.systemMetadata, ga4ghURL.systemMetadata)
				&& Objects.equals(this.userMetadata, ga4ghURL.userMetadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(url, systemMetadata, userMetadata);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Ga4ghURL {\n");

		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    systemMetadata: ").append(toIndentedString(systemMetadata)).append("\n");
		sb.append("    userMetadata: ").append(toIndentedString(userMetadata)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
