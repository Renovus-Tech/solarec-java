package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbStatDefResultVo;

public class StatDefResultVo extends DbStatDefResultVo {

	//--- Constructors --------------------------
	public StatDefResultVo() {
	}

	public StatDefResultVo(Integer statDefId, Integer statTypeId) {
		this.setPk(statDefId, statTypeId);
	}

}