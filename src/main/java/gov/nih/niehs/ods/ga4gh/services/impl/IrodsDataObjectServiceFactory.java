/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionService;
import org.irods.jargon.extensions.datatyper.DataTypeResolutionServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import gov.nih.niehs.ods.ga4gh.services.DataObjectService;
import gov.nih.niehs.ods.ga4gh.services.DataObjectServiceFactory;

/**
 * Implementation of a factory for the @link DataTypeService}
 * 
 * @author Mike Conway - NIEHS
 *
 */
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
