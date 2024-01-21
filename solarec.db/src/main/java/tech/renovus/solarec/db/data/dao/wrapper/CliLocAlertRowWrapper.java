package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliLocAlertVo;

public class CliLocAlertRowWrapper implements RowMapper<CliLocAlertVo> {

	//--- Constructors --------------------------
	protected CliLocAlertRowWrapper() {}
	private static CliLocAlertRowWrapper instance = new CliLocAlertRowWrapper();
	public static CliLocAlertRowWrapper getInstance() { return CliLocAlertRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliLocAlertId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetCliLocAlertAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetCliLocAlertType(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetCliLocAlertData(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCliLocAlertFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CliLocAlertVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliLocAlertVo vo = new CliLocAlertVo();

		vo.setCliId(this.getResultSetCliId(resultSet, CliLocAlertVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, CliLocAlertVo.COLUMN_LOC_ID));
		vo.setCliLocAlertId(this.getResultSetCliLocAlertId(resultSet, CliLocAlertVo.COLUMN_CLI_LOC_ALERT_ID));
		vo.setCliLocAlertAdded(this.getResultSetCliLocAlertAdded(resultSet, CliLocAlertVo.COLUMN_CLI_LOC_ALERT_ADDED));
		vo.setCliLocAlertType(this.getResultSetCliLocAlertType(resultSet, CliLocAlertVo.COLUMN_CLI_LOC_ALERT_TYPE));
		vo.setCliLocAlertData(this.getResultSetCliLocAlertData(resultSet, CliLocAlertVo.COLUMN_CLI_LOC_ALERT_DATA));
		vo.setCliLocAlertFlags(this.getResultSetCliLocAlertFlags(resultSet, CliLocAlertVo.COLUMN_CLI_LOC_ALERT_FLAGS));

		return vo;
	}
}