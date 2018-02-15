package gov.nih.niehs.ods.ga4gh.dos.api;

import javax.validation.Valid;

import org.irods.jargon.rest.security.RestAuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import gov.nih.niehs.ods.ga4gh.dos.exception.DosDataNotFoundException;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghCreateDataBundleRequest;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghCreateDataBundleResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghCreateDataObjectRequest;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghCreateDataObjectResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghDataObject;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghDeleteDataBundleResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghDeleteDataObjectResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghGetDataBundleResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghGetDataBundleVersionsResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghGetDataObjectResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghGetDataObjectVersionsResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghListDataBundlesRequest;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghListDataBundlesResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghListDataObjectsRequest;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghListDataObjectsResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghUpdateDataBundleRequest;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghUpdateDataBundleResponse;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghUpdateDataObjectRequest;
import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghUpdateDataObjectResponse;
import gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface;
import gov.nih.niehs.ods.ga4gh.rest.configuration.PropsBasedDosConfiguration;
import gov.nih.niehs.ods.ga4gh.services.DataObjectService;
import gov.nih.niehs.ods.ga4gh.services.DataObjectServiceFactory;
import gov.nih.niehs.ods.ga4gh.services.IdTranslationService;
import gov.nih.niehs.ods.ga4gh.services.IdTranslationServiceFactory;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "gov.nih.niehs.ods.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

@Controller
public class Ga4ghApiController implements Ga4ghApi {

	/**
	 * Spring injected factory {@link IdTranslationServiceFactory} for translating
	 * ids to iRODS paths and vice versa
	 */
	@Autowired
	IdTranslationServiceFactory idTranslationServiceFactory;

	/**
	 * {@link DataObjectServiceFactory} for the data object service which is the
	 * primary vehicle for iRODS access
	 */
	@Autowired
	DataObjectServiceFactory dataObjectServiceFactory;

	/**
	 * {@link PropsBasedDosConfiguration} with general configs
	 */
	@Autowired
	DosConfigInterface dosConfiguration;

	public static final Logger log = LoggerFactory.getLogger(Ga4ghApiController.class);

	@Override
	public ResponseEntity<Ga4ghCreateDataBundleResponse> createDataBundle(
			@ApiParam(value = "", required = true) @Valid @RequestBody Ga4ghCreateDataBundleRequest body) {
		// do some magic!
		return new ResponseEntity<Ga4ghCreateDataBundleResponse>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ga4ghCreateDataObjectResponse> createDataObject(
			@ApiParam(value = "", required = true) @Valid @RequestBody Ga4ghCreateDataObjectRequest body) {
		// do some magic!
		return new ResponseEntity<Ga4ghCreateDataObjectResponse>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ga4ghDeleteDataBundleResponse> deleteDataBundle(
			@ApiParam(value = "", required = true) @PathVariable("data_bundle_id") String dataBundleId) {
		// do some magic!
		return new ResponseEntity<Ga4ghDeleteDataBundleResponse>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ga4ghDeleteDataObjectResponse> deleteDataObject(
			@ApiParam(value = "", required = true) @PathVariable("data_object_id") String dataObjectId) {
		// do some magic!
		return new ResponseEntity<Ga4ghDeleteDataObjectResponse>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ga4ghGetDataBundleResponse> getDataBundle(
			@ApiParam(value = "", required = true) @PathVariable("data_bundle_id") String dataBundleId,
			@ApiParam(value = "OPTIONAL If provided will return the requested version of the selected Data Bundle. Otherwise, only the latest version is returned.") @RequestParam(value = "version", required = false) String version) {
		// do some magic!
		return new ResponseEntity<Ga4ghGetDataBundleResponse>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ga4ghGetDataBundleVersionsResponse> getDataBundleVersions(
			@ApiParam(value = "", required = true) @PathVariable("data_bundle_id") String dataBundleId) {
		// do some magic!
		return new ResponseEntity<Ga4ghGetDataBundleVersionsResponse>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ga4ghGetDataObjectResponse> getDataObject(
			@ApiParam(value = "", required = true) @PathVariable("data_object_id") String dataObjectId,
			@ApiParam(value = "OPTIONAL If provided will return the requested version of the selected Data Object.") @RequestParam(value = "version", required = false) String version) {

		log.info("getDataObject()");

		if (dataObjectId == null || dataObjectId.isEmpty()) {
			throw new IllegalArgumentException("null or empty dataObjectId");
		}

		log.info("dataObjectId:{}", dataObjectId);

		log.debug("translating id to iRODS path");
		IdTranslationService idTranslationService = this.getIdTranslationServiceFactory()
				.instance(RestAuthUtils.irodsAccountFromContext());
		DataObjectService dataObjectService = this.getDataObjectServiceFactory()
				.instance(RestAuthUtils.irodsAccountFromContext());

		ResponseEntity<Ga4ghGetDataObjectResponse> responseEntity;
		try {
			String irodsPath = idTranslationService.irodsPathFromIdentifier(dataObjectId);
			log.debug("translated path:{}", irodsPath);
			Ga4ghDataObject dataObject = dataObjectService.retrieveDataObjectFromIrodsPath(irodsPath);
			Ga4ghGetDataObjectResponse response = new Ga4ghGetDataObjectResponse();
			response.setDataObject(dataObject);
			log.debug("data object response:{}", response);
			responseEntity = new ResponseEntity<Ga4ghGetDataObjectResponse>(response, HttpStatus.OK);

		} catch (DosDataNotFoundException e) {
			log.warn("unable to find iRODS path from id:{}", dataObjectId);
			responseEntity = new ResponseEntity<Ga4ghGetDataObjectResponse>(HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

	@Override
	public ResponseEntity<Ga4ghGetDataObjectVersionsResponse> getDataObjectVersions(
			@ApiParam(value = "", required = true) @PathVariable("data_object_id") String dataObjectId) {
		// do some magic!
		return new ResponseEntity<Ga4ghGetDataObjectVersionsResponse>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ga4ghListDataBundlesResponse> listDataBundles(
			@ApiParam(value = "", required = true) @Valid @RequestBody Ga4ghListDataBundlesRequest body) {
		// do some magic!
		return new ResponseEntity<Ga4ghListDataBundlesResponse>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ga4ghListDataObjectsResponse> listDataObjects(
			@ApiParam(value = "", required = true) @Valid @RequestBody Ga4ghListDataObjectsRequest body) {
		// do some magic!
		return new ResponseEntity<Ga4ghListDataObjectsResponse>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ga4ghUpdateDataBundleResponse> updateDataBundle(
			@ApiParam(value = "", required = true) @PathVariable("data_bundle_id") String dataBundleId,
			@ApiParam(value = "", required = true) @Valid @RequestBody Ga4ghUpdateDataBundleRequest body) {
		// do some magic!
		return new ResponseEntity<Ga4ghUpdateDataBundleResponse>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Ga4ghUpdateDataObjectResponse> updateDataObject(
			@ApiParam(value = "", required = true) @PathVariable("data_object_id") String dataObjectId,
			@ApiParam(value = "", required = true) @Valid @RequestBody Ga4ghUpdateDataObjectRequest body) {
		// do some magic!
		return new ResponseEntity<Ga4ghUpdateDataObjectResponse>(HttpStatus.OK);
	}

	public IdTranslationServiceFactory getIdTranslationServiceFactory() {
		return idTranslationServiceFactory;
	}

	public void setIdTranslationServiceFactory(IdTranslationServiceFactory idTranslationServiceFactory) {
		this.idTranslationServiceFactory = idTranslationServiceFactory;
	}

	public DataObjectServiceFactory getDataObjectServiceFactory() {
		return dataObjectServiceFactory;
	}

	public void setDataObjectServiceFactory(DataObjectServiceFactory dataObjectServiceFactory) {
		this.dataObjectServiceFactory = dataObjectServiceFactory;
	}

	public DosConfigInterface getDosConfiguration() {
		return dosConfiguration;
	}

	public void setDosConfiguration(DosConfigInterface dosConfiguration) {
		this.dosConfiguration = dosConfiguration;
	}

}
