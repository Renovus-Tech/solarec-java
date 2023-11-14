package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.RepTypeVo;

public interface RepTypeDao {

	Collection<RepTypeVo> findAll();
	RepTypeVo findVo(Integer repTypeId);
	void insert(RepTypeVo vo);
	void update(RepTypeVo vo);
	void delete(RepTypeVo vo);
	void synchronize(RepTypeVo vo);
	void synchronize(Collection<RepTypeVo> vos);
	
	Collection<RepTypeVo> getlAllActive();
	RepTypeVo findByName(String type);
}

