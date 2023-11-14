package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.GenAlertVo;

public class GenAlertRowWrapper implements RowMapper<GenAlertVo> {

	//--- Constructors --------------------------
	private GenAlertRowWrapper() {}
	private static GenAlertRowWrapper instance = new GenAlertRowWrapper();
	public static GenAlertRowWrapper getInstance() { return GenAlertRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetAlertDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetAlertDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetAlertDateSend(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetAlertMessage(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetAlertProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public GenAlertVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		GenAlertVo vo = new GenAlertVo();

		vo.setCliId(this.getResultSetCliId(resultSet, GenAlertVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, GenAlertVo.COLUMN_GEN_ID));
		vo.setAlertDefId(this.getResultSetAlertDefId(resultSet, GenAlertVo.COLUMN_ALERT_DEF_ID));
		vo.setAlertDateAdded(this.getResultSetAlertDateAdded(resultSet, GenAlertVo.COLUMN_ALERT_DATE_ADDED));
		vo.setAlertDateSend(this.getResultSetAlertDateSend(resultSet, GenAlertVo.COLUMN_ALERT_DATE_SEND));
		vo.setAlertMessage(this.getResultSetAlertMessage(resultSet, GenAlertVo.COLUMN_ALERT_MESSAGE));
		vo.setAlertProId(this.getResultSetAlertProId(resultSet, GenAlertVo.COLUMN_ALERT_PRO_ID));

		return vo;
	}
}

