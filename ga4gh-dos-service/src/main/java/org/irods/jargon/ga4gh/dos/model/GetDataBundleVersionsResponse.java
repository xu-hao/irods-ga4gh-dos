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
 * GetDataBundleVersionsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class GetDataBundleVersionsResponse {
	@JsonProperty("data_bundles")
	@Valid
	private List<DataBundle> dataBundles = new ArrayList<>();

	public GetDataBundleVersionsResponse dataBundles(List<DataBundle> dataBundles) {
		this.dataBundles = dataBundles;
		return this;
	}

	public GetDataBundleVersionsResponse addDataBundlesItem(DataBundle dataBundlesItem) {
		this.dataBundles.add(dataBundlesItem);
		return this;
	}

	/**
	 * All versions of the Data Bundles that match the GetDataBundleVersions
	 * request.
	 * 
	 * @return dataBundles
	 **/
	@ApiModelProperty(required = true, value = "All versions of the Data Bundles that match the GetDataBundleVersions request.")
	@NotNull

	@Valid

	public List<DataBundle> getDataBundles() {
		return dataBundles;
	}

	public void setDataBundles(List<DataBundle> dataBundles) {
		this.dataBundles = dataBundles;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetDataBundleVersionsResponse getDataBundleVersionsResponse = (GetDataBundleVersionsResponse) o;
		return Objects.equals(this.dataBundles, getDataBundleVersionsResponse.dataBundles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataBundles);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetDataBundleVersionsResponse {\n");

		sb.append("    dataBundles: ").append(toIndentedString(dataBundles)).append("\n");
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
