package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.MlDataRunVo;

public class MlDataRunRowWrapper implements RowMapper<MlDataRunVo> {

	//--- Constructors --------------------------
	private MlDataRunRowWrapper() {}
	private static MlDataRunRowWrapper instance = new MlDataRunRowWrapper();
	public static MlDataRunRowWrapper getInstance() { return MlDataRunRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetRunId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetTotalTimeMs(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetModelVersion(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetTotalEvaluatedRows(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public MlDataRunVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		MlDataRunVo vo = new MlDataRunVo();

		vo.setRunId(this.getResultSetRunId(resultSet, MlDataRunVo.COLUMN_RUN_ID));
		vo.setCliId(this.getResultSetCliId(resultSet, MlDataRunVo.COLUMN_CLI_ID));
		vo.setDataDate(this.getResultSetDataDate(resultSet, MlDataRunVo.COLUMN_DATA_DATE));
		vo.setTotalTimeMs(this.getResultSetTotalTimeMs(resultSet, MlDataRunVo.COLUMN_TOTAL_TIME_MS));
		vo.setModelVersion(this.getResultSetModelVersion(resultSet, MlDataRunVo.COLUMN_MODEL_VERSION));
		vo.setTotalEvaluatedRows(this.getResultSetTotalEvaluatedRows(resultSet, MlDataRunVo.COLUMN_TOTAL_EVALUATED_ROWS));

		return vo;
	}
}

