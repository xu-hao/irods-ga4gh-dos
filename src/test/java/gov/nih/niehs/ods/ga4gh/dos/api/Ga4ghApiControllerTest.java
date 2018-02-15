package gov.nih.niehs.ods.ga4gh.dos.api;

import java.util.Properties;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.connection.auth.AuthResponse;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.extensions.datatyper.DataTyperSettings;
import org.irods.jargon.rest.security.IrodsAuthentication;
import org.irods.jargon.testutils.IRODSTestSetupUtilities;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.irods.jargon.testutils.filemanip.FileGenerator;
import org.irods.jargon.testutils.filemanip.ScratchFileUtils;
import org.irodsext.datatyper.IrodsextDataTypeResolutionServiceFactoryImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import gov.nih.niehs.ods.ga4gh.dos.model.Ga4ghGetDataObjectResponse;
import gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfig;
import gov.nih.niehs.ods.ga4gh.services.DataObjectService;
import gov.nih.niehs.ods.ga4gh.services.IdTranslationServiceFactory;
import gov.nih.niehs.ods.ga4gh.services.impl.IrodsDataObjectServiceFactory;
import gov.nih.niehs.ods.ga4gh.services.impl.IrodsIdTranslationServiceFactory;

public class Ga4ghApiControllerTest {

	private static Properties testingProperties = new Properties();
	private static TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	private static ScratchFileUtils scratchFileUtils = null;
	public static final String IRODS_TEST_SUBDIR_PATH = "Ga4ghApiControllerTest";
	private static IRODSTestSetupUtilities irodsTestSetupUtilities = null;
	private static IRODSFileSystem irodsFileSystem;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestingPropertiesHelper testingPropertiesLoader = new TestingPropertiesHelper();
		testingProperties = testingPropertiesLoader.getTestProperties();
		scratchFileUtils = new ScratchFileUtils(testingProperties);
		scratchFileUtils.clearAndReinitializeScratchDirectory(IRODS_TEST_SUBDIR_PATH);
		irodsTestSetupUtilities = new IRODSTestSetupUtilities();
		irodsTestSetupUtilities.initializeIrodsScratchDirectory();
		irodsTestSetupUtilities.initializeDirectoryForTest(IRODS_TEST_SUBDIR_PATH);
		irodsFileSystem = IRODSFileSystem.instance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		irodsFileSystem.closeAndEatExceptions();
	}

	@After
	public void afterEach() throws Exception {
		irodsFileSystem.closeAndEatExceptions();
	}

	@Test
	public void testGetDataObject() throws Exception {
		// generate a local scratch file
		String testFileName = "testGetDataObject.txt";
		String absPath = scratchFileUtils.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH);
		String localFileName = FileGenerator.generateFileOfFixedLengthGivenName(absPath, testFileName, 1);

		String targetIrodsFile = testingPropertiesHelper.buildIRODSCollectionAbsolutePathFromTestProperties(
				testingProperties, IRODS_TEST_SUBDIR_PATH + '/' + testFileName);
		// now put the file
		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);
		DataTransferOperations dto = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);

		dto.putOperation(localFileName, targetIrodsFile, "", null, null);

		DataTyperSettings dataTyperSettings = new DataTyperSettings();
		DosConfig dosConfig = new DosConfig();
		dosConfig.setUrlPrefix("https://localhost/emc-metalnx-irods/collectionInfo");

		IrodsextDataTypeResolutionServiceFactoryImpl dataTypeResolutionServiceFactory = new IrodsextDataTypeResolutionServiceFactoryImpl();
		dataTypeResolutionServiceFactory.setIrodsAccessObjectFactory(irodsFileSystem.getIRODSAccessObjectFactory());
		dataTypeResolutionServiceFactory.setDataTyperSettings(dataTyperSettings);

		IrodsDataObjectServiceFactory dataObjectServiceFactory = new IrodsDataObjectServiceFactory();
		dataObjectServiceFactory.setDosConfiguration(dosConfig);
		dataObjectServiceFactory.setIrodsAccessObjectFactory(irodsFileSystem.getIRODSAccessObjectFactory());
		dataObjectServiceFactory.setDataTypeResolutionServiceFactory(dataTypeResolutionServiceFactory);

		DataObjectService dos = dataObjectServiceFactory.instance(irodsAccount);

		IdTranslationServiceFactory idTranslationServiceFactory = new IrodsIdTranslationServiceFactory();
		idTranslationServiceFactory.setDosConfiguration(dosConfig);
		idTranslationServiceFactory.setIrodsAccessObjectFactory(irodsFileSystem.getIRODSAccessObjectFactory());

		Ga4ghApiController ga4ghApiController = new Ga4ghApiController();
		ga4ghApiController.setDataObjectServiceFactory(dataObjectServiceFactory);
		ga4ghApiController.setIdTranslationServiceFactory(idTranslationServiceFactory);
		AuthResponse resp = irodsFileSystem.getIRODSAccessObjectFactory().authenticateIRODSAccount(irodsAccount);
		IrodsAuthentication auth = new IrodsAuthentication(irodsAccount, resp);
		SecurityContextHolder.getContext().setAuthentication(auth);

		ResponseEntity<Ga4ghGetDataObjectResponse> actual = ga4ghApiController.getDataObject(targetIrodsFile, null);
		Assert.assertNotNull("no response", actual);

	}

}
