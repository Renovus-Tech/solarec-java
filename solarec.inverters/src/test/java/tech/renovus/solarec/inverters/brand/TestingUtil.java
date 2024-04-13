package tech.renovus.solarec.inverters.brand;

import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;

public class TestingUtil {

	public static CliDataDefParameterVo createParameter(String paramName, String paramValue) {
		DataDefParameterVo paramVo = new DataDefParameterVo();
		paramVo.setDataDefParName(paramName);
		
		CliDataDefParameterVo result = new CliDataDefParameterVo();
		result.setCliDataDefParValue(paramValue);
		result.setDataDefParameter(paramVo);
		
		return result;
	}
}
