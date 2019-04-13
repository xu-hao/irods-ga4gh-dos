package org.irods.jargon.ga4gh.dos.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.impl.ExplodedDosServiceImpl;
import org.irods.jargon.ga4gh.dos.model.CreateBundleRequest;
import org.irods.jargon.ga4gh.dos.model.CreateBundleResponse;
import org.irods.jargon.ga4gh.dos.model.DeleteBundleResponse;
import org.irods.jargon.ga4gh.dos.model.GetBundleResponse;
import org.irods.jargon.ga4gh.dos.model.GetBundleVersionsResponse;
import org.irods.jargon.ga4gh.dos.model.ListBundlesResponse;
import org.irods.jargon.ga4gh.dos.model.UpdateBundleRequest;
import org.irods.jargon.ga4gh.dos.model.UpdateBundleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BundlesApiController implements BundlesApi {

	private static final Logger log = LoggerFactory.getLogger(BundlesApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private DosServiceFactory dosServiceFactory;

	@org.springframework.beans.factory.annotation.Autowired
	public BundlesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public ResponseEntity<CreateBundleResponse> createBundle(
			@ApiParam(value = "", required = true) @Valid @RequestBody CreateBundleRequest body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<CreateBundleResponse>(
						objectMapper.readValue("{  \"bundle_id\" : \"bundle_id\"}", CreateBundleResponse.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<CreateBundleResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<CreateBundleResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<DeleteBundleResponse> deleteBundle(
			@ApiParam(value = "", required = true) @PathVariable("bundle_id") String bundleId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<DeleteBundleResponse>(
						objectMapper.readValue("{  \"bundle_id\" : \"bundle_id\"}", DeleteBundleResponse.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<DeleteBundleResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<DeleteBundleResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<GetBundleResponse> getBundle(
			@ApiParam(value = "", required = true) @PathVariable("bundle_id") String bundleId,
			@ApiParam(value = "If provided will return the requested version of the selected Data Bundle. Otherwise, only the latest version is returned.") @Valid @RequestParam(value = "version", required = false) String version) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			
			if (bundleId == null || bundleId.isEmpty()) {
				log.error("Null or empty bundle id", e);
				return new ResponseEntity<GetBundleResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			
			here is where we use the bundle service
			
			
			
			try {
				return new ResponseEntity<GetBundleResponse>(objectMapper.readValue(
						"{  \"bundle\" : {    \"checksums\" : [ {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    }, {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    } ],    \"object_ids\" : [ \"object_ids\", \"object_ids\" ],    \"aliases\" : [ \"aliases\", \"aliases\" ],    \"user_metadata\" : { },    \"created\" : \"2000-01-23T04:56:07.000+00:00\",    \"description\" : \"description\",    \"id\" : \"id\",    \"updated\" : \"2000-01-23T04:56:07.000+00:00\",    \"version\" : \"version\",    \"system_metadata\" : { }  }}",
						GetBundleResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<GetBundleResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<GetBundleResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<GetBundleVersionsResponse> getBundleVersions(
			@ApiParam(value = "", required = true) @PathVariable("bundle_id") String bundleId) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<GetBundleVersionsResponse>(objectMapper.readValue(
						"{  \"bundles\" : [ {    \"checksums\" : [ {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    }, {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    } ],    \"object_ids\" : [ \"object_ids\", \"object_ids\" ],    \"aliases\" : [ \"aliases\", \"aliases\" ],    \"user_metadata\" : { },    \"created\" : \"2000-01-23T04:56:07.000+00:00\",    \"description\" : \"description\",    \"id\" : \"id\",    \"updated\" : \"2000-01-23T04:56:07.000+00:00\",    \"version\" : \"version\",    \"system_metadata\" : { }  }, {    \"checksums\" : [ {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    }, {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    } ],    \"object_ids\" : [ \"object_ids\", \"object_ids\" ],    \"aliases\" : [ \"aliases\", \"aliases\" ],    \"user_metadata\" : { },    \"created\" : \"2000-01-23T04:56:07.000+00:00\",    \"description\" : \"description\",    \"id\" : \"id\",    \"updated\" : \"2000-01-23T04:56:07.000+00:00\",    \"version\" : \"version\",    \"system_metadata\" : { }  } ]}",
						GetBundleVersionsResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<GetBundleVersionsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<GetBundleVersionsResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<ListBundlesResponse> listBundles(
			@ApiParam(value = "If provided returns Data Bundles that have any alias that matches the request.") @Valid @RequestParam(value = "alias", required = false) String alias,
			@ApiParam(value = "The hexlified checksum that one would like to match on.") @Valid @RequestParam(value = "checksum", required = false) String checksum,
			@ApiParam(value = "If provided will restrict responses to those that match the provided type.  possible values: md5                # most blob stores provide a checksum using this multipart-md5      # multipart uploads provide a specialized tag in S3 sha256 sha512") @Valid @RequestParam(value = "checksum_type", required = false) String checksumType,
			@ApiParam(value = "Specifies the maximum number of results to return in a single page. If unspecified, a system default will be used.") @Valid @RequestParam(value = "page_size", required = false) Integer pageSize,
			@ApiParam(value = "The continuation token, which is used to page through large result sets. To get the next page of results, set this parameter to the value of `next_page_token` from the previous response.") @Valid @RequestParam(value = "page_token", required = false) String pageToken) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<ListBundlesResponse>(objectMapper.readValue(
						"{  \"next_page_token\" : \"next_page_token\",  \"bundles\" : [ {    \"checksums\" : [ {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    }, {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    } ],    \"object_ids\" : [ \"object_ids\", \"object_ids\" ],    \"aliases\" : [ \"aliases\", \"aliases\" ],    \"user_metadata\" : { },    \"created\" : \"2000-01-23T04:56:07.000+00:00\",    \"description\" : \"description\",    \"id\" : \"id\",    \"updated\" : \"2000-01-23T04:56:07.000+00:00\",    \"version\" : \"version\",    \"system_metadata\" : { }  }, {    \"checksums\" : [ {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    }, {      \"checksum\" : \"checksum\",      \"type\" : \"type\"    } ],    \"object_ids\" : [ \"object_ids\", \"object_ids\" ],    \"aliases\" : [ \"aliases\", \"aliases\" ],    \"user_metadata\" : { },    \"created\" : \"2000-01-23T04:56:07.000+00:00\",    \"description\" : \"description\",    \"id\" : \"id\",    \"updated\" : \"2000-01-23T04:56:07.000+00:00\",    \"version\" : \"version\",    \"system_metadata\" : { }  } ]}",
						ListBundlesResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ListBundlesResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<ListBundlesResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<UpdateBundleResponse> updateBundle(
			@ApiParam(value = "The ID of the Data Bundle to update", required = true) @PathVariable("bundle_id") String bundleId,
			@ApiParam(value = "The new content for the Data Bundle identified by the given bundle_id. If the ID specified in the request body is different than that specified in the path, the Data Bundle's ID will be replaced with the one in the request body.", required = true) @Valid @RequestBody UpdateBundleRequest body) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<UpdateBundleResponse>(
						objectMapper.readValue("{  \"bundle_id\" : \"bundle_id\"}", UpdateBundleResponse.class),
						HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<UpdateBundleResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<UpdateBundleResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	public DosServiceFactory getDosServiceFactory() {
		return dosServiceFactory;
	}

	public void setDosServiceFactory(DosServiceFactory dosServiceFactory) {
		this.dosServiceFactory = dosServiceFactory;
	}

}
