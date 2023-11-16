package tech.renovus.solarec.vo.db.data;

import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.util.interfaces.ISetting;
import tech.renovus.solarec.vo.db.relation.DbCliSettingVo;

public class CliSettingVo extends DbCliSettingVo implements ISetting {

	//--- Public constants ----------------------
	public static final String FISCAL_YEAR_END_MONTH 	=	"fiscalYearEndMonth";
	public static final String D_RECS_SOLD_PORCENTAGE	= "dRecsSoldPorcentage";
	public static final String D_RECS_PRICE				= "dRecsPrice";
	
	public static final String DEFAULT_VALUE_FISCAL_YEAR_END_MONTH	= "12";
	public static final String DEFAULT_VALUE_D_RECS_SOLD_PORCENTAGE	= "50";
	public static final String DEFAULT_VALUE_D_RECS_PRICE			= "20";

	//--- Constructors --------------------------
	public CliSettingVo() {
	}

	public CliSettingVo(Integer cliId, String cliSetName) {
		this.setPk(cliId, cliSetName);
	}

	public CliSettingVo(Integer cliId, String cliSetName, String cliSetValue) {
		this.setPk(cliId, cliSetName);
		this.setCliSetValue(cliSetValue);
	}

	//--- Static methods ------------------------
	public static boolean validName(String name) {
		if (StringUtil.isEmpty(name)) return false;
		
		switch (name) {
			case FISCAL_YEAR_END_MONTH:
			case D_RECS_SOLD_PORCENTAGE:
			case D_RECS_PRICE:
				return true;
				
			default:
				return false;
		}
	}
	
	public double doubleValue() {
		return StringUtil.isEmpty(this.getValue()) ? 0 : Double.valueOf(this.getValue()).doubleValue();
	}
	
	//--- Implemented methods -------------------
	@Override public String getName() { return this.getCliSetName(); }
	@Override public String getValue() { return this.getCliSetValue(); }
	
}