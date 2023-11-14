package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.db.data.vo.DataProcessingVo;

public interface DataProcessingDao {

	Collection<DataProcessingVo> findAll();
	DataProcessingVo findVo(Integer dataProId);
	void insert(DataProcessingVo vo);
	void update(DataProcessingVo vo);
	void delete(DataProcessingVo vo);
	void synchronize(DataProcessingVo vo);
	void synchronize(Collection<DataProcessingVo> vos);
	
	Collection<DataProcessingVo> finaAll(Integer cliId);
	Date getDate(Integer cliId, Integer dataProId, boolean fromDate);

}

