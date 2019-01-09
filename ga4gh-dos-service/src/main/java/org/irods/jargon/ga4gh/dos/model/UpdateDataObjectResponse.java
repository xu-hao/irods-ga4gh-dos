package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * UpdateDataObjectResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class UpdateDataObjectResponse {
	@JsonProperty("data_object_id")
	private String dataObjectId = null;

	public UpdateDataObjectResponse dataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
		return this;
	}

	/**
	 * The identifier of the Data Object updated.
	 * 
	 * @return dataObjectId
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the Data Object updated.")
	@NotNull

	public String getDataObjectId() {
		return dataObjectId;
	}

	public void setDataObjectId(String dataObjectId) {
		this.dataObjectId = dataObjectId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UpdateDataObjectResponse updateDataObjectResponse = (UpdateDataObjectResponse) o;
		return Objects.equals(this.dataObjectId, updateDataObjectResponse.dataObjectId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataObjectId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class UpdateDataObjectResponse {\n");

		sb.append("    dataObjectId: ").append(toIndentedString(dataObjectId)).append("\n");
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
