package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseWeaCallVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_WEA_ID = "wea_id";
	public static final String COLUMN_WEA_CALL_ID = "wea_call_id_auto";
	public static final String COLUMN_WEA_CALL_DATE = "wea_call_date";
	public static final String COLUMN_WEA_CALL_RESONSE_STATUS = "wea_call_resonse_status";
	public static final String COLUMN_WEA_CALL_RESPONSE = "wea_call_response";


	//--- Private properties --------------------
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer weaId;
	private @Getter @Setter Integer weaCallId;
	private @Getter @Setter java.util.Date weaCallDate;
	private @Getter @Setter Integer weaCallResonseStatus;
	private @Getter @Setter String weaCallResponse;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.weaId == null) {
			return false;
		}
		if (this.weaCallId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseWeaCallVo)) return false;
		
		BaseWeaCallVo aObj = (BaseWeaCallVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaId,aObj.weaId)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaCallId,aObj.weaCallId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.weaId != null) hashCode += this.weaId.hashCode();
		if (this.weaCallId != null) hashCode += this.weaCallId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseWeaCallVo aObj = (BaseWeaCallVo) obj;
		if (!ClassUtil.equals(this.weaCallDate,aObj.weaCallDate)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaCallResonseStatus,aObj.weaCallResonseStatus)) {
			return false;
		}
		if (!ClassUtil.equals(this.weaCallResponse,aObj.weaCallResponse)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer weaId, Integer weaCallId) {
		this.cliId = cliId;
		this.weaId = weaId;
		this.weaCallId = weaCallId;
	}

	public void setPk(BaseWeaCallVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.weaId, aVo.weaCallId);
		}
	}

}