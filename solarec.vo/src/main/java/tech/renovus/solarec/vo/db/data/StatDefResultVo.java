package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbStatDefResultVo;

public class StatDefResultVo extends DbStatDefResultVo {

	//--- Constructors --------------------------
	public StatDefResultVo() {
	}

	public StatDefResultVo(Integer statDefId, Integer statTypeId) {
		this.setPk(statDefId, statTypeId);
	}

}