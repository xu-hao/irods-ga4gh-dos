package org.irods.jargon.ga4gh.dos.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Ga4ghObject
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-22T19:13:50.266Z")

public class Ga4ghObject {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("self_uri")
	private String selfUri = null;

	@JsonProperty("size")
	private Long size = null;

	@JsonProperty("created_time")
	private OffsetDateTime createdTime = null;

	@JsonProperty("updated_time")
	private OffsetDateTime updatedTime = null;

	@JsonProperty("version")
	private String version = null;

	@JsonProperty("mime_type")
	private String mimeType = null;

	@JsonProperty("checksums")
	@Valid
	private List<Checksum> checksums = new ArrayList<>();

	@JsonProperty("access_methods")
	@Valid
	private List<AccessMethod> accessMethods = null;

	@JsonProperty("contents")
	@Valid
	private List<ContentsObject> contents = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("aliases")
	@Valid
	private List<String> aliases = null;

	public Ga4ghObject id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * An identifier unique to this `Ga4ghObject`.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "An identifier unique to this `Ga4ghObject`.")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ga4ghObject name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * A string that can be used to name an `Ga4ghObject`. This string is made up of
	 * uppercase and lowercase letters, decimal digits, hypen, period, and
	 * underscore [A-Za-z0-9.-_]. See
	 * http://pubs.opengroup.org/onlinepubs/9699919799/basedefs/V1_chap03.html#tag_03_282[portable
	 * filenames].
	 * 
	 * @return name
	 **/
	@ApiModelProperty(value = "A string that can be used to name an `Ga4ghObject`. This string is made up of uppercase and lowercase letters, decimal digits, hypen, period, and underscore [A-Za-z0-9.-_]. See http://pubs.opengroup.org/onlinepubs/9699919799/basedefs/V1_chap03.html#tag_03_282[portable filenames].")

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ga4ghObject selfUri(String selfUri) {
		this.selfUri = selfUri;
		return this;
	}

	/**
	 * A drs:// URI, as defined in the DRS documentation, that tells clients how to
	 * access this object. The intent of this field is to make DRS objects
	 * self-contained, and therefore easier for clients to store and pass around.
	 * 
	 * @return selfUri
	 **/
	@ApiModelProperty(example = "drs://drs.example.org/314159", required = true, value = "A drs:// URI, as defined in the DRS documentation, that tells clients how to access this object. The intent of this field is to make DRS objects self-contained, and therefore easier for clients to store and pass around.")
	@NotNull

	public String getSelfUri() {
		return selfUri;
	}

	public void setSelfUri(String selfUri) {
		this.selfUri = selfUri;
	}

	public Ga4ghObject size(Long size) {
		this.size = size;
		return this;
	}

	/**
	 * For blobs, the blob size in bytes. For bundles, the cumulative size, in
	 * bytes, of items in the `contents` field.
	 * 
	 * @return size
	 **/
	@ApiModelProperty(required = true, value = "For blobs, the blob size in bytes. For bundles, the cumulative size, in bytes, of items in the `contents` field.")
	@NotNull

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Ga4ghObject createdTime(OffsetDateTime createdTime) {
		this.createdTime = createdTime;
		return this;
	}

	/**
	 * Timestamp of content creation in RFC3339. (This is the creation time of the
	 * underlying content, not of the JSON object.)
	 * 
	 * @return createdTime
	 **/
	@ApiModelProperty(required = true, value = "Timestamp of content creation in RFC3339. (This is the creation time of the underlying content, not of the JSON object.) ")
	@NotNull

	@Valid

	public OffsetDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(OffsetDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public Ga4ghObject updatedTime(OffsetDateTime updatedTime) {
		this.updatedTime = updatedTime;
		return this;
	}

	/**
	 * Timestamp of content update in RFC3339, identical to `created_time` in
	 * systems that do not support updates. (This is the update time of the
	 * underlying content, not of the JSON object.)
	 * 
	 * @return updatedTime
	 **/
	@ApiModelProperty(value = "Timestamp of content update in RFC3339, identical to `created_time` in systems that do not support updates. (This is the update time of the underlying content, not of the JSON object.)")

	@Valid

	public OffsetDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(OffsetDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Ga4ghObject version(String version) {
		this.version = version;
		return this;
	}

	/**
	 * A string representing a version. (Some systems may use checksum, a RFC3339
	 * timestamp, or an incrementing version number.)
	 * 
	 * @return version
	 **/
	@ApiModelProperty(value = "A string representing a version. (Some systems may use checksum, a RFC3339 timestamp, or an incrementing version number.)")

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Ga4ghObject mimeType(String mimeType) {
		this.mimeType = mimeType;
		return this;
	}

	/**
	 * A string providing the mime-type of the `Ga4ghObject`.
	 * 
	 * @return mimeType
	 **/
	@ApiModelProperty(example = "application/json", value = "A string providing the mime-type of the `Ga4ghObject`.")

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Ga4ghObject checksums(List<Checksum> checksums) {
		this.checksums = checksums;
		return this;
	}

	public Ga4ghObject addChecksumsItem(Checksum checksumsItem) {
		this.checksums.add(checksumsItem);
		return this;
	}

	/**
	 * The checksum of the `Ga4ghObject`. At least one checksum must be provided.
	 * For blobs, the checksum is computed over the bytes in the blob. For bundles,
	 * the checksum is computed over a sorted concatenation of the checksums of its
	 * top-level contained objects (not recursive, names not included). The list of
	 * checksums is sorted alphabetically (hex-code) before concatenation and a
	 * further checksum is performed on the concatenated checksum value. For
	 * example, if a bundle contains blobs with the following checksums: md5(blob1)
	 * = 72794b6d md5(blob2) = 5e089d29 Then the checksum of the bundle is: md5(
	 * concat( sort( md5(blob1), md5(blob2) ) ) ) = md5( concat( sort( 72794b6d,
	 * 5e089d29 ) ) ) = md5( concat( 5e089d29, 72794b6d ) ) = md5( 5e089d2972794b6d
	 * ) = f7a29a04
	 * 
	 * @return checksums
	 **/
	@ApiModelProperty(required = true, value = "The checksum of the `Ga4ghObject`. At least one checksum must be provided. For blobs, the checksum is computed over the bytes in the blob.  For bundles, the checksum is computed over a sorted concatenation of the checksums of its top-level contained objects (not recursive, names not included). The list of checksums is sorted alphabetically (hex-code) before concatenation and a further checksum is performed on the concatenated checksum value.  For example, if a bundle contains blobs with the following checksums: md5(blob1) = 72794b6d md5(blob2) = 5e089d29  Then the checksum of the bundle is: md5( concat( sort( md5(blob1), md5(blob2) ) ) ) = md5( concat( sort( 72794b6d, 5e089d29 ) ) ) = md5( concat( 5e089d29, 72794b6d ) ) = md5( 5e089d2972794b6d ) = f7a29a04")
	@NotNull

	@Valid
	@Size(min = 1)
	public List<Checksum> getChecksums() {
		return checksums;
	}

	public void setChecksums(List<Checksum> checksums) {
		this.checksums = checksums;
	}

	public Ga4ghObject accessMethods(List<AccessMethod> accessMethods) {
		this.accessMethods = accessMethods;
		return this;
	}

	public Ga4ghObject addAccessMethodsItem(AccessMethod accessMethodsItem) {
		if (this.accessMethods == null) {
			this.accessMethods = new ArrayList<>();
		}
		this.accessMethods.add(accessMethodsItem);
		return this;
	}

	/**
	 * The list of access methods that can be used to fetch the `Ga4ghObject`.
	 * Required for single blobs; optional for bundles.
	 * 
	 * @return accessMethods
	 **/
	@ApiModelProperty(value = "The list of access methods that can be used to fetch the `Ga4ghObject`. Required for single blobs; optional for bundles.")

	@Valid
	@Size(min = 1)
	public List<AccessMethod> getAccessMethods() {
		return accessMethods;
	}

	public void setAccessMethods(List<AccessMethod> accessMethods) {
		this.accessMethods = accessMethods;
	}

	public Ga4ghObject contents(List<ContentsObject> contents) {
		this.contents = contents;
		return this;
	}

	public Ga4ghObject addContentsItem(ContentsObject contentsItem) {
		if (this.contents == null) {
			this.contents = new ArrayList<>();
		}
		this.contents.add(contentsItem);
		return this;
	}

	/**
	 * If not set, this `Ga4ghObject` is a single blob. If set, this `Ga4ghObject`
	 * is a bundle containing the listed `ContentsObject` s (some of which may be
	 * further nested).
	 * 
	 * @return contents
	 **/
	@ApiModelProperty(value = "If not set, this `Ga4ghObject` is a single blob. If set, this `Ga4ghObject` is a bundle containing the listed `ContentsObject` s (some of which may be further nested).")

	@Valid

	public List<ContentsObject> getContents() {
		return contents;
	}

	public void setContents(List<ContentsObject> contents) {
		this.contents = contents;
	}

	public Ga4ghObject description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * A human readable description of the `Ga4ghObject`.
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "A human readable description of the `Ga4ghObject`.")

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Ga4ghObject aliases(List<String> aliases) {
		this.aliases = aliases;
		return this;
	}

	public Ga4ghObject addAliasesItem(String aliasesItem) {
		if (this.aliases == null) {
			this.aliases = new ArrayList<>();
		}
		this.aliases.add(aliasesItem);
		return this;
	}

	/**
	 * A list of strings that can be used to find other metadata about this
	 * `Ga4ghObject` from external metadata sources. These aliases can be used to
	 * represent secondary accession numbers or external GUIDs.
	 * 
	 * @return aliases
	 **/
	@ApiModelProperty(value = "A list of strings that can be used to find other metadata about this `Ga4ghObject` from external metadata sources. These aliases can be used to represent secondary accession numbers or external GUIDs.")

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Ga4ghObject object = (Ga4ghObject) o;
		return Objects.equals(this.id, object.id) && Objects.equals(this.name, object.name)
				&& Objects.equals(this.selfUri, object.selfUri) && Objects.equals(this.size, object.size)
				&& Objects.equals(this.createdTime, object.createdTime)
				&& Objects.equals(this.updatedTime, object.updatedTime) && Objects.equals(this.version, object.version)
				&& Objects.equals(this.mimeType, object.mimeType) && Objects.equals(this.checksums, object.checksums)
				&& Objects.equals(this.accessMethods, object.accessMethods)
				&& Objects.equals(this.contents, object.contents)
				&& Objects.equals(this.description, object.description) && Objects.equals(this.aliases, object.aliases);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, selfUri, size, createdTime, updatedTime, version, mimeType, checksums,
				accessMethods, contents, description, aliases);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Ga4ghObject {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    selfUri: ").append(toIndentedString(selfUri)).append("\n");
		sb.append("    size: ").append(toIndentedString(size)).append("\n");
		sb.append("    createdTime: ").append(toIndentedString(createdTime)).append("\n");
		sb.append("    updatedTime: ").append(toIndentedString(updatedTime)).append("\n");
		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
		sb.append("    checksums: ").append(toIndentedString(checksums)).append("\n");
		sb.append("    accessMethods: ").append(toIndentedString(accessMethods)).append("\n");
		sb.append("    contents: ").append(toIndentedString(contents)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    aliases: ").append(toIndentedString(aliases)).append("\n");
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
