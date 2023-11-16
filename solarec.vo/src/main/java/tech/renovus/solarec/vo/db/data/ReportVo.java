package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbReportVo;

public class ReportVo extends DbReportVo {

	//--- Constructors --------------------------
	public ReportVo() {
	}

	public ReportVo(Integer cliId, Integer repId) {
		this.setPk(cliId, repId);
	}

}