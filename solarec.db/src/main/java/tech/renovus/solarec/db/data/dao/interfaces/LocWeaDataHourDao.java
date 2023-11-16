package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.vo.db.data.LocWeaDataHourVo;

public interface LocWeaDataHourDao {

	Collection<LocWeaDataHourVo> findAll();
	LocWeaDataHourVo findVo(Integer cliId, Integer locId, Integer locWeaDataId, java.util.Date locWeaDataHour);
	void insert(LocWeaDataHourVo vo);
	void update(LocWeaDataHourVo vo);
	void delete(LocWeaDataHourVo vo);
	void synchronize(LocWeaDataHourVo vo);
	void synchronize(Collection<LocWeaDataHourVo> vos);
	Collection<LocWeaDataHourVo> getForPeriod(Integer cliId, Integer locId, Date from, Date to);
	Collection<LocWeaDataHourVo> getForPeriod(Integer cliId, Integer locId, Integer locWeaDataId, Date from, Date to);
}

