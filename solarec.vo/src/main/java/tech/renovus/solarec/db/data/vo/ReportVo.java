package tech.renovus.solarec.db.data.vo;

import tech.renovus.solarec.db.data.vo.db.DbReportVo;

public class ReportVo extends DbReportVo {

	//--- Constructors --------------------------
	public ReportVo() {
	}

	public ReportVo(Integer cliId, Integer repId) {
		this.setPk(cliId, repId);
	}

}