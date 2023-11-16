package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbGenMetadataVo;

public class GenMetadataVo extends DbGenMetadataVo {

	//--- Constructors --------------------------
	public GenMetadataVo() {
	}

	public GenMetadataVo(Integer cliId, Integer genId, String metadataCode) {
		this.setPk(cliId, genId, metadataCode);
	}

}