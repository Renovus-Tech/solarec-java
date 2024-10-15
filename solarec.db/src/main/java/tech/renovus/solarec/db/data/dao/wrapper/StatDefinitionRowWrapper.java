package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.StatDefinitionVo;

@javax.annotation.Generated(value = "Renovus") public class StatDefinitionRowWrapper implements RowMapper<StatDefinitionVo> {

	//--- Constructors --------------------------
	private StatDefinitionRowWrapper() {}
	private static StatDefinitionRowWrapper instance = new StatDefinitionRowWrapper();
	public static StatDefinitionRowWrapper getInstance() { return StatDefinitionRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetStatDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetStatDefName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetStatDefDescription(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetStatDefExecutable(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetStatDefFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetStatDefType(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public StatDefinitionVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		StatDefinitionVo vo = new StatDefinitionVo();

		vo.setStatDefId(this.getResultSetStatDefId(resultSet, StatDefinitionVo.COLUMN_STAT_DEF_ID));
		vo.setStatDefName(this.getResultSetStatDefName(resultSet, StatDefinitionVo.COLUMN_STAT_DEF_NAME));
		vo.setStatDefDescription(this.getResultSetStatDefDescription(resultSet, StatDefinitionVo.COLUMN_STAT_DEF_DESCRIPTION));
		vo.setStatDefExecutable(this.getResultSetStatDefExecutable(resultSet, StatDefinitionVo.COLUMN_STAT_DEF_EXECUTABLE));
		vo.setStatDefFlags(this.getResultSetStatDefFlags(resultSet, StatDefinitionVo.COLUMN_STAT_DEF_FLAGS));
		vo.setStatDefType(this.getResultSetStatDefType(resultSet, StatDefinitionVo.COLUMN_STAT_DEF_TYPE));

		return vo;
	}
}

