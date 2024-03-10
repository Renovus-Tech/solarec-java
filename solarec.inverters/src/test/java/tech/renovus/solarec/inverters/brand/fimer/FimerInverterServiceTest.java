package tech.renovus.solarec.inverters.brand.fimer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.fimer.api.authenticate.AuthenticateResponse;
import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;

public class FimerInverterServiceTest {

	//--- Private properties --------------------
	private static String fimerUser;
	private static String fimerPassword;
	private static String fimerKey;
	
	private static FimerInverterService service;
	
	private static ClientVo client;
	
	//--- Private methods -----------------------
	private static CliDataDefParameterVo createParameter(String paramName, String paramValue) {
		DataDefParameterVo paramVo = new DataDefParameterVo();
		paramVo.setDataDefParName(paramName);
		
		CliDataDefParameterVo result = new CliDataDefParameterVo();
		result.setCliDataDefParValue(paramValue);
		result.setDataDefParameter(paramVo);
		
		return result;
	}
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void init() {
		FimerInverterServiceTest.fimerUser		= System.getProperty("fimer_user");
		FimerInverterServiceTest.fimerPassword	= System.getProperty("fimer_passsword");
		FimerInverterServiceTest.fimerKey		= System.getProperty("fimer_key");
		
		ClientVo client = new ClientVo();
		client.add(FimerInverterServiceTest.createParameter(FimerInverterService.PARAM_USER, FimerInverterServiceTest.fimerUser));
		client.add(FimerInverterServiceTest.createParameter(FimerInverterService.PARAM_PASSWORD, FimerInverterServiceTest.fimerPassword));
		client.add(FimerInverterServiceTest.createParameter(FimerInverterService.PARAM_KEY, FimerInverterServiceTest.fimerKey));
		
		FimerInverterServiceTest.service = new FimerInverterService();
		
		boolean allDataRequired = FimerInverterServiceTest.fimerUser != null && FimerInverterServiceTest.fimerPassword != null && FimerInverterServiceTest.fimerKey != null;
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: fimer_user, fimer_passsword, fimer_key", allDataRequired);
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
		 * Example of execution: -Dfimer_user=<user_here> -Dfimer_passsword=<password_here> -Dfimer_key=<key_here>
		 */
		assertNotNull(FimerInverterServiceTest.fimerUser);
		assertNotNull(FimerInverterServiceTest.fimerPassword);
		assertNotNull(FimerInverterServiceTest.fimerKey);
	}
	
	@Test
	public void testValidateConfiguration() {
		assertFalse(service.validateConfiguration(FimerInverterServiceTest.client));
	}
	
	@Test 
	public void testAuthenticate() {
		AuthenticateResponse response = FimerInverterServiceTest.service.authenticate(FimerInverterServiceTest.fimerUser, FimerInverterServiceTest.fimerPassword, FimerInverterServiceTest.fimerKey);
		
		assertNotNull(response);
		assertNotNull(response.getResult());
	}
	
	
}
