package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

public class BaseDocumentVo extends BaseDbVo implements IFlags {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_DOC_ID = "doc_id_auto";
	public static final String COLUMN_DOC_TYPE_ID = "doc_type_id";
	public static final String COLUMN_DOC_DATE_ADDED = "doc_date_added";
	public static final String COLUMN_DOC_DATE_FROM = "doc_date_from";
	public static final String COLUMN_DOC_DATE_TO = "doc_date_to";
	public static final String COLUMN_DOC_FILE_SIZE = "doc_file_size";
	public static final String COLUMN_DOC_FLAGS = "doc_flags";
	public static final String COLUMN_DOC_NAME = "doc_name";
	public static final String COLUMN_DOC_OBSERVATIONS = "doc_observations";
	public static final String COLUMN_DOC_FILE = "doc_file";
	public static final String COLUMN_DOC_FILE_NAME = "doc_file_name";
	public static final String COLUMN_DOC_FILE_CONTENT = "doc_file_content";

	public static final int LENGTH_COLUMN_DOC_FLAGS =  20;
	public static final int LENGTH_COLUMN_DOC_NAME =  200;
	public static final int LENGTH_COLUMN_DOC_OBSERVATIONS =  500;
	public static final int LENGTH_COLUMN_DOC_FILE =  200;
	public static final int LENGTH_COLUMN_DOC_FILE_NAME =  200;

	//--- Implemented methods -------------------
	@Override public String getFlags() { return this.docFlags; }
	@Override public void setFlags(String docFlags) { this.docFlags = docFlags; }

	//--- Private properties --------------------
	private Integer cliId;
	private Integer docId;
	private Integer docTypeId;
	private java.util.Date docDateAdded;
	private java.util.Date docDateFrom;
	private java.util.Date docDateTo;
	private Double docFileSize;
	private String docFlags;
	private String docName;
	private String docObservations;
	private String docFile;
	private String docFileName;
	private String docFileContent;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.docId == null) {
			return false;
		}
		return true;
	}

	//--- Overridden methods --------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseDocumentVo)) return false;
		
		BaseDocumentVo aObj = (BaseDocumentVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.docId,aObj.docId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.docId != null) hashCode += this.docId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseDocumentVo aObj = (BaseDocumentVo) obj;
		if (!ClassUtil.equals(this.docTypeId,aObj.docTypeId)) {
			return false;
		}
		if (!ClassUtil.equals(this.docDateAdded,aObj.docDateAdded)) {
			return false;
		}
		if (!ClassUtil.equals(this.docDateFrom,aObj.docDateFrom)) {
			return false;
		}
		if (!ClassUtil.equals(this.docDateTo,aObj.docDateTo)) {
			return false;
		}
		if (!ClassUtil.equals(this.docFileSize,aObj.docFileSize)) {
			return false;
		}
		if (!ClassUtil.equals(this.docFlags,aObj.docFlags)) {
			return false;
		}
		if (!ClassUtil.equals(this.docName,aObj.docName)) {
			return false;
		}
		if (!ClassUtil.equals(this.docObservations,aObj.docObservations)) {
			return false;
		}
		if (!ClassUtil.equals(this.docFile,aObj.docFile)) {
			return false;
		}
		if (!ClassUtil.equals(this.docFileName,aObj.docFileName)) {
			return false;
		}
		if (!ClassUtil.equals(this.docFileContent,aObj.docFileContent)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer docId) {
		this.cliId = cliId;
		this.docId = docId;
	}

	public void setPk(BaseDocumentVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null);
		} else {
			this.setPk(aVo.cliId, aVo.docId);
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

	public Integer getDocTypeId() {
		return this.docTypeId;
	}
	public void setDocTypeId(Integer docTypeId) {
		this.docTypeId = docTypeId;
	}

	public java.util.Date getDocDateAdded() {
		return this.docDateAdded;
	}
	public void setDocDateAdded(java.util.Date docDateAdded) {
		this.docDateAdded = docDateAdded;
	}

	public java.util.Date getDocDateFrom() {
		return this.docDateFrom;
	}
	public void setDocDateFrom(java.util.Date docDateFrom) {
		this.docDateFrom = docDateFrom;
	}

	public java.util.Date getDocDateTo() {
		return this.docDateTo;
	}
	public void setDocDateTo(java.util.Date docDateTo) {
		this.docDateTo = docDateTo;
	}

	public Double getDocFileSize() {
		return this.docFileSize;
	}
	public void setDocFileSize(Double docFileSize) {
		this.docFileSize = docFileSize;
	}

	public String getDocFlags() {
		return this.docFlags;
	}
	public void setDocFlags(String docFlags) {
		this.docFlags = docFlags;
	}

	public String getDocName() {
		return this.docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocObservations() {
		return this.docObservations;
	}
	public void setDocObservations(String docObservations) {
		this.docObservations = docObservations;
	}

	public String getDocFile() {
		return this.docFile;
	}
	public void setDocFile(String docFile) {
		this.docFile = docFile;
	}

	public String getDocFileName() {
		return this.docFileName;
	}
	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public String getDocFileContent() {
		return this.docFileContent;
	}
	public void setDocFileContent(String docFileContent) {
		this.docFileContent = docFileContent;
	}

}