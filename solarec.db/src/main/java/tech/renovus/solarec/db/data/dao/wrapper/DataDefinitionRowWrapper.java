package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.DataDefinitionVo;

public class DataDefinitionRowWrapper implements RowMapper<DataDefinitionVo> {

	//--- Constructors --------------------------
	private DataDefinitionRowWrapper() {}
	private static DataDefinitionRowWrapper instance = new DataDefinitionRowWrapper();
	public static DataDefinitionRowWrapper getInstance() { return DataDefinitionRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetDataDefName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDataDefDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDataDefExecutable(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDataDefFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public DataDefinitionVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataDefinitionVo vo = new DataDefinitionVo();

		vo.setDataDefId(this.getResultSetDataDefId(resultSet, DataDefinitionVo.COLUMN_DATA_DEF_ID));
		vo.setDataDefName(this.getResultSetDataDefName(resultSet, DataDefinitionVo.COLUMN_DATA_DEF_NAME));
		vo.setDataDefDescription(this.getResultSetDataDefDescription(resultSet, DataDefinitionVo.COLUMN_DATA_DEF_DESCRIPTION));
		vo.setDataDefExecutable(this.getResultSetDataDefExecutable(resultSet, DataDefinitionVo.COLUMN_DATA_DEF_EXECUTABLE));
		vo.setDataDefFlags(this.getResultSetDataDefFlags(resultSet, DataDefinitionVo.COLUMN_DATA_DEF_FLAGS));

		return vo;
	}
}

