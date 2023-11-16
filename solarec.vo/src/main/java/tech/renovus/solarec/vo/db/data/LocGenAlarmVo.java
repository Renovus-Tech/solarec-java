package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbLocGenAlarmVo;

public class LocGenAlarmVo extends DbLocGenAlarmVo {

	//--- Constructors --------------------------
	public LocGenAlarmVo() {
	}

	public LocGenAlarmVo(Integer cliId, Integer locId, Double alarmCode) {
		this.setPk(cliId, locId, alarmCode);
	}

}