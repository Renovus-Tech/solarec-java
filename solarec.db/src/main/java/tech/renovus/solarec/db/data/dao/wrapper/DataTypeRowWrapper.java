package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.DataTypeVo;

public class DataTypeRowWrapper implements RowMapper<DataTypeVo> {

	//--- Constructors --------------------------
	private DataTypeRowWrapper() {}
	private static DataTypeRowWrapper instance = new DataTypeRowWrapper();
	public static DataTypeRowWrapper getInstance() { return DataTypeRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetDataTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetDataTypeName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDataTypeUnits(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDataTypeDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public DataTypeVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataTypeVo vo = new DataTypeVo();

		vo.setDataTypeId(this.getResultSetDataTypeId(resultSet, DataTypeVo.COLUMN_DATA_TYPE_ID));
		vo.setDataTypeName(this.getResultSetDataTypeName(resultSet, DataTypeVo.COLUMN_DATA_TYPE_NAME));
		vo.setDataTypeUnits(this.getResultSetDataTypeUnits(resultSet, DataTypeVo.COLUMN_DATA_TYPE_UNITS));
		vo.setDataTypeDescription(this.getResultSetDataTypeDescription(resultSet, DataTypeVo.COLUMN_DATA_TYPE_DESCRIPTION));

		return vo;
	}
}

