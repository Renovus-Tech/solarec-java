package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.GenAlarmVo;

public class GenAlarmRowWrapper implements RowMapper<GenAlarmVo> {

	//--- Constructors --------------------------
	private GenAlarmRowWrapper() {}
	private static GenAlarmRowWrapper instance = new GenAlarmRowWrapper();
	public static GenAlarmRowWrapper getInstance() { return GenAlarmRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetAlarmCode(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected String getResultSetAlarmDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetDataCatId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public GenAlarmVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		GenAlarmVo vo = new GenAlarmVo();

		vo.setCliId(this.getResultSetCliId(resultSet, GenAlarmVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, GenAlarmVo.COLUMN_GEN_ID));
		vo.setAlarmCode(this.getResultSetAlarmCode(resultSet, GenAlarmVo.COLUMN_ALARM_CODE));
		vo.setAlarmDescription(this.getResultSetAlarmDescription(resultSet, GenAlarmVo.COLUMN_ALARM_DESCRIPTION));
		vo.setDataCatId(this.getResultSetDataCatId(resultSet, GenAlarmVo.COLUMN_DATA_CAT_ID));

		return vo;
	}
}

