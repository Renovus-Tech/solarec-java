package tech.renovus.solarec.vo.db.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

import tech.renovus.solarec.util.CollectionUtil;

public class LocationVoTest {

	@Test public void testVo() {
		Integer cliId = Integer.valueOf(1);
		Integer locId = Integer.valueOf(2);
		Integer staId = Integer.valueOf(3);
		Integer genId = Integer.valueOf(4);
		Integer dataTypeId = Integer.valueOf(5);
		Integer alertDefId = Integer.valueOf(6);
		Integer locEstId = Integer.valueOf(7);
		Integer dataDefParId = Integer.valueOf(8);
		Integer sdgId = Integer.valueOf(9);
		Integer dataDefId = Integer.valueOf(10);
		
		String metadataName = "asdadad";
		String dadDefParName = "sdadadadad";
		
		Date dataDate = new Date();
		Date alertDataAdded = new Date();
		
		LocationVo vo = new LocationVo(cliId, locId);
		vo.setLocName("loc 1");
		
		LocationVo vo2 = new LocationVo();
		vo2.setPk(vo);
		vo2.setLocName("loc 2");
		
		assertEquals(cliId, vo.getCliId());
		assertEquals(locId, vo.getLocId());
		
		assertEquals(vo, vo2);
		
		assertEquals(-1, vo.compareTo(vo2));
		
		StationVo staVo = new StationVo(cliId, staId);
		GeneratorVo genVo = new GeneratorVo(cliId, genId);
		LocDataVo locDataVo = new LocDataVo(null, null, dataDate, dataTypeId);
		LocAlertVo locAlertVo = new LocAlertVo(null, null, alertDefId, alertDataAdded);
		LocEstimationVo locEstVo = new LocEstimationVo(null, null, locEstId);
		LocDataDefParameterVo locDataDefParamVo = new LocDataDefParameterVo(null, null, alertDefId, dataDefParId);
		DataDefParameterVo dataDefParamVo = new DataDefParameterVo(dataDefId, dataDefParId);
		LocMetadataVo locMetadataVo = new LocMetadataVo(null, null, metadataName);
		LocSdgVo locSdgVo = new LocSdgVo(null, null, sdgId);
		
		genVo.setGenName("gen 1");
		staVo.setStaName("sta 1");
		locDataDefParamVo.setDataDefParameter(dataDefParamVo);
		dataDefParamVo.setDataDefParName(dadDefParName);
		
		vo.addStations(Arrays.asList(staVo));
		vo.addGenerators(Arrays.asList(genVo));
		vo.add(locDataVo);
		vo.add(locAlertVo);
		vo.addEstimations(Arrays.asList(locEstVo));
		vo.add(locDataDefParamVo);
		vo.add(locMetadataVo);
		vo.add(locSdgVo);
		
		vo.synchronize(null);
		
		assertEquals(1, CollectionUtil.size(vo.getStations()));
		assertEquals(1, CollectionUtil.size(vo.getGenerators()));
		assertEquals(1, CollectionUtil.size(vo.getDatas()));
		assertEquals(1, CollectionUtil.size(vo.getAlerts()));
		assertEquals(1, CollectionUtil.size(vo.getEstimations()));
		assertEquals(1, CollectionUtil.size(vo.getDataDefParameters()));
		assertEquals(1, CollectionUtil.size(vo.getMetadata()));
		assertEquals(1, CollectionUtil.size(vo.getSdgs()));
		
		assertEquals(locId, locDataVo.getLocId());
		assertEquals(locId, locAlertVo.getLocId());
		assertEquals(locId, locEstVo.getLocId());
		assertEquals(locId, locDataDefParamVo.getLocId());
		assertEquals(locId, locMetadataVo.getLocId());
		assertEquals(locId, locSdgVo.getLocId());
		
		assertEquals(vo, vo.getDataContainer("loc 1"));
		assertEquals(genVo, vo.getDataContainer("gen 1"));
		assertEquals(staVo, vo.getDataContainer("sta 1"));
		assertNull(vo.getDataContainer("asdad"));
		
		assertEquals(locMetadataVo, vo.getMetadataVo(metadataName));
		assertEquals(locDataDefParamVo, vo.getDataDefParameterVo(dadDefParName));
		
	}
}
