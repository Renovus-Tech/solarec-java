package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CliLocUsrSettingVo;

public interface CliLocUsrSettingDao {

	Collection<CliLocUsrSettingVo> findAll();
	CliLocUsrSettingVo findVo(Integer cliId, Integer locId, Integer usrId);
	void insert(CliLocUsrSettingVo vo);
	void update(CliLocUsrSettingVo vo);
	void delete(CliLocUsrSettingVo vo);
	void synchronize(CliLocUsrSettingVo vo);
	void synchronize(Collection<CliLocUsrSettingVo> vos);

}
