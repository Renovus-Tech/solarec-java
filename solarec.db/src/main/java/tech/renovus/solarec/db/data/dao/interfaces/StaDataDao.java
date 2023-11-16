package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.vo.db.data.StaDataVo;

public interface StaDataDao {

	Collection<StaDataVo> findAll();
	StaDataVo findVo(Integer cliId, Integer staId, java.util.Date dataDate, Integer dataTypeId);
	void insert(StaDataVo vo);
	void update(StaDataVo vo);
	void delete(StaDataVo vo);
	void synchronize(StaDataVo vo);
	void synchronize(Collection<StaDataVo> vos);
	void insert(Collection<StaDataVo> vos);
	
	void deleteAllForStation(Integer cliId, Integer staId);
	
	Date getMaxDataDate(Integer cliId, Date currentDate, int... codes);
	Collection<StaDataVo> getStaDataForDate(Integer cliId, Integer staId, Date staDataDateMax, int... codes);
	Collection<StaDataVo> getStaDataForDatePeriod(Integer cliId, Integer staId, Date dateFrom, Date dateTo, int... codes);

}

