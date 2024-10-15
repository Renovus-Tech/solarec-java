package tech.renovus.solarec.db.data.dao.wrapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliSettingVo;

@javax.annotation.Generated(value = "Renovus") public class CliSettingRowWrapper implements RowMapper<CliSettingVo> {

	//--- Constructors --------------------------
	private CliSettingRowWrapper() {}
	private static CliSettingRowWrapper instance = new CliSettingRowWrapper();
	public static CliSettingRowWrapper getInstance() { return CliSettingRowWrapper.instance; }

	//--- Private methods -----------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetCliSetName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetCliSetValue(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CliSettingVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliSettingVo vo = new CliSettingVo();

		vo.setCliId(this.getResultSetCliId(resultSet, CliSettingVo.COLUMN_CLI_ID));
		vo.setCliSetName(this.getResultSetCliSetName(resultSet, CliSettingVo.COLUMN_CLI_SET_NAME));
		vo.setCliSetValue(this.getResultSetCliSetValue(resultSet, CliSettingVo.COLUMN_CLI_SET_VALUE));

		return vo;
	}
}

