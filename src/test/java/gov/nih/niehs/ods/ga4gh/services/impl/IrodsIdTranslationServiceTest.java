package gov.nih.niehs.ods.ga4gh.services.impl;

import java.util.Properties;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import gov.nih.niehs.ods.ga4gh.rest.configuration.DosConfigInterface;
import gov.nih.niehs.ods.ga4gh.rest.configuration.PropsBasedDosConfiguration;

public class IrodsIdTranslationServiceTest {

	private static Properties testingProperties = new Properties();
	private static TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestingPropertiesHelper testingPropertiesLoader = new TestingPropertiesHelper();
		testingProperties = testingPropertiesLoader.getTestProperties();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testIrodsPathFromIdentifier() throws Exception {
		String testPath = "/a/test/path";
		IRODSAccount irodsAccount = testingPropertiesHelper.buildIRODSAccountFromTestProperties(testingProperties);
		IRODSAccessObjectFactory irodsAccessObjectFactory = Mockito.mock(IRODSAccessObjectFactory.class);
		DosConfigInterface dosConfiguration = new PropsBasedDosConfiguration();
		IrodsIdTranslationService txlate = new IrodsIdTranslationService(irodsAccessObjectFactory, irodsAccount,
				dosConfiguration);
		String actual = txlate.irodsPathFromIdentifier(testPath);
		Assert.assertEquals(testPath, actual);

	}

}
