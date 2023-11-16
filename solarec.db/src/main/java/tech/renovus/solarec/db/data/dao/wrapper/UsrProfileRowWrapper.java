package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.UsrProfileVo;

public class UsrProfileRowWrapper implements RowMapper<UsrProfileVo> {

	//--- Constructors --------------------------
	private UsrProfileRowWrapper() {}
	private static UsrProfileRowWrapper instance = new UsrProfileRowWrapper();
	public static UsrProfileRowWrapper getInstance() { return UsrProfileRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetUsrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetPrfId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected java.util.Date getResultSetUsrPrfDateAdded(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return resultSet.getTimestamp(columnName); } else { return null; } }

	//--- Overridden methods --------------------
	@Override public UsrProfileVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		UsrProfileVo vo = new UsrProfileVo();

		vo.setUsrId(this.getResultSetUsrId(resultSet, UsrProfileVo.COLUMN_USR_ID));
		vo.setPrfId(this.getResultSetPrfId(resultSet, UsrProfileVo.COLUMN_PRF_ID));
		vo.setCliId(this.getResultSetCliId(resultSet, UsrProfileVo.COLUMN_CLI_ID));
		vo.setUsrPrfDateAdded(this.getResultSetUsrPrfDateAdded(resultSet, UsrProfileVo.COLUMN_USR_PRF_DATE_ADDED));

		return vo;
	}
}

