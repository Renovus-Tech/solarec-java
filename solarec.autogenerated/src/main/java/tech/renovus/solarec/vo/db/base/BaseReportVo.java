package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
public class BaseReportVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_REP_DATE_GENERATED = "rep_date_generated";
	public static final String COLUMN_REP_DATE_FROM = "rep_date_from";
	public static final String COLUMN_REP_TYPE_ID = "rep_type_id";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_REP_DATE_TO = "rep_date_to";
	public static final String COLUMN_REP_ID = "rep_id_auto";
	public static final String COLUMN_REG_FILE = "reg_file";
	public static final String COLUMN_REP_TITLE = "rep_title";
	public static final String COLUMN_REP_FILE = "rep_file";
	public static final String COLUMN_REP_FLAGS = "rep_flags";

	public static final int LENGTH_COLUMN_REG_FILE =  200;
	public static final int LENGTH_COLUMN_REP_TITLE =  200;
	public static final int LENGTH_COLUMN_REP_FILE =  100;
	public static final int LENGTH_COLUMN_REP_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.repFlags; }
	@Override public void setFlags(String repFlags) { this.repFlags = repFlags; }

	//--- Private properties --------------------
	private @Getter @Setter java.util.Date repDateGenerated;
	private @Getter @Setter java.util.Date repDateFrom;
	private @Getter @Setter Integer repTypeId;
	private @Getter @Setter Integer cliId;
	private @Getter @Setter java.util.Date repDateTo;
	private @Getter @Setter Integer repId;
	private @Getter @Setter String regFile;
	private @Getter @Setter String repTitle;
	private @Getter @Setter String repFile;
	private @Getter @Setter String repFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.repId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseReportVo)) return false;
		
		BaseReportVo aObj = (BaseReportVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.repId,aObj.repId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.repId != null) hashCode += this.repId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseReportVo aObj = (BaseReportVo) obj;
		if (!ClassUtil.equals(this.repDateGenerated,aObj.repDateGenerated)) {
			return false;
		}
		if (!ClassUtil.equals(this.repDateFrom,aObj.repDateFrom)) {
			return false;
		}
		if (!ClassUtil.equals(this.repTypeId,aObj.repTypeId)) {
			return false;
		}
		if (!ClassUtil.equals(this.repDateTo,aObj.repDateTo)) {
			return false;
		}
		if (!ClassUtil.equals(this.regFile,aObj.regFile)) {
			return false;
		}
		if (!ClassUtil.equals(this.repTitle,aObj.repTitle)) {
			return false;
		}
		if (!ClassUtil.equals(this.repFile,aObj.repFile)) {
			return false;
		}
		if (!ClassUtil.equals(this.repFlags,aObj.repFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer repId) {
		this.cliId = cliId;
		this.repId = repId;
	}

	public void setPk(BaseReportVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.cliId, aVo.repId);
		}
	}

}