package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbDataProAlertProcessingVo;

public class DataProAlertProcessingVo extends DbDataProAlertProcessingVo {

	//--- Constructors --------------------------
	public DataProAlertProcessingVo() {
	}

	public DataProAlertProcessingVo(Integer dataProId, Integer alertProId) {
		this.setPk(dataProId, alertProId);
	}

}