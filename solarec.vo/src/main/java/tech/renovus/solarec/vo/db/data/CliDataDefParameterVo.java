package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliDataDefParameterVo;

public class CliDataDefParameterVo extends DbCliDataDefParameterVo {

	//--- Constructors --------------------------
	public CliDataDefParameterVo() {
	}

	public CliDataDefParameterVo(Integer cliId, Integer dataDefId, Integer dataDefParId) {
		this.setPk(cliId, dataDefId, dataDefParId);
	}

}