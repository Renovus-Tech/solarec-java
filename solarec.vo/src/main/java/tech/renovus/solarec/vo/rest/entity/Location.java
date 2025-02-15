package tech.renovus.solarec.vo.rest.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Location {
	
	//--- Innner classes ------------------------
	public static class Type {
		private String code;
		private String text;
		
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
	}

	//--- Private properties --------------------
	private Integer id;
	private String code;
	private String name;
	private String address;
	private String state;
	private Country country;
	private Type typeOf;
	private Double latitude;
	private Double longitude;
	private Double outputCapacity;
	private Double outputTotalCapacity;
	private Double referenceDensity;
	private String type;
	private boolean gridConnected;
	private boolean enabled;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date demoDate;
	
	private Integer dataDefinitionId;
	
	private DataDefinition dataDefinition;
	private Frequency frequency;
	private List<Station> stations;
	private List<Generator> generators;
	private List<Sdg> sdgs;
	
	//--- Public methods ------------------------
	public void add(Station vo) {
		if (this.stations == null) {
			this.stations = new ArrayList<>();
		}
		this.stations.add(vo);
	}
	
	public void add(Generator vo) {
		if (this.generators == null) {
			this.generators = new ArrayList<>();
		}
		this.generators.add(vo);
	}
	
	public void add(Sdg vo) {
		if (this.sdgs == null) {
			this.sdgs = new ArrayList<>();
		}
		this.sdgs.add(vo);
	}
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Double getOutputCapacity() {
		return outputCapacity;
	}
	public void setOutputCapacity(Double outputCapacity) {
		this.outputCapacity = outputCapacity;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	public List<Generator> getGenerators() {
		return generators;
	}
	public void setGenerators(List<Generator> generators) {
		this.generators = generators;
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
	public Double getOutputTotalCapacity() {
		return outputTotalCapacity;
	}
	public void setOutputTotalCapacity(Double outputTotalCapacity) {
		this.outputTotalCapacity = outputTotalCapacity;
	}
	public Double getReferenceDensity() {
		return referenceDensity;
	}
	public void setReferenceDensity(Double referenceDensity) {
		this.referenceDensity = referenceDensity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDemoDate() {
		return demoDate;
	}
	public void setDemoDate(Date demoDate) {
		this.demoDate = demoDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public List<Sdg> getSdgs() {
		return sdgs;
	}
	public void setSdgs(List<Sdg> sdgs) {
		this.sdgs = sdgs;
	}

	public Type getTypeOf() {
		return typeOf;
	}

	public void setTypeOf(Type typeOf) {
		this.typeOf = typeOf;
	}
	public boolean getGridConnected() {
		return gridConnected;
	}
	public void setGridConnected(boolean gridConnected) {
		this.gridConnected = gridConnected;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
}
