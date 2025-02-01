package tech.renovus.solarec.weather.openmeteo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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
import tech.renovus.solarec.weather.openmeteo.api.WeatherData;

@RunWith(MockitoJUnitRunner.class)
public class OpenMeteoWeatherServiceTest {

	@Mock private JsonCaller jsonCaller;
	
	@InjectMocks private OpenMeteoWeatherServiceImpl service = new OpenMeteoWeatherServiceImpl();
	
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
	
	//--- Tests ---------------------------------
	@Test public void testService() throws Exception {
		
		Path classPath			= this.getClassLocation(this.getClass());

		WeatherData forecastData	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/weather/openmeteo/sample-forecast.json")), WeatherData.class);
		WeatherData historicalData	= JsonUtil.toObject(FileUtil.readFile(new File(classPath.toFile(), "tech/renovus/solarec/weather/openmeteo/sample-historical.json")), WeatherData.class);

//		when( this.jsonCaller.get(startsWith(OpenMeteoWeatherServiceImpl.URL_SOLAR_FORECAST), any(),any()) ).thenReturn(forecastData);
		when( this.jsonCaller.get(startsWith(OpenMeteoWeatherServiceImpl.URL_SOLAR_HISTORICAL), any(), any()) ).thenReturn(historicalData);

		
		LocationVo locVo = new LocationVo();
		locVo.setLocCoordLat(-25.635967);
		locVo.setLocCoordLng(-54.505396);
		
		StationVo staVo = new StationVo();
		Exception error = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateFrom = format.parse("2024-05-20");
		Date dateTo = format.parse("2024-06-22");
		
		try {
			Collection<StaDataVo> data = this.service.retrieveWeatherData(locVo, staVo, dateFrom, dateTo);
			assertNotNull(data);
			assertTrue(CollectionUtil.notEmpty(data));
		} catch (WeatherServiceException e) {
			error = e;
		}
		
		assertNull(error);
	}
}
