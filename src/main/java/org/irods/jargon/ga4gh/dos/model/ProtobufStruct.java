package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.*;

import org.irods.jargon.ga4gh.dos.model.ProtobufValue;

/**
 * &#x60;Struct&#x60; represents a structured data value, consisting of fields which map to dynamically typed values. In some languages, &#x60;Struct&#x60; might be supported by a native representation. For example, in scripting languages like JS a struct is represented as an object. The details of that representation are described together with the proto support for the language.  The JSON representation for &#x60;Struct&#x60; is JSON object.
 */
@ApiModel(description = "`Struct` represents a structured data value, consisting of fields which map to dynamically typed values. In some languages, `Struct` might be supported by a native representation. For example, in scripting languages like JS a struct is represented as an object. The details of that representation are described together with the proto support for the language.  The JSON representation for `Struct` is JSON object.")
@javax.annotation.Generated(value = "org.irods.jargon.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class ProtobufStruct   {
  @JsonProperty("fields")
  private Map<String, ProtobufValue> fields = null;

  public ProtobufStruct fields(Map<String, ProtobufValue> fields) {
    this.fields = fields;
    return this;
  }

  public ProtobufStruct putFieldsItem(String key, ProtobufValue fieldsItem) {
    if (this.fields == null) {
      this.fields = new HashMap<String, ProtobufValue>();
    }
    this.fields.put(key, fieldsItem);
    return this;
  }

   /**
   * Unordered map of dynamically typed values.
   * @return fields
  **/
  @ApiModelProperty(value = "Unordered map of dynamically typed values.")

  @Valid

  public Map<String, ProtobufValue> getFields() {
    return fields;
  }

  public void setFields(Map<String, ProtobufValue> fields) {
    this.fields = fields;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProtobufStruct protobufStruct = (ProtobufStruct) o;
    return Objects.equals(this.fields, protobufStruct.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fields);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProtobufStruct {\n");
    
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
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

