package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbSdgVo;

public class SdgVo extends DbSdgVo {

	//--- Constructors --------------------------
	public SdgVo() {
	}

	public SdgVo(Integer sdgId) {
		this.setPk(sdgId);
	}

}