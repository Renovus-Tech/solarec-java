package tech.renovus.solarec.inverters.common;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.vo.db.data.CliGenAlertVo;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;

public interface InverterService {

	//--- Inner classes -------------------------
	public static class InverterData {
		private Collection<GenDataVo> generatorData;
		private Collection<StaDataVo> stationData;
		private Collection<CliLocAlertVo> locationAlerts;
		private Collection<CliGenAlertVo> generatorAlerts;
		
		public InverterData( Collection<GenDataVo> generatorData, Collection<StaDataVo> stationData) {
			this.generatorData = generatorData;
			this.stationData = stationData;
		}
		
		public InverterData( Collection<GenDataVo> generatorData, Collection<StaDataVo> stationData, Collection<CliLocAlertVo> locationAlerts, Collection<CliGenAlertVo> generatorAlerts) {
			this.generatorData = generatorData;
			this.stationData = stationData;
			this.locationAlerts = locationAlerts;
			this.generatorAlerts = generatorAlerts;
		}

		public void add(GenDataVo vo) {
			if (this.generatorData == null) this.generatorData = new ArrayList<>(1);
			this.generatorData.add(vo);
		}
		
		public void add(StaDataVo vo) {
			if (this.stationData == null) this.stationData = new ArrayList<>(1);
			this.stationData.add(vo);
		}
		
		public void add(CliLocAlertVo vo) {
			if (this.locationAlerts == null) this.locationAlerts = new ArrayList<>(1);
			this.locationAlerts.add(vo);
		}		
		
		public void add(CliGenAlertVo vo) {
			if (this.generatorAlerts == null) this.generatorAlerts = new ArrayList<>(1);
			this.generatorAlerts.add(vo);
		}
		public Collection<GenDataVo> getGeneratorData() {
			return generatorData;
		}

		public Collection<StaDataVo> getStationData() {
			return stationData;
		}

		public Collection<CliLocAlertVo> getLocationAlerts() {
			return locationAlerts;
		}

		public Collection<CliGenAlertVo> getGeneratorAlerts() {
			return generatorAlerts;
		}
	}
	
	public static class InveterServiceException extends Exception {
		private static final long serialVersionUID = -5828658862424955323L;
		
		public InveterServiceException() { super(); }
		public InveterServiceException(String msg) { super(msg); }
		public InveterServiceException(Exception parent) { super(parent); }
		public InveterServiceException(String msg, Exception parent) { super(msg, parent); }
	}

	//--- Interface methods ---------------------
	void prepareFor(ClientVo client);
	boolean canRetrieve();
	boolean continueWithStats();
	String getReasonWhyCantRetrieve();
	InverterData retrieveData() throws InveterServiceException;
}
