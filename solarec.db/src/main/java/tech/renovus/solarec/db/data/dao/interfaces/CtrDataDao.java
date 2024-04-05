package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CtrDataVo;

public interface CtrDataDao {

	Collection<CtrDataVo> findAll();
	CtrDataVo findVo(Integer ctrId, java.util.Date dataDate, Integer dataTypeId);
	void insert(CtrDataVo vo);
	void update(CtrDataVo vo);
	void delete(CtrDataVo vo);
	void synchronize(CtrDataVo vo);
	void synchronize(Collection<CtrDataVo> vos);

}
