package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * UpdateObjectResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class UpdateObjectResponse {
	@JsonProperty("object_id")
	private String objectId = null;

	public UpdateObjectResponse objectId(String objectId) {
		this.objectId = objectId;
		return this;
	}

	/**
	 * The identifier of the Data Object updated.
	 * 
	 * @return objectId
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the Data Object updated.")
	@NotNull

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
		UpdateObjectResponse updateObjectResponse = (UpdateObjectResponse) o;
		return Objects.equals(this.objectId, updateObjectResponse.objectId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(objectId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class UpdateObjectResponse {\n");

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
