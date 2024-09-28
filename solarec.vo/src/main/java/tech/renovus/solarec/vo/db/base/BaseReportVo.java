package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@javax.annotation.Generated(value = "Renovus") public class BaseReportVo extends BaseDbVo implements IFlags {

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
	private java.util.Date repDateGenerated;
	private java.util.Date repDateFrom;
	private Integer repTypeId;
	private Integer cliId;
	private java.util.Date repDateTo;
	private Integer repId;
	private String regFile;
	private String repTitle;
	private String repFile;
	private String repFlags;

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

	//--- Overridden methods --------------------
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

	//--- Getters and Setters -------------------
	public java.util.Date getRepDateGenerated() {
		return this.repDateGenerated;
	}
	public void setRepDateGenerated(java.util.Date repDateGenerated) {
		this.repDateGenerated = repDateGenerated;
	}

	public java.util.Date getRepDateFrom() {
		return this.repDateFrom;
	}
	public void setRepDateFrom(java.util.Date repDateFrom) {
		this.repDateFrom = repDateFrom;
	}

	public Integer getRepTypeId() {
		return this.repTypeId;
	}
	public void setRepTypeId(Integer repTypeId) {
		this.repTypeId = repTypeId;
	}

	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public java.util.Date getRepDateTo() {
		return this.repDateTo;
	}
	public void setRepDateTo(java.util.Date repDateTo) {
		this.repDateTo = repDateTo;
	}

	public Integer getRepId() {
		return this.repId;
	}
	public void setRepId(Integer repId) {
		this.repId = repId;
	}

	public String getRegFile() {
		return this.regFile;
	}
	public void setRegFile(String regFile) {
		this.regFile = regFile;
	}

	public String getRepTitle() {
		return this.repTitle;
	}
	public void setRepTitle(String repTitle) {
		this.repTitle = repTitle;
	}

	public String getRepFile() {
		return this.repFile;
	}
	public void setRepFile(String repFile) {
		this.repFile = repFile;
	}

	public String getRepFlags() {
		return this.repFlags;
	}
	public void setRepFlags(String repFlags) {
		this.repFlags = repFlags;
	}

}