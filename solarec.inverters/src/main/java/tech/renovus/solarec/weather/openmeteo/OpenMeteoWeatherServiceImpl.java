package tech.renovus.solarec.weather.openmeteo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService;
import tech.renovus.solarec.weather.openmeteo.api.WeatherData;

/**
 * Site: https://open-meteo.com/
 * API Documentation: https://open-meteo.com/en/docs#start_date=2024-05-08&end_date=2024-05-08&time_mode=time_interval
 * Sample: https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m
 * 
 */
@Service
@ConditionalOnProperty(name = "solarec.service.weather.provider", havingValue = "openmeteo")
public class OpenMeteoWeatherServiceImpl implements WeatherService {

	//--- Private constants --------------------
	private static final String URL_SOLAR				= "https://api.open-meteo.com/v1/forecast";
	private static final String URL_SOLAR_HISTORICAL	= "https://historical-forecast-api.open-meteo.com/v1/forecast";
	
	//--- Resources -----------------------------
	public OpenMeteoWeatherServiceImpl() { /* required for fast testing */ }
	
	//--- Private methods -----------------------
	private Map<String, String> getParams(LocationVo vo, Date dateFrom, Date dateEnd, boolean includeTime) {
		Map<String, String> result = new HashMap<>();
		result.put("latitude", StringUtil.toString(vo.getLocCoordLat(), StringUtil.EMPTY_STRING));
		result.put("longitude", StringUtil.toString(vo.getLocCoordLng(), StringUtil.EMPTY_STRING));
		result.put("hourly", "temperature_2m,direct_radiation,cloud_cover,precipitation");
		
		result.put("start_date", DATE_FORMATTER.format(dateFrom));
		result.put("end_date", DATE_FORMATTER.format(dateEnd));
		
		if (StringUtil.notEmpty(vo.getLocGmt())) {
			String timeZone = vo.getLocGmt();
			timeZone = timeZone.substring(1);
			timeZone = timeZone.substring(0, timeZone.length() - 2);
			result.put("tz", "GMT+" + timeZone);
		}
		
		SimpleDateFormat datetimeFormat = null;
		if (includeTime) {
			datetimeFormat = new SimpleDateFormat(UTC_FORMATTER_PATTERN);
			datetimeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		} else {
			datetimeFormat = new SimpleDateFormat(DateUtil.FMT_PARAMETER_DATE);
		}

		return result;
	}

	private void createData(StationVo station, int typeId, Integer value, Date dateDate, Collection<StaDataVo> result) {
		this.createData(station, typeId, value == null ? null : Double.valueOf(value), dateDate, result);
	}
	
	private void createData(StationVo station, int typeId, Double value, Date dateDate, Collection<StaDataVo> result) {
		if (value == null) {
			return;
		}
		
		//Data is every hour, we need to split it to 15 minutes period
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dateDate);
		
		for (int i = 0; i < 4; i++) {
			StaDataVo staData = new StaDataVo();
			staData.setCliId(station.getCliId());
			staData.setStaId(station.getStaId());
			staData.setDataDate(cal.getTime());
			staData.setDataTypeId(Integer.valueOf(typeId));
			staData.setDataValue(value);
			
			result.add(staData);
			
			cal.add(Calendar.MINUTE, 15);
		}
	}

	//--- Overridden methods --------------------
	@Override public Collection<StaDataVo> retrieveWeatherData(LocationVo locVo, StationVo station, Date dateFrom, Date dateTo) throws WeatherServiceException {
		LoggerService.weatherLogger().info("[OpenMeteo] Start data retrieve from " + DATE_FORMATTER.format(dateFrom) + " from " + DATE_FORMATTER.format(dateTo) + " for coords: " + locVo.getLocCoordLat() + " - " + locVo.getLocCoordLng());

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		
		boolean forHistorical			= dateFrom.before(cal.getTime());
		String urlToUse					= forHistorical ? URL_SOLAR_HISTORICAL : URL_SOLAR;
		
		Map<String, String> params		= this.getParams(locVo, dateFrom, dateTo, false);
		WeatherData data				= JsonCaller.get(urlToUse, params, WeatherData.class);
		Collection<StaDataVo> result	= new ArrayList<>();
		
		if (data == null || data.getHourly() == null || CollectionUtil.isEmpty(data.getHourly().getTime())) {
			LoggerService.weatherLogger().info("[OpenMeteo] No data");
			return result;
		}
		
		LoggerService.weatherLogger().info("[OpenMeteo] Amount of data: " + CollectionUtil.size(data.getHourly().getTime()));
		
		try {
			SimpleDateFormat dateTime = new SimpleDateFormat(UTC_FORMATTER_PATTERN_HH_MM);
			int startIndex		= -1;
			int endIndex		= data.getHourly().getTime().size();
			int index			= 0;
			for (String time : data.getHourly().getTime()) {
				Date date = dateTime.parse(time);
				if (startIndex == -1 && date.after(dateFrom)) {
					startIndex = index;
				}
				if (date.before(dateTo)) {
					endIndex = index;
				}
				index ++;
			}
			
			if (startIndex > -1) {
				LoggerService.weatherLogger().info("[OpenMeteo] Amount of data: " + CollectionUtil.size(data.getHourly().getTime()));
				
				if (endIndex == data.getHourly().getTime().size()) {
					endIndex = data.getHourly().getTime().size() - 1;
				}
				
				LoggerService.weatherLogger().info("[OpenMeteo] Amount of data to retrieve: " + (endIndex - startIndex + 1));
				LoggerService.weatherLogger().info("[OpenMeteo] First date: " + data.getHourly().getTime().get(startIndex));
				LoggerService.weatherLogger().info("[OpenMeteo] Last date: " + data.getHourly().getTime().get(endIndex));

				for (int i = startIndex; i <= endIndex; i++) {
					Date dataDate = dateTime.parse(data.getHourly().getTime().get(i));
					
					this.createData(station, DataTypeVo.TYPE_SOLAR_STATION_AMBIENT_TEMPERATURE,	data.getHourly().getTemperature2m().get(i), dataDate, result);
//					this.createData(station, DataTypeVo.TYPE_SOLAR_STATION_MODULE_TEMPERATURE,	(Double) null, dataDate, result);
					this.createData(station, DataTypeVo.TYPE_SOLAR_STATION_IRRADIATION,			data.getHourly().getDirectRadiation().get(i), dataDate, result);
					this.createData(station, DataTypeVo.TYPE_SOLAR_STATION_TOTAL_CLOUD_COVER,	data.getHourly().getCloudCover().get(i), dataDate, result);
					this.createData(station, DataTypeVo.TYPE_SOLAR_STATION_PRECIPITATION,		data.getHourly().getPrecipitation().get(i), dataDate, result);
				}
			}
		} catch (ParseException e) {
			throw new WeatherServiceException(e);
		}
		
		return result;
		
	}
}