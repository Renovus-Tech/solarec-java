package tech.renovus.solarec.vo.report;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import tech.renovus.solarec.util.FlagUtil;
import tech.renovus.solarec.vo.db.data.RepTypeVo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportDefinition {

	//--- Public constants ----------------------
	public static final String REQUIRES_DATE	= "date";
	public static final String REQUIRES_WEEK	= "week";
	public static final String REQUIRES_MONTH	= "month";
	public static final String REQUIRES_RANGE	= "daterange";
	
	//--- Private properties --------------------
	private Integer id;
	private String name;
	private String title;
	private String requires;
	
	private List<String> values;

	//--- Constructors --------------------------
	public ReportDefinition() {}
	
	public ReportDefinition(RepTypeVo vo) {
		this.id = vo.getRepTypeId();
		this.name = vo.getRepTypeName();
		this.title = vo.getRepTypeTitle();
		
			 if (FlagUtil.getFlagValue(vo, RepTypeVo.FLAG_REQUIRES_DATE)) {
				this.requires = REQUIRES_DATE;
			} else if (FlagUtil.getFlagValue(vo, RepTypeVo.FLAG_REQUIRES_WEEK)) {
			this.requires = REQUIRES_WEEK;
		} else if (FlagUtil.getFlagValue(vo, RepTypeVo.FLAG_REQUIRES_MONTH)) {
			this.requires = REQUIRES_MONTH;
		} else if (FlagUtil.getFlagValue(vo, RepTypeVo.FLAG_REQUIRES_RANGE)) {
			this.requires = REQUIRES_RANGE;
		}
	}

	//--- Getters and Setters -------------------
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRequires() {
		return requires;
	}

	public void setRequires(String required) {
		this.requires = required;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
}
