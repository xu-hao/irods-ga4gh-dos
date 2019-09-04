package org.irods.jargon.ga4gh.dos.bundle.impl;

import java.util.Properties;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.pub.io.IRODSFileFactory;
import org.irods.jargon.ga4gh.dos.bundle.DosService;
import org.irods.jargon.ga4gh.dos.bundle.DosServiceFactory;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.BundleInfoAndPath;
import org.irods.jargon.ga4gh.dos.bundle.internalmodel.IrodsDataBundle;
import org.irods.jargon.ga4gh.dos.bundlemgmnt.DosBundleManagementService;
import org.irods.jargon.ga4gh.dos.configuration.DosConfiguration;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class DemoFunctionalTest {

	private static Properties testingProperties = new Properties();
	private static org.irods.jargon.testutils.TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	private static org.irods.jargon.testutils.filemanip.ScratchFileUtils scratchFileUtils = null;
	public static final String IRODS_TEST_SUBDIR_PATH = "DemoFunctionalTest";
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
	public void dummy() {

	}

	@Ignore
	public void testRetrieveDataBundle() throws Exception {
		String bundleDir = "/tempZone/home/drs/testbundle1";

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		// IRODSAccount irodsAccount = IRODSAccount.instance("107.23.1.37", 1247, "drs",
		// "LookAtTheBigBrainOnDrs", "",
		// "tempZone", "");

		IRODSFileFactory irodsFileFactory = irodsFileSystem.getIRODSFileFactory(irodsAccount);
		IRODSFile destFile = irodsFileFactory.instanceIRODSFile(bundleDir);

		// IRODSAccount testLoginAccount = IRODSAccount.instance(dosConfiguraiton, port,
		// userName, password, homeDirectory, zone, defaultStorageResource)

		String bundleRoot = bundleDir;
		DosConfiguration dosConfiguration = new DosConfiguration();
		dosConfiguration.setDrsRestUrlEndpoint("http://www.example.com/rest/fileStream?path=");
		DosServiceFactory factory = new ExplodedDosServiceFactoryImpl(irodsFileSystem.getIRODSAccessObjectFactory());
		factory.setDosConfiguration(dosConfiguration);

		DosBundleManagementService bundleManagementService = factory.instanceDosBundleManagementService(irodsAccount);
		DosService dosService = factory.instanceDosService(irodsAccount);
		String guid = bundleManagementService.createDataBundle(bundleRoot);

		BundleInfoAndPath bundleInfoAndPath = dosService.resolveId(guid);
		IrodsDataBundle bundle = dosService.retrieveDataBundle(bundleInfoAndPath);
		Assert.assertNotNull("null bundle", bundle);
		Assert.assertEquals("guid incorrect", guid, bundle.getBundleUuid());
		Assert.assertEquals("path missing", bundleRoot, bundle.getIrodsAbsolutePath());
		Assert.assertFalse("missing checksum type", bundle.getBundleChecksumType().isEmpty());
		Assert.assertFalse("missing bundle checksum", bundle.getBundleChecksum().isEmpty());
		Assert.assertFalse("no bundle data objects", bundle.getDataObjects().isEmpty());
	}

}
