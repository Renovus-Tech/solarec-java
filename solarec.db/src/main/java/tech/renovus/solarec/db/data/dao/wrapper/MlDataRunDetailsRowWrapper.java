package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.MlDataRunDetailsVo;

@javax.annotation.Generated(value = "Renovus") public class MlDataRunDetailsRowWrapper implements RowMapper<MlDataRunDetailsVo> {

	//--- Constructors --------------------------
	private MlDataRunDetailsRowWrapper() {}
	private static MlDataRunDetailsRowWrapper instance = new MlDataRunDetailsRowWrapper();
	public static MlDataRunDetailsRowWrapper getInstance() { return MlDataRunDetailsRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetRunDetId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetRunId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetDataGenId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetDataDate(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetPrediction(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public MlDataRunDetailsVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		MlDataRunDetailsVo vo = new MlDataRunDetailsVo();

		vo.setRunDetId(this.getResultSetRunDetId(resultSet, MlDataRunDetailsVo.COLUMN_RUN_DET_ID));
		vo.setCliId(this.getResultSetCliId(resultSet, MlDataRunDetailsVo.COLUMN_CLI_ID));
		vo.setRunId(this.getResultSetRunId(resultSet, MlDataRunDetailsVo.COLUMN_RUN_ID));
		vo.setDataGenId(this.getResultSetDataGenId(resultSet, MlDataRunDetailsVo.COLUMN_DATA_GEN_ID));
		vo.setDataDate(this.getResultSetDataDate(resultSet, MlDataRunDetailsVo.COLUMN_DATA_DATE));
		vo.setPrediction(this.getResultSetPrediction(resultSet, MlDataRunDetailsVo.COLUMN_PREDICTION));

		return vo;
	}
}

