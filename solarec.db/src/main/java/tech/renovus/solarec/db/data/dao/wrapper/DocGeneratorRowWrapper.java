package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.DocGeneratorVo;

public class DocGeneratorRowWrapper implements RowMapper<DocGeneratorVo> {

	//--- Constructors --------------------------
	private DocGeneratorRowWrapper() {}
	private static DocGeneratorRowWrapper instance = new DocGeneratorRowWrapper();
	public static DocGeneratorRowWrapper getInstance() { return DocGeneratorRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public DocGeneratorVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocGeneratorVo vo = new DocGeneratorVo();

		vo.setCliId(this.getResultSetCliId(resultSet, DocGeneratorVo.COLUMN_CLI_ID));
		vo.setDocId(this.getResultSetDocId(resultSet, DocGeneratorVo.COLUMN_DOC_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, DocGeneratorVo.COLUMN_GEN_ID));

		return vo;
	}
}

