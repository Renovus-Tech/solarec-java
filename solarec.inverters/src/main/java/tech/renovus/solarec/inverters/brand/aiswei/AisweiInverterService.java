package tech.renovus.solarec.inverters.brand.aiswei;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.aiswei.api.DeviceListResponse;
import tech.renovus.solarec.inverters.brand.aiswei.api.InverterOverviewResponse;
import tech.renovus.solarec.inverters.brand.aiswei.api.PlantListResponse;
import tech.renovus.solarec.inverters.brand.aiswei.api.PlantOutputData;
import tech.renovus.solarec.inverters.brand.aiswei.api.PlantOutputResponse;
import tech.renovus.solarec.inverters.brand.aiswei.api.PlantOverviewResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InvertersUtil;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.BooleanUtils;
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
 * API Documentation: https://bronze-rois-22.tiiny.site/
 */
public class AisweiInverterService implements InverterService {

	//--- Private constants ---------------------
	private static final String URL_PROD	= "https://api.general.aisweicloud.com";
	private static final String URL_DEMO	= "http://e710888d3ccb4638a723ff8d03837095-cn-qingdao.aliapi.com/demo/post";
	
	//--- Protected constants -------------------
	protected static final String PARAM_BETA_MODE				= "aiswei.beta";
	protected static final String PARAM_ACCESS_APP_KEY			= "aiswei.client.app_key";
	protected static final String PARAM_USER_TOKEN				= "aiswei.client.user_token";
	protected static final String PARAM_GEN_PLANT_KEY			= "aiswei.generator.plant_key";
	protected static final String PARAM_CLI_LAST_DATE_RETRIEVE	= "aiswei.client.last_retrieve";
	protected static final String PARAM_LOC_LAST_DATE_RETRIEVE	= "aiswei.location.last_retrieve";
	protected static final String PARAM_GEN_LAST_DATE_RETRIEVE	= "aiswei.generator.last_retrieve";

	
	//--- Public constants ----------------------
	public static final String END_PINT_GET_PLANT_OVERVIEW		= "/getPlantOverview";
	public static final String END_PINT_GET_PLANT_OUTPUT		= "/getPlantOutput";
	public static final String END_PINT_GET_INVERTER_OVERVIEW	= "/getInverterOverview";
	public static final String END_PINT_GET_PLANT_LIST			= "/planlist";
	public static final String END_PINT_GET_DEVICE_LIST			= "/devicelist";
	
	public static final String PERIOD_BY_DAYS	= "bydays";
	public static final String PERIOD_BY_MONTH	= "bymonth";
	public static final String PERIOD_BY_YEAR	= "byyear";
	public static final String PERIOD_BY_TOTAL	= "bytotal";
	
	//--- Private properties --------------------
	@Autowired WeatherService weatherService;
	
	private final SimpleDateFormat formatPeriodTime		= new SimpleDateFormat("HH:MM");
	private final SimpleDateFormat formatPeriodDate		= new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat formatPeriodMonth	= new SimpleDateFormat("yyyy-MM");
	private final SimpleDateFormat formatPreiodYear		= new SimpleDateFormat("yyyy");

	private ClientVo cliVo;
	
	//--- Private methods -----------------------
	private String formatDate(String period, Date aDate) {
		switch (period) {
			case PERIOD_BY_DAYS:	return this.formatPeriodDate.format(aDate);
			case PERIOD_BY_MONTH:	return this.formatPeriodMonth.format(aDate);
			case PERIOD_BY_YEAR:	return this.formatPreiodYear.format(aDate);
			default: 				return "";
		}
	}
	
	private String calculateSignature(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
		Mac sha256Hmac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
		sha256Hmac.init(secretKey);
		byte[] hash = sha256Hmac.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(hash);
	}
	
    private static String getUtcDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(new Date());
    }
	
	private Map<String, String> generateHeaders(String url, String appKey, String contentMd5) throws NoSuchAlgorithmException, InvalidKeyException {
		Map<String, String> headers = new HashMap<>();
	    headers.put("Host", "api.general.aisweicloud.com");
        headers.put("Date", getUtcDateTime());
        headers.put("User-Agent", "Apache-HttpClient/4.1.2 (java 1.6)");
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("Accept", "application/json");
		headers.put("X-Ca-Key", appKey);
        headers.put("X-Ca-Request-Mode", "debug");
        headers.put("X-Ca-Version", "1");
        headers.put("X-Ca-Stage", "RELEASE");
		headers.put("X-Ca-Signature-Headers", "X-Ca-Key");
		
		String headersString = 
				headers
				.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
				.map(entry -> entry.getKey() + ":" + entry.getValue())
				.reduce((header1, header2) -> header1 + "\n" + header2)
				.orElse("");
		
		String dataToSign = new StringBuilder()
				.append("GET") 
				.append("\n")
				.append(headers.get("Accept"))
				.append("\n")
				.append(contentMd5)
				.append("\n")
				.append(headers.get("Content-Type"))
				.append("\n")
                .append(headers.get("Date"))
                .append("\n")
                .append(headersString)
                .append(url)
                .toString();
		
		String caSignature = this.calculateSignature(dataToSign, appKey);
		
		headers.put("X-Ca-Signature", caSignature);
		
		return headers;
	}
	
	private List<GenDataVo> process(GeneratorVo generator, PlantOutputResponse data, Date fromDate) throws ParseException {
		//	501 no disponible
		//	502 generationValue
		List<GenDataVo> result = new ArrayList<>();
		
		if (data != null && CollectionUtil.notEmpty(data.getData())) {
			InvertersUtil.logInfo("Amount of data: {0}", Integer.toString(CollectionUtil.size(data.getData())));
			Calendar calendar = GregorianCalendar.getInstance();
			
			for (PlantOutputData aData : data.getData()) {
				Date dataTime = this.formatPeriodTime.parse(aData.getTime());
				
				if (dataTime.before(fromDate)) continue;
				
				calendar.setTime(dataTime);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				int minute = calendar.get(Calendar.MINUTE);
				
				calendar.setTime(fromDate);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				calendar.set(Calendar.AM_PM, Calendar.AM);
				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, minute);
				
				GenDataVo genData = new GenDataVo();
				genData.setCliId(generator.getCliId());
				genData.setGenId(generator.getGenId());
				genData.setDataDate(calendar.getTime());
				genData.setDataTypeId(DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER);
				genData.setDataValue(Double.valueOf(StringUtil.isEmpty(aData.getValue()) ? "0" : aData.getValue()));
				
				result.add(genData);
			}
		} else {
			InvertersUtil.logInfo("No data to process");
		}
		
		return result;
	}
	
	private List<GenDataVo> aggregate(List<GenDataVo> dataValues) {
		//data is for 20 minutes period, we need for 15 minutes period. Aggregate and for each hour and calcualte
		
		Calendar cal = Calendar.getInstance();
		
		Map<Integer, Map<Date, GenDataVo>> values = new HashMap<>(2);
		dataValues.forEach(
                data -> {
                	cal.setTime(data.getDataDate());
            		cal.set(Calendar.MINUTE, 0);
            		Date simpleDate = cal.getTime();
            		
                	values.computeIfAbsent(data.getDataTypeId(), x -> new HashMap<>()).computeIfAbsent(simpleDate, x -> new GenDataVo(data.getCliId(), data.getGenId(), x, data.getDataTypeId()));
                	values.get(data.getDataTypeId()).get(simpleDate).add(data.getDataValue());
                }
        );
		
		List<GenDataVo> result = new ArrayList<>();
		
		values.values().forEach(aDate -> {
			aDate.values().forEach(aData -> {
				double value = aData.getDataValue() == null ? 0 : aData.getDataValue().doubleValue() / (double) 4;
				
				cal.setTime(aData.getDataDate());
				
				cal.set(Calendar.MINUTE, 0);
				new GenDataVo(aData.getCliId(), aData.getGenId(), cal.getTime(), aData.getDataTypeId(), Double.valueOf(value));

				cal.set(Calendar.MINUTE, 15);
				new GenDataVo(aData.getCliId(), aData.getGenId(), cal.getTime(), aData.getDataTypeId(), Double.valueOf(value));
				
				cal.set(Calendar.MINUTE, 30);
				new GenDataVo(aData.getCliId(), aData.getGenId(), cal.getTime(), aData.getDataTypeId(), Double.valueOf(value));
				
				cal.set(Calendar.MINUTE, 45);
				new GenDataVo(aData.getCliId(), aData.getGenId(), cal.getTime(), aData.getDataTypeId(), Double.valueOf(value));
				
			});
		});
		
		return result;
	}
	
	private void retrieveData(InverterData inverterData, LocationVo location, StationVo station, GeneratorVo generator, Date dateFrom, Date to) throws WeatherServiceException, NoSuchAlgorithmException, InvalidKeyException {
		String appKey				= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_ACCESS_APP_KEY);
		boolean betaMode		 	= BooleanUtils.isTrue(InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_BETA_MODE));
		String url					= this.getUrl(betaMode);
		String plantKey				= InvertersUtil.getParameter(generator, PARAM_GEN_PLANT_KEY);

		InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_PARAMETER_DATE_TIME), DateUtil.formatDateTime(to, DateUtil.FMT_PARAMETER_DATE_TIME));
		PlantOutputResponse data	= this.getPlantOutput(url, appKey, plantKey, PERIOD_BY_DAYS, dateFrom);
		
		try {
			List<GenDataVo> generatorData = this.process(generator, data, dateFrom);
			generatorData = this.aggregate(generatorData);
			
			if (CollectionUtil.notEmpty(generatorData)) {
				CollectionUtil.addAll(inverterData.getGeneratorData(), generatorData);
				
				Date lastDate = generatorData.stream().max(Comparator.comparing(GenDataVo::getDataDate)).get().getDataDate();
				
				InvertersUtil.setParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE, Long.toString(lastDate.getTime()));
				
				CollectionUtil.addAll(inverterData.getStationData(), this.weatherService.retrieveWeatherData(location, station, dateFrom, lastDate));
				
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(lastDate);
				cal.add(Calendar.MINUTE, 15); //we need next 15 min due to aggregation
				
				if (! cal.getTime().equals(dateFrom) && cal.getTime().before(to)) this.retrieveData(inverterData, location, station, generator, cal.getTime(), to);
				
			}
			
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(CollectionUtil.size(generatorData)));
		} catch (ParseException e) {
			LoggerService.inverterLogger().error("Error parsing data: " + e.getLocalizedMessage(), e);
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(-1));
		}
	}
	
	//--- Overridden methods --------------------
	@Override
	public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}

	@Override public boolean canRetrieve() { return true; }
	@Override public boolean continueWithStats() { return true; }
	@Override public String getReasonWhyCantRetrieve() {return null; }

	@Override public InverterData retrieveData() throws InveterServiceException {
		Calendar cal = Calendar.getInstance();
		
		InverterData inverterData = new InverterData(new ArrayList<>(), new ArrayList<>());
		
		if (CollectionUtil.notEmpty(this.cliVo.getLocations())) {
			for (LocationVo location : this.cliVo.getLocations()) {
				if (CollectionUtil.isEmpty(location.getStations())) {
					LoggerService.inverterLogger().error("Can't fina station for client: " + this.cliVo.getCliName() + " - location: " + location.getLocName());
					continue;
				}
				
				StationVo station = location.getStations().iterator().next();
				
				if (CollectionUtil.notEmpty(location.getGenerators())) {
					for (GeneratorVo generator : location.getGenerators()) {
						boolean isDemoData 		= BooleanUtils.isTrue(InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_BETA_MODE));
						String genLastRetrieve	= InvertersUtil.getParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE);
						Date dateFrom 			= InvertersUtil.calculateDateFrom(genLastRetrieve);

						cal.setTime(dateFrom);
						
						if (isDemoData) {
							cal.add(Calendar.YEAR, -1);
							dateFrom = cal.getTime();
						}
						
						cal.add(Calendar.DAY_OF_YEAR, 1);
						cal.add(Calendar.MILLISECOND, -1);
						
						Date to = cal.getTime();
						
						try {
							this.retrieveData(inverterData, location, station, generator, dateFrom, to);
						} catch (WeatherServiceException | NoSuchAlgorithmException | InvalidKeyException e) {
							throw new InveterServiceException(e);
						}
					}
				}
			}
		}
		
		
		return inverterData;
	}

	//--- Public methods ------------------------
	public String getUrl(boolean forProd) { return forProd ? URL_PROD : URL_DEMO; }
	
	public PlantOverviewResponse getPlantOverview(String url, String appKey, String plantKey) throws NoSuchAlgorithmException, InvalidKeyException {
		Map<String, String> params = new HashMap<>();
		params.put("key", plantKey);
		
		return JsonCaller.get(
				url + END_PINT_GET_PLANT_OVERVIEW, 
				this.generateHeaders(url, appKey, ""), 
				params, 
				PlantOverviewResponse.class
			);
	}
		
	public PlantOutputResponse getPlantOutput(String url, String appKey, String plantKey, String period, Date aDate) throws NoSuchAlgorithmException, InvalidKeyException {
		Map<String, String> params = new HashMap<>();
		params.put("key", plantKey);
		params.put("period", period);
		params.put("date", this.formatDate(period, aDate));
		
		return JsonCaller.get(
				url + END_PINT_GET_PLANT_OUTPUT, 
				this.generateHeaders(url, appKey, ""), 
				params, 
				PlantOutputResponse.class
			);
	}
	
	public InverterOverviewResponse getInverterOverview(String url, String appKey, String plantKey) throws NoSuchAlgorithmException, InvalidKeyException {
		Map<String, String> params = new HashMap<>();
		params.put("key", plantKey);
		
		return JsonCaller.get(
				url + END_PINT_GET_INVERTER_OVERVIEW, 
				this.generateHeaders(url, appKey, ""), 
				params, 
				InverterOverviewResponse.class
			);
	}
	
	public PlantListResponse getPlantList(String url, String appKey, String usrToken) throws NoSuchAlgorithmException, InvalidKeyException {
		Map<String, String> params = new HashMap<>();
		params.put("token", usrToken);
		params.put("page", "");
		params.put("size", "20");
		
		return JsonCaller.get(
				url + END_PINT_GET_PLANT_LIST, 
				this.generateHeaders(url, appKey, ""), 
				params, 
				PlantListResponse.class
			);
	}
	
	public DeviceListResponse getDeviceList(String url, String appKey, String plantKey) throws NoSuchAlgorithmException, InvalidKeyException {
		Map<String, String> params = new HashMap<>();
		params.put("key", plantKey);
		
		return JsonCaller.get(
				url + END_PINT_GET_DEVICE_LIST, 
				this.generateHeaders(url, appKey, ""), 
				params, 
				DeviceListResponse.class
			);
	}
	
}
