package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.LocUsrRepTypeVo;

public interface LocUsrRepTypeDao {

	Collection<LocUsrRepTypeVo> findAll();
	LocUsrRepTypeVo findVo(Integer cliId, Integer locId, Integer usrId, Integer repTypeId);
	void insert(LocUsrRepTypeVo vo);
	void update(LocUsrRepTypeVo vo);
	void delete(LocUsrRepTypeVo vo);
	void synchronize(LocUsrRepTypeVo vo);
	void synchronize(Collection<LocUsrRepTypeVo> vos);
	
	Collection<LocUsrRepTypeVo> getAllForUser(Integer usrId);

}

