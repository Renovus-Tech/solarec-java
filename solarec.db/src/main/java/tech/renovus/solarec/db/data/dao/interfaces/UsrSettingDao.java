package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.UsrSettingVo;

public interface UsrSettingDao {

	Collection<UsrSettingVo> findAll();
	UsrSettingVo findVo(Integer usrId, String usrSetName);
	void insert(UsrSettingVo vo);
	void update(UsrSettingVo vo);
	void delete(UsrSettingVo vo);
	void synchronize(UsrSettingVo vo);
	void synchronize(Collection<UsrSettingVo> vos);
	
	Collection<UsrSettingVo> findAllFor(Integer usrId);
	void deleteAllFor(Integer usrId);
}

