package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliGenUsrAlertVo;

public class CliGenUsrAlertRowWrapper implements RowMapper<CliGenUsrAlertVo> {

	//--- Constructors --------------------------
	private CliGenUsrAlertRowWrapper() {}
	private static CliGenUsrAlertRowWrapper instance = new CliGenUsrAlertRowWrapper();
	public static CliGenUsrAlertRowWrapper getInstance() { return CliGenUsrAlertRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetUsrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliGenAlertId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetCliGenUsrAlertSendDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetCliGenUsrAlertSendResult(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CliGenUsrAlertVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliGenUsrAlertVo vo = new CliGenUsrAlertVo();

		vo.setCliId(this.getResultSetCliId(resultSet, CliGenUsrAlertVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, CliGenUsrAlertVo.COLUMN_GEN_ID));
		vo.setUsrId(this.getResultSetUsrId(resultSet, CliGenUsrAlertVo.COLUMN_USR_ID));
		vo.setCliGenAlertId(this.getResultSetCliGenAlertId(resultSet, CliGenUsrAlertVo.COLUMN_CLI_GEN_ALERT_ID));
		vo.setCliGenUsrAlertSendDate(this.getResultSetCliGenUsrAlertSendDate(resultSet, CliGenUsrAlertVo.COLUMN_CLI_GEN_USR_ALERT_SEND_DATE));
		vo.setCliGenUsrAlertSendResult(this.getResultSetCliGenUsrAlertSendResult(resultSet, CliGenUsrAlertVo.COLUMN_CLI_GEN_USR_ALERT_SEND_RESULT));

		return vo;
	}
}