package org.irods.jargon.ga4gh.dos.api;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.model.AccessURL;
import org.irods.jargon.ga4gh.dos.model.Ga4ghObject;
import org.irods.jargon.ga4gh.dos.security.ContextAccountHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-22T19:13:50.266Z")

@Controller
public class ObjectsApiController implements ObjectsApi {

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private DosServiceFactory dosServiceFactory;

	@Autowired
	private ContextAccountHelper contextAccountHelper;

	private static final Logger log = LoggerFactory.getLogger(ObjectsApiController.class);

	@org.springframework.beans.factory.annotation.Autowired
	public ObjectsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<AccessURL> getAccessURL(
			@ApiParam(value = "An `id` of an `Ga4ghObject`", required = true) @PathVariable("object_id") String objectId,
			@ApiParam(value = "An `access_id` from the `access_methods` list of an `Ga4ghObject`", required = true) @PathVariable("access_id") String accessId) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new ResponseEntity<>(getObjectMapper().get().readValue(
							"{  \"headers\" : {    \"Authorization\" : \"Basic Z2E0Z2g6ZHJz\"  },  \"url\" : \"url\"}",
							AccessURL.class), HttpStatus.NOT_IMPLEMENTED);
				} catch (IOException e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default ObjectsApi interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<Ga4ghObject> getObject(
			@ApiParam(value = "", required = true) @PathVariable("object_id") String objectId,
			@ApiParam(value = "If false and the object_id refers to a bundle, then the ContentsObject array contains only those objects directly contained in the bundle. That is, if the bundle contains other bundles, those other bundles are not recursively included in the result. If true and the object_id refers to a bundle, then the entire set of objects in the bundle is expanded. That is, if the bundle contains aother bundles, then those other bundles are recursively expanded and included in the result. Recursion continues through the entire sub-tree of the bundle. If the object_id refers to a blob, then the query parameter is ignored.", defaultValue = "false") @Valid @RequestParam(value = "expand", required = false, defaultValue = "false") Boolean expand) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					log.info("getObject()");
					if (objectId == null || objectId.isEmpty()) {
						throw new IllegalArgumentException("null or empty objectId");
					}

					log.info("objectId:{}", objectId);
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					String name = auth.getName();
					log.info("name:{}", name);

					// IRODSAccount irodsAccount =
					// this.contextAccountHelper.irodsAccountFromAuthentication(authentication)
					return new ResponseEntity<>(HttpStatus.OK);
				} catch (Exception e) {
					log.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			log.warn(
					"ObjectMapper or HttpServletRequest not configured in default ObjectsApi interface so no example is generated");
		}
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public Optional<ObjectMapper> getObjectMapper() {
		return Optional.ofNullable(objectMapper);
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	public DosServiceFactory getDosServiceFactory() {
		return dosServiceFactory;
	}

	public void setDosServiceFactory(DosServiceFactory dosServiceFactory) {
		this.dosServiceFactory = dosServiceFactory;
	}

	public ContextAccountHelper getContextAccountHelper() {
		return contextAccountHelper;
	}

	public void setContextAccountHelper(ContextAccountHelper contextAccountHelper) {
		this.contextAccountHelper = contextAccountHelper;
	}

}
