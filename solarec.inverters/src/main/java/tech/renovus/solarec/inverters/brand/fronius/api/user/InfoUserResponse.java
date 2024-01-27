
package tech.renovus.solarec.inverters.brand.fronius.api.user;

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
    "name",
    "address",
    "contractInformation",
    "settings",
    "accountAttributes"
})
@Generated("jsonschema2pojo")
public class InfoUserResponse {

    @JsonProperty("name")
    private Name name;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("contractInformation")
    private ContractInformation contractInformation;
    @JsonProperty("settings")
    private Settings settings;
    @JsonProperty("accountAttributes")
    private AccountAttributes accountAttributes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("name")
    public Name getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Name name) {
        this.name = name;
    }

    public InfoUserResponse withName(Name name) {
        this.name = name;
        return this;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    public InfoUserResponse withAddress(Address address) {
        this.address = address;
        return this;
    }

    @JsonProperty("contractInformation")
    public ContractInformation getContractInformation() {
        return contractInformation;
    }

    @JsonProperty("contractInformation")
    public void setContractInformation(ContractInformation contractInformation) {
        this.contractInformation = contractInformation;
    }

    public InfoUserResponse withContractInformation(ContractInformation contractInformation) {
        this.contractInformation = contractInformation;
        return this;
    }

    @JsonProperty("settings")
    public Settings getSettings() {
        return settings;
    }

    @JsonProperty("settings")
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public InfoUserResponse withSettings(Settings settings) {
        this.settings = settings;
        return this;
    }

    @JsonProperty("accountAttributes")
    public AccountAttributes getAccountAttributes() {
        return accountAttributes;
    }

    @JsonProperty("accountAttributes")
    public void setAccountAttributes(AccountAttributes accountAttributes) {
        this.accountAttributes = accountAttributes;
    }

    public InfoUserResponse withAccountAttributes(AccountAttributes accountAttributes) {
        this.accountAttributes = accountAttributes;
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

    public InfoUserResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
