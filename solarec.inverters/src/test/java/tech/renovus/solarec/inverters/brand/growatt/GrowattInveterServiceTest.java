package tech.renovus.solarec.inverters.brand.growatt;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.BaseInveterTest;
import tech.renovus.solarec.inverters.brand.growatt.api.ListPlantsResponse;
import tech.renovus.solarec.inverters.brand.growatt.api.PlantEnergyResponse;
import tech.renovus.solarec.inverters.brand.growatt.api.PlantPowerResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FileUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.weather.WeatherService;

public class GrowattInveterServiceTest extends BaseInveterTest {

	//--- Private properties --------------------
	@Mock private JsonCaller jsonCaller;
	@Mock private WeatherService weatherService;
	
	@InjectMocks private static GrowattInveterService service = null;
	
	
	//--- Test methods --------------------------
	@Test
	public void testCallApi() {
		boolean prodMode = false;
		String token = "not-a-real-token";
		Integer plantId = Integer.valueOf(-1);
		
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
	
	//--- Overridden methods --------------------
	@Override
	public InverterService getService() { return this.service; }
	
	@Override
	public ClientVo createClient() {
		return this.buildClientWith(
			Arrays.asList(
				this.createClientParameter(GrowattInveterService.PARAM_BETA_MODE, "true"),
				this.createClientParameter(GrowattInveterService.PARAM_APP_TOKEN, "not-a-real-key-id")
			),
			null,
			Arrays.asList(
				this.createGeneratorParameter(GrowattInveterService.PARAM_GEN_PLANT_ID, "34232"),
				this.createGeneratorParameter(GrowattInveterService.PARAM_GEN_LAST_DATE_RETRIEVE, "-1160481920")
			)
		);
	}
	
	@Override public void prepareMock() throws InveterServiceException {
		Path classPath								= this.getClassLocation(this.getClass());
		try {
			PlantEnergyResponse plantEnergyResponse	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/growatt/sample-plant-energy.json")), PlantEnergyResponse.class);
			
			when(this.jsonCaller.get(eq(GrowattInveterService.URL_TEST + GrowattInveterService.ENDPOINT_PLANT_ENERGY), any(), any(), any())).thenReturn(plantEnergyResponse);

		} catch (IOException e) {
			throw new InveterServiceException(e);
		}
	}
	
	@Override
	public void postDataRetrieveTest() {
		assertTrue(this.service.continueWithStats());
		assertNull(this.service.getReasonWhyCantRetrieve());
	}
}
