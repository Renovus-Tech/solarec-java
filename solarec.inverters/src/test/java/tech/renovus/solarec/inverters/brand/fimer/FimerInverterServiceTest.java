package tech.renovus.solarec.inverters.brand.fimer;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.BaseInveterTest;
import tech.renovus.solarec.inverters.brand.fimer.api.authenticate.AuthenticateResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.datalogger.IpRangeDataloggerResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.web.IpRangeWebResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.status.StatusResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.energy.timeseries.TelemetryDataEnergyTimeseriesResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.FileUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.weather.WeatherService;

@RunWith(MockitoJUnitRunner.class)
public class FimerInverterServiceTest extends BaseInveterTest {

	//--- Private properties --------------------
	@Mock private JsonCaller jsonCaller;
	@Mock private WeatherService weatherService;
	
	@InjectMocks private FimerInverterService service = new FimerInverterService();

	//--- Private methods -----------------------
	private Path getClassLocation(Class<?> clazz) {
		try {
			// Get the URL where the class is loaded from
			URL location = clazz.getProtectionDomain().getCodeSource().getLocation();

			// Convert the URL to a path
			return Paths.get(location.toURI());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//--- Testing methods -----------------------
	@Test 
	public void testCallApi() throws IOException {
		String user		= "sample";
		String password	= "not-a-real-password";
		String key		= "not-a-real-key";
		
		Path classPath								= this.getClassLocation(this.getClass());
		AuthenticateResponse authenticationResponse	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/fimer/sample-authentication.json")), AuthenticateResponse.class);
		IpRangeDataloggerResponse ipRangeResponse	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/fimer/sample-ip-range-datalogger.json")), IpRangeDataloggerResponse.class);
		IpRangeWebResponse ipRangeWebResponse		= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/fimer/sample-ip-range-web.json")), IpRangeWebResponse.class);
		
		when(this.jsonCaller.get(FimerInverterService.URL + FimerInverterService.ENDPOINT_STATUS, StatusResponse.class)).thenReturn(null);
		when(this.jsonCaller.get(eq(FimerInverterService.URL + FimerInverterService.ENDPOINT_AUTHENTICATE), any(), any(), any())).thenReturn(authenticationResponse);
		when(this.jsonCaller.get(eq(FimerInverterService.URL + FimerInverterService.ENDPOINT_IP_RANGE_DATALOGGER), any(), any(), any())).thenReturn(ipRangeResponse);
		when(this.jsonCaller.get(eq(FimerInverterService.URL + FimerInverterService.ENDPOINT_IP_RANGE_WEB), any(), any(), any())).thenReturn(ipRangeWebResponse);
		
		StatusResponse status = this.service.status();
		assertNull(status);
		
		AuthenticateResponse authentication = this.service.authenticate(user, password, key);
		assertNotNull(authentication);
		assertNotNull(authentication.getResult());
		
		String authKey = authentication.getResult();
		
		IpRangeDataloggerResponse ipRangeDataLogger = this.service.getIpRangeDatalogger(authKey);
		assertNotNull(ipRangeDataLogger);
		assertNotNull(ipRangeDataLogger.getResult());
		assertNotNull(ipRangeDataLogger.getResult().getPrefixes());
		
		IpRangeWebResponse ipRangeDataWeb = this.service.getIpRangeWeb(authKey);
		assertNotNull(ipRangeDataWeb);
		assertNotNull(ipRangeDataWeb.getResult());
		assertNotNull(ipRangeDataWeb.getResult().getPrefixes());
		
//		OrganizationResponse organization = this.service.getPortafolioGroup(authKey);
//		assertNotNull(organization);
//		assertNotNull(organization.getResult());
	}

	//--- Overridden methods --------------------
	@Override
	public InverterService getService() { return this.service; }

	@Override
	public ClientVo createClient() {
		return this.buildClientWith(
			Arrays.asList(
				this.createClientParameter(FimerInverterService.PARAM_USER, "an_user"),
				this.createClientParameter(FimerInverterService.PARAM_PASSWORD, "not-a-real-password"),
				this.createClientParameter(FimerInverterService.PARAM_KEY, "not-a-real-key")
			),
			Arrays.asList(
				this.createLocationParameter(FimerInverterService.PARAM_TIME_ZONE, "-3:00")
			),
			Arrays.asList(
				this.createGeneratorParameter(FimerInverterService.PARAM_DEVICE_ID, "34232"),
				this.createGeneratorParameter(FimerInverterService.PARAM_GENERATOR_LAST_RETRIEVE, "-1160481920")
			)
		);
	}
	
	@Override public void prepareMock() throws InveterServiceException {
		Path classPath								= this.getClassLocation(this.getClass());
		try {
			AuthenticateResponse authenticationResponse	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/fimer/sample-authentication.json")), AuthenticateResponse.class);
			TelemetryDataEnergyTimeseriesResponse data	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/inverters/brand/fimer/sample-telemetry-data.json")), TelemetryDataEnergyTimeseriesResponse.class);
			
			when(this.jsonCaller.get(eq(FimerInverterService.URL + FimerInverterService.ENDPOINT_AUTHENTICATE), any(), any(), any())).thenReturn(authenticationResponse);
			when(this.jsonCaller.get(startsWith(FimerInverterService.URL + FimerInverterService.ENDPOINT_TELEMETRY_DATA_POWER_TIMESERIES), any(), any(), any())).thenReturn(data);

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
