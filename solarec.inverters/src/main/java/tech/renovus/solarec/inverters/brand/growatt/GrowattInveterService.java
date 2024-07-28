package tech.renovus.solarec.inverters.brand.growatt;

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
import tech.renovus.solarec.inverters.brand.growatt.api.Energy;
import tech.renovus.solarec.inverters.brand.growatt.api.ListDevicesResponse;
import tech.renovus.solarec.inverters.brand.growatt.api.ListPlantsResponse;
import tech.renovus.solarec.inverters.brand.growatt.api.PlantEnergyResponse;
import tech.renovus.solarec.inverters.brand.growatt.api.PlantPowerResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InvertersUtil;
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.vo.custom.chart.alerts.AlertTrigger;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.weather.WeatherService;
import tech.renovus.solarec.weather.WeatherService.WeatherServiceException;

public class GrowattInveterService implements InverterService {

	//-- Private constants ----------------------
	private static final String LOG_PREFIX	= "[Growatt] ";
	
	private static final String URL_PROD	= "";
	private static final String URL_TEST	= "http://test.growatt.com";
	
	private static final String ENDPOINT_LIST_PLANTS	= "/v1/plant/list";
	private static final String ENDPOINT_LIST_DEVICES	= "/v1/device/list";
	private static final String ENDPOINT_PLANT_POWER	= "/v1/plant/power";
	private static final String ENDPOINT_PLANT_ENERGY	= "/v1/plant/energy";
	
	private static final String HEADER_TOKEN	= "TOKEN";
	
	private static final int AMOUNT_PARTS	= 96; //24 * 60 / 15;
	

	//--- Public constants ----------------------
	public static final String PARAM_BETA_MODE										= "growatt.test";
	public static final String PARAM_APP_TOKEN										= "growatt.client.app_token";
	protected static final String PARAM_CLI_LAST_DATE_RETRIEVE						= "growatt.client.last_retrieve";
	protected static final String PARAM_LOC_LAST_DATE_RETRIEVE						= "growatt.location.last_retrieve";
	protected static final String PARAM_GEN_PLANT_ID								= "growatt.generator.plant_id";
	protected static final String PARAM_GEN_LAST_DATE_RETRIEVE						= "growatt.generator.last_retrieve";
	
	protected static final String PARAM_DATA_DEMO									= "growatt.data_demo";
	
	public static final String TIME_UNIT_DAY	= "day";
	public static final String TIME_UNIT_MONTH	= "month";
	public static final String TIME_UNIT_YEAR	= "year";
	
	
	//--- Private properties --------------------
	@Autowired WeatherService weatherService;

	private ClientVo cliVo;
	private boolean continueWithStats = true;
	private final SimpleDateFormat formatDate	= new SimpleDateFormat("yyyy-MM-dd");
	
	//--- Private methods -----------------------
	private Map<String, String> getHeaders(String token) {
		Map<String,String> result = new HashMap<>();
		result.put(HEADER_TOKEN, token);
		result.put("Accept", "application/json");
		return result;
	}
	
	private List<GenDataVo> process(GeneratorVo generator, PlantEnergyResponse data, Date fromDate) throws ParseException {
		List<GenDataVo> result = new ArrayList<>();
		
		if (data != null && data.getData() != null && CollectionUtil.notEmpty(data.getData().getEnergys())) {
			InvertersUtil.logInfo("Amount of data: {0}", Integer.toString(CollectionUtil.size(data.getData().getEnergys())));
			for (Energy aData : data.getData().getEnergys()) {
				Date dataDate = this.formatDate.parse(aData.getDate());
				
				if (dataDate.before(fromDate)) {
					continue;
				}
				
				GenDataVo genData = new GenDataVo();
				genData.setCliId(generator.getCliId());
				genData.setGenId(generator.getGenId());
				genData.setDataDate(dataDate);
				genData.setDataTypeId(DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER);
				genData.setDataValue(Double.valueOf(aData.getEnergy()));
				
				result.add(genData);
			}
		} else {
			InvertersUtil.logInfo("No data to process");
		}
		
		return result;
	}
	
	private List<GenDataVo> split(List<GenDataVo> dataValues) {
		Calendar cal = Calendar.getInstance();
		List<GenDataVo> result = new ArrayList<>(Double.valueOf(CollectionUtil.size(dataValues) * AMOUNT_PARTS).intValue());
		
		dataValues.forEach(
                data -> {
                	Double value = data.getDataValue() == null ? null : data.getDataValue().doubleValue() / AMOUNT_PARTS;
                	cal.setTime(data.getDataDate());
                	for (int i = 0; i < AMOUNT_PARTS; i++) {
                		cal.add(Calendar.MINUTE, 15 * i);
                		result.add(new GenDataVo(data.getCliId(), data.getGenId(), cal.getTime(), data.getDataTypeId(), value));
                	}
                }
        );
		
		return result;
	}
	
	private void retrieveData(InverterData inverterData, LocationVo location, StationVo station, GeneratorVo generator, Date dateFrom, Date to) throws WeatherServiceException {
		this.continueWithStats	= false;
		String appKey			= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_APP_TOKEN);
		String paramPlantId		= InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_GEN_PLANT_ID);
		boolean betaMode		= BooleanUtils.isTrue(InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_BETA_MODE));
		
		Integer plantId			= null;
		try {
			plantId = Integer.valueOf(paramPlantId);
		} catch (NumberFormatException e) {
			String errorMessage = "Can't retrieve plant id for: " + paramPlantId;
			LoggerService.inverterLogger().error(LOG_PREFIX + errorMessage);
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(-1));
			
			Date dateError = new Date();
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
			} catch (InveterServiceException ee) {
				LoggerService.inverterLogger().error(LOG_PREFIX + "Error generating alert: " + ee.getLocalizedMessage(), ee);
			}
			
			return;
		}
		
		InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_PARAMETER_DATE_TIME), DateUtil.formatDateTime(to, DateUtil.FMT_PARAMETER_DATE_TIME));
		
		PlantEnergyResponse data = this.getPlantEnergy(this.getUrl(betaMode), appKey, plantId, GrowattInveterService.TIME_UNIT_DAY, dateFrom, to);
		
		if (data == null || data.hasError()) {
			String errorMessage = data == null ? "No data response from server." : "Error parsing data: " + data.getErrorCode() + " - " + data.getErrorMsg();
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
			List<GenDataVo> generatorData = this.process(generator, data, dateFrom);
			generatorData = this.split(generatorData);
			
			if (CollectionUtil.notEmpty(generatorData)) {
				CollectionUtil.addAll(inverterData.getGeneratorData(), generatorData);
				
				Date lastDate = generatorData.stream().max(Comparator.comparing(GenDataVo::getDataDate)).get().getDataDate();
				
				InvertersUtil.setParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE, Long.toString(lastDate.getTime()));
				
				CollectionUtil.addAll(inverterData.getStationData(), this.weatherService.retrieveWeatherData(location, station, dateFrom, lastDate));
				
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(lastDate);
				cal.add(Calendar.MINUTE, 15); //we need next 15 min due to split
				
				if (! cal.getTime().equals(dateFrom) && cal.getTime().before(to)) {
					this.retrieveData(inverterData, location, station, generator, cal.getTime(), to);
				}
				
			}
			
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(CollectionUtil.size(generatorData)));
		} catch (ParseException e) {
			LoggerService.inverterLogger().error(LOG_PREFIX + "Error parsing data: " + e.getLocalizedMessage(), e);
			InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, this.cliVo.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(-1));
		}
	}
	
	//--- Overridden methods --------------------
	@Override public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}

	@Override public boolean canRetrieve() { return true; }
	@Override public boolean continueWithStats() { return this.continueWithStats; }
	@Override public String getReasonWhyCantRetrieve() {return null; }

	@Override public InverterData retrieveData() throws InveterServiceException {
		Calendar cal = Calendar.getInstance();
		
		InverterData inverterData = new InverterData(new ArrayList<>(), new ArrayList<>());
		
		if (CollectionUtil.notEmpty(this.cliVo.getLocations())) {
			for (LocationVo location : this.cliVo.getLocations()) {
				if (CollectionUtil.isEmpty(location.getStations())) {
					LoggerService.inverterLogger().error(LOG_PREFIX + "Can't find station for client: " + this.cliVo.getCliName() + " - location: " + location.getLocName());
					continue;
				}
				
				StationVo station = location.getStations().iterator().next();
				
				if (CollectionUtil.notEmpty(location.getGenerators())) {
					for (GeneratorVo generator : location.getGenerators()) {
						boolean isDemoData 		= BooleanUtils.isTrue(InvertersUtil.getParameter(generator, location, this.cliVo, PARAM_DATA_DEMO));
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
	public String getUrl(boolean forProd) { return forProd ? URL_PROD : URL_TEST; }

	public ListPlantsResponse listPlants(String url, String token) {
		return JsonCaller.get(
				url + ENDPOINT_LIST_PLANTS, 
				this.getHeaders(token), 
				null, 
				ListPlantsResponse.class
			);
	}
	
	public ListDevicesResponse listDevices(String url, String token, Integer plantId) {
		Map<String, String> params = new HashMap<>();
		params.put("plant_id", plantId.toString());
		
		return JsonCaller.get(
				url + ENDPOINT_LIST_DEVICES, 
				this.getHeaders(token), 
				params, 
				ListDevicesResponse.class
			);
	}
	
	public PlantEnergyResponse getPlantEnergy(String url, String token, Integer plantId, String timeUnit, Date dateStart, Date dateEnd) {
		Map<String, String> params = new HashMap<>();
		params.put("plant_id", plantId.toString());
		params.put("time_unit", timeUnit);
		params.put("start_date", this.formatDate.format(dateStart));
		params.put("end_date", this.formatDate.format(dateEnd));
		
		return JsonCaller.get(
				url + ENDPOINT_PLANT_ENERGY, 
				this.getHeaders(token), 
				params, 
				PlantEnergyResponse.class
			);
	}
	
	public PlantPowerResponse getPlantPower(String url, String token, Integer plantId, Date date) {
		Map<String, String> params = new HashMap<>();
		params.put("plant_id", plantId.toString());
		params.put("date", this.formatDate.format(date));
		
		return JsonCaller.get(
				url + ENDPOINT_PLANT_POWER, 
				this.getHeaders(token), 
				params, 
				PlantPowerResponse.class
			);
	}
}
