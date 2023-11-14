package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbLocUserVo;

public class LocUserVo extends DbLocUserVo {

	//--- Constructors --------------------------
	public LocUserVo() {
	}

	public LocUserVo(Integer cliId, Integer locId, Integer usrId) {
		this.setPk(cliId, locId, usrId);
	}

}