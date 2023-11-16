package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbGenAlarmVo;

public class GenAlarmVo extends DbGenAlarmVo {

	//--- Constructors --------------------------
	public GenAlarmVo() {
	}

	public GenAlarmVo(Integer cliId, Integer genId, Double alarmCode) {
		this.setPk(cliId, genId, alarmCode);
	}

}