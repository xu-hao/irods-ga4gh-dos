package org.irods.jargon.ga4gh.dos.services.impl;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.pub.io.IRODSFileFactory;
import org.irods.jargon.testutils.IRODSTestSetupUtilities;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.irods.jargon.testutils.filemanip.FileGenerator;
import org.irods.jargon.testutils.filemanip.ScratchFileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IrodsBundleManagementServiceTest {

	private static Properties testingProperties = new Properties();
	private static TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	private static ScratchFileUtils scratchFileUtils = null;
	public static final String IRODS_TEST_SUBDIR_PATH = "IrodsBundleManagementServiceTest";
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
	public void testRetrieveDataBundleFromId() {

	}

	@Test
	public void testRetrieveDataObjectsInBundle() throws Exception {
		String testDirName = "testRetrieveDataObjectsInBundle";
		String collAttribName = GuidService.GUID_ATTRIBUTE;
		String collAttribValue = UUID.randomUUID().toString();

		String targetIrodsCollection = testingPropertiesHelper.buildIRODSCollectionAbsolutePathFromTestProperties(
				testingProperties, IRODS_TEST_SUBDIR_PATH + '/' + testDirName);

		String bundleParent = targetIrodsCollection;

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem.getIRODSAccessObjectFactory();

		IRODSFileFactory irodsFileFactory = irodsFileSystem.getIRODSFileFactory(irodsAccount);

		IRODSFile dirFile = irodsFileFactory.instanceIRODSFile(targetIrodsCollection);
		dirFile.mkdirs();

		CollectionAO collectionAO = accessObjectFactory.getCollectionAO(irodsAccount);

		AvuData dataToAdd = AvuData.instance(collAttribName, collAttribValue, "");
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		// add some data objects with GUIDS

		String testFileName = "testGa4ghDataObjectFromIrodsDataObject.txt";
		String absPath = scratchFileUtils.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH);
		String localFileName = FileGenerator.generateFileOfFixedLengthGivenName(absPath, testFileName, 1);

		String targetIrodsFile = targetIrodsCollection + '/' + testFileName;
		// now put the file
		DataTransferOperations dto = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);

		dto.putOperation(localFileName, targetIrodsFile, "", null, null);

		GuidService guidService = new GuidServiceImpl(irodsFileSystem.getIRODSAccessObjectFactory(), irodsAccount);
		guidService.createGuidOnDataObject(targetIrodsFile);

		String testFileName2 = "testGa4ghDataObjectFromIrodsDataObject2.txt";
		absPath = scratchFileUtils.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH);
		localFileName = FileGenerator.generateFileOfFixedLengthGivenName(absPath, testFileName, 1);

		targetIrodsFile = targetIrodsCollection + '/' + testFileName2;
		// now put the file

		dto.putOperation(localFileName, targetIrodsFile, "", null, null);
		guidService.createGuidOnDataObject(targetIrodsFile);

		IrodsBundleManagementService bundleManagementService = new IrodsBundleManagementService(accessObjectFactory,
				irodsAccount);

		List<BundleObjectRollup> rollup = bundleManagementService.retrieveDataObjectsInBundle(bundleParent);
		Assert.assertNotNull(rollup);

	}

}
