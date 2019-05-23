package org.irods.jargon.ga4gh.dos.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.irods.jargon.ga4gh.dos.model.AccessURL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public ObjectsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public ResponseEntity<AccessURL> getAccessURL(
			@ApiParam(value = "An `id` of a Data Object", required = true) @PathVariable("object_id") String objectId,
			@ApiParam(value = "An `access_id` from the `access_methods` list of a Data Object", required = true) @PathVariable("access_id") String accessId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<AccessURL>(objectMapper.readValue(
						"{  \"headers\" : {    \"Authorization\" : \"Basic Z2E0Z2g6ZHJz\"  },  \"url\" : \"url\"}",
						AccessURL.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<AccessURL>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<AccessURL>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<org.irods.jargon.ga4gh.dos.model.Object> getObject(
			@ApiParam(value = "", required = true) @PathVariable("object_id") String objectId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<org.irods.jargon.ga4gh.dos.model.Object>(objectMapper.readValue(
						"{  \"checksums\" : [ {    \"checksum\" : \"checksum\",    \"type\" : \"type\"  }, {    \"checksum\" : \"checksum\",    \"type\" : \"type\"  } ],  \"aliases\" : [ \"aliases\", \"aliases\" ],  \"size\" : 0,  \"mime_type\" : \"application/json\",  \"access_methods\" : [ {    \"access_url\" : {      \"headers\" : {        \"Authorization\" : \"Basic Z2E0Z2g6ZHJz\"      },      \"url\" : \"url\"    },    \"access_id\" : \"access_id\",    \"type\" : \"s3\",    \"region\" : \"us-east-1\"  }, {    \"access_url\" : {      \"headers\" : {        \"Authorization\" : \"Basic Z2E0Z2g6ZHJz\"      },      \"url\" : \"url\"    },    \"access_id\" : \"access_id\",    \"type\" : \"s3\",    \"region\" : \"us-east-1\"  } ],  \"created\" : \"2000-01-23T04:56:07.000+00:00\",  \"name\" : \"name\",  \"description\" : \"description\",  \"id\" : \"id\",  \"updated\" : \"2000-01-23T04:56:07.000+00:00\",  \"version\" : \"version\"}",
						org.irods.jargon.ga4gh.dos.model.Object.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<org.irods.jargon.ga4gh.dos.model.Object>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<org.irods.jargon.ga4gh.dos.model.Object>(HttpStatus.NOT_IMPLEMENTED);
	}

}
