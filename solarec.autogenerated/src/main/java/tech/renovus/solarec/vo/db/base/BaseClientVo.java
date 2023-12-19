package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
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
	private @Getter @Setter java.util.Date cliDemoDate;
	private @Getter @Setter Integer dataDefId;
	private @Getter @Setter Integer cliId;
	private @Getter @Setter String cliNameAddress;
	private @Getter @Setter String cliFlags;
	private @Getter @Setter String cliGmt;
	private @Getter @Setter String cliSecCode;
	private @Getter @Setter String cliName;
	private @Getter @Setter String cliNameLegal;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseClientVo)) return false;
		
		BaseClientVo aObj = (BaseClientVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		return true;
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
			this.setPk((Integer)aVo.cliId);
		}
	}

}