package tech.renovus.solarec.vo.rest.security;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordReset {

	//--- Public constants ----------------------
	public static final Integer ERROR_INVALID_REQUEST	= Integer.valueOf(-1);
	public static final Integer ERROR_INVALID_ID		= Integer.valueOf(-2);
	public static final Integer ERROR_INVALID_PASSWORD	= Integer.valueOf(-3);
	
	//--- Private properties --------------------
	private Boolean received;
	private Boolean changed;
	private Integer errorCode;
	private String error;
	
	private String id;
	private String newPassword;
	private String newPasswordConfirm;
	
	//--- Constructors --------------------------
	public PasswordReset() { }
	
	public PasswordReset(Boolean received) {
		this.received = received;
	}
	
	public PasswordReset(Boolean received, Boolean changed) {
		this.received = received;
		this.changed = changed;
	}
	
	public PasswordReset(Integer errorCode, String error) {
		this.changed = Boolean.FALSE;
		this.errorCode = errorCode;
		this.error = error;
	}

	//--- Getters and Setters -------------------
	public Boolean getReceived() {
		return received;
	}

	public void setReceived(Boolean received) {
		this.received = received;
	}

	public Boolean getChanged() {
		return changed;
	}

	public void setChanged(Boolean changed) {
		this.changed = changed;
	}

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}
	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
}
