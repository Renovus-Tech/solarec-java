package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CliSettingVo;

public interface CliSettingDao {

	Collection<CliSettingVo> findAll();
	CliSettingVo findVo(Integer cliId, String cliSetName);
	void insert(CliSettingVo vo);
	void update(CliSettingVo vo);
	void delete(CliSettingVo vo);
	void synchronize(CliSettingVo vo);
	void synchronize(Collection<CliSettingVo> vos);

	Collection<CliSettingVo> findAllFor(Integer cliId);
	void deleteAllFor(Integer cliId);
	CliSettingVo findVoOrDefault(Integer cliId, String cliSetName, String defaultValue);

}

