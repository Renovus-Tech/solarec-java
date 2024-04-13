package tech.renovus.solarec.vo.comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tech.renovus.solarec.vo.db.data.GeneratorVo;

public class ComparatorTests {

	//--- Private methods -----------------------
	private GeneratorVo createGenerator1() {
		GeneratorVo result = new GeneratorVo();
		
		result.setGenCode("Code 1");
		
		return result;
	}

	private GeneratorVo createGenerator2() {
		GeneratorVo result = new GeneratorVo();
		
		result.setGenCode("Code 2");
		
		return result;
	}
	
	private GeneratorVo createGenerator3() {
		GeneratorVo result = new GeneratorVo();
		
		result.setGenCode("3");
		
		return result;
	}

	//--- Test methods --------------------------
	@Test public void testGeneratorGenCodeAsNumberComparator() {
		GeneratorGenCodeAsNumberComparator instance = GeneratorGenCodeAsNumberComparator.getInstance();
		GeneratorVo gen1 = this.createGenerator1();
		GeneratorVo gen2 = this.createGenerator2();
		GeneratorVo gen3 = this.createGenerator3();
		
		assertEquals(0, instance.compare(gen1, gen1));
		assertEquals(0, instance.compare(gen3, gen3));
		assertTrue(instance.compare(gen1, gen2) < 0);
		assertTrue(instance.compare(gen1, gen3) > 0);
	}

	@Test public void testStringGenCodeAsNumberComparator() {
		StringGenCodeAsNumberComparator instance = StringGenCodeAsNumberComparator.getInstance();
		
		GeneratorVo gen1 = this.createGenerator1();
		GeneratorVo gen2 = this.createGenerator2();
		GeneratorVo gen3 = this.createGenerator3();
		
		assertEquals(0, instance.compare(gen1.getGenCode(), gen1.getGenCode()));
		assertEquals(0, instance.compare(gen3.getGenCode(), gen3.getGenCode()));
		assertTrue(instance.compare(gen1.getGenCode(), gen2.getGenCode()) < 0);
		assertTrue(instance.compare(gen1.getGenCode(), gen3.getGenCode()) > 0);
	}
}
