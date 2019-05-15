package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Useful information about the running service.
 */
@ApiModel(description = "Useful information about the running service.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-14T11:28:18.659Z")

public class ServiceInfo {
	@JsonProperty("version")
	private String version = null;

	@JsonProperty("title")
	private String title = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("contact")
	private Object contact = null;

	@JsonProperty("license")
	private Object license = null;

	public ServiceInfo version(String version) {
		this.version = version;
		return this;
	}

	/**
	 * Service version
	 * 
	 * @return version
	 **/
	@ApiModelProperty(required = true, value = "Service version")
	@NotNull

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ServiceInfo title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Service name
	 * 
	 * @return title
	 **/
	@ApiModelProperty(value = "Service name")

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ServiceInfo description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Service description
	 * 
	 * @return description
	 **/
	@ApiModelProperty(value = "Service description")

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ServiceInfo contact(Object contact) {
		this.contact = contact;
		return this;
	}

	/**
	 * Maintainer contact info
	 * 
	 * @return contact
	 **/
	@ApiModelProperty(value = "Maintainer contact info")

	public Object getContact() {
		return contact;
	}

	public void setContact(Object contact) {
		this.contact = contact;
	}

	public ServiceInfo license(Object license) {
		this.license = license;
		return this;
	}

	/**
	 * License information for the exposed API
	 * 
	 * @return license
	 **/
	@ApiModelProperty(value = "License information for the exposed API")

	public Object getLicense() {
		return license;
	}

	public void setLicense(Object license) {
		this.license = license;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ServiceInfo serviceInfo = (ServiceInfo) o;
		return Objects.equals(this.version, serviceInfo.version) && Objects.equals(this.title, serviceInfo.title)
				&& Objects.equals(this.description, serviceInfo.description)
				&& Objects.equals(this.contact, serviceInfo.contact)
				&& Objects.equals(this.license, serviceInfo.license);
	}

	@Override
	public int hashCode() {
		return Objects.hash(version, title, description, contact, license);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ServiceInfo {\n");

		sb.append("    version: ").append(toIndentedString(version)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
		sb.append("    license: ").append(toIndentedString(license)).append("\n");
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
