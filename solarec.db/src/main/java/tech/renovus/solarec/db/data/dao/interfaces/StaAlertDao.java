package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.StaAlertVo;

public interface StaAlertDao {

	Collection<StaAlertVo> findAll();
	StaAlertVo findVo(Integer cliId, Integer staId, Integer alertDefId, java.util.Date alertDateAdded);
	void insert(StaAlertVo vo);
	void update(StaAlertVo vo);
	void delete(StaAlertVo vo);
	void synchronize(StaAlertVo vo);
	void synchronize(Collection<StaAlertVo> vos);
	void insert(Collection<StaAlertVo> vos);

}

