package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.StatProcessingVo;

public class StatProcessingRowWrapper implements RowMapper<StatProcessingVo> {

	//--- Constructors --------------------------
	private StatProcessingRowWrapper() {}
	private static StatProcessingRowWrapper instance = new StatProcessingRowWrapper();
	public static StatProcessingRowWrapper getInstance() { return StatProcessingRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetStatProId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetStatDefId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetStatProDateStart(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetStatProDateEnd(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected Integer getResultSetStatProResult(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetStatProFileLog(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetStatProType(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public StatProcessingVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		StatProcessingVo vo = new StatProcessingVo();

		vo.setStatProId(this.getResultSetStatProId(resultSet, StatProcessingVo.COLUMN_STAT_PRO_ID));
		vo.setStatDefId(this.getResultSetStatDefId(resultSet, StatProcessingVo.COLUMN_STAT_DEF_ID));
		vo.setCliId(this.getResultSetCliId(resultSet, StatProcessingVo.COLUMN_CLI_ID));
		vo.setStatProDateStart(this.getResultSetStatProDateStart(resultSet, StatProcessingVo.COLUMN_STAT_PRO_DATE_START));
		vo.setStatProDateEnd(this.getResultSetStatProDateEnd(resultSet, StatProcessingVo.COLUMN_STAT_PRO_DATE_END));
		vo.setStatProResult(this.getResultSetStatProResult(resultSet, StatProcessingVo.COLUMN_STAT_PRO_RESULT));
		vo.setStatProFileLog(this.getResultSetStatProFileLog(resultSet, StatProcessingVo.COLUMN_STAT_PRO_FILE_LOG));
		vo.setStatProType(this.getResultSetStatProType(resultSet, StatProcessingVo.COLUMN_STAT_PRO_TYPE));

		return vo;
	}
}

