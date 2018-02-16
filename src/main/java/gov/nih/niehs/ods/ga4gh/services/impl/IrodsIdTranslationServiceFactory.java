/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface;
import gov.nih.niehs.ods.ga4gh.services.IdTranslationService;
import gov.nih.niehs.ods.ga4gh.services.IdTranslationServiceFactory;

/**
 * @author Mike Conway - NIEHS
 *
 */
@Component
public class IrodsIdTranslationServiceFactory extends IdTranslationServiceFactory {

	@Override
	public IdTranslationService instance(IRODSAccount irodsAccount) {
		if (irodsAccount == null) {
			throw new IllegalArgumentException("null irodsAccount");
		}
		return new IrodsIdTranslationService(this.getIrodsAccessObjectFactory(), irodsAccount,
				this.getDosConfiguration());
	}

	@Autowired
	@Override
	public void setIrodsAccessObjectFactory(IRODSAccessObjectFactory irodsAccessObjectFactory) {
		super.setIrodsAccessObjectFactory(irodsAccessObjectFactory);
	}

	@Autowired
	@Override
	public void setDosConfiguration(DosConfigInterface dosConfiguration) {
		super.setDosConfiguration(dosConfiguration);
	}

}
