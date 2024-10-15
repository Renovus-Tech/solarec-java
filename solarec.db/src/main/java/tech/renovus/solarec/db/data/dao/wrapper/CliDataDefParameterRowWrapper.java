package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;

@javax.annotation.Generated(value = "Renovus") public class CliDataDefParameterRowWrapper implements RowMapper<CliDataDefParameterVo> {

	//--- Constructors --------------------------
	protected CliDataDefParameterRowWrapper() {}
	private static CliDataDefParameterRowWrapper instance = new CliDataDefParameterRowWrapper();
	public static CliDataDefParameterRowWrapper getInstance() { return CliDataDefParameterRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefParId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetCliDataDefParValue(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CliDataDefParameterVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliDataDefParameterVo vo = new CliDataDefParameterVo();

		vo.setCliId(this.getResultSetCliId(resultSet, CliDataDefParameterVo.COLUMN_CLI_ID));
		vo.setDataDefId(this.getResultSetDataDefId(resultSet, CliDataDefParameterVo.COLUMN_DATA_DEF_ID));
		vo.setDataDefParId(this.getResultSetDataDefParId(resultSet, CliDataDefParameterVo.COLUMN_DATA_DEF_PAR_ID));
		vo.setCliDataDefParValue(this.getResultSetCliDataDefParValue(resultSet, CliDataDefParameterVo.COLUMN_CLI_DATA_DEF_PAR_VALUE));

		return vo;
	}
}