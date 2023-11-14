package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbMlDataRunVo;

public class MlDataRunVo extends DbMlDataRunVo {

	//--- Constructors --------------------------
	public MlDataRunVo() {
	}

	public MlDataRunVo(Integer runId) {
		this.setPk(runId);
	}

}