package tech.renovus.solarec.inverters.brand.aiswei;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.TestingUtil;
import tech.renovus.solarec.inverters.brand.aiswei.api.DeviceListResponse;
import tech.renovus.solarec.inverters.brand.aiswei.api.InverterOverviewResponse;
import tech.renovus.solarec.inverters.brand.aiswei.api.PlantListResponse;
import tech.renovus.solarec.inverters.brand.aiswei.api.PlantOutputResponse;
import tech.renovus.solarec.inverters.brand.aiswei.api.PlantOverviewResponse;
import tech.renovus.solarec.inverters.common.InverterService.InverterData;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;

public class AisweiInverterServiceTest {

	//--- Private properties --------------------
	private static String betaMode;
	private static String clientAppKey;
	private static String clientUserToken;
	private static String generatorPlantKey;
	
	private static AisweiInverterService service;
	private static ClientVo client;
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void init() {
		AisweiInverterServiceTest.betaMode				= System.getProperty("aiswei_beta");
		AisweiInverterServiceTest.clientAppKey			= System.getProperty("aiswei_client_app_key");
		AisweiInverterServiceTest.clientUserToken		= System.getProperty("aiswei_client_user_token");
		AisweiInverterServiceTest.generatorPlantKey		= System.getProperty("aiswei_generator_plant_key");
		
		boolean allDataRequired = 
				AisweiInverterServiceTest.betaMode != null && 
				AisweiInverterServiceTest.clientAppKey != null && 
				AisweiInverterServiceTest.clientUserToken != null && 
				AisweiInverterServiceTest.generatorPlantKey != null;
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: aiswei_beta, aiswei_client_app_key, aiswei_client_user_token, aiswei_generator_plant_key", allDataRequired);
		
		AisweiInverterServiceTest.client = new ClientVo();
		AisweiInverterServiceTest.client.add(TestingUtil.createParameter(AisweiInverterService.PARAM_BETA_MODE, AisweiInverterServiceTest.betaMode));
		AisweiInverterServiceTest.client.add(TestingUtil.createParameter(AisweiInverterService.PARAM_ACCESS_APP_KEY, AisweiInverterServiceTest.clientAppKey));
		AisweiInverterServiceTest.client.add(TestingUtil.createParameter(AisweiInverterService.PARAM_GEN_PLANT_KEY, AisweiInverterServiceTest.generatorPlantKey));
		
		AisweiInverterServiceTest.service = new AisweiInverterService();
	}
	
	//--- Tests ---------------------------------
	@Test
	public void testPrivateProperties() {
		/**
		 * If test fails, make sure that you run the testing with the following system.properties:
		 *   - aiswei_beta
		 *   - aiswei_client_app_key");
		 *   - aiswei_client_user_token");
		 *   - aiswei_generator_plant_key");
		 *   
		 * Example of execution: -D<param_1>=<value_1> -D<param_2=<value_2> -D<param_n>=<value_n>
		 */
		assertNotNull(AisweiInverterServiceTest.betaMode);
		assertNotNull(AisweiInverterServiceTest.clientAppKey);
		assertNotNull(AisweiInverterServiceTest.generatorPlantKey);
	}
	
	
	@Test 
	public void testCallApi() {
		String url			= service.getUrl(! BooleanUtils.isTrue(AisweiInverterServiceTest.betaMode));
		
		assertNotNull(url);
		
		Exception error = null;
		PlantListResponse plantList = null;
		try {
			plantList = service.getPlantList(url, AisweiInverterServiceTest.clientAppKey, AisweiInverterServiceTest.clientUserToken);
			
			assertNotNull(plantList);
			assertNotNull(plantList.getData());
			assertNotNull(plantList.getData().getList());
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			error = e;
		}
		
		assertNull(error);
		
		
		String plantKey = plantList.getData().getList().iterator().next().getApikey();
		
		try {
			DeviceListResponse deviceList = service.getDeviceList(url, AisweiInverterServiceTest.clientAppKey, plantKey);
			
			assertNotNull(deviceList);
			assertNotNull(deviceList.getData());
			assertNotNull(deviceList.getData().getList());
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			error = e;
		}
		
		assertNull(error);
		
		try {
			PlantOverviewResponse plantOverview = service.getPlantOverview(url, AisweiInverterServiceTest.clientAppKey, plantKey);
			
			assertNotNull(plantOverview);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			error = e;
		}
		
		assertNull(error);
		
		try {
			InverterOverviewResponse inverterOverview = service.getInverterOverview(url, AisweiInverterServiceTest.clientAppKey, plantKey);
			
			assertNotNull(inverterOverview);
			assertNotNull(inverterOverview.getData());
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			error = e;
		}
		
		assertNull(error);
		
		try {
			Calendar calendar	= GregorianCalendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, -10);
			Date aDate			= calendar.getTime();

			PlantOutputResponse plantOutput = service.getPlantOutput(url, AisweiInverterServiceTest.clientAppKey, plantKey, AisweiInverterService.PERIOD_BY_DAYS, aDate);
			
			assertNotNull(plantOutput);
			assertNotNull(plantOutput.getData());
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			error = e;
		}
		
		assertNull(error);
		
		try {
			service.prepareFor(client);
			InverterData data = service.retrieveData();
			
			assertNotNull(data);
			assertNotNull(data.getGeneratorData());
			assertNotNull(data.getStationData());
			assertTrue(CollectionUtil.notEmpty(data.getGeneratorData()));
			assertTrue(CollectionUtil.notEmpty(data.getStationData()));
		} catch (InveterServiceException e) {
			error = e;
		}
		
		assertNull(error);
	}
}
