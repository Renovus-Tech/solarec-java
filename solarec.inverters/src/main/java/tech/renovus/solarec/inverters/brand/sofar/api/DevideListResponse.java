
package tech.renovus.solarec.inverters.brand.sofar.api;

import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "total", "deviceList" })
@Generated("jsonschema2pojo")
public class DevideListResponse extends BasicResponse {

	@JsonProperty("total")
	private Integer total;
	@JsonProperty("deviceList")
	private List<Device> deviceList;

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	public DevideListResponse withTotal(Integer total) {
		this.total = total;
		return this;
	}

	@JsonProperty("deviceList")
	public List<Device> getDeviceList() {
		return deviceList;
	}

	@JsonProperty("deviceList")
	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

	public DevideListResponse withDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
		return this;
	}
}
