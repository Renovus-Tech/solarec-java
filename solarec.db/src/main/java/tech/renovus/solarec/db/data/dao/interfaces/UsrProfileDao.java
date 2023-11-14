package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.UsrProfileVo;

public interface UsrProfileDao {

	Collection<UsrProfileVo> findAll();
	UsrProfileVo findVo(Integer usrId, Integer prfId, Integer cliId);
	void insert(UsrProfileVo vo);
	void update(UsrProfileVo vo);
	void delete(UsrProfileVo vo);
	void synchronize(UsrProfileVo vo);
	void synchronize(Collection<UsrProfileVo> vos);

}

