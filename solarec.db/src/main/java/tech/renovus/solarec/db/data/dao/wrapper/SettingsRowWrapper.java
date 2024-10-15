package tech.renovus.solarec.db.data.dao.wrapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.vo.db.data.SettingsVo;

@javax.annotation.Generated(value = "Renovus") public class SettingsRowWrapper implements RowMapper<SettingsVo> {

	//--- Constructors --------------------------
	private SettingsRowWrapper() {}
	private static SettingsRowWrapper instance = new SettingsRowWrapper();
	public static SettingsRowWrapper getInstance() { return SettingsRowWrapper.instance; }

	//--- Protected methods --------------------
	protected String getResultSetSetName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetSetCatName(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetSetType(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetSetUnit(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetSetValueDefault(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetSetValueMin(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetSetValueMax(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }
	protected String getResultSetSetFlags(ResultSet resultSet, String columnName) throws SQLException { return resultSet.getString(columnName); }

	//--- Overridden methods --------------------
	@Override public SettingsVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		SettingsVo vo = new SettingsVo();

		vo.setSetName(this.getResultSetSetName(resultSet, SettingsVo.COLUMN_SET_NAME));
		vo.setSetCatName(this.getResultSetSetCatName(resultSet, SettingsVo.COLUMN_SET_CAT_NAME));
		vo.setSetType(this.getResultSetSetType(resultSet, SettingsVo.COLUMN_SET_TYPE));
		vo.setSetUnit(this.getResultSetSetUnit(resultSet, SettingsVo.COLUMN_SET_UNIT));
		vo.setSetValueDefault(this.getResultSetSetValueDefault(resultSet, SettingsVo.COLUMN_SET_VALUE_DEFAULT));
		vo.setSetValueMin(this.getResultSetSetValueMin(resultSet, SettingsVo.COLUMN_SET_VALUE_MIN));
		vo.setSetValueMax(this.getResultSetSetValueMax(resultSet, SettingsVo.COLUMN_SET_VALUE_MAX));
		vo.setSetFlags(this.getResultSetSetFlags(resultSet, SettingsVo.COLUMN_SET_FLAGS));

		return vo;
	}
}