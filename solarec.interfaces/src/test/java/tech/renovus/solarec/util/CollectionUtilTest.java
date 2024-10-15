package tech.renovus.solarec.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class CollectionUtilTest {

	private void assertEqualsCollection(Collection<String> volExpected, Collection<String> colValues) {
		Iterator<String> itExpected = volExpected.iterator();
		Iterator<String> itValues = colValues.iterator();
		
		while (itValues.hasNext() && itExpected.hasNext()) {
			assertEquals(itExpected.next(), itValues.next());
		}
	}
	private void assertEqualsArray(String[] volExpected, String[] colValues) {

		assertTrue((volExpected == null) == (colValues == null));
		
		if (volExpected == null) {
			return;
		}
		
		assertEquals(volExpected.length, colValues.length);
		
		for (int i = 0; i < volExpected.length; i++) {
			assertEquals(volExpected[i], colValues[i]);
		}
		
	}
	
	@Test public void testArray() {
		String[] values = {"1", "2", "3" };
		String[] values1 = {"2", "3" };
		String[] values2 = {"2",};
		
		assertEqualsArray(values, CollectionUtil.remove(values, null));
		assertEqualsArray(values1, CollectionUtil.remove(values, Arrays.asList(0)));
		assertEqualsArray(values2, CollectionUtil.remove(values, Arrays.asList(0, 2)));
	}
	
	@Test public void testMaps() {
		HashMap<String, String> values = new HashMap<>();
		
		assertTrue(CollectionUtil.isEmpty((Map<?,?>) null));
		assertTrue(CollectionUtil.isEmpty(new HashMap<Object, Object>(0)));

		assertFalse(CollectionUtil.notEmpty((Map<?,?>) null));
		assertFalse(CollectionUtil.notEmpty(new HashMap<Object, Object>(0)));

		CollectionUtil.addValueAvoidNullAdd(values, "1", (String) null);
		assertTrue(CollectionUtil.isEmpty(values));
		
		values.put("1", "3");
		values.put("2", "4");
		assertEquals(2, CollectionUtil.size(values));
		assertNull(CollectionUtil.getValue(null, "1", "2"));
		assertEquals("3", CollectionUtil.getValue(values, "1", "2"));
		assertEquals("4", CollectionUtil.getValue(values, "5", "2"));
		assertNull(CollectionUtil.getValue(values, "5", "6"));
		
		assertNull(CollectionUtil.getValue((HashMap<String, String>) null, "1"));
		assertEquals("3", CollectionUtil.getValue(values, "1"));
		assertNull(CollectionUtil.getValue(values, "5"));
		
		assertTrue(CollectionUtil.existKey(values, "1"));
		assertFalse(CollectionUtil.existKey(values, "6"));
	}
	
	
	@Test public void testCollections() {
		Collection<String> values = Arrays.asList("1","2","3","4");
		Collection<String> values1 = Arrays.asList("1");
		Collection<String> values2 = Arrays.asList("4");
		Collection<String> values3 = Arrays.asList("2","3");
		ArrayList<String> values4 = new ArrayList<>(values);
		
		assertEquals(values, CollectionUtil.subCollection(values, null, null));
		assertEquals(values, CollectionUtil.subCollection(values, -1, 20));
		assertEquals(values1, CollectionUtil.subCollection(values, 0, 1));
		assertEquals(values2, CollectionUtil.subCollection(values, 3, 1));
		assertEquals(values2, CollectionUtil.subCollection(values, 3, 10));
		assertEquals(values3, CollectionUtil.subCollection(values, 1, 2));
		assertEquals(values3, CollectionUtil.subCollection(values4, 1, 2));
		
		assertEquals(Arrays.asList(values), CollectionUtil.subCollections(values, -1));
		
		assertTrue(CollectionUtil.isEmpty((Collection<?>) null));
		assertTrue(CollectionUtil.isEmpty(new ArrayList<Object>(0)));
		assertFalse(CollectionUtil.isEmpty(values));

		assertFalse(CollectionUtil.notEmpty((Collection<?>) null));
		assertFalse(CollectionUtil.notEmpty(new ArrayList<Object>(0)));
		assertTrue(CollectionUtil.notEmpty(values));

		assertTrue(CollectionUtil.isEmpty((Set<?>) null));
		assertTrue(CollectionUtil.isEmpty(new HashSet<Object>(0)));

		assertFalse(CollectionUtil.notEmpty((Set<?>) null));
		assertFalse(CollectionUtil.notEmpty(new HashSet<Object>(0)));
		
		assertTrue(CollectionUtil.notEmpty(values, values2));
		assertFalse(CollectionUtil.notEmpty(values, null));
		
		assertFalse(CollectionUtil.isEmpty(null, values2));
		assertTrue(CollectionUtil.isEmpty(null, null));
		
		Collection<String> cloned = CollectionUtil.clone(values);

		assertEquals(values.size(), cloned.size());
		
		this.assertEqualsCollection(values, cloned);
		
		assertEquals("2", CollectionUtil.getValue(values, "2"));
		assertNull(CollectionUtil.getValue(values, "5"));
		
		assertEquals(0, CollectionUtil.size((Collection<?>) null));
		assertEquals(4, CollectionUtil.size(values));
		
		assertEquals(2, CollectionUtil.size("1", "2"));
		
		Collection<String> result = new ArrayList<>();
		CollectionUtil.addAll(result, values1);
		CollectionUtil.addAll(result, values3);
		CollectionUtil.addAll(result, values2);
		this.assertEqualsCollection(values, result);

		CollectionUtil.addAll(result, Arrays.asList((String) null, (String) null));
		this.assertEqualsCollection(values, result);
		
		result.add(null);
		result = CollectionUtil.removeEmpty(result);
		this.assertEqualsCollection(values, result);
	
		CollectionUtil.clear(result);
		assertTrue(CollectionUtil.isEmpty(result));
	}

}
