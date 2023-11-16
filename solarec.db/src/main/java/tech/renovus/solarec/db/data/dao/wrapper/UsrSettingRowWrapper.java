package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.UsrSettingVo;

public class UsrSettingRowWrapper implements RowMapper<UsrSettingVo> {

	//--- Constructors --------------------------
	private UsrSettingRowWrapper() {}
	private static UsrSettingRowWrapper instance = new UsrSettingRowWrapper();
	public static UsrSettingRowWrapper getInstance() { return UsrSettingRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetUsrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetUsrSetName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetUsrSetValue(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public UsrSettingVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		UsrSettingVo vo = new UsrSettingVo();

		vo.setUsrId(this.getResultSetUsrId(resultSet, UsrSettingVo.COLUMN_USR_ID));
		vo.setUsrSetName(this.getResultSetUsrSetName(resultSet, UsrSettingVo.COLUMN_USR_SET_NAME));
		vo.setUsrSetValue(this.getResultSetUsrSetValue(resultSet, UsrSettingVo.COLUMN_USR_SET_VALUE));

		return vo;
	}
}

