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
 * Ga4ghGetDataObjectVersionsResponse
 */
@javax.annotation.Generated(value = "gov.nih.niehs.ods.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghGetDataObjectVersionsResponse   {
  @JsonProperty("data_objects")
  private List<Ga4ghDataObject> dataObjects = null;

  public Ga4ghGetDataObjectVersionsResponse dataObjects(List<Ga4ghDataObject> dataObjects) {
    this.dataObjects = dataObjects;
    return this;
  }

  public Ga4ghGetDataObjectVersionsResponse addDataObjectsItem(Ga4ghDataObject dataObjectsItem) {
    if (this.dataObjects == null) {
      this.dataObjects = new ArrayList<Ga4ghDataObject>();
    }
    this.dataObjects.add(dataObjectsItem);
    return this;
  }

   /**
   * REQUIRED All versions of the Data Objects that match the GetDataObjectVersions request.
   * @return dataObjects
  **/
  @ApiModelProperty(value = "REQUIRED All versions of the Data Objects that match the GetDataObjectVersions request.")

  @Valid

  public List<Ga4ghDataObject> getDataObjects() {
    return dataObjects;
  }

  public void setDataObjects(List<Ga4ghDataObject> dataObjects) {
    this.dataObjects = dataObjects;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ga4ghGetDataObjectVersionsResponse ga4ghGetDataObjectVersionsResponse = (Ga4ghGetDataObjectVersionsResponse) o;
    return Objects.equals(this.dataObjects, ga4ghGetDataObjectVersionsResponse.dataObjects);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataObjects);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghGetDataObjectVersionsResponse {\n");
    
    sb.append("    dataObjects: ").append(toIndentedString(dataObjects)).append("\n");
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

