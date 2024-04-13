package tech.renovus.solarec.inverters.brand.solarEdge;

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
import tech.renovus.solarec.inverters.brand.solarEdge.api.SiteEnergyResponse;
import tech.renovus.solarec.inverters.brand.solarEdge.api.SiteListResponse;
import tech.renovus.solarec.inverters.common.InverterService.InverterData;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;

public class SolarEdgeInverterServiceTest {

	//--- Private properties --------------------
	private static String apiKey		= null;
	private static String siteId		= null;

	private static SolarEdgeInverterService service;
	private static ClientVo client;
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void init() {
		SolarEdgeInverterServiceTest.apiKey			= System.getProperty("solarEdge_client_api_key");
		SolarEdgeInverterServiceTest.siteId			= System.getProperty("solarEdge_generator_site_id");
		
		boolean allDataRequired = 
				SolarEdgeInverterServiceTest.apiKey != null && 
				SolarEdgeInverterServiceTest.siteId != null
			;
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: solarEdge_client_api_key, solarEdge_generator", allDataRequired);

		SolarEdgeInverterServiceTest.client = new ClientVo();
		SolarEdgeInverterServiceTest.client.add(TestingUtil.createParameter(SolarEdgeInverterService.PARAM_ACCESS_APP_KEY, SolarEdgeInverterServiceTest.apiKey));
		SolarEdgeInverterServiceTest.client.add(TestingUtil.createParameter(SolarEdgeInverterService.PARAM_GEN_SITE_ID, SolarEdgeInverterServiceTest.siteId));

		SolarEdgeInverterServiceTest.service = new SolarEdgeInverterService();
	}
	
	//--- Tests ---------------------------------
	@Test
	public void testPrivateProperties() {
		/**
		 * If test fails, make sure that you run the testing with the following system.properties:
		 *   - solarEdge_client_api_key
		 *   - solarEdge_generator_site_id
		 *   
		 * Example of execution: -D<param_1>=<value_1> -D<param_2=<value_2> -D<param_n>=<value_n>
		 */
		assertNotNull(SolarEdgeInverterServiceTest.apiKey);
		assertNotNull(SolarEdgeInverterServiceTest.siteId);
	}
	
	
	@Test 
	public void testCallApi() {
		String url			= service.getUrl();
		
		assertNotNull(url);
		
		Exception error = null;
	
		SiteListResponse sites = service.getSites(url, apiKey);
		assertNotNull(sites);
		assertNotNull(sites.getSites());
		assertNotNull(sites.getSites().getList());
		assertTrue(CollectionUtil.notEmpty(sites.getSites().getList()));
		
		Integer siteId = sites.getSites().getList().iterator().next().getId();
		
		Calendar calendar	= GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, -10);
		Date aDateStart			= calendar.getTime();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date aDateEnd			= calendar.getTime();
		
		SiteEnergyResponse siteEnergy = service.getSiteEnergy(url, apiKey, siteId, aDateStart, aDateEnd, SolarEdgeInverterService.TIME_UNIT_QUARTER_OF_AN_HOUR);
		assertNotNull(siteEnergy);
		assertNotNull(siteEnergy.getEnergy());
		assertNotNull(siteEnergy.getEnergy().getValues());
		assertTrue(CollectionUtil.notEmpty(siteEnergy.getEnergy().getValues()));
		
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
