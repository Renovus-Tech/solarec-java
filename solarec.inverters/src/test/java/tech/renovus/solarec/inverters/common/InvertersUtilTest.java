package tech.renovus.solarec.inverters.common;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.LocationVo;

public class InvertersUtilTest {

	@Test public void testParameters() {
		ClientVo cliVo = new ClientVo();
		LocationVo locVo = new LocationVo();
		GeneratorVo genVo = new GeneratorVo();
		
		cliVo.setDataDefParameters(new ArrayList<>());
		locVo.setDataDefParameters(new ArrayList<>());
		genVo.setDataDefParameters(new ArrayList<>());

		String cliParName	= "cliParName";
		String cliParValue	= "cliParValue";
		String locParName	= "locParName";
		String locParValue	= "locParValue";
		String genParName	= "genParName";
		String genParValue	= "genParValue";
		
		InvertersUtil.setParameter(cliVo, cliParName, cliParValue);;
		InvertersUtil.setParameter(locVo, locParName, locParValue);;
		InvertersUtil.setParameter(genVo, genParName, genParValue);;
		
		assertEquals(cliParValue, InvertersUtil.getParameter(cliVo, cliParName));
		assertEquals(locParValue, InvertersUtil.getParameter(locVo, locParName));
		assertEquals(genParValue, InvertersUtil.getParameter(genVo, genParName));
		
		
		assertEquals(cliParValue, InvertersUtil.getParameter(null, null, cliVo, cliParName));
		assertEquals(locParValue, InvertersUtil.getParameter(null, locVo, cliVo, locParName));
		assertEquals(genParValue, InvertersUtil.getParameter(genVo, locVo, cliVo, genParName));
	}
	

	@Test public void testMetadata() {
		ClientVo cliVo = new ClientVo();
		LocationVo locVo = new LocationVo();
		GeneratorVo genVo = new GeneratorVo();
		
		cliVo.setMetadata(new ArrayList<>());
		locVo.setMetadata(new ArrayList<>());
		genVo.setMetadata(new ArrayList<>());

		String cliMetaName	= "cliMetaName";
		String cliMetaValue	= "cliMetaValue";
		String locMetaName	= "locMetaName";
		String locMetaValue	= "locMetaValue";
		String genMetaName	= "genMetaName";
		String genMetaValue	= "genMetaValue";
		
		InvertersUtil.setMetadata(cliVo, cliMetaName, cliMetaValue);;
		InvertersUtil.setMetadata(locVo, locMetaName, locMetaValue);;
		InvertersUtil.setMetadata(genVo, genMetaName, genMetaValue);;
		
		assertEquals(cliMetaValue, InvertersUtil.getMetadata(cliVo, cliMetaName));
		assertEquals(locMetaValue, InvertersUtil.getMetadata(locVo, locMetaName));
		assertEquals(genMetaValue, InvertersUtil.getMetadata(genVo, genMetaName));
		
		
		assertEquals(cliMetaValue, InvertersUtil.getMetadata(null, null, cliVo, cliMetaName));
		assertEquals(locMetaValue, InvertersUtil.getMetadata(null, locVo, cliVo, locMetaName));
		assertEquals(genMetaValue, InvertersUtil.getMetadata(genVo, locVo, cliVo, genMetaName));
	}
}
