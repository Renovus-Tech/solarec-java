package tech.renovus.solarec.certificate.drecs;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.certificate.drecs.api.auth.Auth;
import tech.renovus.solarec.certificate.drecs.api.auth.AuthResponse;
import tech.renovus.solarec.certificate.drecs.api.organization.Organization;
import tech.renovus.solarec.certificate.drecs.api.organization.OrganizationResponse;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.vo.db.data.ClientVo;

/**
 * API information: https://api.drecs.org/swagger
 */
public class DRecsService {
	
	//--- Private constants ---------------------
	private static final String DREC_API_URL			= "https://api.drecs.org/api";
	private static final String ENDPOINT_AUTH			= DREC_API_URL + "/auth/login";
	private static final String ENDPOINT_ORGANIZATION	= DREC_API_URL + "/Organization";
	
	//--- Protected properties ------------------
	protected @Autowired RenovusSolarecConfiguration config;

	//--- Private methods -----------------------
	private Organization createOrganizationFrom(ClientVo clientVo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//--- Public methods ------------------------
	/**
	 * Doc: https://api.drecs.org/swagger/#/auth/AuthController_login
	 */
	public AuthResponse authenticate() {
		Auth jsonRequest = new Auth()
				.withUsername(this.config.getDrecsServiceUsername())
				.withPassword(this.config.getDrecsServicePassword());

		return JsonCaller.post(ENDPOINT_AUTH, jsonRequest, AuthResponse.class);
	}

	/**
	 * Doc:
	 * https://api.drecs.org/swagger/#/organization/OrganizationController_register
	 */
	public void registerOrganization(ClientVo clientVo) {
		AuthResponse authentication = this.authenticate();
		
		Organization organization = this.createOrganizationFrom(clientVo);
		OrganizationResponse response = JsonCaller.bearerPost(ENDPOINT_ORGANIZATION, organization, authentication.getAccessToken(), OrganizationResponse.class);
		
		//do somthing with the response
	}

	/**
	 * Doc: https://api.drecs.org/swagger/#/device/DeviceController_create
	 */
	public void registerDevice() {
	}

	/**
	 * Doc: https://api.drecs.org/swagger/#/meter-reads/ReadsController_newstoreRead
	 */
	public void uploadHistoryData() {
	}

	/**
	 * Doc: https://api.drecs.org/swagger/#/meter-reads/ReadsController_storeReads
	 */
	public void uploadMeterData() {
	}

	
	public void checkCertificateStatus() {
		
	}
	
	public void generateCertificate() {
		
	}
	
	public void sellCertificate() {
		
	}
}
