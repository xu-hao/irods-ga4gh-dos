package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Only return Data Bundles that match all of the request parameters. A
 * page_size and page_token are provided for retrieving a large number of
 * results.
 */
@ApiModel(description = "Only return Data Bundles that match all of the request parameters. A page_size and page_token are provided for retrieving a large number of results.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class ListDataBundlesRequest {
	@JsonProperty("alias")
	private String alias = null;

	@JsonProperty("checksum")
	private String checksum = null;

	@JsonProperty("checksum_type")
	private String checksumType = null;

	@JsonProperty("page_size")
	private Integer pageSize = null;

	@JsonProperty("page_token")
	private String pageToken = null;

	public ListDataBundlesRequest alias(String alias) {
		this.alias = alias;
		return this;
	}

	/**
	 * If provided returns Data Bundles that have any alias that matches the
	 * request.
	 * 
	 * @return alias
	 **/
	@ApiModelProperty(value = "If provided returns Data Bundles that have any alias that matches the request.")

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public ListDataBundlesRequest checksum(String checksum) {
		this.checksum = checksum;
		return this;
	}

	/**
	 * The hexlified checksum that one would like to match on.
	 * 
	 * @return checksum
	 **/
	@ApiModelProperty(value = "The hexlified checksum that one would like to match on.")

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public ListDataBundlesRequest checksumType(String checksumType) {
		this.checksumType = checksumType;
		return this;
	}

	/**
	 * If provided will restrict responses to those that match the provided type.
	 * possible values: md5 # most blob stores provide a checksum using this
	 * multipart-md5 # multipart uploads provide a specialized tag in S3 sha256
	 * sha512
	 * 
	 * @return checksumType
	 **/
	@ApiModelProperty(value = "If provided will restrict responses to those that match the provided type.  possible values: md5                # most blob stores provide a checksum using this multipart-md5      # multipart uploads provide a specialized tag in S3 sha256 sha512")

	public String getChecksumType() {
		return checksumType;
	}

	public void setChecksumType(String checksumType) {
		this.checksumType = checksumType;
	}

	public ListDataBundlesRequest pageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	/**
	 * Specifies the maximum number of results to return in a single page. If
	 * unspecified, a system default will be used.
	 * 
	 * @return pageSize
	 **/
	@ApiModelProperty(value = "Specifies the maximum number of results to return in a single page. If unspecified, a system default will be used.")

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public ListDataBundlesRequest pageToken(String pageToken) {
		this.pageToken = pageToken;
		return this;
	}

	/**
	 * The continuation token, which is used to page through large result sets. To
	 * get the next page of results, set this parameter to the value of
	 * `next_page_token` from the previous response.
	 * 
	 * @return pageToken
	 **/
	@ApiModelProperty(value = "The continuation token, which is used to page through large result sets. To get the next page of results, set this parameter to the value of `next_page_token` from the previous response.")

	public String getPageToken() {
		return pageToken;
	}

	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ListDataBundlesRequest listDataBundlesRequest = (ListDataBundlesRequest) o;
		return Objects.equals(this.alias, listDataBundlesRequest.alias)
				&& Objects.equals(this.checksum, listDataBundlesRequest.checksum)
				&& Objects.equals(this.checksumType, listDataBundlesRequest.checksumType)
				&& Objects.equals(this.pageSize, listDataBundlesRequest.pageSize)
				&& Objects.equals(this.pageToken, listDataBundlesRequest.pageToken);
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, checksum, checksumType, pageSize, pageToken);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ListDataBundlesRequest {\n");

		sb.append("    alias: ").append(toIndentedString(alias)).append("\n");
		sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
		sb.append("    checksumType: ").append(toIndentedString(checksumType)).append("\n");
		sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
		sb.append("    pageToken: ").append(toIndentedString(pageToken)).append("\n");
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
