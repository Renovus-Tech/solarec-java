package tech.renovus.solarec.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ClassUtilTest {

	@Test public void test() {
		assertEquals("Integer", ClassUtil.getClassName(Integer.class));
		assertTrue(ClassUtil.equals(null, null));
		assertFalse(ClassUtil.equals(Integer.valueOf(1), null));
		assertFalse(ClassUtil.equals(null, Integer.valueOf(2)));
		assertFalse(ClassUtil.equals(Integer.valueOf(1), Integer.valueOf(2)));
		assertTrue(ClassUtil.equals(Integer.valueOf(1), Integer.valueOf(1)));
	}
}
