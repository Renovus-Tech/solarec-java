package tech.renovus.solarec.business.impl.processing.brands;

import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renvous.solarec.util.StringUtil;

public class AbbDecoder implements IVendorDataDecoder {

	//--- Constructors --------------------------
	private AbbDecoder() {}
	private static final AbbDecoder instance = new AbbDecoder();
	public static AbbDecoder getInstance() { return AbbDecoder.instance; }
	
	//--- Overridden methods --------------------
	@Override public String getSourceCode(String code) {
//		if (code.equals("P_avg_parktotal") || code.equals("P_avg_parkmaximum"))	return null;
		
		int indexUnder = code.indexOf('_');
		return indexUnder == -1 ? null : code.substring(0, indexUnder);
	}
	
	//Pattern: Inverter_2_DC_Power
	//Pattern: Inverter_22_AC_Power
	@Override public Integer getDataType(String code) {
		if (code.startsWith("Inverter_"))	return this.getDataTypeInverter(code);
		if (code.startsWith("Met_"))		return this.getDataTypeStation(code);
		return null;
	}

	private Integer getDataTypeInverter(String code) {
		int index = code.indexOf('_', "Inverter_".length());
		String typeText = code.substring(index + 1);
		int typeCode = -1;
		
		if (StringUtil.notEmpty(typeText)) {
				 if (typeText.endsWith("DC_Power")) 				typeCode = DataTypeVo.TYPE_SOLAR_INVERTER_DC_POWER;		//501	TYPE_SOLAR_INVERTER_DC_POWER	Inverter_2_DC_Power
			else if (typeText.endsWith("AC_Power")) 				typeCode = DataTypeVo.TYPE_SOLAR_INVERTER_AC_POWER;		//502	TYPE_SOLAR_INVERTER_AC_POWER	Inverter_2_AC_Power
		}

		return typeCode == -1 ? null : Integer.valueOf(typeCode);
	}
	
	private Integer getDataTypeStation(String code) {
		int index = code.indexOf('_', "Met_".length());
		String typeText = code.substring(index + 1);
		int typeCode = -1;
		
		if (StringUtil.notEmpty(typeText)) {
			if (typeText.endsWith("Irradiation (KWh)")) 			typeCode = DataTypeVo.TYPE_SOLAR_STATION_IRRADIATION;				//505	TYPE_SOLAR_STATION_IRRADIATION			Met_1_Irradiation
			else if (typeText.endsWith("Ambient_Temperature")) 		typeCode = DataTypeVo.TYPE_SOLAR_STATION_AMBIENT_TEMPERATURE;		//503	TYPE_SOLAR_STATION_AMBIENT_TEMPERATURE	Met_1_Ambient_Temperature
//			else if (typeText.endsWith("Module_Temperature")) 		typeCode = DataTypeVo.TYPE_SOLAR_STATION_MODULE_TEMPERATURE;		//504	TYPE_SOLAR_STATION_MODULE_TEMPERATURE	Met_1_Module_Temperature
		}
		
		return typeCode == -1 ? null : Integer.valueOf(typeCode);
	}
}
