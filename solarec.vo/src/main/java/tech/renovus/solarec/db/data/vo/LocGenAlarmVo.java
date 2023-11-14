package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbLocGenAlarmVo;

public class LocGenAlarmVo extends DbLocGenAlarmVo {

	//--- Constructors --------------------------
	public LocGenAlarmVo() {
	}

	public LocGenAlarmVo(Integer cliId, Integer locId, Double alarmCode) {
		this.setPk(cliId, locId, alarmCode);
	}

}