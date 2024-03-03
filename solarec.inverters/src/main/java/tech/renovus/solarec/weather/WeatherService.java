package tech.renovus.solarec.weather;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;

public interface WeatherService {
	
	public static class WeatherServiceException extends Exception {
		
		private static final long serialVersionUID = -4151013261263513247L;
		
		public WeatherServiceException() { super(); }
		public WeatherServiceException(String msg) { super(msg); }
		public WeatherServiceException(Exception parent) { super(parent); }
		public WeatherServiceException(String msg, Exception parent) { super(msg, parent); }
	}

	//--- Public constants ---------------------
	public static final String ISO_FORMATTER_PATTERN = "yyyy-MM-dd'T'HH:mm:ss 'GMT'Z";
	public static final String UTC_FORMATTER_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATE_HOUR_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMATTER_PATTERN = "yyyy-MM-dd";
	public static final SimpleDateFormat ISO_FORMATTER = new SimpleDateFormat(ISO_FORMATTER_PATTERN);
	public static final SimpleDateFormat DATE_HOUR_FORMATTER = new SimpleDateFormat(DATE_HOUR_FORMATTER_PATTERN);
	public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMATTER_PATTERN);
	
	Collection<StaDataVo> retrieveWeatherData(LocationVo locVo, StationVo station, Date dateFrom, Date dateTo) throws WeatherServiceException;

}
