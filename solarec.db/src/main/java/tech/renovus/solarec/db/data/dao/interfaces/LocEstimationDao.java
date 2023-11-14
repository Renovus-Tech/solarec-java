package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.LocEstimationVo;

public interface LocEstimationDao {

	Collection<LocEstimationVo> findAll();
	LocEstimationVo findVo(Integer cliId, Integer locId, Integer locEstId);
	void insert(LocEstimationVo vo);
	void update(LocEstimationVo vo);
	void delete(LocEstimationVo vo);
	void synchronize(LocEstimationVo vo);
	void synchronize(Collection<LocEstimationVo> vos);
	
	Collection<LocEstimationVo> findAll(Integer cliId, Integer locId);
	void deleteAllFor(Integer cliId, Integer locId);
}

