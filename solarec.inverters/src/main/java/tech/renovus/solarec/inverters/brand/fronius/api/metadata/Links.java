package tech.renovus.solarec.inverters.brand.fronius.api.metadata;
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
"first",
"prev",
"self",
"next",
"last",
"totalItemsCount"
})
@Generated("jsonschema2pojo")
public class Links {

@JsonProperty("first")
private String first;
@JsonProperty("prev")
private Object prev;
@JsonProperty("self")
private String self;
@JsonProperty("next")
private Object next;
@JsonProperty("last")
private String last;
@JsonProperty("totalItemsCount")
private Integer totalItemsCount;
@JsonIgnore
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

@JsonProperty("first")
public String getFirst() {
return first;
}

@JsonProperty("first")
public void setFirst(String first) {
this.first = first;
}

public Links withFirst(String first) {
this.first = first;
return this;
}

@JsonProperty("prev")
public Object getPrev() {
return prev;
}

@JsonProperty("prev")
public void setPrev(Object prev) {
this.prev = prev;
}

public Links withPrev(Object prev) {
this.prev = prev;
return this;
}

@JsonProperty("self")
public String getSelf() {
return self;
}

@JsonProperty("self")
public void setSelf(String self) {
this.self = self;
}

public Links withSelf(String self) {
this.self = self;
return this;
}

@JsonProperty("next")
public Object getNext() {
return next;
}

@JsonProperty("next")
public void setNext(Object next) {
this.next = next;
}

public Links withNext(Object next) {
this.next = next;
return this;
}

@JsonProperty("last")
public String getLast() {
return last;
}

@JsonProperty("last")
public void setLast(String last) {
this.last = last;
}

public Links withLast(String last) {
this.last = last;
return this;
}

@JsonProperty("totalItemsCount")
public Integer getTotalItemsCount() {
return totalItemsCount;
}

@JsonProperty("totalItemsCount")
public void setTotalItemsCount(Integer totalItemsCount) {
this.totalItemsCount = totalItemsCount;
}

public Links withTotalItemsCount(Integer totalItemsCount) {
this.totalItemsCount = totalItemsCount;
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

public Links withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}