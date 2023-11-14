package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.LocStatisticVo;

public class LocStatisticRowWrapper implements RowMapper<LocStatisticVo> {

	//--- Constructors --------------------------
	private LocStatisticRowWrapper() {}
	private static LocStatisticRowWrapper instance = new LocStatisticRowWrapper();
	public static LocStatisticRowWrapper getInstance() { return LocStatisticRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetStatDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetStatTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStatProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetStatValue(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected java.util.Date getResultSetStatDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public LocStatisticVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocStatisticVo vo = new LocStatisticVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocStatisticVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocStatisticVo.COLUMN_LOC_ID));
		vo.setStatDate(this.getResultSetStatDate(resultSet, LocStatisticVo.COLUMN_STAT_DATE));
		vo.setStatTypeId(this.getResultSetStatTypeId(resultSet, LocStatisticVo.COLUMN_STAT_TYPE_ID));
		vo.setStatProId(this.getResultSetStatProId(resultSet, LocStatisticVo.COLUMN_STAT_PRO_ID));
		vo.setStatValue(this.getResultSetStatValue(resultSet, LocStatisticVo.COLUMN_STAT_VALUE));
		vo.setStatDateAdded(this.getResultSetStatDateAdded(resultSet, LocStatisticVo.COLUMN_STAT_DATE_ADDED));

		return vo;
	}
}

