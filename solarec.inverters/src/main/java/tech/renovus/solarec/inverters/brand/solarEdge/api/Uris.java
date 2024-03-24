
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
    "PUBLIC_URL",
    "IMAGE_URI"
})
@Generated("jsonschema2pojo")
public class Uris {

    @JsonProperty("PUBLIC_URL")
    private String publicUrl;
    @JsonProperty("IMAGE_URI")
    private String imageUri;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("PUBLIC_URL")
    public String getPublicUrl() {
        return publicUrl;
    }

    @JsonProperty("PUBLIC_URL")
    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    public Uris withPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
        return this;
    }

    @JsonProperty("IMAGE_URI")
    public String getImageUri() {
        return imageUri;
    }

    @JsonProperty("IMAGE_URI")
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Uris withImageUri(String imageUri) {
        this.imageUri = imageUri;
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

    public Uris withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
