package tech.renovus.solarec.certificate.disabled;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import tech.renovus.solarec.certificate.CertificateService;
import tech.renovus.solarec.vo.db.data.ClientVo;

@Service
@ConditionalOnProperty(name = "solarec.service.certificate.provider", havingValue = "disabled")
public class DisabledCertificateService implements CertificateService {

	@Override public void register(ClientVo client) throws CertificateServiceException { /* do nothing, service is disabled */ 	}

	@Override public void updateRegistration(ClientVo client) throws CertificateServiceException { /* do nothing, service is disabled */ 	}

	@Override public void registerGeneration(ClientVo client) throws CertificateServiceException { /* do nothing, service is disabled */ 	}

	@Override public void generateCertificate(ClientVo client) throws CertificateServiceException { /* do nothing, service is disabled */ 	}

}
