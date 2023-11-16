package tech.renovus.solarec.business;

import java.util.Collection;
import java.util.List;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.rest.entity.Location;

public interface GeneratorService {
	
	Collection<GeneratorVo> findAll(Integer offset, Integer size, String name, UserData userData) throws CoreException;
	
	GeneratorVo findVo(Integer genId, UserData userData) throws CoreException;
	GeneratorVo findFullVo(Integer genId, UserData userData) throws CoreException;

	GeneratorVo create(GeneratorVo vo, UserData userDatao);
	GeneratorVo update(GeneratorVo vo, UserData userDatao);
	void delete(GeneratorVo vo, UserData userDatao);

	Collection<GeneratorVo> findAllForLocation(Integer locId, UserData userData) throws CoreException;

	Collection<LocationVo> getNeighbors(UserData loggedUserData);
	Collection<LocationVo> setNeighbors(List<Location> generators, UserData loggedUserData);
}
