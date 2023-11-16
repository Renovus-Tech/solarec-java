package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbMlDataRunVo;

public class MlDataRunVo extends DbMlDataRunVo {

	//--- Constructors --------------------------
	public MlDataRunVo() {
	}

	public MlDataRunVo(Integer runId) {
		this.setPk(runId);
	}

}