package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbMlDataRunDetailsVo;

public class MlDataRunDetailsVo extends DbMlDataRunDetailsVo {

	//--- Constructors --------------------------
	public MlDataRunDetailsVo() {
	}

	public MlDataRunDetailsVo(Integer runDetId) {
		this.setPk(runDetId);
	}

}