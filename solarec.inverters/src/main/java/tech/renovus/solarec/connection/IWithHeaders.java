package tech.renovus.solarec.connection;

import java.util.List;

public interface IWithHeaders {

	List<String> getHeadersToSet();
	void setHeader(String header, String value);
	
}
