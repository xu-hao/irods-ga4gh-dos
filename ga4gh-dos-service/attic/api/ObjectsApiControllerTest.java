package org.irods.jargon.ga4gh.dos.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsAccessMethod;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataObject;
import org.irods.jargon.ga4gh.dos.model.AccessURL;
import org.irods.jargon.ga4gh.dos.model.BundleObject.TypeEnum;
import org.irods.jargon.ga4gh.dos.model.Checksum;
import org.irods.jargon.ga4gh.dos.model.Ga4ghObject;
import org.irods.jargon.ga4gh.dos.security.RestAuthUtils;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectsApiControllerTest {

	@Test
	public void testGetAccessUrl() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String objectId = "abcd";
		DosService dosService = Mockito.mock(DosService.class);
		IrodsAccessMethod accessMethod = new IrodsAccessMethod();
		List<String> headers = new ArrayList<String>();
		headers.add("hithere");
		accessMethod.setAccessId("foo");
		accessMethod.setType(org.irods.jargon.ga4gh.dos.model.AccessMethod.TypeEnum.HTTPS);
		accessMethod.setUrl("http://foo.com");
		accessMethod.setHeaders(headers);
		Mockito.when(dosService.createAccessUrlForDataObject(objectId, DosService.ACCESS_REST))
				.thenReturn(accessMethod);
		IRODSAccount irodsAccount = TestingPropertiesHelper.buildBogusIrodsAccount();
		RestAuthUtils.setIrodsAccountInContext(irodsAccount);
		DosServiceFactory dosServiceFactory = Mockito.mock(DosServiceFactory.class);
		Mockito.when(dosServiceFactory.instanceDosService(irodsAccount)).thenReturn(dosService);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Accept", "application/json");
		ObjectsApiController controller = new ObjectsApiController(mapper, request);
		controller.setDosServiceFactory(dosServiceFactory);

		ResponseEntity<AccessURL> actual = controller.getAccessURL(objectId, DosService.ACCESS_REST);
		Assert.assertNotNull("null access method", actual);
		Assert.assertEquals(accessMethod.getUrl(), actual.getBody().getUrl());
		Assert.assertEquals("hithere", actual.getBody().getHeaders().get(0));

	}

	@Test
	public void testGetObject() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String objectId = "abcd";
		DosService dosService = Mockito.mock(DosService.class);

		IrodsDataObject dataObject = new IrodsDataObject();

		dataObject.setAbsolutePath("/an/absolute/path");
		dataObject.setFileName("foo");
		dataObject.setGuid("abcd");
		dataObject.setType(TypeEnum.OBJECT);
		IrodsAccessMethod accessMethod = new IrodsAccessMethod();
		accessMethod.setAccessId("foo");
		accessMethod.setType(org.irods.jargon.ga4gh.dos.model.AccessMethod.TypeEnum.FILE);
		accessMethod.setUrl("http://foo.com");
		dataObject.getIrodsAccessMethods().add(accessMethod);
		dataObject.setCreateDate(new Date());
		dataObject.setModifyDate(new Date());

		Mockito.when(dosService.retrieveDataObject(objectId)).thenReturn(dataObject);

		IRODSAccount irodsAccount = TestingPropertiesHelper.buildBogusIrodsAccount();
		RestAuthUtils.setIrodsAccountInContext(irodsAccount);
		DosServiceFactory dosServiceFactory = Mockito.mock(DosServiceFactory.class);
		Mockito.when(dosServiceFactory.instanceDosService(irodsAccount)).thenReturn(dosService);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Accept", "application/json");
		ObjectsApiController controller = new ObjectsApiController(mapper, request);
		controller.setDosServiceFactory(dosServiceFactory);
		ResponseEntity<Ga4ghObject> actual = controller.getObject(objectId);
		Assert.assertNotNull("null response", actual);
		Assert.assertEquals(objectId, actual.getBody().getId());
		Assert.assertEquals(dataObject.getAbsolutePath(), actual.getBody().getAliases().get(0));
		Checksum actualChecksum = actual.getBody().getChecksums().get(0);
		Assert.assertEquals(dataObject.getChecksum(), actualChecksum.getChecksum());
		Assert.assertEquals(dataObject.getChecksumType(), actualChecksum.getType());
		Assert.assertEquals(dataObject.getFileName(), actual.getBody().getName());
		Assert.assertEquals(dataObject.getMimeType(), actual.getBody().getMimeType());
		Assert.assertEquals(dataObject.getSize(), actual.getBody().getSize().longValue());
		Assert.assertFalse("did not set access methods", actual.getBody().getAccessMethods().isEmpty());

	}

}
