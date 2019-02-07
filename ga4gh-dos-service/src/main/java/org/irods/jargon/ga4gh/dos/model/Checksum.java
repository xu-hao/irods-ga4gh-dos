package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Checksum
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class Checksum {
	@JsonProperty("checksum")
	private String checksum = null;

	@JsonProperty("type")
	private String type = null;

	public Checksum checksum(String checksum) {
		this.checksum = checksum;
		return this;
	}

	/**
	 * The hex-string encoded checksum for the Data.
	 * 
	 * @return checksum
	 **/
	@ApiModelProperty(required = true, value = "The hex-string encoded checksum for the Data.")
	@NotNull

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public Checksum type(String type) {
		this.type = type;
		return this;
	}

	/**
	 * The digest method used to create the checksum. If left unspecified md5 will
	 * be assumed. possible values: md5 # most blob stores provide a checksum using
	 * this multipart-md5 # multipart uploads provide a specialized tag in S3 sha256
	 * sha512
	 * 
	 * @return type
	 **/
	@ApiModelProperty(value = "The digest method used to create the checksum. If left unspecified md5 will be assumed.  possible values: md5                # most blob stores provide a checksum using this multipart-md5      # multipart uploads provide a specialized tag in S3 sha256 sha512")

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Checksum checksum = (Checksum) o;
		return Objects.equals(this.checksum, checksum.checksum) && Objects.equals(this.type, checksum.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(checksum, type);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Checksum {\n");

		sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
