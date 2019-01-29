package org.irods.jargon.ga4gh.dos.bundlemgmnt.impl;

import java.security.MessageDigest;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.binary.Hex;
import org.irods.jargon.core.checksum.ChecksumValue;
import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.DataObjectAO;
import org.irods.jargon.core.pub.DataObjectChecksumUtilitiesAO;
import org.irods.jargon.core.pub.DataTransferOperations;
import org.irods.jargon.core.pub.IRODSFileSystem;
import org.irods.jargon.core.pub.io.IRODSFile;
import org.irods.jargon.core.pub.io.IRODSFileImpl;
import org.irods.jargon.core.query.MetaDataAndDomainData;
import org.irods.jargon.datautils.visitor.HierLeaf;
import org.irods.jargon.datautils.visitor.IrodsVisitedLeaf;
import org.irods.jargon.ga4gh.dos.utils.ExplodedBundleMetadataUtils;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.irods.jargon.testutils.filemanip.FileGenerator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataBundleChecksumVisitorTest {

	private static Properties testingProperties = new Properties();
	private static org.irods.jargon.testutils.TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	private static org.irods.jargon.testutils.filemanip.ScratchFileUtils scratchFileUtils = null;
	public static final String IRODS_TEST_SUBDIR_PATH = "DataBundleChecksumVisitorTest";
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
	public void testVisitLeaf() throws Exception {

		String testFileName = "testVisitLeaf.dat";
		String absPath = scratchFileUtils.createAndReturnAbsoluteScratchPath(IRODS_TEST_SUBDIR_PATH);
		String localFileName = FileGenerator.generateFileOfFixedLengthGivenName(absPath, testFileName, 10);

		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);

		// put scratch file into irods in the right place on the first resource

		String targetIrodsCollection = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(testingProperties, IRODS_TEST_SUBDIR_PATH);

		String dataObjectAbsPath = targetIrodsCollection + '/' + testFileName;
		IRODSFile leafFile = irodsFileSystem.getIRODSFileFactory(irodsAccount).instanceIRODSFile(dataObjectAbsPath);

		DataTransferOperations dto = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataTransferOperations(irodsAccount);
		dto.putOperation(localFileName, targetIrodsCollection, irodsAccount.getDefaultStorageResource(), null, null);

		HierLeaf leaf = new IrodsVisitedLeaf((IRODSFileImpl) leafFile);
		DataBundleChecksumVisitor visitor = new DataBundleChecksumVisitor(irodsFileSystem.getIRODSAccessObjectFactory(),
				irodsAccount, MessageDigest.getInstance("SHA-256"));
		visitor.visit(leaf);

		DataObjectAO dataObjectAO = irodsFileSystem.getIRODSAccessObjectFactory().getDataObjectAO(irodsAccount);
		List<MetaDataAndDomainData> avus = dataObjectAO.findMetadataValuesForDataObject(dataObjectAbsPath);

		boolean guidFound = false;
		for (MetaDataAndDomainData avu : avus) {
			if (avu.getAvuAttribute().equals(ExplodedBundleMetadataUtils.GA4GH_DATA_OBJECT_ID_ATTRIBUTE)) {
				guidFound = true;
				Assert.assertFalse("did not find guid", avu.getAvuValue().isEmpty());
				Assert.assertEquals("did not properly set data bundle id guid",
						ExplodedBundleMetadataUtils.GA4GH_BUNDLE_UNIT_PREFIX, avu.getAvuUnit());
			}

		}

		// check the checksum and avu values
		Assert.assertTrue("did not find guid for data object", guidFound);
		DataObjectChecksumUtilitiesAO checksumUtils = irodsFileSystem.getIRODSAccessObjectFactory()
				.getDataObjectChecksumUtilitiesAO(irodsAccount);
		ChecksumValue value = checksumUtils.retrieveExistingChecksumForDataObject(dataObjectAbsPath);
		Assert.assertNotNull("did not get a checksum value", value);
		visitor.getChecksumAccumulator().close();
		MessageDigest digest = visitor.getChecksumAccumulator().getMessageDigest();
		String hexDigest = Hex.encodeHexString(digest.digest());
		Assert.assertNotNull("no digest found", hexDigest);

	}

}
