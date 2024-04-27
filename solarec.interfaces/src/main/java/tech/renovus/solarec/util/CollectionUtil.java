package tech.renovus.solarec.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import tech.renovus.solarec.util.interfaces.IFilter;


/**
 * The <code>CollectionUtil</code> class contains useful methods to work with <code>Collection</code> and retrieve 
 * information from them.
 */
public final class CollectionUtil {

	//--- Constructor ---------------------------
	private CollectionUtil() {
	}
	
	//--- Helpful methods -----------------------
	/**
	 * Returns a <code>Collection</code> where the first item is at the position <b>start</b> of <b>col</b> and will not
	 * have more than <b>count</b> elements.
	 * 
	 * @param col	The <code>Collection</code> from where retrieve the elements
	 * @param start	The first element to add to the result
	 * @param count	The number of item in the result
	 * @return		A <code>Collection</code> of item retrieve from <b>col</b>, where the size will not be grater than <b>count</b>
	 * 
	 * @see #subCollection(Collection, Integer, Integer)
	 * @see #subCollection(ArrayList, Integer, Integer)
	 */
	public static <T extends Object> Collection<T> subCollection(Collection<T> col, int start, int count) {
		return CollectionUtil.subCollection(col, Integer.valueOf(start), Integer.valueOf(count));
	}
	
	/**
	 * Returns a <code>Collection</code> where the first item is at the position <b>start</b> of <b>col</b> and will not
	 * have more than <b>count</b> elements. If <b>start</b> is <code>null</code> then the first element of <b>col</b> will 
	 * be the first element of the result. If <b>count</b> is <code>null</code> then the last element of <b>col</b> will be 
	 * the last element of the result.
	 * 
	 * <p>If <b>col</b> is <code>null</code> or <code>start >= col.size()</code> then a new instance of <code>ArrayList</code>
	 * will be return.</p>
	 * 
	 * <p>If <b>col</b> is detected as an instance of <code>ArrayList</code> then the method <code>subCollection(ArrayList, Integer, Integer)</code>
	 * will be call.</p>
	 * 
	 * @param col	The <code>Collection</code> from where retrieve the elements
	 * @param start	The first element to add to the result
	 * @param count	The number of item in the result
	 * @return		A <code>Collection</code> of item retrieve from <b>col</b>, where the size will not be grater than <b>count</b>
	 * 
	 * @see #subCollection(Collection, int, int)
	 * @see #subCollection(ArrayList, Integer, Integer)
	 */
	public static <T extends Object> Collection<T> subCollection(Collection<T> col, Integer start, Integer count) {
		if (col instanceof ArrayList) return CollectionUtil.subCollection((ArrayList<T>) col, start, count);
		
		if (start == null) start = Integer.valueOf(1);
		
		if (col == null || col.size()  <  start.intValue()) {
			return new ArrayList<T>();
		}
		
		int rest = (count == null) ? col.size() : count.intValue();
		int pos = 0;
		
		Collection<T> result = new ArrayList<T>();
		for (Iterator<T> it = col.iterator(); it.hasNext() && rest > 0;) {
			T obj = it.next();
			if (pos >= start.intValue()) {
				result.add(obj);
				rest--;
			}
			pos++;
		}
		
		return result;
	}
	
	/**
	 * Returns a <code>Collection</code> of <code>Collection</code> where the sub collections contains all the elements of <code>col</code>,
	 * where the sum of all the items of the subcollections is the same as the size of <code>col</code>. Al subcollections will have no more than
	 * <code>size</code> elements. If the size of <col>col</col> is lower than <code>size</code> a sub collection is generated.
	 * 
	 * @param col	The <code>Collection</code> to the divide in subcollections.
	 * @param size	The size of the subcollections.
	 * @return		A <code>Collection</code> of <code>Collection</code>.
	 */
	public static <T extends Object> Collection<Collection<T>> subCollections(Collection<T> col, int size) {int colSize = CollectionUtil.size(col);
		if (size <= 0) size = colSize;
		if (size <= 0) size = 1;
		
		Collection<Collection<T>> result = new ArrayList<Collection<T>>(colSize / size);
		Collection<T> subResult = null;
		
		for (T o : col) {
			if (subResult == null || subResult.size() == size) {
				subResult = new ArrayList<T>();
				result.add(subResult);
			}
			
			subResult.add(o);
		}
		
		return result;
	}
	
	/**
	 * Returns a <code>Collection</code> where the first item is at the position <b>start</b> of <b>col</b> and will not
	 * have more than <b>count</b> elements. If <b>start</b> is <code>null</code> then the first element of <b>col</b> will 
	 * be the first element of the result. If <b>count</b> is <code>null</code> then the last element of <b>col</b> will be 
	 * the last element of the result.
	 * 
	 * <p>If <b>col</b> is <code>null</code> or <code>start >= col.size()</code> then a new instance of <code>ArrayList</code>
	 * will be return.</p>
	 * 
	 * <p>If <b>col</b> is detected as an instance of <code>ArrayList</code> then the method <code>subCollection(ArrayList, Integer, Integer)</code>
	 * will be call.</p>
	 * 
	 * @param col	The <code>Collection</code> from where retrieve the elements
	 * @param start	The first element to add to the result
	 * @param count	The number of item in the result
	 * @return		A <code>ArrayList</code> of item retrieve from <b>col</b>, where the size will not be grater than <b>count</b>
	 * 
	 * @see #subCollection(Collection, int, int)
	 * @see #subCollection(Collection, Integer, Integer)
	 */
	public static <T extends Object> ArrayList<T> subCollection(ArrayList<T> arr, Integer start, Integer count) {
		if (start == null) start = Integer.valueOf(1);
		
		if (arr == null || arr.size()  <  start.intValue()) {
			return new ArrayList<T>();
		}
		
		int rest = (count == null)?arr.size():count.intValue();
		ArrayList<T> result = new ArrayList<T>();
		for (int i = start.intValue(); i < arr.size() && rest > 0; i++, rest--) {
			result.add(arr.get(i));
		}
		
		return result;
	}
	
	/**
	 * Checks if an instance of <code>Collection</code> is <code>null</code> or has no elements.
	 * 
	 * @param col	The <code>Collection</code> to check.
	 * @return		<code>true</code> if <b>col</b> is <code>null</code> or with of size 0, <code>false</code> otherwise.
	 */
	public static boolean isEmpty(Collection<?> col) {
		return col == null || col.size() == 0;
	}
	
	/**
	 * Checks if an instance of <code>Map</code> is <code>null</code> or has no elements.
	 * 
	 * @param map	The <code>Map</code> to check.
	 * @return		<code>true</code> if <b>amp</b> is <code>null</code> or with of size 0, <code>false</code> otherwise.
	 */
	public static boolean isEmpty(Map<?,?> map) {
		return map == null || map.size() == 0;
	}
	
	/**
	 * Checks if an instance of <code>Set</code> is <code>null</code> or has no elements.
	 * 
	 * @param col	The <code>Set</code> to check.
	 * @return		<code>true</code> if <b>col</b> is <code>null</code> or with of size 0, <code>false</code> otherwise.
	 */
	public static boolean isEmpty(Set<?> col) {
		return col == null || col.size() == 0;
	}
	
	/**
	 * Checks if the instances of <code>Collection</code> are not empty. If one instance is empty then <code>false</code>
	 * will be return.
	 * 
	 * @param cols	The instances to check
	 * @return		<code>true</code> if all instances are not emtpy, <code>false</code> if at least one instance is empty.
	 * 
	 * @see #notEmpty(Collection)
	 */
	public static boolean notEmpty(Collection<?>... cols) {
		boolean result = true;
		
		if (cols != null) {
			for (int i = 0; i < cols.length && result; i++) result = CollectionUtil.notEmpty(cols[i]);
		}
		
		return result;
	}
	
	/**
	 * Checks if the instances of <code>Collection</code> are empty. If one instance is not empty then <code>false</code>
	 * will be return.
	 * 
	 * @param cols	The instances to check
	 * @return		<code>true</code> if all instances are empty, <code>false</code> if at least one instance is not empty.
	 * 
	 * @see #isEmpty(Collection)
	 */
	public static boolean isEmpty(Collection<?>... cols) {
		boolean result = true;
		
		if (cols != null) {
			for (int i = 0; i < cols.length && result; i++) result = CollectionUtil.isEmpty(cols[i]);
		}
		
		return result;
	}
	
	/**
	 * Checks is an instance of <code>Collection</code> is not empty.
	 * 
	 * @param col	The instance to check
	 * @return		<code>true</code> if the instance is ! empty, <code>false</code> if the instance is empty.
	 * 
	 * @see #isEmpty(Collection)
	 */
	public static boolean notEmpty(Collection<?> col) {
		return !CollectionUtil.isEmpty(col);
	}

	/**
	 * Checks is an instance of <code>Map</code> is not empty.
	 * 
	 * @param map	The instance to check
	 * @return		<code>true</code> if the instance is ! empty, <code>false</code> if the instance is empty.
	 * 
	 * @see #isEmpty(Map)
	 */
	public static boolean notEmpty(Map<?,?> map) {
		return !CollectionUtil.isEmpty(map);
	}
	
	/**
	 * Checks is an instance of <code>Set</code> is not empty.
	 * 
	 * @param col	The instance to check
	 * @return		<code>true</code> if the instance is ! empty, <code>false</code> if the instance is empty.
	 * 
	 * @see #isEmpty(Set)
	 */
	public static boolean notEmpty(Set<Object> col) {
		return !CollectionUtil.isEmpty(col);
	}
	
	/**
	 * Makes a new instance of <code>ArrayList</code> and add all elements of <b>col</b> to the new instance. If
	 * an element is <code>Cloneable</code> then the <code>Cloneable.clone()</code> method is call and the result
	 * is added to the instance.
	 * 
	 * @param col	The <code>Collection</code> to clone
	 * @return		A new instance of <code>ArrayList</code> with the elements of <b>col</b>.
	 * 
	 * @see tech.renovus.solarec.util.interfaces.Cloneable
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> Collection<T> clone(Collection<T> col) {
		Collection<T> result = new ArrayList<T>();

		if (col != null) {
			for (T obj : col) {
				try {
					if (obj instanceof tech.renovus.solarec.util.interfaces.Cloneable) {
						result.add((T) ((tech.renovus.solarec.util.interfaces.Cloneable) obj).clone());
					} else {
						result.add(obj);
					}
				} catch (CloneNotSupportedException e) {
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Adds a <b>value</b> as a value of <b>values</b> using as key <b>key</b>. If <b>value</b> is <code>null</code> 
	 * then <b>value</b> is not added.
	 * 
	 * @param values	Where to add the value.
	 * @param key		The key in the <code>HashMap</code>
	 * @param value		The value to add
	 */
	public static <K extends Object, V extends Object>void addValueAvoidNullAdd(HashMap<K,V> values, K key, V value) {
		if (value != null) values.put(key, value);
	}
	
	/**
	 * Retrieves a <b>value</b> from the <code>Collection</code>. If <b>values</b> is <code>null</code> then <code>null</code>
	 * is return. In order to determinate if an element of <b>values</b> is equals to <b>key</b>, the method <code>.equals</code>
	 * of the element is call.
	 * 
	 * @param values	Where to look for the element
	 * @param key		Compare element with
	 * @return			An element from <b>values</b> that is <code>.equals()</code> to <b>key</b>.
	 */
	public static <T extends Object> T getValue(Collection<T> values, Object key) {
		T result = null;
		if (values != null) {
			for (Iterator<T> it = values.iterator(); it.hasNext() && result == null;) {
				result = it.next();
				if (result != null && !result.equals(key)) result = null;
			}
		}
		return result;
	}
	
	/**
	 * Returns a value from a <code>HashMap</code> getting it from the position <b>key1</b>, in case that there is no key or the value
	 * at <b>key1</b> is <code>null</code> then the value from <b>key2</b> is return. If <b>values</b> is <code>null</code> then 
	 * <code>null</code> is return.
	 * 
	 * @param values	From where retrieve the value
	 * @param key1		The first place to look for
	 * @param key2		The second place to look for
	 * @return			The value in <b>values</b> that is at <b>key1</b> or <b>key2</b>
	 */
	public static <T extends Object, Y extends Object> Y getValue(HashMap<T,Y> values, T key1, T key2) {
		if (values != null) {
			return (values.containsKey(key1) && values.get(key1) != null) ? values.get(key1) : values.get(key2);
		}
		
		return null;
	}
	
	/**
	 * Returns a value from a <code>HashMap</code> searching it at <b>key1</b> position. If <b>values</b> is <code>null</code> then 
	 * <code>null</code> is return
	 * 
	 * @param values	From where retrieve the value
	 * @param key1		The place to look for
	 * @return			The value in <b>values</b> that is at <b>key1</b>
	 */
	public static <T extends Object, Y extends Object> Y getValue(HashMap<T,Y> values, T key1) {
		if (values != null) return values.get(key1);
		return null;
	}
	
	/**
	 * Determinate is exists a key at a <code>HashMap</code> and if at the key the value is not empty. If <b>values</b> is
	 * <code>null</code> then <code>false</code> is return.
	 * 
	 * @param values
	 * @param key
	 * @return
	 * 
	 * @see uy.com.pf.sdk.util.StringUtil#notEmpty(String...)
	 */
	public static <T extends Object> boolean existKey(HashMap<T,String> values, T key) {
		return values != null && values.containsKey(key) && StringUtil.notEmpty(values.get(key));
	}
	
	/**
	 * Returns the size of a <code>Collection</code>. If the <code>Collection</code> is <code>null</code> then <b>0</b> is return.
	 * 
	 * @param col	The <code>Collection</code> to determinate the size.
	 * @return		The size of the <code>Collection</code> or <b>0</b> if it is <code>null</code>
	 */
	public static int size(Collection<?> col) {
		return CollectionUtil.isEmpty(col) ? 0 : col.size();
	}
	
	/**
	 * Returns the size of a <code>Map</code>. If the <code>Map</code> is <code>null</code> then <b>0</b> is return.
	 * 
	 * @param col	The <code>Collection</code> to determinate the size.
	 * @return		The size of the <code>Collection</code> or <b>0</b> if it is <code>null</code>
	 */
	public static int size(Map<?,?> col) {
		return CollectionUtil.isEmpty(col) ? 0 : col.size();
	}
	
	/**
	 * Returns the size of a <code>Array</code>. If the <code>Array</code> is <code>null</code> then <b>0</b> is return.
	 * 
	 * @param col	The <code>Array</code> to determinate the size.
	 * @return		The size of the <code>Array</code> or <b>0</b> if it is <code>null</code>
	 */
	public static int size(String... col) {
		return col == null ? 0 : col.length;
	}
	
	/**
	 * Adds all the elements of <b>from</b> to <b>to</b>. If <b>to</b> is <code>null</code> or <b>from</b> is empty, then 
	 * nothing is done.
	 * 
	 * @param to	Where to add the new elements
	 * @param from	The elements to add
	 */
	public static <T extends Object> void addAll(Collection<T> to, Collection<T> from) {
		if (to != null && CollectionUtil.notEmpty(from)) to.addAll(from);
	}
	
	/**
	 * Clears emtpty's the <code>Collection</code> if it is not <code>null</code>
	 * 
	 * @param col	The <code>Collection</code> to empty
	 */
	public static void clear(Collection<?> col) {
		if (col != null) col.clear();
	}

	/**
	 * Removes all the <code>String</code> that are empty, according to the <code>StringUtil.isEmpty</code> 
	 * definition. The result is a new <code>Collection</code> with no empty <code>String</code>s.
	 * 
	 * @param values	The values to check
	 * @return			A <code>Collection</code> with no empty <code>String</code>s.
	 */
	public static Collection<String> removeEmpty(Collection<String> values) {
		Collection<String> result = new ArrayList<String>();
		
		if (CollectionUtil.notEmpty(values)) {
			for (String aValue : values) {
				if (! StringUtil.isEmpty(aValue)) {
					result.add(aValue);
				}
			}
		}
		
		return result;
	}
	
	public static String[] remove(String[] values, Collection<Integer> positions) {
		if (CollectionUtil.isEmpty(positions)) return values;
		
		String[] result = new String[values.length - CollectionUtil.size(positions)];

		int resultPosition = result.length - 1;
		for (int i = values.length - 1; i >= 0; i--) {
			if (! positions.contains(Integer.valueOf(i))) result[resultPosition--] = values[i];
		}
		
		return result;
	}
	
	public static Object[] remove(Object[] values, Collection<Integer> positions) {
		if (CollectionUtil.isEmpty(positions)) return values;
		
		Object[] result = new Object[values.length - CollectionUtil.size(positions)];

		int position = 0;
		for (int pos = 0; pos < values.length; pos ++) {
			if (! positions.contains(Integer.valueOf(pos))) {
				result[position++] = values[pos];
			}
		}
		
		return result;
	}

	public static <T extends Object> Collection<T> remove(Collection<T> values, Collection<Integer> positions) {
		if (CollectionUtil.isEmpty(positions)) return values;
		
		Collection<T> result = new ArrayList<T>();

		int position = 0;
		for (T value : values) {
			if (! positions.contains(Integer.valueOf(position ++))) {
				result.add(value);
			}
		}
		
		return result;
	}
	
	/**
	 * Returns a <code>Collection</code> with all the elements of <b>values</b> that are a <code>Class</code> of <b>aClass</b>
	 * 
	 * @param <T>
	 * @param values
	 * @param aClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> Collection<T> toCollection(Collection<?> values, Class<T> aClass) {
		Collection<T> result = new ArrayList<T>(CollectionUtil.size(values));
		
		if (CollectionUtil.notEmpty(values)) {
			for (Object obj : values) {
				if (aClass.equals(obj.getClass())) result.add((T) obj);
			}
		}
		
		return result;
	}
	
	/**
	 * Returns an <code>ArrayList</code> with all the elements of <b>values</b> that are a <code>Class</code> of <b>aClass</b>
	 * 
	 * @param <T>
	 * @param values
	 * @param aClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> ArrayList<T> toArrayList(Collection<?> values, Class<T> aClass) {
		ArrayList<T> result = new ArrayList<T>(CollectionUtil.size(values));
		
		if (CollectionUtil.notEmpty(values)) {
			for (Object obj : values) {
				if (aClass.equals(obj.getClass())) result.add((T) obj);
			}
		}
		
		return result;
	}
	
	/**
	 * Returns a <code>Collection</code> of a specific class. If <b>values</b> contains elements that are not of class
	 * <b>aClass</b>, the iteration will generate a <code>ClassCastException</code>. See metho <code>.toCollection</code>.
	 * 
	 * @param <T>
	 * @param values
	 * @param aClass
	 * @return
	 * 
	 * @see #toCollection(Collection, Class)
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> Collection<T> convertToCollection(Collection<Object> values, Class<T> aClass) {
		Collection<T> result = new ArrayList<T>(CollectionUtil.size(values));
		
		if (CollectionUtil.notEmpty(values)) {
			for (Object obj : values) {
				result.add((T) obj);
			}
		}
		
		return result;
	}
	
	/**
	 * Returns an <code>ArrayList</code> of a specific class. If <b>values</b> contains elements that are not of class
	 * <b>aClass</b>, the iteration will generate a <code>ClassCastException</code>. See method <code>.toCollection</code>.
	 * 
	 * @param <T>
	 * @param values
	 * @param aClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> ArrayList<T> convertToArrayList(ArrayList<Object> values, Class<T> aClass) {
		ArrayList<T> result = new ArrayList<T>(CollectionUtil.size(values));
		
		if (CollectionUtil.notEmpty(values)) {
			for (Object obj : values) {
				result.add((T) obj);
			}
		}
		
		return result;
	}
	
	/**
	 * Returns an <code>ArrayList</code> of a specific class. If <b>values</b> contains elements that are not of class
	 * <b>aClass</b>, the iteration will generate a <code>ClassCastException</code>. See method <code>.toCollection</code>.
	 * 
	 * @param <T>
	 * @param values
	 * @param aClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> ArrayList<T> convertToArrayList(Collection<Object> values, Class<T> aClass) {
		ArrayList<T> result = new ArrayList<T>(CollectionUtil.size(values));
		
		if (CollectionUtil.notEmpty(values)) {
			for (Object obj : values) {
				result.add((T) obj);
			}
		}
		
		return result;
	}

	/**
	 * Filters a <code>Collection</code> of values using the <code>IFilter.evaluate()</code> to determinate if a value
	 * must be in the result. If the call of the method <code>IFilter.evaluate()</code> evaluates in <code>true</code>.
	 * 
	 * @param <T> The <code>Object</code> that can be filter.
	 * @param values	The values to analyze
	 * @param filter	The <code>IFilter</code> that has the logic to filter the values.
	 * @return			A <code>Collection</code> will all the values that evaluated in <code>true</code>
	 * 
	 * @see #filter(ArrayList, IFilter)
	 * @see tech.renovus.solarec.util.interfaces.IFilter
	 */
	public static <T extends Object> Collection<T> filter(Collection<T> values, IFilter<T> filter) {
		Collection<T> result = new ArrayList<T>();
		
		if (CollectionUtil.notEmpty(values)) {
			for (T value : values) {
				if (filter.evaluate(value)) result.add(value);
			}
		}
		
		return result;
	}

	/**
	 * Filters a <code>ArrayList</code> of values using the <code>IFilter.evaluate()</code> to determinate if a value
	 * must be in the result. If the call of the method <code>IFilter.evaluate()</code> evaluates in <code>true</code>.
	 * 
	 * @param <T> The <code>Object</code> that can be filter.
	 * @param values	The values to analyze
	 * @param filter	The <code>IFilter</code> that has the logic to filter the values.
	 * @return			An <code>ArrayList</code> will all the values that evaluated in <code>true</code>
	 * 
	 * @see #filter(Collection, IFilter)
	 * @see tech.renovus.solarec.util.interfaces.IFilter
	 */
	public static <T extends Object> ArrayList<T> filter(ArrayList<T> values, IFilter<T> filter) {
		ArrayList<T> result = new ArrayList<T>();
		
		if (CollectionUtil.notEmpty(values)) {
			for (T value : values) {
				if (filter.evaluate(value)) result.add(value);
			}
		}
		
		return result;
	}
	
	/**
	 * Returns the element at the position <b>index</b> of the <code>Collection</code>. If the <code>Collection</code>
	 * if empty or has less items than <b>index</b>, then <code>null</code> is return.
	 * 
	 * @param col	The <code>Collection</code> where to search for the element
	 * @param index	The position of the element
	 * @return		The element at the position
	 * 
	 * @see #isEmpty(Collection)
	 * @see #size(Collection)
	 * @see #getAt(ArrayList, int)
	 */
	public static <T extends Object> T getAt(Collection<T> col, int index) {
		if (index < 0) return null;
		if (CollectionUtil.isEmpty(col)) return null;
		if (CollectionUtil.size(col) <= index) return null;
		
		if (col instanceof ArrayList) return CollectionUtil.getAt((ArrayList<T>) col, index);
		
		Iterator<T> iterator = col.iterator();
		T result = null;
		while (index >= 0) {
			result = iterator.next();
			index--;
		}
		return result;
	}
	
	/**
	 * Returns the element at the position <b>index</b> of the <code>ArrayList</code>. If the <code>Collection</code>
	 * if empty or has less items than <b>index</b>, then <code>null</code> is return.
	 * 
	 * @param array	The <code>ArrayList</code> where to search for the element
	 * @param index	The position of the element
	 * @return		The element at the position
	 * 
	 * @see #isEmpty(Collection)
	 * @see #size(Collection)
	 * @see #getAt(Collection, int)
	 */
	public static <T extends Object> T getAt(ArrayList<T> array, int index) {
		if (index < 0) return null;
		if (CollectionUtil.isEmpty(array)) return null;
		if (CollectionUtil.size(array) <= index) return null;
		return array.get(index);
	}
	
	public static <T extends Object> boolean contains(Collection<T> col, T object) {
		if (CollectionUtil.isEmpty(col)) return false;
		return col.contains(object);
	}
}
