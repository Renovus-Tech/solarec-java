package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.StaStatisticVo;

public class StaStatisticRowWrapper implements RowMapper<StaStatisticVo> {

	//--- Constructors --------------------------
	private StaStatisticRowWrapper() {}
	private static StaStatisticRowWrapper instance = new StaStatisticRowWrapper();
	public static StaStatisticRowWrapper getInstance() { return StaStatisticRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStaId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetStatDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetStatTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStatProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetStatValue(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected java.util.Date getResultSetStatDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public StaStatisticVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		StaStatisticVo vo = new StaStatisticVo();

		vo.setCliId(this.getResultSetCliId(resultSet, StaStatisticVo.COLUMN_CLI_ID));
		vo.setStaId(this.getResultSetStaId(resultSet, StaStatisticVo.COLUMN_STA_ID));
		vo.setStatDate(this.getResultSetStatDate(resultSet, StaStatisticVo.COLUMN_STAT_DATE));
		vo.setStatTypeId(this.getResultSetStatTypeId(resultSet, StaStatisticVo.COLUMN_STAT_TYPE_ID));
		vo.setStatProId(this.getResultSetStatProId(resultSet, StaStatisticVo.COLUMN_STAT_PRO_ID));
		vo.setStatValue(this.getResultSetStatValue(resultSet, StaStatisticVo.COLUMN_STAT_VALUE));
		vo.setStatDateAdded(this.getResultSetStatDateAdded(resultSet, StaStatisticVo.COLUMN_STAT_DATE_ADDED));

		return vo;
	}
}

