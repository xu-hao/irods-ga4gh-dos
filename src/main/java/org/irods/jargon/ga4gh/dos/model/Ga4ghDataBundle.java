package org.irods.jargon.ga4gh.dos.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Ga4ghDataBundle
 */
@javax.annotation.Generated(value = "org.irods.jargon.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class Ga4ghDataBundle {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("data_object_ids")
	private List<String> dataObjectIds = null;

	@JsonProperty("created")
	private DateTime created = null;

	@JsonProperty("updated")
	private DateTime updated = null;

	@JsonProperty("version")
	private String version = null;

	@JsonProperty("checksums")
	private List<Ga4ghChecksum> checksums = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("aliases")
	private List<String> aliases = null;

	@JsonProperty("system_metadata")
	private Map<String, String> systemMetadata = new HashMap<String, String>();

	@JsonProperty("user_metadata")
	private Map<String, String> userMetadata = new HashMap<String, String>();

	public Ga4ghDataBundle id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@ApiModelProperty(value = "")

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ga4ghDataBundle dataObjectIds(List<String> dataObjectIds) {
		this.dataObjectIds = dataObjectIds;
		return this;
	}

	public Ga4ghDataBundle addDataObjectIdsItem(String dataObjectIdsItem) {
		if (this.dataObjectIds == null) {
			this.dataObjectIds = new ArrayList<String>();
		}
		this.dataObjectIds.add(dataObjectIdsItem);
		return this;
	}

	/**
	 * REQUIRED The list of Data Objects that this Data Bundle contains.
	 * 
	 * @return dataObjectIds
	 **/
	@ApiModelProperty(value = "REQUIRED The list of Data Objects that this Data Bundle contains.")

	public List<String> getDataObjectIds() {
		return dataObjectIds;
	}

	public void setDataObjectIds(List<String> dataObjectIds) {
		this.dataObjectIds = dataObjectIds;
	}

	public Ga4ghDataBundle created(DateTime created) {
		this.created = created;
		return this;
	}

	/**
	 * REQUIRED Timestamp of object creation in RFC3339.
	 * 
	 * @return created
	 **/
	@ApiModelProperty(value = "REQUIRED Timestamp of object creation in RFC3339.")

	@Valid

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public Ga4ghDataBundle updated(DateTime updated) {
		this.updated = updated;
		return this;
	}

	/**
	 * REQUIRED Timestamp of update in RFC3339, identical to create timestamp in
	 * systems that do not support updates.
	 * 
	 * @return updated
	 **/
	@ApiModelProperty(value = "REQUIRED Timestamp of update in RFC3339, identical to create timestamp in systems that do not support updates.")

	@Valid

	public DateTime getUpdated() {
		return updated;
	}

	public void setUpdated(DateTime updated) {
		this.updated = updated;
	}

	public Ga4ghDataBundle version(String version) {
		this.version = version;
		return this;
	}

	/**
	 * REQUIRED A string representing a version, some systems may use checksum, a
	 * RFC3339 timestamp, or incrementing version number. For systems that do not
	 * support versioning please use your update timestamp as your version.
	 * 
	 * @return version
	 **/
	@ApiModelProperty(value = "REQUIRED A string representing a version, some systems may use checksum, a RFC3339 timestamp, or incrementing version number. For systems that do not support versioning please use your update timestamp as your version.")

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Ga4ghDataBundle checksums(List<Ga4ghChecksum> checksums) {
		this.checksums = checksums;
		return this;
	}

	public Ga4ghDataBundle addChecksumsItem(Ga4ghChecksum checksumsItem) {
		if (this.checksums == null) {
			this.checksums = new ArrayList<Ga4ghChecksum>();
		}
		this.checksums.add(checksumsItem);
		return this;
	}

	/**
	 * REQUIRED At least one checksum must be provided. The data bundle checksum is
	 * computed over all the checksums of the Data Objects that bundle contains.
	 * 
	 * @return checksums
	 **/
	@ApiModelProperty(value = "REQUIRED At least one checksum must be provided. The data bundle checksum is computed over all the checksums of the Data Objects that bundle contains.")

	@Valid

	public List<Ga4ghChecksum> getChecksums() {
		return checksums;
	}

	public void setChecksums(List<Ga4ghChecksum> checksums) {
		this.checksums = checksums;
	}

	public Ga4ghDataBundle description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * OPTIONAL A human readable description.
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "OPTIONAL A human readable description.")

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Ga4ghDataBundle aliases(List<String> aliases) {
		this.aliases = aliases;
		return this;
	}

	public Ga4ghDataBundle addAliasesItem(String aliasesItem) {
		if (this.aliases == null) {
			this.aliases = new ArrayList<String>();
		}
		this.aliases.add(aliasesItem);
		return this;
	}

	/**
	 * OPTIONAL A list of strings that can be used to identify this Data Bundle.
	 * 
	 * @return aliases
	 **/
	@ApiModelProperty(value = "OPTIONAL A list of strings that can be used to identify this Data Bundle.")

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	/**
	 * OPTIONAL A set of key-value pairs that represent system metadata about the
	 * object.
	 * 
	 * @return systemMetadata
	 **/
	@ApiModelProperty(value = "OPTIONAL A set of key-value pairs that represent system metadata about the object.")

	@Valid

	public Map<String, String> getSystemMetadata() {
		return systemMetadata;
	}

	public void setSystemMetadata(Map<String, String> systemMetadata) {
		this.systemMetadata = systemMetadata;
	}

	public Ga4ghDataBundle userMetadata(Map<String, String> userMetadata) {
		this.userMetadata = userMetadata;
		return this;
	}

	/**
	 * OPTIONAL A set of key-value pairs that represent metadata provided by the
	 * uploader.
	 * 
	 * @return userMetadata
	 **/
	@ApiModelProperty(value = "OPTIONAL A set of key-value pairs that represent metadata provided by the uploader.")

	@Valid

	public Map<String, String> getUserMetadata() {
		return userMetadata;
	}

	public void setUserMetadata(Map<String, String> userMetadata) {
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
		Ga4ghDataBundle ga4ghDataBundle = (Ga4ghDataBundle) o;
		return Objects.equals(this.id, ga4ghDataBundle.id)
				&& Objects.equals(this.dataObjectIds, ga4ghDataBundle.dataObjectIds)
				&& Objects.equals(this.created, ga4ghDataBundle.created)
				&& Objects.equals(this.updated, ga4ghDataBundle.updated)
				&& Objects.equals(this.version, ga4ghDataBundle.version)
				&& Objects.equals(this.checksums, ga4ghDataBundle.checksums)
				&& Objects.equals(this.description, ga4ghDataBundle.description)
				&& Objects.equals(this.aliases, ga4ghDataBundle.aliases)
				&& Objects.equals(this.systemMetadata, ga4ghDataBundle.systemMetadata)
				&& Objects.equals(this.userMetadata, ga4ghDataBundle.userMetadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, dataObjectIds, created, updated, version, checksums, description, aliases,
				systemMetadata, userMetadata);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Ga4ghDataBundle {\n");

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
