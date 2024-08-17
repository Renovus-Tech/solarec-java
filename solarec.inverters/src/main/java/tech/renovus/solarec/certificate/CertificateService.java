package tech.renovus.solarec.certificate;

import tech.renovus.solarec.vo.db.data.ClientVo;

public interface CertificateService {

	public static class CertificateServiceException extends Exception {
		
		private static final long serialVersionUID = -9101682723565122885L;
		
		public CertificateServiceException() { super(); }
		public CertificateServiceException(String msg) { super(msg); }
		public CertificateServiceException(Exception parent) { super(parent); }
		public CertificateServiceException(String msg, Exception parent) { super(msg, parent); }
	}
	
	public void register(ClientVo client) throws CertificateServiceException;
	public void updateRegistration(ClientVo client) throws CertificateServiceException;
	public void registerGeneration(ClientVo client) throws CertificateServiceException;
	public void generateCertificate(ClientVo client) throws CertificateServiceException;
}
