/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundle.internalmodel;

import java.util.ArrayList;
import java.util.List;

import org.irods.jargon.ga4gh.dos.model.BundleObject.TypeEnum;

/**
 * Represents a data object in a data bundle in iRODS
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class IrodsDataObject {

	/**
	 * iRODS full absolute path
	 */
	private String absolutePath = "";

	/**
	 * iRODS file name
	 */
	private String fileName = "";

	/**
	 * DRS GUID stored as an iRODS AVU
	 */
	private String guid = "";

	/**
	 * object|bundle (right now bundles are presented as flat, no nested bundles)
	 */
	private TypeEnum type = TypeEnum.OBJECT;

	/**
	 * Site-specific access urls (site specific, including irods:// and http://
	 * access urls)
	 */
	private List<String> accessUrls = new ArrayList<>();

	public IrodsDataObject() {
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public List<String> getAccessUrls() {
		return accessUrls;
	}

	public void setAccessUrls(List<String> accessUrls) {
		this.accessUrls = accessUrls;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("IrodsDataObject [absolutePath=").append(absolutePath).append(", fileName=").append(fileName)
				.append(", guid=").append(guid).append(", type=").append(type).append(", accessUrls=")
				.append(accessUrls != null ? accessUrls.subList(0, Math.min(accessUrls.size(), maxLen)) : null)
				.append("]");
		return builder.toString();
	}

}
