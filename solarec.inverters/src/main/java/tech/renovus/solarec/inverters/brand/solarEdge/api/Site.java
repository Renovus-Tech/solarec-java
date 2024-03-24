
package tech.renovus.solarec.inverters.brand.solarEdge.api;

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
    "id",
    "name",
    "accountId",
    "status",
    "peakPower",
    "currency",
    "installationDate",
    "ptoDate",
    "notes",
    "type",
    "location",
    "alertQuantity",
    "alertSeverity",
    "uris",
    "publicSettings"
})
@Generated("jsonschema2pojo")
public class Site {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("accountId")
    private Integer accountId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("peakPower")
    private Double peakPower;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("installationDate")
    private String installationDate;
    @JsonProperty("ptoDate")
    private String ptoDate;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("type")
    private String type;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("alertQuantity")
    private Integer alertQuantity;
    @JsonProperty("alertSeverity")
    private String alertSeverity;
    @JsonProperty("uris")
    private Uris uris;
    @JsonProperty("publicSettings")
    private PublicSettings publicSettings;
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

    public Site withId(Integer id) {
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

    public Site withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("accountId")
    public Integer getAccountId() {
        return accountId;
    }

    @JsonProperty("accountId")
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Site withAccountId(Integer accountId) {
        this.accountId = accountId;
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

    public Site withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("peakPower")
    public Double getPeakPower() {
        return peakPower;
    }

    @JsonProperty("peakPower")
    public void setPeakPower(Double peakPower) {
        this.peakPower = peakPower;
    }

    public Site withPeakPower(Double peakPower) {
        this.peakPower = peakPower;
        return this;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Site withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    @JsonProperty("installationDate")
    public String getInstallationDate() {
        return installationDate;
    }

    @JsonProperty("installationDate")
    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }

    public Site withInstallationDate(String installationDate) {
        this.installationDate = installationDate;
        return this;
    }

    @JsonProperty("ptoDate")
    public String getPtoDate() {
        return ptoDate;
    }

    @JsonProperty("ptoDate")
    public void setPtoDate(String ptoDate) {
        this.ptoDate = ptoDate;
    }

    public Site withPtoDate(String ptoDate) {
        this.ptoDate = ptoDate;
        return this;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Site withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Site withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public Site withLocation(Location location) {
        this.location = location;
        return this;
    }

    @JsonProperty("alertQuantity")
    public Integer getAlertQuantity() {
        return alertQuantity;
    }

    @JsonProperty("alertQuantity")
    public void setAlertQuantity(Integer alertQuantity) {
        this.alertQuantity = alertQuantity;
    }

    public Site withAlertQuantity(Integer alertQuantity) {
        this.alertQuantity = alertQuantity;
        return this;
    }

    @JsonProperty("alertSeverity")
    public String getAlertSeverity() {
        return alertSeverity;
    }

    @JsonProperty("alertSeverity")
    public void setAlertSeverity(String alertSeverity) {
        this.alertSeverity = alertSeverity;
    }

    public Site withAlertSeverity(String alertSeverity) {
        this.alertSeverity = alertSeverity;
        return this;
    }

    @JsonProperty("uris")
    public Uris getUris() {
        return uris;
    }

    @JsonProperty("uris")
    public void setUris(Uris uris) {
        this.uris = uris;
    }

    public Site withUris(Uris uris) {
        this.uris = uris;
        return this;
    }

    @JsonProperty("publicSettings")
    public PublicSettings getPublicSettings() {
        return publicSettings;
    }

    @JsonProperty("publicSettings")
    public void setPublicSettings(PublicSettings publicSettings) {
        this.publicSettings = publicSettings;
    }

    public Site withPublicSettings(PublicSettings publicSettings) {
        this.publicSettings = publicSettings;
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

    public Site withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
