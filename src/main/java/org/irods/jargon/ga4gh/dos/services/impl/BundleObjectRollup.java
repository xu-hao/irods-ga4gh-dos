/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services.impl;

import org.irods.jargon.core.checksum.ChecksumValue;

/**
 * Represents an internal rollup of data objects that are part of this bundle
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class BundleObjectRollup {

	private String irodsPath = "";
	private String guid = "";
	private ChecksumValue checksumValue;

	/**
	 * 
	 */
	public BundleObjectRollup() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checksumValue == null) ? 0 : checksumValue.hashCode());
		result = prime * result + ((guid == null) ? 0 : guid.hashCode());
		result = prime * result + ((irodsPath == null) ? 0 : irodsPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BundleObjectRollup other = (BundleObjectRollup) obj;
		if (checksumValue == null) {
			if (other.checksumValue != null)
				return false;
		} else if (!checksumValue.equals(other.checksumValue))
			return false;
		if (guid == null) {
			if (other.guid != null)
				return false;
		} else if (!guid.equals(other.guid))
			return false;
		if (irodsPath == null) {
			if (other.irodsPath != null)
				return false;
		} else if (!irodsPath.equals(other.irodsPath))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BundleObjectRollup [");
		if (irodsPath != null) {
			builder.append("irodsPath=").append(irodsPath).append(", ");
		}
		if (guid != null) {
			builder.append("guid=").append(guid).append(", ");
		}
		if (checksumValue != null) {
			builder.append("checksumValue=").append(checksumValue);
		}
		builder.append("]");
		return builder.toString();
	}

	public String getIrodsPath() {
		return irodsPath;
	}

	public void setIrodsPath(String irodsPath) {
		this.irodsPath = irodsPath;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public ChecksumValue getChecksumValue() {
		return checksumValue;
	}

	public void setChecksumValue(ChecksumValue checksumValue) {
		this.checksumValue = checksumValue;
	}

}
