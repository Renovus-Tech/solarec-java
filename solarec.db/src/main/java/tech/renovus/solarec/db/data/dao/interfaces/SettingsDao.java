package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.SettingsVo;

public interface SettingsDao {

	Collection<SettingsVo> findAll();
	SettingsVo findVo(String setName);
	void insert(SettingsVo vo);
	void update(SettingsVo vo);
	void delete(SettingsVo vo);
	void synchronize(SettingsVo vo);
	void synchronize(Collection<SettingsVo> vos);

}
