package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.FrequencyVo;

public interface FrequencyDao {

	Collection<FrequencyVo> findAll();
	FrequencyVo findVo(Integer frqId);
	void insert(FrequencyVo vo);
	void update(FrequencyVo vo);
	void delete(FrequencyVo vo);
	void synchronize(FrequencyVo vo);
	void synchronize(Collection<FrequencyVo> vos);

}
