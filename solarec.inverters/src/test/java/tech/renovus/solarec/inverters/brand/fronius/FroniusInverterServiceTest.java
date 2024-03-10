package tech.renovus.solarec.inverters.brand.fronius;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.fronius.api.InfoReleaseResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.HistoryDataResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.PvSystemsListResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.user.InfoUserResponse;
import tech.renovus.solarec.util.CollectionUtil;

public class FroniusInverterServiceTest {

	//--- Private properties --------------------
	private static String accessKeyId;
	private static String accessKeyValue;
	private static boolean betaMode = true;
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void setVariables() {
		FroniusInverterServiceTest.accessKeyId		= System.getProperty("fronius_access_key_id");
		FroniusInverterServiceTest.accessKeyValue	= System.getProperty("fronius_access_key_value");
		
		boolean allDataRequired = FroniusInverterServiceTest.accessKeyId != null && FroniusInverterServiceTest.accessKeyValue != null;
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: fimer_user, fronius_access_key_id, fronius_access_key_value", allDataRequired);
	}
	
	//--- Test methods --------------------------
	@Test
	public void testPrivateProperties() {
		/**
		 * If test fails, make sure that you run the testing with the following system.properties:
		 *   - fronius_access_key_id
		 *   - fronius_access_key_value
		 *   
		 * Example of execution: -Dfronius_access_key_id=<access_key_id_here> -Dfronius_access_key_value=<acess_key_id_value_here>
		 */
		assertNotNull(FroniusInverterServiceTest.accessKeyId);
		assertNotNull(FroniusInverterServiceTest.accessKeyValue);
	}
	
	@Test
	public void testInfoRelease() {
		FroniusInverterService service = new FroniusInverterService();
		InfoReleaseResponse response = service.getInfoRelease(FroniusInverterServiceTest.betaMode, FroniusInverterServiceTest.accessKeyId, FroniusInverterServiceTest.accessKeyValue);
		assertNotNull(response);
		assertFalse(response.hasError());
		assertNotNull(response.getReleaseVersion());
		assertNotNull(response.getReleaseDate());
	}

	@Test
	public void testInfoUser() {
		FroniusInverterService service = new FroniusInverterService();
		InfoUserResponse response = service.getInfoUser(FroniusInverterServiceTest.betaMode, FroniusInverterServiceTest.accessKeyId, FroniusInverterServiceTest.accessKeyValue);
		assertNotNull(response);
		assertFalse(response.hasError());
		assertNotNull(response.getName());
		assertEquals(response.getName().getFirstName(), "Api");
		assertEquals(response.getName().getLastName(), "Demo");
	}
	
	@Test
	public void testPvSystemsList() {
		FroniusInverterService service = new FroniusInverterService();
		PvSystemsListResponse response = service.getPvSystemsList(FroniusInverterServiceTest.betaMode, FroniusInverterServiceTest.accessKeyId, FroniusInverterServiceTest.accessKeyValue);
		assertNotNull(response);
		assertFalse(response.hasError());
		assertNotNull(response.getPvSystemIds());
		assertTrue(CollectionUtil.notEmpty(response.getPvSystemIds()));
	}
	
	@Test
	public void testPvSystemsHistoryData() {
		FroniusInverterService service = new FroniusInverterService();
		PvSystemsListResponse response = service.getPvSystemsList(FroniusInverterServiceTest.betaMode, FroniusInverterServiceTest.accessKeyId, FroniusInverterServiceTest.accessKeyValue);
		assertNotNull(response);
		assertFalse(response.hasError());
		assertNotNull(response.getPvSystemIds());
		assertTrue(CollectionUtil.notEmpty(response.getPvSystemIds()));
		
		String pvSystemsId = response.getPvSystemIds().iterator().next();
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		
		cal.set(Calendar.YEAR, 2023);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 12);
		
		Date from = cal.getTime();
		
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.MILLISECOND, -1);
		
		Date to = cal.getTime();

		HistoryDataResponse data = service.getPvSystemsHistData(FroniusInverterServiceTest.betaMode, FroniusInverterServiceTest.accessKeyId, FroniusInverterServiceTest.accessKeyValue, pvSystemsId, from, to);
		assertNotNull(data);
		assertFalse(response.hasError());
		assertEquals(data.getPvSystemId(), pvSystemsId);
		assertNotNull(data.getData());
		assertTrue(CollectionUtil.notEmpty(data.getData()));
	}
}
