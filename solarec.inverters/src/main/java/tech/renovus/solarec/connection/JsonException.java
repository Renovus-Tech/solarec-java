package tech.renovus.solarec.connection;

public class JsonException extends Exception {

	//--- Private constants ---------------------
	private static final long serialVersionUID = 3038076087265628343L;
	
	//--- Private properties --------------------
	private Object error;
	
	//---- Constructors -------------------------
	public JsonException(Object error) {
		this.error = error;;
	}

	//--- Getters and Setters -------------------
	public Object getError() {
		return error;
	}
}
