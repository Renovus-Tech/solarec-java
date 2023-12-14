
package tech.renovus.solarec.certificate.drecs.api.organization;

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
    "name",
    "address",
    "zipCode",
    "city",
    "country",
    "organizationType",
    "orgEmail",
    "documentIds",
    "signatoryDocumentIds"
})
@Generated("jsonschema2pojo")
public class Organization {

    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private String address;
    @JsonProperty("zipCode")
    private String zipCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("organizationType")
    private String organizationType;
    @JsonProperty("orgEmail")
    private String orgEmail;
    @JsonProperty("documentIds")
    private List<String> documentIds;
    @JsonProperty("signatoryDocumentIds")
    private List<String> signatoryDocumentIds;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Organization withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    public Organization withAddress(String address) {
        this.address = address;
        return this;
    }

    @JsonProperty("zipCode")
    public String getZipCode() {
        return zipCode;
    }

    @JsonProperty("zipCode")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Organization withZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    public Organization withCity(String city) {
        this.city = city;
        return this;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    public Organization withCountry(String country) {
        this.country = country;
        return this;
    }

    @JsonProperty("organizationType")
    public String getOrganizationType() {
        return organizationType;
    }

    @JsonProperty("organizationType")
    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public Organization withOrganizationType(String organizationType) {
        this.organizationType = organizationType;
        return this;
    }

    @JsonProperty("orgEmail")
    public String getOrgEmail() {
        return orgEmail;
    }

    @JsonProperty("orgEmail")
    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public Organization withOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
        return this;
    }

    @JsonProperty("documentIds")
    public List<String> getDocumentIds() {
        return documentIds;
    }

    @JsonProperty("documentIds")
    public void setDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
    }

    public Organization withDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
        return this;
    }

    @JsonProperty("signatoryDocumentIds")
    public List<String> getSignatoryDocumentIds() {
        return signatoryDocumentIds;
    }

    @JsonProperty("signatoryDocumentIds")
    public void setSignatoryDocumentIds(List<String> signatoryDocumentIds) {
        this.signatoryDocumentIds = signatoryDocumentIds;
    }

    public Organization withSignatoryDocumentIds(List<String> signatoryDocumentIds) {
        this.signatoryDocumentIds = signatoryDocumentIds;
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

    public Organization withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
