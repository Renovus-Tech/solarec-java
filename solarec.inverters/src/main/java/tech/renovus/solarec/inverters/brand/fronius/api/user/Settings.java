
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
    "timeZone",
    "dateFormat",
    "timeFormat",
    "language"
})
@Generated("jsonschema2pojo")
public class Settings {

    @JsonProperty("timeZone")
    private String timeZone;
    @JsonProperty("dateFormat")
    private String dateFormat;
    @JsonProperty("timeFormat")
    private String timeFormat;
    @JsonProperty("language")
    private String language;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("timeZone")
    public String getTimeZone() {
        return timeZone;
    }

    @JsonProperty("timeZone")
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Settings withTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    @JsonProperty("dateFormat")
    public String getDateFormat() {
        return dateFormat;
    }

    @JsonProperty("dateFormat")
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Settings withDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    @JsonProperty("timeFormat")
    public String getTimeFormat() {
        return timeFormat;
    }

    @JsonProperty("timeFormat")
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public Settings withTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
        return this;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public Settings withLanguage(String language) {
        this.language = language;
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

    public Settings withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
