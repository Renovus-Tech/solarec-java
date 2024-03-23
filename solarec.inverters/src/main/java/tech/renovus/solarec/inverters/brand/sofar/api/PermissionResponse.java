package tech.renovus.solarec.inverters.brand.sofar.api;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "viewStationList", "viewStationInfo", "viewStationDeviceList", "viewStationData",
		"viewStationAlertList", "viewDeviceList", "viewDeviceData", "viewDeviceAlertList", "viewDeviceAlertData" })
@Generated("jsonschema2pojo")
public class PermissionResponse extends BasicResponse {

	@JsonProperty("viewStationList")
	private Integer viewStationList;
	@JsonProperty("viewStationInfo")
	private Integer viewStationInfo;
	@JsonProperty("viewStationDeviceList")
	private Integer viewStationDeviceList;
	@JsonProperty("viewStationData")
	private Integer viewStationData;
	@JsonProperty("viewStationAlertList")
	private Integer viewStationAlertList;
	@JsonProperty("viewDeviceList")
	private Integer viewDeviceList;
	@JsonProperty("viewDeviceData")
	private Integer viewDeviceData;
	@JsonProperty("viewDeviceAlertList")
	private Integer viewDeviceAlertList;
	@JsonProperty("viewDeviceAlertData")
	private Integer viewDeviceAlertData;

	@JsonProperty("viewStationList")
	public Integer getViewStationList() {
		return viewStationList;
	}

	@JsonProperty("viewStationList")
	public void setViewStationList(Integer viewStationList) {
		this.viewStationList = viewStationList;
	}

	public PermissionResponse withViewStationList(Integer viewStationList) {
		this.viewStationList = viewStationList;
		return this;
	}

	@JsonProperty("viewStationInfo")
	public Integer getViewStationInfo() {
		return viewStationInfo;
	}

	@JsonProperty("viewStationInfo")
	public void setViewStationInfo(Integer viewStationInfo) {
		this.viewStationInfo = viewStationInfo;
	}

	public PermissionResponse withViewStationInfo(Integer viewStationInfo) {
		this.viewStationInfo = viewStationInfo;
		return this;
	}

	@JsonProperty("viewStationDeviceList")
	public Integer getViewStationDeviceList() {
		return viewStationDeviceList;
	}

	@JsonProperty("viewStationDeviceList")
	public void setViewStationDeviceList(Integer viewStationDeviceList) {
		this.viewStationDeviceList = viewStationDeviceList;
	}

	public PermissionResponse withViewStationDeviceList(Integer viewStationDeviceList) {
		this.viewStationDeviceList = viewStationDeviceList;
		return this;
	}

	@JsonProperty("viewStationData")
	public Integer getViewStationData() {
		return viewStationData;
	}

	@JsonProperty("viewStationData")
	public void setViewStationData(Integer viewStationData) {
		this.viewStationData = viewStationData;
	}

	public PermissionResponse withViewStationData(Integer viewStationData) {
		this.viewStationData = viewStationData;
		return this;
	}

	@JsonProperty("viewStationAlertList")
	public Integer getViewStationAlertList() {
		return viewStationAlertList;
	}

	@JsonProperty("viewStationAlertList")
	public void setViewStationAlertList(Integer viewStationAlertList) {
		this.viewStationAlertList = viewStationAlertList;
	}

	public PermissionResponse withViewStationAlertList(Integer viewStationAlertList) {
		this.viewStationAlertList = viewStationAlertList;
		return this;
	}

	@JsonProperty("viewDeviceList")
	public Integer getViewDeviceList() {
		return viewDeviceList;
	}

	@JsonProperty("viewDeviceList")
	public void setViewDeviceList(Integer viewDeviceList) {
		this.viewDeviceList = viewDeviceList;
	}

	public PermissionResponse withViewDeviceList(Integer viewDeviceList) {
		this.viewDeviceList = viewDeviceList;
		return this;
	}

	@JsonProperty("viewDeviceData")
	public Integer getViewDeviceData() {
		return viewDeviceData;
	}

	@JsonProperty("viewDeviceData")
	public void setViewDeviceData(Integer viewDeviceData) {
		this.viewDeviceData = viewDeviceData;
	}

	public PermissionResponse withViewDeviceData(Integer viewDeviceData) {
		this.viewDeviceData = viewDeviceData;
		return this;
	}

	@JsonProperty("viewDeviceAlertList")
	public Integer getViewDeviceAlertList() {
		return viewDeviceAlertList;
	}

	@JsonProperty("viewDeviceAlertList")
	public void setViewDeviceAlertList(Integer viewDeviceAlertList) {
		this.viewDeviceAlertList = viewDeviceAlertList;
	}

	public PermissionResponse withViewDeviceAlertList(Integer viewDeviceAlertList) {
		this.viewDeviceAlertList = viewDeviceAlertList;
		return this;
	}

	@JsonProperty("viewDeviceAlertData")
	public Integer getViewDeviceAlertData() {
		return viewDeviceAlertData;
	}

	@JsonProperty("viewDeviceAlertData")
	public void setViewDeviceAlertData(Integer viewDeviceAlertData) {
		this.viewDeviceAlertData = viewDeviceAlertData;
	}

	public PermissionResponse withViewDeviceAlertData(Integer viewDeviceAlertData) {
		this.viewDeviceAlertData = viewDeviceAlertData;
		return this;
	}
}