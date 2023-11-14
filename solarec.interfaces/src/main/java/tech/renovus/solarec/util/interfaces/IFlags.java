package tech.renovus.solarec.util.interfaces;

/**
 * The <code>IFlags</code> interface is used by objects that have a attribute/column that
 * represents flags, where each flag represents <code>true</code>/<code>false</code> of a
 * property.
 * 
 * @see uy.com.pf.sdk.util.FlagUtil
 * 
 */
public interface IFlags {
	
	/** Value for flags in <code>true</code>. The value is <b>1</b>. */
	public static final String FLAG_VALUE_TRUE			= "1";
	
	/** Value for flags in <code>true</code>. The value is <b>1</b>. */
	public static final char FLAG_CHAR_TRUE				= IFlags.FLAG_VALUE_TRUE.charAt(0);
	
	/** Value for flags in <code>false</code>. The value is <b>0</b>. */
	public static final String FLAG_VALUE_FALSE			= "0";
	
	/** Value for flags in <code>false</code>. The value is <b>0</b>. */
	public static final char FLAG_CHAR_FALSE			= IFlags.FLAG_VALUE_FALSE.charAt(0);
	
	/** Value for flags with unknown value in SQL. The value is <b>_</b>. */
	public static final String FLAG_SQL_UNKNOW_VALUE	= "_";
	
	/**
	 * Returns a <code>String</code> with the current flags of the object
	 * 
	 * @return	The flags of the object.
	 */
	String getFlags();
	
	/**
	 * Sets the new flags of the object
	 * 
	 * @param flags	The flags to set.
	 */
	void setFlags(String flags);
}
