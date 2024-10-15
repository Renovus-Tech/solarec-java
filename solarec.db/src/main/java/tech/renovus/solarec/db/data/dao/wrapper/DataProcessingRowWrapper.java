package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.DataProcessingVo;

@javax.annotation.Generated(value = "Renovus") public class DataProcessingRowWrapper implements RowMapper<DataProcessingVo> {

	//--- Constructors --------------------------
	private DataProcessingRowWrapper() {}
	private static DataProcessingRowWrapper instance = new DataProcessingRowWrapper();
	public static DataProcessingRowWrapper getInstance() { return DataProcessingRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetDataProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetTriId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataProResult(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataProDateStart(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetDataProDateEnd(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetDataProFileName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetDataProFileLog(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public DataProcessingVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DataProcessingVo vo = new DataProcessingVo();

		vo.setDataProId(this.getResultSetDataProId(resultSet, DataProcessingVo.COLUMN_DATA_PRO_ID));
		vo.setDataDefId(this.getResultSetDataDefId(resultSet, DataProcessingVo.COLUMN_DATA_DEF_ID));
		vo.setCliId(this.getResultSetCliId(resultSet, DataProcessingVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, DataProcessingVo.COLUMN_LOC_ID));
		vo.setTriId(this.getResultSetTriId(resultSet, DataProcessingVo.COLUMN_TRI_ID));
		vo.setDataProResult(this.getResultSetDataProResult(resultSet, DataProcessingVo.COLUMN_DATA_PRO_RESULT));
		vo.setGenId(this.getResultSetGenId(resultSet, DataProcessingVo.COLUMN_GEN_ID));
		vo.setDataProDateStart(this.getResultSetDataProDateStart(resultSet, DataProcessingVo.COLUMN_DATA_PRO_DATE_START));
		vo.setDataProDateEnd(this.getResultSetDataProDateEnd(resultSet, DataProcessingVo.COLUMN_DATA_PRO_DATE_END));
		vo.setDataProFileName(this.getResultSetDataProFileName(resultSet, DataProcessingVo.COLUMN_DATA_PRO_FILE_NAME));
		vo.setDataProFileLog(this.getResultSetDataProFileLog(resultSet, DataProcessingVo.COLUMN_DATA_PRO_FILE_LOG));

		return vo;
	}
}