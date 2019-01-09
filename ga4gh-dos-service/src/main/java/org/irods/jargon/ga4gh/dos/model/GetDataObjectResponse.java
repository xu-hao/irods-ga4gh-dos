package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * GetDataObjectResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class GetDataObjectResponse {
	@JsonProperty("data_object")
	private DataObject dataObject = null;

	public GetDataObjectResponse dataObject(DataObject dataObject) {
		this.dataObject = dataObject;
		return this;
	}

	/**
	 * Get dataObject
	 * 
	 * @return dataObject
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public DataObject getDataObject() {
		return dataObject;
	}

	public void setDataObject(DataObject dataObject) {
		this.dataObject = dataObject;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetDataObjectResponse getDataObjectResponse = (GetDataObjectResponse) o;
		return Objects.equals(this.dataObject, getDataObjectResponse.dataObject);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataObject);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetDataObjectResponse {\n");

		sb.append("    dataObject: ").append(toIndentedString(dataObject)).append("\n");
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
