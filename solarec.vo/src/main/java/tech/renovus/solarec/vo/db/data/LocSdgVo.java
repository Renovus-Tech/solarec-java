package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbLocSdgVo;

public class LocSdgVo extends DbLocSdgVo {

	//--- Constructors --------------------------
	public LocSdgVo() {
	}

	public LocSdgVo(Integer cliId, Integer locId, Integer sdgId) {
		this.setPk(cliId, locId, sdgId);
	}

}