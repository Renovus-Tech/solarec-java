package tech.renovus.solarec.api.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.vo.rest.entity.Location;

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
		result.setLocCountryAlpha2("UY");
		result.setLocName("Test");
		result.setLocAddress("Address");
		result.setLocState("State");
		result.setLocCountry("Uruguay");
		result.setLocFlags("10101");
		result.setLocCode("T1");
		result.setLocType(LocationVo.TYPE_SOLAR);
		result.setLocGmt("-3:00");

		result.add(this.creteGenerator());
		result.add(this.createStation());
		
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
		
		return result;
	}

	private GeneratorVo creteGenerator() {
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
		assertEquals(result.getCountry(), vo.getLocCountry());
		assertEquals(result.getCountryAlpha2(), vo.getLocCountryAlpha2());
		assertEquals(result.getLatitude(), vo.getLocCoordLat());
		assertEquals(result.getLongitude(), vo.getLocCoordLng());
		assertEquals(result.getOutputCapacity(), vo.getLocOutputCapacity());
		assertEquals(result.getOutputTotalCapacity(), vo.getLocOutputTotalCapacity());
		assertEquals(result.getReferenceDensity(), vo.getLocReferenceDensity());
		assertEquals(result.getDataDefinitionId(), vo.getDataDefId());
		assertEquals(result.getType(), vo.getLocType());
		assertEquals(result.getDemoDate(), vo.getLocDemoDate());
		
		assertEquals(CollectionUtil.size(result.getGenerators()), CollectionUtil.size(vo.getGenerators()));
		assertEquals(CollectionUtil.size(result.getStations()), CollectionUtil.size(vo.getStations()));
		
	}
}
