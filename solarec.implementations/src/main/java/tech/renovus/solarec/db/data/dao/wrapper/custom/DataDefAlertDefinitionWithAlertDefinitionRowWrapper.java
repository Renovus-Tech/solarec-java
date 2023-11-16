package tech.renovus.solarec.db.data.dao.wrapper.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.dao.wrapper.AlertDefinitionRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.DataDefAlertDefinitionRowWrapper;
import tech.renovus.solarec.vo.db.data.DataDefAlertDefinitionVo;

public class DataDefAlertDefinitionWithAlertDefinitionRowWrapper implements RowMapper<DataDefAlertDefinitionVo> {

	//--- Constructors --------------------------
	private DataDefAlertDefinitionWithAlertDefinitionRowWrapper() {}
	private static DataDefAlertDefinitionWithAlertDefinitionRowWrapper instance = new DataDefAlertDefinitionWithAlertDefinitionRowWrapper();
	public static DataDefAlertDefinitionWithAlertDefinitionRowWrapper getInstance() { return DataDefAlertDefinitionWithAlertDefinitionRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetAlertDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetAlertDefCallOrder(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public DataDefAlertDefinitionVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataDefAlertDefinitionVo vo = DataDefAlertDefinitionRowWrapper.getInstance().mapRow(resultSet, arg1);
		vo.setAlertDefVo(AlertDefinitionRowWrapper.getInstance().mapRow(resultSet, arg1));
		return vo;
	}
}
