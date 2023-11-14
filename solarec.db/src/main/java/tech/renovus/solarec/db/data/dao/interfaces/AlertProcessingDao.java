package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.AlertProcessingVo;

public interface AlertProcessingDao {

	Collection<AlertProcessingVo> findAll();
	AlertProcessingVo findVo(Integer alertProId);
	void insert(AlertProcessingVo vo);
	void update(AlertProcessingVo vo);
	void delete(AlertProcessingVo vo);
	void synchronize(AlertProcessingVo vo);
	void synchronize(Collection<AlertProcessingVo> vos);

}

