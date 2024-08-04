package tech.renovus.solarec.inverters.brand.fronius;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tech.renovus.solarec.inverters.brand.fronius.api.aggregated.specific.AggregatedSpecificDate;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.Channel;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.Datum;
import tech.renovus.solarec.inverters.brand.fronius.api.history.data.HistoryDataResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.DeviceListResponse;
import tech.renovus.solarec.inverters.brand.fronius.api.metadata.PvSystemsListResponse;
import tech.renovus.solarec.vo.db.data.DataDefParameterVo;
import tech.renovus.solarec.vo.db.data.GenDataDefParameterVo;

public class FastTest {

	private static GenDataDefParameterVo createParameter(String paramName, String paramValue) {
		DataDefParameterVo paramVo = new DataDefParameterVo();
		paramVo.setDataDefParName(paramName);
		
		GenDataDefParameterVo result = new GenDataDefParameterVo();
		result.setGenDataDefParValue(paramValue);
		result.setDataDefParameter(paramVo);
		
		return result;
	}
	
	public static void main(String... args) {
		String accessKeyId = "FKIA109022C0C41D4DD58BAEAF387E9A0C00";
		String accessKeyValue = "58911d3a-b7d0-41f1-97b5-cf11b1c7fcf9";
		boolean betaMode = false;
//		String pvSystemsIdToUse = "20bb600e-019b-4e03-9df3-a0a900cda689"; //beta
//		pv system id> 370643da-db44-4515-af25-2e35893c5d17
//		device id> 2e303432-3934-3830-3238-000000000000
		FroniusInverterService service = new FroniusInverterService();

		PvSystemsListResponse pvSystemListResponse = service.getPvSystemsList(betaMode, accessKeyId, accessKeyValue);
		
		if (pvSystemListResponse.hasError()) {
			System.out.println("Error code: " + pvSystemListResponse.getResponseError());
			System.out.println("Message: " + pvSystemListResponse.getResponseMessage());
			System.exit(-1);
		}
		
		System.out.println("List of available systems");
		pvSystemListResponse.getPvSystemIds().forEach(System.out::println);
		String pvSystemsIdToUse = pvSystemListResponse.getPvSystemIds().iterator().next();
		System.out.println("System to use: " + pvSystemsIdToUse);

		DeviceListResponse pvSystemDeviecsListResponse = service.getPvSystemDevicesList(betaMode, accessKeyId, accessKeyValue, pvSystemsIdToUse);
		
		if (pvSystemDeviecsListResponse == null) {
			System.out.println("No available devices");
			return;
		}
		
		System.out.println("List of available devices in system");
		pvSystemDeviecsListResponse.getDeviceIds().forEach(System.out::println);
		String pvDeviceIdTouse = pvSystemDeviecsListResponse.getDeviceIds().iterator().next();
		System.out.println("Device to use: " + pvDeviceIdTouse);
		
		Date dateToSearch = new Date();
		int amountData = 0;
		int amountTrys = 0;

		
		do {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateToSearch);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.AM_PM, Calendar.AM);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			
			Date from = cal.getTime();
			
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			cal.add(Calendar.DAY_OF_YEAR, 1);
			cal.add(Calendar.MILLISECOND, -1);
			
			Date to = cal.getTime();
			
			System.out.println("Getting aggregated data for: " + pvSystemsIdToUse);
			System.out.println("Year: " + year);
			System.out.println("Month: " + month);
			System.out.println("Day: " + day);
			
			AggregatedSpecificDate aggregatedData = service.getPvSystemsAggredatedDataSpecificDate(betaMode, accessKeyId, accessKeyValue, pvSystemsIdToUse, year, month, day);
			if (aggregatedData != null) {
				if (aggregatedData.hasError()) {
					System.out.println("Error code: " + aggregatedData.getResponseError());
					System.out.println("Message: " + aggregatedData.getResponseMessage());
					System.exit(-1);
				}
				amountData = aggregatedData.getData().getChannels().size();
				System.out.println("Aggregated data retrieved: " + amountData);
				aggregatedData.getData().getChannels().forEach(x -> System.out.println(x.getChannelName() + " - " + x.getValues().getAdditionalProperties().get(Integer.toString(day)) + " " + x.getUnit()));
			}
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
			System.out.println("Getting history data for: " + pvSystemsIdToUse);
			System.out.println("From: " + formatter.format(from));
			System.out.println("To: " + formatter.format(to));
			
			HistoryDataResponse historyData = service.getPvSystemsHistData(betaMode,accessKeyId, accessKeyValue, pvSystemsIdToUse, from, to);
			if (historyData != null) {
				if (historyData.hasError()) {
					System.out.println("Error code: " + historyData.getResponseError());
					System.out.println("Message: " + historyData.getResponseMessage());
					System.exit(-1);
				}
	
				amountData += historyData.getData().size();
				System.out.println("History data retrieved: " + amountData);
				for (Datum aData : historyData.getData()) {
					for (Channel aChannel : aData.getChannels()) {
						if (! "EnergyProductionTotal".equals(aChannel.getChannelName())) continue;
						Integer orgValue = aChannel.getValue();
						Double finalValue = orgValue == null ? null : Double.valueOf(aChannel.getValue().intValue() / (double) 1000);
						String time = aData.getLogDateTime();
						
						System.out.println(time + " - " + orgValue + " - " + finalValue);
					}
				}
//				historyData.getData().forEach(x -> System.out.println(x.getLogDateTime()));
			}

//			DeviceHistoryDataResponse deviceHistoryData = service.getPvSystemsDeviceHistData(betaMode, accessKeyId, accessKeyValue, pvSystemsIdToUse, pvDeviceIdTouse, from, to);
//			if (deviceHistoryData != null) {
//				if (deviceHistoryData.hasError()) {
//					System.out.println("Error code: " + deviceHistoryData.getResponseError());
//					System.out.println("Message: " + deviceHistoryData.getResponseMessage());
//					System.exit(-1);
//				}
//				
//				amountData += deviceHistoryData.getData().size();
//				System.out.println("Device History data retrieved: " + amountData);
//				deviceHistoryData.getData().forEach(x -> System.out.println(x.getLogDateTime()));
//			}
			
			amountTrys ++;
			dateToSearch = from;
		} while (amountData == 0 && amountTrys < 200);
	}
}
