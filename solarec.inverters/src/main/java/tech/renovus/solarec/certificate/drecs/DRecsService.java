package tech.renovus.solarec.certificate.drecs;

/**
 * API information: https://api.drecs.org/swagger
 */
public class DRecsService {

	/**
	 * Doc: https://api.drecs.org/swagger/#/auth/AuthController_login
	 */
	public void authenticate() {}
	
	/**
	 * Doc: https://api.drecs.org/swagger/#/organization/OrganizationController_register
	 */
	public void registerOrganization() {}
	
	/**
	 * Doc: https://api.drecs.org/swagger/#/device/DeviceController_create
	 */
	public void registerDevice() {}
	
	/**
	 * Doc: https://api.drecs.org/swagger/#/meter-reads/ReadsController_newstoreRead
	 */
	public void uploadHistoryData() {}
	
	/**
	 * Doc: https://api.drecs.org/swagger/#/meter-reads/ReadsController_storeReads
	 */
	public void uploadMeterData() {}
	
}
