package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbStaMetadataVo;

public class StaMetadataVo extends DbStaMetadataVo {

	//--- Constructors --------------------------
	public StaMetadataVo() {
	}

	public StaMetadataVo(Integer cliId, Integer staId, String metadataName) {
		this.setPk(cliId, staId, metadataName);
	}

}