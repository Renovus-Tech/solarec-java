package tech.renovus.solarec.business;

public class ReportException extends Exception {

	//--- Private constants ---------------------
	private static final long serialVersionUID = 3058623273909913419L;
	
	//--- Constructors --------------------------
	public ReportException(String msg) { super(msg); }
	public ReportException(Exception exp) { super(exp); }
}
