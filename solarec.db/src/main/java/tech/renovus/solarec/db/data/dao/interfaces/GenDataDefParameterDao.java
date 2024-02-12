package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;

public interface GenDataDefParameterDao {

	Collection<GenDataDefParameterVo> findAll();
	GenDataDefParameterVo findVo(Integer cliId, Integer genId, Integer dataDefId, Integer dataDefParId);
	void insert(GenDataDefParameterVo vo);
	void update(GenDataDefParameterVo vo);
	void delete(GenDataDefParameterVo vo);
	void synchronize(GenDataDefParameterVo vo);
	void synchronize(Collection<GenDataDefParameterVo> vos);
	
	Collection<GenDataDefParameterVo> getAllFor(Integer cliId, Integer genId);

}
