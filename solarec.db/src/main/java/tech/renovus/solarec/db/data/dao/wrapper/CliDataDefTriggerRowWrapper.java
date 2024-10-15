package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;

@javax.annotation.Generated(value = "Renovus") public class CliDataDefTriggerRowWrapper implements RowMapper<CliDataDefTriggerVo> {

	//--- Constructors --------------------------
	private CliDataDefTriggerRowWrapper() {}
	private static CliDataDefTriggerRowWrapper instance = new CliDataDefTriggerRowWrapper();
	public static CliDataDefTriggerRowWrapper getInstance() { return CliDataDefTriggerRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetTriId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStaId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetTriSource(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetTriValue(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetTriFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetTriName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CliDataDefTriggerVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliDataDefTriggerVo vo = new CliDataDefTriggerVo();

		vo.setTriId(this.getResultSetTriId(resultSet, CliDataDefTriggerVo.COLUMN_TRI_ID));
		vo.setCliId(this.getResultSetCliId(resultSet, CliDataDefTriggerVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, CliDataDefTriggerVo.COLUMN_LOC_ID));
		vo.setGenId(this.getResultSetGenId(resultSet, CliDataDefTriggerVo.COLUMN_GEN_ID));
		vo.setStaId(this.getResultSetStaId(resultSet, CliDataDefTriggerVo.COLUMN_STA_ID));
		vo.setDataDefId(this.getResultSetDataDefId(resultSet, CliDataDefTriggerVo.COLUMN_DATA_DEF_ID));
		vo.setTriSource(this.getResultSetTriSource(resultSet, CliDataDefTriggerVo.COLUMN_TRI_SOURCE));
		vo.setTriValue(this.getResultSetTriValue(resultSet, CliDataDefTriggerVo.COLUMN_TRI_VALUE));
		vo.setTriFlags(this.getResultSetTriFlags(resultSet, CliDataDefTriggerVo.COLUMN_TRI_FLAGS));
		vo.setTriName(this.getResultSetTriName(resultSet, CliDataDefTriggerVo.COLUMN_TRI_NAME));

		return vo;
	}
}

