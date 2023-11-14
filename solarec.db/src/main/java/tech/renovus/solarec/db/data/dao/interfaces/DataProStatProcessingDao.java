package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.DataProStatProcessingVo;

public interface DataProStatProcessingDao {

	Collection<DataProStatProcessingVo> findAll();
	DataProStatProcessingVo findVo(Integer dataProId, Integer statProId);
	void insert(DataProStatProcessingVo vo);
	void update(DataProStatProcessingVo vo);
	void delete(DataProStatProcessingVo vo);
	void synchronize(DataProStatProcessingVo vo);
	void synchronize(Collection<DataProStatProcessingVo> vos);

}

