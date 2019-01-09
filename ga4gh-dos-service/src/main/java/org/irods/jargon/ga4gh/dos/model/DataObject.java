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
 * DataObject
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

public class DataObject {
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

	@JsonProperty("mime_type")
	private String mimeType = null;

	@JsonProperty("checksums")
	@Valid
	private List<Checksum> checksums = new ArrayList<>();

	@JsonProperty("urls")
	@Valid
	private List<URL> urls = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("aliases")
	@Valid
	private List<String> aliases = null;

	public DataObject id(String id) {
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

	public DataObject name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * A string that can be optionally used to name a Data Object.
	 * 
	 * @return name
	 **/
	@ApiModelProperty(value = "A string that can be optionally used to name a Data Object.")

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataObject size(String size) {
		this.size = size;
		return this;
	}

	/**
	 * The computed size in bytes.
	 * 
	 * @return size
	 **/
	@ApiModelProperty(required = true, value = "The computed size in bytes.")
	@NotNull

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public DataObject created(OffsetDateTime created) {
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

	public DataObject updated(OffsetDateTime updated) {
		this.updated = updated;
		return this;
	}

	/**
	 * Timestamp of update in RFC3339, identical to create timestamp in systems that
	 * do not support updates.
	 * 
	 * @return updated
	 **/
	@ApiModelProperty(value = "Timestamp of update in RFC3339, identical to create timestamp in systems that do not support updates.")

	@Valid

	public OffsetDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(OffsetDateTime updated) {
		this.updated = updated;
	}

	public DataObject version(String version) {
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

	public DataObject mimeType(String mimeType) {
		this.mimeType = mimeType;
		return this;
	}

	/**
	 * A string providing the mime-type of the Data Object. For example,
	 * \"application/json\".
	 * 
	 * @return mimeType
	 **/
	@ApiModelProperty(value = "A string providing the mime-type of the Data Object. For example, \"application/json\".")

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public DataObject checksums(List<Checksum> checksums) {
		this.checksums = checksums;
		return this;
	}

	public DataObject addChecksumsItem(Checksum checksumsItem) {
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

	public DataObject urls(List<URL> urls) {
		this.urls = urls;
		return this;
	}

	public DataObject addUrlsItem(URL urlsItem) {
		if (this.urls == null) {
			this.urls = new ArrayList<>();
		}
		this.urls.add(urlsItem);
		return this;
	}

	/**
	 * The list of URLs that can be used to access the Data Object.
	 * 
	 * @return urls
	 **/
	@ApiModelProperty(value = "The list of URLs that can be used to access the Data Object.")

	@Valid

	public List<URL> getUrls() {
		return urls;
	}

	public void setUrls(List<URL> urls) {
		this.urls = urls;
	}

	public DataObject description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * A human readable description of the contents of the Data Object.
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "A human readable description of the contents of the Data Object.")

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DataObject aliases(List<String> aliases) {
		this.aliases = aliases;
		return this;
	}

	public DataObject addAliasesItem(String aliasesItem) {
		if (this.aliases == null) {
			this.aliases = new ArrayList<>();
		}
		this.aliases.add(aliasesItem);
		return this;
	}

	/**
	 * A list of strings that can be used to find this Data Object. These aliases
	 * can be used to represent the Data Object's location in a directory (e.g.
	 * \"bucket/folder/file.name\") to make Data Objects more discoverable. They
	 * might also be used to represent
	 * 
	 * @return aliases
	 **/
	@ApiModelProperty(value = "A list of strings that can be used to find this Data Object. These aliases can be used to represent the Data Object's location in a directory (e.g. \"bucket/folder/file.name\") to make Data Objects more discoverable. They might also be used to represent")

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
		DataObject dataObject = (DataObject) o;
		return Objects.equals(this.id, dataObject.id) && Objects.equals(this.name, dataObject.name)
				&& Objects.equals(this.size, dataObject.size) && Objects.equals(this.created, dataObject.created)
				&& Objects.equals(this.updated, dataObject.updated) && Objects.equals(this.version, dataObject.version)
				&& Objects.equals(this.mimeType, dataObject.mimeType)
				&& Objects.equals(this.checksums, dataObject.checksums) && Objects.equals(this.urls, dataObject.urls)
				&& Objects.equals(this.description, dataObject.description)
				&& Objects.equals(this.aliases, dataObject.aliases);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, size, created, updated, version, mimeType, checksums, urls, description, aliases);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class DataObject {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    size: ").append(toIndentedString(size)).append("\n");
		sb.append("    created: ").append(toIndentedString(created)).append("\n");
		sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
		sb.append("    checksums: ").append(toIndentedString(checksums)).append("\n");
		sb.append("    urls: ").append(toIndentedString(urls)).append("\n");
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
