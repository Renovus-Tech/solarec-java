package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDataDefParameterVo;

public class DataDefParameterVo extends DbDataDefParameterVo {

	//--- Constructors --------------------------
	public DataDefParameterVo() {
	}

	public DataDefParameterVo(Integer dataDefId, Integer dataDefParId) {
		this.setPk(dataDefId, dataDefParId);
	}

}