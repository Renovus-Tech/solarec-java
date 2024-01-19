package tech.renovus.solarec.db.data.dao.wrapper.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tech.renovus.solarec.db.data.dao.wrapper.SettingsRowWrapper;
import tech.renovus.solarec.db.data.dao.wrapper.UsrSettingRowWrapper;
import tech.renovus.solarec.vo.db.data.UsrSettingVo;

public class UsrSettingAndSettingsRowWrapper implements RowMapper<UsrSettingVo> {

	//--- Constructors --------------------------
	private UsrSettingAndSettingsRowWrapper() {}
	private static UsrSettingAndSettingsRowWrapper instance = new UsrSettingAndSettingsRowWrapper();
	public static UsrSettingAndSettingsRowWrapper getInstance() { return UsrSettingAndSettingsRowWrapper.instance; }

	//--- Implemented methods -------------------
	@Override public UsrSettingVo mapRow(ResultSet resultSet, int arg1) throws SQLException {
		UsrSettingVo vo = UsrSettingRowWrapper.getInstance().mapRow(resultSet, arg1);
		vo.setSettingVo(SettingsRowWrapper.getInstance().mapRow(resultSet, arg1));
		return vo;
	}
	
}
