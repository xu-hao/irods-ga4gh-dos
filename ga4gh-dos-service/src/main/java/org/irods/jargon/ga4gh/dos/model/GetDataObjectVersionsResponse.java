package org.irods.jargon.ga4gh.dos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * GetDataObjectVersionsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class GetDataObjectVersionsResponse {
	@JsonProperty("data_objects")
	@Valid
	private List<DataObject> dataObjects = new ArrayList<>();

	public GetDataObjectVersionsResponse dataObjects(List<DataObject> dataObjects) {
		this.dataObjects = dataObjects;
		return this;
	}

	public GetDataObjectVersionsResponse addDataObjectsItem(DataObject dataObjectsItem) {
		this.dataObjects.add(dataObjectsItem);
		return this;
	}

	/**
	 * All versions of the Data Objects that match the GetDataObjectVersions
	 * request.
	 * 
	 * @return dataObjects
	 **/
	@ApiModelProperty(required = true, value = "All versions of the Data Objects that match the GetDataObjectVersions request.")
	@NotNull

	@Valid

	public List<DataObject> getDataObjects() {
		return dataObjects;
	}

	public void setDataObjects(List<DataObject> dataObjects) {
		this.dataObjects = dataObjects;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetDataObjectVersionsResponse getDataObjectVersionsResponse = (GetDataObjectVersionsResponse) o;
		return Objects.equals(this.dataObjects, getDataObjectVersionsResponse.dataObjects);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataObjects);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetDataObjectVersionsResponse {\n");

		sb.append("    dataObjects: ").append(toIndentedString(dataObjects)).append("\n");
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
