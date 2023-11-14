package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbLocMetadataVo;

public class LocMetadataVo extends DbLocMetadataVo {

	//--- Constructors --------------------------
	public LocMetadataVo() {
	}

	public LocMetadataVo(Integer cliId, Integer locId, String metadataName) {
		this.setPk(cliId, locId, metadataName);
	}

}