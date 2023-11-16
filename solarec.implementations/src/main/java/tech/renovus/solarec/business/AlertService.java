package tech.renovus.solarec.business;

import tech.renovus.solarec.vo.db.data.AlertDefinitionVo;
import tech.renovus.solarec.vo.db.data.AlertProcessingVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;

public interface AlertService {

	AlertProcessingVo doProcessing(DataProcessingVo dataProVo, AlertDefinitionVo alertDefVo);

}
