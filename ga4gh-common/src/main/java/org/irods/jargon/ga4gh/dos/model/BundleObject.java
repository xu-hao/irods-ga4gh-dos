package org.irods.jargon.ga4gh.dos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * BundleObject
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-14T11:28:18.659Z")

public class BundleObject {
	@JsonProperty("name")
	private String name = null;

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("drs_uri")
	@Valid
	private List<String> drsUri = null;

	/**
	 * The type of content being referenced. BundleObject of type bundle will need
	 * to be recursed further.
	 */
	public enum TypeEnum {
		OBJECT("object"),

		BUNDLE("bundle");

		private String value;

		TypeEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static TypeEnum fromValue(String text) {
			for (TypeEnum b : TypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("type")
	private TypeEnum type = null;

	public BundleObject name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * A name declared by the Bundle author that must be used when materialising the
	 * associated data object, overriding any name directly associated with the
	 * object itself. This string MUST NOT contain any slashes.
	 * 
	 * @return name
	 **/
	@ApiModelProperty(required = true, value = "A name declared by the Bundle author that must be used when materialising the associated data object, overriding any name directly associated with the object itself. This string MUST NOT contain any slashes.")
	@NotNull

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BundleObject id(String id) {
		this.id = id;
		return this;
	}

	/**
	 * A DRS identifier of a Data Ga4ghObject or a nested Data Bundle.
	 * 
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "A DRS identifier of a Data Ga4ghObject or a nested Data Bundle.")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BundleObject drsUri(List<String> drsUri) {
		this.drsUri = drsUri;
		return this;
	}

	public BundleObject addDrsUriItem(String drsUriItem) {
		if (this.drsUri == null) {
			this.drsUri = new ArrayList<String>();
		}
		this.drsUri.add(drsUriItem);
		return this;
	}

	/**
	 * A list of full DRS identifier URI paths that may be used obtain the Data
	 * Ga4ghObject or Data Bundle. These URIs may be external to this DRS instance.
	 * 
	 * @return drsUri
	 **/
	@ApiModelProperty(example = "\"drs://example.com/ga4gh/drs/v1/objects/{object_id}\"", value = "A list of full DRS identifier URI paths that may be used obtain the Data Ga4ghObject or Data Bundle. These URIs may be external to this DRS instance.")

	public List<String> getDrsUri() {
		return drsUri;
	}

	public void setDrsUri(List<String> drsUri) {
		this.drsUri = drsUri;
	}

	public BundleObject type(TypeEnum type) {
		this.type = type;
		return this;
	}

	/**
	 * The type of content being referenced. BundleObject of type bundle will need
	 * to be recursed further.
	 * 
	 * @return type
	 **/
	@ApiModelProperty(required = true, value = "The type of content being referenced. BundleObject of type bundle will need to be recursed further.")
	@NotNull

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
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
		BundleObject bundleObject = (BundleObject) o;
		return Objects.equals(this.name, bundleObject.name) && Objects.equals(this.id, bundleObject.id)
				&& Objects.equals(this.drsUri, bundleObject.drsUri) && Objects.equals(this.type, bundleObject.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, id, drsUri, type);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BundleObject {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    drsUri: ").append(toIndentedString(drsUri)).append("\n");
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
