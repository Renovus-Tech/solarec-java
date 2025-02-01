package tech.renovus.solarec.inverters.brand.enphase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.enphase.api.TokenResponse;
import tech.renovus.solarec.inverters.brand.enphase.api.data.Interval;
import tech.renovus.solarec.inverters.brand.enphase.api.data.InverterDataResponse;
import tech.renovus.solarec.inverters.brand.enphase.api.devices.DevicesResponse;
import tech.renovus.solarec.inverters.brand.enphase.api.systems.SystemsResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InvertersUtil;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService;
import tech.renovus.solarec.weather.WeatherService.WeatherServiceException;

/**
 * Documentation: https://developer-v4.enphase.com/docs/quickstart.html
 * Documentation: https://drive.google.com/file/d/1NpSK5Dc4ricvTQsMUXoAMafrRnh7GgoS/view


curl --location -g --request GET 'https://api.enphaseenergy.com/api/v4/systems?key=b2b2fd806ed13efb463691b436957798' \
--header 'Authorization: Bearer unique_access_token'


https://www.postman.com/blue-shuttle-199906/enphase/request/uyf5taj/retrieve-entrez-tokens

 *
 *
 */
public class EnphaseInverterService implements InverterService {

	//--- Private constants ---------------------
	private static final String URL_PROD		= "https://api.enphaseenergy.com";
	
	private static final String ENDPOINT_AUTH	= "/oauth/authorize?response_type=code&client_id=${client_id}&redirect_uri=https://api.enphaseenergy.com/oauth/redirect_uri";
	private static final String ENDPOINT_TOKEN	= "/oauth/token?grant_type=authorization_code&redirect_uri=https://api.enphaseenergy.com/oauth/redirect_uri&code=${code}";
	
	private static final String ENDPOINT_SYSTEMS						= "/api/v4/systems";
	private static final String ENDPOINT_SYSTEM_DEVICES					= "/api/v4/systems/${system_id}/devices";
	private static final String ENDPOINT_SYSTEM_DEVICE_MICROS_DATA		= "/api/v4/systems/{system_id}/devices/micros/{serial_no}/telemetry?start_at={start_at}&granularity={granularity}";
	private static final String ENDPOINT_SYSTEM_DEVICE_ACBS_DATA		= "/api/v4/systems/{system_id}/devices/acbs/{serial_no}/telemetry?start_at={start_at}&granularity={granularity}";
	private static final String ENDPOINT_SYSTEM_DEVICE_ENCHARGES_DATA	= "/api/v4/systems/{system_id}/devices/encharges/{serial_no}/telemetry?start_at={start_at}&granularity={granularity}";
	
	public static final String GRANULARITY_5_MINS	= "5mins";
	public static final String GRANULARITY_15_MINS	= "15mins";
	public static final String GRANULARITY_DAY		= "day";
	public static final String GRANULARITY_WEEK		= "week";
	
	//---- Public constants ---------------------
	public static final String PARAM_CLIENT_ID					= "enphase.client_id";
	public static final String PARAM_CLIENT_SECRET				= "enphase.client_secret";
	public static final String PARAM_API_KEY					= "enphase.api_key";
	public static final String PARAM_SYSTEM_ID					= "enphase.system_id";
	public static final String PARAM_GENERATOR_DEVICE_ID		= "enphase.generator.device_id";
	public static final String PARAM_GENERATOR_LAST_RETRIEVE	= "enphase.generator.last_retrieve";
	
	//--- Private properties --------------------
	private @Autowired WeatherService weatherService;
	private @Autowired JsonCaller jsonCaller;

	private final SimpleDateFormat formatDate							= new SimpleDateFormat("yyyy-MM-dd");
	private ClientVo cliVo;
	private TokenResponse authentication;
	
	//--- Private methods -----------------------
	private Map<String, String> getHeaders(String authCode, String apiKey) {
		Map<String, String> headers = new HashMap<>(2);
		
		headers.put("Authorization", "Bearer " + authCode);
		headers.put("key", apiKey);
		
		return headers;
	}
	
	private boolean isAuthenticated() {
		String authenticationKey = this.authentication == null ? null : this.authentication.getAccessToken();
		return StringUtil.notEmpty(authenticationKey);
	}
	
	private List<GenDataVo> process(GeneratorVo generator, InverterDataResponse data, Date dateFrom) {
		List<GenDataVo> result = new ArrayList<>();
		
		if (data != null && CollectionUtil.notEmpty(data.getIntervals())) {
			Calendar cal = GregorianCalendar.getInstance();
			
			for (Interval aData : data.getIntervals()) {
				cal.setTimeInMillis(aData.getEndAt());
				cal.add(Calendar.MINUTE, -5);
				GenDataVo genData = new GenDataVo();
				
				double value = 0;
				if (aData.getPowr() != null) {
					value = aData.getPowr() / (double) 1000;
				}
				
				genData.setCliId(generator.getCliId());
				genData.setGenId(generator.getGenId());
				genData.setDataDate(cal.getTime());
				genData.setDataTypeId(DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER);
				genData.setDataValue(value);
				
				result.add(genData);
			}
		}
		
		return result;
	}
	
	
	private List<GenDataVo> aggregate(List<GenDataVo> dataValues) {
		Calendar cal = Calendar.getInstance();
		
		Map<Date, GenDataVo> result = new HashMap<>(CollectionUtil.size(dataValues) / 4);
		dataValues.forEach(
                data -> {
                	cal.setTime(data.getDataDate());
            		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) / 15 * 15);
            		Date simpleDate = cal.getTime();
                	
                	result.computeIfAbsent(simpleDate, x -> new GenDataVo(data.getCliId(), data.getGenId(), x, data.getDataTypeId()));
                	result.get(simpleDate).add(data.getDataValue());
                }
        );
		
		result.values().forEach(x -> x.sum());
		
		return new ArrayList<>(result.values());
	}
	
	//--- Overridden methods --------------------
	@Override public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}
	
	@Override public boolean canRetrieve() { return true; }
	
	@Override public boolean continueWithStats() { return this.isAuthenticated(); }
	
	@Override public String getReasonWhyCantRetrieve() { return null; }
	
	@Override public InverterData retrieveData() {
		long t = System.currentTimeMillis();
		LoggerService.inverterLogger().info("[{}] Start retrieve for: {} ({})", t, this.cliVo.getCliName(), this.cliVo.getCliId());
		InverterData result = new InverterData(new ArrayList<>(), new ArrayList<>());

		Calendar cal = GregorianCalendar.getInstance();
		
		if (CollectionUtil.notEmpty(this.cliVo.getLocations())) {
			for (LocationVo location : this.cliVo.getLocations()) {
				if (CollectionUtil.notEmpty(location.getGenerators())) {
					if (CollectionUtil.isEmpty(location.getStations())) {
						LoggerService.inverterLogger().error("Can't fina station for client: " + this.cliVo.getCliName() + " - location: " + location.getLocName());
						continue;
					}
					
					StationVo station = location.getStations().iterator().next();
					
					for (GeneratorVo generator : location.getGenerators()) {
						
						String clientId		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_CLIENT_ID);
						String clietnSecret = InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_CLIENT_SECRET);
						String apiKey		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_API_KEY);
						String systemId		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_SYSTEM_ID);
						
						String authCode = this.authorize(
								clientId,
								InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_API_KEY)
							);
						this.authentication = this.getToken(
								clientId,
								clietnSecret,
								authCode
							);
						
						boolean authenticated = this.isAuthenticated();

						LoggerService.inverterLogger().info("[{}] Authentication ok: {} ", t, authenticated);
						if (authenticated) {
							String timeZone = location.getLocGmt();
							if (StringUtil.isEmpty(timeZone)) {
								timeZone = this.cliVo.getCliGmt();
							}

							String serialNo			= InvertersUtil.getParameter(generator, PARAM_GENERATOR_DEVICE_ID);
							String getLastRetrieve	= InvertersUtil.getParameter(generator, PARAM_GENERATOR_LAST_RETRIEVE);
							Date dateFrom 			= InvertersUtil.calculateDateFrom(getLastRetrieve);
							
							cal.setTime(dateFrom);
							cal.add(Calendar.DAY_OF_YEAR, 1);
							cal.add(Calendar.MILLISECOND, -1);
							
							Date dateTo = cal.getTime();
	
							InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_DATE), DateUtil.formatDateTime(dateTo, DateUtil.FMT_DATE));
							
							InverterDataResponse data = this.retrieveData(
									ENDPOINT_SYSTEM_DEVICE_ACBS_DATA, 
									this.authentication.getAccessToken(), 
									apiKey, 
									systemId, 
									serialNo, 
									dateTo, 
									GRANULARITY_DAY
								);
							
							List<GenDataVo> generatorData = this.process(generator, data, dateFrom);
							generatorData = this.aggregate(generatorData);
							
							if (CollectionUtil.notEmpty(generatorData)) {
								CollectionUtil.addAll(result.getGeneratorData(), generatorData);
								
								Collections.reverse(generatorData);
								GenDataVo lastData = generatorData.iterator().next();
								
								try {
									CollectionUtil.addAll(result.getStationData(), this.weatherService.retrieveWeatherData(location, station, dateFrom, dateTo));
								} catch (WeatherServiceException e) {
//									throw new InveterServiceException(e);
								}
								
								InvertersUtil.setParameter(generator, PARAM_GENERATOR_LAST_RETRIEVE, Long.toString(lastData.getDataDate().getTime()));
							}
							
							InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(CollectionUtil.size(generatorData)));
						} else {
							String error = this.authentication == null ? "n/a" : StringUtil.toString(this.authentication.getCode());
							String message = this.authentication == null ? "n/a" : this.authentication.getMessage();
							String exception = this.authentication == null ? "n/a" : this.authentication.getReason();
							LoggerService.inverterLogger().error("[{}] Error: {} | {} | {}", t, error, message, exception);
						}
					}
				}
			}
		}

		LoggerService.inverterLogger().info("[{t}] End retrieve for: {client} ({cliId})", t, this.cliVo.getCliName(), this.cliVo.getCliId());

		return result;
	}

	//--- API Methods ---------------------------
	public String authorize(String clientId, String apiKey) {
		return "code";
	}
	
	public TokenResponse getToken(String clientId, String clientSecret, String code) {
		String url = URL_PROD + ENDPOINT_TOKEN;
		url = StringUtil.replace(url, "${code}", code);
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));
		
		return this.jsonCaller.post(url, headers, null, TokenResponse.class);
	}
	
	public SystemsResponse retrieveSystems(String authCode, String apiKey) {
		return this.jsonCaller.get(
				URL_PROD + ENDPOINT_SYSTEMS, 
				this.getHeaders(authCode, apiKey), 
				null, 
				SystemsResponse.class
			);
	}
	
	public DevicesResponse retrieveDevices(String authCode, String apiKey, String systemId) {
		String url = URL_PROD + ENDPOINT_SYSTEM_DEVICES;
		url = StringUtil.replace(url, "${system_id}", systemId);
		return this.jsonCaller.get(
				url,
				this.getHeaders(authCode, apiKey),
				null,
				DevicesResponse.class
			);
	}
	
	public InverterDataResponse retrieveData(String fromEndPoint, String authCode, String apiKey, String systemId, String serialNo, Date date, String granularity) {
		String url = URL_PROD + fromEndPoint;
		url = StringUtil.replace(url, "{system_id}", systemId);
		url = StringUtil.replace(url, "{serial_no}", serialNo);
		url = StringUtil.replace(url, "{start_at}", this.formatDate.format(date));
		url = StringUtil.replace(url, "{start_at}", this.formatDate.format(date));
		url = StringUtil.replace(url, "{granularity}", granularity);
		
		return this.jsonCaller.get(
				url,
				this.getHeaders(authCode, apiKey),
				null,
				InverterDataResponse.class
			);
	}
}
