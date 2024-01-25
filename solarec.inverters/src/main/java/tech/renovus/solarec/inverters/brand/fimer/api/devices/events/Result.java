
package tech.renovus.solarec.inverters.brand.fimer.api.devices.events;

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
    "content",
    "lastPage",
    "fisrtPage",
    "numberOfElements",
    "totalElements",
    "totalPages",
    "pageSize",
    "pageNumber"
})
@Generated("jsonschema2pojo")
public class Result {

    @JsonProperty("content")
    private List<Content> content;
    @JsonProperty("lastPage")
    private String lastPage;
    @JsonProperty("fisrtPage")
    private String fisrtPage;
    @JsonProperty("numberOfElements")
    private Integer numberOfElements;
    @JsonProperty("totalElements")
    private Integer totalElements;
    @JsonProperty("totalPages")
    private Integer totalPages;
    @JsonProperty("pageSize")
    private Integer pageSize;
    @JsonProperty("pageNumber")
    private Integer pageNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("content")
    public List<Content> getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(List<Content> content) {
        this.content = content;
    }

    public Result withContent(List<Content> content) {
        this.content = content;
        return this;
    }

    @JsonProperty("lastPage")
    public String getLastPage() {
        return lastPage;
    }

    @JsonProperty("lastPage")
    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    public Result withLastPage(String lastPage) {
        this.lastPage = lastPage;
        return this;
    }

    @JsonProperty("fisrtPage")
    public String getFisrtPage() {
        return fisrtPage;
    }

    @JsonProperty("fisrtPage")
    public void setFisrtPage(String fisrtPage) {
        this.fisrtPage = fisrtPage;
    }

    public Result withFisrtPage(String fisrtPage) {
        this.fisrtPage = fisrtPage;
        return this;
    }

    @JsonProperty("numberOfElements")
    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    @JsonProperty("numberOfElements")
    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Result withNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
        return this;
    }

    @JsonProperty("totalElements")
    public Integer getTotalElements() {
        return totalElements;
    }

    @JsonProperty("totalElements")
    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Result withTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    @JsonProperty("totalPages")
    public Integer getTotalPages() {
        return totalPages;
    }

    @JsonProperty("totalPages")
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Result withTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty("pageSize")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Result withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    @JsonProperty("pageNumber")
    public Integer getPageNumber() {
        return pageNumber;
    }

    @JsonProperty("pageNumber")
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Result withPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
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

    public Result withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
