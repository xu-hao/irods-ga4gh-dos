/**
 * 
 */
package org.irods.jargon.ga4gh.dos.bundle.internalmodel;

/**
 * Represents the id of an object and related path/type information for
 * differential processing between bundles and objects
 * 
 * @author Mike Conway - NIEHS
 *
 */
public class BundleInfoAndPath {

	/**
	 * GUID of object or bundle
	 */
	private String id = "";
	/**
	 * Corresponding iRODS path
	 */
	private String irodsPath = "";
	/**
	 * Indicates whether the given path is a bundle or object
	 */
	private boolean collection = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIrodsPath() {
		return irodsPath;
	}

	public void setIrodsPath(String irodsPath) {
		this.irodsPath = irodsPath;
	}

	public boolean isCollection() {
		return collection;
	}

	public void setCollection(boolean collection) {
		this.collection = collection;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BundleInfoAndPath [id=").append(id).append(", irodsPath=").append(irodsPath)
				.append(", collection=").append(collection).append("]");
		return builder.toString();
	}

}
