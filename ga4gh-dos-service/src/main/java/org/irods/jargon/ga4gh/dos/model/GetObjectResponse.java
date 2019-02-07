package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * GetObjectResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class GetObjectResponse {
	@JsonProperty("object")
	private Object object = null;

	public GetObjectResponse object(Object object) {
		this.object = object;
		return this;
	}

	/**
	 * Get object
	 * 
	 * @return object
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetObjectResponse getObjectResponse = (GetObjectResponse) o;
		return Objects.equals(this.object, getObjectResponse.object);
	}

	@Override
	public int hashCode() {
		return Objects.hash(object);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetObjectResponse {\n");

		sb.append("    object: ").append(toIndentedString(object)).append("\n");
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
