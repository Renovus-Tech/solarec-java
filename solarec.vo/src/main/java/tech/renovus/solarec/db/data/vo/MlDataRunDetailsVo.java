package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbMlDataRunDetailsVo;

public class MlDataRunDetailsVo extends DbMlDataRunDetailsVo {

	//--- Constructors --------------------------
	public MlDataRunDetailsVo() {
	}

	public MlDataRunDetailsVo(Integer runDetId) {
		this.setPk(runDetId);
	}

}