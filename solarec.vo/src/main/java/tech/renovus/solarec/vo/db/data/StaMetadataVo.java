package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbStaMetadataVo;

public class StaMetadataVo extends DbStaMetadataVo {

	//--- Constructors --------------------------
	public StaMetadataVo() {
	}

	public StaMetadataVo(Integer cliId, Integer staId, String metadataName) {
		this.setPk(cliId, staId, metadataName);
	}

}