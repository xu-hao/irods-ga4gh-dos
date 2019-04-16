package org.irods.jargon.ga4gh.dos.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataBundle;
import org.irods.jargon.ga4gh.dos.model.GetBundleResponse;
import org.irods.jargon.ga4gh.dos.security.RestAuthUtils;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BundlesApiControllerTest {

	@Test
	public void testGetBundle() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String bundleId = "helloImABundle";
		DosService dosService = Mockito.mock(DosService.class);
		IrodsDataBundle irodsDataBundle = new IrodsDataBundle();
		irodsDataBundle.setBundleChecksum("b828w8d9");
		irodsDataBundle.setBundleUuid(bundleId);
		irodsDataBundle.setCreateDate(new Date());
		irodsDataBundle.setDataObjects(new ArrayList<String>(Arrays.asList("foo", "bar")));
		irodsDataBundle.setDescription("desc");
		irodsDataBundle.setIrodsAbsolutePath("/foo/bar");
		irodsDataBundle.setUpdatedDate(new Date());
		irodsDataBundle.setVersion("1");
		Mockito.when(dosService.retrieveDataBundle(bundleId)).thenReturn(irodsDataBundle);
		IRODSAccount irodsAccount = TestingPropertiesHelper.buildBogusIrodsAccount();
		RestAuthUtils.setIrodsAccountInContext(irodsAccount);
		DosServiceFactory dosServiceFactory = Mockito.mock(DosServiceFactory.class);
		Mockito.when(dosServiceFactory.instanceDosService(irodsAccount)).thenReturn(dosService);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Accept", "application/json");
		BundlesApiController controller = new BundlesApiController(mapper, request);
		controller.setDosServiceFactory(dosServiceFactory);
		ResponseEntity<GetBundleResponse> actual = controller.getBundle(bundleId, "");
		Assert.assertNotNull("null response", actual);
	}

}
