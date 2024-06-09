package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbLocTypeVo;

public class LocTypeVo extends DbLocTypeVo {

	//--- Constructors --------------------------
	public LocTypeVo() {
	}

	public LocTypeVo(Integer locTypeId) {
		this.setPk(locTypeId);
	}

}