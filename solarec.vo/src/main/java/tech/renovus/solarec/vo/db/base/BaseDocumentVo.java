package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.IFlags;

@NoArgsConstructor
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
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer docId;
	private @Getter @Setter Integer docTypeId;
	private @Getter @Setter java.util.Date docDateAdded;
	private @Getter @Setter java.util.Date docDateFrom;
	private @Getter @Setter java.util.Date docDateTo;
	private @Getter @Setter Double docFileSize;
	private @Getter @Setter String docFlags;
	private @Getter @Setter String docName;
	private @Getter @Setter String docObservations;
	private @Getter @Setter String docFile;
	private @Getter @Setter String docFileName;
	private @Getter @Setter String docFileContent;

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

	//--- Overriden methods ---------------------
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

}