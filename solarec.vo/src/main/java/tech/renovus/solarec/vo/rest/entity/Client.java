package tech.renovus.solarec.vo.rest.entity;

import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Client {

	//--- Private properties --------------------
	private Integer id;
	private String name;
	private String legalName;
	private String address;
	private boolean enabled;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date demoDate;
	
	private Integer dataDefinitionId;
	private DataDefinition dataDefinition;
	
	private Collection<Setting> settings;
	
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
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getDataDefinitionId() {
		return dataDefinitionId;
	}
	public void setDataDefinitionId(Integer dataDefinitionId) {
		this.dataDefinitionId = dataDefinitionId;
	}
	public DataDefinition getDataDefinition() {
		return dataDefinition;
	}
	public void setDataDefinition(DataDefinition dataDefinition) {
		this.dataDefinition = dataDefinition;
	}
	public Collection<Setting> getSettings() {
		return settings;
	}
	public void setSettings(Collection<Setting> settings) {
		this.settings = settings;
	}
	public Date getDemoDate() {
		return demoDate;
	}
	public void setDemoDate(Date demoDate) {
		this.demoDate = demoDate;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
