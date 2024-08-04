package tech.renovus.solarec.inverters.brand.sma;

import java.security.KeyStore.Entry;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import tech.renovus.solarec.inverters.brand.sma.api.authorization.AuthResponse;
import tech.renovus.solarec.inverters.brand.sma.api.authorization.BcAuthorizeResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.Device;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.device.measurements.MeasurementsResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.Plant;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantDevicesResponse;
import tech.renovus.solarec.inverters.brand.sma.api.monitoring.plant.PlantsResponse;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;

public class FastTest {

	public static void main(String... args) {
		String clientId = "renovus_api";
		String clientSecret = "hlpkxuYaHrcqlemnXFsDDkI9qhNNQVDf";
		String resourceOwner = "apiTestUser@apiSandbox.com";
		boolean sandboxMode = true;
//		String plantId = "13";
//		String deviceId = "17";
		
		SmaInverterService service = new SmaInverterService();
		
		Map<Plant, Collection<Device>> allData = new HashMap<>();
		
		AuthResponse auth = service.retrieveToken(sandboxMode, clientId, clientSecret);
		System.out.println("Auth response: " + auth.getAccessToken());
		
		BcAuthorizeResponse authorize	= service.retrieveBcAutorizeToken(sandboxMode, auth, resourceOwner);
		System.out.println("Authroize: " + authorize.getState());
		
		if (SmaInverterService.AUTHORIZE_ACCEPTED.equals(authorize.getState())) {
			System.out.println("Plants");
			PlantsResponse plants = service.retrievePlants(sandboxMode, auth);
			
			for (Plant plant : plants.getPlants()) {
			
				System.out.println("Devices for plant: " + plant.getPlantId());
				PlantDevicesResponse devices = service.retrievePlantDevices(sandboxMode, auth, plant.getPlantId());
				
				allData.put(plant, devices.getDevices().stream().filter(x -> service.isInveter(x)).collect(Collectors.toList()));
			}
		}

		
		for (Map.Entry<Plant, Collection<Device>> entry : allData.entrySet()) {
			Plant plant = entry.getKey();
			Collection<Device> devices = entry.getValue();
			
			System.out.println("Plant: [" + plant.getPlantId() + "] " + plant.getName());
			for (Device device : devices) {
				System.out.println("\tDevice: [" + device.getDeviceId() + "] " + device.getName() + " - " + device.getType() + " - " + device.getIsActive());
				
//				System.out.println("\t\tData for device: " + device.getDeviceId());
//				MeasurementsResponse data = service.retrieveDeviceData(
//						sandboxMode, 
//						auth, 
//						device.getDeviceId(), 
//						SmaInverterService.SET_TYPE_ENERGY_AND_POWER_PV, 
//						SmaInverterService.PERIOD_DAY, 
//						"2024-02-10"
//					);
//				
//				System.out.println("\t\t\tError: " + data.getMessage());
//				System.out.println("\t\t\tCode: " + data.getCode());
//				System.out.println("\t\t\tSet type: " + data.getSetType());
//				System.out.println("\t\t\tAmount of data: " + CollectionUtil.size(data.getSet()));
			}
			
		}
	}
}
