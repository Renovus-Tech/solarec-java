package tech.renovus.solarec.inverters.common;

import java.util.Collection;

import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GenDataVo;
import tech.renovus.solarec.vo.db.data.StaDataVo;

public interface InverterService {

	public static class InverterData {
		private Collection<GenDataVo> generatorData;
		private Collection<StaDataVo> stationData;
		
		public InverterData( Collection<GenDataVo> generatorData, Collection<StaDataVo> stationData) {
			this.generatorData = generatorData;
			this.stationData = stationData;
		}

		public Collection<GenDataVo> getGeneratorData() {
			return generatorData;
		}

		public Collection<StaDataVo> getStationData() {
			return stationData;
		}
	}
	

	public static class InveterServiceException extends Exception {
		
		private static final long serialVersionUID = -5828658862424955323L;
		
		public InveterServiceException() { super(); }
		public InveterServiceException(String msg) { super(msg); }
		public InveterServiceException(Exception parent) { super(parent); }
		public InveterServiceException(String msg, Exception parent) { super(msg, parent); }
	}

	void prepareFor(ClientVo client);
	boolean canRetrieve();
	boolean continueWithStats();
	String getReasonWhyCantRetrieve();
	InverterData retrieveData() throws InveterServiceException;
}
