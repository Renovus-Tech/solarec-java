package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbCliGenAlarmVo;

public class CliGenAlarmVo extends DbCliGenAlarmVo {

	//--- Constructors --------------------------
	public CliGenAlarmVo() {
	}

	public CliGenAlarmVo(Integer cliId, Double alarmCode) {
		this.setPk(cliId, alarmCode);
	}

}