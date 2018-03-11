/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionService;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionServiceFactory;
import org.irods.jargon.ga4gh.dos.services.DataObjectService;
import org.irods.jargon.ga4gh.dos.services.DataObjectServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of a factory for the @link DataTypeService}
 * 
 * @author Mike Conway - NIEHS
 *
 */
@Component("dataObjectServiceFactory")
public class IrodsDataObjectServiceFactory extends DataObjectServiceFactory {

	@Autowired
	DataTypeResolutionServiceFactory dataTypeResolutionServiceFactory;

	@Override
	public DataObjectService instance(IRODSAccount irodsAccount) {
		if (irodsAccount == null) {
			throw new IllegalArgumentException("null irodsAccount");
		}

		DataTypeResolutionService dataTypeResolutionService = dataTypeResolutionServiceFactory
				.instanceDataTypeResolutionService(irodsAccount);

		return new IrodsDataObjectService(this.getIrodsAccessObjectFactory(), irodsAccount, this.getDosConfiguration(),
				dataTypeResolutionService);

	}

	public DataTypeResolutionServiceFactory getDataTypeResolutionServiceFactory() {
		return dataTypeResolutionServiceFactory;
	}

	public void setDataTypeResolutionServiceFactory(DataTypeResolutionServiceFactory dataTypeResolutionServiceFactory) {
		this.dataTypeResolutionServiceFactory = dataTypeResolutionServiceFactory;
	}

}
