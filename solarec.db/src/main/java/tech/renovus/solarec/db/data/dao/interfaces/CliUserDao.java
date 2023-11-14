package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.db.data.vo.CliUserVo;

public interface CliUserDao {

	Collection<CliUserVo> findAll();
	CliUserVo findVo(Integer cliId, Integer usrId);
	void insert(CliUserVo vo);
	void update(CliUserVo vo);
	void delete(CliUserVo vo);
	void synchronize(CliUserVo vo);
	void synchronize(Collection<CliUserVo> vos);
	void setAccessDate(Integer cliId, Integer usrId, Date date);

}

