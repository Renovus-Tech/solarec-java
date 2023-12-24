package tech.renovus.solarec.inverters.branch.fimer;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import tech.renovus.solarec.inverters.branch.fimer.api.status.StatusResponse;

public class FimerInverterServiceTest {

	@Test
	public void testStatus() {
		FimerInverterService service = new FimerInverterService();
		StatusResponse status = service.status();
		assertNotNull(status);
	}

}
