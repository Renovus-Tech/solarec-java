package tech.renovus.solarec.inverters.brand.growatt;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.TestingUtil;
import tech.renovus.solarec.inverters.brand.growatt.api.ListPlantsResponse;
import tech.renovus.solarec.inverters.brand.growatt.api.PlantEnergyResponse;
import tech.renovus.solarec.inverters.brand.growatt.api.PlantPowerResponse;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;

public class GrowattInveterServiceTest {

	//--- Private properties --------------------
	private static boolean prodMode;
	private static String token;
	private static Integer plantId;
	
	private static GrowattInveterService service = null;
	private static ClientVo client;
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void setVariables() {
		GrowattInveterServiceTest.prodMode			= BooleanUtils.isTrue(System.getProperty("growatt_prod_mode"));
		GrowattInveterServiceTest.token				= System.getProperty("growatt_token");
		try {
			GrowattInveterServiceTest.plantId		= Integer.valueOf(System.getProperty("growatt_plant_id"));
		} catch (NumberFormatException e) {
			/* do nothing */
		}
		
		boolean allDataRequired = GrowattInveterServiceTest.token != null && GrowattInveterServiceTest.plantId != null;
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: growatt_prod_mode, growatt_token, growatt_plant_id", allDataRequired);
		
		GrowattInveterServiceTest.client = new ClientVo();
		GrowattInveterServiceTest.client.add(TestingUtil.createParameter(GrowattInveterService.PARAM_BETA_MODE, BooleanUtils.toString(GrowattInveterServiceTest.prodMode)));
		GrowattInveterServiceTest.client.add(TestingUtil.createParameter(GrowattInveterService.PARAM_APP_TOKEN, GrowattInveterServiceTest.token));
		GrowattInveterServiceTest.client.add(TestingUtil.createParameter(GrowattInveterService.PARAM_GEN_PLANT_ID, GrowattInveterServiceTest.plantId.toString()));
		
		GrowattInveterServiceTest.service = new GrowattInveterService();
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
		assertNotNull(GrowattInveterServiceTest.token);
		assertNotNull(GrowattInveterServiceTest.plantId);
	}
	
	@Test
	public void testCallApi() {
		Date dateStart = new Date();
		Date dateEnd = new Date();
		String url = service.getUrl(prodMode);
		
		ListPlantsResponse plants = service.listPlants(url, token);
		assertNotNull(plants);
		assertTrue(plants != null && plants.getData() != null && CollectionUtil.notEmpty(plants.getData().getPlants()));

		PlantEnergyResponse data = service.getPlantEnergy(url, token, plantId, GrowattInveterService.TIME_UNIT_DAY, dateStart, dateEnd);
		assertNotNull(data);
		
		PlantPowerResponse data2 = service.getPlantPower(url, token, plantId, dateStart);
		assertNotNull(data2);
	}
}
