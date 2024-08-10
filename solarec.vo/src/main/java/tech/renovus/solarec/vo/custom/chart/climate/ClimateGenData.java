package tech.renovus.solarec.vo.custom.chart.climate;

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
@JsonPropertyOrder({ "id", "name", "code", "productionMwh", "acProductionMwh", "irradiationKwhM2" })
@Generated("jsonschema2pojo")
public class GenDatum {

	@JsonProperty("id")
	private Integer id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("code")
	private String code;
	@JsonProperty("productionMwh")
	private Double productionMwh;
	@JsonProperty("acProductionMwh")
	private Double acProductionMwh;
	@JsonProperty("irradiationKwhM2")
	private Double irradiationKwhM2;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	public GenDatum withId(Integer id) {
		this.id = id;
		return this;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public GenDatum withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	public GenDatum withCode(String code) {
		this.code = code;
		return this;
	}

	@JsonProperty("productionMwh")
	public Double getProductionMwh() {
		return productionMwh;
	}

	@JsonProperty("productionMwh")
	public void setProductionMwh(Double productionMwh) {
		this.productionMwh = productionMwh;
	}

	public GenDatum withProductionMwh(Double productionMwh) {
		this.productionMwh = productionMwh;
		return this;
	}

	@JsonProperty("acProductionMwh")
	public Double getAcProductionMwh() {
		return acProductionMwh;
	}

	@JsonProperty("acProductionMwh")
	public void setAcProductionMwh(Double acProductionMwh) {
		this.acProductionMwh = acProductionMwh;
	}

	public GenDatum withAcProductionMwh(Double acProductionMwh) {
		this.acProductionMwh = acProductionMwh;
		return this;
	}

	@JsonProperty("irradiationKwhM2")
	public Double getIrradiationKwhM2() {
		return irradiationKwhM2;
	}

	@JsonProperty("irradiationKwhM2")
	public void setIrradiationKwhM2(Double irradiationKwhM2) {
		this.irradiationKwhM2 = irradiationKwhM2;
	}

	public GenDatum withIrradiationKwhM2(Double irradiationKwhM2) {
		this.irradiationKwhM2 = irradiationKwhM2;
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

	public GenDatum withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}