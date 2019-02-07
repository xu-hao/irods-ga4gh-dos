package org.irods.jargon.ga4gh.dos.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Placeholder for the Info Object
 */
@ApiModel(description = "Placeholder for the Info Object")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

public class ServiceInfoResponse {
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

	public ServiceInfoResponse version(String version) {
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

	public ServiceInfoResponse title(String title) {
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

	public ServiceInfoResponse description(String description) {
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

	public ServiceInfoResponse contact(Object contact) {
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

	public ServiceInfoResponse license(Object license) {
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
		ServiceInfoResponse serviceInfoResponse = (ServiceInfoResponse) o;
		return Objects.equals(this.version, serviceInfoResponse.version)
				&& Objects.equals(this.title, serviceInfoResponse.title)
				&& Objects.equals(this.description, serviceInfoResponse.description)
				&& Objects.equals(this.contact, serviceInfoResponse.contact)
				&& Objects.equals(this.license, serviceInfoResponse.license);
	}

	@Override
	public int hashCode() {
		return Objects.hash(version, title, description, contact, license);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ServiceInfoResponse {\n");

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
