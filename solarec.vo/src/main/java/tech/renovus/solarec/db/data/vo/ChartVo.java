package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbChartVo;

public class ChartVo extends DbChartVo {

	//--- Constructors --------------------------
	public ChartVo() {
	}

	public ChartVo(Integer chrId) {
		this.setPk(chrId);
	}

}