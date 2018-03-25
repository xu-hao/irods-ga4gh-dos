package org.irods.jargon.ga4gh.dos.services.impl;

import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.JargonException;

/**
 * Service to manage a GUID as an iRODS AVU in a default implementation
 * 
 * @author Mike Conway - NIEHS
 *
 */
public interface GuidService {

	String GUID_ATTRIBUTE = "GUID";
	String GUID_UNIT = "irods:GUID";

	/**
	 * Create a GUID and attach to a data object
	 * 
	 * @param irodsPath
	 *            {@link String} with the path to the data object
	 * 
	 * @return {@link String} with the string-ified representation of the GUID
	 * @throws DataNotFoundException
	 * @throws JargonException
	 */
	String createGuidOnDataObject(String irodsPath) throws DataNotFoundException, JargonException;

}