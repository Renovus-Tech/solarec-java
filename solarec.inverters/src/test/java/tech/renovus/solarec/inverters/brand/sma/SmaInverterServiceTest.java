package tech.renovus.solarec.inverters.brand.sma;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.BaseInveterTest;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.AuthResponse;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.BcAuthorizeResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.Device;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.measurements.MeasurementsResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.Plant;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantDevicesResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantsResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.FileUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.weather.WeatherService;

@RunWith(MockitoJUnitRunner.class)
public class SmaInverterServiceTest extends BaseInveterTest {

	//--- Private properties --------------------
	@Mock private JsonCaller jsonCaller;
	@Mock private WeatherService weatherService;
	
	@InjectMocks private SmaInverterService service = new SmaInverterService();
	
	//--- Test methods --------------------------
	@Test
	public void testCallApi() throws IOException {
		boolean sandbox			= true;
		String clientId 		= "not-real-client-id";
		String clientSecret 	= "not-real-secret";
		String resourceOwner	= "not-real-resource-owner";
		String pantId			= "13";
		String deviceId			= "14";
		
		Path classPath								= this.getClassLocation(this.getClass());
		
		AuthResponse retrieveTokenMock				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/sma/sample-retrieve-token.json")), AuthResponse.class);
		BcAuthorizeResponse bcAuthorizeTokenMock	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/sma/sample-retrieve-bc-authorize-token.json")), BcAuthorizeResponse.class);
		PlantsResponse plantsMock					= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/sma/sample-plants.json")), PlantsResponse.class);
		PlantDevicesResponse devicesMock			= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/sma/sample-plant-devices.json")), PlantDevicesResponse.class);
		MeasurementsResponse dataMock				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/sma/sample-device-data.json")), MeasurementsResponse.class);
		
		when(this.jsonCaller.post(eq(SmaInverterService.SANDBOX_URL_AUTH + SmaInverterService.ENDPOINT_TOKEN), any(), any())).thenReturn(retrieveTokenMock);
		when(this.jsonCaller.bearerGet(startsWith(SmaInverterService.SANDBOX_URL_DATA + SmaInverterService.ENDPOINT_BC_AUTHROIZE_TOKEN.replaceFirst("\\{emailAddressResourceOwner\\}", "")), any(), any(), any())).thenReturn(bcAuthorizeTokenMock);
		when(this.jsonCaller.bearerGet(eq(SmaInverterService.SANDBOX_URL_DATA + SmaInverterService.ENDPOINT_MONITORING_PLANTS), any(), any(), any())).thenReturn(plantsMock);
		when(this.jsonCaller.bearerGet(startsWith(SmaInverterService.SANDBOX_URL_DATA + SmaInverterService.ENDPOINT_MONITORING_PLAT_DEVICES.replaceFirst("\\{plantId\\}", pantId)), any(), any(), any())).thenReturn(devicesMock);
		when(this.jsonCaller.bearerGet(startsWith(SmaInverterService.SANDBOX_URL_DATA + SmaInverterService.ENDPOINT_MEASUREMENTS_DEVICE
				.replaceFirst("\\{deviceId\\}", deviceId) 
				.replaceFirst("\\{setType\\}", SmaInverterService.SET_TYPE_ENERGY_AND_POWER_PV) 
				.replaceFirst("\\{period\\}", SmaInverterService.PERIOD_DAY)), any(), any(), any())).thenReturn(dataMock);
		
		AuthResponse retrieveToken = this.service.retrieveToken(sandbox, clientId, clientSecret);
		assertNotNull(retrieveToken);
		assertNotNull(retrieveToken.getAccessToken());
		
		BcAuthorizeResponse bcAuthorizeToken	= service.retrieveBcAuthorizeToken(sandbox, retrieveToken, resourceOwner);
		assertNotNull(bcAuthorizeToken);
		assertNotNull(bcAuthorizeToken.getState());
		assertEquals(SmaInverterService.AUTHORIZE_ACCEPTED, bcAuthorizeToken.getState());
		
		PlantsResponse plants = service.retrievePlants(sandbox, retrieveToken);
		assertNotNull(plants);
		assertNotNull(plants.getPlants());
		assertTrue(plants.getPlants().size() > 0);
		
		Plant plant = plants.getPlants().iterator().next();
		PlantDevicesResponse devices = service.retrievePlantDevices(sandbox, retrieveToken, plant.getPlantId());
		assertNotNull(devices);
		assertNotNull(devices.getDevices());
		assertTrue(devices.getDevices().size() > 0);
		
		Device device = devices.getDevices().iterator().next();
		MeasurementsResponse data = service.retrieveDeviceData(
				sandbox, 
				retrieveToken, 
				device.getDeviceId(), 
				SmaInverterService.SET_TYPE_ENERGY_AND_POWER_PV, 
				SmaInverterService.PERIOD_DAY, 
				"2024-02-10"
			);
		assertNotNull(data);
	}
	
	//--- Overridden methods --------------------
	@Override
	public InverterService getService() { return this.service; }
	
	@Override
	public ClientVo createClient() {
		return this.buildClientWith(
			Arrays.asList(
				this.createClientParameter(SmaInverterService.PARAM_SANBOX, "true"),
				this.createClientParameter(SmaInverterService.PARAM_CLIENT_CLIENT_ID, "not-a-real-client-id"),
				this.createClientParameter(SmaInverterService.PARAM_CLIENT_CLIENT_SECRET, "not-a-real-client-secret"),
				this.createClientParameter(SmaInverterService.PARAM_CLIENT_RESOURCE_OWNER, "not-a-real-resource-owner")
			),
			null,
			Arrays.asList(
				this.createGeneratorParameter(SmaInverterService.PARAM_GENERATOR_DEVICE_ID, "34232"),
				this.createGeneratorParameter(SmaInverterService.PARAM_GEN_LAST_DATE_RETRIEVE, "-1160481920")
			)
		);
	}
	
	@Override public void prepareMock() throws InveterServiceException {
		Path classPath								= this.getClassLocation(this.getClass());
		try {
			AuthResponse retrieveTokenMock				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/sma/sample-retrieve-token.json")), AuthResponse.class);
			BcAuthorizeResponse bcAuthorizeTokenMock	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/sma/sample-retrieve-bc-authorize-token.json")), BcAuthorizeResponse.class);
			MeasurementsResponse dataMock				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/sma/sample-device-data.json")), MeasurementsResponse.class);
			
			when(this.jsonCaller.post(eq(SmaInverterService.SANDBOX_URL_AUTH + SmaInverterService.ENDPOINT_TOKEN), any(), any())).thenReturn(retrieveTokenMock);
			when(this.jsonCaller.bearerGet(startsWith(SmaInverterService.SANDBOX_URL_DATA + SmaInverterService.ENDPOINT_BC_AUTHROIZE_TOKEN.replaceFirst("\\{emailAddressResourceOwner\\}", "")), any(), any(), any())).thenReturn(bcAuthorizeTokenMock);
			when(this.jsonCaller.bearerGet(startsWith(SmaInverterService.SANDBOX_URL_DATA + SmaInverterService.ENDPOINT_MEASUREMENTS_DEVICE
					.replaceFirst("\\{deviceId\\}", "34232") 
					.replaceFirst("\\{setType\\}", SmaInverterService.SET_TYPE_ENERGY_AND_POWER_PV) 
					.replaceFirst("\\{period\\}", SmaInverterService.PERIOD_DAY)), any(), any(), any())).thenReturn(dataMock);

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
