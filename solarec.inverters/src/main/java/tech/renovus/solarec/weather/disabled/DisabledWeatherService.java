package tech.renovus.solarec.weather.disabled;

import java.util.Collection;
import java.util.Date;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService;

@Service
@ConditionalOnProperty(name = "solarec.service.weather.provider", havingValue = "disabled")
public class DisabledWeatherService implements WeatherService {

	@Override
	public Collection<StaDataVo> retrieveWeatherData(LocationVo locVo, StationVo station, Date dateFrom, Date dateTo) throws WeatherServiceException {
		/* Disabled service */
		return null;
	}

}
