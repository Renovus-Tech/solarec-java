package tech.renovus.solarec.util.db;

import java.util.ArrayList;
import java.util.Collection;

import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.interfaces.ISynchronizable;


/**
 * The class helps to the synchronization of <code>BaseDbVo</code> to determinate the action that must be done
 * in the database. It can compare two <code>Collection</code> of objects and return a unique <code>Collection</code>
 * with all the objects, indicating in each of them the action that must be done in the database.
 *
 * @see #compareCollections
 * @see uy.com.pf.sdk.util.interfaces.ISynchronizable
 * @see uy.com.pf.sdk.db.BaseDbVo
 */
public final class BaseDbUtil {

	//--- Constructor ---------------------------
	private BaseDbUtil() {
	}
	
	//--- Helpful methods -----------------------
	/**
	 * Set to all the elements of the <code>Collection</code> to the synchronization <b>type</b> send. If an element
	 * is <code>ISyncronizable</code> it fill be forced to synchronize to that <b>type</b>.
	 * 
	 * @param col	The <code>Collection</code> to set the type.
	 * @param type	The type to set
	 */
	public static void setAll(Collection<? extends BaseDbVo> col, int type) {
		BaseDbUtil.setAll(col, type, true);
	}
	
	/**
	 * Set to all the elements of the <code>Collection</code> to the synchronization <b>type</b> send. According to 
	 * the value of <b>forceIfSynchronizable</b> if an element is <code>ISyncronizable</code> it fill be forced 
	 * to synchronize to that <b>type</b>.
	 * 
	 * @param col					The <code>Collection</code> to set the type.
	 * @param type					The type to set
	 * @param forceIfSynchronizable	To force the synchronization
	 */
	public static void setAll(Collection<? extends BaseDbVo> col, int type, boolean forceIfSynchronizable) {
		if (CollectionUtil.notEmpty(col)) {
			for (BaseDbVo object : col) {
				object.setSyncType(type);
				if (forceIfSynchronizable && object instanceof ISynchronizable) ((ISynchronizable<?>) object).synchronizeForce(type);
			}
		}		
	}

	/**
	 * Returns an object that is the <code>Collection</code> that is <b>equals</b> to the <code>BaseDbVo</code>.
	 * 
	 * @param col		The <code>Collection</code> where to search for the object.
	 * @param object	The object to search for.
	 * @return			An <code>BaseDbVo</code> in the <code>Collection</code> that is equals to <b>object</b> useing the <code>equals</code> method.
	 */
	public static <T extends BaseDbVo> T getObject(Collection<T> col, BaseDbVo object) {
		if (CollectionUtil.notEmpty(col)) {
			for (T obj : col) {
				if (obj.equals(object)) return obj;
			}
		}

		return null;
	}
	
	/**
	 * Iterates throw all the objects of <b>colNew</b> to determinate if they are in <b>colOld</b>. Then it iterates
	 * throw all the all the objects of <b>colOld</b> to determinate if they aren't in <b>colNew</b>. If an object
	 * exists in <b>colNew</b> but not in <b>colOld</b>, it is tag with <b>insertTag</b>. 
	 * <p>If an object exists in <b>colNew</b> and in <b>colOld</b>, if they are the same, the object in <b>colNew</> 
	 * is tag as <code>BaseDbVo.SYNC_INIT</code>, if not is tag with <code>BaseDbVo.SYNC_UPDATE</code>. If an object 
	 * exists in <b>colOld</b> but not in <b>colNew</b>, it is tag with <b>deleteFlag</b>.</p>
	 * 
	 * The <code>Collection</code>'s <b>colNew</b> and <b>colOld</b> will not be altered, a new <code>Collection</code> will be  
	 * return.
	 * 
	 * @param colNew
	 * @param colOld
	 * @param insertFlag
	 * @param deleteFlag
	 * @return
	 */
	public static <T extends BaseDbVo> Collection<T> compareCollections(Collection<T> colNew, Collection<T> colOld, int insertFlag, int deleteFlag) {
		Collection<T> result = new ArrayList<T>();
		
		if (CollectionUtil.notEmpty(colNew)) { //determinate what to do with the new elements
			for (T newObject : colNew) {
				T oldObject = BaseDbUtil.getObject(colOld, newObject);
				
				if (oldObject == null) { //If new, then it must be inserted
					newObject.setSyncType(insertFlag);
					if (newObject instanceof ISynchronizable) ((ISynchronizable<?>) newObject).synchronizeForce(insertFlag);
				
				} else if (! newObject.isSame(oldObject)) { //if not the same, then it must be updated
					newObject.setSyncType(BaseDbVo.SYNC_UPDATE);
					if (newObject instanceof ISynchronizable) ((ISynchronizable<T>) newObject).synchronize(oldObject);
				
				} else {
					newObject.setSyncType(BaseDbVo.SYNC_INIT); //is the same, do nothing
					if (newObject instanceof ISynchronizable) ((ISynchronizable<T>) newObject).synchronize(oldObject);
				}
			}
		} 

		if (CollectionUtil.isEmpty(colNew)){ //if no new elements, delete the old ones.
			BaseDbUtil.setAll(colOld,deleteFlag);
			
			if (CollectionUtil.notEmpty(colOld)) result.addAll(colOld);
			return result;
		}
		
		result.addAll(colNew);
		if (CollectionUtil.notEmpty(colOld)) { //determinate what to do with the old elements
			for (T oldObject : colOld) {
				T newObject = BaseDbUtil.getObject(colNew, oldObject);
	
				if (newObject == null) { //the old element is not in the new collection, delete it
					oldObject.setSyncType(deleteFlag);
					if (oldObject instanceof ISynchronizable) ((ISynchronizable<T>) oldObject).synchronizeForce(deleteFlag);
					result.add(oldObject);
				} 
			}
		}
		
		return result;
	}

	/**
	 * Returns all the elements that have a specific synchronization type in the <code>Collection</code>.
	 * 
	 * @param vos		The <code>Collection</code> of elements to analyze.
	 * @param syncFlag	The synchronization type to search for
	 * @return			A <code>Collection</code> of elements. Will not be null.
	 */
	public static <T extends BaseDbVo> Collection<T> getAllFor(Collection<T> vos, int syncFlag) {
		Collection<T> result = new ArrayList<T>();
		
		if (CollectionUtil.notEmpty(vos)) {
			for (T vo : vos) {
				if (vo.getSyncType() == syncFlag) {
					result.add(vo);
				}
			}
		}
		
		return result;
	}
}
