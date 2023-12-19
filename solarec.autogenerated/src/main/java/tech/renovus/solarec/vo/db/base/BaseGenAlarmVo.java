package tech.renovus.solarec.vo.db.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.db.BaseDbVo;

@NoArgsConstructor
public class BaseGenAlarmVo extends BaseDbVo {

	//--- Columns name --------------------------
	public static final String COLUMN_CLI_ID = "cli_id";
	public static final String COLUMN_GEN_ID = "gen_id";
	public static final String COLUMN_ALARM_CODE = "alarm_code";
	public static final String COLUMN_DATA_CAT_ID = "data_cat_id";
	public static final String COLUMN_ALARM_DESCRIPTION = "alarm_description";

	public static final int LENGTH_COLUMN_ALARM_DESCRIPTION =  1000;

	//--- Private properties --------------------
	private @Getter @Setter Integer cliId;
	private @Getter @Setter Integer genId;
	private @Getter @Setter Double alarmCode;
	private @Getter @Setter Integer dataCatId;
	private @Getter @Setter String alarmDescription;

	//--- Public methods ------------------------
	public boolean validData() {
		if (this.cliId == null) {
			return false;
		}
		if (this.genId == null) {
			return false;
		}
		if (this.alarmCode == null) {
			return false;
		}
		return true;
	}

	//--- Overriden methods ---------------------
	@Override public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof BaseGenAlarmVo)) return false;
		
		BaseGenAlarmVo aObj = (BaseGenAlarmVo) obj;
		if (!ClassUtil.equals(this.cliId,aObj.cliId)) {
			return false;
		}
		if (!ClassUtil.equals(this.genId,aObj.genId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alarmCode,aObj.alarmCode)) {
			return false;
		}
		return true;
	}

	@Override public int hashCode() {
		int hashCode = 1;
		if (this.cliId != null) hashCode += this.cliId.hashCode();
		if (this.genId != null) hashCode += this.genId.hashCode();
		if (this.alarmCode != null) hashCode += this.alarmCode.hashCode();
		return hashCode;
	}

	@Override public boolean isSame(Object obj) {
		if (! this.equals(obj)) return false;
		
		BaseGenAlarmVo aObj = (BaseGenAlarmVo) obj;
		if (!ClassUtil.equals(this.dataCatId,aObj.dataCatId)) {
			return false;
		}
		if (!ClassUtil.equals(this.alarmDescription,aObj.alarmDescription)) {
			return false;
		}
		return true;
	}

	public void setPk(Integer cliId, Integer genId, Double alarmCode) {
		this.cliId = cliId;
		this.genId = genId;
		this.alarmCode = alarmCode;
	}

	public void setPk(BaseGenAlarmVo aVo) {
		if(aVo == null) { 
			this.setPk(null, null, null);
		} else {
			this.setPk(aVo.cliId, aVo.genId, aVo.alarmCode);
		}
	}

}