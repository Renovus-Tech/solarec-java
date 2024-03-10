package tech.renovus.solarec.inverters.brand.sma;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.sma.api.authorization.AuthResponse;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.BcAuthorizeResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.Device;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.measurements.MeasurementsResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.Plant;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantDevicesResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantsResponse;

public class SmaInverterServiceTest {

	//--- Private properties --------------------
	private static String clientId;
	private static String clientSecret;
	private static String resourceOwner;
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void setVariables() {
		SmaInverterServiceTest.clientId = System.getProperty("sma_client_id");
		SmaInverterServiceTest.clientSecret = System.getProperty("sma_client_secret");
		SmaInverterServiceTest.resourceOwner = System.getProperty("sma_resource_owner");
		
		boolean allDataRequired = SmaInverterServiceTest.clientId != null && SmaInverterServiceTest.clientSecret != null && SmaInverterServiceTest.resourceOwner != null;
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: sma_client_id, sma_client_secret, sma_resource_owner", allDataRequired);
	}
	
	//--- Test methods --------------------------
	@Test
	public void testPrivateProperties() {
		/**
		 * If test fails, make sure that you run the testing with the following system.properties:
		 *   - sma_client_id
		 *   - sma_client_secret
		 *   - sma_resource_owner
		 *   
		 * Example of execution: -Dfronius_access_key_id=<access_key_id_here> -Dfronius_access_key_value=<acess_key_id_value_here>
		 */
		assertNotNull(SmaInverterServiceTest.clientId);
		assertNotNull(SmaInverterServiceTest.clientSecret);
		assertNotNull(SmaInverterServiceTest.resourceOwner);
	}
	
	@Test 
	public void testAPI() {
		SmaInverterService service = new SmaInverterService();
		boolean sandboxMode = true;
		
		AuthResponse auth = service.retrieveToken(sandboxMode, SmaInverterServiceTest.clientId, SmaInverterServiceTest.clientSecret);
		assertNotNull(auth);
		assertNotNull(auth.getAccessToken());
		
		BcAuthorizeResponse authorize	= service.retrieveBcAutorizeToken(sandboxMode, auth, SmaInverterServiceTest.resourceOwner);
		assertNotNull(authorize);
		assertNotNull(authorize.getState());
		assertTrue(SmaInverterService.AUTHORIZE_ACCEPTED.equals(authorize.getState()));
		
		PlantsResponse plants = service.retrievePlants(sandboxMode, auth);
		assertNotNull(plants);
		assertNotNull(plants.getPlants());
		assertTrue(plants.getPlants().size() > 0);
		
		Plant plant = plants.getPlants().iterator().next();
		PlantDevicesResponse devices = service.retrievePlantDevices(sandboxMode, auth, plant.getPlantId());
		assertNotNull(devices);
		assertNotNull(devices.getDevices());
		assertTrue(devices.getDevices().size() > 0);
		
		Device device = devices.getDevices().iterator().next();
		MeasurementsResponse data = service.retrieveDeviceData(
				sandboxMode, 
				auth, 
				device.getDeviceId(), 
				SmaInverterService.SET_TYPE_ENERGY_AND_POWER_PV, 
				SmaInverterService.PERIOD_DAY, 
				"2024-02-10"
			);
		assertNotNull(data);
		//Due to issue with SMA sandbox, data migh not be given or internal server error generated.
	}
}
