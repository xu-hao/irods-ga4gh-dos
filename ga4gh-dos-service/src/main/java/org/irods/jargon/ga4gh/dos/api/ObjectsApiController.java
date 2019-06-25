package org.irods.jargon.ga4gh.dos.api;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsAccessMethod;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataObject;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.exception.DosSystemException;
import org.irods.jargon.ga4gh.dos.model.AccessMethod;
import org.irods.jargon.ga4gh.dos.model.AccessURL;
import org.irods.jargon.ga4gh.dos.model.Checksum;
import org.irods.jargon.ga4gh.dos.model.Ga4ghObject;
import org.irods.jargon.ga4gh.dos.security.RestAuthUtils;
import org.irods.jargon.ga4gh.dos.utils.DataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-14T11:28:18.659Z")

@Controller
public class ObjectsApiController implements ObjectsApi {

	private static final Logger log = LoggerFactory.getLogger(ObjectsApiController.class);

	@Autowired
	private DosServiceFactory dosServiceFactory;

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public ObjectsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public ResponseEntity<AccessURL> getAccessURL(
			@ApiParam(value = "An `id` of a Data Ga4ghObject", required = true) @PathVariable("object_id") String objectId,
			@ApiParam(value = "An `access_id` from the `access_methods` list of a Data Ga4ghObject", required = true) @PathVariable("access_id") String accessId) {

		log.info("getAccessURL()");

		if (objectId == null || objectId.isEmpty()) {
			throw new IllegalArgumentException("null or empty objectId");
		}

		log.info("objectId:{}", objectId);

		if (accessId == null || accessId.isEmpty()) {
			throw new IllegalArgumentException("null or empty objectId");
		}

		log.info("accessId:{}", accessId);

		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {

				IRODSAccount irodsAccount = RestAuthUtils.irodsAccountFromContext();
				log.debug("irodsAccount:{}", irodsAccount);

				DosService dosService = dosServiceFactory.instanceDosService(irodsAccount);

				try {
					IrodsAccessMethod irodsAccessMethod = dosService.createAccessUrlForDataObject(objectId, accessId);
					AccessURL accessUrl = new AccessURL();
					accessUrl.setHeaders(new ArrayList<String>());
					accessUrl.setUrl(irodsAccessMethod.getUrl());

					for (String header : irodsAccessMethod.getHeaders()) {
						accessUrl.getHeaders().add(header);
					}

					log.info("accessUrl:{}", accessUrl);
					return new ResponseEntity<AccessURL>(accessUrl, HttpStatus.OK);

				} catch (DosDataNotFoundException e) {
					log.error("Data not found for id", e);
					return new ResponseEntity<AccessURL>(HttpStatus.NOT_FOUND);
				}

			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<AccessURL>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<AccessURL>(HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Ga4ghObject> getObject(
			@ApiParam(value = "", required = true) @PathVariable("object_id") String objectId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {

			if (objectId == null || objectId.isEmpty()) {
				log.error("no objectId in request");
				return new ResponseEntity<Ga4ghObject>(HttpStatus.BAD_REQUEST);
			}

			log.info("objectId:{}", objectId);
			IRODSAccount irodsAccount = RestAuthUtils.irodsAccountFromContext();
			log.debug("irodsAccount:{}", irodsAccount);

			DosService dosService = dosServiceFactory.instanceDosService(irodsAccount);
			try {
				IrodsDataObject irodsDataObject = dosService.retrieveDataObject(objectId);
				log.info("irodsDataObject:{}", irodsDataObject);

				Ga4ghObject dataObject = new Ga4ghObject();
				dataObject.setCreated(DataUtils.dateToOffsetDateTime(irodsDataObject.getCreateDate()));
				dataObject.setDescription(""); // TODO: add standard description metadata
				dataObject.setId(irodsDataObject.getGuid());
				dataObject.setMimeType(irodsDataObject.getMimeType());
				dataObject.setName(irodsDataObject.getFileName());
				dataObject.setSize(irodsDataObject.getSize());
				dataObject.setUpdated(DataUtils.dateToOffsetDateTime(irodsDataObject.getModifyDate()));
				dataObject.setVersion(irodsDataObject.getVersion());
				dataObject.setAccessMethods(new ArrayList<AccessMethod>());
				dataObject.setAliases(new ArrayList<String>());
				dataObject.setChecksums(new ArrayList<Checksum>());

				/*
				 * Access methods
				 */

				AccessMethod accessMethod;
				AccessURL accessUrl;

				for (IrodsAccessMethod irodsAccessMethods : irodsDataObject.getIrodsAccessMethods()) {
					log.debug("irodsAccessMethod:{}", irodsAccessMethods);
					accessMethod = new AccessMethod();
					accessMethod.setAccessId(irodsAccessMethods.getAccessId());
					accessMethod.setType(irodsAccessMethods.getType());
					if (!irodsAccessMethods.getUrl().isEmpty()) {
						accessUrl = new AccessURL();
						accessUrl.setUrl(irodsAccessMethods.getUrl());
						accessUrl.setHeaders(new ArrayList<String>());
						for (String header : irodsAccessMethods.getHeaders()) {
							accessUrl.getHeaders().add(header);
						}

						accessMethod.setAccessUrl(accessUrl);
					}

					dataObject.getAccessMethods().add(accessMethod);

				}

				dataObject.setAliases(new ArrayList<String>());
				dataObject.getAliases().add(irodsDataObject.getAbsolutePath());
				Checksum checksum = new Checksum();
				checksum.setChecksum(irodsDataObject.getChecksum());
				checksum.setType(irodsDataObject.getChecksumType());
				dataObject.getChecksums().add(checksum);
				return new ResponseEntity<Ga4ghObject>(dataObject, HttpStatus.OK);

			} catch (DosSystemException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<Ga4ghObject>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (DosDataNotFoundException e) {
				log.error("Data not found for id", e);
				return new ResponseEntity<Ga4ghObject>(HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity<Ga4ghObject>(HttpStatus.BAD_REQUEST);
		}
	}

	public DosServiceFactory getDosServiceFactory() {
		return dosServiceFactory;
	}

	public void setDosServiceFactory(DosServiceFactory dosServiceFactory) {
		this.dosServiceFactory = dosServiceFactory;
	}

}
