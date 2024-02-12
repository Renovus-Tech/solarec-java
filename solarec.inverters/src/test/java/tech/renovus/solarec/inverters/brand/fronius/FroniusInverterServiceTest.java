package tech.renovus.solarec.inverters.brand.fronius;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import tech.renovus.solarec.inverters.brand.fronius.api.InfoReleaseResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.HistoryDataResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.PvSystemsListResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.user.InfoUserResponse;
import tech.renovus.solarec.util.CollectionUtil;

public class FroniusInverterServiceTest {

	//--- Private properties --------------------
	private String accessKeyId;
	private String accessKeyValue;
	
	//--- Init methods --------------------------
	@Before
	public void setVariables() {
		this.accessKeyId = System.getProperty("fronius_access_key_id");
		this.accessKeyValue = System.getProperty("fronius_access_key_value");
	}
	
	//--- Test methods --------------------------
	@Test
	public void testPrivateProperties() {
		/**
		 * If test fails, make sure that you run the testing with the following system.properties:
		 *   - fronius_access_key_id
		 *   - fronius_access_key_value
		 *   
		 * Example of execution: -Dfronius_access_key_id=<access_key_id_here> -Dfronius_access_key_value=<acess_key_id_value_here>
		 */
		assertNotNull(this.accessKeyId);
		assertNotNull(this.accessKeyValue);
	}
	
	@Test
	public void testInfoRelease() {
		FroniusInverterService service = new FroniusInverterService();
		InfoReleaseResponse response = service.getInfoRelease(this.accessKeyId, this.accessKeyValue);
		assertNotNull(response);
		assertFalse(response.hasError());
		assertNotNull(response.getReleaseVersion());
		assertNotNull(response.getReleaseDate());
	}

	@Test
	public void testInfoUser() {
		FroniusInverterService service = new FroniusInverterService();
		InfoUserResponse response = service.getInfoUser(this.accessKeyId, this.accessKeyValue);
		assertNotNull(response);
		assertFalse(response.hasError());
		assertNotNull(response.getName());
		assertEquals(response.getName().getFirstName(), "Api");
		assertEquals(response.getName().getLastName(), "Demo");
	}
	
	@Test
	public void testPvSystemsList() {
		FroniusInverterService service = new FroniusInverterService();
		PvSystemsListResponse response = service.getPvSystemsList(this.accessKeyId, this.accessKeyValue);
		assertNotNull(response);
		assertFalse(response.hasError());
		assertNotNull(response.getPvSystemIds());
		assertTrue(CollectionUtil.notEmpty(response.getPvSystemIds()));
	}
	
	@Test
	public void testPvSystemsHistoryData() {
		FroniusInverterService service = new FroniusInverterService();
		PvSystemsListResponse response = service.getPvSystemsList(this.accessKeyId, this.accessKeyValue);
		assertNotNull(response);
		assertFalse(response.hasError());
		assertNotNull(response.getPvSystemIds());
		assertTrue(CollectionUtil.notEmpty(response.getPvSystemIds()));
		
		String pvSystemsId = response.getPvSystemIds().iterator().next();
		
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

		HistoryDataResponse data = service.getPvSystemsHistData(this.accessKeyId, this.accessKeyValue, pvSystemsId, from, to);
		assertNotNull(data);
		assertFalse(response.hasError());
		assertEquals(data.getPvSystemId(), pvSystemsId);
		assertNotNull(data.getData());
		assertTrue(CollectionUtil.notEmpty(data.getData()));
	}
}
