package tech.renovus.solarec.db.data.dao.wrapper.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.dao.wrapper.CliSettingRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.SettingsRowWrapper;
import tech.renovus.solarec.vo.db.data.CliSettingVo;

public class CliSettingAndSettingsRowWrapper implements RowMapper<CliSettingVo> {

	//--- Constructors --------------------------
	private CliSettingAndSettingsRowWrapper() {}
	private static CliSettingAndSettingsRowWrapper instance = new CliSettingAndSettingsRowWrapper();
	public static CliSettingAndSettingsRowWrapper getInstance() { return CliSettingAndSettingsRowWrapper.instance; }

	//--- Implemented methods -------------------
	@Override public CliSettingVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		CliSettingVo vo = CliSettingRowWrapper.getInstance().mapRow(resultSet, arg1);
		vo.setSettingVo(SettingsRowWrapper.getInstance().mapRow(resultSet, arg1));
		return vo;
	}
	
}
