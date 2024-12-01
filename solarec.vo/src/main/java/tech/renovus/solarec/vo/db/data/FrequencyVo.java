package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbFrequencyVo;

public class FrequencyVo extends DbFrequencyVo {

	//--- Constructors --------------------------
	public FrequencyVo() {
	}

	public FrequencyVo(Integer frqId) {
		this.setPk(frqId);
	}

	//--- Overridden methods --------------------
	@Override public String toString() {
		return this.getFrqAmount() + this.getFrqUnit();
	}
}