
package tech.renovus.solarec.inverters.brand.solis.api.inveterList;

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
    "records",
    "total",
    "size",
    "current",
    "orders",
    "optimizeCountSql",
    "searchCount",
    "pages"
})
@Generated("jsonschema2pojo")
public class Page {

    @JsonProperty("records")
    private List<Record> records;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("current")
    private Integer current;
    @JsonProperty("orders")
    private List<Object> orders;
    @JsonProperty("optimizeCountSql")
    private Boolean optimizeCountSql;
    @JsonProperty("searchCount")
    private Boolean searchCount;
    @JsonProperty("pages")
    private Integer pages;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("records")
    public List<Record> getRecords() {
        return records;
    }

    @JsonProperty("records")
    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Page withRecords(List<Record> records) {
        this.records = records;
        return this;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    public Page withTotal(Integer total) {
        this.total = total;
        return this;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    public Page withSize(Integer size) {
        this.size = size;
        return this;
    }

    @JsonProperty("current")
    public Integer getCurrent() {
        return current;
    }

    @JsonProperty("current")
    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Page withCurrent(Integer current) {
        this.current = current;
        return this;
    }

    @JsonProperty("orders")
    public List<Object> getOrders() {
        return orders;
    }

    @JsonProperty("orders")
    public void setOrders(List<Object> orders) {
        this.orders = orders;
    }

    public Page withOrders(List<Object> orders) {
        this.orders = orders;
        return this;
    }

    @JsonProperty("optimizeCountSql")
    public Boolean getOptimizeCountSql() {
        return optimizeCountSql;
    }

    @JsonProperty("optimizeCountSql")
    public void setOptimizeCountSql(Boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
    }

    public Page withOptimizeCountSql(Boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }

    @JsonProperty("searchCount")
    public Boolean getSearchCount() {
        return searchCount;
    }

    @JsonProperty("searchCount")
    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public Page withSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
        return this;
    }

    @JsonProperty("pages")
    public Integer getPages() {
        return pages;
    }

    @JsonProperty("pages")
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Page withPages(Integer pages) {
        this.pages = pages;
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

    public Page withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
