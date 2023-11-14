package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbGenAlarmVo;

public class GenAlarmVo extends DbGenAlarmVo {

	//--- Constructors --------------------------
	public GenAlarmVo() {
	}

	public GenAlarmVo(Integer cliId, Integer genId, Double alarmCode) {
		this.setPk(cliId, genId, alarmCode);
	}

}