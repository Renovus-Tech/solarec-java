package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.UsersVo;

public class UsersRowWrapper implements RowMapper<UsersVo> {

	//--- Constructors --------------------------
	private UsersRowWrapper() {}
	private static UsersRowWrapper instance = new UsersRowWrapper();
	public static UsersRowWrapper getInstance() { return UsersRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetUsrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetUsrEmail(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetUsrName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetUsrFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetUsrPassword(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected java.util.Date getResultSetUsrDateLogin(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetUsrDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected java.util.Date getResultSetUsrDateLocked(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetUsrComments(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected java.util.Date getResultSetUsrPwdResetRequested(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }
	protected String getResultSetUsrPwdResetUuid(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public UsersVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		UsersVo vo = new UsersVo();

		vo.setUsrId(this.getResultSetUsrId(resultSet, UsersVo.COLUMN_USR_ID));
		vo.setUsrEmail(this.getResultSetUsrEmail(resultSet, UsersVo.COLUMN_USR_EMAIL));
		vo.setUsrName(this.getResultSetUsrName(resultSet, UsersVo.COLUMN_USR_NAME));
		vo.setUsrFlags(this.getResultSetUsrFlags(resultSet, UsersVo.COLUMN_USR_FLAGS));
		vo.setUsrPassword(this.getResultSetUsrPassword(resultSet, UsersVo.COLUMN_USR_PASSWORD));
		vo.setUsrDateLogin(this.getResultSetUsrDateLogin(resultSet, UsersVo.COLUMN_USR_DATE_LOGIN));
		vo.setUsrDateAdded(this.getResultSetUsrDateAdded(resultSet, UsersVo.COLUMN_USR_DATE_ADDED));
		vo.setUsrDateLocked(this.getResultSetUsrDateLocked(resultSet, UsersVo.COLUMN_USR_DATE_LOCKED));
		vo.setUsrComments(this.getResultSetUsrComments(resultSet, UsersVo.COLUMN_USR_COMMENTS));
		vo.setUsrPwdResetRequested(this.getResultSetUsrPwdResetRequested(resultSet, UsersVo.COLUMN_USR_PWD_RESET_REQUESTED));
		vo.setUsrPwdResetUuid(this.getResultSetUsrPwdResetUuid(resultSet, UsersVo.COLUMN_USR_PWD_RESET_UUID));

		return vo;
	}
}

