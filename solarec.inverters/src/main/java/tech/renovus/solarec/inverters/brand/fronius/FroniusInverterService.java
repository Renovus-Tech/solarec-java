package tech.renovus.solarec.inverters.brand.fronius;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.fronius.api.InfoReleaseResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.aggregated.specific.AggregatedSpecificDate;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.Channel;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.Datum;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.HistoryDataResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.PvSystemsListResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.user.InfoUserResponse;
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

/**
 * URL: https://www.fronius.com/en/solarweb-query-api
 * URL API BEta: https://swqapi-beta.solarweb.com/index.html
 * 
 * Authentication format (2 header parameters):
 * 
 * GET https://api.solarweb.com/swqapi/pvsystems HTTP/1.1
 * AccessKeyId: FKIAFEF58CFEFA94486F9C804CF6077A01AB
 * AccessKeyValue: 47c076bc-23e5-4949-37a6-4bcfcf8d21d6
 * 
 * Data required from client: key_id and key_value
 * Data required from inverter: pv_sustems_id
 */
public class FroniusInverterService implements InverterService {

	//--- Protected constants -------------------
	protected static final String URL_PRD											= "https://api.solarweb.com/swqapi";
	protected static final String URL_BETA											= "https://swqapi-beta.solarweb.com";

	protected static final String ENDPOINT_INFO_RELEASE								= "/info/release";
	protected static final String ENDPOINT_INFO_USER								= "/info/user";
	protected static final String ENDPOINT_PV_SYSTEMS_LIST							= "/pvsystems-list";
	protected static final String ENDPOINT_PV_SYSTEMS_HISTORY_DATA					= "/pvsystems/{pvSystemId}/histdata";
	protected static final String ENDPOINT_PV_SYSTEM_AGGREGATED_DATA_SPECIFIC_DATE	= "/pvsystems/{pvSystemId}/aggdata/years/{year}/months/{month}/days/{day}";
	
	protected static final String PARAM_BETA_MODE									= "fronius.beta";
	protected static final String PARAM_ACCESS_KEY_ID								= "fronius.client.key_id";
	protected static final String PARAM_ACCESS_KEY_VALUE							= "fronius.client.key_value";
	protected static final String PARAM_CLI_LAST_DATE_RETRIEVE						= "fronius.client.last_retrieve";
	protected static final String PARAM_LOC_LAST_DATE_RETRIEVE						= "fronius.location.last_retrieve";
	protected static final String PARAM_GEN_PV_SYSTEM_ID							= "fronius.generator.pv_systems_id";
	protected static final String PARAM_GEN_LAST_DATE_RETRIEVE						= "fronius.generator.last_retrieve";
	
	protected static final String PARAM_DATA_DEMO									= "fronius.data_demo";
	
	private static final SimpleDateFormat DATE_FORMATTER							= new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'Z'");
	
	//--- Private methods -----------------------
	private String getUrl(boolean betaMode) { return betaMode ? URL_BETA : URL_PRD; }
	
	private String getParameterValue(String param, ClientVo cliVo, LocationVo locVo, GeneratorVo genVo) {
		String result = InvertersUtil.getParameter(genVo, param);
		if (StringUtil.isEmpty(result)) result = InvertersUtil.getParameter(locVo, param);
		if (StringUtil.isEmpty(result)) result = InvertersUtil.getParameter(cliVo, param);
		
		return result;
	}
	
	private Map<String, String> getAuthenticationHeaders(String accessKeyId, String accessKeyValue) {
		Map<String, String> result = new HashMap<>(2);
		
		result.put("AccessKeyId", accessKeyId);
		result.put("AccessKeyValue", accessKeyValue);
		
		return result;
	}
	
	private List<GenDataVo> process(GeneratorVo generator, HistoryDataResponse data, Date fromDate) throws ParseException {
		List<GenDataVo> result = new ArrayList<>();
		
		if (data != null && CollectionUtil.notEmpty(data.getData())) {
			for (Datum aData : data.getData()) {
				if (CollectionUtil.notEmpty(aData.getChannels())) {
					for (Channel aChannel : aData.getChannels()) {
						if (! "EnergySelfConsumption".equals(aChannel.getChannelName())) continue;
						
						Date dataDate = DATE_FORMATTER.parse(aData.getLogDateTime());
						
						if (dataDate.before(fromDate)) continue;
						
						GenDataVo genData = new GenDataVo();
						genData.setCliId(generator.getCliId());
						genData.setGenId(generator.getGenId());
						genData.setDataDate(dataDate);
						genData.setDataTypeId(DataTypeVo.TYPE_GENERATOR_POWER_KWH);
						genData.setDataValue(aChannel.getValue() == null ? null : Double.valueOf(aChannel.getValue().intValue()));
						
						result.add(genData);
					}
				}
			}
		}
		
		return result;
	}
	
	private String generateUrl(boolean betaMode, String endPoint, String... variables) {
		String url = this.getUrl(betaMode) + endPoint;
		
		for (int pos = 0; pos < variables.length; pos += 2) {
			url = url.replaceFirst(variables[pos], variables[pos +1]);
		}
		
		return url;
	}
	
	private Date calculateFrom(String genLastRetrieve) {
		Calendar cal = Calendar.getInstance();
		
		if (StringUtil.isEmpty(genLastRetrieve)) {
			cal.add(Calendar.DAY_OF_YEAR, -1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.AM_PM, Calendar.AM);
		} else {
			cal.setTimeInMillis(Long.parseLong(genLastRetrieve));
		}
		
		return cal.getTime();
	}
	
	//--- Implemented methods -------------------
	@Override public Collection<GenDataVo> retrieveData(ClientVo client) {
		Calendar cal = Calendar.getInstance();
		boolean betaMode = BooleanUtils.isTrue(InvertersUtil.getParameter(client, PARAM_BETA_MODE));
		
		Collection<GenDataVo> result = new ArrayList<>();
		
		boolean isDemoData = BooleanUtils.isTrue(InvertersUtil.getParameter(client, PARAM_DATA_DEMO));
		
		if (CollectionUtil.notEmpty(client.getLocations())) {
			for (LocationVo location : client.getLocations()) {
				if (CollectionUtil.notEmpty(location.getGenerators())) {
					for (GeneratorVo generator : location.getGenerators()) {
						String accessKeyId		= this.getParameterValue(PARAM_ACCESS_KEY_ID, client, location, generator);
						String accessKeyValue	= this.getParameterValue(PARAM_ACCESS_KEY_VALUE, client, location, generator);
						String pvSystemsId		= this.getParameterValue(PARAM_GEN_PV_SYSTEM_ID, client, location, generator);
						
						String genLastRetrieve	= InvertersUtil.getParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE);
						Date dateFrom 			= this.calculateFrom(genLastRetrieve);

						cal.setTime(dateFrom);
						
						if (isDemoData) {
							cal.add(Calendar.YEAR, -1);
							dateFrom = cal.getTime();
						}
						
						cal.add(Calendar.DAY_OF_YEAR, 1);
						cal.add(Calendar.MILLISECOND, -1);
						
						Date to = cal.getTime();
						
						InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_START, client.getCliName(), location.getLocName(), generator.getGenName(), DateUtil.formatDateTime(dateFrom, DateUtil.FMT_DATE));
						
						HistoryDataResponse data = this.getPvSystemsHistData(betaMode, accessKeyId, accessKeyValue, pvSystemsId, dateFrom, to);
						
						try {
							List<GenDataVo> generatorData = this.process(generator, data, dateFrom);
							
							if (CollectionUtil.notEmpty(generatorData)) {
								CollectionUtil.addAll(result, generatorData);
								
								Collections.reverse(generatorData);
								GenDataVo lastData = generatorData.iterator().next();
								
								InvertersUtil.setParameter(generator, PARAM_GEN_LAST_DATE_RETRIEVE, Long.toString(lastData.getDataDate().getTime()));
							}
							
							InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, client.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(CollectionUtil.size(generatorData)));
						} catch (ParseException e) {
							LoggerService.inverterLogger().error("Error parsing data: " + e.getLocalizedMessage(), e);
							InvertersUtil.logInfo(InvertersUtil.INFO_DATA_RETRIEVE_END, client.getCliName(), location.getLocName(), generator.getGenName(), Integer.valueOf(-1));
						}
					}
				}
			}
		}
		
		return result;
	}
	

	//--- Public methods ------------------------
	public InfoReleaseResponse getInfoRelease(boolean betaMode, String accessKeyId, String accessKeyValue) {
		return JsonCaller.get(
				this.getUrl(betaMode) + ENDPOINT_INFO_RELEASE,
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				null,
				InfoReleaseResponse.class);
	}
	
	public InfoUserResponse getInfoUser(boolean betaMode, String accessKeyId, String accessKeyValue) {
		return JsonCaller.get(
				this.getUrl(betaMode) + ENDPOINT_INFO_USER,
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				null,
				InfoUserResponse.class);
	}
	
	public PvSystemsListResponse getPvSystemsList(boolean betaMode, String accessKeyId, String accessKeyValue) {
		return JsonCaller.get(
				this.getUrl(betaMode) + ENDPOINT_PV_SYSTEMS_LIST,
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				null,
				PvSystemsListResponse.class);
	}

	public AggregatedSpecificDate getPvSystemsAggredatedDataSpecificDate(boolean betaMode, String accessKeyId, String accessKeyValue, String pvSystemsId, int year, int month, int day) {
		return JsonCaller.get(
			this.generateUrl(betaMode, ENDPOINT_PV_SYSTEM_AGGREGATED_DATA_SPECIFIC_DATE, 
					"\\{pvSystemId\\}", pvSystemsId ,
					"\\{year\\}", Integer.toString(year), 
					"\\{month\\}", Integer.toString(month), 
					"\\{day\\}", Integer.toString(day) 
				),
			this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
			null,
			AggregatedSpecificDate.class
		);
	}
	
	public HistoryDataResponse getPvSystemsHistData(boolean betaMode, String accessKeyId, String accessKeyValue, String pvSystemsId, Date from, Date to) {
		Map<String, String> params = new HashMap<>(2);
		params.put("from", DATE_FORMATTER.format(from));
		params.put("to", DATE_FORMATTER.format(to));
		
		return JsonCaller.get(
				this.generateUrl(betaMode, ENDPOINT_PV_SYSTEMS_HISTORY_DATA, "\\{pvSystemId\\}", pvSystemsId ),
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				params,
				HistoryDataResponse.class
			);
	}
}
