package tech.renovus.solarec.certificate.common;

import tech.renovus.solarec.vo.db.data.ClientVo;

public interface CertificateService {

	public void register(ClientVo client);
	public void updateRegistration(ClientVo client);
	public void registerGeneration(ClientVo client);
	public void generateCertificate(ClientVo client);
}
