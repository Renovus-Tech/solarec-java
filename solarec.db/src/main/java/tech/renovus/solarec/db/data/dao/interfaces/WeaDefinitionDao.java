package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.WeaDefinitionVo;

public interface WeaDefinitionDao {

	Collection<WeaDefinitionVo> findAll();
	WeaDefinitionVo findVo(Integer cliId, Integer weaId);
	void insert(WeaDefinitionVo vo);
	void update(WeaDefinitionVo vo);
	void delete(WeaDefinitionVo vo);
	void synchronize(WeaDefinitionVo vo);
	void synchronize(Collection<WeaDefinitionVo> vos);
	
	Collection<WeaDefinitionVo> findAll(Integer cliId);

}

