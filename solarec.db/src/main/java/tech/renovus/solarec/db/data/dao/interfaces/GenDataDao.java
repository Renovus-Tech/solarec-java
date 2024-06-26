package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.vo.db.data.GenDataVo;

public interface GenDataDao {

	Collection<GenDataVo> findAll();
	GenDataVo findVo(Integer cliId, Integer genId, java.util.Date dataDate, Integer dataTypeId);
	void insert(GenDataVo vo);
	void update(GenDataVo vo);
	void delete(GenDataVo vo);
	void synchronize(GenDataVo vo);
	void synchronize(Collection<GenDataVo> vos);
	void insert(Collection<GenDataVo> vos);
	
	void deleteAllForGenerator(Integer cliId, Integer genId);
	Collection<GenDataVo> getGeneratorDataSumValueEquals(Integer cliId, Integer locId, Integer dataTypeId, Double value, Date from, Date to);
	Collection<GenDataVo> getGeneratorDataSumValueEquals(Integer cliId, Integer locId, Integer dataTypeId, Double value, Date from, Date to, String groupBy);
	Collection<GenDataVo> getGeneratorDataAvg(Integer cliId, Integer locId, Integer dataTypeId, Date from, Date to);
	Collection<GenDataVo> getGeneratorDataAvg(Integer cliId, Integer locId, Integer dataTypeId, Date from, Date to, String groupBy);

	Collection<GenDataVo> getGenDataForDatePeriod(Integer cliId, Integer genId, Date genDataDateMin, Date genDataDateMax, int... codes);
	Collection<GenDataVo> getGenDataForDate(Integer cliId, Integer genId, Date genDataDateMax, int... codes);
	Date getMaxDataDate(Integer cliId, Date currentDate, int... codes);
	Collection<GenDataVo> getAllWithoutCertProvData(Integer cliId, Integer locId, int... codes);
}

