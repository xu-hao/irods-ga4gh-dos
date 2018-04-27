package org.irods.jargon.ga4gh.dos.services;

import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.ga4gh.dos.services.metadata.SystemDescriptiveMetadata;

public interface SystemDescriptiveMetadataService {

	String SYSTEM_AVU_UNIT = "irods::ga4ghdos:systemMetadata";

	SystemDescriptiveMetadata initializeSystemDescriptiveMetadata() throws JargonException;

}