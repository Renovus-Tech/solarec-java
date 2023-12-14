
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
    "id",
    "name",
    "address",
    "zipCode",
    "city",
    "country",
    "organizationType",
    "status",
    "documentIds",
    "signatoryDocumentIds",
    "blockchainAccountAddress",
    "blockchainAccountSignedMessage"
})
@Generated("jsonschema2pojo")
public class OrganizationResponse {

    @JsonProperty("id")
    private Integer id;
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
    @JsonProperty("status")
    private String status;
    @JsonProperty("documentIds")
    private List<String> documentIds;
    @JsonProperty("signatoryDocumentIds")
    private List<String> signatoryDocumentIds;
    @JsonProperty("blockchainAccountAddress")
    private String blockchainAccountAddress;
    @JsonProperty("blockchainAccountSignedMessage")
    private String blockchainAccountSignedMessage;
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

    public OrganizationResponse withId(Integer id) {
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

    public OrganizationResponse withName(String name) {
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

    public OrganizationResponse withAddress(String address) {
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

    public OrganizationResponse withZipCode(String zipCode) {
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

    public OrganizationResponse withCity(String city) {
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

    public OrganizationResponse withCountry(String country) {
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

    public OrganizationResponse withOrganizationType(String organizationType) {
        this.organizationType = organizationType;
        return this;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public OrganizationResponse withStatus(String status) {
        this.status = status;
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

    public OrganizationResponse withDocumentIds(List<String> documentIds) {
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

    public OrganizationResponse withSignatoryDocumentIds(List<String> signatoryDocumentIds) {
        this.signatoryDocumentIds = signatoryDocumentIds;
        return this;
    }

    @JsonProperty("blockchainAccountAddress")
    public String getBlockchainAccountAddress() {
        return blockchainAccountAddress;
    }

    @JsonProperty("blockchainAccountAddress")
    public void setBlockchainAccountAddress(String blockchainAccountAddress) {
        this.blockchainAccountAddress = blockchainAccountAddress;
    }

    public OrganizationResponse withBlockchainAccountAddress(String blockchainAccountAddress) {
        this.blockchainAccountAddress = blockchainAccountAddress;
        return this;
    }

    @JsonProperty("blockchainAccountSignedMessage")
    public String getBlockchainAccountSignedMessage() {
        return blockchainAccountSignedMessage;
    }

    @JsonProperty("blockchainAccountSignedMessage")
    public void setBlockchainAccountSignedMessage(String blockchainAccountSignedMessage) {
        this.blockchainAccountSignedMessage = blockchainAccountSignedMessage;
    }

    public OrganizationResponse withBlockchainAccountSignedMessage(String blockchainAccountSignedMessage) {
        this.blockchainAccountSignedMessage = blockchainAccountSignedMessage;
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

    public OrganizationResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
