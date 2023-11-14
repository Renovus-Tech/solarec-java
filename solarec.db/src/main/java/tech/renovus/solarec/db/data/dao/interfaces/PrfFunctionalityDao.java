package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.PrfFunctionalityVo;

public interface PrfFunctionalityDao {

	Collection<PrfFunctionalityVo> findAll();
	PrfFunctionalityVo findVo(Integer prfId, Integer fncId);
	void insert(PrfFunctionalityVo vo);
	void update(PrfFunctionalityVo vo);
	void delete(PrfFunctionalityVo vo);
	void synchronize(PrfFunctionalityVo vo);
	void synchronize(Collection<PrfFunctionalityVo> vos);

}

