package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbProfileVo;

public class ProfileVo extends DbProfileVo {

	//--- Constructors --------------------------
	public ProfileVo() {
	}

	public ProfileVo(Integer prfId) {
		this.setPk(prfId);
	}

}