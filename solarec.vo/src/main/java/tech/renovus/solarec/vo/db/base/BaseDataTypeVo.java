package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseDataTypeVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_DATA_TYPE_ID = "data_type_id";
	public static final String COLUMN_DATA_CAT_ID = "data_cat_id";
	public static final String COLUMN_DATA_TYPE_NAME = "data_type_name";
	public static final String COLUMN_DATA_TYPE_UNITS = "data_type_units";
	public static final String COLUMN_DATA_TYPE_DESCRIPTION = "data_type_description";

	public static final int LENGTH_COLUMN_DATA_TYPE_NAME =  100;
	public static final int LENGTH_COLUMN_DATA_TYPE_UNITS =  100;
	public static final int LENGTH_COLUMN_DATA_TYPE_DESCRIPTION =  200;

	//--- Private properties --------------------
	private @Getter @Setter Integer dataTypeId;
	private @Getter @Setter Integer dataCatId;
	private @Getter @Setter String dataTypeName;
	private @Getter @Setter String dataTypeUnits;
	private @Getter @Setter String dataTypeDescription;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.dataTypeId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataTypeVo)) return false;
		
		BaseDataTypeVo aObj = (BaseDataTypeVo) obj;
		if (!ClassUtil.equals(this.dataTypeId,aObj.dataTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.dataTypeId != null) hashCode += this.dataTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseDataTypeVo aObj = (BaseDataTypeVo) obj;
		if (!ClassUtil.equals(this.dataCatId,aObj.dataCatId)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataTypeName,aObj.dataTypeName)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataTypeUnits,aObj.dataTypeUnits)) {
			return false;
		}
		if (!ClassUtil.equals(this.dataTypeDescription,aObj.dataTypeDescription)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public void setPk(BaseDataTypeVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.dataTypeId);
		}
	}

}