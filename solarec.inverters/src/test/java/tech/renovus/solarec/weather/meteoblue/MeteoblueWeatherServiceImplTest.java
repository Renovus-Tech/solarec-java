package tech.renovus.solarec.weather.meteoblue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import tech.renovus.solarec.util.FileUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.weather.meteoblue.api.WeatherData;

@RunWith(MockitoJUnitRunner.class)
public class MeteoblueWeatherServiceImplTest {

	@Mock public MeteoblueWeatherServiceImpl service;
	
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
		Path classPath			= getClassLocation(this.getClass());
		String sampleString		= FileUtil.readFile(new File(classPath.toFile(), "sample-meteoblue-data.json"));
		WeatherData sampleData	= JsonUtil.toObject(sampleString, WeatherData.class);
		
		MeteoblueConfiguration configuration = new MeteoblueConfiguration();
		configuration.setMaxDaysPast(Integer.valueOf(7));
		this.service = new MeteoblueWeatherServiceImpl(configuration);

		LocationVo locVo = new LocationVo();
		locVo.setLocCoordLat(-25.635967);
		locVo.setLocCoordLng(-54.505396);

		//wroking in mock
		
//		when(
//			this.service.retrieveData(locVo, any(Date.class), any(Date.class), false)
//		).thenReturn(sampleData);
//		
//
//		StationVo staVo = new StationVo();
//		Exception error = null;
//
//		Calendar cal = GregorianCalendar.getInstance();
//		cal.add(Calendar.DAY_OF_YEAR, -5);
//		Date dateTo = cal.getTime();
//		cal.add(Calendar.DAY_OF_YEAR, -1);
//		Date dateFrom = cal.getTime();
//
//
//		try {
//			Collection<StaDataVo> data = service.retrieveWeatherData(locVo, staVo, dateFrom, dateTo);
//			assertNotNull(data);
//			assertTrue(CollectionUtil.notEmpty(data));
//		} catch (WeatherServiceException e) {
//			error = e;
//		}
//
//		assertNull(error);
	}
}
