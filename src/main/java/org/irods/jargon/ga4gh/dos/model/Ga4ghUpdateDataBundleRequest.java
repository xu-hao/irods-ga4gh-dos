package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.irods.jargon.ga4gh.dos.model.Ga4ghDataBundle;

/**
 * Ga4ghUpdateDataBundleRequest
 */
@javax.annotation.Generated(value = "org.irods.jargon.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghUpdateDataBundleRequest   {
  @JsonProperty("data_bundle_id")
  private String dataBundleId = null;

  @JsonProperty("data_bundle")
  private Ga4ghDataBundle dataBundle = null;

  public Ga4ghUpdateDataBundleRequest dataBundleId(String dataBundleId) {
    this.dataBundleId = dataBundleId;
    return this;
  }

   /**
   * Get dataBundleId
   * @return dataBundleId
  **/
  @ApiModelProperty(value = "")


  public String getDataBundleId() {
    return dataBundleId;
  }

  public void setDataBundleId(String dataBundleId) {
    this.dataBundleId = dataBundleId;
  }

  public Ga4ghUpdateDataBundleRequest dataBundle(Ga4ghDataBundle dataBundle) {
    this.dataBundle = dataBundle;
    return this;
  }

   /**
   * REQUIRED The new Data Bundle content.
   * @return dataBundle
  **/
  @ApiModelProperty(value = "REQUIRED The new Data Bundle content.")

  @Valid

  public Ga4ghDataBundle getDataBundle() {
    return dataBundle;
  }

  public void setDataBundle(Ga4ghDataBundle dataBundle) {
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
    Ga4ghUpdateDataBundleRequest ga4ghUpdateDataBundleRequest = (Ga4ghUpdateDataBundleRequest) o;
    return Objects.equals(this.dataBundleId, ga4ghUpdateDataBundleRequest.dataBundleId) &&
        Objects.equals(this.dataBundle, ga4ghUpdateDataBundleRequest.dataBundle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataBundleId, dataBundle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghUpdateDataBundleRequest {\n");
    
    sb.append("    dataBundleId: ").append(toIndentedString(dataBundleId)).append("\n");
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

