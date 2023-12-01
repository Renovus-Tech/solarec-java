package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliLocUsrAlertVo;

public class CliLocUsrAlertRowWrapper implements RowMapper<CliLocUsrAlertVo> {

	//--- Constructors --------------------------
	private CliLocUsrAlertRowWrapper() {}
	private static CliLocUsrAlertRowWrapper instance = new CliLocUsrAlertRowWrapper();
	public static CliLocUsrAlertRowWrapper getInstance() { return CliLocUsrAlertRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetUsrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliLocAlertId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetCliLocUsrAlertSendDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetCliLocUsrAlertSendResult(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CliLocUsrAlertVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliLocUsrAlertVo vo = new CliLocUsrAlertVo();

		vo.setCliId(this.getResultSetCliId(resultSet, CliLocUsrAlertVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, CliLocUsrAlertVo.COLUMN_LOC_ID));
		vo.setUsrId(this.getResultSetUsrId(resultSet, CliLocUsrAlertVo.COLUMN_USR_ID));
		vo.setCliLocAlertId(this.getResultSetCliLocAlertId(resultSet, CliLocUsrAlertVo.COLUMN_CLI_LOC_ALERT_ID));
		vo.setCliLocUsrAlertSendDate(this.getResultSetCliLocUsrAlertSendDate(resultSet, CliLocUsrAlertVo.COLUMN_CLI_LOC_USR_ALERT_SEND_DATE));
		vo.setCliLocUsrAlertSendResult(this.getResultSetCliLocUsrAlertSendResult(resultSet, CliLocUsrAlertVo.COLUMN_CLI_LOC_USR_ALERT_SEND_RESULT));

		return vo;
	}
}