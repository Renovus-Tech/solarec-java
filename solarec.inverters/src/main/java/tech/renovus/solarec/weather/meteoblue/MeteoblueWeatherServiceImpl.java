package tech.renovus.solarec.weather.meteoblue;

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

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.db.data.dao.interfaces.LocDataWeatherDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService;
import tech.renovus.solarec.weather.meteoblue.api.WeatherData;

/**
 * 
 * URLs:
 *   .- Solar - https://my.meteoblue.com/packages/basic-1h_clouds-1h_solar-1h?apikey=<key>&lat=-34.9033&lon=-56.1882&asl=34&format=json&windspeed=ms-1&history_days=4
 *
 * Documentation:
 *   .- https://docs.meteoblue.com/en/weather-apis/packages-api/overview
 */

/**
 * Sample weather



{
    "metadata": {
        "name": "",
        "latitude": -34.90,
        "longitude": -56.19,
        "height": 34,
        "timezone_abbrevation": "GMT-03",
        "utc_timeoffset": -3.00,
        "modelrun_utc": "2024-03-02 00:00",
        "modelrun_updatetime_utc": "2024-03-02 10:41",
        "kwp": null,
        "slope": null,
        "facing": null,
        "tracking": null
    },
    "units": {
        "time": "YYYY-MM-DD hh:mm",
        "precipitation_probability": "percent",
        "cloudcover": "percent",
        "sunshinetime": "minutes",
        "pressure": "hPa",
        "relativehumidity": "percent",
        "visibility": "m",
        "radiation": "Wm-2",
        "co": "ug/m3",
        "precipitation": "mm",
        "temperature": "C",
        "windspeed": "ms-1",
        "winddirection": "degree"
    },
    "data_1h": {
        "time": [
            "2024-02-27 00:00",
            "2024-02-27 01:00",
            "2024-02-27 02:00",
            "2024-02-27 03:00",
            "2024-02-27 04:00",
            "2024-02-27 05:00",
            "2024-02-27 06:00",
            "2024-02-27 07:00",
            "2024-02-27 08:00",
            "2024-02-27 09:00",
            "2024-02-27 10:00",
            "2024-02-27 11:00",
            "2024-02-27 12:00",
            ...
            "2024-03-09 08:00",
            "2024-03-09 09:00"
        ],
        "sunshinetime": [
            null,
            0,
            0,
            0,
            0,
            0,
            0,
            32,
            60,
            60,
            50,
            34,
            43,
            39,
          ...,
            34,
            42
        ],
        "lowclouds": [
            0,
            0,
            33,
            33,
            0,
            0,
            0,
            0,
            0,
            15,
            40,
            ...,
            28,
            18
        ],
        "midclouds": [
            0,
            0,
            0,
            0,
            0,
            ...,
            27,
            38
        ],
        "highclouds": [
            0,
            0,
            0,
            0,
            0,
            0,
            ...,
            1,
            2,
            3
        ],
        "visibility": [
            10310,
            10460,
            10660,
            10860,
            ...,
            9820,
            9020,
            9420,
            10210
        ],
        "totalcloudcover": [
            0,
            0,
            ...,
            41,
            28,
            38
        ],
        "precipitation": [
            0.00,
            0.00,
            0.00,
            0.00,
            ...,
            0.00,
            0.00,
            0.00
        ],
        "snowfraction": [
            0.00,
            0.00,
            0.00,
            ...,
            0.00,
            0.00,
            0.00
        ],
        "rainspot": [
            "0000000000000000000000000000000000000000000000000",
            "0000000000000000000000000000000000000000000000000",
            "0000000000000000000000000000000000000000000000000",
            "0000000000000000000000000000000000000000000000000",
            ...,
            "0000000000000000000000000000000220000011000000000",
            "0000000000000000000000000000000000000000000000000"
        ],
        "temperature": [
            20.13,
            20.12,
            ...,
            21.11,
            22.15
        ],
        "felttemperature": [
            20.99,
            21.18,
            21.42,
            21.80,
            ...,
            20.09,
            20.89,
            21.96
        ],
        "pictocode": [
            13,
            13,
            13,
            13,
            ...,
            10,
            4,
            4
        ],
        "windspeed": [
            3.65,
            3.40,
            2.94,
            2.47,
            2.14,
            1.77,
            ...,
            5.39,
            5.75,
            5.99
        ],
        "winddirection": [
            60,
            61,
            62,
            65,
            63,
            62,
            ,
            20,
            16
        ],
        "relativehumidity": [
            94,
            94,
            92,
            92,
            ...,
            91,
            93,
            89
        ],
        "sealevelpressure": [
            1013.44,
            1012.99,
            1012.36,
            1011.81,
            1011.39,
            1011.06,
            ...,
            1012.83,
            1012.94
        ],
        "precipitation_probability": [
            3,
            1,
            1,
            0,
            1,
            ...,
            19,
            20,
            20
        ],
        "convective_precipitation": [
            0.00,
            0.00,
            0.00,
            0.00,
            ...,
            0.00,
            0.00,
            0.00
        ],
        "isdaylight": [
            0,
            0,
            0,
            0,
            ...,
            1,
            1,
            1
        ],
        "uvindex": [
            0,
            0,
            0,
            0,
            ...,
            0,
            1,
            2
        ],
        "gni_instant": [
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            291.23,
            462.73,
            ...,
            127.34,
            157.06,
            9.43
        ],
        "gni_backwards": [
            null,
            0.00,
            0.00,
            0.00,
            ...,
            22.26,
            337.84,
            45.17
        ],
        "dni_instant": [
            0.00,
            0.00,
            0.00,
            0.00,
            ...,
            86.47,
            0.26
        ],
        "dni_backwards": [
            null,
            0.00,
            0.00,
            0.00,
            0.00,
            ...,
            0.00,
            1.00,
            230.35,
            10.50
        ],
        "dif_instant": [
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            13.28,
            44.64,
            ...,
            46.78,
            58.08,
            11.97
        ],
        "dif_backwards": [
            null,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            4.65,
            23.71,
            65.77,
            ...,
            8.36,
            39.56,
            42.44
        ],
        "ghi_instant": [
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            33.80,
            148.72,
            ...,
            47.25,
            81.44,
            12.09
        ],
        "ghi_backwards": [
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            8.33,
            87.17,
            221.85,
            ...,
            8.45,
            79.17,
            45.56
        ],
        "extraterrestrialradiation_instant": [
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            128.90,
            420.83,
            ...,
            82.06,
            374.30,
            644.97
        ],
        "extraterrestrialradiation_backwards": [
            null,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            0.00,
            29.20,
            278.21,
            ...,
            12.05,
            231.60,
            503.74
        ]
    }
}


 *
 */

@Service
public class MeteoblueWeatherServiceImpl implements WeatherService {

	//--- Private constants --------------------
	private static final String URL_SOLAR	= "https://my.meteoblue.com/packages/basic-1h_clouds-1h_solar-1h";
	
	//--- Resources -----------------------------
	@Autowired MeteoblueConfiguration config;
	@Resource LocationDao locDao;
	@Resource LocDataWeatherDao locDataWeatherDao;
	
	public MeteoblueWeatherServiceImpl() {}
	
	public MeteoblueWeatherServiceImpl(MeteoblueConfiguration config) {
		this.config = config;
	}
	
	//--- Private methods -----------------------
	private Map<String, String> getParams(LocationVo vo, Date dateFrom, Date dateEnd, boolean includeTime) {
		Map<String, String> result = new HashMap<>();
		result.put("lat", StringUtil.toString(vo.getLocCoordLat(), StringUtil.EMPTY_STRING));
		result.put("lon", StringUtil.toString(vo.getLocCoordLng(), StringUtil.EMPTY_STRING));
		result.put("apikey", this.config.getKey());
		
		result.put("asl", "34");
		result.put("format", "json");
		result.put("windspeed", "ms-1");
		result.put("history_days", "1");
		
		result.put("startdate", DATE_FORMATTER.format(dateFrom));
		result.put("enddate", DATE_FORMATTER.format(dateEnd));
		
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
		this.createData(station, typeId, value == null ? null : Double.valueOf(value.intValue()), dateDate, result);
	}
	
	private void createData(StationVo station, int typeId, Double value, Date dateDate, Collection<StaDataVo> result) {
		if (value == null) return; //in same cases, meteoblue returns null values
		
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
		LoggerService.weatherLogger().info("[Meteoblue] Start data retrieve from " + DATE_FORMATTER.format(dateFrom) + " from " + DATE_FORMATTER.format(dateTo) + " for coords: " + locVo.getLocCoordLat() + " - " + locVo.getLocCoordLng());
		
		Map<String, String> params		= this.getParams(locVo, dateFrom, dateTo, false);
		WeatherData data				= JsonCaller.get(URL_SOLAR, params, WeatherData.class);
		int startIndex					= 0;
		int endIndex					= -1;
		Collection<StaDataVo> result	= new ArrayList<>();
		
		if (data == null || data.getData1h() == null || CollectionUtil.isEmpty(data.getData1h().getTime())) {
			LoggerService.weatherLogger().info("[Meteoblue] No data");
			return result;
		}
		LoggerService.weatherLogger().info("[Meteoblue] Amount of data: " + CollectionUtil.size(data.getData1h().getTime()));
		
		try {
			int index = 0;
			for (String time : data.getData1h().getTime()) {
				Date date = WeatherService.DATE_HOUR_FORMATTER.parse(time);
				if (startIndex == -1 && dateFrom.after(date)) startIndex = index;
				if (date.before(dateTo)) endIndex = index;
				index ++;
			}
			
			for (int i = startIndex; i <= endIndex; i++) {
				Date dataDate = WeatherService.DATE_HOUR_FORMATTER.parse(data.getData1h().getTime().get(i));
				
				this.createData(station, DataTypeVo.TYPE_SOLAR_STATION_AMBIENT_TEMPERATURE,	data.getData1h().getTemperature().get(i), dataDate, result);
//				this.createData(station, DataTypeVo.TYPE_SOLAR_STATION_MODULE_TEMPERATURE,	(Double) null, dataDate, result);
				this.createData(station, DataTypeVo.TYPE_SOLAR_STATION_IRRADIATION,			data.getData1h().getGhiInstant().get(i), dataDate, result);
				this.createData(station, DataTypeVo.TYPE_SOLAR_STATION_TOTAL_CLOUD_COVER,	data.getData1h().getTotalcloudcover().get(i), dataDate, result);
				this.createData(station, DataTypeVo.TYPE_SOLAR_STATION_PRECIPITATION,		data.getData1h().getPrecipitation().get(i), dataDate, result);
			}
		} catch (ParseException e) {
			throw new WeatherServiceException(e);
		}
		
		return result;
		
	}
}