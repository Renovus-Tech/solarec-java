
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
    "premiumMembership",
    "termsAcceptedLatest",
    "termsAcceptedVersion"
})
@Generated("jsonschema2pojo")
public class AccountAttributes {

    @JsonProperty("premiumMembership")
    private Boolean premiumMembership;
    @JsonProperty("termsAcceptedLatest")
    private Boolean termsAcceptedLatest;
    @JsonProperty("termsAcceptedVersion")
    private Integer termsAcceptedVersion;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("premiumMembership")
    public Boolean getPremiumMembership() {
        return premiumMembership;
    }

    @JsonProperty("premiumMembership")
    public void setPremiumMembership(Boolean premiumMembership) {
        this.premiumMembership = premiumMembership;
    }

    public AccountAttributes withPremiumMembership(Boolean premiumMembership) {
        this.premiumMembership = premiumMembership;
        return this;
    }

    @JsonProperty("termsAcceptedLatest")
    public Boolean getTermsAcceptedLatest() {
        return termsAcceptedLatest;
    }

    @JsonProperty("termsAcceptedLatest")
    public void setTermsAcceptedLatest(Boolean termsAcceptedLatest) {
        this.termsAcceptedLatest = termsAcceptedLatest;
    }

    public AccountAttributes withTermsAcceptedLatest(Boolean termsAcceptedLatest) {
        this.termsAcceptedLatest = termsAcceptedLatest;
        return this;
    }

    @JsonProperty("termsAcceptedVersion")
    public Integer getTermsAcceptedVersion() {
        return termsAcceptedVersion;
    }

    @JsonProperty("termsAcceptedVersion")
    public void setTermsAcceptedVersion(Integer termsAcceptedVersion) {
        this.termsAcceptedVersion = termsAcceptedVersion;
    }

    public AccountAttributes withTermsAcceptedVersion(Integer termsAcceptedVersion) {
        this.termsAcceptedVersion = termsAcceptedVersion;
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

    public AccountAttributes withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
