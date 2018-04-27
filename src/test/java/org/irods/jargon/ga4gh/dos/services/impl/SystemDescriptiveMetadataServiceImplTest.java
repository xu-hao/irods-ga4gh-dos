package org.irods.jargon.ga4gh.dos.services.impl;

import java.util.Properties;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.CollectionAO;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.domain.AvuData;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.pub.io.IRODSFileFactory;
import org.irods.jargon.ga4gh.dos.services.SystemDescriptiveMetadataService;
import org.irods.jargon.ga4gh.dos.services.metadata.SystemDescriptiveMetadata;
import org.irods.jargon.testutils.IRODSTestSetupUtilities;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.irods.jargon.testutils.filemanip.ScratchFileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class SystemDescriptiveMetadataServiceImplTest {

	private static Properties testingProperties = new Properties();
	private static TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	private static ScratchFileUtils scratchFileUtils = null;
	public static final String IRODS_TEST_SUBDIR_PATH = "SystemDescriptiveMetadataServiceImplTest";
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
	public void testInitializeSystemDescriptiveMetadata() throws Exception {
		String testDirName = "testInitializeSystemDescriptiveMetadata";

		String targetIrodsCollection = testingPropertiesHelper.buildIRODSCollectionAbsolutePathFromTestProperties(
				testingProperties, IRODS_TEST_SUBDIR_PATH + '/' + testDirName);

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		IRODSAccessObjectFactory accessObjectFactory = irodsFileSystem.getIRODSAccessObjectFactory();

		IRODSFileFactory irodsFileFactory = irodsFileSystem.getIRODSFileFactory(irodsAccount);

		IRODSFile dirFile = irodsFileFactory.instanceIRODSFile(targetIrodsCollection);
		dirFile.deleteWithForceOption();
		dirFile.mkdirs();

		CollectionAO collectionAO = accessObjectFactory.getCollectionAO(irodsAccount);

		AvuData dataToAdd = AvuData.instance("contact_name", "Jane Does",
				SystemDescriptiveMetadataService.SYSTEM_AVU_UNIT);
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		dataToAdd = AvuData.instance("repo_license_name", "cc0 Attribution v4.0 International",
				SystemDescriptiveMetadataService.SYSTEM_AVU_UNIT);
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);
		dataToAdd = AvuData.instance("contact_form", "n/a", SystemDescriptiveMetadataService.SYSTEM_AVU_UNIT);
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);
		dataToAdd = AvuData.instance("manifest", "/Manifest.prototype.tsv",
				SystemDescriptiveMetadataService.SYSTEM_AVU_UNIT);
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);
		dataToAdd = AvuData.instance("terms_of_service", "/tos.html", SystemDescriptiveMetadataService.SYSTEM_AVU_UNIT);
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);
		dataToAdd = AvuData.instance("landing_page", "/about.html", SystemDescriptiveMetadataService.SYSTEM_AVU_UNIT);
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);
		dataToAdd = AvuData.instance("supported_scoring_engines", "prototype",
				SystemDescriptiveMetadataService.SYSTEM_AVU_UNIT);
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);
		dataToAdd = AvuData.instance("repo_license_URL", "https://creativecommons.org/licenses/by/4.0/legalcode",
				SystemDescriptiveMetadataService.SYSTEM_AVU_UNIT);
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);
		dataToAdd = AvuData.instance("contact_email", "jdoe@email.unc.edu",
				SystemDescriptiveMetadataService.SYSTEM_AVU_UNIT);
		collectionAO.addAVUMetadata(targetIrodsCollection, dataToAdd);

		SystemDescriptiveMetadataService sds = new SystemDescriptiveMetadataServiceImpl(accessObjectFactory,
				irodsAccount);
		SystemDescriptiveMetadata md = sds.initializeSystemDescriptiveMetadata();
		Assert.assertNotNull("null metadata", md);
		Assert.assertFalse("no md", md.getKvps().isEmpty());

	}

}
