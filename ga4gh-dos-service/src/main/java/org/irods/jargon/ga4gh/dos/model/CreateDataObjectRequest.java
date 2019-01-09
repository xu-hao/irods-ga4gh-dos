package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The Data Object one would like to index. One must provide any aliases and
 * URLs to this file when sending the CreateDataObjectRequest. It is up to
 * implementations to validate that the Data Object is available from the
 * provided URLs.
 */
@ApiModel(description = "The Data Object one would like to index. One must provide any aliases and URLs to this file when sending the CreateDataObjectRequest. It is up to implementations to validate that the Data Object is available from the provided URLs.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class CreateDataObjectRequest {
	@JsonProperty("data_object")
	private DataObject dataObject = null;

	public CreateDataObjectRequest dataObject(DataObject dataObject) {
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
		CreateDataObjectRequest createDataObjectRequest = (CreateDataObjectRequest) o;
		return Objects.equals(this.dataObject, createDataObjectRequest.dataObject);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataObject);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CreateDataObjectRequest {\n");

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
