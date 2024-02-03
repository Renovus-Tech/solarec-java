package tech.renovus.solarec.inverters.common;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.LocationVo;

public interface InverterService {

	Collection<GenDataVo> retrieveData(ClientVo client);
	
	default String getParameter(ClientVo vo, String name) {
		if (vo == null) return null;
		CliDataDefParameterVo paramVo = vo.getDataDefParameterVo(name);
		return paramVo == null ? null : paramVo.getCliDataDefParValue();
	}
	
	default String getParameter(LocationVo vo, String name) {
		if (vo == null) return null;
		LocDataDefParameterVo paramVo = vo.getDataDefParameterVo(name);
		return paramVo == null ? null : paramVo.getCliDataDefParValue();
	}
	
	default String getParameter(GeneratorVo vo, String name) {
		if (vo == null) return null;
		GenDataDefParameterVo paramVo = vo.getDataDefParameterVo(name);
		return paramVo == null ? null : paramVo.getCliDataDefParValue();
	}
}
