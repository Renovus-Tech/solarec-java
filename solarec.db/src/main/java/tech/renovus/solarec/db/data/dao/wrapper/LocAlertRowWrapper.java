package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocAlertVo;

public class LocAlertRowWrapper implements RowMapper<LocAlertVo> {

	//--- Constructors --------------------------
	private LocAlertRowWrapper() {}
	private static LocAlertRowWrapper instance = new LocAlertRowWrapper();
	public static LocAlertRowWrapper getInstance() { return LocAlertRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetAlertDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetAlertDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetAlertDateSend(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetAlertMessage(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetAlertProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public LocAlertVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocAlertVo vo = new LocAlertVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocAlertVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocAlertVo.COLUMN_LOC_ID));
		vo.setAlertDefId(this.getResultSetAlertDefId(resultSet, LocAlertVo.COLUMN_ALERT_DEF_ID));
		vo.setAlertDateAdded(this.getResultSetAlertDateAdded(resultSet, LocAlertVo.COLUMN_ALERT_DATE_ADDED));
		vo.setAlertDateSend(this.getResultSetAlertDateSend(resultSet, LocAlertVo.COLUMN_ALERT_DATE_SEND));
		vo.setAlertMessage(this.getResultSetAlertMessage(resultSet, LocAlertVo.COLUMN_ALERT_MESSAGE));
		vo.setAlertProId(this.getResultSetAlertProId(resultSet, LocAlertVo.COLUMN_ALERT_PRO_ID));

		return vo;
	}
}

