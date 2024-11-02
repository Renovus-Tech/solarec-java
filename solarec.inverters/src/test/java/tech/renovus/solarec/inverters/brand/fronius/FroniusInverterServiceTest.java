package tech.renovus.solarec.inverters.brand.fronius;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.BaseInveterTest;
import tech.renovus.solarec.inverters.brand.fronius.api.InfoReleaseResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.HistoryDataResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.DeviceListResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.PvSystemsListResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.user.InfoUserResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FileUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.weather.WeatherService;

@RunWith(MockitoJUnitRunner.class)
public class FroniusInverterServiceTest extends BaseInveterTest {

	//--- Private properties --------------------
	@Mock private JsonCaller jsonCaller;
	@Mock private WeatherService weatherService;
	
	@InjectMocks private FroniusInverterService service = new FroniusInverterService();
	
	//--- Test methods --------------------------
	@Test
	public void testCallApi() throws IOException {
		Boolean betaMode			= false;
		String accessKeyId		= "not-a-real-key-id";
		String accessKeyValue	= "not-a-real-key-value";

		Path classPath			= this.getClassLocation(this.getClass());
		
		InfoReleaseResponse infoReleaseResponseMock	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/fronius/sample-info-release.json")), InfoReleaseResponse.class);
		InfoUserResponse infoUserResponseMock		= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/fronius/sample-info-user.json")), InfoUserResponse.class);
		PvSystemsListResponse pvSystemListMock		= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/fronius/sample-pvsystem-list.json")), PvSystemsListResponse.class);
		HistoryDataResponse pvSystemHistoryDataMock	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/fronius/sample-pvsystem-history-data.json")), HistoryDataResponse.class);
		DeviceListResponse pvSystemDevicesListMock	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/fronius/sample-pvsystem-device-list.json")), DeviceListResponse.class);
		
		when(this.jsonCaller.get(eq(FroniusInverterService.URL_PRD + FroniusInverterService.ENDPOINT_INFO_RELEASE), any(), any(), any())).thenReturn(infoReleaseResponseMock);
		when(this.jsonCaller.get(eq(FroniusInverterService.URL_PRD + FroniusInverterService.ENDPOINT_INFO_USER), any(), any(), any())).thenReturn(infoUserResponseMock);
		when(this.jsonCaller.get(eq(FroniusInverterService.URL_PRD + FroniusInverterService.ENDPOINT_PV_SYSTEMS_LIST), any(), any(), any())).thenReturn(pvSystemListMock);
		
		InfoReleaseResponse infoReleaseResponse = this.service.getInfoRelease(betaMode, accessKeyId, accessKeyValue);
		assertNotNull(infoReleaseResponse);
		assertFalse(infoReleaseResponse.hasError());
		assertNotNull(infoReleaseResponse.getReleaseVersion());
		assertNotNull(infoReleaseResponse.getReleaseDate());

		InfoUserResponse infoUserResponse = this.service.getInfoUser(betaMode, accessKeyId, accessKeyValue);
		assertNotNull(infoUserResponse);
		assertFalse(infoUserResponse.hasError());
		assertNotNull(infoUserResponse.getName());
		assertEquals(infoUserResponse.getName().getFirstName(), "John");
		assertEquals(infoUserResponse.getName().getLastName(), "Doe");

		PvSystemsListResponse pvSystemListResponse = this.service.getPvSystemsList(betaMode, accessKeyId, accessKeyValue);
		assertNotNull(pvSystemListResponse);
		assertFalse(pvSystemListResponse.hasError());
		assertNotNull(pvSystemListResponse.getPvSystemIds());
		assertTrue(CollectionUtil.notEmpty(pvSystemListResponse.getPvSystemIds()));

		String pvSystemsId = pvSystemListResponse.getPvSystemIds().iterator().next();
		when(this.jsonCaller.get(eq(FroniusInverterService.URL_PRD + "/pvsystems/" + pvSystemsId + "/histdata"), any(), any(), any())).thenReturn(pvSystemHistoryDataMock);
		when(this.jsonCaller.get(eq(FroniusInverterService.URL_PRD + "/pvsystems/" + pvSystemsId + "/devices-list"), any(), any(), any())).thenReturn(pvSystemDevicesListMock);
		
		DeviceListResponse devicesList = this.service.getPvSystemDevicesList(betaMode, accessKeyId, accessKeyValue, pvSystemsId);
		assertNotNull(devicesList);
		assertFalse(devicesList.hasError());
		assertNotNull(devicesList.getDeviceIds());
		assertTrue(CollectionUtil.notEmpty(devicesList.getDeviceIds()));
		
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

		HistoryDataResponse data = this.service.getPvSystemsHistData(betaMode, accessKeyId, accessKeyValue, pvSystemsId, from, to);
		assertNotNull(data);
		assertEquals(pvSystemsId, data.getPvSystemId());
		assertNotNull(data.getData());
		assertTrue(CollectionUtil.notEmpty(data.getData()));
	}
	
	//--- Overridden methods --------------------
	@Override
	public InverterService getService() { return this.service; }
	
	@Override
	public ClientVo createClient() {
		return this.buildClientWith(
			Arrays.asList(
				this.createClientParameter(FroniusInverterService.PARAM_BETA_MODE, "false"),
				this.createClientParameter(FroniusInverterService.PARAM_ACCESS_KEY_ID, "not-a-real-key-id"),
				this.createClientParameter(FroniusInverterService.PARAM_ACCESS_KEY_VALUE, "not-a-real-key-value")
			),
			null,
			Arrays.asList(
				this.createGeneratorParameter(FroniusInverterService.PARAM_GEN_PV_SYSTEM_ID, "34232"),
				this.createGeneratorParameter(FroniusInverterService.PARAM_GEN_LAST_DATE_RETRIEVE, "-1160481920")
			)
		);
	}
	
	@Override public void prepareMock() throws InveterServiceException {
		Path classPath								= this.getClassLocation(this.getClass());
		try {
			HistoryDataResponse pvSystemHistoryDataMock	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/fronius/sample-pvsystem-history-data.json")), HistoryDataResponse.class);
			
			String pvSystemsId = "34232";
			when(this.jsonCaller.get(eq(FroniusInverterService.URL_PRD + "/pvsystems/" + pvSystemsId + "/histdata"), any(), any(), any())).thenReturn(pvSystemHistoryDataMock);

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
