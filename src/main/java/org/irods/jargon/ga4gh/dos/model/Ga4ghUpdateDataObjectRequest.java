package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.irods.jargon.ga4gh.dos.model.Ga4ghDataObject;

/**
 * Ga4ghUpdateDataObjectRequest
 */
@javax.annotation.Generated(value = "org.irods.jargon.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghUpdateDataObjectRequest   {
  @JsonProperty("data_object_id")
  private String dataObjectId = null;

  @JsonProperty("data_object")
  private Ga4ghDataObject dataObject = null;

  public Ga4ghUpdateDataObjectRequest dataObjectId(String dataObjectId) {
    this.dataObjectId = dataObjectId;
    return this;
  }

   /**
   * REQUIRED The identifier of the Data Object to be updated.
   * @return dataObjectId
  **/
  @ApiModelProperty(value = "REQUIRED The identifier of the Data Object to be updated.")


  public String getDataObjectId() {
    return dataObjectId;
  }

  public void setDataObjectId(String dataObjectId) {
    this.dataObjectId = dataObjectId;
  }

  public Ga4ghUpdateDataObjectRequest dataObject(Ga4ghDataObject dataObject) {
    this.dataObject = dataObject;
    return this;
  }

   /**
   * REQUIRED The new Data Object for this identifier.
   * @return dataObject
  **/
  @ApiModelProperty(value = "REQUIRED The new Data Object for this identifier.")

  @Valid

  public Ga4ghDataObject getDataObject() {
    return dataObject;
  }

  public void setDataObject(Ga4ghDataObject dataObject) {
    this.dataObject = dataObject;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ga4ghUpdateDataObjectRequest ga4ghUpdateDataObjectRequest = (Ga4ghUpdateDataObjectRequest) o;
    return Objects.equals(this.dataObjectId, ga4ghUpdateDataObjectRequest.dataObjectId) &&
        Objects.equals(this.dataObject, ga4ghUpdateDataObjectRequest.dataObject);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataObjectId, dataObject);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghUpdateDataObjectRequest {\n");
    
    sb.append("    dataObjectId: ").append(toIndentedString(dataObjectId)).append("\n");
    sb.append("    dataObject: ").append(toIndentedString(dataObject)).append("\n");
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

