package tech.renovus.solarec.vo.rest.entity;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DataDefinitionTrigger {

	//--- Private properties ------------------
	private Integer id;
	private String name;
	private String source;
	private String trigger;
	
	private Integer dataDefinitionId;
	private Integer clientId;
	private Integer locationId;
	private Integer generatorId;
	private Integer stationId;
	
	private DataDefinition dataDefinition;
	private Client client;
	private Location location;
	private Generator generator;
	private Station station;
	
	private Integer result;
	private String filePath;
	
	@JsonIgnore
	private MultipartFile filePart;
	
	//--- Getters and Setters -------------------
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTrigger() {
		return trigger;
	}
	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
	public DataDefinition getDataDefinition() {
		return dataDefinition;
	}
	public void setDataDefinition(DataDefinition dataDefinition) {
		this.dataDefinition = dataDefinition;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Generator getGenerator() {
		return generator;
	}
	public void setGenerator(Generator generator) {
		this.generator = generator;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public MultipartFile getFilePart() {
		return filePart;
	}
	public void setFilePart(MultipartFile filePart) {
		this.filePart = filePart;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Integer getGeneratorId() {
		return generatorId;
	}
	public void setGeneratorId(Integer generatorId) {
		this.generatorId = generatorId;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDataDefinitionId() {
		return dataDefinitionId;
	}
	public void setDataDefinitionId(Integer dataDefinitionId) {
		this.dataDefinitionId = dataDefinitionId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
