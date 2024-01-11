package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CliGenAlertVo;

public interface CliGenAlertDao {

	Collection<CliGenAlertVo> findAll();
	CliGenAlertVo findVo(Integer cliId, Integer genId, Integer cliGenAlertId);
	void insert(CliGenAlertVo vo);
	void update(CliGenAlertVo vo);
	void delete(CliGenAlertVo vo);
	void synchronize(CliGenAlertVo vo);
	void synchronize(Collection<CliGenAlertVo> vos);

}
