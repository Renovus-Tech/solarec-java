package tech.renovus.solarec.business.impl.processing.base;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.business.ProcessingException;
import tech.renovus.solarec.db.data.dao.interfaces.DataProcessingDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.db.data.dao.interfaces.StaDataDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;

public class FixStationDataWithMeteo extends AbstractDataProcessing {

	//--- Private properties --------------------
	@Autowired protected DataProcessingDao dataProcessingDao;
	@Autowired protected StationDao stationDao;
	@Autowired protected LocationDao locationDDao;
	@Autowired protected StaDataDao staDataDao;
	
	private ClientVo cliVo;
	
	
	//--- Overridden methods --------------------
	@Override public boolean continueWithSatistics() { return true; }

	@Override public Collection<File> validateFile(ClientVo client, InputStream stream) throws ProcessingException {
		return null;
	}

	@Override public void prepareFor(ClientVo cliVo, InputStream stream) {
		super.initLog();
		this.cliVo = cliVo;
	}

	@Override public void process() throws ProcessingException {
		Integer dataProId = Integer.valueOf(2228);
		
		Date dateFrom	= this.dataProcessingDao.getDate(this.cliVo.getCliId(), dataProId, true);
		Date dateTo		= this.dataProcessingDao.getDate(this.cliVo.getCliId(), dataProId, false);

		if (dateFrom == null || dateTo == null) throw new ProcessingException("Can't find date range to validate.");
		this.cliVo.setLocations(this.locationDDao.findAll(this.cliVo.getCliId()));
		
		
		for (LocationVo locVo : this.cliVo.getLocations()) {
			if (! LocationVo.TYPE_WIND.equals(locVo.getLocType())) continue;
			
			locVo.setStations(this.stationDao.findAll(locVo.getCliId(), locVo.getLocId()));
			
			for (StationVo staVo : locVo.getStations()) {
				Collection<StaDataVo> datas = new ArrayList<>();
				staVo.setDatas(datas);
				CollectionUtil.addAll(
						datas, 
						this.staDataDao.getStaDataForDatePeriod(
								staVo.getCliId(), 
								staVo.getStaId(), 
								dateFrom, 
								dateTo, 
								DataTypeVo.TYPE_STATION_TEMPERATURE, 
								DataTypeVo.TYPE_STATION_PRESSURE, 
								DataTypeVo.TYPE_STATION_HUMIDITY,
								DataTypeVo.TYPE_STATION_WEATHER_TEMPERATURE,
								DataTypeVo.TYPE_STATION_WEATHER_PRESSURE,
								DataTypeVo.TYPE_STATION_WEATHER_HUMIDITY
							)
					);
				
				this.fixSatationDataWithMeteo(
						staVo, 
						dateFrom,
						dateTo
					);
			}
			for (StationVo staVo : locVo.getStations()) {
				this.logInfo("Amount of data for station " + staVo.getStaCode() + ": " + CollectionUtil.size(staVo.getDatas().stream().filter(x -> x.getSyncType() == StaDataVo.SYNC_INSERT).collect(Collectors.toList())));
			}
		}
		
		
		this.endLog();
	}

	@Override public ClientVo generateDataToSave() {
		return this.cliVo;
	}
}
