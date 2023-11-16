package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.vo.db.data.GeneratorVo;

public interface GeneratorDao {

	Collection<GeneratorVo> findAll();
	GeneratorVo findVo(Integer cliId, Integer genId);
	void insert(GeneratorVo vo);
	void update(GeneratorVo vo);
	void delete(GeneratorVo vo);
	void synchronize(GeneratorVo vo);
	void synchronize(Collection<GeneratorVo> vos);
	
	Collection<GeneratorVo> findAll(Integer cliId);
	Collection<GeneratorVo> findAll(Integer cliId, Integer locId);
	GeneratorVo findVoBySerialNumber(Integer cliId, String genSerailNum);
	void updateDataDateMaxMin();
	
	Date getMaxDataDate(Integer cliId, Date beforeDate);

}

