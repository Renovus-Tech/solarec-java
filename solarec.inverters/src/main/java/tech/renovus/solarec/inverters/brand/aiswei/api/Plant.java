
package tech.renovus.solarec.inverters.brand.aiswei.api;

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
    "country",
    "totalpower",
    "apikey",
    "city",
    "ludt",
    "etoday",
    "createdt",
    "wd",
    "etotal",
    "imgurl",
    "province",
    "name",
    "jd",
    "status"
})
@Generated("jsonschema2pojo")
public class Plant {

    @JsonProperty("country")
    private String country;
    @JsonProperty("totalpower")
    private String totalpower;
    @JsonProperty("apikey")
    private String apikey;
    @JsonProperty("city")
    private String city;
    @JsonProperty("ludt")
    private String ludt;
    @JsonProperty("etoday")
    private String etoday;
    @JsonProperty("createdt")
    private String createdt;
    @JsonProperty("wd")
    private String wd;
    @JsonProperty("etotal")
    private String etotal;
    @JsonProperty("imgurl")
    private String imgurl;
    @JsonProperty("province")
    private String province;
    @JsonProperty("name")
    private String name;
    @JsonProperty("jd")
    private String jd;
    @JsonProperty("status")
    private Integer status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    public Plant withCountry(String country) {
        this.country = country;
        return this;
    }

    @JsonProperty("totalpower")
    public String getTotalpower() {
        return totalpower;
    }

    @JsonProperty("totalpower")
    public void setTotalpower(String totalpower) {
        this.totalpower = totalpower;
    }

    public Plant withTotalpower(String totalpower) {
        this.totalpower = totalpower;
        return this;
    }

    @JsonProperty("apikey")
    public String getApikey() {
        return apikey;
    }

    @JsonProperty("apikey")
    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Plant withApikey(String apikey) {
        this.apikey = apikey;
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

    public Plant withCity(String city) {
        this.city = city;
        return this;
    }

    @JsonProperty("ludt")
    public String getLudt() {
        return ludt;
    }

    @JsonProperty("ludt")
    public void setLudt(String ludt) {
        this.ludt = ludt;
    }

    public Plant withLudt(String ludt) {
        this.ludt = ludt;
        return this;
    }

    @JsonProperty("etoday")
    public String getEtoday() {
        return etoday;
    }

    @JsonProperty("etoday")
    public void setEtoday(String etoday) {
        this.etoday = etoday;
    }

    public Plant withEtoday(String etoday) {
        this.etoday = etoday;
        return this;
    }

    @JsonProperty("createdt")
    public String getCreatedt() {
        return createdt;
    }

    @JsonProperty("createdt")
    public void setCreatedt(String createdt) {
        this.createdt = createdt;
    }

    public Plant withCreatedt(String createdt) {
        this.createdt = createdt;
        return this;
    }

    @JsonProperty("wd")
    public String getWd() {
        return wd;
    }

    @JsonProperty("wd")
    public void setWd(String wd) {
        this.wd = wd;
    }

    public Plant withWd(String wd) {
        this.wd = wd;
        return this;
    }

    @JsonProperty("etotal")
    public String getEtotal() {
        return etotal;
    }

    @JsonProperty("etotal")
    public void setEtotal(String etotal) {
        this.etotal = etotal;
    }

    public Plant withEtotal(String etotal) {
        this.etotal = etotal;
        return this;
    }

    @JsonProperty("imgurl")
    public String getImgurl() {
        return imgurl;
    }

    @JsonProperty("imgurl")
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Plant withImgurl(String imgurl) {
        this.imgurl = imgurl;
        return this;
    }

    @JsonProperty("province")
    public String getProvince() {
        return province;
    }

    @JsonProperty("province")
    public void setProvince(String province) {
        this.province = province;
    }

    public Plant withProvince(String province) {
        this.province = province;
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

    public Plant withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("jd")
    public String getJd() {
        return jd;
    }

    @JsonProperty("jd")
    public void setJd(String jd) {
        this.jd = jd;
    }

    public Plant withJd(String jd) {
        this.jd = jd;
        return this;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Plant withStatus(Integer status) {
        this.status = status;
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

    public Plant withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
