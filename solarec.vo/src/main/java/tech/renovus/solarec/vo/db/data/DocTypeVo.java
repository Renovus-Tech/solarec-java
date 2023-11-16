package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbDocTypeVo;

public class DocTypeVo extends DbDocTypeVo {

	//--- Constructors --------------------------
	public DocTypeVo() {
	}

	public DocTypeVo(Integer docTypeId) {
		this.setPk(docTypeId);
	}

}