package org.irods.jargon.ga4gh.dos.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.irods.jargon.ga4gh.dos.model.CreateObjectRequest;
import org.irods.jargon.ga4gh.dos.model.CreateObjectResponse;
import org.irods.jargon.ga4gh.dos.model.DeleteObjectResponse;
import org.irods.jargon.ga4gh.dos.model.GetObjectResponse;
import org.irods.jargon.ga4gh.dos.model.GetObjectVersionsResponse;
import org.irods.jargon.ga4gh.dos.model.ListObjectsResponse;
import org.irods.jargon.ga4gh.dos.model.UpdateObjectRequest;
import org.irods.jargon.ga4gh.dos.model.UpdateObjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

@Controller
public class ObjectsApiController implements ObjectsApi {

	private static final Logger log = LoggerFactory.getLogger(ObjectsApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public ObjectsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<CreateObjectResponse> createObject(
			@ApiParam(value = "The Data Object to be created. The ID scheme is left up to the implementor but should be unique to the server instance.", required = true) @Valid @RequestBody CreateObjectRequest body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<CreateObjectResponse>(
						objectMapper.readValue("{  \"object_id\" : \"object_id\"}", CreateObjectResponse.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<CreateObjectResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<CreateObjectResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<DeleteObjectResponse> deleteObject(
			@ApiParam(value = "", required = true) @PathVariable("object_id") String objectId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<DeleteObjectResponse>(
						objectMapper.readValue("{  \"object_id\" : \"object_id\"}", DeleteObjectResponse.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<DeleteObjectResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<DeleteObjectResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<GetObjectResponse> getObject(
			@ApiParam(value = "", required = true) @PathVariable("object_id") String objectId,
			@ApiParam(value = "If provided will return the requested version of the selected Data Object.") @Valid @RequestParam(value = "version", required = false) String version) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<GetObjectResponse>(objectMapper.readValue(
						"{  \"object\" : {    \"checksums\" : [ {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    }, {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    } ],    \"urls\" : [ {      \"user_metadata\" : { },      \"system_metadata\" : { },      \"url\" : \"url\",      \"authorization_metadata\" : {        \"auth_type\" : \"auth_type\",        \"auth_url\" : \"auth_url\"      }    }, {      \"user_metadata\" : { },      \"system_metadata\" : { },      \"url\" : \"url\",      \"authorization_metadata\" : {        \"auth_type\" : \"auth_type\",        \"auth_url\" : \"auth_url\"      }    } ],    \"aliases\" : [ \"aliases\", \"aliases\" ],    \"size\" : \"size\",    \"mime_type\" : \"mime_type\",    \"created\" : \"2000-01-23T04:56:07.000+00:00\",    \"name\" : \"name\",    \"description\" : \"description\",    \"id\" : \"id\",    \"updated\" : \"2000-01-23T04:56:07.000+00:00\",    \"version\" : \"version\"  }}",
						GetObjectResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<GetObjectResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<GetObjectResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<GetObjectVersionsResponse> getObjectVersions(
			@ApiParam(value = "", required = true) @PathVariable("object_id") String objectId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<GetObjectVersionsResponse>(objectMapper.readValue(
						"{  \"objects\" : [ {    \"checksums\" : [ {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    }, {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    } ],    \"urls\" : [ {      \"user_metadata\" : { },      \"system_metadata\" : { },      \"url\" : \"url\",      \"authorization_metadata\" : {        \"auth_type\" : \"auth_type\",        \"auth_url\" : \"auth_url\"      }    }, {      \"user_metadata\" : { },      \"system_metadata\" : { },      \"url\" : \"url\",      \"authorization_metadata\" : {        \"auth_type\" : \"auth_type\",        \"auth_url\" : \"auth_url\"      }    } ],    \"aliases\" : [ \"aliases\", \"aliases\" ],    \"size\" : \"size\",    \"mime_type\" : \"mime_type\",    \"created\" : \"2000-01-23T04:56:07.000+00:00\",    \"name\" : \"name\",    \"description\" : \"description\",    \"id\" : \"id\",    \"updated\" : \"2000-01-23T04:56:07.000+00:00\",    \"version\" : \"version\"  }, {    \"checksums\" : [ {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    }, {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    } ],    \"urls\" : [ {      \"user_metadata\" : { },      \"system_metadata\" : { },      \"url\" : \"url\",      \"authorization_metadata\" : {        \"auth_type\" : \"auth_type\",        \"auth_url\" : \"auth_url\"      }    }, {      \"user_metadata\" : { },      \"system_metadata\" : { },      \"url\" : \"url\",      \"authorization_metadata\" : {        \"auth_type\" : \"auth_type\",        \"auth_url\" : \"auth_url\"      }    } ],    \"aliases\" : [ \"aliases\", \"aliases\" ],    \"size\" : \"size\",    \"mime_type\" : \"mime_type\",    \"created\" : \"2000-01-23T04:56:07.000+00:00\",    \"name\" : \"name\",    \"description\" : \"description\",    \"id\" : \"id\",    \"updated\" : \"2000-01-23T04:56:07.000+00:00\",    \"version\" : \"version\"  } ]}",
						GetObjectVersionsResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<GetObjectVersionsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<GetObjectVersionsResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<ListObjectsResponse> listObjects(
			@ApiParam(value = "If provided will only return Data Objects with the given alias.") @Valid @RequestParam(value = "alias", required = false) String alias,
			@ApiParam(value = "If provided will return only Data Objects with a that URL matches this string.") @Valid @RequestParam(value = "url", required = false) String url,
			@ApiParam(value = "The hexlified checksum that one would like to match on.") @Valid @RequestParam(value = "checksum", required = false) String checksum,
			@ApiParam(value = "If provided will restrict responses to those that match the provided type.  possible values: md5                # most blob stores provide a checksum using this multipart-md5      # multipart uploads provide a specialized tag in S3 sha256 sha512") @Valid @RequestParam(value = "checksum_type", required = false) String checksumType,
			@ApiParam(value = "Specifies the maximum number of results to return in a single page. If unspecified, a system default will be used.") @Valid @RequestParam(value = "page_size", required = false) Integer pageSize,
			@ApiParam(value = "The continuation token, which is used to page through large result sets. To get the next page of results, set this parameter to the value of `next_page_token` from the previous response.") @Valid @RequestParam(value = "page_token", required = false) String pageToken) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<ListObjectsResponse>(objectMapper.readValue(
						"{  \"next_page_token\" : \"next_page_token\",  \"objects\" : [ {    \"checksums\" : [ {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    }, {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    } ],    \"urls\" : [ {      \"user_metadata\" : { },      \"system_metadata\" : { },      \"url\" : \"url\",      \"authorization_metadata\" : {        \"auth_type\" : \"auth_type\",        \"auth_url\" : \"auth_url\"      }    }, {      \"user_metadata\" : { },      \"system_metadata\" : { },      \"url\" : \"url\",      \"authorization_metadata\" : {        \"auth_type\" : \"auth_type\",        \"auth_url\" : \"auth_url\"      }    } ],    \"aliases\" : [ \"aliases\", \"aliases\" ],    \"size\" : \"size\",    \"mime_type\" : \"mime_type\",    \"created\" : \"2000-01-23T04:56:07.000+00:00\",    \"name\" : \"name\",    \"description\" : \"description\",    \"id\" : \"id\",    \"updated\" : \"2000-01-23T04:56:07.000+00:00\",    \"version\" : \"version\"  }, {    \"checksums\" : [ {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    }, {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    } ],    \"urls\" : [ {      \"user_metadata\" : { },      \"system_metadata\" : { },      \"url\" : \"url\",      \"authorization_metadata\" : {        \"auth_type\" : \"auth_type\",        \"auth_url\" : \"auth_url\"      }    }, {      \"user_metadata\" : { },      \"system_metadata\" : { },      \"url\" : \"url\",      \"authorization_metadata\" : {        \"auth_type\" : \"auth_type\",        \"auth_url\" : \"auth_url\"      }    } ],    \"aliases\" : [ \"aliases\", \"aliases\" ],    \"size\" : \"size\",    \"mime_type\" : \"mime_type\",    \"created\" : \"2000-01-23T04:56:07.000+00:00\",    \"name\" : \"name\",    \"description\" : \"description\",    \"id\" : \"id\",    \"updated\" : \"2000-01-23T04:56:07.000+00:00\",    \"version\" : \"version\"  } ]}",
						ListObjectsResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ListObjectsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<ListObjectsResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<UpdateObjectResponse> updateObject(
			@ApiParam(value = "The ID of the Data Object to update", required = true) @PathVariable("object_id") String objectId,
			@ApiParam(value = "The new Data Object for the given object_id. If the ID specified in the request body is different than that specified in the path, the Data Object's ID will be replaced with the one in the request body.", required = true) @Valid @RequestBody UpdateObjectRequest body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<UpdateObjectResponse>(
						objectMapper.readValue("{  \"object_id\" : \"object_id\"}", UpdateObjectResponse.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<UpdateObjectResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<UpdateObjectResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

}
