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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class ListObjectsResponse {
	@JsonProperty("objects")
	@Valid
	private List<Object> objects = null;

	@JsonProperty("next_page_token")
	private String nextPageToken = null;

	public ListObjectsResponse objects(List<Object> objects) {
		this.objects = objects;
		return this;
	}

	public ListObjectsResponse addObjectsItem(Object objectsItem) {
		if (this.objects == null) {
			this.objects = new ArrayList<Object>();
		}
		this.objects.add(objectsItem);
		return this;
	}

	/**
	 * The list of Data Objects.
	 * 
	 * @return objects
	 **/
	@ApiModelProperty(value = "The list of Data Objects.")

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	public ListObjectsResponse nextPageToken(String nextPageToken) {
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
		ListObjectsResponse listObjectsResponse = (ListObjectsResponse) o;
		return Objects.equals(this.objects, listObjectsResponse.objects)
				&& Objects.equals(this.nextPageToken, listObjectsResponse.nextPageToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(objects, nextPageToken);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ListObjectsResponse {\n");

		sb.append("    objects: ").append(toIndentedString(objects)).append("\n");
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
