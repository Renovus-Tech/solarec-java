package tech.renovus.solarec.certificate.irec.br.api.registrants;

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
@JsonPropertyOrder({ "social_name", "email", "password", "password_confirmation", "irec", "cnpj", "phone", "expiration_day", "price_irec",
		"price_rec_brazil" })
@Generated("jsonschema2pojo")
public class RegistrantRequest {

	@JsonProperty("social_name")
	private String socialName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("password")
	private String password;
	@JsonProperty("password_confirmation")
	private String passwordConfirmation;
	@JsonProperty("irec")
	private String irec;
	@JsonProperty("cnpj")
	private String cnpj;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("expiration_day")
	private String expirationDay;
	@JsonProperty("price_irec")
	private String priceIrec;
	@JsonProperty("price_rec_brazil")
	private String priceRecBrazil;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("social_name")
	public String getSocialName() {
		return socialName;
	}

	@JsonProperty("social_name")
	public void setSocialName(String socialName) {
		this.socialName = socialName;
	}

	public RegistrantRequest withSocialName(String socialName) {
		this.socialName = socialName;
		return this;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	public RegistrantRequest withEmail(String email) {
		this.email = email;
		return this;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	public RegistrantRequest withPassword(String password) {
		this.password = password;
		return this;
	}
	
	@JsonProperty("password_confirmation")
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	@JsonProperty("password_confirmation")
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public RegistrantRequest withPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	public RegistrantRequest withIrec(String irec) {
		this.irec = irec;
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

	public RegistrantRequest withCnpj(String cnpj) {
		this.cnpj = cnpj;
		return this;
	}

	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}

	@JsonProperty("phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RegistrantRequest withPhone(String phone) {
		this.phone = phone;
		return this;
	}

	@JsonProperty("expiration_day")
	public String getExpirationDay() {
		return expirationDay;
	}

	@JsonProperty("expiration_day")
	public void setExpirationDay(String expirationDay) {
		this.expirationDay = expirationDay;
	}

	public RegistrantRequest withExpirationDay(String expirationDay) {
		this.expirationDay = expirationDay;
		return this;
	}

	@JsonProperty("price_irec")
	public String getPriceIrec() {
		return priceIrec;
	}

	@JsonProperty("price_irec")
	public void setPriceIrec(String priceIrec) {
		this.priceIrec = priceIrec;
	}

	public RegistrantRequest withPriceIrec(String priceIrec) {
		this.priceIrec = priceIrec;
		return this;
	}

	@JsonProperty("price_rec_brazil")
	public String getPriceRecBrazil() {
		return priceRecBrazil;
	}

	@JsonProperty("price_rec_brazil")
	public void setPriceRecBrazil(String priceRecBrazil) {
		this.priceRecBrazil = priceRecBrazil;
	}

	public RegistrantRequest withPriceRecBrazil(String priceRecBrazil) {
		this.priceRecBrazil = priceRecBrazil;
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

	public RegistrantRequest withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

}