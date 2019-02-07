package org.irods.jargon.ga4gh.dos.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.irods.jargon.ga4gh.dos.model.ServiceInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-07T17:18:44.860Z")

@Controller
public class ServiceInfoApiController implements ServiceInfoApi {

	private static final Logger log = LoggerFactory.getLogger(ServiceInfoApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public ServiceInfoApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public ResponseEntity<ServiceInfoResponse> getServiceInfo() {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			try {
				return new ResponseEntity<ServiceInfoResponse>(objectMapper.readValue(
						"{  \"license\" : \"{}\",  \"contact\" : \"{}\",  \"description\" : \"description\",  \"title\" : \"title\",  \"version\" : \"version\"}",
						ServiceInfoResponse.class), HttpStatus.NOT_IMPLEMENTED);
			} catch (IOException e) {
				log.error("Couldn't serialize response for content type application/json", e);
				return new ResponseEntity<ServiceInfoResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<ServiceInfoResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

}
