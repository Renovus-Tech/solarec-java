package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CliGenUsrAlertVo;

public interface CliGenUsrAlertDao {

	Collection<CliGenUsrAlertVo> findAll();
	CliGenUsrAlertVo findVo(Integer cliId, Integer genId, Integer usrId, Integer cliGenAlertId);
	void insert(CliGenUsrAlertVo vo);
	void update(CliGenUsrAlertVo vo);
	void delete(CliGenUsrAlertVo vo);
	void synchronize(CliGenUsrAlertVo vo);
	void synchronize(Collection<CliGenUsrAlertVo> vos);

}
