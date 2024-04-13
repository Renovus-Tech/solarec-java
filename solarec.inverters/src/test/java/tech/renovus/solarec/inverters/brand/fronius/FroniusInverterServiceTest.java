package tech.renovus.solarec.inverters.brand.fronius;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.TestingUtil;
import tech.renovus.solarec.inverters.brand.fronius.api.InfoReleaseResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.HistoryDataResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.PvSystemsListResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.user.InfoUserResponse;
import tech.renovus.solarec.inverters.common.InverterService.InverterData;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;

public class FroniusInverterServiceTest {

	//--- Private properties --------------------
	private static boolean betaMode;
	private static String accessKeyId;
	private static String accessKeyValue;
	
	private static FroniusInverterService service = null;
	private static ClientVo client;
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void setVariables() {
		FroniusInverterServiceTest.betaMode			= BooleanUtils.isTrue(System.getProperty("fronius_fronius_beta"));
		FroniusInverterServiceTest.accessKeyId		= System.getProperty("fronius_access_key_id");
		FroniusInverterServiceTest.accessKeyValue	= System.getProperty("fronius_access_key_value");
		
		boolean allDataRequired = FroniusInverterServiceTest.accessKeyId != null && FroniusInverterServiceTest.accessKeyValue != null;
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: fimer_user, fronius_access_key_id, fronius_access_key_value", allDataRequired);
		
		FroniusInverterServiceTest.client = new ClientVo();
		FroniusInverterServiceTest.client.add(TestingUtil.createParameter(FroniusInverterService.PARAM_BETA_MODE, BooleanUtils.toString(FroniusInverterServiceTest.betaMode)));
		FroniusInverterServiceTest.client.add(TestingUtil.createParameter(FroniusInverterService.PARAM_ACCESS_KEY_ID, FroniusInverterServiceTest.accessKeyId));
		FroniusInverterServiceTest.client.add(TestingUtil.createParameter(FroniusInverterService.PARAM_ACCESS_KEY_VALUE, FroniusInverterServiceTest.accessKeyValue));
		
		FroniusInverterServiceTest.service = new FroniusInverterService();
	}
	
	//--- Test methods --------------------------
	@Test
	public void testPrivateProperties() {
		/**
		 * If test fails, make sure that you run the testing with the following system.properties:
		 *   - fronius_access_key_id
		 *   - fronius_access_key_value
		 *   
		 * Example of execution: -D<param_1>=<value_1> -D<param_2=<value_2> -D<param_n>=<value_n>
		 */
		assertNotNull(FroniusInverterServiceTest.accessKeyId);
		assertNotNull(FroniusInverterServiceTest.accessKeyValue);
	}
	
	@Test
	public void testCallApi() {
		Exception error = null;
		
		InfoReleaseResponse infoReleaseResponse = service.getInfoRelease(FroniusInverterServiceTest.betaMode, FroniusInverterServiceTest.accessKeyId, FroniusInverterServiceTest.accessKeyValue);
		assertNotNull(infoReleaseResponse);
		assertFalse(infoReleaseResponse.hasError());
		assertNotNull(infoReleaseResponse.getReleaseVersion());
		assertNotNull(infoReleaseResponse.getReleaseDate());

		InfoUserResponse infoUserResponse = service.getInfoUser(FroniusInverterServiceTest.betaMode, FroniusInverterServiceTest.accessKeyId, FroniusInverterServiceTest.accessKeyValue);
		assertNotNull(infoUserResponse);
		assertFalse(infoUserResponse.hasError());
		assertNotNull(infoUserResponse.getName());
		assertEquals(infoUserResponse.getName().getFirstName(), "Api");
		assertEquals(infoUserResponse.getName().getLastName(), "Demo");

		PvSystemsListResponse pvSystemListResponse = service.getPvSystemsList(FroniusInverterServiceTest.betaMode, FroniusInverterServiceTest.accessKeyId, FroniusInverterServiceTest.accessKeyValue);
		assertNotNull(pvSystemListResponse);
		assertFalse(pvSystemListResponse.hasError());
		assertNotNull(pvSystemListResponse.getPvSystemIds());
		assertTrue(CollectionUtil.notEmpty(pvSystemListResponse.getPvSystemIds()));
		assertFalse(pvSystemListResponse.hasError());

		String pvSystemsId = pvSystemListResponse.getPvSystemIds().iterator().next();
		
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
		assertEquals(data.getPvSystemId(), pvSystemsId);
		assertNotNull(data.getData());
		assertTrue(CollectionUtil.notEmpty(data.getData()));
		
		try {
			service.prepareFor(client);
			InverterData apiData = service.retrieveData();
			
			assertNotNull(apiData);
			assertNotNull(apiData.getGeneratorData());
			assertNotNull(apiData.getStationData());
			assertTrue(CollectionUtil.notEmpty(apiData.getGeneratorData()));
			assertTrue(CollectionUtil.notEmpty(apiData.getStationData()));
		} catch (InveterServiceException e) {
			error = e;
		}
		
		assertNull(error);
	}
}
