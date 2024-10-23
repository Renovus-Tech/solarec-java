
package tech.renovus.solarec.inverters.brand.enphase.api.devices;

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
@JsonPropertyOrder({ "micros", "meters", "gateways", "q_relays", "acbs", "encharges", "enpowers" })
@Generated("jsonschema2pojo")
public class Devices {

	@JsonProperty("micros")
	private List<Micro> micros;
	@JsonProperty("meters")
	private List<Meter> meters;
	@JsonProperty("gateways")
	private List<Gateway> gateways;
	@JsonProperty("q_relays")
	private List<QRelay> qRelays;
	@JsonProperty("acbs")
	private List<Acb> acbs;
	@JsonProperty("encharges")
	private List<Encharge> encharges;
	@JsonProperty("enpowers")
	private List<Enpower> enpowers;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("micros")
	public List<Micro> getMicros() {
		return micros;
	}

	@JsonProperty("micros")
	public void setMicros(List<Micro> micros) {
		this.micros = micros;
	}

	public Devices withMicros(List<Micro> micros) {
		this.micros = micros;
		return this;
	}

	@JsonProperty("meters")
	public List<Meter> getMeters() {
		return meters;
	}

	@JsonProperty("meters")
	public void setMeters(List<Meter> meters) {
		this.meters = meters;
	}

	public Devices withMeters(List<Meter> meters) {
		this.meters = meters;
		return this;
	}

	@JsonProperty("gateways")
	public List<Gateway> getGateways() {
		return gateways;
	}

	@JsonProperty("gateways")
	public void setGateways(List<Gateway> gateways) {
		this.gateways = gateways;
	}

	public Devices withGateways(List<Gateway> gateways) {
		this.gateways = gateways;
		return this;
	}

	@JsonProperty("q_relays")
	public List<QRelay> getqRelays() {
		return qRelays;
	}

	@JsonProperty("q_relays")
	public void setqRelays(List<QRelay> qRelays) {
		this.qRelays = qRelays;
	}

	public Devices withqRelays(List<QRelay> qRelays) {
		this.qRelays = qRelays;
		return this;
	}

	@JsonProperty("acbs")
	public List<Acb> getAcbs() {
		return acbs;
	}

	@JsonProperty("acbs")
	public void setAcbs(List<Acb> acbs) {
		this.acbs = acbs;
	}

	public Devices withAcbs(List<Acb> acbs) {
		this.acbs = acbs;
		return this;
	}

	@JsonProperty("encharges")
	public List<Encharge> getEncharges() {
		return encharges;
	}

	@JsonProperty("encharges")
	public void setEncharges(List<Encharge> encharges) {
		this.encharges = encharges;
	}

	public Devices withEncharges(List<Encharge> encharges) {
		this.encharges = encharges;
		return this;
	}

	@JsonProperty("enpowers")
	public List<Enpower> getEnpowers() {
		return enpowers;
	}

	@JsonProperty("enpowers")
	public void setEnpowers(List<Enpower> enpowers) {
		this.enpowers = enpowers;
	}

	public Devices withEnpowers(List<Enpower> enpowers) {
		this.enpowers = enpowers;
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

	public Devices withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}
