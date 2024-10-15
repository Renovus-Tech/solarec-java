package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.DocStationVo;

@javax.annotation.Generated(value = "Renovus") public class DocStationRowWrapper implements RowMapper<DocStationVo> {

	//--- Constructors --------------------------
	private DocStationRowWrapper() {}
	private static DocStationRowWrapper instance = new DocStationRowWrapper();
	public static DocStationRowWrapper getInstance() { return DocStationRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStaId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public DocStationVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DocStationVo vo = new DocStationVo();

		vo.setCliId(this.getResultSetCliId(resultSet, DocStationVo.COLUMN_CLI_ID));
		vo.setStaId(this.getResultSetStaId(resultSet, DocStationVo.COLUMN_STA_ID));
		vo.setDocId(this.getResultSetDocId(resultSet, DocStationVo.COLUMN_DOC_ID));

		return vo;
	}
}

