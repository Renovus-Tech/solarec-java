package tech.renovus.solarec.business;

import java.util.Collection;
import java.util.List;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.rest.entity.Location;

public interface LocationService {
	
	Collection<LocationVo> findAll(Integer offset, Integer size, String name, UserData userData) throws CoreException;
	
	LocationVo findVo(Integer locId, UserData userData) throws CoreException;
	LocationVo findFullVo(Integer locId, UserData userData) throws CoreException;

	void create(LocationVo vo, UserData userDatao);
	void update(LocationVo vo, UserData userDatao);
	void delete(LocationVo vo, UserData userDatao);

	Collection<LocationVo> getEstimations(UserData loggedUserData);
	Collection<LocationVo> setEstimations(List<Location> locations, UserData loggedUserData);
}
