package org.irods.jargon.ga4gh.dos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Object
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-14T11:28:18.659Z")

public class Object {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("size")
	private Long size = null;

	@JsonProperty("created")
	private OffsetDateTime created = null;

	@JsonProperty("updated")
	private OffsetDateTime updated = null;

	@JsonProperty("version")
	private String version = null;

	@JsonProperty("mime_type")
	private String mimeType = null;

	@JsonProperty("checksums")
	@Valid
	private List<Checksum> checksums = new ArrayList<Checksum>();

	@JsonProperty("access_methods")
	@Valid
	private List<AccessMethod> accessMethods = new ArrayList<AccessMethod>();

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("aliases")
	@Valid
	private List<String> aliases = null;

	public Object id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * An identifier unique to this Data Object.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "An identifier unique to this Data Object.")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * A string that can be used to name a Data Object.
	 * 
	 * @return name
	 **/
	@ApiModelProperty(value = "A string that can be used to name a Data Object.")

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object size(Long size) {
		this.size = size;
		return this;
	}

	/**
	 * The object size in bytes.
	 * 
	 * @return size
	 **/
	@ApiModelProperty(required = true, value = "The object size in bytes.")
	@NotNull

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Object created(OffsetDateTime created) {
		this.created = created;
		return this;
	}

	/**
	 * Timestamp of object creation in RFC3339.
	 * 
	 * @return created
	 **/
	@ApiModelProperty(required = true, value = "Timestamp of object creation in RFC3339.")
	@NotNull

	@Valid

	public OffsetDateTime getCreated() {
		return created;
	}

	public void setCreated(OffsetDateTime created) {
		this.created = created;
	}

	public Object updated(OffsetDateTime updated) {
		this.updated = updated;
		return this;
	}

	/**
	 * Timestamp of Object update in RFC3339, identical to create timestamp in
	 * systems that do not support updates.
	 * 
	 * @return updated
	 **/
	@ApiModelProperty(value = "Timestamp of Object update in RFC3339, identical to create timestamp in systems that do not support updates.")

	@Valid

	public OffsetDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(OffsetDateTime updated) {
		this.updated = updated;
	}

	public Object version(String version) {
		this.version = version;
		return this;
	}

	/**
	 * A string representing a version.
	 * 
	 * @return version
	 **/
	@ApiModelProperty(value = "A string representing a version.")

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Object mimeType(String mimeType) {
		this.mimeType = mimeType;
		return this;
	}

	/**
	 * A string providing the mime-type of the Data Object.
	 * 
	 * @return mimeType
	 **/
	@ApiModelProperty(example = "application/json", value = "A string providing the mime-type of the Data Object.")

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Object checksums(List<Checksum> checksums) {
		this.checksums = checksums;
		return this;
	}

	public Object addChecksumsItem(Checksum checksumsItem) {
		this.checksums.add(checksumsItem);
		return this;
	}

	/**
	 * The checksum of the Data Object. At least one checksum must be provided.
	 * 
	 * @return checksums
	 **/
	@ApiModelProperty(required = true, value = "The checksum of the Data Object. At least one checksum must be provided.")
	@NotNull

	@Valid

	public List<Checksum> getChecksums() {
		return checksums;
	}

	public void setChecksums(List<Checksum> checksums) {
		this.checksums = checksums;
	}

	public Object accessMethods(List<AccessMethod> accessMethods) {
		this.accessMethods = accessMethods;
		return this;
	}

	public Object addAccessMethodsItem(AccessMethod accessMethodsItem) {
		this.accessMethods.add(accessMethodsItem);
		return this;
	}

	/**
	 * The list of access methods that can be used to fetch the Data Object.
	 * 
	 * @return accessMethods
	 **/
	@ApiModelProperty(required = true, value = "The list of access methods that can be used to fetch the Data Object.")
	@NotNull

	@Valid
	@Size(min = 1)
	public List<AccessMethod> getAccessMethods() {
		return accessMethods;
	}

	public void setAccessMethods(List<AccessMethod> accessMethods) {
		this.accessMethods = accessMethods;
	}

	public Object description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * A human readable description of the Data Object.
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "A human readable description of the Data Object.")

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object aliases(List<String> aliases) {
		this.aliases = aliases;
		return this;
	}

	public Object addAliasesItem(String aliasesItem) {
		if (this.aliases == null) {
			this.aliases = new ArrayList<String>();
		}
		this.aliases.add(aliasesItem);
		return this;
	}

	/**
	 * A list of strings that can be used to find other metadata about this Data
	 * Object from external metadata sources. These aliases can be used to represent
	 * the Data Object's secondary accession numbers or external GUIDs.
	 * 
	 * @return aliases
	 **/
	@ApiModelProperty(value = "A list of strings that can be used to find other metadata about this Data Object from external metadata sources. These aliases can be used to represent the Data Object's secondary accession numbers or external GUIDs.")

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
		Object object = (Object) o;
		return Objects.equals(this.id, object.id) && Objects.equals(this.name, object.name)
				&& Objects.equals(this.size, object.size) && Objects.equals(this.created, object.created)
				&& Objects.equals(this.updated, object.updated) && Objects.equals(this.version, object.version)
				&& Objects.equals(this.mimeType, object.mimeType) && Objects.equals(this.checksums, object.checksums)
				&& Objects.equals(this.accessMethods, object.accessMethods)
				&& Objects.equals(this.description, object.description) && Objects.equals(this.aliases, object.aliases);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, size, created, updated, version, mimeType, checksums, accessMethods, description,
				aliases);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Object {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    size: ").append(toIndentedString(size)).append("\n");
		sb.append("    created: ").append(toIndentedString(created)).append("\n");
		sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
		sb.append("    checksums: ").append(toIndentedString(checksums)).append("\n");
		sb.append("    accessMethods: ").append(toIndentedString(accessMethods)).append("\n");
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
