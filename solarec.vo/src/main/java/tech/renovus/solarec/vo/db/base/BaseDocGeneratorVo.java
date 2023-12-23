package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseDocGeneratorVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_DOC_ID = "doc_id";
	public static final String COLUMN_GEN_ID = "gen_id";


	//--- Private properties --------------------
	private Integer cliId;
	private Integer docId;
	private Integer genId;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.docId == null) {
			return false;
		}
		if (this.genId == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDocGeneratorVo)) return false;
		
		BaseDocGeneratorVo aObj = (BaseDocGeneratorVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.docId,aObj.docId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.docId != null) hashCode += this.docId.hashCode();
		if (this.genId != null) hashCode += this.genId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseDocGeneratorVo aObj = (BaseDocGeneratorVo) obj;
		return true;
	}

	public void setPk(Integer cliId, Integer docId, Integer genId) {
		this.cliId = cliId;
		this.docId = docId;
		this.genId = genId;
	}

	public void setPk(BaseDocGeneratorVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.docId, aVo.genId);
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

	public Integer getGenId() {
		return this.genId;
	}
	public void setGenId(Integer genId) {
		this.genId = genId;
	}

}