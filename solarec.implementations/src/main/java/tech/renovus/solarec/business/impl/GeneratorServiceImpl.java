package tech.renovus.solarec.business.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.GeneratorService;
import tech.renovus.solarec.db.data.dao.interfaces.DocGeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenNeighbourDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenPowerDao;
import tech.renovus.solarec.db.data.dao.interfaces.GenStatisticDao;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.GenNeighbourVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.rest.entity.Generator;
import tech.renovus.solarec.vo.rest.entity.Location;

@Service
public class GeneratorServiceImpl implements GeneratorService {

	//--- Resources -----------------------------
	@Resource GeneratorDao dao;
	@Resource DocGeneratorDao docGeneratorDao;
	@Resource GenStatisticDao genStatisticDao;
	@Resource GenNeighbourDao genNeighbourDao;
	@Resource GenDataDao genDataDao;
	@Resource GenPowerDao genPowerDao;
	@Resource LocationDao locationDao;
	
	//--- Overridden methods ------------------------
	@Override public Collection<GeneratorVo> findAll(Integer offset, Integer size, String name, UserData userData) throws CoreException {
		return this.dao.findAll(userData.getCliId());
	}
	
	@Override public GeneratorVo findFullVo(Integer locId, UserData userData) throws CoreException {
		GeneratorVo vo = this.dao.findVo(userData.getCliId(), locId);
		
		if (vo != null) {
			vo.setPowerCurve(this.genPowerDao.getAllFor(vo.getCliId(), vo.getGenId()));
		}
		
		return vo;
	}
	
	@Override public GeneratorVo create(GeneratorVo vo, UserData userData) {
		vo.setCliId(userData.getCliId());
		this.dao.insert(vo);
		return vo;
	}
	
	@Override @Transactional public GeneratorVo update(GeneratorVo vo, UserData userData) {
		GeneratorVo dbVo = this.dao.findVo(userData.getCliId(), vo.getGenId());
		dbVo.setPowerCurve(this.genPowerDao.getAllFor(dbVo.getCliId(), dbVo.getGenId()));
		
		dbVo.setLocId(vo.getLocId());
		dbVo.setDataDefId(vo.getDataDefId());
		dbVo.setGenCode(vo.getGenCode());
		dbVo.setGenName(vo.getGenName());
		dbVo.setGenDescription(vo.getGenDescription());
		dbVo.setGenCoordLat(vo.getGenCoordLat());
		dbVo.setGenCoordLng(vo.getGenCoordLng());
		dbVo.setGenBrand(vo.getGenBrand());
		dbVo.setGenModel(vo.getGenModel());
		dbVo.setGenSerialNum(vo.getGenSerialNum());
		dbVo.setGenRatePower(vo.getGenRatePower());
		dbVo.setDataDefId(vo.getDataDefId());
		
		dbVo.setChildrensId();
		dbVo.synchronizeForce(GeneratorVo.SYNC_DELETE);
		this.genPowerDao.synchronize(dbVo.getPowerCurve());
		
		this.dao.update(dbVo);
		
		dbVo.setPowerCurve(vo.getPowerCurve());
		dbVo.setChildrensId();
		dbVo.synchronizeForce(GeneratorVo.SYNC_INSERT);
		this.genPowerDao.synchronize(dbVo.getPowerCurve());
		
		return dbVo;
	}
	
	@Override @Transactional public void delete(GeneratorVo vo, UserData userData) {
		GeneratorVo dbVo = this.dao.findVo(userData.getCliId(), vo.getGenId());
		
		if (dbVo == null) return;
		
		this.docGeneratorDao.deleteAllForGenerator(dbVo.getCliId(), vo.getGenId());
		this.genStatisticDao.deleteAllForGenerator(dbVo.getCliId(), vo.getGenId());
		this.genDataDao.deleteAllForGenerator(dbVo.getCliId(), vo.getGenId());
		this.dao.delete(dbVo);
	}

	@Override public GeneratorVo findVo(Integer genId, UserData userData) throws CoreException {
		return this.dao.findVo(userData.getCliId(), genId);
	}

	@Override public Collection<GeneratorVo> findAllForLocation(Integer locId, UserData userData) throws CoreException {
		return this.dao.findAll(userData.getCliId(), locId);
	}

	@Override public Collection<LocationVo> getNeighbors(UserData userData) {
		Collection<LocationVo> locations		= this.locationDao.findAll(userData.getCliId());

		if (CollectionUtil.notEmpty(locations)) {
			Map<Integer, LocationVo> mapLocations	= locations.stream().collect(Collectors.toMap(LocationVo::getLocId, Function.identity()));
			
			Collection<GeneratorVo> generators		= this.dao.findAll(userData.getCliId());
			if (CollectionUtil.notEmpty(generators)) {
				Map<Integer, GeneratorVo> mapGenerators	= generators.stream().collect(Collectors.toMap(GeneratorVo::getGenId, Function.identity()));
				
				Collection<GenNeighbourVo> neighbours	= this.genNeighbourDao.getAllFor(userData.getCliId());
				if (CollectionUtil.notEmpty(neighbours)) {
					for (GenNeighbourVo vo : neighbours) {
						mapGenerators.get(vo.getGenId()).add(vo);
					}
				}
				
				for (GeneratorVo genVo : generators) {
					mapLocations.get(genVo.getLocId()).add(genVo);
				}
			}
			
		}
		
		return locations;
	}

	@Override public Collection<LocationVo> setNeighbors(List<Location> locations, UserData userData) {
		Collection<GeneratorVo> dbGenerators	= this.dao.findAll(userData.getCliId());
		Map<Integer, GeneratorVo> map			= dbGenerators.stream().collect(Collectors.toMap(GeneratorVo::getGenId, Function.identity()));
		
		if (CollectionUtil.notEmpty(locations)) {
			for (Location location : locations) {
				if (CollectionUtil.notEmpty(location.getGenerators())) { 
					for (Generator gen : location.getGenerators()) {
						if (CollectionUtil.notEmpty(gen.getNeighbors())) {
							GeneratorVo genVo = map.get(gen.getId());
							if (genVo == null) continue;
							
							for (Integer genId : gen.getNeighbors()) {
								if (! map.containsKey(genId)) continue;
								if (ClassUtil.equals(genId, genVo.getGenId())) continue;
								genVo.add(new GenNeighbourVo(genVo, genId, CollectionUtil.size(genVo.getNeighbours())));
								
								if (CollectionUtil.size(genVo.getNeighbours()) == 2) break;
							}
							
							this.genNeighbourDao.deleteAllFor(genVo.getCliId(), genVo.getGenId());
							this.genNeighbourDao.synchronize(genVo.getNeighbours());
						}
					}
				}
			}
		}
		
		return this.getNeighbors(userData);
	}
}
