
package tech.renovus.solarec.connection.api;

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
    "x-forwarded-proto",
    "x-forwarded-port",
    "host",
    "x-amzn-trace-id",
    "accept-encoding",
    "user-agent",
    "accept",
})
@Generated("jsonschema2pojo")
public class Headers {

    @JsonProperty("x-forwarded-proto")
    private String xForwardedProto;
    @JsonProperty("x-forwarded-port")
    private String xForwardedPort;
    @JsonProperty("host")
    private String host;
    @JsonProperty("x-amzn-trace-id")
    private String xAmznTraceId;
    @JsonProperty("accept-encoding")
    private String acceptEncoding;
    @JsonProperty("user-agent")
    private String userAgent;
    @JsonProperty("accept")
    private String accept;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("x-forwarded-proto")
    public String getxForwardedProto() {
        return xForwardedProto;
    }

    @JsonProperty("x-forwarded-proto")
    public void setxForwardedProto(String xForwardedProto) {
        this.xForwardedProto = xForwardedProto;
    }

    @JsonProperty("x-forwarded-port")
    public String getxForwardedPort() {
        return xForwardedPort;
    }

    @JsonProperty("x-forwarded-port")
    public void setxForwardedPort(String xForwardedPort) {
        this.xForwardedPort = xForwardedPort;
    }

    @JsonProperty("host")
    public String getHost() {
        return host;
    }

    @JsonProperty("host")
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty("x-amzn-trace-id")
    public String getxAmznTraceId() {
        return xAmznTraceId;
    }

    @JsonProperty("x-amzn-trace-id")
    public void setxAmznTraceId(String xAmznTraceId) {
        this.xAmznTraceId = xAmznTraceId;
    }

    @JsonProperty("accept-encoding")
    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    @JsonProperty("accept-encoding")
    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    @JsonProperty("user-agent")
    public String getUserAgent() {
        return userAgent;
    }

    @JsonProperty("user-agent")
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @JsonProperty("accept")
    public String getAccept() {
        return accept;
    }

    @JsonProperty("accept")
    public void setAccept(String accept) {
        this.accept = accept;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
