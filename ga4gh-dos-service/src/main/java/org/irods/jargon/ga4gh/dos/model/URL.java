package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * URL
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class URL {
	@JsonProperty("url")
	private String url = null;

	@JsonProperty("system_metadata")
	private SystemMetadata systemMetadata = null;

	@JsonProperty("user_metadata")
	private UserMetadata userMetadata = null;

	@JsonProperty("authorization_metadata")
	private AuthorizationMetadata authorizationMetadata = null;

	public URL url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * A URL that can be used to access the file.
	 * 
	 * @return url
	 **/
	@ApiModelProperty(required = true, value = "A URL that can be used to access the file.")
	@NotNull

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public URL systemMetadata(SystemMetadata systemMetadata) {
		this.systemMetadata = systemMetadata;
		return this;
	}

	/**
	 * Get systemMetadata
	 * 
	 * @return systemMetadata
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public SystemMetadata getSystemMetadata() {
		return systemMetadata;
	}

	public void setSystemMetadata(SystemMetadata systemMetadata) {
		this.systemMetadata = systemMetadata;
	}

	public URL userMetadata(UserMetadata userMetadata) {
		this.userMetadata = userMetadata;
		return this;
	}

	/**
	 * Get userMetadata
	 * 
	 * @return userMetadata
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public UserMetadata getUserMetadata() {
		return userMetadata;
	}

	public void setUserMetadata(UserMetadata userMetadata) {
		this.userMetadata = userMetadata;
	}

	public URL authorizationMetadata(AuthorizationMetadata authorizationMetadata) {
		this.authorizationMetadata = authorizationMetadata;
		return this;
	}

	/**
	 * Get authorizationMetadata
	 * 
	 * @return authorizationMetadata
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public AuthorizationMetadata getAuthorizationMetadata() {
		return authorizationMetadata;
	}

	public void setAuthorizationMetadata(AuthorizationMetadata authorizationMetadata) {
		this.authorizationMetadata = authorizationMetadata;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		URL URL = (URL) o;
		return Objects.equals(this.url, URL.url) && Objects.equals(this.systemMetadata, URL.systemMetadata)
				&& Objects.equals(this.userMetadata, URL.userMetadata)
				&& Objects.equals(this.authorizationMetadata, URL.authorizationMetadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(url, systemMetadata, userMetadata, authorizationMetadata);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class URL {\n");

		sb.append("    url: ").append(toIndentedString(url)).append("\n");
		sb.append("    systemMetadata: ").append(toIndentedString(systemMetadata)).append("\n");
		sb.append("    userMetadata: ").append(toIndentedString(userMetadata)).append("\n");
		sb.append("    authorizationMetadata: ").append(toIndentedString(authorizationMetadata)).append("\n");
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
