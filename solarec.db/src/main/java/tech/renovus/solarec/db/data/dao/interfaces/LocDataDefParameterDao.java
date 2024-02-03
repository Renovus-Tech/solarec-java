package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.LocDataDefParameterVo;

public interface LocDataDefParameterDao {

	Collection<LocDataDefParameterVo> findAll();
	LocDataDefParameterVo findVo(Integer cliId, Integer locId, Integer dataDefId, Integer dataDefParId);
	void insert(LocDataDefParameterVo vo);
	void update(LocDataDefParameterVo vo);
	void delete(LocDataDefParameterVo vo);
	void synchronize(LocDataDefParameterVo vo);
	void synchronize(Collection<LocDataDefParameterVo> vos);

}
