package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * GetDataBundleResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class GetDataBundleResponse {
	@JsonProperty("data_bundle")
	private DataBundle dataBundle = null;

	public GetDataBundleResponse dataBundle(DataBundle dataBundle) {
		this.dataBundle = dataBundle;
		return this;
	}

	/**
	 * Get dataBundle
	 * 
	 * @return dataBundle
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public DataBundle getDataBundle() {
		return dataBundle;
	}

	public void setDataBundle(DataBundle dataBundle) {
		this.dataBundle = dataBundle;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetDataBundleResponse getDataBundleResponse = (GetDataBundleResponse) o;
		return Objects.equals(this.dataBundle, getDataBundleResponse.dataBundle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataBundle);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetDataBundleResponse {\n");

		sb.append("    dataBundle: ").append(toIndentedString(dataBundle)).append("\n");
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
