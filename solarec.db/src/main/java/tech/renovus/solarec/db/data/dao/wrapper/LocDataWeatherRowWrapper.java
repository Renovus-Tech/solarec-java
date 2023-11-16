package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocDataWeatherVo;

public class LocDataWeatherRowWrapper implements RowMapper<LocDataWeatherVo> {

	//--- Constructors --------------------------
	private LocDataWeatherRowWrapper() {}
	private static LocDataWeatherRowWrapper instance = new LocDataWeatherRowWrapper();
	public static LocDataWeatherRowWrapper getInstance() { return LocDataWeatherRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetDataDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetDataTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetDataValue(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public LocDataWeatherVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocDataWeatherVo vo = new LocDataWeatherVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocDataWeatherVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocDataWeatherVo.COLUMN_LOC_ID));
		vo.setDataDateAdded(this.getResultSetDataDateAdded(resultSet, LocDataWeatherVo.COLUMN_DATA_DATE_ADDED));
		vo.setDataDate(this.getResultSetDataDate(resultSet, LocDataWeatherVo.COLUMN_DATA_DATE));
		vo.setDataTypeId(this.getResultSetDataTypeId(resultSet, LocDataWeatherVo.COLUMN_DATA_TYPE_ID));
		vo.setDataValue(this.getResultSetDataValue(resultSet, LocDataWeatherVo.COLUMN_DATA_VALUE));

		return vo;
	}
}

