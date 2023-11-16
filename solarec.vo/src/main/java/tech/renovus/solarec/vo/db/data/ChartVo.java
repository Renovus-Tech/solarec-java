package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbChartVo;

public class ChartVo extends DbChartVo {

	//--- Constructors --------------------------
	public ChartVo() {
	}

	public ChartVo(Integer chrId) {
		this.setPk(chrId);
	}

}