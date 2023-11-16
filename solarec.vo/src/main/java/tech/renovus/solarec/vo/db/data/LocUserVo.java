package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbLocUserVo;

public class LocUserVo extends DbLocUserVo {

	//--- Constructors --------------------------
	public LocUserVo() {
	}

	public LocUserVo(Integer cliId, Integer locId, Integer usrId) {
		this.setPk(cliId, locId, usrId);
	}

}