package tech.renovus.solarec.inverters.brand.sma;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.TestingUtil;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.AuthResponse;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.BcAuthorizeResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.Device;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.measurements.MeasurementsResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.Plant;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantDevicesResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantsResponse;
import tech.renovus.solarec.inverters.common.InverterService.InverterData;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;

public class SmaInverterServiceTest {

	//--- Private properties --------------------
	private static boolean sandbox;
	private static String clientId;
	private static String clientSecret;
	private static String resourceOwner;
	
	private static SmaInverterService service = null;
	private static ClientVo client;
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void setVariables() {
		SmaInverterServiceTest.sandbox			= BooleanUtils.isTrue(System.getProperty("sma_sandbox"));
		SmaInverterServiceTest.clientId			= System.getProperty("sma_client_id");
		SmaInverterServiceTest.clientSecret		= System.getProperty("sma_client_secret");
		SmaInverterServiceTest.resourceOwner	= System.getProperty("sma_resource_owner");
		
		boolean allDataRequired = SmaInverterServiceTest.clientId != null && SmaInverterServiceTest.clientSecret != null && SmaInverterServiceTest.resourceOwner != null;
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: sma_sandbox, sma_client_id, sma_client_secret, sma_resource_owner", allDataRequired);
		
		SmaInverterServiceTest.client = new ClientVo();
		SmaInverterServiceTest.client.add(TestingUtil.createParameter(SmaInverterService.PARAM_SANBOX, BooleanUtils.toString(SmaInverterServiceTest.sandbox)));
		SmaInverterServiceTest.client.add(TestingUtil.createParameter(SmaInverterService.PARAM_CLIENT_CLIENT_ID, SmaInverterServiceTest.clientId));
		SmaInverterServiceTest.client.add(TestingUtil.createParameter(SmaInverterService.PARAM_CLIENT_CLIENT_SECRET, SmaInverterServiceTest.clientSecret));
		SmaInverterServiceTest.client.add(TestingUtil.createParameter(SmaInverterService.PARAM_CLIENT_RESOURCE_OWNER, SmaInverterServiceTest.resourceOwner));
		
		SmaInverterServiceTest.service = new SmaInverterService();
	}
	
	//--- Test methods --------------------------
	@Test
	public void testPrivateProperties() {
		/**
		 * If test fails, make sure that you run the testing with the following system.properties:
		 *   - sma_sandbox
		 *   - sma_client_id
		 *   - sma_client_secret
		 *   - sma_resource_owner
		 *   
		 * Example of execution: -D<param_1>=<value_1> -D<param_2=<value_2> -D<param_n>=<value_n>
		 */
		assertNotNull(SmaInverterServiceTest.clientId);
		assertNotNull(SmaInverterServiceTest.clientSecret);
		assertNotNull(SmaInverterServiceTest.resourceOwner);
	}
	
	@Test
	public void testCallApi() {
		Exception error = null;
		
		AuthResponse auth = service.retrieveToken(sandbox, SmaInverterServiceTest.clientId, SmaInverterServiceTest.clientSecret);
		assertNotNull(auth);
		assertNotNull(auth.getAccessToken());
		
		BcAuthorizeResponse authorize	= service.retrieveBcAutorizeToken(sandbox, auth, SmaInverterServiceTest.resourceOwner);
		assertNotNull(authorize);
		assertNotNull(authorize.getState());
		assertTrue(SmaInverterService.AUTHORIZE_ACCEPTED.equals(authorize.getState()));
		
		PlantsResponse plants = service.retrievePlants(sandbox, auth);
		assertNotNull(plants);
		assertNotNull(plants.getPlants());
		assertTrue(plants.getPlants().size() > 0);
		
		Plant plant = plants.getPlants().iterator().next();
		PlantDevicesResponse devices = service.retrievePlantDevices(sandbox, auth, plant.getPlantId());
		assertNotNull(devices);
		assertNotNull(devices.getDevices());
		assertTrue(devices.getDevices().size() > 0);
		
		Device device = devices.getDevices().iterator().next();
		MeasurementsResponse data = service.retrieveDeviceData(
				sandbox, 
				auth, 
				device.getDeviceId(), 
				SmaInverterService.SET_TYPE_ENERGY_AND_POWER_PV, 
				SmaInverterService.PERIOD_DAY, 
				"2024-02-10"
			);
		assertNotNull(data);
		
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
