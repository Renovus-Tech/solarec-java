package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliUserVo;

@javax.annotation.Generated(value = "Renovus") public class CliUserRowWrapper implements RowMapper<CliUserVo> {

	//--- Constructors --------------------------
	private CliUserRowWrapper() {}
	private static CliUserRowWrapper instance = new CliUserRowWrapper();
	public static CliUserRowWrapper getInstance() { return CliUserRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetUsrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetCliUserDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetCliUserDateAccess(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public CliUserVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliUserVo vo = new CliUserVo();

		vo.setCliId(this.getResultSetCliId(resultSet, CliUserVo.COLUMN_CLI_ID));
		vo.setUsrId(this.getResultSetUsrId(resultSet, CliUserVo.COLUMN_USR_ID));
		vo.setCliUserDateAdded(this.getResultSetCliUserDateAdded(resultSet, CliUserVo.COLUMN_CLI_USER_DATE_ADDED));
		vo.setCliUserDateAccess(this.getResultSetCliUserDateAccess(resultSet, CliUserVo.COLUMN_CLI_USER_DATE_ACCESS));

		return vo;
	}
}

