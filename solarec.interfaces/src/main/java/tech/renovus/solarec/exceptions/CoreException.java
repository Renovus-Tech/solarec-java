package tech.renovus.solarec.exceptions;

import java.util.ArrayList;
import java.util.Collection;

public class CoreException extends Exception {

	//--- Private constants ---------------------
	private static final long serialVersionUID = -5950339334979625848L;
	
	//--- Private properties --------------------
	private Collection<String> params;
	
	//--- Protected properties ------------------
	protected boolean isLabel = false;

	//--- Constructors --------------------------
	public CoreException(Exception e) {
		super(e);
	}

	public CoreException(Throwable e) {
		super(e);
	}
	
	public CoreException(CoreException e) {
		super(e);
		this.isLabel = e.isLabel();
		this.params = e.getParams();
	}
	
	public CoreException(String msg) {
		super(msg);
		this.isLabel = true;
	}
	
	public CoreException(String msg, Collection<String> params) {
		super(msg);
		this.isLabel = true;
		this.params = params;
	}
	
	public CoreException(String msg, String... params) {
		super(msg);
		this.isLabel = true;
		
		this.params = new ArrayList<String>();
		
		if (params != null) {
			for (String param : params) this.params.add(param);
		}
	}
	
	public boolean isLabel() {
		if (this.isLabel) return true;
		if (this.getCause() instanceof CoreException) return ((CoreException) this.getCause()).isLabel();
		
		return false;
	}

	public Collection<String> getParams() {
		if (this.isLabel) return this.params;
		if (this.getCause() instanceof CoreException && ((CoreException) this.getCause()).isLabel()) return ((CoreException) this.getCause()).getParams();
		
		return null;
	}
	
	public String getMessage() {
		if (this.isLabel) return super.getMessage();
		if (this.getCause() instanceof CoreException && ((CoreException) this.getCause()).isLabel()) return ((CoreException) this.getCause()).getMessage();
		
		return super.getMessage();
	}

	//--- Getters and Setters -------------------
}
