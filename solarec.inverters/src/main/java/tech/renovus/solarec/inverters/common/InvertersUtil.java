package tech.renovus.solarec.inverters.common;

import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;
import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.LocationVo;

public class InvertersUtil {
	
	//--- Constructors --------------------------
	private InvertersUtil() {}
	
	//--- Public methods ------------------------
	
	public static String getParameter(ClientVo vo, String name) {
		if (vo == null) return null;
		CliDataDefParameterVo paramVo = vo.getDataDefParameterVo(name);
		return paramVo == null ? null : paramVo.getCliDataDefParValue();
	}
	
	public static String getParameter(LocationVo vo, String name) {
		if (vo == null) return null;
		LocDataDefParameterVo paramVo = vo.getDataDefParameterVo(name);
		return paramVo == null ? null : paramVo.getLocDataDefParValue();
	}
	
	public static String getParameter(GeneratorVo vo, String name) {
		if (vo == null) return null;
		GenDataDefParameterVo paramVo = vo.getDataDefParameterVo(name);
		return paramVo == null ? null : paramVo.getGenDataDefParValue();
	}
	
	
	public static void setParameter(ClientVo vo, String name, String value) {
		if (vo == null) return;
		CliDataDefParameterVo paramVo = vo.getDataDefParameterVo(name);
		if (paramVo == null) paramVo = createParameter(vo, name);
		paramVo.setCliDataDefParValue(value);
	}
	
	public static void setParameter(LocationVo vo, String name, String value) {
		if (vo == null) return;
		LocDataDefParameterVo paramVo = vo.getDataDefParameterVo(name);
		if (paramVo == null) paramVo = createParameter(vo, name);
		paramVo.setLocDataDefParValue(value);
	}
	
	public static void setParameter(GeneratorVo vo, String name, String value) {
		if (vo == null) return;
		GenDataDefParameterVo paramVo = vo.getDataDefParameterVo(name);
		if (paramVo == null) paramVo = createParameter(vo, name);
		paramVo.setGenDataDefParValue(value);
	}
	
	public static CliDataDefParameterVo createParameter(ClientVo vo, String paramName) {
		DataDefParameterVo paramVo = new DataDefParameterVo();
		paramVo.setDataDefParName(paramName);
		
		CliDataDefParameterVo result = new CliDataDefParameterVo();
		result.setDataDefParameter(paramVo);
		result.setCliId(vo.getCliId());
		
		vo.add(result);
		
		return result;
	}
	
	public static LocDataDefParameterVo createParameter(LocationVo vo, String paramName) {
		DataDefParameterVo paramVo = new DataDefParameterVo();
		paramVo.setDataDefParName(paramName);
		
		LocDataDefParameterVo result = new LocDataDefParameterVo();
		result.setDataDefParameter(paramVo);
		result.setCliId(vo.getCliId());
		result.setLocId(vo.getLocId());
		
		vo.add(result);
		
		return result;
	}
	
	public static GenDataDefParameterVo createParameter(GeneratorVo vo, String paramName) {
		DataDefParameterVo paramVo = new DataDefParameterVo();
		paramVo.setDataDefParName(paramName);
		
		GenDataDefParameterVo result = new GenDataDefParameterVo();
		result.setDataDefParameter(paramVo);
		result.setCliId(vo.getCliId());
		result.setGenId(vo.getGenId());
		
		vo.add(result);
		
		return result;
	}
}
