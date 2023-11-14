package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.db.data.vo.CliGenAlarmVo;

public interface CliGenAlarmDao {

	Collection<CliGenAlarmVo> findAll();
	CliGenAlarmVo findVo(Integer cliId, Double alarmCode);
	void insert(CliGenAlarmVo vo);
	void update(CliGenAlarmVo vo);
	void delete(CliGenAlarmVo vo);
	void synchronize(CliGenAlarmVo vo);
	void synchronize(Collection<CliGenAlarmVo> vos);
	Collection<CliGenAlarmVo> findAll(Integer cliId);

}

