package tech.renovus.solarec.inverters.brand.solis.api.inveterList;

import java.util.LinkedHashMap;
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
"pageNo",
"pageSize",
"stationId",
"nmiCode"
})
@Generated("jsonschema2pojo")
public class InveterListRequest {

@JsonProperty("pageNo")
private Integer pageNo;
@JsonProperty("pageSize")
private Integer pageSize;
@JsonProperty("stationId")
private String stationId;
@JsonProperty("nmiCode")
private String nmiCode;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("pageNo")
public Integer getPageNo() {
return pageNo;
}

@JsonProperty("pageNo")
public void setPageNo(Integer pageNo) {
this.pageNo = pageNo;
}

public InveterListRequest withPageNo(Integer pageNo) {
this.pageNo = pageNo;
return this;
}

@JsonProperty("pageSize")
public Integer getPageSize() {
return pageSize;
}

@JsonProperty("pageSize")
public void setPageSize(Integer pageSize) {
this.pageSize = pageSize;
}

public InveterListRequest withPageSize(Integer pageSize) {
this.pageSize = pageSize;
return this;
}

@JsonProperty("stationId")
public String getStationId() {
return stationId;
}

@JsonProperty("stationId")
public void setStationId(String stationId) {
this.stationId = stationId;
}

public InveterListRequest withStationId(String stationId) {
this.stationId = stationId;
return this;
}

@JsonProperty("nmiCode")
public String getNmiCode() {
return nmiCode;
}

@JsonProperty("nmiCode")
public void setNmiCode(String nmiCode) {
this.nmiCode = nmiCode;
}

public InveterListRequest withNmiCode(String nmiCode) {
this.nmiCode = nmiCode;
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

public InveterListRequest withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}