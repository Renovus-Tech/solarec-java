
package tech.renovus.solarec.weather.meteoblue.api;

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
    "time",
    "sunshinetime",
    "lowclouds",
    "midclouds",
    "highclouds",
    "visibility",
    "totalcloudcover",
    "precipitation",
    "snowfraction",
    "rainspot",
    "temperature",
    "felttemperature",
    "pictocode",
    "windspeed",
    "winddirection",
    "relativehumidity",
    "sealevelpressure",
    "precipitation_probability",
    "convective_precipitation",
    "isdaylight",
    "uvindex",
    "gni_instant",
    "gni_backwards",
    "dni_instant",
    "dni_backwards",
    "dif_instant",
    "dif_backwards",
    "ghi_instant",
    "ghi_backwards",
    "extraterrestrialradiation_instant",
    "extraterrestrialradiation_backwards"
})
@Generated("jsonschema2pojo")
public class Data1h {

    @JsonProperty("time")
    private List<String> time;
    @JsonProperty("sunshinetime")
    private List<Object> sunshinetime;
    @JsonProperty("lowclouds")
    private List<Integer> lowclouds;
    @JsonProperty("midclouds")
    private List<Integer> midclouds;
    @JsonProperty("highclouds")
    private List<Integer> highclouds;
    @JsonProperty("visibility")
    private List<Integer> visibility;
    @JsonProperty("totalcloudcover")
    private List<Integer> totalcloudcover;
    @JsonProperty("precipitation")
    private List<Double> precipitation;
    @JsonProperty("snowfraction")
    private List<Double> snowfraction;
    @JsonProperty("rainspot")
    private List<String> rainspot;
    @JsonProperty("temperature")
    private List<Double> temperature;
    @JsonProperty("felttemperature")
    private List<Double> felttemperature;
    @JsonProperty("pictocode")
    private List<Integer> pictocode;
    @JsonProperty("windspeed")
    private List<Double> windspeed;
    @JsonProperty("winddirection")
    private List<Integer> winddirection;
    @JsonProperty("relativehumidity")
    private List<Integer> relativehumidity;
    @JsonProperty("sealevelpressure")
    private List<Double> sealevelpressure;
    @JsonProperty("precipitation_probability")
    private List<Integer> precipitationProbability;
    @JsonProperty("convective_precipitation")
    private List<Double> convectivePrecipitation;
    @JsonProperty("isdaylight")
    private List<Integer> isdaylight;
    @JsonProperty("uvindex")
    private List<Integer> uvindex;
    @JsonProperty("gni_instant")
    private List<Double> gniInstant;
    @JsonProperty("gni_backwards")
    private List<Object> gniBackwards;
    @JsonProperty("dni_instant")
    private List<Double> dniInstant;
    @JsonProperty("dni_backwards")
    private List<Object> dniBackwards;
    @JsonProperty("dif_instant")
    private List<Double> difInstant;
    @JsonProperty("dif_backwards")
    private List<Object> difBackwards;
    @JsonProperty("ghi_instant")
    private List<Double> ghiInstant;
    @JsonProperty("ghi_backwards")
    private List<Double> ghiBackwards;
    @JsonProperty("extraterrestrialradiation_instant")
    private List<Double> extraterrestrialradiationInstant;
    @JsonProperty("extraterrestrialradiation_backwards")
    private List<Object> extraterrestrialradiationBackwards;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("time")
    public List<String> getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(List<String> time) {
        this.time = time;
    }

    public Data1h withTime(List<String> time) {
        this.time = time;
        return this;
    }

    @JsonProperty("sunshinetime")
    public List<Object> getSunshinetime() {
        return sunshinetime;
    }

    @JsonProperty("sunshinetime")
    public void setSunshinetime(List<Object> sunshinetime) {
        this.sunshinetime = sunshinetime;
    }

    public Data1h withSunshinetime(List<Object> sunshinetime) {
        this.sunshinetime = sunshinetime;
        return this;
    }

    @JsonProperty("lowclouds")
    public List<Integer> getLowclouds() {
        return lowclouds;
    }

    @JsonProperty("lowclouds")
    public void setLowclouds(List<Integer> lowclouds) {
        this.lowclouds = lowclouds;
    }

    public Data1h withLowclouds(List<Integer> lowclouds) {
        this.lowclouds = lowclouds;
        return this;
    }

    @JsonProperty("midclouds")
    public List<Integer> getMidclouds() {
        return midclouds;
    }

    @JsonProperty("midclouds")
    public void setMidclouds(List<Integer> midclouds) {
        this.midclouds = midclouds;
    }

    public Data1h withMidclouds(List<Integer> midclouds) {
        this.midclouds = midclouds;
        return this;
    }

    @JsonProperty("highclouds")
    public List<Integer> getHighclouds() {
        return highclouds;
    }

    @JsonProperty("highclouds")
    public void setHighclouds(List<Integer> highclouds) {
        this.highclouds = highclouds;
    }

    public Data1h withHighclouds(List<Integer> highclouds) {
        this.highclouds = highclouds;
        return this;
    }

    @JsonProperty("visibility")
    public List<Integer> getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(List<Integer> visibility) {
        this.visibility = visibility;
    }

    public Data1h withVisibility(List<Integer> visibility) {
        this.visibility = visibility;
        return this;
    }

    @JsonProperty("totalcloudcover")
    public List<Integer> getTotalcloudcover() {
        return totalcloudcover;
    }

    @JsonProperty("totalcloudcover")
    public void setTotalcloudcover(List<Integer> totalcloudcover) {
        this.totalcloudcover = totalcloudcover;
    }

    public Data1h withTotalcloudcover(List<Integer> totalcloudcover) {
        this.totalcloudcover = totalcloudcover;
        return this;
    }

    @JsonProperty("precipitation")
    public List<Double> getPrecipitation() {
        return precipitation;
    }

    @JsonProperty("precipitation")
    public void setPrecipitation(List<Double> precipitation) {
        this.precipitation = precipitation;
    }

    public Data1h withPrecipitation(List<Double> precipitation) {
        this.precipitation = precipitation;
        return this;
    }

    @JsonProperty("snowfraction")
    public List<Double> getSnowfraction() {
        return snowfraction;
    }

    @JsonProperty("snowfraction")
    public void setSnowfraction(List<Double> snowfraction) {
        this.snowfraction = snowfraction;
    }

    public Data1h withSnowfraction(List<Double> snowfraction) {
        this.snowfraction = snowfraction;
        return this;
    }

    @JsonProperty("rainspot")
    public List<String> getRainspot() {
        return rainspot;
    }

    @JsonProperty("rainspot")
    public void setRainspot(List<String> rainspot) {
        this.rainspot = rainspot;
    }

    public Data1h withRainspot(List<String> rainspot) {
        this.rainspot = rainspot;
        return this;
    }

    @JsonProperty("temperature")
    public List<Double> getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(List<Double> temperature) {
        this.temperature = temperature;
    }

    public Data1h withTemperature(List<Double> temperature) {
        this.temperature = temperature;
        return this;
    }

    @JsonProperty("felttemperature")
    public List<Double> getFelttemperature() {
        return felttemperature;
    }

    @JsonProperty("felttemperature")
    public void setFelttemperature(List<Double> felttemperature) {
        this.felttemperature = felttemperature;
    }

    public Data1h withFelttemperature(List<Double> felttemperature) {
        this.felttemperature = felttemperature;
        return this;
    }

    @JsonProperty("pictocode")
    public List<Integer> getPictocode() {
        return pictocode;
    }

    @JsonProperty("pictocode")
    public void setPictocode(List<Integer> pictocode) {
        this.pictocode = pictocode;
    }

    public Data1h withPictocode(List<Integer> pictocode) {
        this.pictocode = pictocode;
        return this;
    }

    @JsonProperty("windspeed")
    public List<Double> getWindspeed() {
        return windspeed;
    }

    @JsonProperty("windspeed")
    public void setWindspeed(List<Double> windspeed) {
        this.windspeed = windspeed;
    }

    public Data1h withWindspeed(List<Double> windspeed) {
        this.windspeed = windspeed;
        return this;
    }

    @JsonProperty("winddirection")
    public List<Integer> getWinddirection() {
        return winddirection;
    }

    @JsonProperty("winddirection")
    public void setWinddirection(List<Integer> winddirection) {
        this.winddirection = winddirection;
    }

    public Data1h withWinddirection(List<Integer> winddirection) {
        this.winddirection = winddirection;
        return this;
    }

    @JsonProperty("relativehumidity")
    public List<Integer> getRelativehumidity() {
        return relativehumidity;
    }

    @JsonProperty("relativehumidity")
    public void setRelativehumidity(List<Integer> relativehumidity) {
        this.relativehumidity = relativehumidity;
    }

    public Data1h withRelativehumidity(List<Integer> relativehumidity) {
        this.relativehumidity = relativehumidity;
        return this;
    }

    @JsonProperty("sealevelpressure")
    public List<Double> getSealevelpressure() {
        return sealevelpressure;
    }

    @JsonProperty("sealevelpressure")
    public void setSealevelpressure(List<Double> sealevelpressure) {
        this.sealevelpressure = sealevelpressure;
    }

    public Data1h withSealevelpressure(List<Double> sealevelpressure) {
        this.sealevelpressure = sealevelpressure;
        return this;
    }

    @JsonProperty("precipitation_probability")
    public List<Integer> getPrecipitationProbability() {
        return precipitationProbability;
    }

    @JsonProperty("precipitation_probability")
    public void setPrecipitationProbability(List<Integer> precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public Data1h withPrecipitationProbability(List<Integer> precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
        return this;
    }

    @JsonProperty("convective_precipitation")
    public List<Double> getConvectivePrecipitation() {
        return convectivePrecipitation;
    }

    @JsonProperty("convective_precipitation")
    public void setConvectivePrecipitation(List<Double> convectivePrecipitation) {
        this.convectivePrecipitation = convectivePrecipitation;
    }

    public Data1h withConvectivePrecipitation(List<Double> convectivePrecipitation) {
        this.convectivePrecipitation = convectivePrecipitation;
        return this;
    }

    @JsonProperty("isdaylight")
    public List<Integer> getIsdaylight() {
        return isdaylight;
    }

    @JsonProperty("isdaylight")
    public void setIsdaylight(List<Integer> isdaylight) {
        this.isdaylight = isdaylight;
    }

    public Data1h withIsdaylight(List<Integer> isdaylight) {
        this.isdaylight = isdaylight;
        return this;
    }

    @JsonProperty("uvindex")
    public List<Integer> getUvindex() {
        return uvindex;
    }

    @JsonProperty("uvindex")
    public void setUvindex(List<Integer> uvindex) {
        this.uvindex = uvindex;
    }

    public Data1h withUvindex(List<Integer> uvindex) {
        this.uvindex = uvindex;
        return this;
    }

    @JsonProperty("gni_instant")
    public List<Double> getGniInstant() {
        return gniInstant;
    }

    @JsonProperty("gni_instant")
    public void setGniInstant(List<Double> gniInstant) {
        this.gniInstant = gniInstant;
    }

    public Data1h withGniInstant(List<Double> gniInstant) {
        this.gniInstant = gniInstant;
        return this;
    }

    @JsonProperty("gni_backwards")
    public List<Object> getGniBackwards() {
        return gniBackwards;
    }

    @JsonProperty("gni_backwards")
    public void setGniBackwards(List<Object> gniBackwards) {
        this.gniBackwards = gniBackwards;
    }

    public Data1h withGniBackwards(List<Object> gniBackwards) {
        this.gniBackwards = gniBackwards;
        return this;
    }

    @JsonProperty("dni_instant")
    public List<Double> getDniInstant() {
        return dniInstant;
    }

    @JsonProperty("dni_instant")
    public void setDniInstant(List<Double> dniInstant) {
        this.dniInstant = dniInstant;
    }

    public Data1h withDniInstant(List<Double> dniInstant) {
        this.dniInstant = dniInstant;
        return this;
    }

    @JsonProperty("dni_backwards")
    public List<Object> getDniBackwards() {
        return dniBackwards;
    }

    @JsonProperty("dni_backwards")
    public void setDniBackwards(List<Object> dniBackwards) {
        this.dniBackwards = dniBackwards;
    }

    public Data1h withDniBackwards(List<Object> dniBackwards) {
        this.dniBackwards = dniBackwards;
        return this;
    }

    @JsonProperty("dif_instant")
    public List<Double> getDifInstant() {
        return difInstant;
    }

    @JsonProperty("dif_instant")
    public void setDifInstant(List<Double> difInstant) {
        this.difInstant = difInstant;
    }

    public Data1h withDifInstant(List<Double> difInstant) {
        this.difInstant = difInstant;
        return this;
    }

    @JsonProperty("dif_backwards")
    public List<Object> getDifBackwards() {
        return difBackwards;
    }

    @JsonProperty("dif_backwards")
    public void setDifBackwards(List<Object> difBackwards) {
        this.difBackwards = difBackwards;
    }

    public Data1h withDifBackwards(List<Object> difBackwards) {
        this.difBackwards = difBackwards;
        return this;
    }

    @JsonProperty("ghi_instant")
    public List<Double> getGhiInstant() {
        return ghiInstant;
    }

    @JsonProperty("ghi_instant")
    public void setGhiInstant(List<Double> ghiInstant) {
        this.ghiInstant = ghiInstant;
    }

    public Data1h withGhiInstant(List<Double> ghiInstant) {
        this.ghiInstant = ghiInstant;
        return this;
    }

    @JsonProperty("ghi_backwards")
    public List<Double> getGhiBackwards() {
        return ghiBackwards;
    }

    @JsonProperty("ghi_backwards")
    public void setGhiBackwards(List<Double> ghiBackwards) {
        this.ghiBackwards = ghiBackwards;
    }

    public Data1h withGhiBackwards(List<Double> ghiBackwards) {
        this.ghiBackwards = ghiBackwards;
        return this;
    }

    @JsonProperty("extraterrestrialradiation_instant")
    public List<Double> getExtraterrestrialradiationInstant() {
        return extraterrestrialradiationInstant;
    }

    @JsonProperty("extraterrestrialradiation_instant")
    public void setExtraterrestrialradiationInstant(List<Double> extraterrestrialradiationInstant) {
        this.extraterrestrialradiationInstant = extraterrestrialradiationInstant;
    }

    public Data1h withExtraterrestrialradiationInstant(List<Double> extraterrestrialradiationInstant) {
        this.extraterrestrialradiationInstant = extraterrestrialradiationInstant;
        return this;
    }

    @JsonProperty("extraterrestrialradiation_backwards")
    public List<Object> getExtraterrestrialradiationBackwards() {
        return extraterrestrialradiationBackwards;
    }

    @JsonProperty("extraterrestrialradiation_backwards")
    public void setExtraterrestrialradiationBackwards(List<Object> extraterrestrialradiationBackwards) {
        this.extraterrestrialradiationBackwards = extraterrestrialradiationBackwards;
    }

    public Data1h withExtraterrestrialradiationBackwards(List<Object> extraterrestrialradiationBackwards) {
        this.extraterrestrialradiationBackwards = extraterrestrialradiationBackwards;
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

    public Data1h withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
