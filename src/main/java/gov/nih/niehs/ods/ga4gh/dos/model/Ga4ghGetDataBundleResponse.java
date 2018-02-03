package gov.nih.niehs.ods.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghDataBundle;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ga4ghGetDataBundleResponse
 */
@javax.annotation.Generated(value = "gov.nih.niehs.ods.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghGetDataBundleResponse   {
  @JsonProperty("data_bundle")
  private Ga4ghDataBundle dataBundle = null;

  public Ga4ghGetDataBundleResponse dataBundle(Ga4ghDataBundle dataBundle) {
    this.dataBundle = dataBundle;
    return this;
  }

   /**
   * Get dataBundle
   * @return dataBundle
  **/
  @ApiModelProperty(value = "")

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
    Ga4ghGetDataBundleResponse ga4ghGetDataBundleResponse = (Ga4ghGetDataBundleResponse) o;
    return Objects.equals(this.dataBundle, ga4ghGetDataBundleResponse.dataBundle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataBundle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghGetDataBundleResponse {\n");
    
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

