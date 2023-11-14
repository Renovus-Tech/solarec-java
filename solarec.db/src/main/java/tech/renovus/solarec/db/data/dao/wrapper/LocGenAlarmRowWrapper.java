package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.LocGenAlarmVo;

public class LocGenAlarmRowWrapper implements RowMapper<LocGenAlarmVo> {

	//--- Constructors --------------------------
	private LocGenAlarmRowWrapper() {}
	private static LocGenAlarmRowWrapper instance = new LocGenAlarmRowWrapper();
	public static LocGenAlarmRowWrapper getInstance() { return LocGenAlarmRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetAlarmCode(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected String getResultSetAlarmDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetDataCatId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public LocGenAlarmVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocGenAlarmVo vo = new LocGenAlarmVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocGenAlarmVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocGenAlarmVo.COLUMN_LOC_ID));
		vo.setAlarmCode(this.getResultSetAlarmCode(resultSet, LocGenAlarmVo.COLUMN_ALARM_CODE));
		vo.setAlarmDescription(this.getResultSetAlarmDescription(resultSet, LocGenAlarmVo.COLUMN_ALARM_DESCRIPTION));
		vo.setDataCatId(this.getResultSetDataCatId(resultSet, LocGenAlarmVo.COLUMN_DATA_CAT_ID));

		return vo;
	}
}

