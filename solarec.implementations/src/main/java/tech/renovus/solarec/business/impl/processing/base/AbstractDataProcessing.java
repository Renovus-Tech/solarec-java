package tech.renovus.solarec.business.impl.processing.base;

import java.io.File;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import tech.renovus.solarec.db.data.dao.interfaces.LocDataWeatherDao;
import tech.renovus.solarec.exceptions.ProcessingException;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataTypeVo;
import tech.renovus.solarec.vo.db.data.LocDataWeatherVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;
import tech.renovus.solarec.vo.db.data.StationVo;

public abstract class AbstractDataProcessing {

	//--- Public inner class --------------------
	public static final class DateRange {
		private long from	= -1;
		private long to		= -1;

		public void set(Date aDate) {
			if (aDate == null) return;
			this.from = this.from == -1 ? aDate.getTime() : Math.min(this.from, aDate.getTime());
			this.to = this.to == -1 ? aDate.getTime() : Math.max(this.to, aDate.getTime());
		}
		
		public Date getDateFrom() { return this.from == -1 ? null : new Date(this.from); }
		public Date getDateTo() { return this.to == -1 ? null : new Date(this.to); }
	}
	
	//--- Private constants ---------------------
	private static final int POPULATE_TIME_INCREMENT_MINUTES = 10;
	
	//--- Private properties --------------------
	private StringBuilder log;
	@Resource LocDataWeatherDao weatherDataDao;
	
	//--- Abstract methods ----------------------
	public abstract Collection<File> validateFile(ClientVo client, InputStream stream) throws ProcessingException;
	public abstract void prepareFor(ClientVo cliVo, InputStream stream);
	public abstract void process() throws ProcessingException;
	public abstract ClientVo generateDataToSave();
	public abstract boolean continueWithSatistics();
	
	//--- Protected methods ---------------------
	protected void logInfo(String msg) {
		this.log.append("[ ");
		this.log.append(System.currentTimeMillis());
		this.log.append("] [ INFO ] ");
		this.log.append(msg);
		this.log.append("\r\n");
	}
	
	protected void logWarning(String msg) {
		this.log.append("[ ");
		this.log.append(System.currentTimeMillis());
		this.log.append("] [ WARN ] ");
		this.log.append(msg);
		this.log.append("\r\n");
	}
	
	protected void logError(String msg) {
		this.log.append("[ ");
		this.log.append(System.currentTimeMillis());
		this.log.append("] [ ERROR ] ");
		this.log.append(msg);
		this.log.append("\r\n");
	}
	
	protected void initLog() {
		this.log = new StringBuilder();
		this.logInfo("Start at: " + DateUtil.formatDateTime(new Date(), DateUtil.FMT_MILITAR));
	}
	
	protected void endLog() {
		this.logInfo("Ended at: " + DateUtil.formatDateTime(new Date(), DateUtil.FMT_MILITAR));
	}
	
	//--- Fix data methods ----------------------
	private void copyStationDataWithMeteo(StationVo stationVo, int dataTypeIdFrom, int dataTypeIdTo) {
		if (CollectionUtil.isEmpty(stationVo.getDatas()))	return;
		if (CollectionUtil.size(stationVo.getDatas()) == 1)	return;
		
		List<StaDataVo> datas = stationVo
			.getDatas()
			.stream()
			.filter(x -> x.getDataTypeId().intValue() == dataTypeIdFrom)
			.collect(Collectors.toList());

		for (StaDataVo aData : datas) {
			StaDataVo newData = aData.copy();
			newData.setSyncType(StaDataVo.SYNC_INSERT);
			newData.setDataTypeId(dataTypeIdTo);
			
			if (! stationVo.getDatas().contains(newData)) 
				stationVo.add(newData);
		}
	}
	
	protected void fixSatationDataWithMeteo(StationVo stationVo, Date dateFrom, Date dateTo) throws ProcessingException {
		this.copyStationDataWithMeteo(stationVo, DataTypeVo.TYPE_STATION_TEMPERATURE, DataTypeVo.TYPE_STATION_WEATHER_TEMPERATURE);
		this.copyStationDataWithMeteo(stationVo, DataTypeVo.TYPE_STATION_PRESSURE, DataTypeVo.TYPE_STATION_WEATHER_PRESSURE);
		this.copyStationDataWithMeteo(stationVo, DataTypeVo.TYPE_STATION_HUMIDITY, DataTypeVo.TYPE_STATION_WEATHER_HUMIDITY);
	
		this.populateStationDataWithMeteo(stationVo, DataTypeVo.TYPE_STATION_TEMPERATURE, DataTypeVo.TYPE_STATION_WEATHER_TEMPERATURE, DataTypeVo.TYPE_WEATHER_AIR_TEMPERATURE_100, dateFrom, dateTo);
		this.populateStationDataWithMeteo(stationVo, DataTypeVo.TYPE_STATION_PRESSURE, DataTypeVo.TYPE_STATION_WEATHER_PRESSURE, DataTypeVo.TYPE_WEATHER_ATMOSPHERE_PRESURE, dateFrom, dateTo);
		this.populateStationDataWithMeteo(stationVo, DataTypeVo.TYPE_STATION_HUMIDITY, DataTypeVo.TYPE_STATION_WEATHER_HUMIDITY, DataTypeVo.TYPE_WEATHER_HUMIDITY, dateFrom, dateTo);
	}
	
	private void populateStationDataWithMeteo(StationVo stationVo, int dataTypeIdFrom, int dataTypeIdTo, int locWeaDataTypeId, Date dateFrom, Date dateTo) throws ProcessingException {
		SimpleDateFormat formatter	= new SimpleDateFormat("yyyyMMddHHmm");
		Calendar calendar			= GregorianCalendar.getInstance();
		
		List<String> dates			= stationVo == null || stationVo.getDatas() == null ? new ArrayList<>() : stationVo
			.getDatas()
			.stream()
			.filter(x -> x.getDataTypeId().intValue() == dataTypeIdFrom || x.getDataTypeId().intValue() == dataTypeIdTo)
			.map(x -> formatter.format(x.getDataDate()))
			.sorted()
			.collect(Collectors.toList());

		calendar.setTime(dateFrom);
		
		StaDataVo sampleData	= CollectionUtil.isEmpty(stationVo.getDatas()) ? null : stationVo.getDatas().iterator().next();
		if (sampleData == null) {
			sampleData = new StaDataVo();
			sampleData.setCliId(stationVo.getCliId());
			sampleData.setStaId(stationVo.getStaId());
			sampleData.setDataDate(dateFrom);
			sampleData.setDataTypeId(dataTypeIdTo);
		}
		
		Collection<String> allTimes = new ArrayList<>();
		while (calendar.getTimeInMillis() <= dateTo.getTime()) {
			allTimes.add(formatter.format(calendar.getTime()));
			calendar.add(Calendar.MINUTE, POPULATE_TIME_INCREMENT_MINUTES);
		}
		
		allTimes.removeAll(dates);
		
		if (CollectionUtil.isEmpty(allTimes)) return;
		
		Collection<LocDataWeatherVo> locWeatherData = this.weatherDataDao.getForPeriod(stationVo.getCliId(), stationVo.getLocId(), Integer.valueOf(locWeaDataTypeId), dateFrom, dateTo);
		locWeatherData = locWeatherData == null ? null : locWeatherData
				.stream()
				.sorted(Comparator.comparing(LocDataWeatherVo::getDataDate))
				.collect(Collectors.toList());
		
		try {
			for (String aTime : allTimes) {
				Date aDate			= formatter.parse(aTime);
				Double value		= this.getDataValue(locWeatherData, aDate, dataTypeIdFrom);
				
				if (value == null) continue;
				
				StaDataVo newData	= sampleData.copy();
				newData.setDataDate(aDate);
				newData.setDataTypeId(dataTypeIdTo);
				newData.setDataValue(value);
				newData.setSyncType(StaDataVo.SYNC_INSERT);
				
				stationVo.add(newData);
			}
		} catch (ParseException e) {
			throw new ProcessingException(e);
		}
	}
	
	private Double getDataValue(Collection<LocDataWeatherVo> weatherDatas, Date dateExpected, int dataTypeIdFrom) {
		if (CollectionUtil.isEmpty(weatherDatas)) return null;
		
		List<LocDataWeatherVo> oldData = weatherDatas
			.stream()
			.filter(x -> ! x.getDataDate().after(dateExpected))
			.sorted(Comparator.comparing(LocDataWeatherVo::getDataDate))
			.collect(Collectors.toList());
		
		return CollectionUtil.notEmpty(oldData) ? oldData.get(oldData.size() -1).getDataValue() : null;
	}
	//--- Public methods ------------------------
	public String getLog() { return this.log.toString(); }
}
