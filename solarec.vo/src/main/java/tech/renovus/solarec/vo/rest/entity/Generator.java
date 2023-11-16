package tech.renovus.solarec.vo.rest.entity;

import java.util.Collection;

public class Generator {
	
	//--- Inner classes -------------------------
	public static class Power {
		
		//--- Private properties ----------------
		private Double windSpeed;
		private Double airDensity;
		private Double power;
		
		//--- Getters and Setters ---------------
		public Double getWindSpeed() {
			return windSpeed;
		}
		public void setWindSpeed(Double windSpeed) {
			this.windSpeed = windSpeed;
		}
		public Double getAirDensity() {
			return airDensity;
		}
		public void setAirDensity(Double airDensity) {
			this.airDensity = airDensity;
		}
		public Double getPower() {
			return power;
		}
		public void setPower(Double power) {
			this.power = power;
		}
	}

	//--- Private properties --------------------
	private Integer id;
	private Integer locationId;
	private String code;
	private String name;
	private String description;
	private Double latitude;
	private Double longitude;
	private String brand;
	private String model;
	private String serialNumber;
	private Double ratePower;
	
	private Integer dataDefinitionId;
	private DataDefinition dataDefinition;
	
	private Collection<Power> powerCurve;
	private Collection<Integer> neighbors;
	
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Double getRatePower() {
		return ratePower;
	}
	public void setRatePower(Double ratePower) {
		this.ratePower = ratePower;
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
	public Collection<Power> getPowerCurve() {
		return powerCurve;
	}
	public void setPowerCurve(Collection<Power> powerCurve) {
		this.powerCurve = powerCurve;
	}
	public Collection<Integer> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(Collection<Integer> neighbours) {
		this.neighbors = neighbours;
	}
}
