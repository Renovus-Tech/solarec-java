package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseStatProcessingVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_STAT_PRO_TYPE = "stat_pro_type";
	public static final String COLUMN_STAT_DEF_ID = "stat_def_id";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_STAT_PRO_DATE_START = "stat_pro_date_start";
	public static final String COLUMN_STAT_PRO_DATE_END = "stat_pro_date_end";
	public static final String COLUMN_STAT_PRO_RESULT = "stat_pro_result";
	public static final String COLUMN_STAT_PRO_ID = "stat_pro_id_auto";
	public static final String COLUMN_STAT_PRO_FILE_LOG = "stat_pro_file_log";

	public static final int LENGTH_COLUMN_STAT_PRO_FILE_LOG =  100;

	//--- Private properties --------------------
	private @Getter @Setter Integer statProType;
	private @Getter @Setter Integer statDefId;
	private @Getter @Setter Integer cliId;
	private @Getter @Setter java.util.Date statProDateStart;
	private @Getter @Setter java.util.Date statProDateEnd;
	private @Getter @Setter Integer statProResult;
	private @Getter @Setter Integer statProId;
	private @Getter @Setter String statProFileLog;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.statDefId == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		if (this.statProId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStatProcessingVo)) return false;
		
		BaseStatProcessingVo aObj = (BaseStatProcessingVo) obj;
		if (!ClassUtil.equals(this.statProId,aObj.statProId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.statProId != null) hashCode += this.statProId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStatProcessingVo aObj = (BaseStatProcessingVo) obj;
		if (!ClassUtil.equals(this.statProType,aObj.statProType)) {
			return false;
		}
		if (!ClassUtil.equals(this.statDefId,aObj.statDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.statProDateStart,aObj.statProDateStart)) {
			return false;
		}
		if (!ClassUtil.equals(this.statProDateEnd,aObj.statProDateEnd)) {
			return false;
		}
		if (!ClassUtil.equals(this.statProResult,aObj.statProResult)) {
			return false;
		}
		if (!ClassUtil.equals(this.statProFileLog,aObj.statProFileLog)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer statProId) {
		this.statProId = statProId;
	}

	public void setPk(BaseStatProcessingVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.statProId);
		}
	}

}