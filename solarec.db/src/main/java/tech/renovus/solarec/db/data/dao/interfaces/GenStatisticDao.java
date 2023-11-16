package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.GenStatisticVo;

public interface GenStatisticDao {

	Collection<GenStatisticVo> findAll();
	GenStatisticVo findVo(Integer cliId, Integer genId, java.util.Date statDate, Integer statTypeId);
	void insert(GenStatisticVo vo);
	void update(GenStatisticVo vo);
	void delete(GenStatisticVo vo);
	void synchronize(GenStatisticVo vo);
	void synchronize(Collection<GenStatisticVo> vos);
	
	void deleteAllForGenerator(Integer cliId, Integer genId);

}

