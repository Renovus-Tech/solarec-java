package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.WeaCallVo;

public interface WeaCallDao {

	Collection<WeaCallVo> findAll();
	WeaCallVo findVo(Integer cliId, Integer weaId, Integer weaCallId);
	void insert(WeaCallVo vo);
	void update(WeaCallVo vo);
	void delete(WeaCallVo vo);
	void synchronize(WeaCallVo vo);
	void synchronize(Collection<WeaCallVo> vos);
	
	WeaCallVo getLast(Integer cliId, Integer weaId);
	Collection<WeaCallVo> getHistory(Integer cliId, Integer weaId, Integer amount);
}

