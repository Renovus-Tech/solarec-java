package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbStatDefSourceVo;

public class StatDefSourceVo extends DbStatDefSourceVo {

	//--- Constructors --------------------------
	public StatDefSourceVo() {
	}

	public StatDefSourceVo(Integer statDefId, Integer dataTypeId) {
		this.setPk(statDefId, dataTypeId);
	}

}