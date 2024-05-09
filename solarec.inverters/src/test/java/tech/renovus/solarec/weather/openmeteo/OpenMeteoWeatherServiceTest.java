package tech.renovus.solarec.weather.openmeteo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService.WeatherServiceException;

public class OpenMeteoWeatherServiceTest {

	//--- Tests ---------------------------------
	@Test public void testService() {
		LocationVo locVo = new LocationVo();
		locVo.setLocCoordLat(-25.635967);
		locVo.setLocCoordLng(-54.505396);
		
		StationVo staVo = new StationVo();
		Exception error = null;
		OpenMeteoWeatherServiceImpl service = new OpenMeteoWeatherServiceImpl();

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -5);
		Date dateTo = cal.getTime();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		Date dateFrom = cal.getTime();

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
