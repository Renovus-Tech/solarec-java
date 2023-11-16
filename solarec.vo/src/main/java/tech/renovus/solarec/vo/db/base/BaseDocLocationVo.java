package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseDocLocationVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_DOC_ID = "doc_id";
	 public static final String COLUMN_LOC_ID = "loc_id";

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer docId;
	 private Integer locId;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.docId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDocLocationVo)) return false;
		
		BaseDocLocationVo aObj = (BaseDocLocationVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.docId,aObj.docId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.docId != null) hashCode += this.docId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDocLocationVo)) return false;
		
		BaseDocLocationVo aObj = (BaseDocLocationVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.docId,aObj.docId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer docId, Integer locId) {
		this.cliId = cliId;
		this.docId = docId;
		this.locId = locId;
	}

	public void setPk(BaseDocLocationVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getDocId(), aVo.getLocId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getDocId() {
		return this.docId;
	}
	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public Integer getLocId() {
		return this.locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}

}