package tech.renovus.solarec.business;

import java.util.Collection;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.StationVo;

public interface StationService {
	
	Collection<StationVo> findAll(Integer offset, Integer size, String name, UserData userData) throws CoreException;
	
	StationVo findVo(Integer locId, UserData userData) throws CoreException;

	void create(StationVo vo, UserData userDatao);
	void update(StationVo vo, UserData userDatao);
	void delete(StationVo vo, UserData userDatao);

	Collection<StationVo> findAllForLocation(Integer locId, UserData userData) throws CoreException;
}
