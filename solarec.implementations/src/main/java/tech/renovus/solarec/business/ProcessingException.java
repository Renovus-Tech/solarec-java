package tech.renovus.solarec.business;

public class ProcessingException extends Exception {

	//--- Private constants ---------------------
	private static final long serialVersionUID = 5115906742948401577L;

	//--- Constructors --------------------------
	public ProcessingException(String msg) { super(msg); }
	public ProcessingException(Exception e) { super(e); }
}
