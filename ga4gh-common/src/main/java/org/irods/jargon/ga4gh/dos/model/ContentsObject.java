package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.irods.jargon.ga4gh.dos.model.ContentsObject;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ContentsObject
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-22T19:13:50.266Z")

public class ContentsObject   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("drs_uri")
  @Valid
  private List<String> drsUri = null;

  @JsonProperty("contents")
  @Valid
  private List<ContentsObject> contents = null;

  public ContentsObject name(String name) {
    this.name = name;
    return this;
  }

  /**
   * A name declared by the bundle author that must be used when materialising this object, overriding any name directly associated with the object itself. The name must be unique with the containing bundle.  This string is made up of uppercase and lowercase letters, decimal digits, hypen, period, and underscore [A-Za-z0-9.-_]. See http://pubs.opengroup.org/onlinepubs/9699919799/basedefs/V1_chap03.html#tag_03_282[portable filenames].
   * @return name
  **/
  @ApiModelProperty(required = true, value = "A name declared by the bundle author that must be used when materialising this object, overriding any name directly associated with the object itself. The name must be unique with the containing bundle.  This string is made up of uppercase and lowercase letters, decimal digits, hypen, period, and underscore [A-Za-z0-9.-_]. See http://pubs.opengroup.org/onlinepubs/9699919799/basedefs/V1_chap03.html#tag_03_282[portable filenames].")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ContentsObject id(String id) {
    this.id = id;
    return this;
  }

  /**
   * A DRS identifier of an `Ga4ghObject` (either a single blob or a nested bundle). If this ContentsObject is an object within a nested bundle, then the id is optional. Otherwise, the id is required.
   * @return id
  **/
  @ApiModelProperty(value = "A DRS identifier of an `Ga4ghObject` (either a single blob or a nested bundle). If this ContentsObject is an object within a nested bundle, then the id is optional. Otherwise, the id is required.")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ContentsObject drsUri(List<String> drsUri) {
    this.drsUri = drsUri;
    return this;
  }

  public ContentsObject addDrsUriItem(String drsUriItem) {
    if (this.drsUri == null) {
      this.drsUri = new ArrayList<>();
    }
    this.drsUri.add(drsUriItem);
    return this;
  }

  /**
   * A list of full DRS identifier URI paths that may be used to obtain the object. These URIs may be external to this DRS instance.
   * @return drsUri
  **/
  @ApiModelProperty(example = "\"drs://example.com/ga4gh/drs/v1/objects/{object_id}\"", value = "A list of full DRS identifier URI paths that may be used to obtain the object. These URIs may be external to this DRS instance.")


  public List<String> getDrsUri() {
    return drsUri;
  }

  public void setDrsUri(List<String> drsUri) {
    this.drsUri = drsUri;
  }

  public ContentsObject contents(List<ContentsObject> contents) {
    this.contents = contents;
    return this;
  }

  public ContentsObject addContentsItem(ContentsObject contentsItem) {
    if (this.contents == null) {
      this.contents = new ArrayList<>();
    }
    this.contents.add(contentsItem);
    return this;
  }

  /**
   * If this ContentsObject describes a nested bundle and the caller specified \"?expand=true\" on the request, then this contents array must be present and describe the objects within the nested bundle.
   * @return contents
  **/
  @ApiModelProperty(value = "If this ContentsObject describes a nested bundle and the caller specified \"?expand=true\" on the request, then this contents array must be present and describe the objects within the nested bundle.")

  @Valid

  public List<ContentsObject> getContents() {
    return contents;
  }

  public void setContents(List<ContentsObject> contents) {
    this.contents = contents;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContentsObject contentsObject = (ContentsObject) o;
    return Objects.equals(this.name, contentsObject.name) &&
        Objects.equals(this.id, contentsObject.id) &&
        Objects.equals(this.drsUri, contentsObject.drsUri) &&
        Objects.equals(this.contents, contentsObject.contents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, id, drsUri, contents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContentsObject {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    drsUri: ").append(toIndentedString(drsUri)).append("\n");
    sb.append("    contents: ").append(toIndentedString(contents)).append("\n");
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

