package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbDataProStatProcessingVo;

public class DataProStatProcessingVo extends DbDataProStatProcessingVo {

	//--- Constructors --------------------------
	public DataProStatProcessingVo() {
	}

	public DataProStatProcessingVo(Integer dataProId, Integer statProId) {
		this.setPk(dataProId, statProId);
	}

}