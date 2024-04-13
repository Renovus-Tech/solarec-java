package tech.renovus.solarec.inverters.brand.sofar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.TestingUtil;
import tech.renovus.solarec.inverters.brand.sofar.api.AuthorizationResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.DevideListResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.PermissionResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.StationHistoryDataResponse;
import tech.renovus.solarec.inverters.brand.sofar.api.StationListResponse;
import tech.renovus.solarec.inverters.common.InverterService.InverterData;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;

public class SofarInverterServiceTest {

	//--- Private properties --------------------
	private static String sofarBeta						= null;
	private static String sofarClientAppId				= null;
	private static String sofarClientAppSecret			= null;
	private static Integer sofarClientApporgId			= null;
	private static String sofarClientAppUser			= null;
	private static String sofarClientAppPassword		= null;

	private static SofarInverterService service;
	private static ClientVo client;
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void init() {
		SofarInverterServiceTest.sofarBeta					= System.getProperty("sofar_beta");
		SofarInverterServiceTest.sofarClientAppId			= System.getProperty("sofar_client_app_id");
		SofarInverterServiceTest.sofarClientAppSecret		= System.getProperty("sofar_client_app_secret");
		try {SofarInverterServiceTest.sofarClientApporgId	= Integer.valueOf(System.getProperty("sofar_client_app_org_id")); } catch (Exception e) { /* do nothing */ }
		SofarInverterServiceTest.sofarClientAppUser			= System.getProperty("sofar_client_app_user");
		SofarInverterServiceTest.sofarClientAppPassword		= System.getProperty("sofar_client_app_password");
		
		boolean allDataRequired = 
				SofarInverterServiceTest.sofarBeta != null && 
				SofarInverterServiceTest.sofarClientAppId != null && 
				SofarInverterServiceTest.sofarClientAppSecret != null && 
				SofarInverterServiceTest.sofarClientApporgId != null && 
				SofarInverterServiceTest.sofarClientAppUser != null && 
				SofarInverterServiceTest.sofarClientAppPassword != null;
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: sofar_beta, sofar_client_app_id, sofar_client_app_secret, sofar_client_app_org_id, sofar_client_app_user, sofar_client_app_password", allDataRequired);

		SofarInverterServiceTest.client = new ClientVo();
		SofarInverterServiceTest.client.add(TestingUtil.createParameter(SofarInverterService.PARAM_BETA_MODE, SofarInverterServiceTest.sofarBeta));
		SofarInverterServiceTest.client.add(TestingUtil.createParameter(SofarInverterService.PARAM_ACCESS_APP_ID, SofarInverterServiceTest.sofarClientAppId));
		SofarInverterServiceTest.client.add(TestingUtil.createParameter(SofarInverterService.PARAM_ACCESS_APP_SECRET, SofarInverterServiceTest.sofarClientAppSecret));
		SofarInverterServiceTest.client.add(TestingUtil.createParameter(SofarInverterService.PARAM_ACCESS_APP_ORG_ID, StringUtil.toString(SofarInverterServiceTest.sofarClientApporgId)));
		SofarInverterServiceTest.client.add(TestingUtil.createParameter(SofarInverterService.PARAM_ACCESS_APP_USER, SofarInverterServiceTest.sofarClientAppUser));
		SofarInverterServiceTest.client.add(TestingUtil.createParameter(SofarInverterService.PARAM_ACCESS_APP_PASSWORD, SofarInverterServiceTest.sofarClientAppPassword));
		
		SofarInverterServiceTest.service = new SofarInverterService();
	}
	
	//--- Tests ---------------------------------
	@Test
	public void testPrivateProperties() {
		/**
		 * If test fails, make sure that you run the testing with the following system.properties:
		 *   - sofar_beta
		 *   - sofar_client_app_id
		 *   - sofar_client_app_secret
		 *   - sofar_client_app_org_id
		 *   - sofar_client_app_user
		 *   - sofar_client_app_password
		 *   
		 * Example of execution: -D<param_1>=<value_1> -D<param_2=<value_2> -D<param_n>=<value_n>
		 */
		assertNotNull(SofarInverterServiceTest.sofarBeta);
		assertNotNull(SofarInverterServiceTest.sofarClientAppId);
		assertNotNull(SofarInverterServiceTest.sofarClientAppSecret);
		assertNotNull(SofarInverterServiceTest.sofarClientApporgId);
		assertNotNull(SofarInverterServiceTest.sofarClientAppUser);
		assertNotNull(SofarInverterServiceTest.sofarClientAppPassword);
	}
	
	
	@Test 
	public void testCallApi() {
		String url			= service.getUrl(! BooleanUtils.isTrue(SofarInverterServiceTest.sofarBeta));
		
		assertNotNull(url);
		
		Exception error = null;
	
		AuthorizationResponse authorization = service.getAuthorize(
				url, 
				sofarClientAppId, 
				sofarClientAppSecret, 
				sofarClientApporgId, 
				sofarClientAppUser, 
				sofarClientAppPassword
			);
		
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
		DevideListResponse stationDevices = service.getStationDeviceList(url, authCode, stationId);
		assertNotNull(stationDevices);
		assertNotNull(stationDevices.getTotal());
		assertNotNull(stationDevices.getDeviceList());
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
