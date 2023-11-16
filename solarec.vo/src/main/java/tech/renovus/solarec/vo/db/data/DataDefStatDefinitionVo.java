package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDataDefStatDefinitionVo;

public class DataDefStatDefinitionVo extends DbDataDefStatDefinitionVo {

	//--- Constructors --------------------------
	public DataDefStatDefinitionVo() {
	}

	public DataDefStatDefinitionVo(Integer dataDefId, Integer statDefId) {
		this.setPk(dataDefId, statDefId);
	}

}