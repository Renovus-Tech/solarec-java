package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.LocTypeVo;

public interface LocTypeDao {

	Collection<LocTypeVo> findAll();
	LocTypeVo findVo(Integer locTypeId);
	void insert(LocTypeVo vo);
	void update(LocTypeVo vo);
	void delete(LocTypeVo vo);
	void synchronize(LocTypeVo vo);
	void synchronize(Collection<LocTypeVo> vos);

}
