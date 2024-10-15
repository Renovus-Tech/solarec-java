package tech.renovus.solarec.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BooleanUtilsTest {

	@Test public void testTest() {
		
		assertFalse(BooleanUtils.isTrue((Object) null));
		assertTrue(BooleanUtils.isTrue((Object) "true"));
		assertTrue(BooleanUtils.isTrue((Object) "t"));
		assertTrue(BooleanUtils.isTrue((Object) "on"));
		assertTrue(BooleanUtils.isTrue((Object) "1"));
		assertTrue(BooleanUtils.isTrue((Object) "yes"));
		assertTrue(BooleanUtils.isTrue((Object) "si"));
		assertTrue(BooleanUtils.isTrue((Object) "s"));

		assertTrue(BooleanUtils.isTrue((Object) Integer.valueOf(1)));
		assertTrue(BooleanUtils.isTrue((Object) Double.valueOf(1)));
		assertTrue(BooleanUtils.isTrue((Object) Boolean.TRUE));
		
		assertFalse(BooleanUtils.isTrue((Integer) null));
		assertFalse(BooleanUtils.isTrue((Double) null));
		assertFalse(BooleanUtils.isTrue((Boolean) null));
		
		assertNull(BooleanUtils.toString((Boolean) null));
		
		assertEquals("true,false", BooleanUtils.toString(new boolean[] {true, false}, ","));
		
		assertTrue(BooleanUtils.isOneTrue(new boolean[] {true, false}));
		assertTrue(BooleanUtils.isOneTrue(new boolean[] {true, false, true}));
		assertFalse(BooleanUtils.isOneTrue(new boolean[] {false, false, false}));
		
		assertTrue(BooleanUtils.isOneTrue(new boolean[] {true, false}, 1));
		assertTrue(BooleanUtils.isOneTrue(new boolean[] {true, false, true}, 0));
		assertFalse(BooleanUtils.isOneTrue(new boolean[] {false, false, false}, 1));
	}
	
}
