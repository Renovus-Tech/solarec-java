package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliGenAlertVo;

public class CliGenAlertRowWrapper implements RowMapper<CliGenAlertVo> {

	//--- Constructors --------------------------
	private CliGenAlertRowWrapper() {}
	private static CliGenAlertRowWrapper instance = new CliGenAlertRowWrapper();
	public static CliGenAlertRowWrapper getInstance() { return CliGenAlertRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliGenAlertId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetCliGenAlertAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetCliGenAlertType(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetCliGenAlertData(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCliGenAlertFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CliGenAlertVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliGenAlertVo vo = new CliGenAlertVo();

		vo.setCliId(this.getResultSetCliId(resultSet, CliGenAlertVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, CliGenAlertVo.COLUMN_GEN_ID));
		vo.setCliGenAlertId(this.getResultSetCliGenAlertId(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_ID));
		vo.setCliGenAlertAdded(this.getResultSetCliGenAlertAdded(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_ADDED));
		vo.setCliGenAlertType(this.getResultSetCliGenAlertType(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_TYPE));
		vo.setCliGenAlertData(this.getResultSetCliGenAlertData(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_DATA));
		vo.setCliGenAlertFlags(this.getResultSetCliGenAlertFlags(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_FLAGS));

		return vo;
	}
}