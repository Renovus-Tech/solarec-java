package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliGenAlarmVo;

public class CliGenAlarmVo extends DbCliGenAlarmVo {

	//--- Constructors --------------------------
	public CliGenAlarmVo() {
	}

	public CliGenAlarmVo(Integer cliId, Double alarmCode) {
		this.setPk(cliId, alarmCode);
	}

}