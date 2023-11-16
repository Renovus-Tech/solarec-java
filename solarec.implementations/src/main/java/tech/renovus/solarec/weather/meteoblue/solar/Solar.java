
package tech.renovus.solarec.weather.meteoblue.solar;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "metadata",
    "units",
    "data_1h"
})
@Generated("jsonschema2pojo")
public class Solar {

    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("units")
    private Units units;
    @JsonProperty("data_1h")
    private Data1h data1h;

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Solar withMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    @JsonProperty("units")
    public Units getUnits() {
        return units;
    }

    @JsonProperty("units")
    public void setUnits(Units units) {
        this.units = units;
    }

    public Solar withUnits(Units units) {
        this.units = units;
        return this;
    }

    @JsonProperty("data_1h")
    public Data1h getData1h() {
        return data1h;
    }

    @JsonProperty("data_1h")
    public void setData1h(Data1h data1h) {
        this.data1h = data1h;
    }

    public Solar withData1h(Data1h data1h) {
        this.data1h = data1h;
        return this;
    }

}
