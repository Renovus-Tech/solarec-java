package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.StaAlertVo;

public class StaAlertRowWrapper implements RowMapper<StaAlertVo> {

	//--- Constructors --------------------------
	private StaAlertRowWrapper() {}
	private static StaAlertRowWrapper instance = new StaAlertRowWrapper();
	public static StaAlertRowWrapper getInstance() { return StaAlertRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStaId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetAlertDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetAlertDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetAlertDateSend(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetAlertMessage(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetAlertProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public StaAlertVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		StaAlertVo vo = new StaAlertVo();

		vo.setCliId(this.getResultSetCliId(resultSet, StaAlertVo.COLUMN_CLI_ID));
		vo.setStaId(this.getResultSetStaId(resultSet, StaAlertVo.COLUMN_STA_ID));
		vo.setAlertDefId(this.getResultSetAlertDefId(resultSet, StaAlertVo.COLUMN_ALERT_DEF_ID));
		vo.setAlertDateAdded(this.getResultSetAlertDateAdded(resultSet, StaAlertVo.COLUMN_ALERT_DATE_ADDED));
		vo.setAlertDateSend(this.getResultSetAlertDateSend(resultSet, StaAlertVo.COLUMN_ALERT_DATE_SEND));
		vo.setAlertMessage(this.getResultSetAlertMessage(resultSet, StaAlertVo.COLUMN_ALERT_MESSAGE));
		vo.setAlertProId(this.getResultSetAlertProId(resultSet, StaAlertVo.COLUMN_ALERT_PRO_ID));

		return vo;
	}
}

