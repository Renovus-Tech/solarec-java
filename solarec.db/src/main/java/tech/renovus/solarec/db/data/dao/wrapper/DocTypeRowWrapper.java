package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.DocTypeVo;

public class DocTypeRowWrapper implements RowMapper<DocTypeVo> {

	//--- Constructors --------------------------
	private DocTypeRowWrapper() {}
	private static DocTypeRowWrapper instance = new DocTypeRowWrapper();
	public static DocTypeRowWrapper getInstance() { return DocTypeRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetDocTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetDocTypeName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDocTypeTitle(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDocTypeFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public DocTypeVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocTypeVo vo = new DocTypeVo();

		vo.setDocTypeId(this.getResultSetDocTypeId(resultSet, DocTypeVo.COLUMN_DOC_TYPE_ID));
		vo.setDocTypeName(this.getResultSetDocTypeName(resultSet, DocTypeVo.COLUMN_DOC_TYPE_NAME));
		vo.setDocTypeTitle(this.getResultSetDocTypeTitle(resultSet, DocTypeVo.COLUMN_DOC_TYPE_TITLE));
		vo.setDocTypeFlags(this.getResultSetDocTypeFlags(resultSet, DocTypeVo.COLUMN_DOC_TYPE_FLAGS));

		return vo;
	}
}

