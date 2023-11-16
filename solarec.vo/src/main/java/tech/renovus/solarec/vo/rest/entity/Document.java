package tech.renovus.solarec.vo.rest.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Document {

	//--- Private methods -----------------------
	private Integer id;
	private String name;
	private DocType type;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date added;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date from;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date to;
	private String size;
	private String observations;
	private String metadata;
	private Boolean isOpen;
	
	private Integer forcedLocation;
	
	private List<Generator> generators;
	private List<Station> stations;
	private List<Location> locations;
	
	private String downloadLink;
	
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
	public DocType getType() {
		return type;
	}
	public void setType(DocType type) {
		this.type = type;
	}
	public Date getAdded() {
		return added;
	}
	public void setAdded(Date added) {
		this.added = added;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public List<Generator> getGenerators() {
		return generators;
	}
	public void setGenerators(List<Generator> generators) {
		this.generators = generators;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	public Integer getForcedLocation() {
		return forcedLocation;
	}
	public void setForcedLocation(Integer forcedLocation) {
		this.forcedLocation = forcedLocation;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
}
