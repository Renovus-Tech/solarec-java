
package tech.renovus.solarec.inverters.brand.sofar.api;

import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "total", "stationDataItems" })
@Generated("jsonschema2pojo")
public class StationHistoryDataResponse extends BasicResponse {

	@JsonProperty("total")
	private Integer total;
	@JsonProperty("stationDataItems")
	private List<StationDataItem> stationDataItems;

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	public StationHistoryDataResponse withTotal(Integer total) {
		this.total = total;
		return this;
	}

	@JsonProperty("stationDataItems")
	public List<StationDataItem> getStationDataItems() {
		return stationDataItems;
	}

	@JsonProperty("stationDataItems")
	public void setStationDataItems(List<StationDataItem> stationDataItems) {
		this.stationDataItems = stationDataItems;
	}

	public StationHistoryDataResponse withStationDataItems(List<StationDataItem> stationDataItems) {
		this.stationDataItems = stationDataItems;
		return this;
	}
}
