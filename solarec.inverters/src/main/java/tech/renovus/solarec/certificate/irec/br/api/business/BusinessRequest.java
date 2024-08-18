package tech.renovus.solarec.certificate.irec.br.api.business;

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
@JsonPropertyOrder({ "isStandard", "registrant_irec", "registrant_irec_name", "social_name", "ceg", "aneel", "cnpj",
		"irec", "is_rec_brazil", "is_carbon_credit", "is_prepaid", "irec_registerd_at", "rec_generation_at",
		"comercial_operation_at", "payment_responsible", "registrant_id", "source_id", "technology_id", "address" })
@Generated("jsonschema2pojo")
public class BusinessRequest {

	@JsonProperty("isStandard")
	private Integer isStandard;
	@JsonProperty("registrant_irec")
	private String registrantIrec;
	@JsonProperty("registrant_irec_name")
	private String registrantIrecName;
	@JsonProperty("social_name")
	private String socialName;
	@JsonProperty("ceg")
	private String ceg;
	@JsonProperty("aneel")
	private String aneel;
	@JsonProperty("cnpj")
	private String cnpj;
	@JsonProperty("irec")
	private String irec;
	@JsonProperty("is_rec_brazil")
	private Integer isRecBrazil;
	@JsonProperty("is_carbon_credit")
	private Integer isCarbonCredit;
	@JsonProperty("is_prepaid")
	private Integer isPrepaid;
	@JsonProperty("irec_registerd_at")
	private String irecRegisterdAt;
	@JsonProperty("rec_generation_at")
	private String recGenerationAt;
	@JsonProperty("comercial_operation_at")
	private String comercialOperationAt;
	@JsonProperty("payment_responsible")
	private String paymentResponsible;
	@JsonProperty("registrant_id")
	private String registrantId;
	@JsonProperty("source_id")
	private String sourceId;
	@JsonProperty("technology_id")
	private String technologyId;
	@JsonProperty("address")
	private Object address;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("isStandard")
	public Integer getIsStandard() {
		return isStandard;
	}

	@JsonProperty("isStandard")
	public void setIsStandard(Integer isStandard) {
		this.isStandard = isStandard;
	}

	public BusinessRequest withIsStandard(Integer isStandard) {
		this.isStandard = isStandard;
		return this;
	}

	@JsonProperty("registrant_irec")
	public String getRegistrantIrec() {
		return registrantIrec;
	}

	@JsonProperty("registrant_irec")
	public void setRegistrantIrec(String registrantIrec) {
		this.registrantIrec = registrantIrec;
	}

	public BusinessRequest withRegistrantIrec(String registrantIrec) {
		this.registrantIrec = registrantIrec;
		return this;
	}

	@JsonProperty("registrant_irec_name")
	public String getRegistrantIrecName() {
		return registrantIrecName;
	}

	@JsonProperty("registrant_irec_name")
	public void setRegistrantIrecName(String registrantIrecName) {
		this.registrantIrecName = registrantIrecName;
	}

	public BusinessRequest withRegistrantIrecName(String registrantIrecName) {
		this.registrantIrecName = registrantIrecName;
		return this;
	}

	@JsonProperty("social_name")
	public String getSocialName() {
		return socialName;
	}

	@JsonProperty("social_name")
	public void setSocialName(String socialName) {
		this.socialName = socialName;
	}

	public BusinessRequest withSocialName(String socialName) {
		this.socialName = socialName;
		return this;
	}

	@JsonProperty("ceg")
	public String getCeg() {
		return ceg;
	}

	@JsonProperty("ceg")
	public void setCeg(String ceg) {
		this.ceg = ceg;
	}

	public BusinessRequest withCeg(String ceg) {
		this.ceg = ceg;
		return this;
	}

	@JsonProperty("aneel")
	public String getAneel() {
		return aneel;
	}

	@JsonProperty("aneel")
	public void setAneel(String aneel) {
		this.aneel = aneel;
	}

	public BusinessRequest withAneel(String aneel) {
		this.aneel = aneel;
		return this;
	}

	@JsonProperty("cnpj")
	public String getCnpj() {
		return cnpj;
	}

	@JsonProperty("cnpj")
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public BusinessRequest withCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

	@JsonProperty("irec")
	public String getIrec() {
		return irec;
	}

	@JsonProperty("irec")
	public void setIrec(String irec) {
		this.irec = irec;
	}

	public BusinessRequest withIrec(String irec) {
		this.irec = irec;
		return this;
	}

	@JsonProperty("is_rec_brazil")
	public Integer getIsRecBrazil() {
		return isRecBrazil;
	}

	@JsonProperty("is_rec_brazil")
	public void setIsRecBrazil(Integer isRecBrazil) {
		this.isRecBrazil = isRecBrazil;
	}

	public BusinessRequest withIsRecBrazil(Integer isRecBrazil) {
		this.isRecBrazil = isRecBrazil;
		return this;
	}

	@JsonProperty("is_carbon_credit")
	public Integer getIsCarbonCredit() {
		return isCarbonCredit;
	}

	@JsonProperty("is_carbon_credit")
	public void setIsCarbonCredit(Integer isCarbonCredit) {
		this.isCarbonCredit = isCarbonCredit;
	}

	public BusinessRequest withIsCarbonCredit(Integer isCarbonCredit) {
		this.isCarbonCredit = isCarbonCredit;
		return this;
	}

	@JsonProperty("is_prepaid")
	public Integer getIsPrepaid() {
		return isPrepaid;
	}

	@JsonProperty("is_prepaid")
	public void setIsPrepaid(Integer isPrepaid) {
		this.isPrepaid = isPrepaid;
	}

	public BusinessRequest withIsPrepaid(Integer isPrepaid) {
		this.isPrepaid = isPrepaid;
		return this;
	}

	@JsonProperty("irec_registerd_at")
	public String getIrecRegisterdAt() {
		return irecRegisterdAt;
	}

	@JsonProperty("irec_registerd_at")
	public void setIrecRegisterdAt(String irecRegisterdAt) {
		this.irecRegisterdAt = irecRegisterdAt;
	}

	public BusinessRequest withIrecRegisterdAt(String irecRegisterdAt) {
		this.irecRegisterdAt = irecRegisterdAt;
		return this;
	}

	@JsonProperty("rec_generation_at")
	public String getRecGenerationAt() {
		return recGenerationAt;
	}

	@JsonProperty("rec_generation_at")
	public void setRecGenerationAt(String recGenerationAt) {
		this.recGenerationAt = recGenerationAt;
	}

	public BusinessRequest withRecGenerationAt(String recGenerationAt) {
		this.recGenerationAt = recGenerationAt;
		return this;
	}

	@JsonProperty("comercial_operation_at")
	public String getComercialOperationAt() {
		return comercialOperationAt;
	}

	@JsonProperty("comercial_operation_at")
	public void setComercialOperationAt(String comercialOperationAt) {
		this.comercialOperationAt = comercialOperationAt;
	}

	public BusinessRequest withComercialOperationAt(String comercialOperationAt) {
		this.comercialOperationAt = comercialOperationAt;
		return this;
	}

	@JsonProperty("payment_responsible")
	public String getPaymentResponsible() {
		return paymentResponsible;
	}

	@JsonProperty("payment_responsible")
	public void setPaymentResponsible(String paymentResponsible) {
		this.paymentResponsible = paymentResponsible;
	}

	public BusinessRequest withPaymentResponsible(String paymentResponsible) {
		this.paymentResponsible = paymentResponsible;
		return this;
	}

	@JsonProperty("registrant_id")
	public String getRegistrantId() {
		return registrantId;
	}

	@JsonProperty("registrant_id")
	public void setRegistrantId(String registrantId) {
		this.registrantId = registrantId;
	}

	public BusinessRequest withRegistrantId(String registrantId) {
		this.registrantId = registrantId;
		return this;
	}

	@JsonProperty("source_id")
	public String getSourceId() {
		return sourceId;
	}

	@JsonProperty("source_id")
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public BusinessRequest withSourceId(String sourceId) {
		this.sourceId = sourceId;
		return this;
	}

	@JsonProperty("technology_id")
	public String getTechnologyId() {
		return technologyId;
	}

	@JsonProperty("technology_id")
	public void setTechnologyId(String technologyId) {
		this.technologyId = technologyId;
	}

	public BusinessRequest withTechnologyId(String technologyId) {
		this.technologyId = technologyId;
		return this;
	}

	@JsonProperty("address")
	public Object getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Object address) {
		this.address = address;
	}

	public BusinessRequest withAddress(Object address) {
		this.address = address;
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

	public BusinessRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}