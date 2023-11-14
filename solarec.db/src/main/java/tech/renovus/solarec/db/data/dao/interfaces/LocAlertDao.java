package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.LocAlertVo;

public interface LocAlertDao {

	Collection<LocAlertVo> findAll();
	LocAlertVo findVo(Integer cliId, Integer locId, Integer alertDefId, java.util.Date alertDateAdded);
	void insert(LocAlertVo vo);
	void update(LocAlertVo vo);
	void delete(LocAlertVo vo);
	void synchronize(LocAlertVo vo);
	void synchronize(Collection<LocAlertVo> vos);
	void insert(Collection<LocAlertVo> vos);

}

