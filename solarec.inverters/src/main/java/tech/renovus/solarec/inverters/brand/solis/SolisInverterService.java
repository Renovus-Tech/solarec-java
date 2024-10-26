package tech.renovus.solarec.inverters.brand.solis;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.solis.api.inverterDay.InverterDayRequest;
import tech.renovus.solarec.inverters.brand.solis.api.inverterDay.InverterDayResponse;
import tech.renovus.solarec.inverters.brand.solis.api.inveterList.InveterListRequest;
import tech.renovus.solarec.inverters.brand.solis.api.inveterList.InveterListResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InvertersUtil;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.custom.chart.alerts.AlertTrigger;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService;
import tech.renovus.solarec.weather.WeatherService.WeatherServiceException;

/**
 * Documentation: https://drive.google.com/file/d/1NpSK5Dc4ricvTQsMUXoAMafrRnh7GgoS/view
 */
public class SolisInverterService implements InverterService {

	//--- Private constants ---------------------
	private static final String LOG_PREFIX	= "[SOLIS] ";
	private static final String URL_PROD	= "https://www.soliscloud.com:13333";
	
	private static final String ENDPOINT_DEVICE_LIST	= "/v1/api/inverterList";
	private static final String ENDPOINT_INVERTER_DAY	= "/v1/api/inverterDay";
	
	private static final String PARAM_API_ID					= "solis.api_id";
	private static final String PARAM_API_SECRETE				= "solis.api_secret";
	private static final String PARAM_GEN_DEVICE_ID				= "solis.generator.device_id";
	private static final String PARAM_GEN_DEVICE_SN				= "solis.generator.device_sn";
	private static final String PARAM_GEN_LAST_DATE_RETRIEVE	= "solis.generator.last_retrieve";

	//--- Resources -----------------------------
	@Autowired RenovusSolarecConfiguration configuration;
	@Autowired WeatherService weatherService;

	//--- Private properties --------------------
	private final SimpleDateFormat formatDate							= new SimpleDateFormat("yyyy'-'MM'-'dd");
	private final SimpleDateFormat formatDateTime						= new SimpleDateFormat("yyyy'-'MM'-'dd HH':'mm':'ss");
	private ClientVo cliVo;
	private boolean continueWithStats = true;

	//--- Private methods -----------------------
	private String getDigest(String test) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		md.update(test.getBytes());
		return Base64.getEncoder().encodeToString(md.digest());
	}

	private String getGMTTime() {
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new

		SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		return sdf.format(cd.getTime());
	}
	
	private String generateHMACSHA1(String key, String message) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKeySpec);
        byte[] hmacSha1Bytes = mac.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(hmacSha1Bytes);
    }
	
	private Map<String, String> getHeaders(Object content, String apiId, String apiSecret, String endpoint) throws InvalidKeyException, NoSuchAlgorithmException, JsonProcessingException {
		String contentMd5 = this.getDigest(JsonUtil.toString(content));
		String date = this.getGMTTime();
		String Sign = Base64.getEncoder().encodeToString(generateHMACSHA1(apiSecret, "POST" + "\n" + contentMd5 + "\n" + "application/json;charset=UTF-8" + "\n" + date + "\n" + endpoint).getBytes());
		String authorization = "API " + apiId + ":" + Sign;

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-MD5", contentMd5);
		headers.put("Date", date);
		headers.put("Authorization", authorization);
		
		return headers;
	}
	
	private Date adjustGmt(Date aDate, String gmt) {
		if (StringUtil.isEmpty(gmt)) {
			return aDate;
		}
		String[] parts = StringUtil.split(gmt, ":");
		if (parts == null || parts.length != 2) {
			return aDate;
		}
		int hours = Integer.parseInt(parts[0]);
		int minutes = Integer.parseInt(parts[1]);
		
		if (hours < 0) {
			minutes *= -1;
		}
		return DateUtil.addUnit(aDate, Calendar.MINUTE, (hours * 60) + minutes);
	}
	
	private void retrieveData(InverterData inverterData, LocationVo location, StationVo station, GeneratorVo generator, Date dateFrom, Date to) throws InveterServiceException, WeatherServiceException {
		String gmtToUse = location.getLocGmt();
		if (StringUtil.isEmpty(gmtToUse)) {
			gmtToUse = this.cliVo.getCliGmt();
		}
		
		String appId			= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_API_ID);
		String apiSecret		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_API_SECRETE);
		String deviceID			= InvertersUtil.getParameter(generator, PARAM_GEN_DEVICE_ID);
		String deviceSN			= InvertersUtil.getParameter(generator, PARAM_GEN_DEVICE_SN);
		Integer timeZone		= Integer.valueOf(gmtToUse);
		
		InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_PARAMETER_DATE_TIME), DateUtil.formatDateTime(to, DateUtil.FMT_PARAMETER_DATE_TIME));
		
		InverterDayResponse data = this.getInvereterDay(appId, apiSecret, deviceID, deviceSN, dateFrom, timeZone);
		
		if (data == null || ! BooleanUtils.isTrue(data.getSuccess())) {
			this.continueWithStats = false;
			String errorMessage = data == null ? "No data response from server." : "Error parsing data: " + data.getCode() + " - " + data.getMsg();
			LoggerService.inverterLogger().error(LOG_PREFIX + errorMessage);
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(-1));
			
			Date dateError = new Date();
			dateError = DateUtil.clearTime(dateError);
			dateError = DateUtil.addUnit(dateError, Calendar.MINUTE, -1);
			
			AlertTrigger trigger = new AlertTrigger();
			trigger.setDate(formatDate.format(dateError));
			trigger.setType(AlertTrigger.TYPE_CUSTOM);
			trigger.setDescription(
					"Error during data retrieve: " + errorMessage + 
					" - Date to retrieve: " + this.formatDate.format(dateFrom) + 
					" - Date of error: " + this.formatDate.format(new Date()) + 
					" - Location: " + location.getLocName() + 
					" - Inverter: " + generator.getGenName()
				);
			
			try {
				inverterData.add(InvertersUtil.generateAlert(generator, dateError, trigger));
			} catch (InveterServiceException e) {
				LoggerService.inverterLogger().error(LOG_PREFIX + "Error generating alert: " + e.getLocalizedMessage(), e);
			}
			
			return;
		}
		
		try {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(this.formatDate.parse(data.getData().getTimeStr()));
			calendar.set(Calendar.HOUR_OF_DAY, 12);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.AM_PM, Calendar.PM);
			
			GenDataVo dayData = new GenDataVo();
			dayData.setCliId(generator.getCliId());
			dayData.setGenId(generator.getGenId());
			dayData.setDataDate(calendar.getTime());
			dayData.setDataTypeId(DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER);
			dayData.setDataValue(data.getData().getPac());
			
			inverterData.getGeneratorData().add(dayData);

			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.AM_PM, Calendar.AM);

			InvertersUtil.setParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE, Long.toString(calendar.getTimeInMillis()));
			
			CollectionUtil.addAll(inverterData.getStationData(), this.retrieveWeatherData(location, station, dateFrom, calendar.getTime(), gmtToUse));
			
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(1));
		} catch (ParseException e) {
			LoggerService.inverterLogger().error(LOG_PREFIX + "Error parsing data: " + e.getLocalizedMessage(), e);
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(-1));
		}
	}
	
	private Collection<StaDataVo> retrieveWeatherData(LocationVo location, StationVo station, Date dateFrom, Date lastDate, String gmt) throws WeatherServiceException {
		Collection<StaDataVo> result = this.weatherService.retrieveWeatherData(location, station, dateFrom, lastDate);
		
		if (CollectionUtil.notEmpty(result)) {
			result.forEach(data -> data.setDataDate(this.adjustGmt(data.getDataDate(), gmt)));
		}
		
		return result;
	}
	
	//--- Overridden methods --------------------
	@Override public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}
	
	@Override public boolean canRetrieve() { return true; }
	
	@Override public boolean continueWithStats() { return true; }
	
	@Override public String getReasonWhyCantRetrieve() { return null; }
	
	@Override
	public InverterData retrieveData() throws InveterServiceException {
		Calendar cal = Calendar.getInstance();
		
		InverterData inverterData = new InverterData(new ArrayList<>(), new ArrayList<>());
		
		if (CollectionUtil.notEmpty(this.cliVo.getLocations())) {
			for (LocationVo location : this.cliVo.getLocations()) {
				if (CollectionUtil.isEmpty(location.getStations())) {
					LoggerService.inverterLogger().error(LOG_PREFIX + "Can't fina station for client: " + this.cliVo.getCliName() + " - location: " + location.getLocName());
					continue;
				}
				
				StationVo station = location.getStations().iterator().next();
				
				if (CollectionUtil.notEmpty(location.getGenerators())) {
					for (GeneratorVo generator : location.getGenerators()) {
						String genLastRetrieve	= InvertersUtil.getParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE);
						Date dateFrom 			= InvertersUtil.calculateDateFrom(genLastRetrieve);

						cal.setTime(dateFrom);
						cal.add(Calendar.DAY_OF_YEAR, 1);
						cal.add(Calendar.MILLISECOND, -1);
						
						Date to = cal.getTime();
						
						try {
							this.retrieveData(inverterData, location, station, generator, dateFrom, to);
						} catch (WeatherServiceException e) {
							throw new InveterServiceException(e);
						}
					}
				}
			}
		}
		
		return inverterData;
	}

	//--- Data methods --------------------------
	public InveterListResponse getInvereterList(String appId, String apiSecret) throws InvalidKeyException, JsonProcessingException, NoSuchAlgorithmException {
		InveterListRequest request = new InveterListRequest();
		
		String url = URL_PROD + ENDPOINT_DEVICE_LIST;
		
		return JsonCaller.post(url, this.getHeaders(request, appId, apiSecret, ENDPOINT_DEVICE_LIST), request, InveterListResponse.class);
	}

	
	public InverterDayResponse getInvereterDay(String appId, String apiSecret, String devideID, String deviceSN, Date date, Integer timeZone) throws InveterServiceException {
		InverterDayRequest request = new InverterDayRequest()
				.withId(devideID)
				.withSn(deviceSN)
				.withTime(this.formatDate.format(date))
				.withTimeZone(timeZone);
		
		String url = URL_PROD + ENDPOINT_INVERTER_DAY;
		
		try {
			return JsonCaller.post(url, this.getHeaders(request, appId, apiSecret, ENDPOINT_INVERTER_DAY), request, InverterDayResponse.class);
		} catch (InvalidKeyException | JsonProcessingException | NoSuchAlgorithmException e) {
			throw new InveterServiceException(e);
		}
	}
}
