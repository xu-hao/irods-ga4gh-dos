package org.irods.jargon.ga4gh.dos.bundlemgmnt.impl;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.pub.io.IRODSFileFactory;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.DosBundleManagementService;
import org.irods.jargon.ga4gh.dos.utils.ExplodedBundleMetadataUtils;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.irods.jargon.testutils.filemanip.FileGenerator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExplodedDosBundleManagementServiceImplTest {

	private static Properties testingProperties = new Properties();
	private static org.irods.jargon.testutils.TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	private static org.irods.jargon.testutils.filemanip.ScratchFileUtils scratchFileUtils = null;
	public static final String IRODS_TEST_SUBDIR_PATH = "ExplodedDosBundleManagementServiceImplTest";
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
	public void testCreateDataBundle() throws Exception {
		// create a dir with files

		String bundleDir = "testCreateDataBundle";

		String localCollectionAbsolutePath = scratchFileUtils
				.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH + '/' + bundleDir);

		String irodsCollectionRootAbsolutePath = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(testingProperties, IRODS_TEST_SUBDIR_PATH);

		FileGenerator.generateManyFilesAndCollectionsInParentCollectionByAbsolutePath(localCollectionAbsolutePath,
				"testPutCollectionWithTwoFiles", 3, 5, 3, "testFile", ".txt", 10, 9, 20, 200);

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSFileFactory irodsFileFactory = irodsFileSystem.getIRODSFileFactory(irodsAccount);
		IRODSFile destFile = irodsFileFactory.instanceIRODSFile(irodsCollectionRootAbsolutePath);
		DataTransferOperations dataTransferOperationsAO = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);
		File localFile = new File(localCollectionAbsolutePath);

		dataTransferOperationsAO.putOperation(localFile, destFile, null, null);
		// bundle it up

		String bundleRoot = irodsCollectionRootAbsolutePath + "/" + bundleDir;

		DosBundleManagementService explodedDosService = new ExplodedDosBundleManagementServiceImpl(
				irodsFileSystem.getIRODSAccessObjectFactory(), irodsAccount);
		String guid = explodedDosService.createDataBundle(bundleRoot);
		Assert.assertNotNull("no guid returned", guid);

		// check assertions

		CollectionAO collectionAO = irodsFileSystem.getIRODSAccessObjectFactory().getCollectionAO(irodsAccount);
		List<MetaDataAndDomainData> avus = collectionAO.findMetadataValuesForCollection(bundleRoot);

		boolean guidFound = false;
		boolean checksumFound = false;
		for (MetaDataAndDomainData avu : avus) {
			if (avu.getAvuAttribute().equals(ExplodedBundleMetadataUtils.GA4GH_BUNDLE_ID_ATTRIBUTE)) {
				guidFound = true;
				Assert.assertFalse("did not find guid", avu.getAvuValue().isEmpty());
				Assert.assertEquals("did not properly set data bundle id guid",
						ExplodedBundleMetadataUtils.GA4GH_BUNDLE_UNIT_PREFIX, avu.getAvuUnit());
				Assert.assertEquals("did not return same guid that was saved", guid, avu.getAvuValue());
			} else if (avu.getAvuAttribute().equals(ExplodedBundleMetadataUtils.GA4GH_BUNDLE_CHECKSUM_ATTRIBUTE)) {
				checksumFound = true;
				Assert.assertFalse("did not find checksum", avu.getAvuValue().isEmpty());
				Assert.assertEquals("did not properly set checksum unit",
						ExplodedBundleMetadataUtils.GA4GH_BUNDLE_UNIT_PREFIX, avu.getAvuUnit());
				Assert.assertFalse("did not return checksum", avu.getAvuValue().isEmpty());
			}
		}

		Assert.assertTrue("did not find guid for bundle", guidFound);
		Assert.assertTrue("did not find checksum for bundle", checksumFound);

		// Constituent data objects are tested in the visitor test suite

	}

	@Test
	public void testBundleIdToIrodsPath() throws Exception {

		// create a dir with files

		String bundleDir = "testBundleIdToIrodsPath";

		String localCollectionAbsolutePath = scratchFileUtils
				.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH + '/' + bundleDir);

		String irodsCollectionRootAbsolutePath = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(testingProperties, IRODS_TEST_SUBDIR_PATH);

		FileGenerator.generateManyFilesAndCollectionsInParentCollectionByAbsolutePath(localCollectionAbsolutePath,
				"testPutCollectionWithTwoFiles", 1, 2, 3, "testFile", ".txt", 2, 2, 20, 25);

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSFileFactory irodsFileFactory = irodsFileSystem.getIRODSFileFactory(irodsAccount);
		IRODSFile destFile = irodsFileFactory.instanceIRODSFile(irodsCollectionRootAbsolutePath);
		DataTransferOperations dataTransferOperationsAO = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);
		File localFile = new File(localCollectionAbsolutePath);

		dataTransferOperationsAO.putOperation(localFile, destFile, null, null);
		// bundle it up

		String bundleRoot = irodsCollectionRootAbsolutePath + "/" + bundleDir;

		DosBundleManagementService explodedDosService = new ExplodedDosBundleManagementServiceImpl(
				irodsFileSystem.getIRODSAccessObjectFactory(), irodsAccount);
		String guid = explodedDosService.createDataBundle(bundleRoot);

		String actual = explodedDosService.bundleIdToIrodsPath(guid);
		Assert.assertEquals("did not get path back for bundle", bundleRoot, actual);

	}

	@Test
	public void testDeleteDataBundle() throws Exception {
		// create a dir with files

		String bundleDir = "testDeleteDataBundle";

		String localCollectionAbsolutePath = scratchFileUtils
				.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH + '/' + bundleDir);

		String irodsCollectionRootAbsolutePath = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(testingProperties, IRODS_TEST_SUBDIR_PATH);

		FileGenerator.generateManyFilesAndCollectionsInParentCollectionByAbsolutePath(localCollectionAbsolutePath,
				"testDeleteDataBundle", 3, 5, 3, "testFile", ".txt", 3, 2, 2, 3);

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSFileFactory irodsFileFactory = irodsFileSystem.getIRODSFileFactory(irodsAccount);
		IRODSFile destFile = irodsFileFactory.instanceIRODSFile(irodsCollectionRootAbsolutePath);
		DataTransferOperations dataTransferOperationsAO = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);
		File localFile = new File(localCollectionAbsolutePath);

		dataTransferOperationsAO.putOperation(localFile, destFile, null, null);
		// bundle it up

		String bundleRoot = irodsCollectionRootAbsolutePath + "/" + bundleDir;

		DosBundleManagementService explodedDosService = new ExplodedDosBundleManagementServiceImpl(
				irodsFileSystem.getIRODSAccessObjectFactory(), irodsAccount);
		String guid = explodedDosService.createDataBundle(bundleRoot);
		Assert.assertNotNull("null guid", guid);
		explodedDosService.deleteDataBundle(guid);
	}

	@Test
	public void testDetermineMessageDigestFromIrods() {
		fail("Not yet implemented");
	}

	@Test
	public void testChecksumAndMarkObjectsInBundle() {
		fail("Not yet implemented");
	}

}
