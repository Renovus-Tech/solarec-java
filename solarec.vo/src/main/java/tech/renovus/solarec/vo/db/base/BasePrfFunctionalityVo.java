package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BasePrfFunctionalityVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_PRF_ID = "prf_id";
	public static final String COLUMN_FNC_ID = "fnc_id";


	//--- Private properties --------------------
	private @Getter @Setter Integer prfId;
	private @Getter @Setter Integer fncId;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.prfId == null) {
			return false;
		}
		if (this.fncId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BasePrfFunctionalityVo)) return false;
		
		BasePrfFunctionalityVo aObj = (BasePrfFunctionalityVo) obj;
		if (!ClassUtil.equals(this.prfId,aObj.prfId)) {
			return false;
		}
		if (!ClassUtil.equals(this.fncId,aObj.fncId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.prfId != null) hashCode += this.prfId.hashCode();
		if (this.fncId != null) hashCode += this.fncId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BasePrfFunctionalityVo aObj = (BasePrfFunctionalityVo) obj;
		return true;
	}

	public void setPk(Integer prfId, Integer fncId) {
		this.prfId = prfId;
		this.fncId = fncId;
	}

	public void setPk(BasePrfFunctionalityVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.prfId, aVo.fncId);
		}
	}

}