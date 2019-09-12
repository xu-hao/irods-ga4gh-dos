/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundle.internalmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a data object in a data bundle in iRODS
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class IrodsDataObject {

	/**
	 * Version number (currently not supported)
	 */
	private String version = "";

	/**
	 * iRODS create date
	 */
	private Date createDate;

	/**
	 * iRODS modify date
	 */
	private Date modifyDate;

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
		builder.append("IrodsDataObject [version=").append(version).append(", createDate=").append(createDate)
				.append(", modifyDate=").append(modifyDate).append(", size=").append(size).append(", mimeType=")
				.append(mimeType).append(", checksum=").append(checksum).append(", checksumType=").append(checksumType)
				.append(", irodsAccessMethods=")
				.append(irodsAccessMethods != null
						? irodsAccessMethods.subList(0, Math.min(irodsAccessMethods.size(), maxLen))
						: null)
				.append(", absolutePath=").append(absolutePath).append(", fileName=").append(fileName).append(", guid=")
				.append(guid).append(", getGuid()=").append(getGuid()).append(", getFileName()=").append(getFileName())
				.append(", getType()=").append(", getAbsolutePath()=").append(getAbsolutePath())
				.append(", getIrodsAccessMethods()=")
				.append(getIrodsAccessMethods() != null
						? getIrodsAccessMethods().subList(0, Math.min(getIrodsAccessMethods().size(), maxLen))
						: null)
				.append(", getSize()=").append(getSize()).append(", getMimeType()=").append(getMimeType())
				.append(", getChecksum()=").append(getChecksum()).append(", getChecksumType()=")
				.append(getChecksumType()).append(", getCreateDate()=").append(getCreateDate())
				.append(", getModifyDate()=").append(getModifyDate()).append(", getVersion()=").append(getVersion())
				.append(", getClass()=").append(getClass()).append(", hashCode()=").append(hashCode())
				.append(", toString()=").append(super.toString()).append("]");
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
