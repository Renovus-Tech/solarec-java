
package tech.renovus.solarec.inverters.brand.sofar.api;

import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "total", "stationList" })
@Generated("jsonschema2pojo")
public class StationListResponse extends BasicResponse {

	@JsonProperty("total")
	private Integer total;
	@JsonProperty("stationList")
	private List<Station> stationList;

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	public StationListResponse withTotal(Integer total) {
		this.total = total;
		return this;
	}

	@JsonProperty("stationList")
	public List<Station> getStationList() {
		return stationList;
	}

	@JsonProperty("stationList")
	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public StationListResponse withStationList(List<Station> stationList) {
		this.stationList = stationList;
		return this;
	}
}
