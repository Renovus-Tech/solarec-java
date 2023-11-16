package tech.renovus.solarec.vo.db.base;

import tech.renvous.solarec.util.db.BaseDbVo;
import tech.renvous.solarec.util.ClassUtil;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseDocTypeVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	 public static final String COLUMN_DOC_TYPE_ID = "doc_type_id_auto";
	 public static final String COLUMN_DOC_TYPE_NAME = "doc_type_name";
	 public static final String COLUMN_DOC_TYPE_TITLE = "doc_type_title";
	 public static final String COLUMN_DOC_TYPE_FLAGS = "doc_type_flags";

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.docTypeFlags; }
	@Override public void setFlags(String docTypeFlags) { this.docTypeFlags = docTypeFlags; }

	//--- Private properties --------------------
	 private Integer docTypeId;
	 private String docTypeName;
	 private String docTypeTitle;
	 private String docTypeFlags;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.docTypeId == null) {
			return false;
		}
		if (this.docTypeName == null) {
			return false;
		}
		if (this.docTypeTitle == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDocTypeVo)) return false;
		
		BaseDocTypeVo aObj = (BaseDocTypeVo) obj;
		if (!ClassUtil.equals(this.docTypeId,aObj.docTypeId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.docTypeId != null) hashCode += this.docTypeId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDocTypeVo)) return false;
		
		BaseDocTypeVo aObj = (BaseDocTypeVo) obj;
		if (!ClassUtil.equals(this.docTypeId,aObj.docTypeId)) {
			return false;
		}
		if (!ClassUtil.equals(this.docTypeName,aObj.docTypeName)) {
			return false;
		}
		if (!ClassUtil.equals(this.docTypeTitle,aObj.docTypeTitle)) {
			return false;
		}
		if (!ClassUtil.equals(this.docTypeFlags,aObj.docTypeFlags)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer docTypeId) {
		this.docTypeId = docTypeId;
	}

	public void setPk(BaseDocTypeVo aVo) {
		if(aVo == null) { 
			this.setPk((Integer)null);
		} else {
			this.setPk((Integer)aVo.getDocTypeId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getDocTypeId() {
		return this.docTypeId;
	}
	public void setDocTypeId(Integer docTypeId) {
		this.docTypeId = docTypeId;
	}

	public String getDocTypeName() {
		return this.docTypeName;
	}
	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

	public String getDocTypeTitle() {
		return this.docTypeTitle;
	}
	public void setDocTypeTitle(String docTypeTitle) {
		this.docTypeTitle = docTypeTitle;
	}

	public String getDocTypeFlags() {
		return this.docTypeFlags;
	}
	public void setDocTypeFlags(String docTypeFlags) {
		this.docTypeFlags = docTypeFlags;
	}

}