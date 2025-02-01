package tech.renovus.solarec.inverters.brand.huawei;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.brand.huawei.api.LoginRequest;
import tech.renovus.solarec.inverters.brand.huawei.api.LoginResponse;
import tech.renovus.solarec.inverters.brand.huawei.api.PowerStationHourRequest;
import tech.renovus.solarec.inverters.brand.huawei.api.PowerStationHourResponse;
import tech.renovus.solarec.inverters.brand.huawei.api.PowerStationMonthRequest;
import tech.renovus.solarec.inverters.brand.huawei.api.PowerStationMonthResponse;
import tech.renovus.solarec.inverters.common.InverterService;
import tech.renovus.solarec.inverters.common.InvertersUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;

public class HuaweiInverterService implements InverterService {

	//-- Private constants ----------------------
	private static final String URL_PROD	= "https://eu5.fusionsolar.huawei.com";
	private static final String URL_TEST	= "";
	
	private static final String HEADER_AUTH	= "XSRF-TOKEN";
	
	private static final String ENDPOINT_LOGIN				= "/thirdData/login";
	private static final String ENDPOINT_PLANT_LIST			= "/thirdData/getStationList";
	private static final String ENDPOINT_KPI_STATION_HOUR	= "/thirdData/getKpiStationHour";
	private static final String ENDPOINT_KPI_STATION_MONTH	= "/thirdData/getKpiStationMonth";
	
	
	//--- Public constants ----------------------
	public static final String PARAM_TEST_MODE										= "huawei.test";
	public static final String PARAM_CLIENT_USER_NAME								= "huawei.client.user_name";
	public static final String PARAM_CLIENT_SYSTEM_CODE								= "huawei.client.system_code";
	protected static final String PARAM_GEN_PANT_CODE								= "huawei.generator.plant_code";
	protected static final String PARAM_CLI_LAST_DATE_RETRIEVE						= "huawei.client.last_retrieve";
	protected static final String PARAM_LOC_LAST_DATE_RETRIEVE						= "huawei.location.last_retrieve";
	protected static final String PARAM_GEN_LAST_DATE_RETRIEVE						= "huawei.generator.last_retrieve";

	//--- Private properties --------------------
	private @Autowired JsonCaller jsonCaller;
	
	private ClientVo cliVo;

	//--- Private methods -----------------------
	public String getUrl(boolean forProd) { return forProd ? URL_PROD : URL_TEST; }
	
	private Map<String, String> getHeaders(String auth) {
		Map<String, String> result = new HashMap<>();
		result.put(HEADER_AUTH, auth);
		return result;
	}

	
	//--- Overridden methods --------------------
	@Override public void prepareFor(ClientVo client) {
		this.cliVo = client;
	}

	@Override
	public boolean canRetrieve() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean continueWithStats() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getReasonWhyCantRetrieve() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InverterData retrieveData() throws InveterServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	
	//--- Public methods ------------------------
	public String authenticate(boolean forProd) {
		String url			= this.getUrl(forProd);
		String userName		= InvertersUtil.getMetadata(this.cliVo, PARAM_CLIENT_USER_NAME);
		String systemCode	= InvertersUtil.getMetadata(this.cliVo, PARAM_CLIENT_SYSTEM_CODE);
		
		LoginRequest request = new LoginRequest()
				.withUserName(userName)
				.withSystemCode(systemCode);
		
		LoginResponse response = this.jsonCaller.post(url + ENDPOINT_LOGIN, request, LoginResponse.class);
		
		return response.getXsrfToken();
	}
	
	public Object getPlantList(boolean forProd, String auth, String stationCode, Date date) {
		String url			= this.getUrl(forProd);
		
		return this.jsonCaller.post(url + ENDPOINT_PLANT_LIST, this.getHeaders(auth), "{}", String.class);
	}
	
	public Object getStationHourData(boolean forProd, String auth, String stationCode, Date date) {
		String url			= this.getUrl(forProd);
		
		PowerStationHourRequest request = new PowerStationHourRequest()
				.withStationCodes(stationCode)
				.withCollectTime(date.getTime());
		
		return this.jsonCaller.post(url + ENDPOINT_KPI_STATION_HOUR, this.getHeaders(auth), request, PowerStationHourResponse.class);
	}
	
	public Object getStationMonthData(boolean forProd, String auth, String stationCode, Date date) {
		String url			= this.getUrl(forProd);
		
		PowerStationMonthRequest request = new PowerStationMonthRequest()
				.withStationCodes(stationCode)
				.withCollectTime(date.getTime());
		
		return this.jsonCaller.post(url + ENDPOINT_KPI_STATION_MONTH, this.getHeaders(auth), request, PowerStationMonthResponse.class);
	}
}
