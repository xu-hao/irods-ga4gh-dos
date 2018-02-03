package gov.nih.niehs.ods.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghDataObject;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ga4ghGetDataObjectResponse
 */
@javax.annotation.Generated(value = "gov.nih.niehs.ods.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghGetDataObjectResponse   {
  @JsonProperty("data_object")
  private Ga4ghDataObject dataObject = null;

  public Ga4ghGetDataObjectResponse dataObject(Ga4ghDataObject dataObject) {
    this.dataObject = dataObject;
    return this;
  }

   /**
   * REQUIRED The Data Object that coincides with a specific GetDataObjectRequest.
   * @return dataObject
  **/
  @ApiModelProperty(value = "REQUIRED The Data Object that coincides with a specific GetDataObjectRequest.")

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
    Ga4ghGetDataObjectResponse ga4ghGetDataObjectResponse = (Ga4ghGetDataObjectResponse) o;
    return Objects.equals(this.dataObject, ga4ghGetDataObjectResponse.dataObject);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataObject);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghGetDataObjectResponse {\n");
    
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

