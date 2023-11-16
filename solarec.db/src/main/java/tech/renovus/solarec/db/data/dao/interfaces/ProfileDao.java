package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.ProfileVo;

public interface ProfileDao {

	Collection<ProfileVo> findAll();
	ProfileVo findVo(Integer prfId);
	void insert(ProfileVo vo);
	void update(ProfileVo vo);
	void delete(ProfileVo vo);
	void synchronize(ProfileVo vo);
	void synchronize(Collection<ProfileVo> vos);

}

