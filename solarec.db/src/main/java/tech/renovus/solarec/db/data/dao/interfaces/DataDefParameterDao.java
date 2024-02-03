package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.DataDefParameterVo;

public interface DataDefParameterDao {

	Collection<DataDefParameterVo> findAll();
	DataDefParameterVo findVo(Integer dataDefId, Integer dataDefParId);
	void insert(DataDefParameterVo vo);
	void update(DataDefParameterVo vo);
	void delete(DataDefParameterVo vo);
	void synchronize(DataDefParameterVo vo);
	void synchronize(Collection<DataDefParameterVo> vos);

}
