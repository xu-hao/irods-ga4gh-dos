package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * DeleteObjectResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class DeleteObjectResponse {
	@JsonProperty("object_id")
	private String objectId = null;

	public DeleteObjectResponse objectId(String objectId) {
		this.objectId = objectId;
		return this;
	}

	/**
	 * The identifier of the Data Object deleted.
	 * 
	 * @return objectId
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the Data Object deleted.")
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
		DeleteObjectResponse deleteObjectResponse = (DeleteObjectResponse) o;
		return Objects.equals(this.objectId, deleteObjectResponse.objectId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(objectId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DeleteObjectResponse {\n");

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
