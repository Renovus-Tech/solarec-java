package tech.renovus.solarec.inverters.brand.aiswei;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.weather.WeatherService;

/**
 * Aiswei API changed and waiting for access to new API.
 */
public class AisweiInverterServiceTest /* extends BaseInveterTest */ {

	//--- Private properties --------------------
	@Mock private JsonCaller jsonCaller;
	@Mock private WeatherService weatherService;
	
	@InjectMocks private AisweiInverterService service;
	
	//--- Tests ---------------------------------
//	@Test 
//	public void testCallApi() throws IOException {
//		boolean betaMode		= true;
//		String clientAppKey		= "not-real-key";
//		String clientUserToken	= "not-real-token";
//		String plantKey			= "not-real-key";
//		
//		Path classPath										= this.getClassLocation(this.getClass());
//		PlantListResponse plantListResponse					= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/aiswei/sample-plant-list.json")), PlantListResponse.class);
//		DeviceListResponse deviceListResponse				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/aiswei/sample-device-list.json")), DeviceListResponse.class);
//		PlantOverviewResponse pantOverviewResponse			= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/aiswei/sample-plant-overview.json")), PlantOverviewResponse.class);
//		InverterOverviewResponse inverterOverviewResponse	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/aiswei/sample-inverter-overview.json")), InverterOverviewResponse.class);
//		PlantOutputResponse plantOutputResponse				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/aiswei/sample-plant-output.json")), PlantOutputResponse.class);
//
//		when(this.jsonCaller.get(AisweiInverterService.URL_DEMO + AisweiInverterService.END_PINT_GET_PLANT_LIST, any(), any(),  PlantListResponse.class)).thenReturn(plantListResponse);
//		when(this.jsonCaller.get(AisweiInverterService.URL_DEMO + AisweiInverterService.END_PINT_GET_DEVICE_LIST, any(), any(),  DeviceListResponse.class)).thenReturn(deviceListResponse);
//		when(this.jsonCaller.get(AisweiInverterService.URL_DEMO + AisweiInverterService.END_PINT_GET_PLANT_OVERVIEW, any(), any(),  PlantOverviewResponse.class)).thenReturn(pantOverviewResponse);
//		when(this.jsonCaller.get(AisweiInverterService.URL_DEMO + AisweiInverterService.END_PINT_GET_INVERTER_OVERVIEW, any(), any(),  InverterOverviewResponse.class)).thenReturn(inverterOverviewResponse);
//		when(this.jsonCaller.get(AisweiInverterService.URL_DEMO + AisweiInverterService.END_PINT_GET_PLANT_OUTPUT, any(), any(),  PlantOutputResponse.class)).thenReturn(plantOutputResponse);
//		
//		String url			= this.service.getUrl(! BooleanUtils.isTrue(betaMode));
//		
//		assertNotNull(url);
//		
//		Exception error = null;
//		PlantListResponse plantList = null;
//		try {
//			plantList = this.service.getPlantList(url, clientAppKey, clientUserToken);
//			
//			assertNotNull(plantList);
//			assertNotNull(plantList.getData());
//			assertNotNull(plantList.getData().getList());
//		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
//			error = e;
//		}
//		
//		assertNull(error);
//		
//		try {
//			DeviceListResponse deviceList = this.service.getDeviceList(url, clientAppKey, plantKey);
//			
//			assertNotNull(deviceList);
//			assertNotNull(deviceList.getData());
//			assertNotNull(deviceList.getData().getList());
//		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
//			error = e;
//		}
//		
//		assertNull(error);
//		
//		try {
//			PlantOverviewResponse plantOverview = this.service.getPlantOverview(url, clientAppKey, plantKey);
//			
//			assertNotNull(plantOverview);
//		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
//			error = e;
//		}
//		
//		assertNull(error);
//		
//		try {
//			InverterOverviewResponse inverterOverview = this.service.getInverterOverview(url, clientAppKey, plantKey);
//			
//			assertNotNull(inverterOverview);
//			assertNotNull(inverterOverview.getData());
//		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
//			error = e;
//		}
//		
//		assertNull(error);
//		
//		try {
//			Calendar calendar	= GregorianCalendar.getInstance();
//			calendar.setTime(new Date());
//			calendar.add(Calendar.DAY_OF_YEAR, -10);
//			Date aDate			= calendar.getTime();
//
//			PlantOutputResponse plantOutput = this.service.getPlantOutput(url, clientAppKey, plantKey, AisweiInverterService.PERIOD_BY_DAYS, aDate);
//			
//			assertNotNull(plantOutput);
//			assertNotNull(plantOutput.getData());
//		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
//			error = e;
//		}
//		
//		assertNull(error);
//	}
//	
//	//--- Overridden methods --------------------
//	@Override
//	public InverterService getService() { return this.service; }
//
//	@Override
//	public ClientVo createClient() {
//		return this.buildClientWith(
//			Arrays.asList(
//				this.createClientParameter(AisweiInverterService.PARAM_BETA_MODE, "false"),
//				this.createClientParameter(AisweiInverterService.PARAM_ACCESS_APP_KEY, "not-a-real-password"),
//				this.createClientParameter(AisweiInverterService.PARAM_USER_TOKEN, "not-a-real-key")
//			),
//			null,
//			Arrays.asList(
//				this.createGeneratorParameter(AisweiInverterService.PARAM_GEN_PLANT_KEY, "34232"),
//				this.createGeneratorParameter(AisweiInverterService.PARAM_GEN_LAST_DATE_RETRIEVE, "-1160481920")
//			)
//		);
//	}
//	
//	@Override public void prepareMock() throws InveterServiceException {
//		Path classPath								= this.getClassLocation(this.getClass());
//		try {
//			PlantOutputResponse plantOutputResponse				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/aiswei/sample-plant-output.json")), PlantOutputResponse.class);
//
//			when(this.jsonCaller.get(AisweiInverterService.URL_DEMO + AisweiInverterService.END_PINT_GET_PLANT_OUTPUT, any(), any(),  PlantOutputResponse.class)).thenReturn(plantOutputResponse);
//
//		} catch (IOException e) {
//			throw new InveterServiceException(e);
//		}
//	}
//	
//	@Override
//	public void postDataRetrieveTest() {
//		assertTrue(this.service.continueWithStats());
//		assertNull(this.service.getReasonWhyCantRetrieve());
//	}
}
