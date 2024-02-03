package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.DataDefParameterVo;

public class DataDefParameterRowWrapper implements RowMapper<DataDefParameterVo> {

	//--- Constructors --------------------------
	private DataDefParameterRowWrapper() {}
	private static DataDefParameterRowWrapper instance = new DataDefParameterRowWrapper();
	public static DataDefParameterRowWrapper getInstance() { return DataDefParameterRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefParId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetDataDefParName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDataDefDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public DataDefParameterVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataDefParameterVo vo = new DataDefParameterVo();

		vo.setDataDefId(this.getResultSetDataDefId(resultSet, DataDefParameterVo.COLUMN_DATA_DEF_ID));
		vo.setDataDefParId(this.getResultSetDataDefParId(resultSet, DataDefParameterVo.COLUMN_DATA_DEF_PAR_ID));
		vo.setDataDefParName(this.getResultSetDataDefParName(resultSet, DataDefParameterVo.COLUMN_DATA_DEF_PAR_NAME));
		vo.setDataDefDescription(this.getResultSetDataDefDescription(resultSet, DataDefParameterVo.COLUMN_DATA_DEF_DESCRIPTION));

		return vo;
	}
}