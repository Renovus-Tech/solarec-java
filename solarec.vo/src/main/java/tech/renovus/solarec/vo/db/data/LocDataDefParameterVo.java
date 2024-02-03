package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbLocDataDefParameterVo;

public class LocDataDefParameterVo extends DbLocDataDefParameterVo {

	//--- Constructors --------------------------
	public LocDataDefParameterVo() {
	}

	public LocDataDefParameterVo(Integer cliId, Integer locId, Integer dataDefId, Integer dataDefParId) {
		this.setPk(cliId, locId, dataDefId, dataDefParId);
	}

}