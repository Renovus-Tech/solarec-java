package tech.renovus.solarec.util.interfaces;

import tech.renovus.solarec.util.db.BaseDbVo;

public interface ISynchronizable<T extends BaseDbVo> {
	
	/**
	 * Synchronize an <code>BaseDbVo</code> object with un other object, makeing the comparison with 
	 * <b>aVo</b>.
	 * 
	 * @param aVo	<code>BaseDbVo</code> to compare with</code>
	 */
	void synchronize(T aVo);
	
	void synchronizeForce(int syncType);
	
	void setChildrensId();
}
