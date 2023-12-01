package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliLocUsrSettingVo;

public class CliLocUsrSettingVo extends DbCliLocUsrSettingVo {

	//--- Constructors --------------------------
	public CliLocUsrSettingVo() {
	}

	public CliLocUsrSettingVo(Integer cliId, Integer locId, Integer usrId) {
		this.setPk(cliId, locId, usrId);
	}

}