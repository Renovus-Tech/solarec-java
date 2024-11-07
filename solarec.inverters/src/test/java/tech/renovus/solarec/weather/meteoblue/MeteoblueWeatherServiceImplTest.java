package tech.renovus.solarec.weather.meteoblue;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FileUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService.WeatherServiceException;
import tech.renovus.solarec.weather.meteoblue.api.WeatherData;

@RunWith(MockitoJUnitRunner.class)
public class MeteoblueWeatherServiceImplTest {

	@Mock private JsonCaller jsonCaller;
	
	@InjectMocks private MeteoblueWeatherServiceImpl service = new MeteoblueWeatherServiceImpl();
	
	private Path getClassLocation(Class<?> clazz) {
		try {
			// Get the URL where the class is loaded from
			URL location = clazz.getProtectionDomain().getCodeSource().getLocation();

			// Convert the URL to a path
			return Paths.get(location.toURI());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// --- Tests ---------------------------------
	@Test
	public void testService() throws IOException {
		Path classPath			= this.getClassLocation(this.getClass());
		String sampleString		= FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/weather/meteoblue/sample-data.json"));
		WeatherData sampleData	= JsonUtil.toObject(sampleString, WeatherData.class);
		
		MeteoblueConfiguration configuration = new MeteoblueConfiguration();
		configuration.setMaxDaysPast(Integer.valueOf(7));

		this.service.setConfig(configuration);

		LocationVo locVo = new LocationVo();
		locVo.setLocCoordLat(-25.635967);
		locVo.setLocCoordLng(-54.505396);

		when(
			this.jsonCaller.get(any(String.class), any(Map.class), any(Class.class))
		).thenReturn(sampleData);

		StationVo staVo = new StationVo();
		Exception error = null;

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -5);
		Date dateTo = cal.getTime();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		Date dateFrom = cal.getTime();


		try {
			Collection<StaDataVo> data = this.service.retrieveWeatherData(locVo, staVo, dateFrom, dateTo);
			assertNotNull(data);
			assertTrue(CollectionUtil.notEmpty(data));
		} catch (WeatherServiceException e) {
			error = e;
		}

		assertNull(error);
		
		cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -(configuration.getMaxDaysPast() + 2));
		dateTo = cal.getTime();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		dateFrom = cal.getTime();
		
		try {
			Collection<StaDataVo> data = this.service.retrieveWeatherData(locVo, staVo, dateFrom, dateTo);
			assertNotNull(data);
			assertFalse(CollectionUtil.notEmpty(data));
		} catch (WeatherServiceException e) {
			error = e;
		}
		
		assertNull(error);
	}
}
