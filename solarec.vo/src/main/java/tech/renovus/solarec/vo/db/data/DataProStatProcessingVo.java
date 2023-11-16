package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDataProStatProcessingVo;

public class DataProStatProcessingVo extends DbDataProStatProcessingVo {

	//--- Constructors --------------------------
	public DataProStatProcessingVo() {
	}

	public DataProStatProcessingVo(Integer dataProId, Integer statProId) {
		this.setPk(dataProId, statProId);
	}

}