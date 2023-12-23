package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliLocAlertVo;

public class CliLocAlertVo extends DbCliLocAlertVo {

	//--- Public constants ----------------------
	public static final int FLAG_SEEN	= 0;
	
	//--- Private properties --------------------
	private String parsedMessage;
	
	//--- Constructors --------------------------
	public CliLocAlertVo() {
	}

	public CliLocAlertVo(Integer cliId, Integer locId, Integer cliLocAlertId) {
		this.setPk(cliId, locId, cliLocAlertId);
	}

	//--- Getters and Setters -------------------
	public String getParsedMessage() {
		return parsedMessage;
	}

	public void setParsedMessage(String parsedMessage) {
		this.parsedMessage = parsedMessage;
	}

}