package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbLocMetadataVo;

public class LocMetadataVo extends DbLocMetadataVo {

	//--- Constructors --------------------------
	public LocMetadataVo() {
	}

	public LocMetadataVo(Integer cliId, Integer locId, String metadataName) {
		this.setPk(cliId, locId, metadataName);
	}

}