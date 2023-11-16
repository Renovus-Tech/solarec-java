package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.LocGenAlarmVo;

public interface LocGenAlarmDao {

	Collection<LocGenAlarmVo> findAll();
	LocGenAlarmVo findVo(Integer cliId, Integer locId, Double alarmCode);
	void insert(LocGenAlarmVo vo);
	void update(LocGenAlarmVo vo);
	void delete(LocGenAlarmVo vo);
	void synchronize(LocGenAlarmVo vo);
	void synchronize(Collection<LocGenAlarmVo> vos);
	Collection<LocGenAlarmVo> findAll(Integer cliId, Integer locId);

}

