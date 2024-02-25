package tech.renovus.solarec.inverters.brand.fimer;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.fimer.api.authenticate.AuthenticateResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.status.StatusResponse;
import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;

public class FimerInverterServiceTest {

	//--- Private properties --------------------
	private String fimerUser;
	private String fimerPassword;
	private String fimerKey;
	
	private FimerInverterService service;
	
	private ClientVo client;
	
	//--- Private methods -----------------------
	private CliDataDefParameterVo createParameter(String paramName, String paramValue) {
		DataDefParameterVo paramVo = new DataDefParameterVo();
		paramVo.setDataDefParName(paramName);
		
		CliDataDefParameterVo result = new CliDataDefParameterVo();
		result.setCliDataDefParValue(paramValue);
		result.setDataDefParameter(paramVo);
		
		return result;
	}
	
	//--- Init methods --------------------------
	@Before
	public void init() {
//		this.fimerUser = System.getProperty("fimer_user");
//		this.fimerPassword = System.getProperty("fimer_passsword");
//		this.fimerKey = System.getProperty("fimer_key");
//		
//		ClientVo client = new ClientVo();
//		client.add(this.createParameter(FimerInverterService.PARAM_USER, this.fimerUser));
//		client.add(this.createParameter(FimerInverterService.PARAM_PASSWORD, this.fimerPassword));
//		client.add(this.createParameter(FimerInverterService.PARAM_KEY, this.fimerKey));
//		
//		this.service = new FimerInverterService();;
	}
	
	
	//--- Testing methods -----------------------
	@Test
	public void testPrivateProperties() {
//		/**
//		 * If test fails, make sure that you run the testing with the following system.properties:
//		 *   - fimer_user
//		 *   - fimer_passsword
//		 *   - fimer_key
//		 *   
//		 * Example of execution: -Dfimer_user=<user_here> -Dfimer_passsword=<password_here> -Dfimer_key=<key_here>
//		 */
//		assertNotNull(this.fimerUser);
//		assertNotNull(this.fimerPassword);
//		assertNotNull(this.fimerKey);
	}
//	
////	@Test Last testing faild due to API endpoint not responding
////	public void testStatus() {
////		StatusResponse status = this.service.status();
////		assertNotNull(status);
////	}
//
//	@Test
//	public void testValidateConfiguration() {
//		assertFalse(service.validateConfiguration(this.client));
//	}
//	
//	@Test 
//	public void testAuthenticate() {
//		AuthenticateResponse response = this.service.authenticate(this.fimerUser, this.fimerPassword, this.fimerKey);
//		
//		assertNotNull(response);
//		assertNotNull(response.getResult());
//	}
	
	
}
