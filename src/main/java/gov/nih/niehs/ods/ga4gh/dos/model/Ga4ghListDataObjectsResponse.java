package gov.nih.niehs.ods.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghDataObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ga4ghListDataObjectsResponse
 */
@javax.annotation.Generated(value = "gov.nih.niehs.ods.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghListDataObjectsResponse   {
  @JsonProperty("data_objects")
  private List<Ga4ghDataObject> dataObjects = null;

  @JsonProperty("next_page_token")
  private String nextPageToken = null;

  public Ga4ghListDataObjectsResponse dataObjects(List<Ga4ghDataObject> dataObjects) {
    this.dataObjects = dataObjects;
    return this;
  }

  public Ga4ghListDataObjectsResponse addDataObjectsItem(Ga4ghDataObject dataObjectsItem) {
    if (this.dataObjects == null) {
      this.dataObjects = new ArrayList<Ga4ghDataObject>();
    }
    this.dataObjects.add(dataObjectsItem);
    return this;
  }

   /**
   * The list of Data Objects.
   * @return dataObjects
  **/
  @ApiModelProperty(value = "The list of Data Objects.")

  @Valid

  public List<Ga4ghDataObject> getDataObjects() {
    return dataObjects;
  }

  public void setDataObjects(List<Ga4ghDataObject> dataObjects) {
    this.dataObjects = dataObjects;
  }

  public Ga4ghListDataObjectsResponse nextPageToken(String nextPageToken) {
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
    Ga4ghListDataObjectsResponse ga4ghListDataObjectsResponse = (Ga4ghListDataObjectsResponse) o;
    return Objects.equals(this.dataObjects, ga4ghListDataObjectsResponse.dataObjects) &&
        Objects.equals(this.nextPageToken, ga4ghListDataObjectsResponse.nextPageToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataObjects, nextPageToken);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghListDataObjectsResponse {\n");
    
    sb.append("    dataObjects: ").append(toIndentedString(dataObjects)).append("\n");
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

