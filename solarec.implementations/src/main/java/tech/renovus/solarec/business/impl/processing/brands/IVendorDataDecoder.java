package tech.renovus.solarec.business.impl.processing.brands;

public interface IVendorDataDecoder {

	public String getSourceCode(String code);
	public Integer getDataType(String code);
}
