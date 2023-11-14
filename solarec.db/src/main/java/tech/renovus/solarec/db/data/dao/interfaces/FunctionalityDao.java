package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.FunctionalityVo;

public interface FunctionalityDao {

	Collection<FunctionalityVo> findAll();
	FunctionalityVo findVo(Integer fncId);
	void insert(FunctionalityVo vo);
	void update(FunctionalityVo vo);
	void delete(FunctionalityVo vo);
	void synchronize(FunctionalityVo vo);
	void synchronize(Collection<FunctionalityVo> vos);
	Collection<FunctionalityVo> findFor(Integer usrId, Integer cliId);

}

