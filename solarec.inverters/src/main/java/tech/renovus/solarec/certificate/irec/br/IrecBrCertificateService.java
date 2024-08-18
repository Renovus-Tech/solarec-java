package tech.renovus.solarec.certificate.irec.br;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

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
import tech.renovus.solarec.vo.db.data.ClientVo;

@Service
@ConditionalOnProperty(name = "solarec.service.certificate.provider", havingValue = "irrecbr")
public class IrecBrCertificateService implements CertificateService {

	//--- Endpoints -----------------------------
	private static final String URL_PROD				= "https://api.sisgasrec.institutototum.com.br/api/v1";
	private static final String URL_TEST				= "";
	
	private static final String ENDPOINT_LOGIN			= "/auth/login";
	private static final String ENDPOINT_REGISTRANTS	= "/registrants";
	private static final String ENDPOINT_SOURCES		= "/sources";
	private static final String ENDPOINT_TECHNOLOGIES	= "/technologies";
	private static final String ENDPOINT_BUSINESS		= "/business";
	private static final String ENDPOINT_PARTICIPANTS	= "/participants";
	private static final String ENDPOINT_RECS			= "/recs";
	
	//--- Private methods -----------------------
	private String getUrl(boolean forTest, String endpoint) {
		return (forTest ? URL_TEST : URL_PROD) + endpoint;
	}
	
	private Map<String, String> generateHeaders(String authorizationKey) {
		Map<String, String> result = new HashMap<>();
		result.put("Authorization", authorizationKey);
		return result;
	}
	
	//--- Implemented methods -------------------
	@Override
	public void register(ClientVo client) throws CertificateServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateRegistration(ClientVo client) throws CertificateServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerGeneration(ClientVo client) throws CertificateServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateCertificate(ClientVo client) throws CertificateServiceException {
		// TODO Auto-generated method stub

	}

	//--- API methods ---------------------------
	public LoginResponse login(boolean forTest, String email, String password) {
		return JsonCaller.post(
				this.getUrl(forTest, ENDPOINT_LOGIN), 
				new LoginRequest()
					.withEmail(email)
					.withPassword(password),
				LoginResponse.class);
	}
	
	public RegistrantResponse createRegistrant(
		boolean forTest,
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
			this.getUrl(forTest,  ENDPOINT_REGISTRANTS), 
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
		boolean forTest,
		String authorizationKey,
		String name,
		Integer variation,
		Integer efficency
	) {
		return JsonCaller.post(
			this.getUrl(forTest,  ENDPOINT_SOURCES), 
			this.generateHeaders(authorizationKey),
			new SourceRequest()
				.withName(name)
				.withVariation(variation)
				.withEfficency(efficency)
			,
			SourceResponse.class);
	}

	public TechnologyResponse createTechnology(
		boolean forTest,
		String authorizationKey,
		String name
	) {
		return JsonCaller.post(
			this.getUrl(forTest,  ENDPOINT_TECHNOLOGIES), 
			this.generateHeaders(authorizationKey),
			new TechnologyRequest()
				.withName(name)
			,
			TechnologyResponse.class);
	}
	
	public BusinessResponse createBusiness(
		boolean forTest,
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
			this.getUrl(forTest,  ENDPOINT_BUSINESS), 
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
		boolean forTest,
		String authorizationKey,
		String name,
		String irec
	) {
		return JsonCaller.post(
			this.getUrl(forTest,  ENDPOINT_PARTICIPANTS), 
			this.generateHeaders(authorizationKey),
			new ParticipantRequest()
				.withName(name)
				.withIrec(irec)
			,
			ParticipantResponse.class);
	}
	
	public RecResponse createRec(
		boolean forTest,
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
			this.getUrl(forTest,  ENDPOINT_RECS), 
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
