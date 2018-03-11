package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ga4ghChecksum
 */
@javax.annotation.Generated(value = "org.irods.jargon.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghChecksum   {
  @JsonProperty("checksum")
  private String checksum = null;

  @JsonProperty("type")
  private String type = null;

  public Ga4ghChecksum checksum(String checksum) {
    this.checksum = checksum;
    return this;
  }

   /**
   * REQUIRED The hex-string encoded checksum for the Data.
   * @return checksum
  **/
  @ApiModelProperty(value = "REQUIRED The hex-string encoded checksum for the Data.")


  public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public Ga4ghChecksum type(String type) {
    this.type = type;
    return this;
  }

   /**
   * OPTIONAL The digest method used to create the checksum. If left unspecified md5 will be assumed.  possible values: md5                # most blob stores provide a checksum using this multipart-md5      # multipart uploads provide a specialized tag in S3 sha256 sha512
   * @return type
  **/
  @ApiModelProperty(value = "OPTIONAL The digest method used to create the checksum. If left unspecified md5 will be assumed.  possible values: md5                # most blob stores provide a checksum using this multipart-md5      # multipart uploads provide a specialized tag in S3 sha256 sha512")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ga4ghChecksum ga4ghChecksum = (Ga4ghChecksum) o;
    return Objects.equals(this.checksum, ga4ghChecksum.checksum) &&
        Objects.equals(this.type, ga4ghChecksum.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(checksum, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ga4ghChecksum {\n");
    
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

