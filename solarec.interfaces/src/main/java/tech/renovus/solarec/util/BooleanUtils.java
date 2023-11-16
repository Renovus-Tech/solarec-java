package tech.renovus.solarec.util;

/**
 * The <code>BooleanUtil</code> class contains useful methods to work with boolean values.
 */
public final class BooleanUtils {
	
	//--- Public constants ----------------------
	/** String constants representing the <b>true</b> value. */
	public static final String TRUE_STRING	= "true";
	
	/** String constants representing the <b>false</b> value. */
	public static final String FALSE_STRING	= "false";
	
	//--- Constructor ---------------------------
	private BooleanUtils() {
	}
	
	//--- Helpful methods -----------------------
	/**
	 * Determinate if the instance of the class <code>Object</code> is <code>true</code>, by
	 * first checking the class instance of the object. If <b>value</b> is <code>null</code> 
	 * then <code>false</code> is return.
	 * 
	 * @param value	The instance of <code>Object</code> to check.
	 * @return <code>true</code> is the instance represents a positive value.
	 * 
	 * @see #isTrue(Boolean)
	 * @see #isTrue(Double)
	 * @see #isTrue(Integer)
	 * @see #isTrue(String)
	 */
	public static boolean isTrue(Object value) {
		if (value == null) return false;

		if (value instanceof String) {
			return BooleanUtils.isTrue((String) value);
		} else if (value instanceof Integer) {
			return BooleanUtils.isTrue((Integer) value);
		} else if (value instanceof Double) {
			return BooleanUtils.isTrue((Double) value);
		} else if (value instanceof Boolean) {
			return BooleanUtils.isTrue((Boolean) value);
		}
		
		return false;
	}
	
	/**
	 * Determinate if the instance of the class <code>String</code> represents a positive value. An instance
	 * of <code>String</code> is consider as positive if it is equals (ignore case) to:
	 * <ul>
	 * 	<li>true</li>
	 * 	<li>on</li>
	 * 	<li>1</li>
	 * 	<li>y</li>
	 * 	<li>yes</li>
	 * </ul>
	 * 
	 * @param value	The instance of <code>String</code> to check.
	 * @return		<code>true</code> is the instance represents a positive value.
	 */
	public static boolean isTrue(String value) {
		if (value == null) return false;
		return BooleanUtils.TRUE_STRING.equalsIgnoreCase(value) || "on".equalsIgnoreCase(value) || "1".equals(value) || "y".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || "si".equalsIgnoreCase(value) || "s".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value) || "t".equalsIgnoreCase(value);
	}
	
	/**
	 * Determinate if the instance of the class <code>Integer</code> represents a positive value. An instance
	 * of <code>Integer</code> is consider as positive if it value is <b>1</b>, otherwise returns <code>false</code>. If
	 * <b>value</b> is <code>null</code> then <code>false</code> is return.
	 * 
	 * @param value	The instance of <code>Integer</code> to check.
	 * @return		<code>true</code> is the instance represents a positive value.
	 */
	public static boolean isTrue(Integer value) {
		if (value == null) return false;
		return value.intValue() == 1;
	}
	
	/**
	 * Determinate if the instance of the class <code>Double</code> represents a positive value. An instance
	 * of <code>Double</code> is consider as positive if it value is <b>1.0</b>, otherwise returns <code>false</code>. If
	 * <b>value</b> is <code>null</code> then <code>false</code> is return.
	 * 
	 * @param value	The instance of <code>Double</code> to check.
	 * @return		<code>true</code> is the instance represents a positive value.
	 */
	public static boolean isTrue(Double value) {
		if (value == null) return false;
		return value.intValue() == 1;
	}
	
	/**
	 * Determinate if the instance of the class <code>Boolean</code> represents a positive value. An instance
	 * of <code>Boolean</code> is consider as positive if <code>value.booleanValue()</code> returns <code>true</code>. If
	 * <b>value</b> is <code>null</code> then <code>false</code> is return.
	 * 
	 * @param value	The instance of <code>Double</code> to check.
	 * @return		<code>true</code> is the instance represents a positive value.
	 */
	public static boolean isTrue(Boolean value) {
		if (value == null) return false;
		return value.booleanValue();
	}
	
	/**
	 * Converts a <code>boolean</code> value into a <code>String</code> value. The values returned may be the
	 * constants <code>BooleanUtils.TRUE_STRING</code> or <code>BooleanUtils.FALSE_STRING</code>.
	 * 
	 * @param value	The <code>boolean</code> to convert.
	 * @return		A <code>String</code> representing the <code>boolean</code> value.
	 */
	public static String toString(boolean value) {
		return value ? BooleanUtils.TRUE_STRING : BooleanUtils.FALSE_STRING;
	}
	
	/**
	 * Converts a <code>Boolean</code> value into a <code>String</code> value. The values returned may be the
	 * constants <code>BooleanUtils.TRUE_STRING</code> or <code>BooleanUtils.FALSE_STRING</code> or <code>null</code>
	 * in case that <b>value</b> is <code>null</code>.
	 * 
	 * @param value	The <code>boolean</code> to convert.
	 * @return		A <code>String</code> representing the <code>boolean</code> value.
	 * 
	 * @see #toString(boolean)
	 */
	public static String toString(Boolean value) {
		if (value == null) return null;
		return BooleanUtils.toString(value.booleanValue());
	}
	
	/**
	 * Converts a series of <code>boolean</code> values a to a <code>String</code>, all the values will be
	 * separated by the <b>separator</b>.
	 * 
	 * @param values		Values to convert
	 * @param separator		Separator of the values
	 * @return				A <code>String</code> with all the values separated by <b>separator</b>
	 */
	public static String toString(boolean[] values, String separator) {
		StringBuffer buffer = new StringBuffer();
		for (boolean value : values) {
			if (buffer.length() > 0) buffer.append(separator);
			buffer.append(BooleanUtils.toString(value));
		}
		return buffer.toString();
	}
	
	/**
	 * Check if at least one of the values in <b>values</b> is <code>true</code>. If there are no values or no one
	 * is <code>true</code>, then <code>false</code> is return.
	 * 
	 * @param values	Values to check
	 * @return			<code>true</code> if at least one of the values is <code>true</code>, otherwise returns <code>false</code>.
	 * 
	 * @see #isOneTrue(boolean[], int)
	 */
	public static boolean isOneTrue(boolean[] values) {
		for (boolean value : values) {
			if (value) return true;
		}
		return false;
	}
	
	/**
	 * Check if at least one of the values in <b>values</b> is <code>true</code>, except a specific position. If there are no values or no one
	 * is <code>true</code>, then <code>false</code> is return.
	 * 
	 * @param values	Values to check
	 * @param exceptPosition The position that wont be checked
	 * @return			<code>true</code> if at least one of the values is <code>true</code>, otherwise returns <code>false</code>.
	 * 
	 * @see #isOneTrue(boolean[])
	 */
	public static boolean isOneTrue(boolean[] values, int exceptPosition) {
		int currentPosition = 0;
		for (boolean value : values) {
			if (currentPosition ++ == exceptPosition) continue;
			if (value) return true;
		}
		return false;
	}
}
