package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocDataVo;

@javax.annotation.Generated(value = "Renovus") public class LocDataRowWrapper implements RowMapper<LocDataVo> {

	//--- Constructors --------------------------
	private LocDataRowWrapper() {}
	private static LocDataRowWrapper instance = new LocDataRowWrapper();
	public static LocDataRowWrapper getInstance() { return LocDataRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetDataTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetDataValue(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public LocDataVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocDataVo vo = new LocDataVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocDataVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocDataVo.COLUMN_LOC_ID));
		vo.setDataDate(this.getResultSetDataDate(resultSet, LocDataVo.COLUMN_DATA_DATE));
		vo.setDataTypeId(this.getResultSetDataTypeId(resultSet, LocDataVo.COLUMN_DATA_TYPE_ID));
		vo.setDataProId(this.getResultSetDataProId(resultSet, LocDataVo.COLUMN_DATA_PRO_ID));
		vo.setDataValue(this.getResultSetDataValue(resultSet, LocDataVo.COLUMN_DATA_VALUE));
		vo.setDataDateAdded(this.getResultSetDataDateAdded(resultSet, LocDataVo.COLUMN_DATA_DATE_ADDED));

		return vo;
	}
}

