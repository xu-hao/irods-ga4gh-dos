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
 * GetObjectVersionsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class GetObjectVersionsResponse {
	@JsonProperty("objects")
	@Valid
	private List<Object> objects = new ArrayList<Object>();

	public GetObjectVersionsResponse objects(List<Object> objects) {
		this.objects = objects;
		return this;
	}

	public GetObjectVersionsResponse addObjectsItem(Object objectsItem) {
		this.objects.add(objectsItem);
		return this;
	}

	/**
	 * All versions of the Data Objects that match the GetObjectVersions request.
	 * 
	 * @return objects
	 **/
	@ApiModelProperty(required = true, value = "All versions of the Data Objects that match the GetObjectVersions request.")
	@NotNull

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetObjectVersionsResponse getObjectVersionsResponse = (GetObjectVersionsResponse) o;
		return Objects.equals(this.objects, getObjectVersionsResponse.objects);
	}

	@Override
	public int hashCode() {
		return Objects.hash(objects);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetObjectVersionsResponse {\n");

		sb.append("    objects: ").append(toIndentedString(objects)).append("\n");
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
