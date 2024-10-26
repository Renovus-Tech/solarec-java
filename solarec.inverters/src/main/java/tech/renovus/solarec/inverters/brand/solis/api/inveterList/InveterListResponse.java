
package tech.renovus.solarec.inverters.brand.solis.api.inveterList;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;

import tech.renovus.solarec.inverters.brand.solis.api.DataResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("jsonschema2pojo")
public class InveterListResponse extends DataResponse<InverterList> {

	@Override
	public DataResponse<InverterList> getSelf() {
		return this;
	}

}
