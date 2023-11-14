package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.AlertProcessingVo;

public class AlertProcessingRowWrapper implements RowMapper<AlertProcessingVo> {

	//--- Constructors --------------------------
	private AlertProcessingRowWrapper() {}
	private static AlertProcessingRowWrapper instance = new AlertProcessingRowWrapper();
	public static AlertProcessingRowWrapper getInstance() { return AlertProcessingRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetAlertProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetAlertDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetAlertProFileName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected java.util.Date getResultSetAlertProDateStart(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetAlertProDateEnd(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetAlertProResult(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetAlertProFileLog(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public AlertProcessingVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		AlertProcessingVo vo = new AlertProcessingVo();

		vo.setAlertProId(this.getResultSetAlertProId(resultSet, AlertProcessingVo.COLUMN_ALERT_PRO_ID));
		vo.setAlertDefId(this.getResultSetAlertDefId(resultSet, AlertProcessingVo.COLUMN_ALERT_DEF_ID));
		vo.setCliId(this.getResultSetCliId(resultSet, AlertProcessingVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, AlertProcessingVo.COLUMN_LOC_ID));
		vo.setAlertProFileName(this.getResultSetAlertProFileName(resultSet, AlertProcessingVo.COLUMN_ALERT_PRO_FILE_NAME));
		vo.setAlertProDateStart(this.getResultSetAlertProDateStart(resultSet, AlertProcessingVo.COLUMN_ALERT_PRO_DATE_START));
		vo.setAlertProDateEnd(this.getResultSetAlertProDateEnd(resultSet, AlertProcessingVo.COLUMN_ALERT_PRO_DATE_END));
		vo.setAlertProResult(this.getResultSetAlertProResult(resultSet, AlertProcessingVo.COLUMN_ALERT_PRO_RESULT));
		vo.setAlertProFileLog(this.getResultSetAlertProFileLog(resultSet, AlertProcessingVo.COLUMN_ALERT_PRO_FILE_LOG));

		return vo;
	}
}

