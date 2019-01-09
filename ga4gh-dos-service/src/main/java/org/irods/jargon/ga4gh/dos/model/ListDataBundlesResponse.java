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
 * A list of Data Bundles matching the request parameters and a continuation
 * token that can be used to retrieve more results.
 */
@ApiModel(description = "A list of Data Bundles matching the request parameters and a continuation token that can be used to retrieve more results.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class ListDataBundlesResponse {
	@JsonProperty("data_bundles")
	@Valid
	private List<DataBundle> dataBundles = null;

	@JsonProperty("next_page_token")
	private String nextPageToken = null;

	public ListDataBundlesResponse dataBundles(List<DataBundle> dataBundles) {
		this.dataBundles = dataBundles;
		return this;
	}

	public ListDataBundlesResponse addDataBundlesItem(DataBundle dataBundlesItem) {
		if (this.dataBundles == null) {
			this.dataBundles = new ArrayList<>();
		}
		this.dataBundles.add(dataBundlesItem);
		return this;
	}

	/**
	 * The list of Data Bundles.
	 * 
	 * @return dataBundles
	 **/
	@ApiModelProperty(value = "The list of Data Bundles.")

	@Valid

	public List<DataBundle> getDataBundles() {
		return dataBundles;
	}

	public void setDataBundles(List<DataBundle> dataBundles) {
		this.dataBundles = dataBundles;
	}

	public ListDataBundlesResponse nextPageToken(String nextPageToken) {
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
		ListDataBundlesResponse listDataBundlesResponse = (ListDataBundlesResponse) o;
		return Objects.equals(this.dataBundles, listDataBundlesResponse.dataBundles)
				&& Objects.equals(this.nextPageToken, listDataBundlesResponse.nextPageToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataBundles, nextPageToken);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ListDataBundlesResponse {\n");

		sb.append("    dataBundles: ").append(toIndentedString(dataBundles)).append("\n");
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
