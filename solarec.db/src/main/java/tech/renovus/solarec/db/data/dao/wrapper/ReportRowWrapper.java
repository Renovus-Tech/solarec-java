package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.ReportVo;

public class ReportRowWrapper implements RowMapper<ReportVo> {

	//--- Constructors --------------------------
	private ReportRowWrapper() {}
	private static ReportRowWrapper instance = new ReportRowWrapper();
	public static ReportRowWrapper getInstance() { return ReportRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetRepId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetRepDateGenerated(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetRepDateFrom(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetRepDateTo(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetRepTitle(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetRepFile(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetRepFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected Integer getResultSetRepTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetRegFile(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public ReportVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ReportVo vo = new ReportVo();

		vo.setCliId(this.getResultSetCliId(resultSet, ReportVo.COLUMN_CLI_ID));
		vo.setRepId(this.getResultSetRepId(resultSet, ReportVo.COLUMN_REP_ID));
		vo.setRepDateGenerated(this.getResultSetRepDateGenerated(resultSet, ReportVo.COLUMN_REP_DATE_GENERATED));
		vo.setRepDateFrom(this.getResultSetRepDateFrom(resultSet, ReportVo.COLUMN_REP_DATE_FROM));
		vo.setRepDateTo(this.getResultSetRepDateTo(resultSet, ReportVo.COLUMN_REP_DATE_TO));
		vo.setRepTitle(this.getResultSetRepTitle(resultSet, ReportVo.COLUMN_REP_TITLE));
		vo.setRepFile(this.getResultSetRepFile(resultSet, ReportVo.COLUMN_REP_FILE));
		vo.setRepFlags(this.getResultSetRepFlags(resultSet, ReportVo.COLUMN_REP_FLAGS));
		vo.setRepTypeId(this.getResultSetRepTypeId(resultSet, ReportVo.COLUMN_REP_TYPE_ID));
		vo.setRegFile(this.getResultSetRegFile(resultSet, ReportVo.COLUMN_REG_FILE));

		return vo;
	}
}

