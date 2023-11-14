package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbUsrProfileVo;

public class UsrProfileVo extends DbUsrProfileVo {

	//--- Constructors --------------------------
	public UsrProfileVo() {
	}

	public UsrProfileVo(Integer usrId, Integer prfId, Integer cliId) {
		this.setPk(usrId, prfId, cliId);
	}

}