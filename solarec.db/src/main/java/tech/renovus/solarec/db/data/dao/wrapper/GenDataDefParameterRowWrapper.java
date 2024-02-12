package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;

public class GenDataDefParameterRowWrapper implements RowMapper<GenDataDefParameterVo> {

	//--- Constructors --------------------------
	protected GenDataDefParameterRowWrapper() {}
	private static GenDataDefParameterRowWrapper instance = new GenDataDefParameterRowWrapper();
	public static GenDataDefParameterRowWrapper getInstance() { return GenDataDefParameterRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefParId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetGenDataDefParValue(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public GenDataDefParameterVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		GenDataDefParameterVo vo = new GenDataDefParameterVo();

		vo.setCliId(this.getResultSetCliId(resultSet, GenDataDefParameterVo.COLUMN_CLI_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, GenDataDefParameterVo.COLUMN_GEN_ID));
		vo.setDataDefId(this.getResultSetDataDefId(resultSet, GenDataDefParameterVo.COLUMN_DATA_DEF_ID));
		vo.setDataDefParId(this.getResultSetDataDefParId(resultSet, GenDataDefParameterVo.COLUMN_DATA_DEF_PAR_ID));
		vo.setGenDataDefParValue(this.getResultSetGenDataDefParValue(resultSet, GenDataDefParameterVo.COLUMN_GEN_DATA_DEF_PAR_VALUE));

		return vo;
	}
}