package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.DocumentVo;

public class DocumentRowWrapper implements RowMapper<DocumentVo> {

	//--- Constructors --------------------------
	private DocumentRowWrapper() {}
	private static DocumentRowWrapper instance = new DocumentRowWrapper();
	public static DocumentRowWrapper getInstance() { return DocumentRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetDocName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetDocTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDocDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetDocDateFrom(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetDocDateTo(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetDocFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDocObservations(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDocFile(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDocFileName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Double getResultSetDocFileSize(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected String getResultSetDocFileContent(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public DocumentVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocumentVo vo = new DocumentVo();

		vo.setCliId(this.getResultSetCliId(resultSet, DocumentVo.COLUMN_CLI_ID));
		vo.setDocId(this.getResultSetDocId(resultSet, DocumentVo.COLUMN_DOC_ID));
		vo.setDocName(this.getResultSetDocName(resultSet, DocumentVo.COLUMN_DOC_NAME));
		vo.setDocTypeId(this.getResultSetDocTypeId(resultSet, DocumentVo.COLUMN_DOC_TYPE_ID));
		vo.setDocDateAdded(this.getResultSetDocDateAdded(resultSet, DocumentVo.COLUMN_DOC_DATE_ADDED));
		vo.setDocDateFrom(this.getResultSetDocDateFrom(resultSet, DocumentVo.COLUMN_DOC_DATE_FROM));
		vo.setDocDateTo(this.getResultSetDocDateTo(resultSet, DocumentVo.COLUMN_DOC_DATE_TO));
		vo.setDocFlags(this.getResultSetDocFlags(resultSet, DocumentVo.COLUMN_DOC_FLAGS));
		vo.setDocObservations(this.getResultSetDocObservations(resultSet, DocumentVo.COLUMN_DOC_OBSERVATIONS));
		vo.setDocFile(this.getResultSetDocFile(resultSet, DocumentVo.COLUMN_DOC_FILE));
		vo.setDocFileName(this.getResultSetDocFileName(resultSet, DocumentVo.COLUMN_DOC_FILE_NAME));
		vo.setDocFileSize(this.getResultSetDocFileSize(resultSet, DocumentVo.COLUMN_DOC_FILE_SIZE));
		vo.setDocFileContent(this.getResultSetDocFileContent(resultSet, DocumentVo.COLUMN_DOC_FILE_CONTENT));

		return vo;
	}
}

