package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseClientVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_DEMO_DATE = "cli_demo_date";
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_CLI_ID = "cli_id_auto";
	public static final String COLUMN_CLI_NAME_ADDRESS = "cli_name_address";
	public static final String COLUMN_CLI_FLAGS = "cli_flags";
	public static final String COLUMN_CLI_GMT = "cli_gmt";
	public static final String COLUMN_CLI_SEC_CODE = "cli_sec_code";
	public static final String COLUMN_CLI_NAME = "cli_name";
	public static final String COLUMN_CLI_NAME_LEGAL = "cli_name_legal";

	public static final int LENGTH_COLUMN_CLI_NAME_ADDRESS =  500;
	public static final int LENGTH_COLUMN_CLI_FLAGS =  20;
	public static final int LENGTH_COLUMN_CLI_GMT =  8;
	public static final int LENGTH_COLUMN_CLI_SEC_CODE =  512;
	public static final int LENGTH_COLUMN_CLI_NAME =  100;
	public static final int LENGTH_COLUMN_CLI_NAME_LEGAL =  200;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.cliFlags; }
	@Override public void setFlags(String cliFlags) { this.cliFlags = cliFlags; }

	//--- Private properties --------------------
	private java.util.Date cliDemoDate;
	private Integer dataDefId;
	private Integer cliId;
	private String cliNameAddress;
	private String cliFlags;
	private String cliGmt;
	private String cliSecCode;
	private String cliName;
	private String cliNameLegal;

	//--- Public methods ------------------------
	public boolean validData() {
		return this.cliId != null;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseClientVo)) return false;
		
		BaseClientVo aObj = (BaseClientVo) obj;
		return ClassUtil.equals(this.cliId,aObj.cliId);
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseClientVo aObj = (BaseClientVo) obj;
		if (!ClassUtil.equals(this.cliDemoDate,aObj.cliDemoDate)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliNameAddress,aObj.cliNameAddress)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliFlags,aObj.cliFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliGmt,aObj.cliGmt)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliSecCode,aObj.cliSecCode)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliName,aObj.cliName)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliNameLegal,aObj.cliNameLegal)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId) {
		this.cliId = cliId;
	}

	public void setPk(BaseClientVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk(aVo.cliId);
		}
	}

	//--- Getters and Setters -------------------
	public java.util.Date getCliDemoDate() {
		return this.cliDemoDate;
	}
	public void setCliDemoDate(java.util.Date cliDemoDate) {
		this.cliDemoDate = cliDemoDate;
	}

	public Integer getDataDefId() {
		return this.dataDefId;
	}
	public void setDataDefId(Integer dataDefId) {
		this.dataDefId = dataDefId;
	}

	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public String getCliNameAddress() {
		return this.cliNameAddress;
	}
	public void setCliNameAddress(String cliNameAddress) {
		this.cliNameAddress = cliNameAddress;
	}

	public String getCliFlags() {
		return this.cliFlags;
	}
	public void setCliFlags(String cliFlags) {
		this.cliFlags = cliFlags;
	}

	public String getCliGmt() {
		return this.cliGmt;
	}
	public void setCliGmt(String cliGmt) {
		this.cliGmt = cliGmt;
	}

	public String getCliSecCode() {
		return this.cliSecCode;
	}
	public void setCliSecCode(String cliSecCode) {
		this.cliSecCode = cliSecCode;
	}

	public String getCliName() {
		return this.cliName;
	}
	public void setCliName(String cliName) {
		this.cliName = cliName;
	}

	public String getCliNameLegal() {
		return this.cliNameLegal;
	}
	public void setCliNameLegal(String cliNameLegal) {
		this.cliNameLegal = cliNameLegal;
	}

}