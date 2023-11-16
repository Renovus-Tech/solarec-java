package tech.renovus.solarec;

import java.util.Collection;
import java.util.Locale;
import java.util.TreeSet;
import java.util.stream.Collectors;

import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.FunctionalityVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.db.data.UsersVo;
import tech.renvous.solarec.util.CollectionUtil;

public class UserData {

	//--- Private properties --------------------
	private UsersVo userVo;
	private ClientVo clientVo;
	private LocationVo locationVo;
	private Collection<FunctionalityVo> functionalities;
	private Collection<FunctionalityVo> locationFunctionalities;

	private Locale locale = Locale.ENGLISH;
	private long sessionId;
	private boolean logged;
	
	private int authenticationError;

	//--- Constructors --------------------------
	public UserData() {
		this.sessionId = System.currentTimeMillis();
	}
	
	//--- Private properties --------------------
	private void calculateLocationFunctionalities() {
		if (this.locationVo == null) {
			this.locationFunctionalities = null;
		} else {
			this.locationFunctionalities = new TreeSet<>();
			if (CollectionUtil.notEmpty(this.functionalities)) this.locationFunctionalities = this.functionalities.stream().filter(x -> x.isValidFor(this.locationVo.getLocType())).collect(Collectors.toList());
		}
	}
	
	//--- Public methods ------------------------
	public Integer getUsrId() { return this.userVo == null ? null : this.userVo.getUsrId(); }
	public Integer getCliId() { return this.clientVo == null ? null : this.clientVo.getCliId(); }
	public Integer getLocId() { return this.locationVo == null ? null : this.locationVo.getLocId(); }
	
	public String getUsrEmail() { return this.userVo == null ? null : this.userVo.getUsrEmail(); }
	public String getCliName() { return this.clientVo == null ? null : this.clientVo.getCliName(); }
	public String getLocName() { return this.locationVo == null ? null : this.locationVo.getLocName(); }
	
	//--- Getters and Setters -------------------
	public UsersVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UsersVo userVo) {
		this.userVo = userVo;
	}
	public ClientVo getClientVo() {
		return clientVo;
	}
	public void setClientVo(ClientVo clientVo) {
		this.clientVo = clientVo;
	}
	public Collection<FunctionalityVo> getFunctionalities() {
		return functionalities;
	}
	public void setFunctionalities(Collection<FunctionalityVo> functionalities) {
		this.functionalities = functionalities;
	}
	public boolean isLogged() {
		return logged;
	}
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public int getAuthenticationError() {
		return authenticationError;
	}

	public void setAuthenticationError(int authenticationError) {
		this.authenticationError = authenticationError;
	}
	public LocationVo getLocationVo() {
		return locationVo;
	}
	public void setLocationVo(LocationVo locationVo) {
		this.locationVo = locationVo;
		this.calculateLocationFunctionalities();
	}
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public Collection<FunctionalityVo> getLocationFunctionalities() {
		return locationFunctionalities;
	}
	public void setLocationFunctionalities(Collection<FunctionalityVo> locationFunctionalities) {
		this.locationFunctionalities = locationFunctionalities;
	}
}
