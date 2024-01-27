package tech.renovus.solarec.inverters.brand.fronius;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import reactor.core.publisher.Mono;
import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.fronius.api.ErrorResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.InfoReleaseResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.HistoryDataResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.PvSystemsListResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.user.InfoUserResponse;
import tech.renovus.solarec.inverters.common.InverterCofigurationVo;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;

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
	
	private static final String ENDPOINT_INFO_RELEASE				= "/info/release";
	private static final String ENDPOINT_INFO_USER					= "/info/user";
	private static final String ENDPOINT_PV_SYSTEMS_LIST			= "/pvsystems-list";
	private static final String ENDPOINT_PV_SYSTEMS_HISTORY_DATA	= "/pvsystems/{pvSystemId}/histdata";
	
	//--- Private methods -----------------------
	private Map<String, String> getAuthenticationHeaders(String accessKeyId, String accessKeyValue) {
		Map<String, String> result = new HashMap<>(2);
		
		result.put("AccessKeyId", accessKeyId);
		result.put("AccessKeyValue", accessKeyValue);
		
		return result;
	}
	
	//--- Implemented methods -------------------
	@Override public Collection<GenDataVo> retrieveData(ClientVo client, InverterCofigurationVo configuration) {
		// TODO Auto-generated method stub
		return null;
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

	public HistoryDataResponse getPvSystemsHistData(String accessKeyId, String accessKeyValue, String pvSystemsId) {
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
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		Map<String, String> params = new HashMap<>(2);
		params.put("from", formatter.format(from));
		params.put("to", formatter.format(to));
		
		return JsonCaller.get(
				URL_PRD + ENDPOINT_PV_SYSTEMS_HISTORY_DATA.replaceFirst("\\{pvSystemId\\}", pvSystemsId),
				this.getAuthenticationHeaders(accessKeyId, accessKeyValue),
				params,
				HistoryDataResponse.class
			);
	}
}
