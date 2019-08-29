package org.irods.jargon.ga4gh.dos.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.connection.IRODSSession;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.BundleInfoAndPath;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsAccessMethod;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataBundle;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataObject;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.exception.DosDataNotFoundException;
import org.irods.jargon.ga4gh.dos.model.AccessMethod;
import org.irods.jargon.ga4gh.dos.model.AccessURL;
import org.irods.jargon.ga4gh.dos.model.Checksum;
import org.irods.jargon.ga4gh.dos.model.ContentsObject;
import org.irods.jargon.ga4gh.dos.model.Ga4ghObject;
import org.irods.jargon.ga4gh.dos.security.ContextAccountHelper;
import org.irods.jargon.ga4gh.dos.utils.ServiceUtils;
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

	@Autowired
	DosConfiguration dosConfiguration;

	@Autowired
	IRODSSession irodsSession;

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

		log.info("getObject()");
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
					// this.contextAccountHelper.irodsAccountFromAuthentication(name);
					IRODSAccount irodsAccount = IRODSAccount.instance("107.23.1.37", 1247, "drs",
							"LookAtTheBigBrainOnDrs", "", "tempZone", "");
					DosService dosService = dosServiceFactory.instanceDosService(irodsAccount);
					try {
						BundleInfoAndPath bundleInfo = dosService.resolveId(objectId);
						if (bundleInfo.isCollection()) {
							log.info("this is a data bundle");
							IrodsDataBundle irodsDataBundle = dosService.retrieveDataBundle(bundleInfo);
							Ga4ghObject ga4ghObject = new Ga4ghObject();
							ga4ghObject.setId(objectId);
							ga4ghObject.setName(irodsDataBundle.getIrodsAbsolutePath());
							ga4ghObject.setSelfUri(dosConfiguration.getDrsRestUrlEndpoint() + "/objects/" + objectId);
							ga4ghObject.setSize(0L);
							ga4ghObject.setCreatedTime(
									ServiceUtils.offsetDateTimeFromDate(irodsDataBundle.getCreateDate()));
							ga4ghObject.setUpdatedTime(
									ServiceUtils.offsetDateTimeFromDate(irodsDataBundle.getUpdatedDate()));
							ga4ghObject.setVersion("0");
							ga4ghObject.setMimeType("text/directory");

							ga4ghObject.addAliasesItem(irodsDataBundle.getIrodsAbsolutePath());
							Checksum checksum = new Checksum();
							checksum.setChecksum(irodsDataBundle.getBundleChecksum());
							checksum.setType(irodsDataBundle.getBundleChecksumType());
							ga4ghObject.addChecksumsItem(checksum);
							ContentsObject bundleObject;
							List<ContentsObject> dataObjects = new ArrayList<>();
							for (IrodsDataObject dataObject : irodsDataBundle.getDataObjects()) {
								bundleObject = new ContentsObject();
								bundleObject.setId(dataObject.getGuid());
								bundleObject.setName(dataObject.getFileName());
								bundleObject.setDrsUri(new ArrayList<String>());
								bundleObject.getDrsUri().add(dataObject.getIrodsAccessMethods().get(0).getUrl());
								dataObjects.add(bundleObject);
							}

							ga4ghObject.setContents(dataObjects);
							ga4ghObject.setDescription(irodsDataBundle.getDescription());
							log.info("ga4ghObject:{}", ga4ghObject);
							return new ResponseEntity<Ga4ghObject>(ga4ghObject, HttpStatus.OK);

						} else {
							log.info("this is a data object");
							IrodsDataObject irodsDataObject = dosService.retrieveDataObject(bundleInfo);
							log.debug("have the data object:{}", irodsDataObject);

							Ga4ghObject ga4ghObject = new Ga4ghObject();
							ga4ghObject.setId(irodsDataObject.getGuid());
							ga4ghObject.setName(irodsDataObject.getAbsolutePath());
							ga4ghObject.setSelfUri(dosConfiguration.getDrsRestUrlEndpoint() + "/objects/" + objectId);
							ga4ghObject.setSize(irodsDataObject.getSize());
							ga4ghObject.setCreatedTime(
									ServiceUtils.offsetDateTimeFromDate(irodsDataObject.getCreateDate()));
							ga4ghObject.setUpdatedTime(
									ServiceUtils.offsetDateTimeFromDate(irodsDataObject.getModifyDate()));
							ga4ghObject.setVersion("");
							ga4ghObject.setMimeType(irodsDataObject.getMimeType());

							List<Checksum> checksums = new ArrayList<>();

							Checksum checksum = new Checksum();
							checksum.setChecksum(irodsDataObject.getChecksum());
							checksum.setType(irodsDataObject.getChecksumType());
							checksums.add(checksum);

							List<AccessMethod> accessMethods = new ArrayList<>();

							for (IrodsAccessMethod irodsAccessMethod : irodsDataObject.getIrodsAccessMethods()) {
								AccessMethod accessMethod = new AccessMethod();
								accessMethod.setAccessId(irodsAccessMethod.getAccessId());
								AccessURL accessURL = new AccessURL();
								accessURL.setHeaders(irodsAccessMethod.getHeaders());
								accessURL.setUrl(irodsAccessMethod.getUrl());
								accessMethod.setAccessUrl(accessURL);
								accessMethods.add(accessMethod);
							}

							ga4ghObject.setDescription(""); // TODO: add formal description AVU
							List<String> aliases = new ArrayList<>();
							aliases.add(irodsDataObject.getAbsolutePath());
							ga4ghObject.setAliases(aliases);

							log.info("ga4ghObject:{}", ga4ghObject);
							return new ResponseEntity<Ga4ghObject>(ga4ghObject, HttpStatus.OK);

						}
					} catch (DosDataNotFoundException e) {
						log.warn("data not found for objectId:{}", objectId);
						return new ResponseEntity<>(HttpStatus.NOT_FOUND);
					} finally {
						irodsSession.closeSession();
					}

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

	public DosConfiguration getDosConfiguration() {
		return dosConfiguration;
	}

	public void setDosConfiguration(DosConfiguration dosConfiguration) {
		this.dosConfiguration = dosConfiguration;
	}

	public IRODSSession getIrodsSession() {
		return irodsSession;
	}

	public void setIrodsSession(IRODSSession irodsSession) {
		this.irodsSession = irodsSession;
	}

}
