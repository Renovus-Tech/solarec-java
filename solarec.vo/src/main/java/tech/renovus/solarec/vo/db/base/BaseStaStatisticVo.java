package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseStaStatisticVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_STA_ID = "sta_id";
	public static final String COLUMN_STAT_DATE = "stat_date";
	public static final String COLUMN_STAT_TYPE_ID = "stat_type_id";
	public static final String COLUMN_STAT_PRO_ID = "stat_pro_id";
	public static final String COLUMN_STAT_VALUE = "stat_value";
	public static final String COLUMN_STAT_DATE_ADDED = "stat_date_added";


	//--- Private properties --------------------
	private Integer cliId;
	private Integer staId;
	private java.util.Date statDate;
	private Integer statTypeId;
	private Integer statProId;
	private Double statValue;
	private java.util.Date statDateAdded;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.staId == null) {
			return false;
		}
		if (this.statDate == null) {
			return false;
		}
		if (this.statTypeId == null) {
			return false;
		}
		if (this.statProId == null) {
			return false;
		}
		if (this.statValue == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseStaStatisticVo)) return false;
		
		BaseStaStatisticVo aObj = (BaseStaStatisticVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.staId,aObj.staId)) {
			return false;
		}
		if (!ClassUtil.equals(this.statDate.getTime(),aObj.statDate.getTime())) {
			return false;
		}
		if (!ClassUtil.equals(this.statTypeId,aObj.statTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.staId != null) hashCode += this.staId.hashCode();
		if (this.statDate != null) hashCode += this.statDate.hashCode();
		if (this.statTypeId != null) hashCode += this.statTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseStaStatisticVo aObj = (BaseStaStatisticVo) obj;
		if (!ClassUtil.equals(this.statProId,aObj.statProId)) {
			return false;
		}
		if (!ClassUtil.equals(this.statValue,aObj.statValue)) {
			return false;
		}
		if (!ClassUtil.equals(this.statDateAdded,aObj.statDateAdded)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer staId, java.util.Date statDate, Integer statTypeId) {
		this.cliId = cliId;
		this.staId = staId;
		this.statDate = statDate;
		this.statTypeId = statTypeId;
	}

	public void setPk(BaseStaStatisticVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.staId, aVo.statDate, aVo.statTypeId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getStaId() {
		return this.staId;
	}
	public void setStaId(Integer staId) {
		this.staId = staId;
	}

	public java.util.Date getStatDate() {
		return this.statDate;
	}
	public void setStatDate(java.util.Date statDate) {
		this.statDate = statDate;
	}

	public Integer getStatTypeId() {
		return this.statTypeId;
	}
	public void setStatTypeId(Integer statTypeId) {
		this.statTypeId = statTypeId;
	}

	public Integer getStatProId() {
		return this.statProId;
	}
	public void setStatProId(Integer statProId) {
		this.statProId = statProId;
	}

	public Double getStatValue() {
		return this.statValue;
	}
	public void setStatValue(Double statValue) {
		this.statValue = statValue;
	}

	public java.util.Date getStatDateAdded() {
		return this.statDateAdded;
	}
	public void setStatDateAdded(java.util.Date statDateAdded) {
		this.statDateAdded = statDateAdded;
	}

}