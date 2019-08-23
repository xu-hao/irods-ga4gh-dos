/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.7).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.irods.jargon.ga4gh.dos.api;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.irods.jargon.ga4gh.dos.model.AccessURL;
import org.irods.jargon.ga4gh.dos.model.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-22T19:13:50.266Z")

@Api(value = "objects", description = "the objects API")
public interface ObjectsApi {

	Logger log = LoggerFactory.getLogger(ObjectsApi.class);

	default Optional<ObjectMapper> getObjectMapper() {
		return Optional.empty();
	}

	default Optional<HttpServletRequest> getRequest() {
		return Optional.empty();
	}

	default Optional<String> getAcceptHeader() {
		return getRequest().map(r -> r.getHeader("Accept"));
	}

	@ApiOperation(value = "Get a URL for fetching bytes.", nickname = "getAccessURL", notes = "Returns a URL that can be used to fetch the bytes of an `Ga4ghObject`.  This method only needs to be called when using an `AccessMethod` that contains an `access_id` (e.g., for servers that use signed URLs for fetching object bytes).", response = AccessURL.class, authorizations = {
			@Authorization(value = "authToken") }, tags = { "DataRepositoryService", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The access URL was found successfully.", response = AccessURL.class),
			@ApiResponse(code = 202, message = "The operation is delayed and will continue asynchronously. The client should retry this same request after the delay specified by Retry-After header. "),
			@ApiResponse(code = 400, message = "The request is malformed.", response = Error.class),
			@ApiResponse(code = 401, message = "The request is unauthorized.", response = Error.class),
			@ApiResponse(code = 403, message = "The requester is not authorized to perform this action.", response = Error.class),
			@ApiResponse(code = 404, message = "The requested access URL wasn't found", response = Error.class),
			@ApiResponse(code = 500, message = "An unexpected error occurred.", response = Error.class) })
	@RequestMapping(value = "/objects/{object_id}/access/{access_id}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	default ResponseEntity<AccessURL> getAccessURL(
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

	@ApiOperation(value = "Get info about an `Ga4ghObject`.", nickname = "getObject", notes = "Returns object metadata, and a list of access methods that can be used to fetch object bytes.", response = Object.class, authorizations = {
			@Authorization(value = "authToken") }, tags = { "DataRepositoryService", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The `Ga4ghObject` was found successfully.", response = Object.class),
			@ApiResponse(code = 202, message = "The operation is delayed and will continue asynchronously. The client should retry this same request after the delay specified by Retry-After header. "),
			@ApiResponse(code = 400, message = "The request is malformed.", response = Error.class),
			@ApiResponse(code = 401, message = "The request is unauthorized.", response = Error.class),
			@ApiResponse(code = 403, message = "The requester is not authorized to perform this action.", response = Error.class),
			@ApiResponse(code = 404, message = "The requested `Ga4ghObject` wasn't found", response = Error.class),
			@ApiResponse(code = 500, message = "An unexpected error occurred.", response = Error.class) })
	@RequestMapping(value = "/objects/{object_id}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	default ResponseEntity<Object> getObject(
			@ApiParam(value = "", required = true) @PathVariable("object_id") String objectId,
			@ApiParam(value = "If false and the object_id refers to a bundle, then the ContentsObject array contains only those objects directly contained in the bundle. That is, if the bundle contains other bundles, those other bundles are not recursively included in the result. If true and the object_id refers to a bundle, then the entire set of objects in the bundle is expanded. That is, if the bundle contains aother bundles, then those other bundles are recursively expanded and included in the result. Recursion continues through the entire sub-tree of the bundle. If the object_id refers to a blob, then the query parameter is ignored.", defaultValue = "false") @Valid @RequestParam(value = "expand", required = false, defaultValue = "false") Boolean expand) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new ResponseEntity<>(getObjectMapper().get().readValue(
							"{  \"checksums\" : [ {    \"checksum\" : \"checksum\",    \"type\" : \"sha-256\"  }, {    \"checksum\" : \"checksum\",    \"type\" : \"sha-256\"  } ],  \"created_time\" : \"2000-01-23T04:56:07.000+00:00\",  \"updated_time\" : \"2000-01-23T04:56:07.000+00:00\",  \"aliases\" : [ \"aliases\", \"aliases\" ],  \"description\" : \"description\",  \"self_uri\" : \"drs://drs.example.org/314159\",  \"version\" : \"version\",  \"size\" : 0,  \"mime_type\" : \"application/json\",  \"access_methods\" : [ {    \"access_url\" : {      \"headers\" : {        \"Authorization\" : \"Basic Z2E0Z2g6ZHJz\"      },      \"url\" : \"url\"    },    \"access_id\" : \"access_id\",    \"type\" : \"s3\",    \"region\" : \"us-east-1\"  }, {    \"access_url\" : {      \"headers\" : {        \"Authorization\" : \"Basic Z2E0Z2g6ZHJz\"      },      \"url\" : \"url\"    },    \"access_id\" : \"access_id\",    \"type\" : \"s3\",    \"region\" : \"us-east-1\"  } ],  \"contents\" : [ {    \"contents\" : [ null, null ],    \"name\" : \"name\",    \"id\" : \"id\",    \"drs_uri\" : \"drs://example.com/ga4gh/drs/v1/objects/{object_id}\"  }, {    \"contents\" : [ null, null ],    \"name\" : \"name\",    \"id\" : \"id\",    \"drs_uri\" : \"drs://example.com/ga4gh/drs/v1/objects/{object_id}\"  } ],  \"name\" : \"name\",  \"id\" : \"id\"}",
							Object.class), HttpStatus.NOT_IMPLEMENTED);
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

}
