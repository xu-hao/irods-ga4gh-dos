package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Data Object one would like to index. One must provide any aliases and
 * URLs to this file when sending the CreateObjectRequest. It is up to
 * implementations to validate that the Data Object is available from the
 * provided URLs.
 */
@ApiModel(description = "The Data Object one would like to index. One must provide any aliases and URLs to this file when sending the CreateObjectRequest. It is up to implementations to validate that the Data Object is available from the provided URLs.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class CreateObjectRequest {
	@JsonProperty("object")
	private Object object = null;

	public CreateObjectRequest object(Object object) {
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
		CreateObjectRequest createObjectRequest = (CreateObjectRequest) o;
		return Objects.equals(this.object, createObjectRequest.object);
	}

	@Override
	public int hashCode() {
		return Objects.hash(object);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CreateObjectRequest {\n");

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
