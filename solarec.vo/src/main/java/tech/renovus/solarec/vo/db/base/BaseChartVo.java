package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@javax.annotation.Generated(value = "Renovus") public class BaseChartVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_CHR_ID = "chr_id_auto";
	public static final String COLUMN_CHR_NAME = "chr_name";
	public static final String COLUMN_CHR_TITLE = "chr_title";
	public static final String COLUMN_CHR_DESCRIPTION = "chr_description";
	public static final String COLUMN_CHR_FLAGS = "chr_flags";
	public static final String COLUMN_CHR_URL = "chr_url";

	public static final int LENGTH_COLUMN_CHR_NAME =  100;
	public static final int LENGTH_COLUMN_CHR_TITLE =  200;
	public static final int LENGTH_COLUMN_CHR_DESCRIPTION =  500;
	public static final int LENGTH_COLUMN_CHR_FLAGS =  20;
	public static final int LENGTH_COLUMN_CHR_URL =  255;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.chrFlags; }
	@Override public void setFlags(String chrFlags) { this.chrFlags = chrFlags; }

	//--- Private properties --------------------
	private Integer chrId;
	private String chrName;
	private String chrTitle;
	private String chrDescription;
	private String chrFlags;
	private String chrUrl;

	//--- Public methods ------------------------
	public boolean validData() {
		return this.chrId != null;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseChartVo)) return false;
		
		BaseChartVo aObj = (BaseChartVo) obj;
		return ClassUtil.equals(this.chrId,aObj.chrId);
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.chrId != null) hashCode += this.chrId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseChartVo aObj = (BaseChartVo) obj;
		if (!ClassUtil.equals(this.chrName,aObj.chrName)) {
			return false;
		}
		if (!ClassUtil.equals(this.chrTitle,aObj.chrTitle)) {
			return false;
		}
		if (!ClassUtil.equals(this.chrDescription,aObj.chrDescription)) {
			return false;
		}
		if (!ClassUtil.equals(this.chrFlags,aObj.chrFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.chrUrl,aObj.chrUrl)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer chrId) {
		this.chrId = chrId;
	}

	public void setPk(BaseChartVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk(aVo.chrId);
		}
	}

	//--- Getters and Setters -------------------
	public Integer getChrId() {
		return this.chrId;
	}
	public void setChrId(Integer chrId) {
		this.chrId = chrId;
	}

	public String getChrName() {
		return this.chrName;
	}
	public void setChrName(String chrName) {
		this.chrName = chrName;
	}

	public String getChrTitle() {
		return this.chrTitle;
	}
	public void setChrTitle(String chrTitle) {
		this.chrTitle = chrTitle;
	}

	public String getChrDescription() {
		return this.chrDescription;
	}
	public void setChrDescription(String chrDescription) {
		this.chrDescription = chrDescription;
	}

	public String getChrFlags() {
		return this.chrFlags;
	}
	public void setChrFlags(String chrFlags) {
		this.chrFlags = chrFlags;
	}

	public String getChrUrl() {
		return this.chrUrl;
	}
	public void setChrUrl(String chrUrl) {
		this.chrUrl = chrUrl;
	}

}