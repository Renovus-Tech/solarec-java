package tech.renovus.solarec.business;

import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.db.data.StatProcessingVo;

public interface CalculationService {

	StatProcessingVo calculate(DataProcessingVo dataProVo, StatDefinitionVo statDefVo);
}
