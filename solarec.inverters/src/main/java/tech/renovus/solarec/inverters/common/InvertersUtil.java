package tech.renovus.solarec.inverters.common;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;

import tech.renovus.solarec.logger.LoggerService;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.CliMetadataVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;
import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.GenMetadataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.LocMetadataVo;
import tech.renovus.solarec.vo.db.data.LocationVo;

public class InvertersUtil {
	
	//--- Public constants ----------------------
	public static final String INFO_DATA_RETRIEVE_START	= "Start data retrieve for: {0} | {1} | {2} for period {3} - {4}";
	public static final String INFO_DATA_RETRIEVE_END	= "Ended data retrieve for: {0} | {1} | {2} - with {3} records of data";
	
	//--- Constructors --------------------------
	private InvertersUtil() {}
	
	//--- Log methods ---------------------------
	public static final void logInfo(String message, Object... params) {
		LoggerService.inverterLogger().info(MessageFormat.format(message, params));
	}
	
	public static final void logError(String message, Object... params) {
		LoggerService.inverterLogger().error(MessageFormat.format(message, params));
	}
	
	public static final void logError(String message, Exception exception, Object... params) {
		LoggerService.inverterLogger().error(MessageFormat.format(message, params), exception);
	}
	
	public static final void logDebug(String message, Object... params) {
		LoggerService.inverterLogger().debug(MessageFormat.format(message, params));
	}
	
	//--- Public methods ------------------------
	public static String getParameter(GeneratorVo genVo, LocationVo locVo, ClientVo cliVo, String param) {
		String result = InvertersUtil.getParameter(genVo, param);
		if (StringUtil.isEmpty(result)) result = InvertersUtil.getParameter(locVo, param);
		if (StringUtil.isEmpty(result)) result = InvertersUtil.getParameter(cliVo, param);
		
		return result;
	}
	
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
	
	public static String getMetadata(GeneratorVo genVo, LocationVo locVo, ClientVo cliVo, String name) {
		String result = InvertersUtil.getMetadata(genVo, name);
		if (StringUtil.isEmpty(result)) result = InvertersUtil.getMetadata(locVo, name);
		if (StringUtil.isEmpty(result)) result = InvertersUtil.getMetadata(cliVo, name);
		
		return result;
	}
	
	public static String getMetadata(ClientVo vo, String name) {
		if (vo == null) return null;
		CliMetadataVo metadata = vo.getMetadataVo(name);
		return metadata == null ? null : metadata.getMetadataValue();
	}
	
	public static String getMetadata(LocationVo vo, String name) {
		if (vo == null) return null;
		LocMetadataVo metadata = vo.getMetadataVo(name);
		return metadata == null ? null : metadata.getMetadataValue();
	}
	
	public static String getMetadata(GeneratorVo vo, String name) {
		if (vo == null) return null;
		GenMetadataVo metadata = vo.getMetadataVo(name);
		return metadata == null ? null : metadata.getMetadataValue();
	}
	
	public static void setMetadata(ClientVo vo, String name, String value) {
		if (vo == null) return;
		CliMetadataVo metadataVo = vo.getMetadataVo(name);
		if (metadataVo == null) metadataVo = createMetadata(vo, name, value);
		else metadataVo.setMetadataValue(value);
	}
	
	public static void setMetadata(LocationVo vo, String name, String value) {
		if (vo == null) return;
		LocMetadataVo metadataVo = vo.getMetadataVo(name);
		if (metadataVo == null) metadataVo = createMetadata(vo, name, value);
		else metadataVo.setMetadataValue(value);
	}
	
	public static void setMetadata(GeneratorVo vo, String name, String value) {
		if (vo == null) return;
		GenMetadataVo metadataVo = vo.getMetadataVo(name);
		if (metadataVo == null) metadataVo = createMetadata(vo, name, value);
		else metadataVo.setMetadataValue(value);
	}
	
	public static CliMetadataVo createMetadata(ClientVo vo, String name, String value) {
		CliMetadataVo result = new CliMetadataVo(vo.getCliId(), name);
		result.setMetadataValue(value);
		
		vo.add(result);
		
		return result;
	}
	
	public static LocMetadataVo createMetadata(LocationVo vo, String name, String value) {
		LocMetadataVo result = new LocMetadataVo(vo.getCliId(), vo.getLocId(), name);
		result.setMetadataValue(value);
		
		vo.add(result);
		
		return result;
	}
	
	public static GenMetadataVo createMetadata(GeneratorVo vo, String name, String value) {
		GenMetadataVo result = new GenMetadataVo(vo.getCliId(), vo.getGenId(), name);
		result.setMetadataValue(value);
		
		vo.add(result);
		
		return result;
	}
	
	public static Date calculateDateFrom(String genLastRetrieve) {
		Calendar cal = Calendar.getInstance();
		
		if (StringUtil.isEmpty(genLastRetrieve)) {
			cal.add(Calendar.DAY_OF_YEAR, -1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.AM_PM, Calendar.AM);
		} else {
			cal.setTimeInMillis(Long.parseLong(genLastRetrieve));
		}
		
		return cal.getTime();
	}

}
