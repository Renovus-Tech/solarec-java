package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.SdgVo;

public interface SdgDao {

	Collection<SdgVo> findAll();
	SdgVo findVo(Integer sdgId);
	void insert(SdgVo vo);
	void update(SdgVo vo);
	void delete(SdgVo vo);
	void synchronize(SdgVo vo);
	void synchronize(Collection<SdgVo> vos);

}
