package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDataDefinitionVo;

public class DataDefinitionVo extends DbDataDefinitionVo {
	
	//--- Public constants ----------------------
	public static final int FLAG_ENABLED		= 0;
	public static final int FLAG_ENABLED_DEV	= 1;
	public static final int FALG_INVERTER		= 2;

	//--- Constructors --------------------------
	public DataDefinitionVo() {
	}

	public DataDefinitionVo(Integer dataDefId) {
		this.setPk(dataDefId);
	}

}