package tech.renovus.solarec.util.interfaces;

/**
 * Indicates that the method that implements this class must be clone instead of inserted to
 * a <code>Collection</code>
 * 
 * @see uy.com.pf.sdk.util.CollectionUtil#clone(java.util.Collection)
 */
public interface Cloneable extends java.lang.Cloneable {

	/**
	 * Clones the current instances and returns a new instance with the information cloned.
	 * 
	 * @return								A new instance of the object.
	 * @throws CloneNotSupportedException	In case of an error while cloning.
	 */
	Object clone() throws CloneNotSupportedException;
}
