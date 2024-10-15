package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocWeatherDataVo;

@javax.annotation.Generated(value = "Renovus") public class LocWeatherDataRowWrapper implements RowMapper<LocWeatherDataVo> {

	//--- Constructors --------------------------
	private LocWeatherDataRowWrapper() {}
	private static LocWeatherDataRowWrapper instance = new LocWeatherDataRowWrapper();
	public static LocWeatherDataRowWrapper getInstance() { return LocWeatherDataRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocWeaDataId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetLocWeaDataDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetLocWeaDataResonseStatus(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetLocWeaDataResponse(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public LocWeatherDataVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocWeatherDataVo vo = new LocWeatherDataVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocWeatherDataVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocWeatherDataVo.COLUMN_LOC_ID));
		vo.setLocWeaDataId(this.getResultSetLocWeaDataId(resultSet, LocWeatherDataVo.COLUMN_LOC_WEA_DATA_ID));
		vo.setLocWeaDataDate(this.getResultSetLocWeaDataDate(resultSet, LocWeatherDataVo.COLUMN_LOC_WEA_DATA_DATE));
		vo.setLocWeaDataResonseStatus(this.getResultSetLocWeaDataResonseStatus(resultSet, LocWeatherDataVo.COLUMN_LOC_WEA_DATA_RESONSE_STATUS));
		vo.setLocWeaDataResponse(this.getResultSetLocWeaDataResponse(resultSet, LocWeatherDataVo.COLUMN_LOC_WEA_DATA_RESPONSE));

		return vo;
	}
}

