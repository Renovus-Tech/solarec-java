package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseStatDefSourceVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_STAT_DEF_ID = "stat_def_id";
	public static final String COLUMN_DATA_TYPE_ID = "data_type_id";


	//--- Private properties --------------------
	private @Getter @Setter Integer statDefId;
	private @Getter @Setter Integer dataTypeId;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.statDefId == null) {
			return false;
		}
		if (this.dataTypeId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStatDefSourceVo)) return false;
		
		BaseStatDefSourceVo aObj = (BaseStatDefSourceVo) obj;
		if (!ClassUtil.equals(this.statDefId,aObj.statDefId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataTypeId,aObj.dataTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.statDefId != null) hashCode += this.statDefId.hashCode();
		if (this.dataTypeId != null) hashCode += this.dataTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStatDefSourceVo aObj = (BaseStatDefSourceVo) obj;
		return true;
	}

	public void setPk(Integer statDefId, Integer dataTypeId) {
		this.statDefId = statDefId;
		this.dataTypeId = dataTypeId;
	}

	public void setPk(BaseStatDefSourceVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.statDefId, aVo.dataTypeId);
		}
	}

}