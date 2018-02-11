/**
 * 
 */
package gov.nih.niehs.ods.ga4gh.services.impl;

import org.irods.jargon.core.connection.IRODSAccount;
import org.springframework.stereotype.Component;

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

}
