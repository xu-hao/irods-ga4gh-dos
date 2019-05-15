package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * An object that can optionally include information about the error.
 */
@ApiModel(description = "An object that can optionally include information about the error.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-14T11:28:18.659Z")

public class Error {
	@JsonProperty("msg")
	private String msg = null;

	@JsonProperty("status_code")
	private Integer statusCode = null;

	public Error msg(String msg) {
		this.msg = msg;
		return this;
	}

	/**
	 * A detailed error message.
	 * 
	 * @return msg
	 **/
	@ApiModelProperty(value = "A detailed error message.")

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Error statusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	/**
	 * The integer representing the HTTP status code (e.g. 200, 404).
	 * 
	 * @return statusCode
	 **/
	@ApiModelProperty(value = "The integer representing the HTTP status code (e.g. 200, 404).")

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Error error = (Error) o;
		return Objects.equals(this.msg, error.msg) && Objects.equals(this.statusCode, error.statusCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(msg, statusCode);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Error {\n");

		sb.append("    msg: ").append(toIndentedString(msg)).append("\n");
		sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
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
