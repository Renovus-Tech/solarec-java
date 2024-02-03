package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDataCategoryVo;

public class DataCategoryVo extends DbDataCategoryVo {

	//--- Constructors --------------------------
	public DataCategoryVo() {
	}

	public DataCategoryVo(Integer dataCatId) {
		this.setPk(dataCatId);
	}

}