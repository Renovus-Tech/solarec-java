package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseLocEstimationVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_LOC_EST_12 = "loc_est_12";
	public static final String COLUMN_LOC_ID = "loc_id";
	public static final String COLUMN_LOC_EST_ID = "loc_est_id_auto";
	public static final String COLUMN_LOC_EST_ORDER = "loc_est_order";
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_LOC_EST_01 = "loc_est_01";
	public static final String COLUMN_LOC_EST_02 = "loc_est_02";
	public static final String COLUMN_LOC_EST_03 = "loc_est_03";
	public static final String COLUMN_LOC_EST_04 = "loc_est_04";
	public static final String COLUMN_LOC_EST_05 = "loc_est_05";
	public static final String COLUMN_LOC_EST_06 = "loc_est_06";
	public static final String COLUMN_LOC_EST_07 = "loc_est_07";
	public static final String COLUMN_LOC_EST_08 = "loc_est_08";
	public static final String COLUMN_LOC_EST_09 = "loc_est_09";
	public static final String COLUMN_LOC_EST_10 = "loc_est_10";
	public static final String COLUMN_LOC_EST_11 = "loc_est_11";
	public static final String COLUMN_LOC_EST_TITLE = "loc_est_title";

	public static final int LENGTH_COLUMN_LOC_EST_TITLE =  200;

	//--- Private properties --------------------
	private @Getter @Setter Double locEst12;
	private @Getter @Setter Integer locId;
	private @Getter @Setter Integer locEstId;
	private @Getter @Setter Integer locEstOrder;
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Double locEst01;
	private @Getter @Setter Double locEst02;
	private @Getter @Setter Double locEst03;
	private @Getter @Setter Double locEst04;
	private @Getter @Setter Double locEst05;
	private @Getter @Setter Double locEst06;
	private @Getter @Setter Double locEst07;
	private @Getter @Setter Double locEst08;
	private @Getter @Setter Double locEst09;
	private @Getter @Setter Double locEst10;
	private @Getter @Setter Double locEst11;
	private @Getter @Setter String locEstTitle;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.locId == null) {
			return false;
		}
		if (this.locEstId == null) {
			return false;
		}
		if (this.locEstOrder == null) {
			return false;
		}
		if (this.cliId == null) {
			return false;
		}
		if (this.locEstTitle == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocEstimationVo)) return false;
		
		BaseLocEstimationVo aObj = (BaseLocEstimationVo) obj;
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEstId,aObj.locEstId)) {
			return false;
		}
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.locEstId != null) hashCode += this.locEstId.hashCode();
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseLocEstimationVo aObj = (BaseLocEstimationVo) obj;
		if (!ClassUtil.equals(this.locEst12,aObj.locEst12)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEstOrder,aObj.locEstOrder)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst01,aObj.locEst01)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst02,aObj.locEst02)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst03,aObj.locEst03)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst04,aObj.locEst04)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst05,aObj.locEst05)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst06,aObj.locEst06)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst07,aObj.locEst07)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst08,aObj.locEst08)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst09,aObj.locEst09)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst10,aObj.locEst10)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEst11,aObj.locEst11)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEstTitle,aObj.locEstTitle)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer locId, Integer locEstId, Integer cliId) {
		this.locId = locId;
		this.locEstId = locEstId;
		this.cliId = cliId;
	}

	public void setPk(BaseLocEstimationVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.locId, aVo.locEstId, aVo.cliId);
		}
	}

}