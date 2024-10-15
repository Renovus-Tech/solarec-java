package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.DocLocationVo;

@javax.annotation.Generated(value = "Renovus") public class DocLocationRowWrapper implements RowMapper<DocLocationVo> {

	//--- Constructors --------------------------
	private DocLocationRowWrapper() {}
	private static DocLocationRowWrapper instance = new DocLocationRowWrapper();
	public static DocLocationRowWrapper getInstance() { return DocLocationRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public DocLocationVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocLocationVo vo = new DocLocationVo();

		vo.setCliId(this.getResultSetCliId(resultSet, DocLocationVo.COLUMN_CLI_ID));
		vo.setDocId(this.getResultSetDocId(resultSet, DocLocationVo.COLUMN_DOC_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, DocLocationVo.COLUMN_LOC_ID));

		return vo;
	}
}

