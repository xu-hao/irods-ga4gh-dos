package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.irods.jargon.ga4gh.dos.model.ProtobufListValue;
import org.irods.jargon.ga4gh.dos.model.ProtobufNullValue;
import org.irods.jargon.ga4gh.dos.model.ProtobufStruct;

/**
 * &#x60;Value&#x60; represents a dynamically typed value which can be either null, a number, a string, a boolean, a recursive struct value, or a list of values. A producer of value is expected to set one of that variants, absence of any variant indicates an error.  The JSON representation for &#x60;Value&#x60; is JSON value.
 */
@ApiModel(description = "`Value` represents a dynamically typed value which can be either null, a number, a string, a boolean, a recursive struct value, or a list of values. A producer of value is expected to set one of that variants, absence of any variant indicates an error.  The JSON representation for `Value` is JSON value.")
@javax.annotation.Generated(value = "org.irods.jargon.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class ProtobufValue   {
  @JsonProperty("null_value")
  private ProtobufNullValue nullValue = null;

  @JsonProperty("number_value")
  private Double numberValue = null;

  @JsonProperty("string_value")
  private String stringValue = null;

  @JsonProperty("bool_value")
  private Boolean boolValue = null;

  @JsonProperty("struct_value")
  private ProtobufStruct structValue = null;

  @JsonProperty("list_value")
  private ProtobufListValue listValue = null;

  public ProtobufValue nullValue(ProtobufNullValue nullValue) {
    this.nullValue = nullValue;
    return this;
  }

   /**
   * Represents a null value.
   * @return nullValue
  **/
  @ApiModelProperty(value = "Represents a null value.")

  @Valid

  public ProtobufNullValue getNullValue() {
    return nullValue;
  }

  public void setNullValue(ProtobufNullValue nullValue) {
    this.nullValue = nullValue;
  }

  public ProtobufValue numberValue(Double numberValue) {
    this.numberValue = numberValue;
    return this;
  }

   /**
   * Represents a double value.
   * @return numberValue
  **/
  @ApiModelProperty(value = "Represents a double value.")


  public Double getNumberValue() {
    return numberValue;
  }

  public void setNumberValue(Double numberValue) {
    this.numberValue = numberValue;
  }

  public ProtobufValue stringValue(String stringValue) {
    this.stringValue = stringValue;
    return this;
  }

   /**
   * Represents a string value.
   * @return stringValue
  **/
  @ApiModelProperty(value = "Represents a string value.")


  public String getStringValue() {
    return stringValue;
  }

  public void setStringValue(String stringValue) {
    this.stringValue = stringValue;
  }

  public ProtobufValue boolValue(Boolean boolValue) {
    this.boolValue = boolValue;
    return this;
  }

   /**
   * Represents a boolean value.
   * @return boolValue
  **/
  @ApiModelProperty(value = "Represents a boolean value.")


  public Boolean getBoolValue() {
    return boolValue;
  }

  public void setBoolValue(Boolean boolValue) {
    this.boolValue = boolValue;
  }

  public ProtobufValue structValue(ProtobufStruct structValue) {
    this.structValue = structValue;
    return this;
  }

   /**
   * Represents a structured value.
   * @return structValue
  **/
  @ApiModelProperty(value = "Represents a structured value.")

  @Valid

  public ProtobufStruct getStructValue() {
    return structValue;
  }

  public void setStructValue(ProtobufStruct structValue) {
    this.structValue = structValue;
  }

  public ProtobufValue listValue(ProtobufListValue listValue) {
    this.listValue = listValue;
    return this;
  }

   /**
   * Represents a repeated `Value`.
   * @return listValue
  **/
  @ApiModelProperty(value = "Represents a repeated `Value`.")

  @Valid

  public ProtobufListValue getListValue() {
    return listValue;
  }

  public void setListValue(ProtobufListValue listValue) {
    this.listValue = listValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProtobufValue protobufValue = (ProtobufValue) o;
    return Objects.equals(this.nullValue, protobufValue.nullValue) &&
        Objects.equals(this.numberValue, protobufValue.numberValue) &&
        Objects.equals(this.stringValue, protobufValue.stringValue) &&
        Objects.equals(this.boolValue, protobufValue.boolValue) &&
        Objects.equals(this.structValue, protobufValue.structValue) &&
        Objects.equals(this.listValue, protobufValue.listValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nullValue, numberValue, stringValue, boolValue, structValue, listValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProtobufValue {\n");
    
    sb.append("    nullValue: ").append(toIndentedString(nullValue)).append("\n");
    sb.append("    numberValue: ").append(toIndentedString(numberValue)).append("\n");
    sb.append("    stringValue: ").append(toIndentedString(stringValue)).append("\n");
    sb.append("    boolValue: ").append(toIndentedString(boolValue)).append("\n");
    sb.append("    structValue: ").append(toIndentedString(structValue)).append("\n");
    sb.append("    listValue: ").append(toIndentedString(listValue)).append("\n");
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

