package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliGenAlertVo;

public class CliGenAlertVo extends DbCliGenAlertVo {

	//--- Public constants ----------------------
	public static final int FLAG_SEEN	= 0;
	
	//--- Private properties --------------------
	private String parsedMessage;
	
	//--- Constructors --------------------------
	public CliGenAlertVo() {
	}

	public CliGenAlertVo(Integer cliId, Integer genId, Integer cliGenAlertId) {
		this.setPk(cliId, genId, cliGenAlertId);
	}

	//--- Getters and Setters -------------------
	public String getParsedMessage() {
		return parsedMessage;
	}

	public void setParsedMessage(String parsedMessage) {
		this.parsedMessage = parsedMessage;
	}
}