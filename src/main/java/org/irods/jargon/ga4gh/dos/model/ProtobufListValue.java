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

import org.irods.jargon.ga4gh.dos.model.ProtobufValue;

/**
 * &#x60;ListValue&#x60; is a wrapper around a repeated field of values.  The JSON representation for &#x60;ListValue&#x60; is JSON array.
 */
@ApiModel(description = "`ListValue` is a wrapper around a repeated field of values.  The JSON representation for `ListValue` is JSON array.")
@javax.annotation.Generated(value = "org.irods.jargon.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class ProtobufListValue   {
  @JsonProperty("values")
  private List<ProtobufValue> values = null;

  public ProtobufListValue values(List<ProtobufValue> values) {
    this.values = values;
    return this;
  }

  public ProtobufListValue addValuesItem(ProtobufValue valuesItem) {
    if (this.values == null) {
      this.values = new ArrayList<ProtobufValue>();
    }
    this.values.add(valuesItem);
    return this;
  }

   /**
   * Repeated field of dynamically typed values.
   * @return values
  **/
  @ApiModelProperty(value = "Repeated field of dynamically typed values.")

  @Valid

  public List<ProtobufValue> getValues() {
    return values;
  }

  public void setValues(List<ProtobufValue> values) {
    this.values = values;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProtobufListValue protobufListValue = (ProtobufListValue) o;
    return Objects.equals(this.values, protobufListValue.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProtobufListValue {\n");
    
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
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

