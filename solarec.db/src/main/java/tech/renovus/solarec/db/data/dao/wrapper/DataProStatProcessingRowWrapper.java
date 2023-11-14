package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.DataProStatProcessingVo;

public class DataProStatProcessingRowWrapper implements RowMapper<DataProStatProcessingVo> {

	//--- Constructors --------------------------
	private DataProStatProcessingRowWrapper() {}
	private static DataProStatProcessingRowWrapper instance = new DataProStatProcessingRowWrapper();
	public static DataProStatProcessingRowWrapper getInstance() { return DataProStatProcessingRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetDataProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStatProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public DataProStatProcessingVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataProStatProcessingVo vo = new DataProStatProcessingVo();

		vo.setDataProId(this.getResultSetDataProId(resultSet, DataProStatProcessingVo.COLUMN_DATA_PRO_ID));
		vo.setStatProId(this.getResultSetStatProId(resultSet, DataProStatProcessingVo.COLUMN_STAT_PRO_ID));

		return vo;
	}
}

