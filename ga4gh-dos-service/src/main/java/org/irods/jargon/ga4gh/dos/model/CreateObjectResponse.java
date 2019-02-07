package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * CreateObjectResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class CreateObjectResponse {
	@JsonProperty("object_id")
	private String objectId = null;

	public CreateObjectResponse objectId(String objectId) {
		this.objectId = objectId;
		return this;
	}

	/**
	 * The ID of the created Data Object.
	 * 
	 * @return objectId
	 **/
	@ApiModelProperty(value = "The ID of the created Data Object.")

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CreateObjectResponse createObjectResponse = (CreateObjectResponse) o;
		return Objects.equals(this.objectId, createObjectResponse.objectId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(objectId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CreateObjectResponse {\n");

		sb.append("    objectId: ").append(toIndentedString(objectId)).append("\n");
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
