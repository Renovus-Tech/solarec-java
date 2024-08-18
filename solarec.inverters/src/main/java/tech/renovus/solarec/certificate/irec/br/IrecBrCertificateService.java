package tech.renovus.solarec.certificate.irec.br;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.certificate.CertificateService;
import tech.renovus.solarec.certificate.irec.br.api.business.BusinessRequest;
import tech.renovus.solarec.certificate.irec.br.api.business.BusinessResponse;
import tech.renovus.solarec.certificate.irec.br.api.login.LoginRequest;
import tech.renovus.solarec.certificate.irec.br.api.login.LoginResponse;
import tech.renovus.solarec.certificate.irec.br.api.participant.ParticipantRequest;
import tech.renovus.solarec.certificate.irec.br.api.participant.ParticipantResponse;
import tech.renovus.solarec.certificate.irec.br.api.recs.RecRequest;
import tech.renovus.solarec.certificate.irec.br.api.recs.RecResponse;
import tech.renovus.solarec.certificate.irec.br.api.registrants.RegistrantRequest;
import tech.renovus.solarec.certificate.irec.br.api.registrants.RegistrantResponse;
import tech.renovus.solarec.certificate.irec.br.api.source.SourceRequest;
import tech.renovus.solarec.certificate.irec.br.api.source.SourceResponse;
import tech.renovus.solarec.certificate.irec.br.api.technology.TechnologyRequest;
import tech.renovus.solarec.certificate.irec.br.api.technology.TechnologyResponse;
import tech.renovus.solarec.connection.JsonCaller;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.UsersDao;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.UsersVo;

/**
 * Site: https://site.institutototum.com.br/totum-services/i-rece/
 * API documentation: https://api.sisgasrec.institutototum.com.br/docs/#/Participant/post_participants
 * URL prod: https://api.sisgasrec.institutototum.com.br/api/v1
 * URL test:
 */
@Service
@ConditionalOnProperty(name = "solarec.service.certificate.provider", havingValue = "irrecbr")
public class IrecBrCertificateService implements CertificateService {

	//--- Endpoints -----------------------------
	private static final String ENDPOINT_LOGIN			= "/auth/login";
	private static final String ENDPOINT_REGISTRANTS	= "/registrants";
	private static final String ENDPOINT_SOURCES		= "/sources";
	private static final String ENDPOINT_TECHNOLOGIES	= "/technologies";
	private static final String ENDPOINT_BUSINESS		= "/business";
	private static final String ENDPOINT_PARTICIPANTS	= "/participants";
	private static final String ENDPOINT_RECS			= "/recs";
	
	//--- Resouces ------------------------------
	@Autowired IrecBrConfiguration configuration;
	@Autowired UsersDao usersDao;
	@Autowired GenDataDao genDataDao;
	
	//--- Private methods -----------------------
	private Map<String, String> generateHeaders(String authorizationKey) {
		Map<String, String> result = new HashMap<>();
		result.put("Authorization", authorizationKey);
		return result;
	}
	
	private String getAuthorizationCode(LoginResponse loginResponse) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//--- Implemented methods -------------------
	@Override
	public void register(ClientVo cliVo) throws CertificateServiceException {
		LoginResponse loginResponse	= this.login(this.configuration.getUser(), this.configuration.getPassword());
		String authorizationKey		= this.getAuthorizationCode(loginResponse);

		UsersVo usrVo = this.usersDao.findBy(cliVo.getCliName());
		
		try {
			RegistrantResponse registrantResponse = this.createRegistrant(
				authorizationKey,
				null, //socialName,
				usrVo.getUsrEmail(),
				usrVo.getUsrPassword(),
				null, //iRec,
				null, //cnpj,
				usrVo.getUsrComments(),
				null, //expirationDay,
				null, //priceIrec,
				null //priceRecBrazil
			);
			
			BusinessResponse businessResponse = this.createBusiness(
				authorizationKey,
				cliVo.getCliNameLegal(),
				null, //ceg,
				null, //aneel,
				null, //cnpj,
				Integer.valueOf(0), //isCarbonCredit,
				null, //comercialOperationAt,
				registrantResponse.getId(),
				this.configuration.getIdSource(),
				this.configuration.getIdTechnology(),
				null //address
			);
			
			for (LocationVo locVo : cliVo.getLocations()) {
				ParticipantResponse participantResponse = this.createParticipant(
					authorizationKey,
					locVo.getLocName(),
					null //irec
				);
				
				locVo.setLocCertProvData(JsonUtil.toStringPretty(participantResponse));
			}
			
			usrVo.setUsrCertProvData(JsonUtil.toStringPretty(registrantResponse));
			cliVo.setCliCertProvData(JsonUtil.toStringPretty(businessResponse));
		} catch (JsonProcessingException e) {
			throw new CertificateServiceException(e);
		}
	}

	@Override
	public void updateRegistration(ClientVo client) throws CertificateServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerGeneration(ClientVo cliVo) throws CertificateServiceException {
		LoginResponse loginResponse	= this.login(this.configuration.getUser(), this.configuration.getPassword());
		String authorizationKey		= this.getAuthorizationCode(loginResponse);
		Date now					= new Date();
		
		try {
			BusinessResponse businessResponse = JsonUtil.toObject(cliVo.getCliCertProvData(), BusinessResponse.class);
			
			for (LocationVo locVo : cliVo.getLocations()) {
				ParticipantResponse participantResponse = JsonUtil.toObject(locVo.getLocCertProvData(), ParticipantResponse.class);
				
				Collection<GenDataVo> genData = this.genDataDao.getAllWithoutCertProvData(locVo.getCliId(), locVo.getLocId(), DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER);
				
				double amount = genData.stream().mapToDouble(x -> x.getDataValue()).sum();
				
				RecResponse recResponse = this.createRec(
					authorizationKey,
					null, //recId,
					businessResponse.getId(),
					now,
					Double.valueOf(amount),
					participantResponse.getId(),
					Integer.valueOf(0), //nationalStats,
					Integer.valueOf(0) 				//publicConsumption
				);
				
				String recResponseJson = JsonUtil.toStringPretty(recResponse);
				
				genData.forEach(x -> x.setGenDataCertProvData(recResponseJson));
			}
		} catch (JsonProcessingException e) {
			throw new CertificateServiceException(e);
		}

	}

	@Override
	public void generateCertificate(ClientVo client) throws CertificateServiceException {
		// TODO Auto-generated method stub

	}

	//--- API methods ---------------------------
	public LoginResponse login(String email, String password) {
		return JsonCaller.post(
				this.configuration.getUrl() + ENDPOINT_LOGIN, 
				new LoginRequest()
					.withEmail(email)
					.withPassword(password),
				LoginResponse.class);
	}
	
	public RegistrantResponse createRegistrant(
		String authorizationKey,
		String socialName,
		String email,
		String password,
		String iRec,
		String cnpj,
		String phone,
		String expirationDay,
		String priceIrec,
		String priceRecBrazil
	) {
		return JsonCaller.post(
			this.configuration.getUrl() +  ENDPOINT_REGISTRANTS, 
			this.generateHeaders(authorizationKey),
			new RegistrantRequest()
				.withSocialName(socialName)
				.withEmail(email)
				.withPassword(password)
				.withPasswordConfirmation(password)
				.withIrec(iRec)
				.withCnpj(cnpj)
				.withPhone(phone)
				.withExpirationDay(expirationDay)
				.withPriceIrec(priceIrec)
				.withPriceRecBrazil(priceRecBrazil)
			,
			RegistrantResponse.class);
	}
	
	public SourceResponse createSource(
		String authorizationKey,
		String name,
		Integer variation,
		Integer efficency
	) {
		return JsonCaller.post(
			this.configuration.getUrl() +  ENDPOINT_SOURCES, 
			this.generateHeaders(authorizationKey),
			new SourceRequest()
				.withName(name)
				.withVariation(variation)
				.withEfficency(efficency)
			,
			SourceResponse.class);
	}

	public TechnologyResponse createTechnology(
		String authorizationKey,
		String name
	) {
		return JsonCaller.post(
			this.configuration.getUrl() +  ENDPOINT_TECHNOLOGIES, 
			this.generateHeaders(authorizationKey),
			new TechnologyRequest()
				.withName(name)
			,
			TechnologyResponse.class);
	}
	
	public BusinessResponse createBusiness(
		String authorizationKey,
		String socialName,
		String ceg,
		String aneel,
		String cnpj,
		Integer isCarbonCredit,
		Date comercialOperationAt,
		String registrantId,
		String sourceId,
		String technologyId,
		String address
	) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return JsonCaller.post(
			this.configuration.getUrl() +  ENDPOINT_BUSINESS, 
			this.generateHeaders(authorizationKey),
			new BusinessRequest()
				.withSocialName(socialName)
				.withCeg(ceg)
				.withAneel(aneel)
				.withCnpj(cnpj)
				.withIsCarbonCredit(isCarbonCredit)
				.withComercialOperationAt(dateFormat.format(comercialOperationAt))
				.withRegistrantId(registrantId)
				.withSourceId(sourceId)
				.withTechnologyId(technologyId)
				.withAddress(address)
			,
			BusinessResponse.class);
	}
	
	public ParticipantResponse createParticipant(
		String authorizationKey,
		String name,
		String irec
	) {
		return JsonCaller.post(
			this.configuration.getUrl() +  ENDPOINT_PARTICIPANTS, 
			this.generateHeaders(authorizationKey),
			new ParticipantRequest()
				.withName(name)
				.withIrec(irec)
			,
			ParticipantResponse.class);
	}
	
	public RecResponse createRec(
		String authorizationKey,
		String recId,
		String bssinessId,
		Date date,
		Double volumn,
		Integer participantId,
		Integer nationalStats,
		Integer publicConsumption
	) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return JsonCaller.post(
			this.configuration.getUrl() +  ENDPOINT_RECS,
			this.generateHeaders(authorizationKey),
			new RecRequest()
				.withRecId(recId)
				.withBusinessId(bssinessId)
				.withDate(bssinessId)
				.withDate(dateFormat.format(date))
				.withVolume(volumn)
				.withParticipantId(participantId)
				.withNationalStats(nationalStats)
				.withPublicConsumption(publicConsumption)
			,
			RecResponse.class);
	}
}
