package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * UpdateBundleResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class UpdateBundleResponse {
	@JsonProperty("bundle_id")
	private String bundleId = null;

	public UpdateBundleResponse bundleId(String bundleId) {
		this.bundleId = bundleId;
		return this;
	}

	/**
	 * The identifier of the Data Bundle updated.
	 * 
	 * @return bundleId
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the Data Bundle updated.")
	@NotNull

	public String getBundleId() {
		return bundleId;
	}

	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UpdateBundleResponse updateBundleResponse = (UpdateBundleResponse) o;
		return Objects.equals(this.bundleId, updateBundleResponse.bundleId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(bundleId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class UpdateBundleResponse {\n");

		sb.append("    bundleId: ").append(toIndentedString(bundleId)).append("\n");
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
