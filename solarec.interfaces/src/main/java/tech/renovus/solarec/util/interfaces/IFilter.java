package tech.renovus.solarec.util.interfaces;

/**
 * Contains the logic to determinate if an <code>Object</code> must be filter from a <code>Collection</code>.
 * @param <T> The <code>Object</code> that can analyze
 * 
 * @see uy.com.pf.sdk.util.CollectionUtil#filter(java.util.ArrayList, IFilter)
 * @see uy.com.pf.sdk.util.CollectionUtil#filter(java.util.Collection, IFilter)
 */
public interface IFilter<T extends Object> {

	/**
	 * Analyze an object to see if has to be filter (removed) from a <code>Collection</code>.
	 * 
	 * @param value	The <code>Object</code> to analyze
	 * @return	<code>true</code> if the object evaluated successfully, <code>false</code> otherwise.
	 */
	boolean evaluate(T value);
}
