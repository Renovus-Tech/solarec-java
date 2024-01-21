package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbCliGenAlertVo;

public class CliGenAlertVo extends DbCliGenAlertVo {

	//--- Public constants ----------------------
	public static final int FLAG_SEEN	= 0;
	
	//--- Private properties --------------------
	private String cliName;
	private String locName;
	private String locCode;
	private String genName;
	private String genCode;
	
	//--- Constructors --------------------------
	public CliGenAlertVo() {
	}

	public CliGenAlertVo(Integer cliId, Integer genId, Integer cliGenAlertId) {
		this.setPk(cliId, genId, cliGenAlertId);
	}

	//--- Getters and Setters -------------------
	public String getCliName() {
		return cliName;
	}

	public void setCliName(String cliName) {
		this.cliName = cliName;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getGenName() {
		return genName;
	}

	public void setGenName(String genName) {
		this.genName = genName;
	}

	public String getGenCode() {
		return genCode;
	}

	public void setGenCode(String genCode) {
		this.genCode = genCode;
	}
}