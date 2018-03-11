package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

import org.irods.jargon.ga4gh.dos.model.Ga4ghDataBundle;

/**
 * Ga4ghListDataBundlesResponse
 */
@javax.annotation.Generated(value = "org.irods.jargon.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghListDataBundlesResponse   {
  @JsonProperty("data_bundles")
  private List<Ga4ghDataBundle> dataBundles = null;

  @JsonProperty("next_page_token")
  private String nextPageToken = null;

  public Ga4ghListDataBundlesResponse dataBundles(List<Ga4ghDataBundle> dataBundles) {
    this.dataBundles = dataBundles;
    return this;
  }

  public Ga4ghListDataBundlesResponse addDataBundlesItem(Ga4ghDataBundle dataBundlesItem) {
    if (this.dataBundles == null) {
      this.dataBundles = new ArrayList<Ga4ghDataBundle>();
    }
    this.dataBundles.add(dataBundlesItem);
    return this;
  }

   /**
   * The list of Data Bundles.
   * @return dataBundles
  **/
  @ApiModelProperty(value = "The list of Data Bundles.")

  @Valid

  public List<Ga4ghDataBundle> getDataBundles() {
    return dataBundles;
  }

  public void setDataBundles(List<Ga4ghDataBundle> dataBundles) {
    this.dataBundles = dataBundles;
  }

  public Ga4ghListDataBundlesResponse nextPageToken(String nextPageToken) {
    this.nextPageToken = nextPageToken;
    return this;
  }

   /**
   * The continuation token, which is used to page through large result sets. Provide this value in a subsequent request to return the next page of results. This field will be empty if there aren't any additional results.
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
    Ga4ghListDataBundlesResponse ga4ghListDataBundlesResponse = (Ga4ghListDataBundlesResponse) o;
    return Objects.equals(this.dataBundles, ga4ghListDataBundlesResponse.dataBundles) &&
        Objects.equals(this.nextPageToken, ga4ghListDataBundlesResponse.nextPageToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataBundles, nextPageToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghListDataBundlesResponse {\n");
    
    sb.append("    dataBundles: ").append(toIndentedString(dataBundles)).append("\n");
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

