package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CliLocUsrAlertVo;

public interface CliLocUsrAlertDao {

	Collection<CliLocUsrAlertVo> findAll();
	CliLocUsrAlertVo findVo(Integer cliId, Integer locId, Integer usrId, Integer cliLocAlertId);
	void insert(CliLocUsrAlertVo vo);
	void update(CliLocUsrAlertVo vo);
	void delete(CliLocUsrAlertVo vo);
	void synchronize(CliLocUsrAlertVo vo);
	void synchronize(Collection<CliLocUsrAlertVo> vos);

}
