package org.irods.jargon.ga4gh.dos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * A list of Data Objects matching the requested parameters, and a paging token,
 * that can be used to retrieve more results.
 */
@ApiModel(description = "A list of Data Objects matching the requested parameters, and a paging token, that can be used to retrieve more results.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class ListDataObjectsResponse {
	@JsonProperty("data_objects")
	@Valid
	private List<DataObject> dataObjects = null;

	@JsonProperty("next_page_token")
	private String nextPageToken = null;

	public ListDataObjectsResponse dataObjects(List<DataObject> dataObjects) {
		this.dataObjects = dataObjects;
		return this;
	}

	public ListDataObjectsResponse addDataObjectsItem(DataObject dataObjectsItem) {
		if (this.dataObjects == null) {
			this.dataObjects = new ArrayList<>();
		}
		this.dataObjects.add(dataObjectsItem);
		return this;
	}

	/**
	 * The list of Data Objects.
	 * 
	 * @return dataObjects
	 **/
	@ApiModelProperty(value = "The list of Data Objects.")

	@Valid

	public List<DataObject> getDataObjects() {
		return dataObjects;
	}

	public void setDataObjects(List<DataObject> dataObjects) {
		this.dataObjects = dataObjects;
	}

	public ListDataObjectsResponse nextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
		return this;
	}

	/**
	 * The continuation token, which is used to page through large result sets.
	 * Provide this value in a subsequent request to return the next page of
	 * results. This field will be empty if there aren't any additional results.
	 * 
	 * @return nextPageToken
	 **/
	@ApiModelProperty(value = "The continuation token, which is used to page through large result sets. Provide this value in a subsequent request to return the next page of results. This field will be empty if there aren't any additional results.")

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ListDataObjectsResponse listDataObjectsResponse = (ListDataObjectsResponse) o;
		return Objects.equals(this.dataObjects, listDataObjectsResponse.dataObjects)
				&& Objects.equals(this.nextPageToken, listDataObjectsResponse.nextPageToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataObjects, nextPageToken);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ListDataObjectsResponse {\n");

		sb.append("    dataObjects: ").append(toIndentedString(dataObjects)).append("\n");
		sb.append("    nextPageToken: ").append(toIndentedString(nextPageToken)).append("\n");
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
