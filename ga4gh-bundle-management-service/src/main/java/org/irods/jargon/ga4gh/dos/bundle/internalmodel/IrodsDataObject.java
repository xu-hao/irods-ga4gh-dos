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
	 * File size, in bytes
	 */
	private long size = 0L;

	/**
	 * mime type of underlying file
	 */
	private String mimeType = "";

	/**
	 * encoded checksum value from iRODS
	 */
	private String checksum = "";

	/**
	 * type of checksum hash algo
	 */
	private String checksumType = "";

	/**
	 * {@code List} of {@link IrodsAccessMethod} that enumerates the possible ways
	 * of obtaining the data object bytes
	 */
	private List<IrodsAccessMethod> irodsAccessMethods = new ArrayList<>();

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

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	/**
	 * @return the irodsAccessMethods
	 */
	public List<IrodsAccessMethod> getIrodsAccessMethods() {
		return irodsAccessMethods;
	}

	/**
	 * @param irodsAccessMethods the irodsAccessMethods to set
	 */
	public void setIrodsAccessMethods(List<IrodsAccessMethod> irodsAccessMethods) {
		this.irodsAccessMethods = irodsAccessMethods;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("IrodsDataObject [irodsAccessMethods=")
				.append(irodsAccessMethods != null
						? irodsAccessMethods.subList(0, Math.min(irodsAccessMethods.size(), maxLen))
						: null)
				.append(", absolutePath=").append(absolutePath).append(", fileName=").append(fileName).append(", guid=")
				.append(guid).append(", type=").append(type).append("]");
		return builder.toString();
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	public String getChecksumType() {
		return checksumType;
	}

	public void setChecksumType(String checksumType) {
		this.checksumType = checksumType;
	}

}
