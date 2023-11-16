
package tech.renovus.solarec.weather.meteoblue.solar;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time",
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
    private List<String> time = null;
    @JsonProperty("gni_instant")
    private List<Double> gniInstant = null;
    @JsonProperty("gni_backwards")
    private List<Double> gniBackwards = null;
    @JsonProperty("dni_instant")
    private List<Double> dniInstant = null;
    @JsonProperty("dni_backwards")
    private List<Double> dniBackwards = null;
    @JsonProperty("dif_instant")
    private List<Double> difInstant = null;
    @JsonProperty("dif_backwards")
    private List<Double> difBackwards = null;
    @JsonProperty("ghi_instant")
    private List<Double> ghiInstant = null;
    @JsonProperty("ghi_backwards")
    private List<Double> ghiBackwards = null;
    @JsonProperty("extraterrestrialradiation_instant")
    private List<Double> extraterrestrialradiationInstant = null;
    @JsonProperty("extraterrestrialradiation_backwards")
    private List<Double> extraterrestrialradiationBackwards = null;

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
    public List<Double> getGniBackwards() {
        return gniBackwards;
    }

    @JsonProperty("gni_backwards")
    public void setGniBackwards(List<Double> gniBackwards) {
        this.gniBackwards = gniBackwards;
    }

    public Data1h withGniBackwards(List<Double> gniBackwards) {
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
    public List<Double> getDniBackwards() {
        return dniBackwards;
    }

    @JsonProperty("dni_backwards")
    public void setDniBackwards(List<Double> dniBackwards) {
        this.dniBackwards = dniBackwards;
    }

    public Data1h withDniBackwards(List<Double> dniBackwards) {
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
    public List<Double> getDifBackwards() {
        return difBackwards;
    }

    @JsonProperty("dif_backwards")
    public void setDifBackwards(List<Double> difBackwards) {
        this.difBackwards = difBackwards;
    }

    public Data1h withDifBackwards(List<Double> difBackwards) {
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
    public List<Double> getExtraterrestrialradiationBackwards() {
        return extraterrestrialradiationBackwards;
    }

    @JsonProperty("extraterrestrialradiation_backwards")
    public void setExtraterrestrialradiationBackwards(List<Double> extraterrestrialradiationBackwards) {
        this.extraterrestrialradiationBackwards = extraterrestrialradiationBackwards;
    }

    public Data1h withExtraterrestrialradiationBackwards(List<Double> extraterrestrialradiationBackwards) {
        this.extraterrestrialradiationBackwards = extraterrestrialradiationBackwards;
        return this;
    }

}
