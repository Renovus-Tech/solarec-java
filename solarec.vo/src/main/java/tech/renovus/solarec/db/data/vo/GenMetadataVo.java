package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbGenMetadataVo;

public class GenMetadataVo extends DbGenMetadataVo {

	//--- Constructors --------------------------
	public GenMetadataVo() {
	}

	public GenMetadataVo(Integer cliId, Integer genId, String metadataCode) {
		this.setPk(cliId, genId, metadataCode);
	}

}