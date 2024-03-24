
package tech.renovus.solarec.inverters.brand.aiswei.api;

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
    "totalcount",
    "pagesize",
    "page",
    "list"
})
@Generated("jsonschema2pojo")
public class PlantListData {

    @JsonProperty("totalcount")
    private Integer totalcount;
    @JsonProperty("pagesize")
    private Integer pagesize;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("list")
    private List<Plant> list;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("totalcount")
    public Integer getTotalcount() {
        return totalcount;
    }

    @JsonProperty("totalcount")
    public void setTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
    }

    public PlantListData withTotalcount(Integer totalcount) {
        this.totalcount = totalcount;
        return this;
    }

    @JsonProperty("pagesize")
    public Integer getPagesize() {
        return pagesize;
    }

    @JsonProperty("pagesize")
    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public PlantListData withPagesize(Integer pagesize) {
        this.pagesize = pagesize;
        return this;
    }

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    public PlantListData withPage(Integer page) {
        this.page = page;
        return this;
    }

    @JsonProperty("list")
    public List<Plant> getList() {
        return list;
    }

    @JsonProperty("list")
    public void setList(List<Plant> list) {
        this.list = list;
    }

    public PlantListData withList(List<Plant> list) {
        this.list = list;
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

    public PlantListData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
