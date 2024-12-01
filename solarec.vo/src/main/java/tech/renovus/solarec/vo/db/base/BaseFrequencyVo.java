package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseFrequencyVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_FRQ_ID = "frq_id_auto";
	public static final String COLUMN_FRQ_AMOUNT = "frq_amount";
	public static final String COLUMN_FRQ_NAME = "frq_name";
	public static final String COLUMN_FRQ_UNIT = "frq_unit";
	public static final String COLUMN_FRQ_FLAGS = "frq_flags";

	public static final int LENGTH_COLUMN_FRQ_NAME =  200;
	public static final int LENGTH_COLUMN_FRQ_UNIT =  5;
	public static final int LENGTH_COLUMN_FRQ_FLAGS =  20;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.frqFlags; }
	@Override public void setFlags(String frqFlags) { this.frqFlags = frqFlags; }

	//--- Private properties --------------------
	private Integer frqId;
	private Integer frqAmount;
	private String frqName;
	private String frqUnit;
	private String frqFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.frqId == null) {
			return false;
		}
		if (this.frqAmount == null) {
			return false;
		}
		if (this.frqName == null) {
			return false;
		}
		if (this.frqUnit == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseFrequencyVo)) return false;
		
		BaseFrequencyVo aObj = (BaseFrequencyVo) obj;
		return ClassUtil.equals(this.frqId,aObj.frqId);
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.frqId != null) hashCode += this.frqId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseFrequencyVo aObj = (BaseFrequencyVo) obj;
		if (!ClassUtil.equals(this.frqAmount,aObj.frqAmount)) {
			return false;
		}
		if (!ClassUtil.equals(this.frqName,aObj.frqName)) {
			return false;
		}
		if (!ClassUtil.equals(this.frqUnit,aObj.frqUnit)) {
			return false;
		}
		if (!ClassUtil.equals(this.frqFlags,aObj.frqFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer frqId) {
		this.frqId = frqId;
	}

	public void setPk(BaseFrequencyVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk(aVo.frqId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getFrqId() {
		return this.frqId;
	}
	public void setFrqId(Integer frqId) {
		this.frqId = frqId;
	}

	public Integer getFrqAmount() {
		return this.frqAmount;
	}
	public void setFrqAmount(Integer frqAmount) {
		this.frqAmount = frqAmount;
	}

	public String getFrqName() {
		return this.frqName;
	}
	public void setFrqName(String frqName) {
		this.frqName = frqName;
	}

	public String getFrqUnit() {
		return this.frqUnit;
	}
	public void setFrqUnit(String frqUnit) {
		this.frqUnit = frqUnit;
	}

	public String getFrqFlags() {
		return this.frqFlags;
	}
	public void setFrqFlags(String frqFlags) {
		this.frqFlags = frqFlags;
	}

}