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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class Bundle {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("object_ids")
	@Valid
	private List<String> objectIds = new ArrayList<String>();

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

	@JsonProperty("system_metadata")
	private SystemMetadata systemMetadata = null;

	@JsonProperty("user_metadata")
	private UserMetadata userMetadata = null;

	public Bundle id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * An identifier, unique to this Data Bundle
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "An identifier, unique to this Data Bundle")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Bundle objectIds(List<String> objectIds) {
		this.objectIds = objectIds;
		return this;
	}

	public Bundle addObjectIdsItem(String objectIdsItem) {
		this.objectIds.add(objectIdsItem);
		return this;
	}

	/**
	 * The list of Data Objects that this Data Bundle contains.
	 * 
	 * @return objectIds
	 **/
	@ApiModelProperty(required = true, value = "The list of Data Objects that this Data Bundle contains.")
	@NotNull

	public List<String> getObjectIds() {
		return objectIds;
	}

	public void setObjectIds(List<String> objectIds) {
		this.objectIds = objectIds;
	}

	public Bundle created(OffsetDateTime created) {
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

	public Bundle updated(OffsetDateTime updated) {
		this.updated = updated;
		return this;
	}

	/**
	 * Timestamp of update in RFC3339, identical to create timestamp in systems that
	 * do not support updates.
	 * 
	 * @return updated
	 **/
	@ApiModelProperty(required = true, value = "Timestamp of update in RFC3339, identical to create timestamp in systems that do not support updates.")
	@NotNull

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
	 * A string representing a version, some systems may use checksum, a RFC3339
	 * timestamp, or incrementing version number. For systems that do not support
	 * versioning please use your update timestamp as your version.
	 * 
	 * @return version
	 **/
	@ApiModelProperty(required = true, value = "A string representing a version, some systems may use checksum, a RFC3339 timestamp, or incrementing version number. For systems that do not support versioning please use your update timestamp as your version.")
	@NotNull

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
	 * At least one checksum must be provided. The Data Bundle checksum is computed
	 * over all the checksums of the Data Objects that bundle contains.
	 * 
	 * @return checksums
	 **/
	@ApiModelProperty(required = true, value = "At least one checksum must be provided. The Data Bundle checksum is computed over all the checksums of the Data Objects that bundle contains.")
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
	 * A human readable description.
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "A human readable description.")

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
	 * A list of strings that can be used to identify this Data Bundle.
	 * 
	 * @return aliases
	 **/
	@ApiModelProperty(value = "A list of strings that can be used to identify this Data Bundle.")

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	public Bundle systemMetadata(SystemMetadata systemMetadata) {
		this.systemMetadata = systemMetadata;
		return this;
	}

	/**
	 * Get systemMetadata
	 * 
	 * @return systemMetadata
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public SystemMetadata getSystemMetadata() {
		return systemMetadata;
	}

	public void setSystemMetadata(SystemMetadata systemMetadata) {
		this.systemMetadata = systemMetadata;
	}

	public Bundle userMetadata(UserMetadata userMetadata) {
		this.userMetadata = userMetadata;
		return this;
	}

	/**
	 * Get userMetadata
	 * 
	 * @return userMetadata
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public UserMetadata getUserMetadata() {
		return userMetadata;
	}

	public void setUserMetadata(UserMetadata userMetadata) {
		this.userMetadata = userMetadata;
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
		return Objects.equals(this.id, bundle.id) && Objects.equals(this.objectIds, bundle.objectIds)
				&& Objects.equals(this.created, bundle.created) && Objects.equals(this.updated, bundle.updated)
				&& Objects.equals(this.version, bundle.version) && Objects.equals(this.checksums, bundle.checksums)
				&& Objects.equals(this.description, bundle.description) && Objects.equals(this.aliases, bundle.aliases)
				&& Objects.equals(this.systemMetadata, bundle.systemMetadata)
				&& Objects.equals(this.userMetadata, bundle.userMetadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, objectIds, created, updated, version, checksums, description, aliases, systemMetadata,
				userMetadata);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Bundle {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    objectIds: ").append(toIndentedString(objectIds)).append("\n");
		sb.append("    created: ").append(toIndentedString(created)).append("\n");
		sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    checksums: ").append(toIndentedString(checksums)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    aliases: ").append(toIndentedString(aliases)).append("\n");
		sb.append("    systemMetadata: ").append(toIndentedString(systemMetadata)).append("\n");
		sb.append("    userMetadata: ").append(toIndentedString(userMetadata)).append("\n");
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
