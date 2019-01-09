package org.irods.jargon.ga4gh.dos.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.model.CreateDataBundleRequest;
import org.irods.jargon.ga4gh.dos.model.CreateDataBundleResponse;
import org.irods.jargon.ga4gh.dos.model.CreateDataObjectRequest;
import org.irods.jargon.ga4gh.dos.model.CreateDataObjectResponse;
import org.irods.jargon.ga4gh.dos.model.DataBundle;
import org.irods.jargon.ga4gh.dos.model.DeleteDataBundleResponse;
import org.irods.jargon.ga4gh.dos.model.DeleteDataObjectResponse;
import org.irods.jargon.ga4gh.dos.model.GetDataBundleResponse;
import org.irods.jargon.ga4gh.dos.model.GetDataBundleVersionsResponse;
import org.irods.jargon.ga4gh.dos.model.GetDataObjectResponse;
import org.irods.jargon.ga4gh.dos.model.GetDataObjectVersionsResponse;
import org.irods.jargon.ga4gh.dos.model.ListDataBundlesResponse;
import org.irods.jargon.ga4gh.dos.model.ListDataObjectsResponse;
import org.irods.jargon.ga4gh.dos.model.ServiceInfoResponse;
import org.irods.jargon.ga4gh.dos.model.UpdateDataBundleRequest;
import org.irods.jargon.ga4gh.dos.model.UpdateDataBundleResponse;
import org.irods.jargon.ga4gh.dos.model.UpdateDataObjectRequest;
import org.irods.jargon.ga4gh.dos.model.UpdateDataObjectResponse;
import org.irods.jargon.ga4gh.dos.security.RestAuthUtils;
import org.irods.jargon.ga4gh.dos.services.IrodsDataObjectService;
import org.irods.jargon.ga4gh.dos.services.IrodsDataObjectServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-13T20:57:40.775Z")

@Controller
public class DataObjectServiceApiController implements DataObjectServiceApi {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public DataObjectServiceApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public Optional<ObjectMapper> getObjectMapper() {
		return Optional.ofNullable(objectMapper);
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	/**
	 * {@link IrodsDataObjectServiceFactory} for the data object service which is
	 * the primary vehicle for iRODS access
	 */
	@Autowired
	IrodsDataObjectServiceFactory irodsDataObjectServiceFactory;

	/**
	 * {@link PropsBasedDosConfiguration} with general configs
	 */
	@Autowired
	DosConfiguration dosConfiguration;

	public static final Logger log = LoggerFactory.getLogger(Ga4ghApiController.class);

	@Override
	public ResponseEntity<CreateDataBundleResponse> createDataBundle(CreateDataBundleRequest body) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.createDataBundle(body);
	}

	@Override
	public ResponseEntity<CreateDataObjectResponse> createDataObject(CreateDataObjectRequest body) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.createDataObject(body);
	}

	@Override
	public ResponseEntity<DeleteDataBundleResponse> deleteDataBundle(String dataBundleId) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.deleteDataBundle(dataBundleId);
	}

	@Override
	public ResponseEntity<DeleteDataObjectResponse> deleteDataObject(String dataObjectId) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.deleteDataObject(dataObjectId);
	}

	@Override
	public ResponseEntity<GetDataBundleResponse> getDataBundle(String dataBundleId, String version) {
		log.info("getDataBundle()");
		if (dataBundleId == null || dataBundleId.isEmpty()) {
			throw new IllegalArgumentException("null or empty dataBundleId");
		}

		log.info("dataBundleId:{}", dataBundleId);

		IrodsDataObjectService dataObjectService = irodsDataObjectServiceFactory
				.instance(RestAuthUtils.irodsAccountFromContext());
		ResponseEntity<GetDataBundleResponse> responseEntity;

		try {

			DataBundle dataBundle = dataObjectService.retrieveDataBundleFromId(dataBundleId);
			GetDataBundleResponse response = new GetDataBundleResponse();
			response.setDataBundle(dataBundle);
			log.debug("data bundle response:{}", response);
			responseEntity = new ResponseEntity<GetDataBundleResponse>(response, HttpStatus.OK);

		} catch (DosDataNotFoundException e) {
			log.warn("unable to find iRODS path from id:{}", dataBundleId);
			responseEntity = new ResponseEntity<GetDataBundleResponse>(HttpStatus.NOT_FOUND);
		}

		return responseEntity;

	}

	@Override
	public ResponseEntity<GetDataBundleVersionsResponse> getDataBundleVersions(String dataBundleId) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.getDataBundleVersions(dataBundleId);
	}

	@Override
	public ResponseEntity<GetDataObjectResponse> getDataObject(String dataObjectId, String version) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.getDataObject(dataObjectId, version);
	}

	@Override
	public ResponseEntity<GetDataObjectVersionsResponse> getDataObjectVersions(String dataObjectId) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.getDataObjectVersions(dataObjectId);
	}

	@Override
	public ResponseEntity<ServiceInfoResponse> getServiceInfo() {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.getServiceInfo();
	}

	@Override
	public ResponseEntity<ListDataBundlesResponse> listDataBundles(String alias, String checksum, String checksumType,
			Integer pageSize, String pageToken) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.listDataBundles(alias, checksum, checksumType, pageSize, pageToken);
	}

	@Override
	public ResponseEntity<ListDataObjectsResponse> listDataObjects(String alias, String url, String checksum,
			String checksumType, Integer pageSize, String pageToken) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.listDataObjects(alias, url, checksum, checksumType, pageSize, pageToken);
	}

	@Override
	public ResponseEntity<UpdateDataBundleResponse> updateDataBundle(String dataBundleId,
			UpdateDataBundleRequest body) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.updateDataBundle(dataBundleId, body);
	}

	@Override
	public ResponseEntity<UpdateDataObjectResponse> updateDataObject(String dataObjectId,
			UpdateDataObjectRequest body) {
		// TODO Auto-generated method stub
		return DataObjectServiceApi.super.updateDataObject(dataObjectId, body);
	}

}
