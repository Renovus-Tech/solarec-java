package tech.renovus.solarec.inverters.brand.solarEdge;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.BaseInveterTest;
import tech.renovus.solarec.inverters.brand.solarEdge.api.SiteEnergyResponse;
import tech.renovus.solarec.inverters.brand.solarEdge.api.SiteListResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InverterService.InveterServiceException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FileUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.weather.WeatherService;

public class SolarEdgeInverterServiceTest extends BaseInveterTest {

	//--- Private properties --------------------
	@Mock private JsonCaller jsonCaller;
	@Mock private WeatherService weatherService;

	@InjectMocks private SolarEdgeInverterService service;
	
	//--- Tests ---------------------------------
	@Test 
	public void testCallApi() throws IOException {
		String url			= service.getUrl();
		String apiKey		= "not-real-api-key";

		Path classPath							= this.getClassLocation(this.getClass());
		SiteListResponse sitesMock				= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/solarEdge/sample-sites.json")), SiteListResponse.class);
		SiteEnergyResponse siteEnergyMock 		= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/solarEdge/sample-energy.json")), SiteEnergyResponse.class);

		when(this.jsonCaller.get(eq(url + SolarEdgeInverterService.ENDPOINT_SITE_LIST), any(), any())) .thenReturn(sitesMock);
		when(this.jsonCaller.get(eq(url + SolarEdgeInverterService.ENDPOINT_SITE_ENERGY), any(), any())) .thenReturn(siteEnergyMock);
		
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
	}
	
	//--- Overridden methods --------------------
	@Override
	public InverterService getService() { return this.service; }
	
	@Override
	public ClientVo createClient() {
		return this.buildClientWith(
			Arrays.asList(
				this.createClientParameter(SolarEdgeInverterService.PARAM_ACCESS_APP_KEY, "not-real-api-key")
			),
			null,
			Arrays.asList(
				this.createGeneratorParameter(SolarEdgeInverterService.PARAM_GEN_SITE_ID, "not-real-site-id"),
				this.createGeneratorParameter(SolarEdgeInverterService.PARAM_GEN_LAST_DATE_RETRIEVE, "-1160481920")
			)
		);
	}
	
	@Override public void prepareMock() throws InveterServiceException {
		Path classPath								= this.getClassLocation(this.getClass());
		try {
			SiteEnergyResponse siteEnergyMock 		= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "/tech/renovus/solarec/inverters/brand/solarEdge/sample-energy.json")), SiteEnergyResponse.class);
			
			when(this.jsonCaller.get(eq(SolarEdgeInverterService.URL_PROD + SolarEdgeInverterService.ENDPOINT_SITE_ENERGY), any(), any())) .thenReturn(siteEnergyMock);

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
