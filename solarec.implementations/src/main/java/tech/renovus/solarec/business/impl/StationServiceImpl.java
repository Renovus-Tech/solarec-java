package tech.renovus.solarec.business.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.StationService;
import tech.renovus.solarec.db.data.dao.interfaces.DocStationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaStatisticDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.vo.db.data.StationVo;

@Service
public class StationServiceImpl implements StationService {

	//--- Resources -----------------------------
	@Resource StationDao stationDao;
	@Resource DocStationDao docStationDao;
	@Resource StaStatisticDao staStatisticDao;
	@Resource StaDataDao staDataDao;
	
	//--- Overridden methods ------------------------
	@Override public Collection<StationVo> findAll(Integer offset, Integer size, String name, UserData userData) throws CoreException {
		return this.stationDao.findAll(userData.getCliId());
	}
	
	@Override public StationVo findVo(Integer staId, UserData userData) throws CoreException {
		return this.stationDao.findVo(userData.getCliId(), staId);
	}
	
	@Override public void create(StationVo vo, UserData userData) {
		vo.setCliId(userData.getCliId());
		this.stationDao.insert(vo);
	}
	
	@Override public void update(StationVo vo, UserData userData) {
		StationVo dbVo = this.stationDao.findVo(userData.getCliId(), vo.getStaId());
		
		dbVo.setLocId(vo.getLocId());
		dbVo.setDataDefId(vo.getDataDefId());
		dbVo.setStaCode(vo.getStaCode());
		dbVo.setStaName(vo.getStaName());
		dbVo.setStaDescription(vo.getStaDescription());
		dbVo.setStaCoordLat(vo.getStaCoordLat());
		dbVo.setStaCoordLng(vo.getStaCoordLng());
		dbVo.setDataDefId(vo.getDataDefId());
		
		this.stationDao.update(dbVo);
	}
	
	@Override @Transactional public void delete(StationVo vo, UserData userData) {
		StationVo dbVo = this.stationDao.findVo(userData.getCliId(), vo.getStaId());
		
		if (dbVo == null) {
			return;
		}
		
		this.docStationDao.deleteAllForStation(dbVo.getCliId(), vo.getStaId());
		this.staStatisticDao.deleteAllForStation(dbVo.getCliId(), vo.getStaId());
		this.staDataDao.deleteAllForStation(dbVo.getCliId(), vo.getStaId());
		this.stationDao.delete(dbVo);
	}

	@Override public Collection<StationVo> findAllForLocation(Integer locId, UserData userData) throws CoreException {
		return this.stationDao.findAll(userData.getCliId(), locId);
	}
}
