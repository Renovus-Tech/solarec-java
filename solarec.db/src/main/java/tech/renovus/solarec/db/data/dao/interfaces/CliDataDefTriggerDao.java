package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;

public interface CliDataDefTriggerDao {

	Collection<CliDataDefTriggerVo> findAll();
	CliDataDefTriggerVo findVo(Integer triId);
	void insert(CliDataDefTriggerVo vo);
	void update(CliDataDefTriggerVo vo);
	void delete(CliDataDefTriggerVo vo);
	void synchronize(CliDataDefTriggerVo vo);
	void synchronize(Collection<CliDataDefTriggerVo> vos);
	
	CliDataDefTriggerVo findFullVo(Integer triId);
	CliDataDefTriggerVo findFullVo(Integer triSource, String triValue);
	Collection<CliDataDefTriggerVo> findAllFor(Integer cliId);
	Collection<CliDataDefTriggerVo> getAllForSource(Integer triSource, boolean forceEnabled);
}

