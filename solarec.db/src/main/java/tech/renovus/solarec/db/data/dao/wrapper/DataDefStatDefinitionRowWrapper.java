package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.DataDefStatDefinitionVo;

@javax.annotation.Generated(value = "Renovus") public class DataDefStatDefinitionRowWrapper implements RowMapper<DataDefStatDefinitionVo> {

	//--- Constructors --------------------------
	private DataDefStatDefinitionRowWrapper() {}
	private static DataDefStatDefinitionRowWrapper instance = new DataDefStatDefinitionRowWrapper();
	public static DataDefStatDefinitionRowWrapper getInstance() { return DataDefStatDefinitionRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStatDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStatDefCallOrder(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public DataDefStatDefinitionVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataDefStatDefinitionVo vo = new DataDefStatDefinitionVo();

		vo.setDataDefId(this.getResultSetDataDefId(resultSet, DataDefStatDefinitionVo.COLUMN_DATA_DEF_ID));
		vo.setStatDefId(this.getResultSetStatDefId(resultSet, DataDefStatDefinitionVo.COLUMN_STAT_DEF_ID));
		vo.setStatDefCallOrder(this.getResultSetStatDefCallOrder(resultSet, DataDefStatDefinitionVo.COLUMN_STAT_DEF_CALL_ORDER));

		return vo;
	}
}

