package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.vo.LocUsrRepTypeVo;

public class LocUsrRepTypeRowWrapper implements RowMapper<LocUsrRepTypeVo> {

	//--- Constructors --------------------------
	private LocUsrRepTypeRowWrapper() {}
	private static LocUsrRepTypeRowWrapper instance = new LocUsrRepTypeRowWrapper();
	public static LocUsrRepTypeRowWrapper getInstance() { return LocUsrRepTypeRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetUsrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetRepTypeId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetLocUsrRepTypeFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public LocUsrRepTypeVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		LocUsrRepTypeVo vo = new LocUsrRepTypeVo();

		vo.setCliId(this.getResultSetCliId(resultSet, LocUsrRepTypeVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, LocUsrRepTypeVo.COLUMN_LOC_ID));
		vo.setUsrId(this.getResultSetUsrId(resultSet, LocUsrRepTypeVo.COLUMN_USR_ID));
		vo.setRepTypeId(this.getResultSetRepTypeId(resultSet, LocUsrRepTypeVo.COLUMN_REP_TYPE_ID));
		vo.setLocUsrRepTypeFlags(this.getResultSetLocUsrRepTypeFlags(resultSet, LocUsrRepTypeVo.COLUMN_LOC_USR_REP_TYPE_FLAGS));

		return vo;
	}
}

