package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.DataProAlertProcessingVo;

public class DataProAlertProcessingRowWrapper implements RowMapper<DataProAlertProcessingVo> {

	//--- Constructors --------------------------
	private DataProAlertProcessingRowWrapper() {}
	private static DataProAlertProcessingRowWrapper instance = new DataProAlertProcessingRowWrapper();
	public static DataProAlertProcessingRowWrapper getInstance() { return DataProAlertProcessingRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetDataProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetAlertProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public DataProAlertProcessingVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataProAlertProcessingVo vo = new DataProAlertProcessingVo();

		vo.setDataProId(this.getResultSetDataProId(resultSet, DataProAlertProcessingVo.COLUMN_DATA_PRO_ID));
		vo.setAlertProId(this.getResultSetAlertProId(resultSet, DataProAlertProcessingVo.COLUMN_ALERT_PRO_ID));

		return vo;
	}
}

