package tech.renovus.solarec.inverters.brand.fimer;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import tech.renovus.solarec.inverters.brand.fimer.api.status.StatusResponse;
import tech.renovus.solarec.inverters.common.InverterCofigurationVo;

public class FimerInverterServiceTest {

	@Test
	public void testStatus() {
		FimerInverterService service = new FimerInverterService();
		StatusResponse status = service.status();
		assertNotNull(status);
		System.out.println(status.getResult());
	}

	@Test
	public void testValidateConfiguration() {
		InverterCofigurationVo configuration = new InverterCofigurationVo("gadadad","asdadasd!","2asdadadasda");
//		FimerInverterService service = new FimerInverterService();
//		assertFalse(service.validateConfiguration(configuration));
	}
	
}
