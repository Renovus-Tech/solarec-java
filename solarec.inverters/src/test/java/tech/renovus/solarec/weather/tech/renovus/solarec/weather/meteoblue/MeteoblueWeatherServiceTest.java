package tech.renovus.solarec.weather.tech.renovus.solarec.weather.meteoblue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService.WeatherServiceException;
import tech.renovus.solarec.weather.meteoblue.MeteoblueConfiguration;
import tech.renovus.solarec.weather.meteoblue.MeteoblueWeatherServiceImpl;

public class MeteoblueWeatherServiceTest {

	//--- Private properties --------------------
	private static String key;
	
	//--- Init methods --------------------------
	@BeforeClass
	public static void init() {
		MeteoblueWeatherServiceTest.key		= System.getProperty("meteoblue_key");
		
		boolean allDataRequired = StringUtil.notEmpty(MeteoblueWeatherServiceTest.key);
		
		Assume.assumeTrue("Skipping tests because test data is missing. Added the following system.properties to execute tests: meteoblue_key", allDataRequired);
	}
	
	//--- Tests ---------------------------------
	@Test public void testService() {
		MeteoblueConfiguration config = new MeteoblueConfiguration();
		config.setKey(MeteoblueWeatherServiceTest.key);
		
		assertEquals(MeteoblueWeatherServiceTest.key, config.getKey());
		
		LocationVo locVo = new LocationVo();
		locVo.setLocCoordLat(null);
		locVo.setLocCoordLng(null);
		locVo.setLocGmt(key);
		
		StationVo staVo = new StationVo();
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -5);
		Date dateTo = cal.getTime();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		Date dateFrom = cal.getTime();
		
		MeteoblueWeatherServiceImpl service = new MeteoblueWeatherServiceImpl(config);
		Exception error = null;
		try {
			Collection<StaDataVo> data = service.retrieveWeatherData(locVo, staVo, dateFrom, dateTo);
			assertNotNull(data);
			assertTrue(CollectionUtil.isEmpty(data));
		} catch (WeatherServiceException e) {
			error = e;
		}
		
		assertNull(error);

		locVo.setLocCoordLat(-25.635967);
		locVo.setLocCoordLng(-54.505396);

		try {
			Collection<StaDataVo> data = service.retrieveWeatherData(locVo, staVo, dateFrom, dateTo);
			assertNotNull(data);
			assertTrue(CollectionUtil.isEmpty(data));
		} catch (WeatherServiceException e) {
			error = e;
		}
		
		assertNull(error);
	}
}
