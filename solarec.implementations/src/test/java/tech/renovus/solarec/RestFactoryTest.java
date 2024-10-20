package tech.renovus.solarec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

import tech.renovus.solarec.business.SecurityService;
import tech.renovus.solarec.interfaces.ISetting;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.vo.db.data.CliSettingVo;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.CountryVo;
import tech.renovus.solarec.vo.db.data.DataDefinitionVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.db.data.DocGeneratorVo;
import tech.renovus.solarec.vo.db.data.DocLocationVo;
import tech.renovus.solarec.vo.db.data.DocStationVo;
import tech.renovus.solarec.vo.db.data.DocTypeVo;
import tech.renovus.solarec.vo.db.data.DocumentVo;
import tech.renovus.solarec.vo.db.data.FunctionalityVo;
import tech.renovus.solarec.vo.db.data.GenPowerVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocSdgVo;
import tech.renovus.solarec.vo.db.data.LocTypeVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.RepTypeVo;
import tech.renovus.solarec.vo.db.data.SdgVo;
import tech.renovus.solarec.vo.db.data.SettingsVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.vo.db.data.UsersVo;
import tech.renovus.solarec.vo.db.data.WeaDefinitionVo;
import tech.renovus.solarec.vo.rest.entity.Client;
import tech.renovus.solarec.vo.rest.entity.Country;
import tech.renovus.solarec.vo.rest.entity.DataDefinition;
import tech.renovus.solarec.vo.rest.entity.Functionality;
import tech.renovus.solarec.vo.rest.entity.Generator;
import tech.renovus.solarec.vo.rest.entity.Location;
import tech.renovus.solarec.vo.rest.entity.Setting;
import tech.renovus.solarec.vo.rest.entity.Station;
import tech.renovus.solarec.vo.rest.entity.User;

public class RestFactoryTest {

	//--- Private methods -----------------------
	private LocationVo createLocationSampleVo() {
		LocationVo result = new LocationVo();
		
		result.setCliId(Integer.valueOf(-123));
		result.setLocId(Integer.valueOf(-23));
		result.setDataDefId(Integer.valueOf(-325));
		result.setLocCoordLat(Double.valueOf(33.2333));
		result.setLocCoordLng(Double.valueOf(-54.234));
		result.setLocOutputCapacity(Double.valueOf(40));
		result.setLocOutputTotalCapacity(Double.valueOf(30));
		result.setLocReferenceDensity(Double.valueOf(1.2));
		result.setLocDataDateMax(new Date());
		result.setLocDataDateMin(new Date());
		result.setLocDemoDate(new Date());
		result.setLocName("Test");
		result.setLocAddress("Address");
		result.setLocState("State");
		result.setLocCode("T1");
		result.setLocType(LocationVo.TYPE_SOLAR);
		result.setLocGmt("-3:00");
		FlagUtil.setFlagValue(result, LocationVo.FLAG_REPORT_ENABLED		    , true);
		FlagUtil.setFlagValue(result, LocationVo.FLAG_HIDE_FROM_DASHBOARD		, true);
		FlagUtil.setFlagValue(result, LocationVo.FLAG_ALERT_CALCULATION_ENABLED	, true);
		FlagUtil.setFlagValue(result, LocationVo.FLAG_ENABLED_FOR_EMAIL_ALERT	, true);
		FlagUtil.setFlagValue(result, LocationVo.FLAG_CONNECTED_TO_GRID			, true);
		FlagUtil.setFlagValue(result, LocationVo.FLAG_ENABLED					, true);

		result.add(this.createGenerator());
		result.add(this.createStation());
		result.add(this.createLocSdg());
		
		result.setCountryVo(this.creteCountry());
		result.setLocTypeVo(this.createLocType());
		
		return result;
	}
	
	private LocSdgVo createLocSdg() {
		LocSdgVo result = new LocSdgVo();
		
		result.setCliId(Integer.valueOf(-123));
		result.setLocId(Integer.valueOf(-1233));
		result.setSdgId(Integer.valueOf(-34));
		result.setLocSdgDescription("Location SDG description");
		
		result.setSdgVo(this.createSdg());
		
		return result;
	}
	
	private SdgVo createSdg() {
		SdgVo result = new SdgVo();
		
		result.setSdgId(Integer.valueOf(-123));
		result.setSdgCode("SDG-Code");
		result.setSdgName("SDG-Name");
		
		return result;
	}
	
	private StationVo createStation() {
		StationVo result = new StationVo();
		
		result.setStaDataDateMin(new Date());
		result.setStaId(Integer.valueOf(-2323));
		result.setDataDefId(Integer.valueOf(-21313));
		result.setLocId(Integer.valueOf(-1313));
		result.setStaCoordLat(Double.valueOf(-34.232323));
		result.setStaCoordLng(Double.valueOf(-54.2323));
		result.setStaDataDateMax(new Date());
		result.setCliId(Integer.valueOf(-123123));
		result.setStaName("Station");
		result.setStaDescription("Description stateion");
		result.setStaFlags("1010101");
		result.setStaCode("S1");
		
		FlagUtil.setFlagValue(result, StationVo.FLAG_ENABLED, true);
		
		FlagUtil.setFlagValue(result, StationVo.FLAG_DEFAULT					, true);
		FlagUtil.setFlagValue(result, StationVo.FLAG_ENABLED					, true);
		FlagUtil.setFlagValue(result, StationVo.FLAG_REGISTERED_AT_CERT_PROVIDER, true);
		
		return result;
	}

	private GeneratorVo createGenerator() {
		GeneratorVo result = new GeneratorVo();
		
		result.setCliId(Integer.valueOf(-2313));
		result.setGenId(Integer.valueOf(-1233));
		result.setDataDefId(Integer.valueOf(-545));
		result.setLocId(Integer.valueOf(-332));
		result.setGenCoordLat(Double.valueOf(-32.2323));
		result.setGenCoordLng(Double.valueOf(-54.2323));
		result.setGenRatePower(Double.valueOf(-23));
		result.setGenDataDateMax(new Date());
		result.setGenDataDateMin(new Date());
		result.setGenModel("asdad");
		result.setGenSerialNum("23asasda23");
		result.setGenName("G1");
		result.setGenDescription("Generator");
		result.setGenCode("Code");
		result.setGenFlags("101010");
		result.setGenBrand("Brand");
		
		FlagUtil.setFlagValue(result, GeneratorVo.FLAG_ENABLED						, true);
		FlagUtil.setFlagValue(result, GeneratorVo.FLAG_ENABLED_FOR_EMAIL_ALERT		, true);
		FlagUtil.setFlagValue(result, GeneratorVo.FLAG_REGISTERED_AT_CERT_PROVIDER	, true);

		
		return result;
	}

	private CountryVo creteCountry() {
		CountryVo result = new CountryVo();
		result.setCtrName("name");
		result.setCtrCode2("2");
		result.setCtrCode3("3");
		
		return result;
	}
	
	private LocTypeVo createLocType() {
		LocTypeVo result = new LocTypeVo();
		result.setLocTypeId(Integer.valueOf(1));
		result.setLocTypeCode("loc_type_code");
		result.setLocTypeText("loc type text");
		
		return result;
	}
	
	private FunctionalityVo createFunctionality() {
		FunctionalityVo result = new FunctionalityVo();
		
		result.setFncId(1);
		result.setFncTitle("title");
		result.setFncDescription("description");
		result.setFncUrl("url");
		
		return result;
	}
	
	private GenPowerVo createGenPowerVo() {
		GenPowerVo result = new GenPowerVo();
		
		result.setPwrWindSpeed(Double.valueOf(10));
		result.setPwrAirDensity(Double.valueOf(22));
		result.setGenPower(Double.valueOf(44));
		
		return result;
	}
	
	private DataProcessingVo createDataProcessingVo() {
		DataProcessingVo result = new DataProcessingVo();
		
		result.setDataProId(Integer.valueOf(22));
		result.setDataDefId(Integer.valueOf(23));
		result.setCliId(Integer.valueOf(24));
		result.setLocId(Integer.valueOf(25));
		result.setTriId(Integer.valueOf(26));
		result.setDataProResult(Integer.valueOf(27));
		result.setGenId(Integer.valueOf(28));
		result.setDataProDateStart(new Date());
		result.setDataProDateEnd(new Date());
		result.setDataProFileName("test file");
		result.setDataProFileLog("test log");
		
		return result;
	}
	
	private DocTypeVo createDocTypeVo() {
		DocTypeVo result = new DocTypeVo();
		
		result.setDocTypeId(Integer.valueOf(12));
		result.setDocTypeName("Name");
		result.setDocTypeTitle("Title");
		result.setDocTypeFlags("000110");
		
		return result;
	}
	
	private DocumentVo createDocumentVo() {
		DocumentVo result = new DocumentVo();
		
		result.setCliId(Integer.valueOf(44));
		result.setDocId(Integer.valueOf(45));
		result.setDocTypeId(Integer.valueOf(46));
		result.setDocDateAdded(new Date());
		result.setDocDateFrom(new Date());
		result.setDocDateTo(new Date());
		result.setDocFileSize(Double.valueOf(121.22));
		result.setDocFlags("001100");
		result.setDocName("Name");
		result.setDocObservations("Observations");
		result.setDocFile("file path.txt");
		result.setDocFileName("file name.text");
		result.setDocFileContent("the content");

		DocGeneratorVo docGenVo = new DocGeneratorVo();
		docGenVo.setGeneratorVo(this.createGenerator());
		docGenVo.setCliId(result.getCliId());
		docGenVo.setDocId(result.getDocId());
		docGenVo.setGenId(docGenVo.getGeneratorVo().getGenId());
		
		DocLocationVo docLocVo = new DocLocationVo();
		docLocVo.setLocationVo(this.createLocationSampleVo());
		docLocVo.setCliId(result.getCliId());
		docLocVo.setDocId(result.getDocId());
		docLocVo.setLocId(docLocVo.getLocationVo().getLocId());

		DocStationVo docStaVo = new DocStationVo();
		docStaVo.setStationVo(this.createStation());
		docStaVo.setCliId(result.getCliId());
		docStaVo.setDocId(result.getDocId());
		docStaVo.setStaId(docStaVo.getStationVo().getStaId());
		
		result.add(docGenVo);
		result.add(docStaVo);
		result.add(docLocVo);
		
		return result;
	}
	
	private WeaDefinitionVo createWeaDefinitionVo() {
		WeaDefinitionVo result = new WeaDefinitionVo();
		
		result.setCliId(Integer.valueOf(55));
		result.setWeaId(Integer.valueOf(56));
		result.setWeaCoordLng(Double.valueOf(-33.3332));
		result.setWeaCoordLat(Double.valueOf(-55.232));
		result.setWeaCheckType(Integer.valueOf(1));
		result.setWeaCheckFrequency(Integer.valueOf(15));
		result.setWeaName("Name");
		result.setWeaDescription("Description");
		result.setWeaFlags("001110");
		
		return result;
	}
	
	private RepTypeVo createRepTypeVo() {
		RepTypeVo result = new RepTypeVo();
		
		result.setRepTypeId(Integer.valueOf(77));
		result.setRepOrder(Integer.valueOf(7));
		result.setRepTypeName("Name");
		result.setRepTypeTitle("Title");
		result.setRepFlags("011000");
		result.setRepExecutable("executable.class");
		
		return result;
	}
	
	private CountryVo createCountryVo() {
		CountryVo result = new CountryVo();
		
		result.setCtrCoordLng(Double.valueOf(-33.255));
		result.setCtrDataDateMax(new Date());
		result.setCtrDataDateMin(new Date());
		result.setCtrCoordLat(Double.valueOf(-56.232));
		result.setCtrId(Integer.valueOf(88));
		result.setCtrCodePhone("+111");
		result.setCtrName("Name");
		result.setCtrNameShow("To show");
		result.setCtrCode2("22");
		result.setCtrCode3("222");

		return result;
	}
	
	private DataDefinitionVo createDataDefinitionVo() {
		DataDefinitionVo result = new DataDefinitionVo();
		
		result.setDataDefId(Integer.valueOf(123));
		result.setDataDefName("name");
		result.setDataDefDescription("description");
		
		return result;
	}
	
	private SettingsVo craeteSettingVo() {
		SettingsVo result = new SettingsVo();
		
		result.setSetCatName("category");
		result.setSetName("name");
		result.setSetType("type");
		result.setSetUnit("unit");
		result.setSetValueDefault("default value");
		result.setSetValueMax("max");
		result.setSetValueMin("min");
		
		return result;
	}
	
	private CliSettingVo createISetting() {
		CliSettingVo result = new CliSettingVo();
		
		result.setCliSetName("name");
		result.setCliSetValue("value");
		
		result.setSettingVo(this.craeteSettingVo());
		
		return result;
	}
	
	private ClientVo createClientVo() {
		ClientVo result = new ClientVo();
		
		result.setCliId(Integer.valueOf(123));
		result.setCliName("Name");
		result.setCliNameLegal("legal name");
		result.setCliNameAddress("address");
		result.setDataDefId(Integer.valueOf(1244));
		result.setDataDefinitionVo(this.createDataDefinitionVo());
		result.setSettings(Arrays.asList(this.createISetting()));
		result.setCliDemoDate(new Date());
		
		FlagUtil.setFlagValue(result, ClientVo.FLAG_REPORT_ENABLED				, true);
		FlagUtil.setFlagValue(result, ClientVo.FLAG_ALERT_CALCULATION_ENABLED	, true);
		FlagUtil.setFlagValue(result, ClientVo.FLAG_ENABLED_FOR_EMAIL_ALERT		, true);
		FlagUtil.setFlagValue(result, ClientVo.FLAG_ENABLED						, true);
		FlagUtil.setFlagValue(result, ClientVo.FLAG_REGISTERED_AT_CERT_PROVIDER	, true);
		
		return result;
	}
	
	private UsersVo createUser() {
		UsersVo result = new UsersVo();
		
		result.setUsrId(Integer.valueOf(-123));
		result.setUsrDateLogin(new Date());
		result.setUsrDateAdded(new Date());
		result.setUsrDateLocked(null);
		result.setUsrPwdResetRequested(null);;
		result.setUsrPwdResetUuid(null);
		result.setUsrComments("User comments");
		result.setUsrEmail("test@test.com");
		result.setUsrName("The name");
		result.setUsrFlags("01010");
		result.setUsrPassword("not real");
		
		return result;
	}
	
	private UserData createUserData() {
		UserData result = new UserData();
		
		result.setLogged(true);
		result.setUserVo(this.createUser());
		result.setClientVo(this.createClientVo());
		result.setLocationVo(this.createLocationSampleVo());
		
		result.setFunctionalities(null);
		
		return result;
	}
	
	//--- Test methods --------------------------
	@Test
	public void testCollections() {
		RestFactory factory =  new RestFactory();
		assertTrue(CollectionUtil.isEmpty(factory.convertProcessings(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertDataDefinitions(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertLocations(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertStations(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertGenerators(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertFunctionalities(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertSettings(null, null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertClients(null, null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertDocTypes(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertDocuments(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertPower(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertWeatherDefinitions(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertRestPowerCurve(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertReportTypes(null)));
		assertTrue(CollectionUtil.isEmpty(factory.convertCountries(null)));

		assertTrue(CollectionUtil.notEmpty(factory.convertProcessings(Arrays.asList(this.createDataProcessingVo()))));
		assertTrue(CollectionUtil.notEmpty(factory.convertDataDefinitions(Arrays.asList(this.createDataDefinitionVo()))));
		assertTrue(CollectionUtil.notEmpty(factory.convertLocations(Arrays.asList(this.createLocationSampleVo()))));
		assertTrue(CollectionUtil.notEmpty(factory.convertStations(Arrays.asList(this.createStation()))));
		assertTrue(CollectionUtil.notEmpty(factory.convertGenerators(Arrays.asList(this.createGenerator()))));
		assertTrue(CollectionUtil.notEmpty(factory.convertFunctionalities(Arrays.asList(this.createFunctionality()))));
		assertTrue(CollectionUtil.notEmpty(factory.convertClients(Arrays.asList(this.createClientVo()), null)));
		assertTrue(CollectionUtil.notEmpty(factory.convertDocTypes(Arrays.asList(this.createDocTypeVo()))));
		assertTrue(CollectionUtil.notEmpty(factory.convertDocuments(Arrays.asList(this.createDocumentVo()))));
		assertTrue(CollectionUtil.notEmpty(factory.convertWeatherDefinitions(Arrays.asList(this.createWeaDefinitionVo()))));
		assertTrue(CollectionUtil.notEmpty(factory.convertReportTypes(Arrays.asList(this.createRepTypeVo()))));
		assertTrue(CollectionUtil.notEmpty(factory.convertCountries(Arrays.asList(this.createCountryVo()))));
	}
	
	@Test
	public void testVos() {
		RestFactory factory =  new RestFactory();
		
		factory.convert(this.createDocumentVo());
	}
	
	@Test
	public void testLocation() {
		RestFactory factory =  new RestFactory();
		LocationVo vo = this.createLocationSampleVo();
		Location result = factory.convert(vo);
		
		assertNull(factory.convert((GeneratorVo) null));
		
		assertEquals(result.getId(), vo.getLocId());
		assertEquals(result.getCode(), vo.getLocCode());
		assertEquals(result.getName(), vo.getLocName());
		assertEquals(result.getAddress(), vo.getLocAddress());
		assertEquals(result.getState(), vo.getLocState());
		
		assertEquals(result.getLatitude(), vo.getLocCoordLat());
		assertEquals(result.getLongitude(), vo.getLocCoordLng());
		assertEquals(result.getOutputCapacity(), vo.getLocOutputCapacity());
		assertEquals(result.getOutputTotalCapacity(), vo.getLocOutputTotalCapacity());
		assertEquals(result.getReferenceDensity(), vo.getLocReferenceDensity());
		assertEquals(result.getDataDefinitionId(), vo.getDataDefId());
		assertEquals(result.getType(), vo.getLocType());
		assertEquals(result.getDemoDate(), vo.getLocDemoDate());
		
		assertEquals(FlagUtil.getFlagValue(vo, LocationVo.FLAG_CONNECTED_TO_GRID), result.getGridConnected());
		assertEquals(FlagUtil.getFlagValue(vo, LocationVo.FLAG_ENABLED), result.getEnabled());
		
		assertEquals(CollectionUtil.size(result.getGenerators()), CollectionUtil.size(vo.getGenerators()));
		assertEquals(CollectionUtil.size(result.getStations()), CollectionUtil.size(vo.getStations()));
		assertEquals(CollectionUtil.size(result.getSdgs()), CollectionUtil.size(vo.getSdgs()));
		
		assertNotNull(result.getCountry());
		assertNotNull(result.getTypeOf());
	}

	@Test
	public void testCountry() {
		RestFactory factory =  new RestFactory();
		CountryVo vo = this.creteCountry();
		Country result = factory.convert(vo);
		
		assertNull(factory.convert((CountryVo) null));
		assertEquals(vo.getCtrName(), result.getName());
		assertEquals(vo.getCtrCode2(), result.getIso2());
		assertEquals(vo.getCtrCode3(), result.getIso3());
	}
	
	@Test
	public void testLocType() {
		RestFactory factory =  new RestFactory();
		LocTypeVo vo = this.createLocType();
		Location.Type result = factory.convert(vo);
		
		assertNull(factory.convert((CountryVo) null));
		assertEquals(vo.getLocTypeCode(), result.getCode());
		assertEquals(vo.getLocTypeText(), result.getText());
	}

	@Test
	public void testFunctionlaity() {
		RestFactory factory =  new RestFactory();
		FunctionalityVo vo = this.createFunctionality();
		Functionality result = factory.convert(vo);
		
		assertNull(factory.convert((FunctionalityVo) null));
		assertEquals(vo.getFncId(), result.getId());
		assertEquals(vo.getFncTitle(), result.getTitle());
		assertEquals(vo.getFncDescription(), result.getDescription());
		assertEquals(vo.getFncUrl(), result.getUrl());
	}
	
	@Test
	public void testGenPower() {
		RestFactory factory =  new RestFactory();
		GenPowerVo vo = this.createGenPowerVo();
		Collection<Generator.Power> result = factory.convertPower(Arrays.asList(vo));
		
		assertNotNull(factory.convertPower((Collection<GenPowerVo>) null));
		assertEquals(1, CollectionUtil.size(result));
		
		Generator.Power power = result.iterator().next();
		
		assertEquals(vo.getGenPower(), power.getPower());
		assertEquals(vo.getPwrAirDensity(), power.getAirDensity());
		assertEquals(vo.getPwrWindSpeed(), power.getWindSpeed());
	}
	
	@Test
	public void testDataDefinition() {
		RestFactory factory =  new RestFactory();
		DataDefinitionVo vo = this.createDataDefinitionVo();
		DataDefinition result = factory.convert(vo);
		
		assertNotNull(factory.convertPower((Collection<GenPowerVo>) null));
		
		assertEquals(vo.getDataDefId(), result.getId());
		assertEquals(vo.getDataDefName(), result.getName());
		assertEquals(vo.getDataDefDescription(), result.getDescription());
	}
	
	@Test
	public void testSettings() {
		RestFactory factory =  new RestFactory();
		ISetting vo = this.createISetting();
		Setting result = factory.convert(vo, null);
		
		assertNotNull(factory.convertPower((Collection<GenPowerVo>) null));
		
		assertEquals(vo.getName(), result.getName());
		assertEquals(vo.getValue(), result.getValue());
	}
	
	@Test
	public void testClient() {
		RestFactory factory =  new RestFactory();
		ClientVo vo = this.createClientVo();
		Client result = factory.convert(vo, null);
		
		assertNotNull(factory.convertPower((Collection<GenPowerVo>) null));
		
		assertEquals(vo.getCliId(), result.getId());
		assertEquals(vo.getCliName(), result.getName());
		assertEquals(vo.getCliNameLegal(), result.getLegalName());
		assertEquals(vo.getCliNameAddress(), result.getAddress());
		assertEquals(vo.getDataDefId(), result.getDataDefinitionId());
		
		assertEquals(FlagUtil.getFlagValue(vo, ClientVo.FLAG_REPORT_ENABLED), result.getEnabled());
		
		assertNotNull(result.getDataDefinition());
		
		assertEquals(vo.getDataDefinitionVo().getDataDefId(), result.getDataDefinition().getId());
		assertEquals(vo.getDataDefinitionVo().getDataDefName(), result.getDataDefinition().getName());
		assertEquals(vo.getDataDefinitionVo().getDataDefDescription(), result.getDataDefinition().getDescription());
	}
	
	@Test
	public void testUserData() {
		UserData userData = this.createUserData();
		RestFactory factory =  new RestFactory();
		User user = factory.convert(userData);
		
		assertNotNull(user);
		assertEquals(userData.isLogged(), user.isAuthenticated());
		assertEquals(userData.getUsrId(), user.getId());
		assertEquals(userData.getUserVo().getUsrName(), user.getName());
		assertEquals(userData.getUserVo().getUsrEmail(), user.getEmail());
		assertNotNull(user.getClient());
		assertNotNull(user.getLocation());
		assertTrue(CollectionUtil.isEmpty(user.getFunctionalities()));
		assertTrue(CollectionUtil.isEmpty(user.getSettings()));
		
		userData.setLogged(false);
		userData.setAuthenticationError(SecurityService.AUTHENTICATION_ERROR_BAD_EMAIL_PASSWORD_CLIENT);
		user = factory.convert(userData);
		
		assertNotNull(user);
		assertEquals(userData.isLogged(), user.isAuthenticated());
		assertNotNull(user.getId());
		assertNotNull(user.getErrorCode());
		assertEquals(userData.getAuthenticationError(), user.getErrorCode().intValue());
		assertEquals("Not authenticated, bad combination of email, password and client.", user.getError());
	}

	@Test
	public void testFromJson() {
		RestFactory factory =  new RestFactory();

		LocationVo locVo = this.createLocationSampleVo();
		Location loc = factory.convert(locVo);
		LocationVo locRest = factory.convert(loc);
		assertEquals(locVo.getLocName(), locRest.getLocName());
		assertEquals(locVo.getLocAddress(), locRest.getLocAddress());
		assertEquals(locVo.getLocOutputCapacity(), locRest.getLocOutputCapacity());
		assertEquals(locVo.getDataDefId(), locRest.getDataDefId());
		
		GeneratorVo genVo = this.createGenerator();
		Generator gen = factory.convert(genVo);
		GeneratorVo genRest = factory.convert(gen);
		assertEquals(genVo.getGenId(), genRest.getGenId());
		assertEquals(genVo.getGenName(), genRest.getGenName());
		assertEquals(genVo.getGenCode(), genRest.getGenCode());
		assertEquals(genVo.getGenBrand(), genRest.getGenBrand());
		assertEquals(genVo.getGenModel(), genRest.getGenModel());

		StationVo staVo = this.createStation();
		Station sta = factory.convert(staVo);
		StationVo staRest = factory.convert(sta);
		assertEquals(staVo.getStaId(), staRest.getStaId());
		assertEquals(staVo.getStaName(), staRest.getStaName());
		assertEquals(staVo.getStaCode(), staRest.getStaCode());
		assertEquals(staVo.getStaDescription(), staRest.getStaDescription());
		
	}
}
