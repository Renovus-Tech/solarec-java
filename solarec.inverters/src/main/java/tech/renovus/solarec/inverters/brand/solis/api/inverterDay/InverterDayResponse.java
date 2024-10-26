package tech.renovus.solarec.inverters.brand.solis.api.inverterDay;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;

import tech.renovus.solarec.inverters.brand.solis.api.DataResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("jsonschema2pojo")
public class InverterDayResponse extends DataResponse<InverterDay> {

	@Override
	public DataResponse<InverterDay> getSelf() {
		return this;
	}

}