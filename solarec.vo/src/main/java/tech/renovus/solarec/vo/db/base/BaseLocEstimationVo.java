package tech.renovus.solarec.vo.db.base;

import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BaseLocEstimationVo extends BaseDbVo {

	//--- Columns name --------------------------
	 public static final String COLUMN_CLI_ID = "cli_id";
	 public static final String COLUMN_LOC_ID = "loc_id";
	 public static final String COLUMN_LOC_EST_ID = "loc_est_id_auto";
	 public static final String COLUMN_LOC_EST_ORDER = "loc_est_order";
	 public static final String COLUMN_LOC_EST_TITLE = "loc_est_title";
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
	 public static final String COLUMN_LOC_EST_12 = "loc_est_12";

	//--- Private properties --------------------
	 private Integer cliId;
	 private Integer locId;
	 private Integer locEstId;
	 private Integer locEstOrder;
	 private String locEstTitle;
	 private Double locEst01;
	 private Double locEst02;
	 private Double locEst03;
	 private Double locEst04;
	 private Double locEst05;
	 private Double locEst06;
	 private Double locEst07;
	 private Double locEst08;
	 private Double locEst09;
	 private Double locEst10;
	 private Double locEst11;
	 private Double locEst12;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.locId == null) {
			return false;
		}
		if (this.locEstId == null) {
			return false;
		}
		if (this.locEstOrder == null) {
			return false;
		}
		if (this.locEstTitle == null) {
			return false;
		}
		if (this.locEst01 == null) {
			return false;
		}
		if (this.locEst02 == null) {
			return false;
		}
		if (this.locEst03 == null) {
			return false;
		}
		if (this.locEst04 == null) {
			return false;
		}
		if (this.locEst05 == null) {
			return false;
		}
		if (this.locEst06 == null) {
			return false;
		}
		if (this.locEst07 == null) {
			return false;
		}
		if (this.locEst08 == null) {
			return false;
		}
		if (this.locEst09 == null) {
			return false;
		}
		if (this.locEst10 == null) {
			return false;
		}
		if (this.locEst11 == null) {
			return false;
		}
		if (this.locEst12 == null) {
			return false;
		}
		return true;
	}

	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocEstimationVo)) return false;
		
		BaseLocEstimationVo aObj = (BaseLocEstimationVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEstId,aObj.locEstId)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.locId != null) hashCode += this.locId.hashCode();
		if (this.locEstId != null) hashCode += this.locEstId.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseLocEstimationVo)) return false;
		
		BaseLocEstimationVo aObj = (BaseLocEstimationVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locId,aObj.locId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEstId,aObj.locEstId)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEstOrder,aObj.locEstOrder)) {
			return false;
		}
		if (!ClassUtil.equals(this.locEstTitle,aObj.locEstTitle)) {
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
		if (!ClassUtil.equals(this.locEst12,aObj.locEst12)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer locId, Integer locEstId) {
		this.cliId = cliId;
		this.locId = locId;
		this.locEstId = locEstId;
	}

	public void setPk(BaseLocEstimationVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.getCliId(), aVo.getLocId(), aVo.getLocEstId());
		}
	}

	//--- Getters and Setters -------------------
	public Integer getCliId() {
		return this.cliId;
	}
	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public Integer getLocId() {
		return this.locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public Integer getLocEstId() {
		return this.locEstId;
	}
	public void setLocEstId(Integer locEstId) {
		this.locEstId = locEstId;
	}

	public Integer getLocEstOrder() {
		return this.locEstOrder;
	}
	public void setLocEstOrder(Integer locEstOrder) {
		this.locEstOrder = locEstOrder;
	}

	public String getLocEstTitle() {
		return this.locEstTitle;
	}
	public void setLocEstTitle(String locEstTitle) {
		this.locEstTitle = locEstTitle;
	}

	public Double getLocEst01() {
		return this.locEst01;
	}
	public void setLocEst01(Double locEst01) {
		this.locEst01 = locEst01;
	}

	public Double getLocEst02() {
		return this.locEst02;
	}
	public void setLocEst02(Double locEst02) {
		this.locEst02 = locEst02;
	}

	public Double getLocEst03() {
		return this.locEst03;
	}
	public void setLocEst03(Double locEst03) {
		this.locEst03 = locEst03;
	}

	public Double getLocEst04() {
		return this.locEst04;
	}
	public void setLocEst04(Double locEst04) {
		this.locEst04 = locEst04;
	}

	public Double getLocEst05() {
		return this.locEst05;
	}
	public void setLocEst05(Double locEst05) {
		this.locEst05 = locEst05;
	}

	public Double getLocEst06() {
		return this.locEst06;
	}
	public void setLocEst06(Double locEst06) {
		this.locEst06 = locEst06;
	}

	public Double getLocEst07() {
		return this.locEst07;
	}
	public void setLocEst07(Double locEst07) {
		this.locEst07 = locEst07;
	}

	public Double getLocEst08() {
		return this.locEst08;
	}
	public void setLocEst08(Double locEst08) {
		this.locEst08 = locEst08;
	}

	public Double getLocEst09() {
		return this.locEst09;
	}
	public void setLocEst09(Double locEst09) {
		this.locEst09 = locEst09;
	}

	public Double getLocEst10() {
		return this.locEst10;
	}
	public void setLocEst10(Double locEst10) {
		this.locEst10 = locEst10;
	}

	public Double getLocEst11() {
		return this.locEst11;
	}
	public void setLocEst11(Double locEst11) {
		this.locEst11 = locEst11;
	}

	public Double getLocEst12() {
		return this.locEst12;
	}
	public void setLocEst12(Double locEst12) {
		this.locEst12 = locEst12;
	}

}