/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundle.internalmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.irods.jargon.core.pub.domain.AvuData;

/**
 * Represents data about the data bundle in iRODS, can be used to create the
 * GA4GH domain model objects
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class IrodsDataBundle {

	/**
	 * GUID of the data bundle
	 */
	private String bundleUuid = "";

	/**
	 * List of data objects contained in this bundle by their UUIDs
	 */
	private List<String> dataObjectUuids = new ArrayList<String>();

	/**
	 * Date of creation of the iRODS collection
	 */
	private Date createDate;

	/**
	 * Last modified date of the iRODS collection
	 */
	private Date updatedDate;

	/**
	 * Version of the data bundle (currently not supported).
	 */
	private String version = "";

	/**
	 * Hex encoded {@code String} that is the checksum of the bundle checksums
	 */
	private String bundleChecksum = "";

	/**
	 * Description of the data bundle. TODO: do I make this an Avu that is set on
	 * bundle creation?
	 */
	private String description = "";

	/**
	 * Absolute path to the iRODS collection, can serve as an 'alias' in the GA4GH
	 * layer
	 */
	private String irodsAbsolutePath = "";

	/**
	 * Collection of {@link AvuData} with GA4GH bundle internal avus filtered out.
	 * Can be used to produce system and user metadata in the bundle
	 */
	private List<AvuData> avus = new ArrayList<AvuData>();

	public IrodsDataBundle() {
	}

	public String getBundleUuid() {
		return bundleUuid;
	}

	public void setBundleUuid(String bundleUuid) {
		this.bundleUuid = bundleUuid;
	}

	public List<String> getDataObjectUuids() {
		return dataObjectUuids;
	}

	public void setDataObjectUuids(List<String> dataObjectUuids) {
		this.dataObjectUuids = dataObjectUuids;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBundleChecksum() {
		return bundleChecksum;
	}

	public void setBundleChecksum(String bundleChecksum) {
		this.bundleChecksum = bundleChecksum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIrodsAbsolutePath() {
		return irodsAbsolutePath;
	}

	public void setIrodsAbsolutePath(String irodsAbsolutePath) {
		this.irodsAbsolutePath = irodsAbsolutePath;
	}

	public List<AvuData> getAvus() {
		return avus;
	}

	public void setAvus(List<AvuData> avus) {
		this.avus = avus;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("IrodsDataBundle [bundleUuid=").append(bundleUuid).append(", dataObjectUuids=")
				.append(dataObjectUuids != null ? dataObjectUuids.subList(0, Math.min(dataObjectUuids.size(), maxLen))
						: null)
				.append(", createDate=").append(createDate).append(", updatedDate=").append(updatedDate)
				.append(", version=").append(version).append(", bundleChecksum=").append(bundleChecksum)
				.append(", description=").append(description).append(", irodsAbsolutePath=").append(irodsAbsolutePath)
				.append(", avus=").append(avus != null ? avus.subList(0, Math.min(avus.size(), maxLen)) : null)
				.append("]");
		return builder.toString();
	}

}
