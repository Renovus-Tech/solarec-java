package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbDocTypeVo;

public class DocTypeVo extends DbDocTypeVo {

	//--- Constructors --------------------------
	public DocTypeVo() {
	}

	public DocTypeVo(Integer docTypeId) {
		this.setPk(docTypeId);
	}

}