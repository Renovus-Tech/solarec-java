package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseDataProcessingVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_DATA_PRO_RESULT = "data_pro_result";
	public static final String COLUMN_DATA_PRO_ID = "data_pro_id_auto";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_TRI_ID = "tri_id";
	public static final String COLUMN_DATA_DEF_ID = "data_def_id";
	public static final String COLUMN_DATA_PRO_DATE_START = "data_pro_date_start";
	public static final String COLUMN_DATA_PRO_DATE_END = "data_pro_date_end";
	public static final String COLUMN_DATA_PRO_FILE_LOG = "data_pro_file_log";
	public static final String COLUMN_DATA_PRO_FILE_NAME = "data_pro_file_name";

	public static final int LENGTH_COLUMN_DATA_PRO_FILE_LOG =  100;
	public static final int LENGTH_COLUMN_DATA_PRO_FILE_NAME =  100;

	//--- Private properties --------------------
	private @Getter @Setter Integer dataProResult;
	private @Getter @Setter Integer dataProId;
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer locId;
	private @Getter @Setter Integer triId;
	private @Getter @Setter Integer dataDefId;
	private @Getter @Setter java.util.Date dataProDateStart;
	private @Getter @Setter java.util.Date dataProDateEnd;
	private @Getter @Setter String dataProFileLog;
	private @Getter @Setter String dataProFileName;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.dataProId == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		if (this.dataDefId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataProcessingVo)) return false;
		
		BaseDataProcessingVo aObj = (BaseDataProcessingVo) obj;
		if (!ClassUtil.equals(this.dataProId,aObj.dataProId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.dataProId != null) hashCode += this.dataProId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseDataProcessingVo aObj = (BaseDataProcessingVo) obj;
		if (!ClassUtil.equals(this.dataProResult,aObj.dataProResult)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.triId,aObj.triId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataDefId,aObj.dataDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataProDateStart,aObj.dataProDateStart)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataProDateEnd,aObj.dataProDateEnd)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataProFileLog,aObj.dataProFileLog)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataProFileName,aObj.dataProFileName)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer dataProId) {
		this.dataProId = dataProId;
	}

	public void setPk(BaseDataProcessingVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.dataProId);
		}
	}

}