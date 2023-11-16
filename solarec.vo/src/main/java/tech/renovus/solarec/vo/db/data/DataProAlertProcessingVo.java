package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDataProAlertProcessingVo;

public class DataProAlertProcessingVo extends DbDataProAlertProcessingVo {

	//--- Constructors --------------------------
	public DataProAlertProcessingVo() {
	}

	public DataProAlertProcessingVo(Integer dataProId, Integer alertProId) {
		this.setPk(dataProId, alertProId);
	}

}