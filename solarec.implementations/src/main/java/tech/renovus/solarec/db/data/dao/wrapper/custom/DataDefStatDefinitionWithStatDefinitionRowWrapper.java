package tech.renovus.solarec.db.data.dao.wrapper.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.dao.wrapper.DataDefStatDefinitionRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.StatDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefStatDefinitionVo;

public class DataDefStatDefinitionWithStatDefinitionRowWrapper implements RowMapper<DataDefStatDefinitionVo> {

	//--- Constructors --------------------------
	private DataDefStatDefinitionWithStatDefinitionRowWrapper() {}
	private static DataDefStatDefinitionWithStatDefinitionRowWrapper instance = new DataDefStatDefinitionWithStatDefinitionRowWrapper();
	public static DataDefStatDefinitionWithStatDefinitionRowWrapper getInstance() { return DataDefStatDefinitionWithStatDefinitionRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStatDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStatDefCallOrder(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public DataDefStatDefinitionVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataDefStatDefinitionVo vo = DataDefStatDefinitionRowWrapper.getInstance().mapRow(resultSet, arg1);
		vo.setStatDefVo(StatDefinitionRowWrapper.getInstance().mapRow(resultSet, arg1));
		return vo;
	}
}
