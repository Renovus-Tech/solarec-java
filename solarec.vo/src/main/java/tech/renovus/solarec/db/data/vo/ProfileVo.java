package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbProfileVo;

public class ProfileVo extends DbProfileVo {

	//--- Constructors --------------------------
	public ProfileVo() {
	}

	public ProfileVo(Integer prfId) {
		this.setPk(prfId);
	}

}