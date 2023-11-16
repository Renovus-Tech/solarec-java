package tech.renovus.solarec.util;

import tech.renovus.solarec.util.interfaces.IFlags;

/**
 * The <code>FlagUtil</code> class contains useful methods to work with <code>IFlags</code> and retrieve/set 
 * information from it.
 * 
 * <p>The flags are a series of <code>IFlags.FLAG_VALUE_TRUE</code> and <code>IFlags.FLAG_VALUE_FALSE</code> in
 * a <code>String</code> each position represents the value for a specific actions/method/command/etc.</p>
 * 
 * @see uy.com.pf.sdk.util.interfaces.IFlags
 */
public final class FlagUtil {

	//--- Constructor ---------------------------
	private FlagUtil() {
	}
	
	//--- Helpful methods -----------------------
	/**
	 * Returns the value of a flag in the position <b>pos</b> of the flag sequence <b>flags</b>. It the <b>pos</b>
	 * is negative, then the value of the flag will be return as its opposite.
	 * 
	 * @param flags	The flag sequence
	 * @param pos	The position to retrieve the value from
	 * @return		<code>true</code> if the position at <b>pos</b> is equals to <code>IFlags.FLAG_VALUE_TRUE</code>, <code>false</code> otherwise.
	 */
	public static boolean getFlagValue(String flags, int pos) {
		boolean negate = pos < 0;
		boolean result = false;
		pos = Math.abs(pos);
		if ((flags != null) && (flags.length() >= (pos + 1))) {
			result = flags.charAt(pos) == IFlags.FLAG_CHAR_TRUE;
		}
		return negate ? !result : result;
	}
	
	/**
	 * Sets a flag value, defined by <b>value</b> into the position <b>pos</b> in the flags <b>flags</b>. If the <b>flags</b> has no 
	 * other values before the <b>pos</b> all flags will be defined as <code>IFlags.FLAG_VALUE_FALSE</code>.
	 * 
	 * <p>If <b>flags</b> is <b>01</b> and <b>pos</b> is <b>3</b> and <b>value</b> es <code>true</code> the result will be <b>0101</b>.</p>
	 * 
	 * @param flags		The flags where to set the new value
	 * @param pos		The flag position to set
	 * @param value		The value to set to the flag.
	 * @return			The new flags sequence
	 */
	public static String setFlagValue(String flags, int pos, boolean value) {
		String flag = value ? IFlags.FLAG_VALUE_TRUE : IFlags.FLAG_VALUE_FALSE;

		if (flags == null) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i <= pos; i++) {
				buffer.append(IFlags.FLAG_VALUE_FALSE);			
			}
			flags = buffer.toString();
			
		} else while (flags.length() < pos) {
			flags += IFlags.FLAG_VALUE_FALSE;
		}
		
		return flags.substring(0,pos) + flag + ((pos >= flags.length())?"":flags.substring(pos+1));
	}

	/**
	 * Sets a flag value, defined by <b>value</b> into the position <b>pos</b> in the flags <b>flags</b> to use as part of a SQL sentence. 
	 * If the <b>flags</b> has no other values before the <b>pos</b> all flags will be defined as <code>FLAG_SQL_UNKNOW_VALUE</code>.
	 * 
	 * <p>If <b>flags</b> is <b>01</b> and <b>pos</b> is <b>3</b> and <b>value</b> es <code>true</code> the result will be <b>01_1</b>.</p>
	 * 
	 * @param flags		The flags where to set the new value
	 * @param pos		The flag position to set
	 * @param value		The value to set to the flag.
	 * @return			The new flags sequence
	 */
	public static String getSqlFlagValue(String flags, int pos, Boolean value) {
		return FlagUtil.getSqlFlagValue(flags, pos, value, true);
	}
	
	public static String getSqlFlagValue(String flags, int pos, Boolean value, boolean addPorcentaje) {
		String flag = value == null ? IFlags.FLAG_SQL_UNKNOW_VALUE : value ? IFlags.FLAG_VALUE_TRUE : IFlags.FLAG_VALUE_FALSE;
		
		if (addPorcentaje && StringUtil.notEmpty(flags) && flags.endsWith("%")) flags = flags.substring(0, flags.length() - 1);
		
		if (flags == null) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i <= pos; i++) {
				buffer.append(IFlags.FLAG_SQL_UNKNOW_VALUE);			
			}
			flags = buffer.toString();
		} else while (flags.length() < pos) {
			flags += IFlags.FLAG_SQL_UNKNOW_VALUE;
		}
		
		if (addPorcentaje && StringUtil.noNull(flags).endsWith("%")) addPorcentaje = false;
		
		return flags.substring(0,pos) + flag + ((pos >= flags.length()) ? "" : flags.substring(pos+1)) + (addPorcentaje ? "%" : StringUtil.EMPTY_STRING);
	}
	
	/**
	 * Sets a flag value, defined by <b>value</b> into the position <b>pos</b> in the flags of an object that implements
	 * <code>IFlags</code>. If the <b>flags</b> has no other values before the <b>pos</b> all flags will be defined 
	 * as <code>IFlags.FLAG_VALUE_FALSE</code>.
	 * 
	 * @param vo	The instance where to set the flag
	 * @param pos	The position of the flat to set
	 * @param value	The flag value to set
	 * 
	 * @see #setFlagValue(String, int, boolean)
	 * @see uy.com.pf.sdk.util.interfaces.IFlags#getFlags()
	 */
	public static void setFlagValue(IFlags vo, int pos, boolean value) {
		vo.setFlags(FlagUtil.setFlagValue(vo.getFlags(), pos, value));
	}
	
	/**
	 * Returns the value of a flag in the position <b>pos</b> of an object that implements <code>IFlags</code>. It the <b>pos</b>
	 * is negative, then the value of the flag will be return as its opposite.
	 * 
	 * @param vo	The instance where to get the flags
	 * @param pos	The position of the flat to get
	 * @return		The flag value
	 * 
	 * @see #getFlagValue(String, int)
	 * @see uy.com.pf.sdk.util.interfaces.IFlags#setFlags(String)
	 */
	public static boolean getFlagValue(IFlags vo, int pos) {
		if (vo == null) return false;
		return FlagUtil.getFlagValue(vo.getFlags(), pos);
	}
	
	/**
	 * Checks is at least one flag is of an object that implements <code>IFlags</code> is <code>true</code>. The flags to check
	 * are the ones defined in <b>positions</b>.
	 * 
	 * @param vo		The instance where to get the flags
	 * @param positions	The position to check
	 * @return			<code>true</code> if at least one flat is <code>true</code>.
	 */
	public static boolean isOneTrue(IFlags vo, int... positions) {
		if (positions == null) return false;
		if (vo == null) return false;
		
		for (int pos : positions) {
			if (FlagUtil.getFlagValue(vo, pos)) return true;
		}
		
		return false;
	}
	
	/**
	 * Determinate if all flags are false (returning true). If one is true, returns false.
	 * 
	 * @param flags
	 * @return
	 */
	public static boolean allFalse(String flags) {
		if (StringUtil.isEmpty(flags)) return true;
		for (int i = 0; i < flags.length(); i++) if (FlagUtil.getFlagValue(flags, i)) return false;
		return true;
	}


	public static void adjustFlags(IFlags vo, int amount) {
		String flags = vo.getFlags();
		
		if (flags == null) flags = StringUtil.EMPTY_STRING;
		
		while (flags.length() < amount) {
			flags += IFlags.FLAG_VALUE_FALSE;
		}
		
		vo.setFlags(flags);
	}
	
	public static boolean flagSet(IFlags vo, int position) {
		if (StringUtil.isEmpty(vo.getFlags())) return false;
		return position < vo.getFlags().length();
	}

}
