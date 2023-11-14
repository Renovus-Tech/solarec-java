package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.LocDataVo;

public interface LocDataDao {

	Collection<LocDataVo> findAll();
	LocDataVo findVo(Integer cliId, Integer locId, java.util.Date dataDate, Integer dataTypeId);
	void insert(LocDataVo vo);
	void update(LocDataVo vo);
	void delete(LocDataVo vo);
	void synchronize(LocDataVo vo);
	void synchronize(Collection<LocDataVo> vos);
	void insert(Collection<LocDataVo> vos);

}

