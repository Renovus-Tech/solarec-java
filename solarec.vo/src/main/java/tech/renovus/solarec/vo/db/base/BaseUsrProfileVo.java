package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseUsrProfileVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_USR_ID = "usr_id";
	public static final String COLUMN_PRF_ID = "prf_id";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_USR_PRF_DATE_ADDED = "usr_prf_date_added";


	//--- Private properties --------------------
	private @Getter @Setter Integer usrId;
	private @Getter @Setter Integer prfId;
	private @Getter @Setter Integer cliId;
	private @Getter @Setter java.util.Date usrPrfDateAdded;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.usrId == null) {
			return false;
		}
		if (this.prfId == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseUsrProfileVo)) return false;
		
		BaseUsrProfileVo aObj = (BaseUsrProfileVo) obj;
		if (!ClassUtil.equals(this.usrId,aObj.usrId)) {
			return false;
		}
		if (!ClassUtil.equals(this.prfId,aObj.prfId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.usrId != null) hashCode += this.usrId.hashCode();
		if (this.prfId != null) hashCode += this.prfId.hashCode();
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseUsrProfileVo aObj = (BaseUsrProfileVo) obj;
		if (!ClassUtil.equals(this.usrPrfDateAdded,aObj.usrPrfDateAdded)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer usrId, Integer prfId, Integer cliId) {
		this.usrId = usrId;
		this.prfId = prfId;
		this.cliId = cliId;
	}

	public void setPk(BaseUsrProfileVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.usrId, aVo.prfId, aVo.cliId);
		}
	}

}