package tech.renovus.solarec.inverters.brand.fimer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import tech.renovus.solarec.inverters.brand.fimer.api.authenticate.AuthenticateResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.datalogger.IpRangeDataloggerResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.ipRanges.web.IpRangeWebResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.organization.OrganizationResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.status.StatusResponse;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.energy.timeseries.Result;
import tech.renovus.solarec.inverters.brand.fimer.api.telemetryData.energy.timeseries.TelemetryDataEnergyTimeseriesResponse;
import tech.renovus.solarec.util.CollectionUtil;

public class FastTest {

	public static void main(String... args) {
		FimerInverterService service = new FimerInverterService();
		
		StatusResponse status = service.status();
		if (status == null) {
			System.out.println("No status available");
		} else {
			System.out.println("Status: " + status.getResult());
		}
		
		String user = "gerardo.lapetina";
		String password = "Fimermx1!";
		String key = "23651c4c-ea24-4dbf-841c-f51fc82c47d1-0c81";
		
		AuthenticateResponse authentication = service.authenticate(user, password, key);
		System.out.println("Auth key: " + authentication.getResult());
		
		String authKey = authentication.getResult();
		
//		IpRangeDataloggerResponse ipRangeDataLogger = service.getIpRangeDatalogger(authKey);
//		System.out.println("Has ip range data logger: " + (ipRangeDataLogger != null));
//		if (ipRangeDataLogger != null) {
//			ipRangeDataLogger.getResult().getPrefixes().forEach(System.out::println);
//		}
		
//		IpRangeWebResponse ipRangeDataWeb = service.getIpRangeWeb(authKey);
//		System.out.println("Has ip range web: " + (ipRangeDataWeb != null));
//		if (ipRangeDataWeb != null) {
//			ipRangeDataWeb.getResult().getPrefixes().forEach(System.out::println);
//		}
		
//		OrganizationResponse organization = service.getPortafolioGroup(authKey);
//		System.out.println("Has organization: " + (organization != null));
//		if (organization != null) {
//			System.out.println(organization.getResult().getPortfolioGroupName());
//		}
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.AM_PM, Calendar.AM);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, Calendar.JULY );
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		Date from = cal.getTime();
		
		cal.add(Calendar.DAY_OF_YEAR, 1);
		Date to = cal.getTime();
		
		TelemetryDataEnergyTimeseriesResponse dataPower = service.getTelemetryDataPowerTimeseries(authKey, 20792082, from, to, "Americas/Uruguay" );
		TelemetryDataEnergyTimeseriesResponse dataEnergy = service.getTelemetryDataEnergyTimeseries(authKey, 20792082, from, to, "Americas/Uruguay" );
		
		System.out.println("Has data power: " + dataPower != null);
		System.out.println("Has data energy: " + dataEnergy != null);
		
		System.out.println("Amount of data power: " + CollectionUtil.size(dataPower == null ? null : dataPower.getResult()));
		System.out.println("Amount of data energy: " + CollectionUtil.size(dataEnergy == null ? null : dataEnergy.getResult()));
		
		if (dataPower != null && CollectionUtil.notEmpty(dataPower == null ? null : dataPower.getResult())) {
			int count = 0;
			System.out.println("Sample data power");
			for (Result aData : dataPower.getResult()) {
				Date dataDate = new Date(aData.getStart().intValue() * 1000);
				
				System.out.println(dataDate + " - " + aData.getValue());
				
				count ++;
				
				if (count > 10) {
					break;
				}
			}
		}
		
		if (dataEnergy != null && CollectionUtil.notEmpty(dataEnergy == null ? null : dataEnergy.getResult())) {
			int count = 0;
			System.out.println("Sample data energy");
			for (Result aData : dataEnergy.getResult()) {
				Date dataDate = new Date(aData.getStart().intValue() * 1000);
				
				System.out.println(dataDate + " - " + aData.getValue());
				
				count ++;
				
				if (count > 10) {
					break;
				}
			}
		}
	}
}
