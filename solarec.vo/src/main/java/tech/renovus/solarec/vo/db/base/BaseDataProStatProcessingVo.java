package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseDataProStatProcessingVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_DATA_PRO_ID = "data_pro_id";
	 public static final String COLUMN_STAT_PRO_ID = "stat_pro_id";

	//--- Private properties --------------------
	 private Integer dataProId;
	 private Integer statProId;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.dataProId == null) {
			return false;
		}
		if (this.statProId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataProStatProcessingVo)) return false;
		
		BaseDataProStatProcessingVo aObj = (BaseDataProStatProcessingVo) obj;
		if (!ClassUtil.equals(this.dataProId,aObj.dataProId)) {
			return false;
		}
		if (!ClassUtil.equals(this.statProId,aObj.statProId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.dataProId != null) hashCode += this.dataProId.hashCode();
		if (this.statProId != null) hashCode += this.statProId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDataProStatProcessingVo)) return false;
		
		BaseDataProStatProcessingVo aObj = (BaseDataProStatProcessingVo) obj;
		if (!ClassUtil.equals(this.dataProId,aObj.dataProId)) {
			return false;
		}
		if (!ClassUtil.equals(this.statProId,aObj.statProId)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer dataProId, Integer statProId) {
		this.dataProId = dataProId;
		this.statProId = statProId;
	}

	public void setPk(BaseDataProStatProcessingVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.getDataProId(), aVo.getStatProId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getDataProId() {
		return this.dataProId;
	}
	public void setDataProId(Integer dataProId) {
		this.dataProId = dataProId;
	}

	public Integer getStatProId() {
		return this.statProId;
	}
	public void setStatProId(Integer statProId) {
		this.statProId = statProId;
	}

}