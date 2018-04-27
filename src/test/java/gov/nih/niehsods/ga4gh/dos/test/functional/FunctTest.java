package gov.nih.niehsods.ga4gh.dos.test.functional;

import java.io.StringWriter;
import java.net.URI;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
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
import org.irods.jargon.ga4gh.dos.model.Ga4ghGetDataBundleResponse;
import org.irods.jargon.ga4gh.dos.services.impl.GuidService;
import org.irods.jargon.ga4gh.dos.services.impl.GuidServiceImpl;
import org.irods.jargon.testutils.IRODSTestSetupUtilities;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.irods.jargon.testutils.filemanip.FileGenerator;
import org.irods.jargon.testutils.filemanip.ScratchFileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;

public class FunctTest {

	private static Properties testingProperties = new Properties();
	private static TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	private static ScratchFileUtils scratchFileUtils = null;
	public static final String IRODS_TEST_SUBDIR_PATH = "FunctTest";
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

		// add custom metadata

		dataToAdd = AvuData.instance("version", "prototype", "");
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		dataToAdd = AvuData.instance("derived_from", "1111", "");
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		dataToAdd = AvuData.instance("object_schema", "some schema", "");
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		dataToAdd = AvuData.instance("contact", "Jane Doe", "");
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		dataToAdd = AvuData.instance("persistence", "/persistence.html", "");
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		dataToAdd = AvuData.instance("keywords", "thyroid", "");
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		dataToAdd = AvuData.instance("license", "cc0 Attribution v4.0 International", "");
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		dataToAdd = AvuData.instance("cited_by", "1011,1213", "");
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

		String it = "http://localhost:8080/ga4gh/dos/v1/databundles/" + collAttribValue;
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
		Assert.assertTrue(statusCode == 200);
		StringWriter writer = new StringWriter();
		IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
		String json = writer.toString();
		Assert.assertNotNull(json);

	}
}
