package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbPrfFunctionalityVo;

public class PrfFunctionalityVo extends DbPrfFunctionalityVo {

	//--- Constructors --------------------------
	public PrfFunctionalityVo() {
	}

	public PrfFunctionalityVo(Integer prfId, Integer fncId) {
		this.setPk(prfId, fncId);
	}

}