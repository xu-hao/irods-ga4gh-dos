/**
 * 
 */
package org.irods.jargon.ga4gh.dos.services.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.services.IdTranslationService;
import org.irods.jargon.ga4gh.dos.services.IdTranslationServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public void setDosConfiguration(DosConfiguration dosConfiguration) {
		super.setDosConfiguration(dosConfiguration);
	}

}
