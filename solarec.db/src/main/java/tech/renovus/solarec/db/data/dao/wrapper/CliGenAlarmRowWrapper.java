package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliGenAlarmVo;

public class CliGenAlarmRowWrapper implements RowMapper<CliGenAlarmVo> {

	//--- Constructors --------------------------
	private CliGenAlarmRowWrapper() {}
	private static CliGenAlarmRowWrapper instance = new CliGenAlarmRowWrapper();
	public static CliGenAlarmRowWrapper getInstance() { return CliGenAlarmRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetAlarmCode(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected String getResultSetAlarmDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetDataCatId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public CliGenAlarmVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliGenAlarmVo vo = new CliGenAlarmVo();

		vo.setCliId(this.getResultSetCliId(resultSet, CliGenAlarmVo.COLUMN_CLI_ID));
		vo.setAlarmCode(this.getResultSetAlarmCode(resultSet, CliGenAlarmVo.COLUMN_ALARM_CODE));
		vo.setAlarmDescription(this.getResultSetAlarmDescription(resultSet, CliGenAlarmVo.COLUMN_ALARM_DESCRIPTION));
		vo.setDataCatId(this.getResultSetDataCatId(resultSet, CliGenAlarmVo.COLUMN_DATA_CAT_ID));

		return vo;
	}
}

