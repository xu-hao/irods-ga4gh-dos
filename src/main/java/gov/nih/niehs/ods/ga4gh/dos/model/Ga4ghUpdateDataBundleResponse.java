package gov.nih.niehs.ods.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ga4ghUpdateDataBundleResponse
 */
@javax.annotation.Generated(value = "gov.nih.niehs.ods.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghUpdateDataBundleResponse   {
  @JsonProperty("data_bundle_id")
  private String dataBundleId = null;

  public Ga4ghUpdateDataBundleResponse dataBundleId(String dataBundleId) {
    this.dataBundleId = dataBundleId;
    return this;
  }

   /**
   * REQUIRED The identifier of the Data Bundle updated.
   * @return dataBundleId
  **/
  @ApiModelProperty(value = "REQUIRED The identifier of the Data Bundle updated.")


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
    Ga4ghUpdateDataBundleResponse ga4ghUpdateDataBundleResponse = (Ga4ghUpdateDataBundleResponse) o;
    return Objects.equals(this.dataBundleId, ga4ghUpdateDataBundleResponse.dataBundleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataBundleId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghUpdateDataBundleResponse {\n");
    
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

