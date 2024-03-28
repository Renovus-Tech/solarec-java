package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCtrDataVo;

public class CtrDataVo extends DbCtrDataVo {

	//--- Constructors --------------------------
	public CtrDataVo() {
	}

	public CtrDataVo(Integer ctrId, java.util.Date dataDate, Integer dataTypeId) {
		this.setPk(ctrId, dataDate, dataTypeId);
	}

}