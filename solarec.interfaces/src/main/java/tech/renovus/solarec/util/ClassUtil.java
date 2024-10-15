package tech.renovus.solarec.util;

/**
 * The <code>ClassUtil</code> class contains useful methods to work with classes and retrieve information from them.
 */
public final class ClassUtil {

	/**
	 * Returns the name of the <code>Class</code> without the package name in int.
	 * 
	 * @param aClass	A <code>Class</code>
	 * @return			The class name
	 */
	public static String getClassName(Class<?> aClass) {
		int pos = aClass.getName().lastIndexOf(".");
		if (pos != -1) {
			return aClass.getName().substring(pos + 1);
		}
		
		return aClass.getName();
	}
	
	/**
	 * Determinate if two instances of different objects are equals. If both instances are not <code>null</code> then
	 * the the <b>obj1.equals()</b> method is call and return its value. If both instances are <code>null</code> then 
	 * <code>true</code> is return, otherwise, <code>false</code> is return.
	 * 
	 * @param obj1	The first instance object to compare
	 * @param obj2	The second instance object to compare
	 * @return		<code>true</code> if both instances are equals according to the <b>obj1</b>.<code>equals()</code> method or both are <code>null</code>, <code>false</code> otherwise
	 * 
	 * @see #equalsNotNull(Object, Object)
	 */
	public static boolean equals(Object obj1, Object obj2) {
		if (obj1 != null && obj2 != null) {
			return obj1.equals(obj2);
		
		} else if (obj1 == null && obj2 == null) {
			return true;
		}
		
		return false;
	}
}
