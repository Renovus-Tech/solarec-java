package tech.renovus.solarec.db.data.vo.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;

public class BaseDocStationVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_DOC_ID = "doc_id";
	 public static final String COLUMN_STA_ID = "sta_id";

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer docId;
	 private Integer staId;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.docId == null) {
			return false;
		}
		if (this.staId == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDocStationVo)) return false;
		
		BaseDocStationVo aObj = (BaseDocStationVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.docId,aObj.docId)) {
			return false;
		}
		if (!ClassUtil.equals(this.staId,aObj.staId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.docId != null) hashCode += this.docId.hashCode();
		if (this.staId != null) hashCode += this.staId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDocStationVo)) return false;
		
		BaseDocStationVo aObj = (BaseDocStationVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.docId,aObj.docId)) {
			return false;
		}
		if (!ClassUtil.equals(this.staId,aObj.staId)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer docId, Integer staId) {
		this.cliId = cliId;
		this.docId = docId;
		this.staId = staId;
	}

	public void setPk(BaseDocStationVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getDocId(), aVo.getStaId());
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

	public Integer getStaId() {
		return this.staId;
	}
	public void setStaId(Integer staId) {
		this.staId = staId;
	}

}