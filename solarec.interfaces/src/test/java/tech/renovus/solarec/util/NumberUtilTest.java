package tech.renovus.solarec.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class NumberUtilTest {

	@Test public void testTest() {
		
		assertNull(NumberUtil.truncate(null, 2));
		assertEquals(Double.valueOf("1.123"), NumberUtil.truncate(Double.valueOf("1.1234232"), 3));
		
		assertEquals(Double.valueOf(1.123), Double.valueOf(NumberUtil.truncate(1.1234232323, 3)));
	}
	
}
