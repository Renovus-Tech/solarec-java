package tech.renovus.solarec.vo.rest.entity;

public class Station {

	//--- Private properties --------------------
	private Integer id;
	private Integer locationId;
	private String code;
	private String name;
	private String description;
	private Double latitude;
	private Double longitude;
	
	private Integer dataDefinitionId;
	private DataDefinition dataDefinition;
	
	//--- Getters and Setters -------------------
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
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
}
