/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.4).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package org.irods.jargon.ga4gh.dos.api;

import org.irods.jargon.ga4gh.dos.model.Bundle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-14T11:28:18.659Z")

@Api(value = "bundles", description = "the bundles API")
public interface BundlesApi {

	@ApiOperation(value = "Get info about a Data Bundle.", nickname = "getBundle", notes = "Returns bundle metadata, and a list of ids that can be used to fetch bundle contents.", response = Bundle.class, authorizations = {
			@Authorization(value = "authToken") }, tags = { "DataRepositoryService", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The Data Bundle was found successfully.", response = Bundle.class),
			@ApiResponse(code = 400, message = "The request is malformed.", response = Error.class),
			@ApiResponse(code = 401, message = "The request is unauthorized.", response = Error.class),
			@ApiResponse(code = 403, message = "The requester is not authorized to perform this action.", response = Error.class),
			@ApiResponse(code = 404, message = "The requested Data Bundle wasn't found.", response = Error.class),
			@ApiResponse(code = 500, message = "An unexpected error occurred.", response = Error.class) })
	@RequestMapping(value = "/bundles/{bundle_id}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<Bundle> getBundle(@ApiParam(value = "", required = true) @PathVariable("bundle_id") String bundleId);

}