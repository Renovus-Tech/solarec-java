package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.CliLocUsrSettingVo;

@javax.annotation.Generated(value = "Renovus") public class CliLocUsrSettingRowWrapper implements RowMapper<CliLocUsrSettingVo> {

	//--- Constructors --------------------------
	private CliLocUsrSettingRowWrapper() {}
	private static CliLocUsrSettingRowWrapper instance = new CliLocUsrSettingRowWrapper();
	public static CliLocUsrSettingRowWrapper getInstance() { return CliLocUsrSettingRowWrapper.instance; }

	//--- Protected methods --------------------
	protected Integer getResultSetCliId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetLocId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected Integer getResultSetUsrId(ResultSet resultSet, String columnName) throws SQLException { if (resultSet.getString(columnName) != null) { return Integer.valueOf(resultSet.getInt(columnName)); } else { return null; } }
	protected String getResultSetCliLocAlertFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public CliLocUsrSettingVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliLocUsrSettingVo vo = new CliLocUsrSettingVo();

		vo.setCliId(this.getResultSetCliId(resultSet, CliLocUsrSettingVo.COLUMN_CLI_ID));
		vo.setLocId(this.getResultSetLocId(resultSet, CliLocUsrSettingVo.COLUMN_LOC_ID));
		vo.setUsrId(this.getResultSetUsrId(resultSet, CliLocUsrSettingVo.COLUMN_USR_ID));
		vo.setCliLocAlertFlags(this.getResultSetCliLocAlertFlags(resultSet, CliLocUsrSettingVo.COLUMN_CLI_LOC_ALERT_FLAGS));

		return vo;
	}
}