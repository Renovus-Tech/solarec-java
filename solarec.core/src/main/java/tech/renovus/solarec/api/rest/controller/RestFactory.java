package tech.renovus.solarec.api.rest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.SecurityService;
import tech.renovus.solarec.util.BooleanUtils;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.util.interfaces.ISetting;
import tech.renovus.solarec.vo.comparator.GeneratorGenCodeAsNumberComparator;
import tech.renovus.solarec.vo.db.data.CliDataDefTriggerVo;
import tech.renovus.solarec.vo.db.data.CliLocAlertVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.db.data.DocTypeVo;
import tech.renovus.solarec.vo.db.data.DocumentVo;
import tech.renovus.solarec.vo.db.data.FunctionalityVo;
import tech.renovus.solarec.vo.db.data.GenNeighbourVo;
import tech.renovus.solarec.vo.db.data.GenPowerVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocWeatherDataVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.RepTypeVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.vo.db.data.WeaDefinitionVo;
import tech.renovus.solarec.vo.rest.background.Processing;
import tech.renovus.solarec.vo.rest.entity.Alert;
import tech.renovus.solarec.vo.rest.entity.Client;
import tech.renovus.solarec.vo.rest.entity.DataDefinition;
import tech.renovus.solarec.vo.rest.entity.DataDefinitionTrigger;
import tech.renovus.solarec.vo.rest.entity.DocType;
import tech.renovus.solarec.vo.rest.entity.Document;
import tech.renovus.solarec.vo.rest.entity.Functionality;
import tech.renovus.solarec.vo.rest.entity.Generator;
import tech.renovus.solarec.vo.rest.entity.Location;
import tech.renovus.solarec.vo.rest.entity.Report;
import tech.renovus.solarec.vo.rest.entity.Setting;
import tech.renovus.solarec.vo.rest.entity.Station;
import tech.renovus.solarec.vo.rest.entity.User;
import tech.renovus.solarec.vo.rest.weather.WeatherDefinition;

public class RestFactory {

	//--- Collection public static methods-------
	public static List<Processing> convertProcessings(Collection<DataProcessingVo> vos) {
		List<Processing> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (DataProcessingVo vo : vos) result.add(convert(vo));
		
		return result;
	}

	public static List<DataDefinition> convertDataDefinitions(Collection<DataDefinitionVo> vos) {
		List<DataDefinition> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (DataDefinitionVo vo : vos) result.add(convert(vo));
		
		return result;
	}
	
	public static List<Location> convertLocations(Collection<LocationVo> vos) {
		List<Location> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (LocationVo vo : vos) result.add(convert(vo));
		
		return result;
	}
	
	public static List<Station> convertStations(Collection<StationVo> vos) {
		List<Station> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (StationVo vo : vos) result.add(convert(vo));
		
		return result;
	}
	
	public static List<Generator> convertGenerators(Collection<GeneratorVo> vos) {
		List<Generator> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (GeneratorVo vo : vos) result.add(convert(vo));
		
		return result;
	}
	
	public static List<Functionality> convertFunctionalities(Collection<FunctionalityVo> vos) {
		List<Functionality> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (FunctionalityVo vo : vos) result.add(convert(vo));
		
		return result;
	}
	
	public static <T extends ISetting> Collection<Setting> convertSettings(Collection<T> vos) {
		List<Setting> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (ISetting vo : vos) result.add(convert(vo));
		
		return result;
	}
	
	public static List<Client> convertClients(Collection<ClientVo> vos) {
		List<Client> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (ClientVo vo : vos) result.add(convert(vo));
		
		return result;
	}
	
	public static List<DocType> convertDocTypes(Collection<DocTypeVo> vos) {
		List<DocType> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (DocTypeVo vo : vos) result.add(convert(vo));
		
		return result;
	}
	
	public static List<Document> convertDocuments(Collection<DocumentVo> vos) {
		List<Document> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (DocumentVo vo : vos) result.add(convert(vo));
		
		return result;
	}

	public static List<Generator.Power> convertPower(Collection<GenPowerVo> vos) {
		List<Generator.Power> result = new ArrayList<>(CollectionUtil.size(vos));
		
		if (CollectionUtil.notEmpty(vos)) for (GenPowerVo vo : vos) result.add(convert(vo));
		
		return result;
	}
	
	public static List<WeatherDefinition> convertWeatherDefinitions(Collection<WeaDefinitionVo> vos) {
		List<WeatherDefinition> result = new ArrayList<>(CollectionUtil.size(vos));
		if (CollectionUtil.notEmpty(vos)) for (WeaDefinitionVo vo : vos) result.add(convert(vo));
		return result;
	}
	
	public static Collection<GenPowerVo> convertRestPowerCurve(Collection<Generator.Power> vos) {
		Collection<GenPowerVo> result = new ArrayList<>(CollectionUtil.size(vos));
		if (CollectionUtil.notEmpty(vos)) for (Generator.Power vo : vos) result.add(convert(vo));
		return result;
	}
	
	public static List<Report> convertReportTypes(Collection<RepTypeVo> vos) {
		List<Report> result = new ArrayList<>(CollectionUtil.size(vos));
		if (CollectionUtil.notEmpty(vos)) for (RepTypeVo vo : vos) result.add(convert(vo));
		return result;
	}
	
	public static List<Alert> convertAlerts(Collection<CliLocAlertVo> vos) {
		List<Alert> result = new ArrayList<>(CollectionUtil.size(vos));
		if (CollectionUtil.notEmpty(vos)) for (CliLocAlertVo vo : vos) result.add(convert(vo));
		return result;
	}

	//--- VO to JSON methods --------------------
	public static Location convert(LocationVo vo) {
		if (vo == null) return null;
		
		Location result = new Location();
		
		result.setId(vo.getLocId());
		result.setCode(vo.getLocCode());
		result.setName(vo.getLocName());
		result.setAddress(vo.getLocAddress());
		result.setState(vo.getLocState());
		result.setCountry(vo.getLocCountry());
		result.setCountryAlpha2(vo.getLocCountryAlpha2());
		result.setLatitude(vo.getLocCoordLat());
		result.setLongitude(vo.getLocCoordLng());
		result.setOutputCapacity(vo.getLocOutputCapacity());
		result.setOutputTotalCapacity(vo.getLocOutputTotalCapacity());
		result.setReferenceDensity(vo.getLocReferenceDensity());
		result.setDataDefinitionId(vo.getDataDefId());
		result.setType(vo.getLocType());
		result.setDemoDate(vo.getLocDemoDate());
		
		result.setDataDefinition(convert(vo.getDataDefinitionVo()));
		
		if (CollectionUtil.notEmpty(vo.getGenerators())) {
			Collection<GeneratorVo> sorted = new TreeSet<>(GeneratorGenCodeAsNumberComparator.getInstance());
			CollectionUtil.addAll(sorted, vo.getGenerators());
			for (GeneratorVo genVo : sorted) {
				result.add(convert(genVo));
			}
		}
		
		if (CollectionUtil.notEmpty(vo.getStations()))		for (StationVo staVo : vo.getStations())			result.add(convert(staVo));
		
		return result;
	}
	
	public static Generator convert(GeneratorVo vo) {
		if (vo == null) return null;
		
		Generator result = new Generator();
		
		result.setId(vo.getGenId());
		result.setLocationId(vo.getLocId());
		result.setDataDefinitionId(vo.getDataDefId());
		result.setCode(vo.getGenCode());
		result.setName(vo.getGenName());
		result.setDescription(vo.getGenDescription());
		result.setLatitude(vo.getGenCoordLat());
		result.setLongitude(vo.getGenCoordLng());
		result.setBrand(vo.getGenBrand());
		result.setModel(vo.getGenModel());
		result.setSerialNumber(vo.getGenSerialNum());
		result.setRatePower(vo.getGenRatePower());
		
		result.setPowerCurve(convertPower(vo.getPowerCurve()));
		result.setNeighbors(convertNeighbours(vo.getNeighbours()));
		
		return result;
	}
	
	private static Collection<Integer> convertNeighbours(Collection<GenNeighbourVo> neighbours) {
		if (CollectionUtil.isEmpty(neighbours)) return null;
		return neighbours.stream().map(vo -> vo.getGenIdNeighbour()).collect(Collectors.toList());
	}

	private static Generator.Power convert(GenPowerVo vo) {
		if  (vo == null) return null;
		
		Generator.Power result = new Generator.Power();
		
		result.setWindSpeed(vo.getPwrWindSpeed());
		result.setAirDensity(vo.getPwrAirDensity());
		result.setPower(vo.getGenPower());
		
		return result;
	}
	
	public static Station convert(StationVo vo) {
		if (vo == null) return null;
		
		Station result = new Station();
		
		result.setId(vo.getStaId());
		result.setLocationId(vo.getLocId());
		result.setDataDefinitionId(vo.getDataDefId());
		result.setCode(vo.getStaCode());
		result.setName(vo.getStaName());
		result.setDescription(vo.getStaDescription());
		result.setLatitude(vo.getStaCoordLat());
		result.setLongitude(vo.getStaCoordLng());
		
		return result;
	}
	
	public static DataDefinition convert(DataDefinitionVo vo) {
		if (vo == null) return null;
		
		DataDefinition result = new DataDefinition();
		
		result.setId(vo.getDataDefId());
		result.setName(vo.getDataDefName());
		result.setDescription(vo.getDataDefDescription());
		
		return result;
	}
	
	public static Functionality convert(FunctionalityVo vo) {
		if (vo == null) return null;
		
		Functionality result = new Functionality();
		
		result.setId(vo.getFncId());
		result.setTitle(vo.getFncTitle());
		result.setDescription(vo.getFncDescription());
		result.setUrl(vo.getFncUrl());
		
		return result;
	}
	
	public static Setting convert(ISetting vo) {
		if (vo == null) return null;
		
		Setting result = new Setting();
		
		result.setName(vo.getName());
		result.setValue(vo.getValue());
		
		return result;
	}
	
	public static Client convert(ClientVo vo) {
		if (vo == null) return null;
		
		Client client = new Client();
		
		client.setId(vo.getCliId());
		client.setName(vo.getCliName());
		client.setLegalName(vo.getCliNameLegal());
		client.setAddress(vo.getCliNameAddress());
		client.setDataDefinitionId(vo.getDataDefId());
		client.setDataDefinition(convert(vo.getDataDefinitionVo()));
		client.setSettings(convertSettings(vo.getSettings()));
		client.setDemoDate(vo.getCliDemoDate());
		
		return client;
	}
	
	public static Processing convert(DataProcessingVo vo) {
		if (vo == null) return null;
		
		Processing result = new Processing();
		
		result.setProcessingId(vo.getDataProId());
		result.setClientId(vo.getCliId());
		result.setLocationId(vo.getLocId());
		result.setGeneratorId(null);
		result.setStationId(null);
		result.setFilePath(vo.getDataProFileName());
		result.setStart(vo.getDataProDateStart());
		result.setEnd(vo.getDataProDateEnd());
		result.setResult(vo.getDataProResult());
		result.setLogPath(vo.getDataProFileLog());
		
		return result;
	}
	
	private static DocType convert(DocTypeVo vo) {
		if (vo == null) return null;
		
		DocType docType = new DocType();
		docType.setId(vo.getDocTypeId());
		docType.setName(vo.getDocTypeName());
		docType.setTitle(vo.getDocTypeTitle());

		return docType;
	}

	public static Document convert(DocumentVo vo) {
		if (vo == null) return null;
		
		Document document = new Document();
		
		document.setId(vo.getDocId());
		document.setName(vo.getDocName());
		document.setType(convert(vo.getDocTypeVo()));
		document.setAdded(vo.getDocDateAdded());
		document.setFrom(vo.getDocDateFrom());
		document.setTo(vo.getDocDateTo());
		document.setSize(vo.getDocFileSize() == null ? "n/a" : Integer.toString(vo.getDocFileSize().intValue()));
		document.setObservations(vo.getDocObservations());
		document.setIsOpen(Boolean.valueOf(FlagUtil.getFlagValue(vo, DocumentVo.FLAG_IS_OPEN)));
		document.setDownloadLink(EndPointFactory.REST_ADMINISTRATION_DOCUMENTS_DOWNLOAD + "/" + vo.getDocId());
		
		if (CollectionUtil.notEmpty(vo.getGenerators()))	document.setGenerators(convertGenerators(vo.getGenerators().stream().map(x -> x.getGeneratorVo()).collect(Collectors.toSet())));
		if (CollectionUtil.notEmpty(vo.getStations()))		document.setStations(convertStations(vo.getStations().stream().map(x -> x.getStationVo()).collect(Collectors.toSet())));
		if (CollectionUtil.notEmpty(vo.getLocations()))		document.setLocations(convertLocations(vo.getLocations().stream().map(x -> x.getLocationVo()).collect(Collectors.toSet())));
		
		return document;
	}
	
	public static Report convert(RepTypeVo vo) {
		if (vo == null) return null;
		
		Report report = new Report();
		
		report.setId(vo.getRepTypeId());
		report.setTitle(vo.getRepTypeTitle());
		report.setOrder(vo.getRepOrder());
		
		return report;
	}
	
	public static Alert convert(CliLocAlertVo vo) {
		if (vo == null) return null;
		
		Alert alert = new Alert();
		
		alert.setDate(vo.getCliLocAlertAdded());
		alert.setFirstView(! FlagUtil.getFlagValue(vo, CliLocAlertVo.FLAG_SEEN));
		alert.setType(vo.getCliLocAlertType());
		alert.setMessage(vo.getParsedMessage());
		alert.setExtraInfo(vo.getCliLocAlertData());
		
		return alert;
	}
	
	//--- JSON to VO methods --------------------
	public static LocationVo convert(Location vo) {
		if (vo == null) return null;
		
		LocationVo result = new LocationVo();
		
		result.setLocId(vo.getId());
		result.setLocCode(vo.getCode());
		result.setLocName(vo.getName());
		result.setLocAddress(vo.getAddress());
		result.setLocCoordLat(vo.getLatitude());
		result.setLocCoordLng(vo.getLongitude());
		result.setLocOutputCapacity(vo.getOutputCapacity());
		result.setLocOutputTotalCapacity(vo.getOutputTotalCapacity());
		result.setLocReferenceDensity(vo.getReferenceDensity());
		result.setDataDefId(vo.getDataDefinitionId());
		
		if (CollectionUtil.notEmpty(vo.getGenerators()))	for (Generator genVo : vo.getGenerators())	result.add(convert(genVo));
		if (CollectionUtil.notEmpty(vo.getStations()))		for (Station staVo : vo.getStations())		result.add(convert(staVo));
		
		return result;
	}
	
	public static GeneratorVo convert(Generator vo) {
		if (vo == null) return null;
		
		GeneratorVo result = new GeneratorVo();
		
		result.setLocId(vo.getLocationId());
		result.setDataDefId(vo.getDataDefinitionId());
		result.setGenId(vo.getId());
		result.setGenCode(vo.getCode());
		result.setGenName(vo.getName());
		result.setGenDescription(vo.getDescription());
		result.setGenCoordLat(vo.getLatitude());
		result.setGenCoordLng(vo.getLongitude());
		result.setGenBrand(vo.getBrand());
		result.setGenModel(vo.getModel());
		result.setGenSerialNum(vo.getSerialNumber());
		result.setGenRatePower(vo.getRatePower());
		
		result.setPowerCurve(convertRestPowerCurve(vo.getPowerCurve()));
		
		return result;
	}
	
	public static GenPowerVo convert(Generator.Power vo) {
		if (vo == null) return null;
		
		GenPowerVo result = new GenPowerVo();
		
		result.setPwrWindSpeed(vo.getWindSpeed());
		result.setPwrAirDensity(vo.getAirDensity());
		result.setGenPower(vo.getPower());
		
		return result;
	}
	
	public static StationVo convert(Station vo) {
		if (vo == null) return null;
		
		StationVo result = new StationVo();
		
		result.setLocId(vo.getLocationId());
		result.setDataDefId(vo.getDataDefinitionId());
		result.setStaId(vo.getId());
		result.setStaCode(vo.getCode());
		result.setStaName(vo.getName());
		result.setStaDescription(vo.getDescription());
		result.setStaCoordLat(vo.getLatitude());
		result.setStaCoordLng(vo.getLongitude());
		
		return result;
	}
	
	public static User convert(UserData userData) {
		User result = new User();
		
		result.setAuthenticated(userData.isLogged());
		
		if (userData.isLogged()) {
			result.setId(userData.getUsrId());
			result.setName(userData.getUserVo().getUsrName());
			result.setEmail(userData.getUserVo().getUsrEmail());
			
			result.setClient(RestFactory.convert(userData.getClientVo()));
			result.setLocation(RestFactory.convert(userData.getLocationVo()));
			
			result.setFunctionalities(RestFactory.convertFunctionalities(userData.getLocationFunctionalities()));
			result.setSettings(RestFactory.convertSettings(userData.getUserVo().getSettings()));
		} else {
			result.setErrorCode(Integer.valueOf(userData.getAuthenticationError()));
			switch (userData.getAuthenticationError()) {
				case SecurityService.AUTHENTICATION_ERROR_BAD_EMAIL_PASSWORD_CLIENT:	result.setError("Not authenticated, bad combination of email, password and client."); break;
				case SecurityService.AUTHENTICATION_NOT_ALLOWED:						result.setError("Not allowed."); break;
				case SecurityService.AUTHENTICATION_NOT_LOGGED:							result.setError("Not logged."); break;
			}
		}
		
		return result;
	}

	public static WeatherDefinition convert(WeaDefinitionVo vo) {
		if (vo == null) return null;
		
		WeatherDefinition result = new WeatherDefinition();
		
		result.setId(vo.getWeaId());
		result.setName(vo.getWeaName());
		result.setLatitude(vo.getWeaCoordLat());
		result.setLongitude(vo.getWeaCoordLng());
		result.setType(vo.getType());
		result.setFrequency(vo.getWeaCheckFrequency());
		
		return result;
	}

	public static Object convert(LocWeatherDataVo data) {
		return data == null ? null : data.getLocWeaDataResponse();
		
	}
	
	public static List<DataDefinitionTrigger> convertTriggers(Collection<CliDataDefTriggerVo> triggers) {
		if (triggers == null) return null;
		
		List<DataDefinitionTrigger> result = new ArrayList<>(CollectionUtil.size(triggers));
		for (CliDataDefTriggerVo vo : triggers) {
			DataDefinitionTrigger trigger = new DataDefinitionTrigger();
			trigger.setName(vo.getTriName());
			trigger.setSource(vo.getTriSourceText());
			trigger.setTrigger(vo.getTriValue());
			trigger.setId(vo.getTriId());
			trigger.setDataDefinitionId(vo.getDataDefId());
			trigger.setClientId(vo.getCliId());
			trigger.setLocationId(vo.getLocId());
			trigger.setGeneratorId(vo.getGenId());
			trigger.setStationId(vo.getStaId());;
			
			trigger.setClient(convert(vo.getClientVo()));
			trigger.setLocation(convert(vo.getLocationVo()));
			trigger.setGenerator(convert(vo.getGeneratorVo()));
			trigger.setStation(convert(vo.getStationVo()));
			
			result.add(trigger);
		}
		return result;
	}
	
	public static DocumentVo convert(Document vo) {
		if (vo == null) return null;
		
		DocumentVo document = new DocumentVo();
		
		document.setDocId(vo.getId());
		document.setDocName(vo.getName());
//		document.setDocType(vo.getDocType());
		document.setDocDateAdded(vo.getAdded());
		document.setDocDateFrom(vo.getFrom());
		document.setDocDateTo(vo.getTo());
		document.setForcedLocId(vo.getForcedLocation());
		document.setDocObservations(vo.getObservations());
		FlagUtil.setFlagValue(document, DocumentVo.FLAG_IS_OPEN, BooleanUtils.isTrue(vo.getIsOpen()));
		
		return document;
	}
}
