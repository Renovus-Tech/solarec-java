package tech.renovus.solarec.inverters.brand.solarEdge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.solarEdge.api.SiteEnergyResponse;
import tech.renovus.solarec.inverters.brand.solarEdge.api.SiteListResponse;
import tech.renovus.solarec.inverters.brand.solarEdge.api.Value;
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
 * API Documentation: https://knowledge-center.solaredge.com/sites/kc/files/se_monitoring_api.pdf
 */

public class SolarEdgeInverterService implements InverterService {

	//--- Private constants ---------------------
	private static final String URL_PROD						= "https://monitoringapi.solaredge.com";
	
	private static final String ENDPOINT_SITE_LIST				= "/sites/list";
	private static final String ENDPOINT_SITE_ENERGY			= "/site/1/energy";
	
	//--- Protected constants -------------------
	protected static final String PARAM_ACCESS_APP_KEY			= "solarEdge.client.api_key";
	protected static final String PARAM_GEN_SITE_ID				= "solarEdge.generator.site_id";
	protected static final String PARAM_CLI_LAST_DATE_RETRIEVE	= "solarEdge.client.last_retrieve";
	protected static final String PARAM_LOC_LAST_DATE_RETRIEVE	= "solarEdge.location.last_retrieve";
	protected static final String PARAM_GEN_LAST_DATE_RETRIEVE	= "solarEdge.generator.last_retrieve";

	
	///--- Public constants ---------------------
	public static final String TIME_UNIT_QUARTER_OF_AN_HOUR	= "QUARTER_OF_AN_HOUR";
	public static final String TIME_UNIT_HOUR				= "HOUR";
	public static final String TIME_UNIT_DAY				= "DAY";
	public static final String TIME_UNIT_WEEK				= "WEEK";
	public static final String TIME_UNIT_MONTH				= "MONTH";
	public static final String TIME_UNIT_YEAR				= "YEAR";

	//--- Private properties --------------------
	@Autowired WeatherService weatherService;
	private final SimpleDateFormat formatDateTime	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final SimpleDateFormat formatDate		= new SimpleDateFormat("yyyy-MM-dd");
	private ClientVo cliVo;
	
	//--- Private methods -----------------------
	private List<GenDataVo> process(GeneratorVo generator, SiteEnergyResponse data, Date fromDate) throws ParseException {
		//	501 no disponible
		//	502 generationValue
		List<GenDataVo> result = new ArrayList<>();
		
		if (data != null && data.getEnergy() != null && CollectionUtil.notEmpty(data.getEnergy().getValues())) {
			InvertersUtil.logInfo("Amount of data: {0}", Integer.toString(CollectionUtil.size(data.getEnergy().getValues())));
			for (Value aData : data.getEnergy().getValues()) {
				Date dataDate = this.formatDateTime.parse(aData.getDate());
				
				if (dataDate.before(fromDate)) continue;
				
				GenDataVo genData = new GenDataVo();
				genData.setCliId(generator.getCliId());
				genData.setGenId(generator.getGenId());
				genData.setDataDate(dataDate);
				genData.setDataTypeId(DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER);
				genData.setDataValue((aData.getValue() == null ? Double.valueOf(0) : aData.getValue()));
				
				result.add(genData);
			}
		} else {
			InvertersUtil.logInfo("No data to process");
		}
		
		return result;
	}

	private void retrieveData(InverterData inverterData, LocationVo location, StationVo station, GeneratorVo generator, Date dateFrom, Date to) throws WeatherServiceException {
		String appKey		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_ACCESS_APP_KEY);
		String url			= this.getUrl();
		
		InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_PARAMETER_DATE_TIME), DateUtil.formatDateTime(to, DateUtil.FMT_PARAMETER_DATE_TIME));
		
		Integer siteId				= Integer.valueOf(InvertersUtil.getParameter(generator, PARAM_GEN_SITE_ID));
		SiteEnergyResponse data = this.getSiteEnergy(url, appKey, siteId, dateFrom, to, TIME_UNIT_QUARTER_OF_AN_HOUR);
		
		try {
			List<GenDataVo> generatorData = this.process(generator, data, dateFrom);
			
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

	@Override
	public InverterData retrieveData() throws InveterServiceException {
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

	//--- Public methods ------------------------
	public String getUrl() { return URL_PROD; }
	
	public SiteListResponse getSites(String url, String apiKey) {
		Map<String, String> params = new HashMap<>();
		params.put("api_key", apiKey);
		
		return JsonCaller.get(
				url + ENDPOINT_SITE_LIST,
				params, 
				SiteListResponse.class
			);
	}
	
	public SiteEnergyResponse getSiteEnergy(String url, String apiKey, Integer siteId, Date startDate, Date endDate, String timeUnit) {
		Map<String, String> params = new HashMap<>();
		params.put("api_key", apiKey);
		params.put("siteId", siteId.toString());
		params.put("startDate", this.formatDate.format(startDate));
		params.put("endDate", this.formatDate.format(endDate));
		params.put("timeUnit", timeUnit);
		
		return JsonCaller.get(
				url + ENDPOINT_SITE_ENERGY, 
				params, 
				SiteEnergyResponse.class
			);
	}
}
