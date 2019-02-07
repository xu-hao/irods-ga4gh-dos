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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class ListBundlesResponse {
	@JsonProperty("bundles")
	@Valid
	private List<Bundle> bundles = null;

	@JsonProperty("next_page_token")
	private String nextPageToken = null;

	public ListBundlesResponse bundles(List<Bundle> bundles) {
		this.bundles = bundles;
		return this;
	}

	public ListBundlesResponse addBundlesItem(Bundle bundlesItem) {
		if (this.bundles == null) {
			this.bundles = new ArrayList<Bundle>();
		}
		this.bundles.add(bundlesItem);
		return this;
	}

	/**
	 * The list of Data Bundles.
	 * 
	 * @return bundles
	 **/
	@ApiModelProperty(value = "The list of Data Bundles.")

	@Valid

	public List<Bundle> getBundles() {
		return bundles;
	}

	public void setBundles(List<Bundle> bundles) {
		this.bundles = bundles;
	}

	public ListBundlesResponse nextPageToken(String nextPageToken) {
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
		ListBundlesResponse listBundlesResponse = (ListBundlesResponse) o;
		return Objects.equals(this.bundles, listBundlesResponse.bundles)
				&& Objects.equals(this.nextPageToken, listBundlesResponse.nextPageToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bundles, nextPageToken);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ListBundlesResponse {\n");

		sb.append("    bundles: ").append(toIndentedString(bundles)).append("\n");
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
