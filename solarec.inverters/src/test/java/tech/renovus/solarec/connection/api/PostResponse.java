
package tech.renovus.solarec.connection.api;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import tech.renovus.solarec.connection.IWithHeaders;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"form"
})
@Generated("jsonschema2pojo")
public class PostResponse extends GetResponse implements IWithHeaders {

	@JsonProperty("form")
    private OnlyProperties form;
	
    @JsonProperty("form")
    public OnlyProperties getForm() {
        return form;
    }

    @JsonProperty("form")
    public void setForm(OnlyProperties form) {
        this.form = form;
    }

    
    //--- Implemented methods -------------------
    private String headerContentType;
    
	@Override
	public List<String> getHeadersToSet() {
		return Arrays.asList("Content-Type");
	}

	@Override
	public void setHeader(String header, String value) {
		if ("Content-Type".equals(header)) {
			this.headerContentType = value;
		}
	}

	public String getHeaderContentType() {
		return headerContentType;
	}
}
