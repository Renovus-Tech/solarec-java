package tech.renovus.solarec.inverters.brand.sofar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.BaseInveterTest;
import tech.renovus.solarec.inverters.brand.sofar.api.AuthorizationResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.DevideListResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.PermissionResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.StationDeviceListResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.StationHistoryDataResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.StationListResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FileUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.weather.WeatherService;

@RunWith(MockitoJUnitRunner.class)
public class SofarInverterServiceTest extends BaseInveterTest {

	//--- Private properties --------------------
	@Mock private JsonCaller jsonCaller;
	@Mock private WeatherService weatherService;
	
	@InjectMocks private SofarInverterService service;
	
	//--- Tests methods -------------------------
	@Test 
	public void testCallApi() throws IOException {
		String url			= service.getUrl(true);
		String appId		= "not real app id";
		String appSecret 	= "not real app secret";
		Integer orgId 		= Integer.valueOf(860);
		String userName 	= "not real user";
		String password 	= "not real password";
		
		Path classPath								= this.getClassLocation(this.getClass());
		
		AuthorizationResponse authorizationMock			= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/sofar/sample-authorization.json")), AuthorizationResponse.class);
		PermissionResponse permissionMock				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/sofar/sample-permission.json")), PermissionResponse.class);
		DevideListResponse devicesMock					= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/sofar/sample-devices.json")), DevideListResponse.class);
		StationListResponse stationsMock				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/sofar/sample-stations.json")), StationListResponse.class);
		StationDeviceListResponse stationDevicesMock	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/sofar/sample-station-devices.json")), StationDeviceListResponse.class);
		StationHistoryDataResponse dataMock				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/sofar/sample-station-history-data.json")), StationHistoryDataResponse.class);
		
		when(this.jsonCaller.generateCompleteURL(anyString(), any())).thenAnswer(invocation -> invocation.getArgument(0));
		when(this.jsonCaller.post(      eq(SofarInverterService.URL_BETA + SofarInverterService.ENDPOINT_AUTHORIZATION), any(Object.class), any())).thenReturn(authorizationMock);
		when(this.jsonCaller.bearerPost( eq(SofarInverterService.URL_BETA + SofarInverterService.ENDPOINT_PERMISSION), any(), any(), any())).thenReturn(permissionMock);
		when(this.jsonCaller.bearerPost( eq(SofarInverterService.URL_BETA + SofarInverterService.ENDPOINT_DEVICE_LIST), any(), any(), any())).thenReturn(devicesMock);
		when(this.jsonCaller.bearerPost( eq(SofarInverterService.URL_BETA + SofarInverterService.ENDPOINT_STATION_LIST), any(), any(), any())).thenReturn(stationsMock);
		when(this.jsonCaller.bearerPost( eq(SofarInverterService.URL_BETA + SofarInverterService.ENDPOINT_STATION_DEVICE_LIST), any(), any(), any())).thenReturn(stationDevicesMock);
		when(this.jsonCaller.bearerPost( eq(SofarInverterService.URL_BETA + SofarInverterService.ENDPOINT_STATION_HISTORY_DATA), any(), any(), any())).thenReturn(dataMock);
		
		AuthorizationResponse authorization = service.getAuthorize(url, appId, appSecret, orgId, userName, password);
		
		assertNotNull(authorization);
		assertTrue(authorization.getSuccess());
		assertNotNull(authorization.getAccessToken());
		
		String authCode = authorization.getAccessToken();
		PermissionResponse permission = service.getPermission(url, authCode);
		
		assertNotNull(permission);
		assertTrue(BooleanUtils.isTrue(permission.getViewDeviceList()));
		assertTrue(BooleanUtils.isTrue(permission.getViewDeviceData()));
		assertTrue(BooleanUtils.isTrue(permission.getViewStationList()));
		assertTrue(BooleanUtils.isTrue(permission.getViewStationData()));
		
		DevideListResponse devices = service.getDeviveList(url, authCode);
		assertNotNull(devices);
		assertNotNull(devices.getTotal());
		assertNotNull(devices.getDeviceList());
		assertTrue(devices.getTotal().intValue() > 0);
		
		StationListResponse stations = service.getStationList(url, authCode);
		assertNotNull(stations);
		assertNotNull(stations.getTotal());
		assertNotNull(stations.getStationList());
		assertTrue(stations.getTotal().intValue() > 0);
		
		Integer stationId = stations.getStationList().iterator().next().getId();
		StationDeviceListResponse stationDevices = service.getStationDeviceList(url, authCode, stationId);
		assertNotNull(stationDevices);
		assertNotNull(stationDevices.getTotal());
		assertNotNull(stationDevices.getDeviceListItems());
		assertTrue(stationDevices.getTotal().intValue() > 0);
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(new Date());

		cal.add(Calendar.DAY_OF_YEAR, -2);
		Date dateTo = cal.getTime();
		
		cal.add(Calendar.DAY_OF_YEAR, -1);
		Date dateFrom = cal.getTime();

		StationHistoryDataResponse data = service.getStationHistoryData(url, authCode, stationId, dateFrom, dateTo);
		assertNotNull(data);
		assertNotNull(data.getStationDataItems());
		assertTrue(CollectionUtil.notEmpty(data.getStationDataItems()));
	}
	
	//--- Overridden methods --------------------
	@Override
	public InverterService getService() { return this.service; }
	
	@Override
	public ClientVo createClient() {
		return this.buildClientWith(
			Arrays.asList(
				this.createClientParameter(SofarInverterService.PARAM_BETA_MODE, "true"),
				this.createClientParameter(SofarInverterService.PARAM_ACCESS_APP_ID, "not-a-real-app-id"),
				this.createClientParameter(SofarInverterService.PARAM_ACCESS_APP_SECRET, "not-a-real-app-secret"),
				this.createClientParameter(SofarInverterService.PARAM_ACCESS_APP_ORG_ID, "123"),
				this.createClientParameter(SofarInverterService.PARAM_ACCESS_APP_USER, "not-a-real-app-user"),
				this.createClientParameter(SofarInverterService.PARAM_ACCESS_APP_PASSWORD, "not-a-real-app-password")
			),
			null,
			Arrays.asList(
				this.createGeneratorParameter(SofarInverterService.PARAM_GEN_STATION_ID, "34232"),
				this.createGeneratorParameter(SofarInverterService.PARAM_GEN_LAST_DATE_RETRIEVE, "-1160481920")
			)
		);
	}
	
	@Override public void prepareMock() throws InveterServiceException {
		Path classPath								= this.getClassLocation(this.getClass());
		try {
			AuthorizationResponse authorizationMock		= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/sofar/sample-authorization.json")), AuthorizationResponse.class);
			StationHistoryDataResponse dataMock			= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/sofar/sample-station-history-data.json")), StationHistoryDataResponse.class);
			
			when(this.jsonCaller.generateCompleteURL(anyString(), any())).thenAnswer(invocation -> invocation.getArgument(0));
			when(this.jsonCaller.post(      eq(SofarInverterService.URL_BETA + SofarInverterService.ENDPOINT_AUTHORIZATION), any(Object.class), any())).thenReturn(authorizationMock);
			when(this.jsonCaller.bearerPost(eq(SofarInverterService.URL_BETA + SofarInverterService.ENDPOINT_STATION_HISTORY_DATA), any(), any(), any())).thenReturn(dataMock);
			
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
