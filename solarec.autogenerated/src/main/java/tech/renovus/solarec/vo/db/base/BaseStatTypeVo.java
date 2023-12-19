package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseStatTypeVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_STAT_TYPE_ID = "stat_type_id";
	public static final String COLUMN_STAT_TYPE_NAME = "stat_type_name";
	public static final String COLUMN_STAT_TYPE_UNIT = "stat_type_unit";
	public static final String COLUMN_STAT_TYPE_DESCRIPTION = "stat_type_description";

	public static final int LENGTH_COLUMN_STAT_TYPE_NAME =  100;
	public static final int LENGTH_COLUMN_STAT_TYPE_UNIT =  100;
	public static final int LENGTH_COLUMN_STAT_TYPE_DESCRIPTION =  200;

	//--- Private properties --------------------
	private @Getter @Setter Integer statTypeId;
	private @Getter @Setter String statTypeName;
	private @Getter @Setter String statTypeUnit;
	private @Getter @Setter String statTypeDescription;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.statTypeId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStatTypeVo)) return false;
		
		BaseStatTypeVo aObj = (BaseStatTypeVo) obj;
		if (!ClassUtil.equals(this.statTypeId,aObj.statTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.statTypeId != null) hashCode += this.statTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStatTypeVo aObj = (BaseStatTypeVo) obj;
		if (!ClassUtil.equals(this.statTypeName,aObj.statTypeName)) {
			return false;
		}
		if (!ClassUtil.equals(this.statTypeUnit,aObj.statTypeUnit)) {
			return false;
		}
		if (!ClassUtil.equals(this.statTypeDescription,aObj.statTypeDescription)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer statTypeId) {
		this.statTypeId = statTypeId;
	}

	public void setPk(BaseStatTypeVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.statTypeId);
		}
	}

}