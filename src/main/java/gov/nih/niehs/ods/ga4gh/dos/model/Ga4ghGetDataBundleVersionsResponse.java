package gov.nih.niehs.ods.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghDataBundle;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ga4ghGetDataBundleVersionsResponse
 */
@javax.annotation.Generated(value = "gov.nih.niehs.ods.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghGetDataBundleVersionsResponse   {
  @JsonProperty("data_bundles")
  private List<Ga4ghDataBundle> dataBundles = null;

  public Ga4ghGetDataBundleVersionsResponse dataBundles(List<Ga4ghDataBundle> dataBundles) {
    this.dataBundles = dataBundles;
    return this;
  }

  public Ga4ghGetDataBundleVersionsResponse addDataBundlesItem(Ga4ghDataBundle dataBundlesItem) {
    if (this.dataBundles == null) {
      this.dataBundles = new ArrayList<Ga4ghDataBundle>();
    }
    this.dataBundles.add(dataBundlesItem);
    return this;
  }

   /**
   * REQUIRED All versions of the Data Bundles that match the GetDataBundleVersions request.
   * @return dataBundles
  **/
  @ApiModelProperty(value = "REQUIRED All versions of the Data Bundles that match the GetDataBundleVersions request.")

  @Valid

  public List<Ga4ghDataBundle> getDataBundles() {
    return dataBundles;
  }

  public void setDataBundles(List<Ga4ghDataBundle> dataBundles) {
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
    Ga4ghGetDataBundleVersionsResponse ga4ghGetDataBundleVersionsResponse = (Ga4ghGetDataBundleVersionsResponse) o;
    return Objects.equals(this.dataBundles, ga4ghGetDataBundleVersionsResponse.dataBundles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataBundles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghGetDataBundleVersionsResponse {\n");
    
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

