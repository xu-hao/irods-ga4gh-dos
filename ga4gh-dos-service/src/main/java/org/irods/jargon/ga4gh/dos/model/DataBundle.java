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
 * DataBundle
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class DataBundle {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("data_object_ids")
	@Valid
	private List<String> dataObjectIds = new ArrayList<>();

	@JsonProperty("created")
	private OffsetDateTime created = null;

	@JsonProperty("updated")
	private OffsetDateTime updated = null;

	@JsonProperty("version")
	private String version = null;

	@JsonProperty("checksums")
	@Valid
	private List<Checksum> checksums = new ArrayList<>();

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("aliases")
	@Valid
	private List<String> aliases = null;

	@JsonProperty("system_metadata")
	private SystemMetadata systemMetadata = null;

	@JsonProperty("user_metadata")
	private UserMetadata userMetadata = null;

	public DataBundle id(String id) {
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

	public DataBundle dataObjectIds(List<String> dataObjectIds) {
		this.dataObjectIds = dataObjectIds;
		return this;
	}

	public DataBundle addDataObjectIdsItem(String dataObjectIdsItem) {
		this.dataObjectIds.add(dataObjectIdsItem);
		return this;
	}

	/**
	 * The list of Data Objects that this Data Bundle contains.
	 * 
	 * @return dataObjectIds
	 **/
	@ApiModelProperty(required = true, value = "The list of Data Objects that this Data Bundle contains.")
	@NotNull

	public List<String> getDataObjectIds() {
		return dataObjectIds;
	}

	public void setDataObjectIds(List<String> dataObjectIds) {
		this.dataObjectIds = dataObjectIds;
	}

	public DataBundle created(OffsetDateTime created) {
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

	public DataBundle updated(OffsetDateTime updated) {
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

	public DataBundle version(String version) {
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

	public DataBundle checksums(List<Checksum> checksums) {
		this.checksums = checksums;
		return this;
	}

	public DataBundle addChecksumsItem(Checksum checksumsItem) {
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

	public DataBundle description(String description) {
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

	public DataBundle aliases(List<String> aliases) {
		this.aliases = aliases;
		return this;
	}

	public DataBundle addAliasesItem(String aliasesItem) {
		if (this.aliases == null) {
			this.aliases = new ArrayList<>();
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

	public DataBundle systemMetadata(SystemMetadata systemMetadata) {
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

	public DataBundle userMetadata(UserMetadata userMetadata) {
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
		DataBundle dataBundle = (DataBundle) o;
		return Objects.equals(this.id, dataBundle.id) && Objects.equals(this.dataObjectIds, dataBundle.dataObjectIds)
				&& Objects.equals(this.created, dataBundle.created) && Objects.equals(this.updated, dataBundle.updated)
				&& Objects.equals(this.version, dataBundle.version)
				&& Objects.equals(this.checksums, dataBundle.checksums)
				&& Objects.equals(this.description, dataBundle.description)
				&& Objects.equals(this.aliases, dataBundle.aliases)
				&& Objects.equals(this.systemMetadata, dataBundle.systemMetadata)
				&& Objects.equals(this.userMetadata, dataBundle.userMetadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, dataObjectIds, created, updated, version, checksums, description, aliases,
				systemMetadata, userMetadata);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DataBundle {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    dataObjectIds: ").append(toIndentedString(dataObjectIds)).append("\n");
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
