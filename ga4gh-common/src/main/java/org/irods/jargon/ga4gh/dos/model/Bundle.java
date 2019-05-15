package org.irods.jargon.ga4gh.dos.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Bundle
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-14T11:28:18.659Z")

public class Bundle {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("size")
	private String size = null;

	@JsonProperty("created")
	private OffsetDateTime created = null;

	@JsonProperty("updated")
	private OffsetDateTime updated = null;

	@JsonProperty("version")
	private String version = null;

	@JsonProperty("checksums")
	@Valid
	private List<Checksum> checksums = new ArrayList<Checksum>();

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("aliases")
	@Valid
	private List<String> aliases = null;

	@JsonProperty("contents")
	@Valid
	private List<BundleObject> contents = new ArrayList<BundleObject>();

	public Bundle id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * An identifier unique to this Data Bundle.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "An identifier unique to this Data Bundle.")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Bundle name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * A string that can be used to name a Data Bundle.
	 * 
	 * @return name
	 **/
	@ApiModelProperty(value = "A string that can be used to name a Data Bundle.")

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bundle size(String size) {
		this.size = size;
		return this;
	}

	/**
	 * The cumulative size, in bytes, of all Data Objects and Bundles listed in the
	 * `contents` field.
	 * 
	 * @return size
	 **/
	@ApiModelProperty(required = true, value = "The cumulative size, in bytes, of all Data Objects and Bundles listed in the `contents` field.")
	@NotNull

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Bundle created(OffsetDateTime created) {
		this.created = created;
		return this;
	}

	/**
	 * Timestamp of Bundle creation in RFC3339.
	 * 
	 * @return created
	 **/
	@ApiModelProperty(required = true, value = "Timestamp of Bundle creation in RFC3339.")
	@NotNull

	@Valid

	public OffsetDateTime getCreated() {
		return created;
	}

	public void setCreated(OffsetDateTime created) {
		this.created = created;
	}

	public Bundle updated(OffsetDateTime updated) {
		this.updated = updated;
		return this;
	}

	/**
	 * Timestamp of Bundle update in RFC3339, identical to create timestamp in
	 * systems that do not support updates.
	 * 
	 * @return updated
	 **/
	@ApiModelProperty(value = "Timestamp of Bundle update in RFC3339, identical to create timestamp in systems that do not support updates.")

	@Valid

	public OffsetDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(OffsetDateTime updated) {
		this.updated = updated;
	}

	public Bundle version(String version) {
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

	public Bundle checksums(List<Checksum> checksums) {
		this.checksums = checksums;
		return this;
	}

	public Bundle addChecksumsItem(Checksum checksumsItem) {
		this.checksums.add(checksumsItem);
		return this;
	}

	/**
	 * The checksum of the Data Bundle. At least one checksum must be provided. The
	 * Data Bundle checksum is computed over a sorted concatenation of all the
	 * checksums (names not included) within the top-level 'contents' of the Bundle
	 * (not recursive). The list of Data Object or Bundle checksums are sorted
	 * alphabetically (hex-code) before concatenation and a further checksum is
	 * performed on the concatenated checksum value. Example below: Data Ojects:
	 * md5(DO1) = 72794b6d30bc86d92e40a1aa65c880b8 md5(DO2) =
	 * 5e089d29a18954e68a78ee6a3c6edabd Data Bundle: DB1 = md5( concat( sort(
	 * md5(DO1), md5(DO2) ) ) ) = md5( concat( sort(
	 * 72794b6d30bc86d92e40a1aa65c880b8, 5e089d29a18954e68a78ee6a3c6edabd ) ) ) =
	 * md5( concat( 5e089d29a18954e68a78ee6a3c6edabd,
	 * 72794b6d30bc86d92e40a1aa65c880b8 ) ) = md5(
	 * 5e089d29a18954e68a78ee6a3c6edabd72794b6d30bc86d92e40a1aa65c880b8 ) =
	 * f7a29a0422e7d870b10839ad6c985079
	 * 
	 * @return checksums
	 **/
	@ApiModelProperty(required = true, value = "The checksum of the Data Bundle. At least one checksum must be provided.  The Data Bundle checksum is computed over a sorted concatenation of all the checksums (names not included) within the top-level 'contents' of the Bundle (not recursive). The list of Data Object or Bundle checksums are sorted alphabetically (hex-code) before concatenation and a further checksum is performed on the concatenated checksum value. Example below: Data Ojects:   md5(DO1) = 72794b6d30bc86d92e40a1aa65c880b8   md5(DO2) = 5e089d29a18954e68a78ee6a3c6edabd Data Bundle: DB1 = md5( concat( sort( md5(DO1), md5(DO2) ) ) )     = md5( concat( sort( 72794b6d30bc86d92e40a1aa65c880b8, 5e089d29a18954e68a78ee6a3c6edabd ) ) )     = md5( concat( 5e089d29a18954e68a78ee6a3c6edabd, 72794b6d30bc86d92e40a1aa65c880b8 ) )     = md5( 5e089d29a18954e68a78ee6a3c6edabd72794b6d30bc86d92e40a1aa65c880b8 )     = f7a29a0422e7d870b10839ad6c985079")
	@NotNull

	@Valid

	public List<Checksum> getChecksums() {
		return checksums;
	}

	public void setChecksums(List<Checksum> checksums) {
		this.checksums = checksums;
	}

	public Bundle description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * A human readable description of the Data Bundle.
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "A human readable description of the Data Bundle.")

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bundle aliases(List<String> aliases) {
		this.aliases = aliases;
		return this;
	}

	public Bundle addAliasesItem(String aliasesItem) {
		if (this.aliases == null) {
			this.aliases = new ArrayList<String>();
		}
		this.aliases.add(aliasesItem);
		return this;
	}

	/**
	 * A list of strings that can be used to find other metadata about this Data
	 * Bundle from external metadata sources. These aliases can be used to represent
	 * the Data Bundle's secondary accession numbers or external GUIDs.
	 * 
	 * @return aliases
	 **/
	@ApiModelProperty(value = "A list of strings that can be used to find other metadata about this Data Bundle from external metadata sources. These aliases can be used to represent the Data Bundle's secondary accession numbers or external GUIDs.")

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	public Bundle contents(List<BundleObject> contents) {
		this.contents = contents;
		return this;
	}

	public Bundle addContentsItem(BundleObject contentsItem) {
		this.contents.add(contentsItem);
		return this;
	}

	/**
	 * The list of Data Objects and Data Bundles contained by this Data Bundle.
	 * 
	 * @return contents
	 **/
	@ApiModelProperty(required = true, value = "The list of Data Objects and Data Bundles contained by this Data Bundle.")
	@NotNull

	@Valid

	public List<BundleObject> getContents() {
		return contents;
	}

	public void setContents(List<BundleObject> contents) {
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
		Bundle bundle = (Bundle) o;
		return Objects.equals(this.id, bundle.id) && Objects.equals(this.name, bundle.name)
				&& Objects.equals(this.size, bundle.size) && Objects.equals(this.created, bundle.created)
				&& Objects.equals(this.updated, bundle.updated) && Objects.equals(this.version, bundle.version)
				&& Objects.equals(this.checksums, bundle.checksums)
				&& Objects.equals(this.description, bundle.description) && Objects.equals(this.aliases, bundle.aliases)
				&& Objects.equals(this.contents, bundle.contents);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, size, created, updated, version, checksums, description, aliases, contents);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Bundle {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    size: ").append(toIndentedString(size)).append("\n");
		sb.append("    created: ").append(toIndentedString(created)).append("\n");
		sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    checksums: ").append(toIndentedString(checksums)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    aliases: ").append(toIndentedString(aliases)).append("\n");
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
