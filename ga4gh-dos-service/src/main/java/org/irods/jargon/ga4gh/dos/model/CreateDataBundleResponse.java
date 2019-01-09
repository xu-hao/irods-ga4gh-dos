package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * CreateDataBundleResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class CreateDataBundleResponse {
	@JsonProperty("data_bundle_id")
	private String dataBundleId = null;

	public CreateDataBundleResponse dataBundleId(String dataBundleId) {
		this.dataBundleId = dataBundleId;
		return this;
	}

	/**
	 * The identifier of the Data Bundle created.
	 * 
	 * @return dataBundleId
	 **/
	@ApiModelProperty(required = true, value = "The identifier of the Data Bundle created.")
	@NotNull

	public String getDataBundleId() {
		return dataBundleId;
	}

	public void setDataBundleId(String dataBundleId) {
		this.dataBundleId = dataBundleId;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CreateDataBundleResponse createDataBundleResponse = (CreateDataBundleResponse) o;
		return Objects.equals(this.dataBundleId, createDataBundleResponse.dataBundleId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataBundleId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CreateDataBundleResponse {\n");

		sb.append("    dataBundleId: ").append(toIndentedString(dataBundleId)).append("\n");
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
