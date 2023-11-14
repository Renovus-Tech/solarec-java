package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.DataDefAlertDefinitionVo;

public class DataDefAlertDefinitionRowWrapper implements RowMapper<DataDefAlertDefinitionVo> {

	//--- Constructors --------------------------
	private DataDefAlertDefinitionRowWrapper() {}
	private static DataDefAlertDefinitionRowWrapper instance = new DataDefAlertDefinitionRowWrapper();
	public static DataDefAlertDefinitionRowWrapper getInstance() { return DataDefAlertDefinitionRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetAlertDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetAlertDefCallOrder(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public DataDefAlertDefinitionVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataDefAlertDefinitionVo vo = new DataDefAlertDefinitionVo();

		vo.setDataDefId(this.getResultSetDataDefId(resultSet, DataDefAlertDefinitionVo.COLUMN_DATA_DEF_ID));
		vo.setAlertDefId(this.getResultSetAlertDefId(resultSet, DataDefAlertDefinitionVo.COLUMN_ALERT_DEF_ID));
		vo.setAlertDefCallOrder(this.getResultSetAlertDefCallOrder(resultSet, DataDefAlertDefinitionVo.COLUMN_ALERT_DEF_CALL_ORDER));

		return vo;
	}
}

