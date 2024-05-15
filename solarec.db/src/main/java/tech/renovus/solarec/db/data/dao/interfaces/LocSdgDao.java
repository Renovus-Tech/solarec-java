package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.LocSdgVo;

public interface LocSdgDao {

	Collection<LocSdgVo> findAll();
	LocSdgVo findVo(Integer cliId, Integer locId, Integer sdgId);
	void insert(LocSdgVo vo);
	void update(LocSdgVo vo);
	void delete(LocSdgVo vo);
	void synchronize(LocSdgVo vo);
	void synchronize(Collection<LocSdgVo> vos);
	Collection<LocSdgVo> getAllForLocation(Integer cliId, Integer locId);

}
