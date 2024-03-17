package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliMetadataVo;

public class CliMetadataVo extends DbCliMetadataVo {

	//--- Constructors --------------------------
	public CliMetadataVo() {
	}

	public CliMetadataVo(Integer cliId, String metadataName) {
		this.setPk(cliId, metadataName);
	}

}