package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliLocAlertVo;

public class CliLocAlertVo extends DbCliLocAlertVo {

	//--- Public constants ----------------------
	public static final int FLAG_SEEN	= 0;
	
	//--- Constructors --------------------------
	public CliLocAlertVo() {
	}

	public CliLocAlertVo(Integer cliId, Integer locId, Integer cliLocAlertId) {
		this.setPk(cliId, locId, cliLocAlertId);
	}
}