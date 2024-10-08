package tech.renovus.solarec.certificate.drecs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.certificate.CertificateService;
import tech.renovus.solarec.certificate.drecs.api.auth.Auth;
import tech.renovus.solarec.certificate.drecs.api.auth.AuthResponse;
import tech.renovus.solarec.certificate.drecs.api.device.Device;
import tech.renovus.solarec.certificate.drecs.api.device.DeviceResponse;
import tech.renovus.solarec.certificate.drecs.api.organization.Organization;
import tech.renovus.solarec.certificate.drecs.api.organization.OrganizationResponse;
import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.inverters.common.InvertersUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;

/**
 * API information: https://api.drecs.org/swagger
 */
@Service
@ConditionalOnProperty(name = "solarec.service.certificate.provider", havingValue = "drecs")
public class DRecsService implements CertificateService {
	
	//--- Private constants ---------------------
	private static final String DREC_API_URL			= "https://api.drecs.org/api";
	private static final String ENDPOINT_AUTH			= DREC_API_URL + "/auth/login";
	private static final String ENDPOINT_ORGANIZATION	= DREC_API_URL + "/Organization";
	private static final String ENDPOINT_DEVICE 		= DREC_API_URL + "/device";
	
	private static final String PARAM_CLIENT_ID	= "drec.client.id";
	
	//--- Protected properties ------------------
	protected @Autowired DRecsConfiguration drecsConfig;

	//--- Private methods -----------------------
	private Organization createOrganizationFrom(ClientVo clientVo) {
		Organization organization = new Organization();
		organization.withName(clientVo.getCliName())
			.withAddress(clientVo.getCliNameAddress());
			//.withZipCode(clientVo.getLocations().iterator().next().) //Ahora si es uno a uno porque es una lista? Que pasara si se cambia
			//.withCity(clientVo.get)
			//.withCountry(clientVo.get)
			//.withOrganizationType(clientVo.)
			//.withOrgEmail(clientVo.)
			//.withDocumentIds(null)
			//.withSignatoryDocumentIds(null)
			
		return organization;
	}
	
	private Device createDeviceFrom(GeneratorVo generatorVo) {
		Device device = new Device();
		device.withExternalId(generatorVo.getId().toString()); //Confirmar si el ID es el ID solicitado
			//.withProjectName(generatorVo.)
			//.withAddress()
			//.withLatitude()
			//.withLongitude()
			//.withCountryCode()
			//.withFuelCode()
			//.withDeviceTypeCode()
			//.withCapacity()
			//.withCommissioningDate() //Ojo dice date pero no da formato
			//.withGridInterconnection()
			//.withOffTaker()
			//.withImpactStory()
			//.withImages(null) //Lista de imagenes pasadas a string
			//.withDeviceDescription(generatorVo.getGenDescription()) //Confirmar si es la descripción solicitada
			//.withEnergyStorage()
			//.withEnergyStorageCapacity()
			//.withQualityLabels()
			//.withSDGBenefits() //Lista de strings, beneficios?
			//.withVersion()
		
		return device;
	}
	
	/**
	 * Doc: https://api.drecs.org/swagger/#/auth/AuthController_login
	 */
	private AuthResponse authenticate() {
		Auth jsonRequest = new Auth()
				.withUsername(this.drecsConfig.getUser())
				.withPassword(this.drecsConfig.getPassword());

		return JsonCaller.post(ENDPOINT_AUTH, jsonRequest, AuthResponse.class);
	}

	/**
	 * Doc:
	 * https://api.drecs.org/swagger/#/organization/OrganizationController_register
	 */
	private void registerOrganization(ClientVo clientVo) {
		AuthResponse authentication = this.authenticate();
		
		Organization organization = this.createOrganizationFrom(clientVo);
		OrganizationResponse response = JsonCaller.bearerPost(ENDPOINT_ORGANIZATION, organization, authentication.getAccessToken(), OrganizationResponse.class);
		
		InvertersUtil.setParameter(clientVo, PARAM_CLIENT_ID, response.getId().toString());
	}

	/**
	 * Doc: https://api.drecs.org/swagger/#/device/DeviceController_create
	 */
	private void registerDevice(GeneratorVo generatorVo) {
		AuthResponse authentication = this.authenticate();

		Device device = this.createDeviceFrom(generatorVo);
		DeviceResponse response = JsonCaller.bearerPost(ENDPOINT_DEVICE, device, authentication.getAccessToken(), DeviceResponse.class);

		// do somthing with the response
	}

	/**
	 * Doc: https://api.drecs.org/swagger/#/meter-reads/ReadsController_newstoreRead
	 */
	private void uploadHistoryData() {
		/** Coding halt, check https://github.com/Renovus-Tech/solarec-java/issues/11 **/
	}

	/**
	 * Doc: https://api.drecs.org/swagger/#/meter-reads/ReadsController_storeReads
	 */
	private void uploadMeterData() {
		/** Coding halt, check https://github.com/Renovus-Tech/solarec-java/issues/11 **/
	}

	
	private void checkCertificateStatus() {
		/** Coding halt, check https://github.com/Renovus-Tech/solarec-java/issues/11 **/
	}
	
	private void generateCertificate() {
		/** Coding halt, check https://github.com/Renovus-Tech/solarec-java/issues/11 **/
	}
	
	private void sellCertificate() {
		/** Coding halt, check https://github.com/Renovus-Tech/solarec-java/issues/11 **/
	}
	
	//--- Implemented methods -------------------
	@Override public void register(ClientVo client) {/** Coding halt, check https://github.com/Renovus-Tech/solarec-java/issues/11 **/}
	@Override public void updateRegistration(ClientVo client) {/** Coding halt, check https://github.com/Renovus-Tech/solarec-java/issues/11 **/}
	@Override public void registerGeneration(ClientVo client) {/** Coding halt, check https://github.com/Renovus-Tech/solarec-java/issues/11 **/}
	@Override public void generateCertificate(ClientVo client) {/** Coding halt, check https://github.com/Renovus-Tech/solarec-java/issues/11 **/}
}
