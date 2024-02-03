package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbGenDataDefParameterVo;

public class GenDataDefParameterVo extends DbGenDataDefParameterVo {

	//--- Constructors --------------------------
	public GenDataDefParameterVo() {
	}

	public GenDataDefParameterVo(Integer cliId, Integer genId, Integer dataDefId, Integer dataDefParId) {
		this.setPk(cliId, genId, dataDefId, dataDefParId);
	}

}