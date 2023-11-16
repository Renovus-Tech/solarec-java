package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.LocUserVo;

public interface LocUserDao {

	Collection<LocUserVo> findAll();
	LocUserVo findVo(Integer cliId, Integer locId, Integer usrId);
	void insert(LocUserVo vo);
	void update(LocUserVo vo);
	void delete(LocUserVo vo);
	void synchronize(LocUserVo vo);
	void synchronize(Collection<LocUserVo> vos);

}

