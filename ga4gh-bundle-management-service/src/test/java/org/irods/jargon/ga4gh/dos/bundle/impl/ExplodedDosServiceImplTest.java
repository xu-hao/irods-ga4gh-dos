package org.irods.jargon.ga4gh.dos.bundle.impl;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.pub.io.IRODSFileFactory;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsAccessMethod;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataBundle;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataObject;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.DosBundleManagementService;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.ga4gh.dos.model.BundleObject.TypeEnum;
import org.irods.jargon.ga4gh.dos.utils.ExplodedBundleMetadataUtils;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.irods.jargon.testutils.filemanip.FileGenerator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExplodedDosServiceImplTest {

	private static Properties testingProperties = new Properties();
	private static org.irods.jargon.testutils.TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	private static org.irods.jargon.testutils.filemanip.ScratchFileUtils scratchFileUtils = null;
	public static final String IRODS_TEST_SUBDIR_PATH = "ExplodedDosServiceImplTest";
	private static org.irods.jargon.testutils.IRODSTestSetupUtilities irodsTestSetupUtilities = null;
	private static IRODSFileSystem irodsFileSystem = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.irods.jargon.testutils.TestingPropertiesHelper testingPropertiesLoader = new TestingPropertiesHelper();
		testingProperties = testingPropertiesLoader.getTestProperties();
		scratchFileUtils = new org.irods.jargon.testutils.filemanip.ScratchFileUtils(testingProperties);
		irodsTestSetupUtilities = new org.irods.jargon.testutils.IRODSTestSetupUtilities();
		irodsTestSetupUtilities.initializeIrodsScratchDirectory();
		irodsTestSetupUtilities.initializeDirectoryForTest(IRODS_TEST_SUBDIR_PATH);
		irodsFileSystem = IRODSFileSystem.instance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		irodsFileSystem.closeAndEatExceptions();
	}

	@Test
	public void testRetrieveDataBundle() throws Exception {
		String bundleDir = "testRetrieveDataBundle";

		String localCollectionAbsolutePath = scratchFileUtils
				.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH + '/' + bundleDir);

		String irodsCollectionRootAbsolutePath = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(testingProperties, IRODS_TEST_SUBDIR_PATH);

		FileGenerator.generateManyFilesAndCollectionsInParentCollectionByAbsolutePath(localCollectionAbsolutePath,
				"testRetrieveDataBundle", 3, 5, 3, "testFile", ".txt", 10, 9, 20, 200);

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSFileFactory irodsFileFactory = irodsFileSystem.getIRODSFileFactory(irodsAccount);
		IRODSFile destFile = irodsFileFactory.instanceIRODSFile(irodsCollectionRootAbsolutePath);
		DataTransferOperations dataTransferOperationsAO = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);
		File localFile = new File(localCollectionAbsolutePath);

		dataTransferOperationsAO.putOperation(localFile, destFile, null, null);
		// bundle it up

		String bundleRoot = irodsCollectionRootAbsolutePath + "/" + bundleDir;
		DosConfiguration dosConfiguration = new DosConfiguration();
		dosConfiguration.setDrsRestUrlEndpoint("http://www.example.com/rest/fileStream?path=");
		DosServiceFactory factory = new ExplodedDosServiceFactoryImpl(irodsFileSystem.getIRODSAccessObjectFactory());
		factory.setDosConfiguration(dosConfiguration);

		DosBundleManagementService bundleManagementService = factory.instanceDosBundleManagementService(irodsAccount);
		DosService dosService = factory.instanceDosService(irodsAccount);
		String guid = bundleManagementService.createDataBundle(bundleRoot);

		IrodsDataBundle bundle = dosService.retrieveDataBundle(guid);
		Assert.assertNotNull("null bundle", bundle);
		Assert.assertEquals("guid incorrect", guid, bundle.getBundleUuid());
		Assert.assertEquals("path missing", bundleRoot, bundle.getIrodsAbsolutePath());
		Assert.assertFalse("missing checksum type", bundle.getBundleChecksumType().isEmpty());
		Assert.assertFalse("missing bundle checksum", bundle.getBundleChecksum().isEmpty());
		Assert.assertFalse("no bundle data objects", bundle.getDataObjects().isEmpty());
	}

	@Test
	public void testRetrieveDataObject() throws Exception {
		String bundleDir = "testRetrieveDataObject";
		String testFileName = "testRetrieveDataObject.txt";

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		String irodsCollectionRootAbsolutePath = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(testingProperties, IRODS_TEST_SUBDIR_PATH);

		String localCollectionAbsolutePath = scratchFileUtils
				.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH + '/' + bundleDir);

		FileGenerator.generateFileOfFixedLengthGivenName(localCollectionAbsolutePath, testFileName, 1);

		DataTransferOperations dto = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);
		dto.putOperation(localCollectionAbsolutePath, irodsCollectionRootAbsolutePath,
				irodsAccount.getDefaultStorageResource(), null, null);

		// bundle it up

		String bundleRoot = irodsCollectionRootAbsolutePath + "/" + bundleDir;
		DosConfiguration dosConfiguration = new DosConfiguration();
		dosConfiguration.setDrsRestUrlEndpoint("http://www.example.com/rest/fileStream?path=");
		DosServiceFactory factory = new ExplodedDosServiceFactoryImpl(irodsFileSystem.getIRODSAccessObjectFactory());
		factory.setDosConfiguration(dosConfiguration);

		DosBundleManagementService bundleManagementService = factory.instanceDosBundleManagementService(irodsAccount);
		DosService dosService = factory.instanceDosService(irodsAccount);
		String guid = bundleManagementService.createDataBundle(bundleRoot);

		IrodsDataBundle bundle = dosService.retrieveDataBundle(guid);
		/* should be one file, retrieve it */

		Assert.assertFalse("data object not found", bundle.getDataObjects().isEmpty());
		IrodsDataObject irodsDataObject = bundle.getDataObjects().get(0);

		/* get the guid and retrieve data object via service */

		IrodsDataObject actual = dosService.retrieveDataObject(irodsDataObject.getGuid());
		Assert.assertNotNull("null irodsDataObject", actual);
		Assert.assertEquals(testFileName, actual.getFileName());
		Assert.assertFalse("no guid", irodsDataObject.getGuid().isEmpty());
		Assert.assertFalse("no absPath", irodsDataObject.getAbsolutePath().isEmpty());
		Assert.assertFalse("no access methods", irodsDataObject.getIrodsAccessMethods().isEmpty());

	}

	@Test
	public void testListDataObjectids() throws Exception {
		// create a dir with files

		String bundleDir = "testListDataObjectids";

		String localCollectionAbsolutePath = scratchFileUtils
				.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH + '/' + bundleDir);

		String irodsCollectionRootAbsolutePath = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(testingProperties, IRODS_TEST_SUBDIR_PATH);

		FileGenerator.generateManyFilesAndCollectionsInParentCollectionByAbsolutePath(localCollectionAbsolutePath,
				"testListDataObjectids", 2, 3, 2, "testFile", ".txt", 5, 4, 2, 3);

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSFileFactory irodsFileFactory = irodsFileSystem.getIRODSFileFactory(irodsAccount);
		IRODSFile destFile = irodsFileFactory.instanceIRODSFile(irodsCollectionRootAbsolutePath);
		DataTransferOperations dataTransferOperationsAO = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);
		File localFile = new File(localCollectionAbsolutePath);

		dataTransferOperationsAO.putOperation(localFile, destFile, null, null);
		// bundle it up

		String bundleRoot = irodsCollectionRootAbsolutePath + "/" + bundleDir;
		DosConfiguration dosConfiguration = new DosConfiguration();
		dosConfiguration.setDrsRestUrlEndpoint("http://www.example.com/rest/fileStream?path=");
		DosServiceFactory factory = new ExplodedDosServiceFactoryImpl(irodsFileSystem.getIRODSAccessObjectFactory());
		factory.setDosConfiguration(dosConfiguration);

		DosBundleManagementService bundleManagementService = factory.instanceDosBundleManagementService(irodsAccount);
		DosService dosService = factory.instanceDosService(irodsAccount);
		String guid = bundleManagementService.createDataBundle(bundleRoot);
		Assert.assertNotNull("no guid returned", guid);

		List<IrodsDataObject> dataBundles = dosService.retrieveDataObjectsInBundle(guid,
				dosConfiguration.getDrsRestUrlEndpoint());
		Assert.assertFalse("empty bundle list", dataBundles.isEmpty());

		IrodsDataObject actual = dataBundles.get(0);
		Assert.assertFalse("no abspath", actual.getAbsolutePath().isEmpty());
		Assert.assertFalse("no guid", actual.getGuid().isEmpty());
		Assert.assertFalse("no file name", actual.getFileName().isEmpty());
		Assert.assertEquals("wrong object type", TypeEnum.OBJECT, actual.getType());

		IrodsAccessMethod irodsAccessMethod = dataBundles.get(0).getIrodsAccessMethods().get(0);
		Assert.assertEquals("/objects/" + actual.getGuid(), irodsAccessMethod.getUrl());

	}

	@Test
	public void testDataObjectIdToIrodsPath() throws Exception {
		// create a dir with files

		String testFileName = "testDataObjectIdToIrodsPath.dat";
		String guid = "testDataObjectIdToIrodsPath";
		String absPath = scratchFileUtils.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH);
		String localFileName = FileGenerator.generateFileOfFixedLengthGivenName(absPath, testFileName, 10);

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		// put scratch file into irods in the right place on the first resource

		String targetIrodsCollection = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(testingProperties, IRODS_TEST_SUBDIR_PATH);

		String dataObjectAbsPath = targetIrodsCollection + '/' + testFileName;

		DataTransferOperations dto = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);
		dto.putOperation(localFileName, targetIrodsCollection, irodsAccount.getDefaultStorageResource(), null, null);

		DataObjectAO dataObjectAO = irodsFileSystem.getIRODSAccessObjectFactory().getDataObjectAO(irodsAccount);

		AvuData avuData = ExplodedBundleMetadataUtils.createBundleDataObjectMarkerAvu(guid);

		dataObjectAO.deleteAVUMetadata(dataObjectAbsPath, avuData);
		dataObjectAO.addAVUMetadata(dataObjectAbsPath, avuData);

		DosConfiguration dosConfiguration = new DosConfiguration();
		dosConfiguration.setDrsRestUrlEndpoint("http://www.example.com/rest/fileStream?path=");
		DosServiceFactory factory = new ExplodedDosServiceFactoryImpl(irodsFileSystem.getIRODSAccessObjectFactory());
		factory.setDosConfiguration(dosConfiguration);

		DosService dosService = factory.instanceDosService(irodsAccount);
		String actual = dosService.dataObjectIdToIrodsPath(guid);
		Assert.assertEquals("wrong path", targetIrodsCollection + "/" + testFileName, actual);
	}

}
