package tech.renovus.solarec.db.data.dao.interfaces;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.LocationVo;

public interface LocationDao {

	Collection<LocationVo> findAll();
	LocationVo findVo(Integer cliId, Integer locId);
	void insert(LocationVo vo);
	void update(LocationVo vo);
	void delete(LocationVo vo);
	void synchronize(LocationVo vo);
	void synchronize(Collection<LocationVo> vos);
	
	Collection<LocationVo> findAll(Integer cliId);
	Collection<LocationVo> findAll(Integer cliId, String locType);
	Collection<LocationVo> findAllForUser(Integer cliId, Integer usrId, String locType);
	Collection<LocationVo> findAllForUser(Integer cliId, Integer usrId, boolean orderByLastAccess);
	LocationVo findForUser(Integer usrId, Integer cliId, Integer locId);
	
	Collection<LocationVo> getAllForReport();
	Collection<LocationVo> getAllForAlertCalculation();
	
	void updateDataDateMaxMin();

}

