package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbStatDefSourceVo;

public class StatDefSourceVo extends DbStatDefSourceVo {

	//--- Constructors --------------------------
	public StatDefSourceVo() {
	}

	public StatDefSourceVo(Integer statDefId, Integer dataTypeId) {
		this.setPk(statDefId, dataTypeId);
	}

}