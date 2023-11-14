package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.DataProAlertProcessingVo;

public interface DataProAlertProcessingDao {

	Collection<DataProAlertProcessingVo> findAll();
	DataProAlertProcessingVo findVo(Integer dataProId, Integer alertProId);
	void insert(DataProAlertProcessingVo vo);
	void update(DataProAlertProcessingVo vo);
	void delete(DataProAlertProcessingVo vo);
	void synchronize(DataProAlertProcessingVo vo);
	void synchronize(Collection<DataProAlertProcessingVo> vos);

}

