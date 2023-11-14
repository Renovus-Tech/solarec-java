package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbDataDefinitionVo;

public class DataDefinitionVo extends DbDataDefinitionVo {
	
	//--- Public constants ----------------------
	public static final int FLAG_ENABLED		= 0;
	public static final int FLAG_ENABLED_DEV	= 1;

	//--- Constructors --------------------------
	public DataDefinitionVo() {
	}

	public DataDefinitionVo(Integer dataDefId) {
		this.setPk(dataDefId);
	}

}