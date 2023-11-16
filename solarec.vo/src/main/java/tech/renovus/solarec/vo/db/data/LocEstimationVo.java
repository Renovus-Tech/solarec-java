package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.vo.db.relation.DbLocEstimationVo;

public class LocEstimationVo extends DbLocEstimationVo {

	//--- Constructors --------------------------
	public LocEstimationVo() {
	}

	public LocEstimationVo(Integer cliId, Integer locId, Integer locEstId) {
		this.setPk(cliId, locId, locEstId);
	}

	public LocEstimationVo(Integer cliId, Integer locId, String title) {
		this.setPk(cliId, locId, null);
		this.setLocEstTitle(title);
	}
	
	//--- Builder methods -----------------------
	public LocEstimationVo withLocEstOrder(Integer order) { this.setLocEstOrder(order); return this; }
	public LocEstimationVo withLocEst01(Double value) { this.setLocEst01(value); return this; }
	public LocEstimationVo withLocEst02(Double value) { this.setLocEst02(value); return this; }
	public LocEstimationVo withLocEst03(Double value) { this.setLocEst03(value); return this; }
	public LocEstimationVo withLocEst04(Double value) { this.setLocEst04(value); return this; }
	public LocEstimationVo withLocEst05(Double value) { this.setLocEst05(value); return this; }
	public LocEstimationVo withLocEst06(Double value) { this.setLocEst06(value); return this; }
	public LocEstimationVo withLocEst07(Double value) { this.setLocEst07(value); return this; }
	public LocEstimationVo withLocEst08(Double value) { this.setLocEst08(value); return this; }
	public LocEstimationVo withLocEst09(Double value) { this.setLocEst09(value); return this; }
	public LocEstimationVo withLocEst10(Double value) { this.setLocEst10(value); return this; }
	public LocEstimationVo withLocEst11(Double value) { this.setLocEst11(value); return this; }
	public LocEstimationVo withLocEst12(Double value) { this.setLocEst12(value); return this; }

}