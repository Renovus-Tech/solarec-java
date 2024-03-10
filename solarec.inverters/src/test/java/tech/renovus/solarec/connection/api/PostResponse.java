
package tech.renovus.solarec.connection.api;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"form"
})
@Generated("jsonschema2pojo")
public class PostResponse extends GetResponse {

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
}
