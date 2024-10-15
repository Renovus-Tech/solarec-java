package tech.renovus.solarec.util.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import org.junit.Test;

import tech.renovus.solarec.util.interfaces.ISynchronizable;

public class BaseDBUtilTest {
	
	public static class TestVo extends BaseDbVo implements ISynchronizable {
		int value;
		boolean wasSync = false;
		boolean wasSyncChilds = false;
		BaseDbVo syncWith = null;

		public TestVo(int value) {
			this.value = value;
		}
		
		@Override
		public boolean isSame(Object obj) {
			if (! (obj instanceof TestVo)) {
				return false;
			}
			TestVo vo = (TestVo) obj;
			return vo.value == this.value;
		}

		@Override
		public boolean validData() {
			return this.value != -1;
		}

		@Override
		public void synchronize(BaseDbVo aVo) {
			this.syncWith = aVo;
			this.wasSync = true;
			
		}

		@Override
		public void synchronizeForce(int syncType) {
			this.wasSync = true;
			this.setSyncType(syncType);
		}

		@Override
		public void setChildrensId() {
			this.wasSyncChilds = false;
		}

		@Override
		public int hashCode() {
			return Objects.hash(value);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			TestVo other = (TestVo) obj;
			return value == other.value;
		}
	}

	@Test  public void testSetAll() {
		Collection<TestVo> values = Arrays.asList(
										new TestVo(1),
										new TestVo(2)
										);
		
		BaseDbUtil.setAll(values, TestVo.SYNC_INSERT_NO_AUDIT);
		
		for (TestVo vo : values) {
			assertEquals(TestVo.SYNC_INSERT_NO_AUDIT, vo.getSyncType());
			assertTrue(vo.wasSync);
			assertFalse(vo.wasSyncChilds);
		}
		
	}
	
	@Test  public void testSetAll2() {
		Collection<TestVo> values = Arrays.asList(
										new TestVo(1),
										new TestVo(2)
										);
		
		BaseDbUtil.setAll(values, TestVo.SYNC_INSERT_NO_AUDIT, false);
		
		for (TestVo vo : values) {
			assertEquals(TestVo.SYNC_INSERT_NO_AUDIT, vo.getSyncType());
			assertFalse(vo.wasSync);
			assertFalse(vo.wasSyncChilds);
		}
	}
	
	@Test public void testGetObject() {
		Collection<TestVo> values = Arrays.asList(
				new TestVo(1),
				new TestVo(2)
			);
		
		assertEquals(new TestVo(2), BaseDbUtil.getObject(values, new TestVo(2)));
		assertNull(BaseDbUtil.getObject(values, new TestVo(3)));
	}
	
	@Test public void testCompareCollections() {
		Collection<TestVo> values1 = Arrays.asList(
				new TestVo(1),
				new TestVo(2)
			);
		Collection<TestVo> values2 = Arrays.asList(
				new TestVo(2),
				new TestVo(3)
			);
		
		Collection<TestVo> allToInsert =  BaseDbUtil.compareCollections(values1, null, TestVo.SYNC_INSERT, TestVo.SYNC_DELETE);
		for (TestVo vo : allToInsert) {
			assertEquals(TestVo.SYNC_INSERT, vo.getSyncType());
		}
		
		Collection<TestVo> allToDelete =  BaseDbUtil.compareCollections(null, values2, TestVo.SYNC_INSERT, TestVo.SYNC_DELETE);
		for (TestVo vo : allToDelete) {
			assertEquals(TestVo.SYNC_DELETE, vo.getSyncType());
		}
		
		Collection<TestVo> mix =  BaseDbUtil.compareCollections(values1, values2, TestVo.SYNC_INSERT, TestVo.SYNC_DELETE);
		for (TestVo vo : mix) {
			int expected = -1;
			switch (vo.value) {
				case 1: expected = TestVo.SYNC_INSERT; break;
				case 2: expected = TestVo.SYNC_INIT; break;
				case 3: expected = TestVo.SYNC_DELETE; break;
			}
			assertEquals(expected, vo.getSyncType());
		}
		
		assertEquals(Arrays.asList(new TestVo(1)), BaseDbUtil.getAllFor(mix, TestVo.SYNC_INSERT));
		assertEquals(new ArrayList<TestVo>(), BaseDbUtil.getAllFor(mix, TestVo.SYNC_INSERT_UPDATE));
	}
	
	@Test public void testSimpleMethods() {
		Date aDate = new Date();
		String user = "a user";
		
		TestVo vo = new TestVo(1);
		vo.setAuditDate(aDate);
		vo.setAuditUser(user);
		
		assertEquals(aDate, vo.getAuditDate());
		assertEquals(user, vo.getAuditUser());
		assertEquals("", vo.getLblDeps());
	}
}
