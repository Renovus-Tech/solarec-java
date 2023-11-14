package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.StaDataVo;

public class StaDataRowWrapper implements RowMapper<StaDataVo> {

	//--- Constructors --------------------------
	private StaDataRowWrapper() {}
	private static StaDataRowWrapper instance = new StaDataRowWrapper();
	public static StaDataRowWrapper getInstance() { return StaDataRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStaId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetDataTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Double getResultSetDataValue(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return new Double(resultSet.getDouble(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public StaDataVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		StaDataVo vo = new StaDataVo();

		vo.setCliId(this.getResultSetCliId(resultSet, StaDataVo.COLUMN_CLI_ID));
		vo.setStaId(this.getResultSetStaId(resultSet, StaDataVo.COLUMN_STA_ID));
		vo.setDataDate(this.getResultSetDataDate(resultSet, StaDataVo.COLUMN_DATA_DATE));
		vo.setDataTypeId(this.getResultSetDataTypeId(resultSet, StaDataVo.COLUMN_DATA_TYPE_ID));
		vo.setDataProId(this.getResultSetDataProId(resultSet, StaDataVo.COLUMN_DATA_PRO_ID));
		vo.setDataValue(this.getResultSetDataValue(resultSet, StaDataVo.COLUMN_DATA_VALUE));
		vo.setDataDateAdded(this.getResultSetDataDateAdded(resultSet, StaDataVo.COLUMN_DATA_DATE_ADDED));

		return vo;
	}
}

