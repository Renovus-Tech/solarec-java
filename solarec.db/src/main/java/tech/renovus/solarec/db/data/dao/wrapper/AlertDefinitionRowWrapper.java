package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.AlertDefinitionVo;

public class AlertDefinitionRowWrapper implements RowMapper<AlertDefinitionVo> {

	//--- Constructors --------------------------
	private AlertDefinitionRowWrapper() {}
	private static AlertDefinitionRowWrapper instance = new AlertDefinitionRowWrapper();
	public static AlertDefinitionRowWrapper getInstance() { return AlertDefinitionRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetAlertDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetAlertDefName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetAlertDefDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetAlertDefExecutable(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetAlertDefFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public AlertDefinitionVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		AlertDefinitionVo vo = new AlertDefinitionVo();

		vo.setAlertDefId(this.getResultSetAlertDefId(resultSet, AlertDefinitionVo.COLUMN_ALERT_DEF_ID));
		vo.setAlertDefName(this.getResultSetAlertDefName(resultSet, AlertDefinitionVo.COLUMN_ALERT_DEF_NAME));
		vo.setAlertDefDescription(this.getResultSetAlertDefDescription(resultSet, AlertDefinitionVo.COLUMN_ALERT_DEF_DESCRIPTION));
		vo.setAlertDefExecutable(this.getResultSetAlertDefExecutable(resultSet, AlertDefinitionVo.COLUMN_ALERT_DEF_EXECUTABLE));
		vo.setAlertDefFlags(this.getResultSetAlertDefFlags(resultSet, AlertDefinitionVo.COLUMN_ALERT_DEF_FLAGS));

		return vo;
	}
}

