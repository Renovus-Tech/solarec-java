package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.GenStatisticVo;

@javax.annotation.Generated(value = "Renovus") public class GenStatisticRowWrapper implements RowMapper<GenStatisticVo> {

	//--- Constructors --------------------------
	private GenStatisticRowWrapper() {}
	private static GenStatisticRowWrapper instance = new GenStatisticRowWrapper();
	public static GenStatisticRowWrapper getInstance() { return GenStatisticRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetStatDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetStatTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStatProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetStatValue(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected java.util.Date getResultSetStatDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public GenStatisticVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		GenStatisticVo vo = new GenStatisticVo();

		vo.setCliId(this.getResultSetCliId(resultSet, GenStatisticVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, GenStatisticVo.COLUMN_GEN_ID));
		vo.setStatDate(this.getResultSetStatDate(resultSet, GenStatisticVo.COLUMN_STAT_DATE));
		vo.setStatTypeId(this.getResultSetStatTypeId(resultSet, GenStatisticVo.COLUMN_STAT_TYPE_ID));
		vo.setStatProId(this.getResultSetStatProId(resultSet, GenStatisticVo.COLUMN_STAT_PRO_ID));
		vo.setStatValue(this.getResultSetStatValue(resultSet, GenStatisticVo.COLUMN_STAT_VALUE));
		vo.setStatDateAdded(this.getResultSetStatDateAdded(resultSet, GenStatisticVo.COLUMN_STAT_DATE_ADDED));

		return vo;
	}
}

