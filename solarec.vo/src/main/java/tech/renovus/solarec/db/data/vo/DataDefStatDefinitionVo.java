package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbDataDefStatDefinitionVo;

public class DataDefStatDefinitionVo extends DbDataDefStatDefinitionVo {

	//--- Constructors --------------------------
	public DataDefStatDefinitionVo() {
	}

	public DataDefStatDefinitionVo(Integer dataDefId, Integer statDefId) {
		this.setPk(dataDefId, statDefId);
	}

}