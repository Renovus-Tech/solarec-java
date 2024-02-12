package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CliDataDefParameterVo;

public interface CliDataDefParameterDao {

	Collection<CliDataDefParameterVo> findAll();
	CliDataDefParameterVo findVo(Integer cliId, Integer dataDefId, Integer dataDefParId);
	void insert(CliDataDefParameterVo vo);
	void update(CliDataDefParameterVo vo);
	void delete(CliDataDefParameterVo vo);
	void synchronize(CliDataDefParameterVo vo);
	void synchronize(Collection<CliDataDefParameterVo> vos);
	
	Collection<CliDataDefParameterVo> getAllFor(Integer cliId);

}
