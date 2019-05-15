package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * AccessMethod
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-14T11:28:18.659Z")

public class AccessMethod {
	/**
	 * Type of the access method.
	 */
	public enum TypeEnum {
		S3("s3"),

		GS("gs"),

		FTP("ftp"),

		GSIFTP("gsiftp"),

		GLOBUS("globus"),

		HTSGET("htsget"),

		HTTPS("https"),

		FILE("file");

		private String value;

		TypeEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static TypeEnum fromValue(String text) {
			for (TypeEnum b : TypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("type")
	private TypeEnum type = null;

	@JsonProperty("access_url")
	private AccessURL accessUrl = null;

	@JsonProperty("access_id")
	private String accessId = null;

	@JsonProperty("region")
	private String region = null;

	public AccessMethod type(TypeEnum type) {
		this.type = type;
		return this;
	}

	/**
	 * Type of the access method.
	 * 
	 * @return type
	 **/
	@ApiModelProperty(required = true, value = "Type of the access method.")
	@NotNull

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public AccessMethod accessUrl(AccessURL accessUrl) {
		this.accessUrl = accessUrl;
		return this;
	}

	/**
	 * An `AccessURL` that can be used to fetch the actual object bytes. Note that
	 * at least one of `access_url` and `access_id` must be provided.
	 * 
	 * @return accessUrl
	 **/
	@ApiModelProperty(value = "An `AccessURL` that can be used to fetch the actual object bytes. Note that at least one of `access_url` and `access_id` must be provided.")

	@Valid

	public AccessURL getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(AccessURL accessUrl) {
		this.accessUrl = accessUrl;
	}

	public AccessMethod accessId(String accessId) {
		this.accessId = accessId;
		return this;
	}

	/**
	 * An arbitrary string to be passed to the `/access` method to get an
	 * `AccessURL`. This string must be unique per object. Note that at least one of
	 * `access_url` and `access_id` must be provided.
	 * 
	 * @return accessId
	 **/
	@ApiModelProperty(value = "An arbitrary string to be passed to the `/access` method to get an `AccessURL`. This string must be unique per object. Note that at least one of `access_url` and `access_id` must be provided.")

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public AccessMethod region(String region) {
		this.region = region;
		return this;
	}

	/**
	 * Name of the region in the cloud service provider that the object belongs to.
	 * 
	 * @return region
	 **/
	@ApiModelProperty(example = "us-east-1", value = "Name of the region in the cloud service provider that the object belongs to.")

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AccessMethod accessMethod = (AccessMethod) o;
		return Objects.equals(this.type, accessMethod.type) && Objects.equals(this.accessUrl, accessMethod.accessUrl)
				&& Objects.equals(this.accessId, accessMethod.accessId)
				&& Objects.equals(this.region, accessMethod.region);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, accessUrl, accessId, region);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AccessMethod {\n");

		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    accessUrl: ").append(toIndentedString(accessUrl)).append("\n");
		sb.append("    accessId: ").append(toIndentedString(accessId)).append("\n");
		sb.append("    region: ").append(toIndentedString(region)).append("\n");
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
