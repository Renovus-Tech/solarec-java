package tech.renovus.solarec.business.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.LocationService;
import tech.renovus.solarec.db.data.dao.interfaces.CountryDao;
import tech.renovus.solarec.db.data.dao.interfaces.DataDefinitionDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocEstimationDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.LocEstimationVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.rest.entity.Location;

@Service
public class LocationServiceImpl implements LocationService {

	//--- Private constants ---------------------
	private static final int MAX_AMOUNT_ESTIMATIONS_PER_LOCATION = 2;
	
	//--- Resources -----------------------------
	@Resource LocationDao dao;
	@Resource GeneratorDao generatorsDao;
	@Resource StationDao stationsDao;
	@Resource DataDefinitionDao dataDefinitionDao;
	@Resource LocEstimationDao locEstimationDao;
	@Resource CountryDao countryDao;
	
	//--- Overridden methods ------------------------
	@Override public Collection<LocationVo> findAll(Integer offset, Integer size, String name, UserData userData) throws CoreException {
		return this.dao.findAll(userData.getCliId());
	}
	
	@Override public LocationVo findVo(Integer locId, UserData userData) throws CoreException {
		return this.dao.findVo(userData.getCliId(), locId);
	}
	
	@Override public LocationVo findFullVo(Integer locId, UserData userData) throws CoreException {
		LocationVo result = this.dao.findVo(userData.getCliId(), locId);
		
		if (result != null) {
			result.setDataDefinitionVo(this.dataDefinitionDao.findVo(result.getDataDefId()));
			result.addGenerators(this.generatorsDao.findAll(result.getCliId(), result.getLocId()));
			result.addStations(this.stationsDao.findAll(result.getCliId(), result.getLocId()));
			result.addEstimations(this.locEstimationDao.findAll(result.getCliId(), result.getLocId()));
			result.setCountryVo(this.countryDao.findVo(result.getCtrId()));
		}
		
		return result;
	}
	
	@Override public void create(LocationVo vo, UserData userData) {
		vo.setCliId(userData.getCliId());
		this.dao.insert(vo);
	}
	
	@Override public void update(LocationVo vo, UserData userData) {
		LocationVo dbVo = this.dao.findVo(userData.getCliId(), vo.getLocId());
		
		dbVo.setLocCode(vo.getLocCode());
		dbVo.setLocName(vo.getLocName());
		dbVo.setLocAddress(vo.getLocAddress());
		dbVo.setLocCoordLat(vo.getLocCoordLat());
		dbVo.setLocCoordLng(vo.getLocCoordLng());
		dbVo.setLocOutputCapacity(vo.getLocOutputCapacity());
		dbVo.setLocOutputTotalCapacity(vo.getLocOutputTotalCapacity());
		dbVo.setLocReferenceDensity(vo.getLocReferenceDensity());
		dbVo.setDataDefId(vo.getDataDefId());
		
		this.dao.update(dbVo);
	}
	
	@Override public void delete(LocationVo vo, UserData userData) {
		LocationVo dbVo = this.dao.findVo(userData.getCliId(), vo.getLocId());
		
		this.dao.delete(dbVo);
	}

	@Override public Collection<LocationVo> getEstimations(UserData userData) {
		Collection<LocationVo> result = this.dao.findAllForUser(userData.getCliId(), userData.getUsrId(), false);
		
		if (CollectionUtil.notEmpty(result)) {
			for (LocationVo vo : result) {
				vo.addEstimations(this.locEstimationDao.findAll(vo.getCliId(), vo.getLocId()));
				while (CollectionUtil.size(vo.getEstimations()) < MAX_AMOUNT_ESTIMATIONS_PER_LOCATION) vo.add(new LocEstimationVo());
			}
		}
		
		return result;
	}

	@Override public Collection<LocationVo> setEstimations(List<Location> locations, UserData userData) {
		Collection<LocationVo> usrLocations = this.dao.findAllForUser(userData.getCliId(), userData.getUsrId(), false);
		
		if (CollectionUtil.notEmpty(locations) && CollectionUtil.notEmpty(usrLocations)) {
			Map<Integer, LocationVo> locationsById = usrLocations.stream().collect(Collectors.toMap(LocationVo::getLocId, Function.identity()));
			
			for (Location location : locations) {
				LocationVo locVo = locationsById.get(location.getId());
				if (locVo == null) continue;
				
				this.locEstimationDao.deleteAllFor(locVo.getCliId(), locVo.getLocId());
				this.locEstimationDao.synchronize(locVo.getEstimations());
			}
		}
		
		return this.getEstimations(userData);
	}
}
