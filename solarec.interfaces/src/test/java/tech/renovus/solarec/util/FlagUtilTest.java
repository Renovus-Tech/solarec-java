package tech.renovus.solarec.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tech.renovus.solarec.util.interfaces.IFlags;

public class FlagUtilTest {
	
	private static final class Flags implements IFlags {

		String flags;

		private Flags() {
			
		}
		private Flags(String flags) {
			this.flags = flags;
		}
		
		@Override
		public String getFlags() {
			return this.flags;
		}

		@Override
		public void setFlags(String flags) {
			this.flags = flags;
		}
		
	}

	@Test public void test() {
		
		assertFalse(FlagUtil.getFlagValue((String) null, 0));
		assertFalse(FlagUtil.getFlagValue("", 2));
		assertFalse(FlagUtil.getFlagValue("0", 2));
		assertFalse(FlagUtil.getFlagValue("0", 1));
		assertTrue(FlagUtil.getFlagValue("1", 0));
		
		assertEquals("1", FlagUtil.setFlagValue((String) null, 0, true));
		assertEquals("1", FlagUtil.setFlagValue("", 0, true));
		assertEquals("1", FlagUtil.setFlagValue("1", 0, true));
		assertEquals("1", FlagUtil.setFlagValue("0", 0, true));
		
		assertEquals("11", FlagUtil.setFlagValue("1", 1, true));
		assertEquals("01", FlagUtil.setFlagValue("0", 1, true));
		assertEquals("01", FlagUtil.setFlagValue("00", 1, true));
		
		assertEquals("0%", FlagUtil.getSqlFlagValue("", 0, Boolean.FALSE));
		assertEquals("1%", FlagUtil.getSqlFlagValue("", 0, Boolean.TRUE));
		assertEquals("1_1%", FlagUtil.getSqlFlagValue("1", 2, Boolean.TRUE));
		assertEquals("__0%", FlagUtil.getSqlFlagValue(null, 2, Boolean.FALSE));
		assertEquals("__1%", FlagUtil.getSqlFlagValue(null, 2, Boolean.TRUE));
		
		
		Flags flags = new Flags("0001");
		assertTrue(FlagUtil.isOneTrue(flags, 1,3));
		assertFalse(FlagUtil.isOneTrue(flags, 1,2));
		
		flags = new Flags((String) null);
		FlagUtil.setFlagValue(flags, 0, true);
		assertEquals("1", flags.flags);
		
		assertTrue(FlagUtil.allFalse(null));
		assertTrue(FlagUtil.allFalse(""));
		assertTrue(FlagUtil.allFalse("00000"));
		assertFalse(FlagUtil.allFalse("000100"));
		
		flags = new Flags("");
		FlagUtil.adjustFlags(flags, 4);
		assertEquals("0000", flags.flags);

		flags = new Flags("100");
		assertFalse(FlagUtil.flagSet(flags, 4));
		assertTrue(FlagUtil.flagSet(flags, 1));
	}
}
