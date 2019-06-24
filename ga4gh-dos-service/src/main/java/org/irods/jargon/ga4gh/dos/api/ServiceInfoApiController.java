package org.irods.jargon.ga4gh.dos.api;

import javax.servlet.http.HttpServletRequest;

import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.model.ServiceInfo;
import org.irods.jargon.ga4gh.dos.utils.Ga4ghVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-05-14T11:28:18.659Z")

@Controller
public class ServiceInfoApiController implements ServiceInfoApi {

	private static final Logger log = LoggerFactory.getLogger(ServiceInfoApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private DosConfiguration dosConfiguration;

	@org.springframework.beans.factory.annotation.Autowired
	public ServiceInfoApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public ResponseEntity<ServiceInfo> getServiceInfo() {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {

			ServiceInfo serviceInfo = new ServiceInfo();

			serviceInfo.setContact(dosConfiguration.getContact());
			serviceInfo.setLicense(dosConfiguration.getLicense());
			serviceInfo.setDescription(dosConfiguration.getDescription());
			serviceInfo.setTitle(dosConfiguration.getTitle());
			serviceInfo.setContact(Ga4ghVersion.VERSION + " - " + Ga4ghVersion.BUILD_TIME);

			return new ResponseEntity<ServiceInfo>(serviceInfo, HttpStatus.OK);

		}

		return new ResponseEntity<ServiceInfo>(HttpStatus.BAD_REQUEST);
	}

	public DosConfiguration getDosConfiguration() {
		return dosConfiguration;
	}

	public void setDosConfiguration(DosConfiguration dosConfiguration) {
		this.dosConfiguration = dosConfiguration;
	}

}
