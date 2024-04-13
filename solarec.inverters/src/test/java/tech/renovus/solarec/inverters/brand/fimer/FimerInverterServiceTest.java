package tech.renovus.solarec.inverters.brand.fimer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.TestingUtil;
import tech.renovus.solarec.inverters.brand.fimer.api.authenticate.AuthenticateResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.datalogger.IpRangeDataloggerResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.web.IpRangeWebResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.organization.OrganizationResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.status.StatusResponse;
import tech.renovus.solarec.inverters.common.InverterService.InverterData;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;

public class FimerInverterServiceTest {

	//--- Private properties --------------------
	private static String user;
	private static String password;
	private static String key;
	
	private static FimerInverterService service;
	private static ClientVo client;
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void init() {
		FimerInverterServiceTest.user		= System.getProperty("fimer_user");
		FimerInverterServiceTest.password	= System.getProperty("fimer_passsword");
		FimerInverterServiceTest.key		= System.getProperty("fimer_key");
		
		boolean allDataRequired = FimerInverterServiceTest.user != null && FimerInverterServiceTest.password != null && FimerInverterServiceTest.key != null;
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: fimer_user, fimer_passsword, fimer_key", allDataRequired);
		
		FimerInverterServiceTest.client = new ClientVo();
		FimerInverterServiceTest.client.add(TestingUtil.createParameter(FimerInverterService.PARAM_USER, FimerInverterServiceTest.user));
		FimerInverterServiceTest.client.add(TestingUtil.createParameter(FimerInverterService.PARAM_PASSWORD, FimerInverterServiceTest.password));
		FimerInverterServiceTest.client.add(TestingUtil.createParameter(FimerInverterService.PARAM_KEY, FimerInverterServiceTest.key));
		
		FimerInverterServiceTest.service = new FimerInverterService();
	}
	
	//--- Testing methods -----------------------
	@Test
	public void testPrivateProperties() {
		/**
		 * If test fails, make sure that you run the testing with the following system.properties:
		 *   - fimer_user
		 *   - fimer_passsword
		 *   - fimer_key
		 *   
		 * Example of execution: -D<param_1>=<value_1> -D<param_2=<value_2> -D<param_n>=<value_n>
		 */
		assertNotNull(FimerInverterServiceTest.user);
		assertNotNull(FimerInverterServiceTest.password);
		assertNotNull(FimerInverterServiceTest.key);
	}
	
	@Test 
	public void testCallApi() {
		Exception error = null;
		
		StatusResponse status = service.status();
		assertNotNull(status);
		
		AuthenticateResponse authentication = service.authenticate(user, password, key);
		assertNotNull(authentication);
		assertNotNull(authentication.getResult());
		
		String authKey = authentication.getResult();
		
		IpRangeDataloggerResponse ipRangeDataLogger = service.getIpRangeDatalogger(authKey);
		assertNotNull(ipRangeDataLogger);
		assertNotNull(ipRangeDataLogger.getResult());
		assertNotNull(ipRangeDataLogger.getResult().getPrefixes());
		
		IpRangeWebResponse ipRangeDataWeb = service.getIpRangeWeb(authKey);
		assertNotNull(ipRangeDataWeb);
		assertNotNull(ipRangeDataWeb.getResult());
		assertNotNull(ipRangeDataWeb.getResult().getPrefixes());
		
		OrganizationResponse organization = service.getPortafolioGroup(authKey);
		assertNotNull(organization);
		assertNotNull(organization.getResult());
		
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
