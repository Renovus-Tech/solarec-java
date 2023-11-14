package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbPrfFunctionalityVo;

public class PrfFunctionalityVo extends DbPrfFunctionalityVo {

	//--- Constructors --------------------------
	public PrfFunctionalityVo() {
	}

	public PrfFunctionalityVo(Integer prfId, Integer fncId) {
		this.setPk(prfId, fncId);
	}

}