package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbUsrProfileVo;

public class UsrProfileVo extends DbUsrProfileVo {

	//--- Constructors --------------------------
	public UsrProfileVo() {
	}

	public UsrProfileVo(Integer usrId, Integer prfId, Integer cliId) {
		this.setPk(usrId, prfId, cliId);
	}

}