package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocWeaDataHourVo;

public class LocWeaDataHourRowWrapper implements RowMapper<LocWeaDataHourVo> {

	//--- Constructors --------------------------
	private LocWeaDataHourRowWrapper() {}
	private static LocWeaDataHourRowWrapper instance = new LocWeaDataHourRowWrapper();
	public static LocWeaDataHourRowWrapper getInstance() { return LocWeaDataHourRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocWeaDataId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetLocWeaDataHour(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetLocWeaDataResponse(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected java.util.Date getResultSetLocWeaDataRetrieve(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public LocWeaDataHourVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocWeaDataHourVo vo = new LocWeaDataHourVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocWeaDataHourVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocWeaDataHourVo.COLUMN_LOC_ID));
		vo.setLocWeaDataId(this.getResultSetLocWeaDataId(resultSet, LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_ID));
		vo.setLocWeaDataHour(this.getResultSetLocWeaDataHour(resultSet, LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_HOUR));
		vo.setLocWeaDataResponse(this.getResultSetLocWeaDataResponse(resultSet, LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_RESPONSE));
		vo.setLocWeaDataRetrieve(this.getResultSetLocWeaDataRetrieve(resultSet, LocWeaDataHourVo.COLUMN_LOC_WEA_DATA_RETRIEVE));

		return vo;
	}
}

