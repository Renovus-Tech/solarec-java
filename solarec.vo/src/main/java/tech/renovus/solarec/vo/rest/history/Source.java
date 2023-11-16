
package tech.renovus.solarec.vo.rest.history;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "locations",
    "generators",
    "stations"
})
@Generated("jsonschema2pojo")
public class Source {

    @JsonProperty("locations")
    private List<SourceType> locations;
    @JsonProperty("generators")
    private List<SourceType> generators;
    @JsonProperty("stations")
    private List<SourceType> stations;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("locations")
    public List<SourceType> getLocations() {
        return locations;
    }

    @JsonProperty("locations")
    public void setLocations(List<SourceType> locations) {
        this.locations = locations;
    }

    public Source withLocations(List<SourceType> locations) {
        this.locations = locations;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Source withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @JsonAnyGetter
	public List<SourceType> getGenerators() {
		return generators;
	}

    @JsonAnySetter
	public void setGenerators(List<SourceType> generators) {
		this.generators = generators;
	}

	@JsonAnyGetter
	public List<SourceType> getStations() {
		return stations;
	}

	@JsonAnySetter
	public void setStations(List<SourceType> stations) {
		this.stations = stations;
	}

}
