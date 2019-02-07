package gov.nih.niehsods.ga4gh.dos.test.functional;

import java.net.URI;
import java.util.Properties;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.pub.io.IRODSFileFactory;
import org.irods.jargon.extensions.datatyper.DataTyperSettings;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.JargonDosConfiguration;
import org.irods.jargon.ga4gh.dos.model.Ga4ghGetDataBundleResponse;
import org.irods.jargon.ga4gh.dos.services.impl.GuidService;
import org.irods.jargon.ga4gh.dos.services.impl.GuidServiceImpl;
import org.irods.jargon.testutils.IRODSTestSetupUtilities;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.irods.jargon.testutils.filemanip.FileGenerator;
import org.irods.jargon.testutils.filemanip.ScratchFileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test obtaining data bundle and data object as a functional test
 * 
 * @author Mike Conway - NIEHS
 *
 */
@ContextConfiguration(classes = { JargonDosConfiguration.class, DosConfiguration.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@DirtiesContext

public class IrodsMetadataGetTest {

	private static Properties testingProperties = new Properties();
	private static TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	private static ScratchFileUtils scratchFileUtils = null;
	public static final String IRODS_TEST_SUBDIR_PATH = "IrodsMetadataGetTest";
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

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testMainPage() throws Exception {

		IRODSAccount userAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		ResponseEntity<String> entity = this.restTemplate
				.withBasicAuth(userAccount.getUserName(), userAccount.getPassword())
				.getForEntity("/swagger-ui.html", String.class);
		Assert.assertTrue(entity.getStatusCode() == HttpStatus.OK);
	}

	@Test
	public void testGetDataObjectBundle() throws Exception {
		String testDirName = "testGetDataObjectBundle";
		String collAttribName = GuidService.GUID_ATTRIBUTE;
		String collAttribValue = UUID.randomUUID().toString();

		String targetIrodsCollection = testingPropertiesHelper.buildIRODSCollectionAbsolutePathFromTestProperties(
				testingProperties, IRODS_TEST_SUBDIR_PATH + '/' + testDirName);

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem.getIRODSAccessObjectFactory();

		IRODSFileFactory irodsFileFactory = irodsFileSystem.getIRODSFileFactory(irodsAccount);

		IRODSFile dirFile = irodsFileFactory.instanceIRODSFile(targetIrodsCollection);
		dirFile.mkdirs();

		CollectionAO collectionAO = accessObjectFactory.getCollectionAO(irodsAccount);

		AvuData dataToAdd = AvuData.instance(collAttribName, collAttribValue, "");
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		// add some data objects with GUIDS

		String testFileName = "testGetDataObjectBundle.txt";
		String absPath = scratchFileUtils.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH);
		String localFileName = FileGenerator.generateFileOfFixedLengthGivenName(absPath, testFileName, 1);

		String targetIrodsFile = targetIrodsCollection + '/' + testFileName;
		// now put the file
		DataTransferOperations dto = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);

		dto.putOperation(localFileName, targetIrodsFile, "", null, null);

		GuidService guidService = new GuidServiceImpl(irodsFileSystem.getIRODSAccessObjectFactory(), irodsAccount);
		guidService.createGuidOnDataObject(targetIrodsFile);

		String testFileName2 = "testGetDataObjectBundle2.txt";
		absPath = scratchFileUtils.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH);
		localFileName = FileGenerator.generateFileOfFixedLengthGivenName(absPath, testFileName, 1);

		targetIrodsFile = targetIrodsCollection + '/' + testFileName2;
		// now put the file

		dto.putOperation(localFileName, targetIrodsFile, "", null, null);
		guidService.createGuidOnDataObject(targetIrodsFile);
		DataTyperSettings dataTyperSettings = new DataTyperSettings();
		DosConfiguration dosConfig = new DosConfiguration();
		dosConfig.setUrlPrefix("https://localhost/emc-metalnx-irods/collectionInfo");

		IRODSAccount userAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);
		ParameterizedTypeReference<Ga4ghGetDataBundleResponse> parameterizedTypeReference = new ParameterizedTypeReference<Ga4ghGetDataBundleResponse>() {
		};

		String it = "http://localhost:8080/ga4gh/dos/v1/databundles/00fea60a-e3b3-409f-9973-65b8a79db98b";
		CredentialsProvider provider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("test1", "test");
		provider.setCredentials(AuthScope.ANY, credentials);

		HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();

		URI url = new URI(it);// ("http://localhost:8080/ga4gh/dos/v1/databundles/" + collAttribValue);
		HttpGet get = new HttpGet(url);
		get.setHeader("Accept", "application/json");
		HttpResponse response = client.execute(get);
		int statusCode = response.getStatusLine().getStatusCode();
		Assert.assertNotNull(response);
		Assert.assertTrue(statusCode == 0);

		/*
		 * ResponseEntity<Ga4ghGetDataBundleResponse> entity = this.restTemplate
		 * .withBasicAuth(userAccount.getUserName(), userAccount.getPassword()).
		 * .exchange("ga4gh/dos/v1/databundles/", HttpMethod.GET, null,
		 * parameterizedTypeReference); Assert.assertTrue(entity.getStatusCode() ==
		 * HttpStatus.OK);
		 */
	}

}
