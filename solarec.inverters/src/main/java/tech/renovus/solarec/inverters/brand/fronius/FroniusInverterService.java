package tech.renovus.solarec.inverters.brand.fronius;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.CollectionUtil;
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
 */
public class FroniusInverterService implements InverterService {

	//--- Private constants ---------------------
	private static final String URL_PRD		= "https://api.solarweb.com/swqapi";
	private static final String URL_BETA	= "https://swqapi-beta.solarweb.com";
	
	private static final String URL_TO_USE	= URL_PRD;
	
	private static final String ENDPOINT_INFO_RELEASE								= "/info/release";
	private static final String ENDPOINT_INFO_USER									= "/info/user";
	private static final String ENDPOINT_PV_SYSTEMS_LIST							= "/pvsystems-list";
	private static final String ENDPOINT_PV_SYSTEMS_HISTORY_DATA					= "/pvsystems/{pvSystemId}/histdata";
	private static final String ENDPOINT_PV_SYSTEM_AGGREGATED_DATA_SPECIFIC_DATE	= "/pvsystems/{pvSystemId}/aggdata/years/{year}/months/{month}/days/{day}";
	
	private static final String PARAM_ACCESS_KEY_ID		= "fronius.key_id";
	private static final String PARAM_ACCESS_KEY_VALUE	= "fronius.key_value";
	private static final String PARAM_PV_SYSTEM_ID		= "froniys.pv_systems_id";
	
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
	
	//--- Private methods -----------------------
	private Map<String, String> getAuthenticationHeaders(String accessKeyId, String accessKeyValue) {
		Map<String, String> result = new HashMap<>(2);
		
		result.put("AccessKeyId", accessKeyId);
		result.put("AccessKeyValue", accessKeyValue);
		
		return result;
	}
	
	private Collection<GenDataVo> process(GeneratorVo generator, HistoryDataResponse data) throws ParseException {
		Collection<GenDataVo> result = new ArrayList<>();
		
		if (data != null && CollectionUtil.notEmpty(data.getData())) {
			for (Datum aData : data.getData()) {
				if (CollectionUtil.notEmpty(aData.getChannels())) {
					for (Channel aChannel : aData.getChannels()) {
						if (! "EnergySelfConsumption".equals(aChannel.getChannelName())) continue;
						
						GenDataVo genData = new GenDataVo();
						genData.setCliId(generator.getCliId());
						genData.setGenId(generator.getGenId());
						genData.setDataDate(DATE_FORMATTER.parse(aData.getLogDateTime()));
						genData.setDataTypeId(DataTypeVo.TYPE_GENERATOR_POWER_KWH);
						genData.setDataValue(aChannel.getValue() == null ? null : Double.valueOf(aChannel.getValue().intValue()));
						
						result.add(genData);
					}
				}
			}
		}
		
		return result;
	}
	
	private String generateUrl(String endPoint, String... variables) {
		String url = URL_TO_USE + endPoint;
		
		for (int pos = 0; pos < variables.length; pos += 2) {
			url = url.replaceFirst(variables[pos], variables[pos +1]);
		}
		
		
		return url;
	}
	
	//--- Implemented methods -------------------
	@Override public Collection<GenDataVo> retrieveData(ClientVo client) {
		String accessKeyId = this.getParameter(client, PARAM_ACCESS_KEY_ID);
		String accessKeyValue = this.getParameter(client, PARAM_ACCESS_KEY_VALUE);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		
		Date from = cal.getTime();
		
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.MILLISECOND, -1);
		
		Date to = cal.getTime();
		
		Collection<GenDataVo> result = new ArrayList<>();
		
		if (CollectionUtil.notEmpty(client.getLocations())) {
			for (LocationVo location : client.getLocations()) {
				if (CollectionUtil.notEmpty(location.getGenerators())) {
					for (GeneratorVo generator : location.getGenerators()) {
						String pvSystemsId = this.getParameter(generator, PARAM_PV_SYSTEM_ID);
						HistoryDataResponse data = this.getPvSystemsHistData(accessKeyId, accessKeyValue, pvSystemsId, from, to);
						
						try {
							CollectionUtil.addAll(result, this.process(generator, data));
						} catch (ParseException e) {
							LoggerService.inverterLogger().error("Error parsing data: " + e.getLocalizedMessage(), e);
						}
					}
				}
			}
		}
		
		return result;
	}
	
	//--- Public methods ------------------------
	public InfoReleaseResponse getInfoRelease(String accessKeyId, String accessKeyValue) {
		return JsonCaller.get(
				URL_PRD + ENDPOINT_INFO_RELEASE,
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				null,
				InfoReleaseResponse.class);
	}
	
	public InfoUserResponse getInfoUser(String accessKeyId, String accessKeyValue) {
		return JsonCaller.get(
				URL_PRD + ENDPOINT_INFO_USER,
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				null,
				InfoUserResponse.class);
	}
	
	public PvSystemsListResponse getPvSystemsList(String accessKeyId, String accessKeyValue) {
		return JsonCaller.get(
				URL_PRD + ENDPOINT_PV_SYSTEMS_LIST,
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				null,
				PvSystemsListResponse.class);
	}

	public AggregatedSpecificDate getPvSystemsAggredatedDataSpecificDate(String accessKeyId, String accessKeyValue, String pvSystemsId, int year, int month, int day) {
		return JsonCaller.get(
			this.generateUrl( ENDPOINT_PV_SYSTEM_AGGREGATED_DATA_SPECIFIC_DATE, 
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
	
	public HistoryDataResponse getPvSystemsHistData(String accessKeyId, String accessKeyValue, String pvSystemsId, Date from, Date to) {
		Map<String, String> params = new HashMap<>(2);
		params.put("from", DATE_FORMATTER.format(from));
		params.put("to", DATE_FORMATTER.format(to));
		
		return JsonCaller.get(
				this.generateUrl( ENDPOINT_PV_SYSTEMS_HISTORY_DATA, "\\{pvSystemId\\}", pvSystemsId ),
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				params,
				HistoryDataResponse.class
			);
	}
}
