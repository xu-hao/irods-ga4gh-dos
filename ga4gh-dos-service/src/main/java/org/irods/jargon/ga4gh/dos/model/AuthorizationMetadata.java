package org.irods.jargon.ga4gh.dos.model;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * OPTIONAL A set of key-value pairs that represent sufficient metadata to be
 * granted access to a resource. It may be helpful to provide details about a
 * specific provider, for example.
 */
@ApiModel(description = "OPTIONAL A set of key-value pairs that represent sufficient metadata to be granted access to a resource. It may be helpful to provide details about a specific provider, for example.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class AuthorizationMetadata extends HashMap<String, String> {
	@JsonProperty("auth_type")
	private String authType = null;

	@JsonProperty("auth_url")
	private String authUrl = null;

	public AuthorizationMetadata authType(String authType) {
		this.authType = authType;
		return this;
	}

	/**
	 * The auth standard being used to make data available. For example, 'OAuth2.0'.
	 * 
	 * @return authType
	 **/
	@ApiModelProperty(value = "The auth standard being used to make data available. For example, 'OAuth2.0'.")

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public AuthorizationMetadata authUrl(String authUrl) {
		this.authUrl = authUrl;
		return this;
	}

	/**
	 * The URL where the auth service is located, for example, a URL to get an OAuth
	 * token.
	 * 
	 * @return authUrl
	 **/
	@ApiModelProperty(value = "The URL where the auth service is located, for example, a URL to get an OAuth token.")

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AuthorizationMetadata authorizationMetadata = (AuthorizationMetadata) o;
		return Objects.equals(this.authType, authorizationMetadata.authType)
				&& Objects.equals(this.authUrl, authorizationMetadata.authUrl) && super.equals(o);
	}

	@Override
	public int hashCode() {
		return Objects.hash(authType, authUrl, super.hashCode());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AuthorizationMetadata {\n");
		sb.append("    ").append(toIndentedString(super.toString())).append("\n");
		sb.append("    authType: ").append(toIndentedString(authType)).append("\n");
		sb.append("    authUrl: ").append(toIndentedString(authUrl)).append("\n");
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
