package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliGenAlertVo;

@javax.annotation.Generated(value = "Renovus") public class CliGenAlertRowWrapper implements RowMapper<CliGenAlertVo> {

	//--- Constructors --------------------------
	protected CliGenAlertRowWrapper() {}
	private static CliGenAlertRowWrapper instance = new CliGenAlertRowWrapper();
	public static CliGenAlertRowWrapper getInstance() { return CliGenAlertRowWrapper.instance; }

	//--- Protected methods --------------------
	protected java.util.Date getResultSetCliGenAlertTrigger(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliGenAlertId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetCliGenAlertAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetCliGenAlertType(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetCliGenAlertFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCliGenAlertData(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CliGenAlertVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliGenAlertVo vo = new CliGenAlertVo();

		vo.setCliGenAlertTrigger(this.getResultSetCliGenAlertTrigger(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_TRIGGER));
		vo.setGenId(this.getResultSetGenId(resultSet, CliGenAlertVo.COLUMN_GEN_ID));
		vo.setCliGenAlertId(this.getResultSetCliGenAlertId(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_ID));
		vo.setCliGenAlertAdded(this.getResultSetCliGenAlertAdded(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_ADDED));
		vo.setCliGenAlertType(this.getResultSetCliGenAlertType(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_TYPE));
		vo.setCliId(this.getResultSetCliId(resultSet, CliGenAlertVo.COLUMN_CLI_ID));
		vo.setCliGenAlertFlags(this.getResultSetCliGenAlertFlags(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_FLAGS));
		vo.setCliGenAlertData(this.getResultSetCliGenAlertData(resultSet, CliGenAlertVo.COLUMN_CLI_GEN_ALERT_DATA));

		return vo;
	}
}