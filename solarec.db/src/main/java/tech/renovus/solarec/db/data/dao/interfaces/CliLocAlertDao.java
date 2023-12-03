package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.vo.db.data.CliLocAlertVo;

public interface CliLocAlertDao {

	Collection<CliLocAlertVo> findAll();
	CliLocAlertVo findVo(Integer cliId, Integer locId, Integer cliLocAlertId);
	void insert(CliLocAlertVo vo);
	void update(CliLocAlertVo vo);
	void delete(CliLocAlertVo vo);
	void synchronize(CliLocAlertVo vo);
	void synchronize(Collection<CliLocAlertVo> vos);
	
	Collection<CliLocAlertVo> retrieveFor(Integer cliId, Integer locId, Date from, Date to);
}
