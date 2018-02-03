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
 * The Data Object one would like to index. One must provide any aliases and URLs to this file when sending the CreateDataObjectRequest. It is up to implementations to validate that the Data Object is available from the provided URLs.
 */
@ApiModel(description = "The Data Object one would like to index. One must provide any aliases and URLs to this file when sending the CreateDataObjectRequest. It is up to implementations to validate that the Data Object is available from the provided URLs.")
@javax.annotation.Generated(value = "gov.nih.niehs.ods.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghCreateDataObjectRequest   {
  @JsonProperty("data_object")
  private Ga4ghDataObject dataObject = null;

  public Ga4ghCreateDataObjectRequest dataObject(Ga4ghDataObject dataObject) {
    this.dataObject = dataObject;
    return this;
  }

   /**
   * REQUIRED The data object to be created. The ID scheme is left up to the implementor but should be unique to the server instance.
   * @return dataObject
  **/
  @ApiModelProperty(value = "REQUIRED The data object to be created. The ID scheme is left up to the implementor but should be unique to the server instance.")

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
    Ga4ghCreateDataObjectRequest ga4ghCreateDataObjectRequest = (Ga4ghCreateDataObjectRequest) o;
    return Objects.equals(this.dataObject, ga4ghCreateDataObjectRequest.dataObject);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataObject);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghCreateDataObjectRequest {\n");
    
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

