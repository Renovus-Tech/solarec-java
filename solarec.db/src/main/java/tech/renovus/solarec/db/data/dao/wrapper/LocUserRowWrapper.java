package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.LocUserVo;

@javax.annotation.Generated(value = "Renovus") public class LocUserRowWrapper implements RowMapper<LocUserVo> {

	//--- Constructors --------------------------
	private LocUserRowWrapper() {}
	private static LocUserRowWrapper instance = new LocUserRowWrapper();
	public static LocUserRowWrapper getInstance() { return LocUserRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetUsrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetCliUserDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetCliUserDateAccess(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public LocUserVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocUserVo vo = new LocUserVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocUserVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocUserVo.COLUMN_LOC_ID));
		vo.setUsrId(this.getResultSetUsrId(resultSet, LocUserVo.COLUMN_USR_ID));
		vo.setCliUserDateAdded(this.getResultSetCliUserDateAdded(resultSet, LocUserVo.COLUMN_CLI_USER_DATE_ADDED));
		vo.setCliUserDateAccess(this.getResultSetCliUserDateAccess(resultSet, LocUserVo.COLUMN_CLI_USER_DATE_ACCESS));

		return vo;
	}
}

