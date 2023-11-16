package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.GenAlertVo;

public interface GenAlertDao {

	Collection<GenAlertVo> findAll();
	GenAlertVo findVo(Integer cliId, Integer genId, Integer alertDefId, java.util.Date alertDateAdded);
	void insert(GenAlertVo vo);
	void update(GenAlertVo vo);
	void delete(GenAlertVo vo);
	void synchronize(GenAlertVo vo);
	void synchronize(Collection<GenAlertVo> vos);
	void insert(Collection<GenAlertVo> vos);

}

