package tech.renovus.solarec.interfaces;

import tech.renovus.solarec.vo.db.data.SettingsVo;

public interface ISetting {

	String getName();
	String getValue();
	SettingsVo getSettingVo();
}
