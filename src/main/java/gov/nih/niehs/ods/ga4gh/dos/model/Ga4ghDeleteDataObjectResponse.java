package gov.nih.niehs.ods.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ga4ghDeleteDataObjectResponse
 */
@javax.annotation.Generated(value = "gov.nih.niehs.ods.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghDeleteDataObjectResponse   {
  @JsonProperty("data_object_id")
  private String dataObjectId = null;

  public Ga4ghDeleteDataObjectResponse dataObjectId(String dataObjectId) {
    this.dataObjectId = dataObjectId;
    return this;
  }

   /**
   * REQUIRED The identifier of the Data Object deleted.
   * @return dataObjectId
  **/
  @ApiModelProperty(value = "REQUIRED The identifier of the Data Object deleted.")


  public String getDataObjectId() {
    return dataObjectId;
  }

  public void setDataObjectId(String dataObjectId) {
    this.dataObjectId = dataObjectId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ga4ghDeleteDataObjectResponse ga4ghDeleteDataObjectResponse = (Ga4ghDeleteDataObjectResponse) o;
    return Objects.equals(this.dataObjectId, ga4ghDeleteDataObjectResponse.dataObjectId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataObjectId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghDeleteDataObjectResponse {\n");
    
    sb.append("    dataObjectId: ").append(toIndentedString(dataObjectId)).append("\n");
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

