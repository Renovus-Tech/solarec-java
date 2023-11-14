package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;
import java.util.Date;

import tech.renovus.solarec.db.data.vo.UsersVo;

public interface UsersDao {

	Collection<UsersVo> findAll();
	UsersVo findVo(Integer usrId);
	void insert(UsersVo vo);
	void update(UsersVo vo);
	void delete(UsersVo vo);
	void synchronize(UsersVo vo);
	void synchronize(Collection<UsersVo> vos);
	UsersVo findBy(String usrEmail);
	void setLoginDate(Integer usrId, Date date);
	Collection<UsersVo> getAllForLocationReport(Integer cliId, Integer locId, Integer repTypeId);
	UsersVo findByResetUuid(String uuid);

}

