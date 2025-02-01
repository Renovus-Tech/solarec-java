package tech.renovus.solarec.weather.meteoblueopen;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService;
import tech.renovus.solarec.weather.meteoblue.MeteoblueWeatherServiceImpl;
import tech.renovus.solarec.weather.openmeteo.OpenMeteoWeatherServiceImpl;

@Service
@ConditionalOnProperty(name = "solarec.service.weather.provider", havingValue = "meteoblueopen")
public class MeteoBlueOpenWeatherServiceImpl implements WeatherService {
	
	
	//--- Private properties --------------------
	@Autowired AutowireCapableBeanFactory autowireCapableBeanFactory;
	
	private final WeatherService meteoblueService;
	private final WeatherService openmeteoService;
	
	@Autowired
	public MeteoBlueOpenWeatherServiceImpl(@Autowired AutowireCapableBeanFactory autowireCapableBeanFactory) {
		this.autowireCapableBeanFactory = autowireCapableBeanFactory;
		
		this.meteoblueService = new MeteoblueWeatherServiceImpl();
		this.openmeteoService = new OpenMeteoWeatherServiceImpl();
		
		this.autowireCapableBeanFactory.autowireBean(this.meteoblueService);
		this.autowireCapableBeanFactory.autowireBean(this.openmeteoService);
	}
	
	//--- Overridden methods --------------------
	@Override public Collection<StaDataVo> retrieveWeatherData(LocationVo locVo, StationVo station, Date dateFrom, Date dateTo) throws WeatherServiceException {
		Collection<StaDataVo> result = this.meteoblueService.retrieveWeatherData(locVo, station, dateFrom, dateTo);
		if (CollectionUtil.isEmpty(result)) result = this.openmeteoService.retrieveWeatherData(locVo, station, dateFrom, dateTo);
		return result;
	}
}
