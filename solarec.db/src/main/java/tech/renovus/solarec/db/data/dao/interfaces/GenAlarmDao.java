package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.GenAlarmVo;

public interface GenAlarmDao {

	Collection<GenAlarmVo> findAll();
	GenAlarmVo findVo(Integer cliId, Integer genId, Double alarmCode);
	void insert(GenAlarmVo vo);
	void update(GenAlarmVo vo);
	void delete(GenAlarmVo vo);
	void synchronize(GenAlarmVo vo);
	void synchronize(Collection<GenAlarmVo> vos);
	Collection<GenAlarmVo> findAll(Integer cliId);

}

