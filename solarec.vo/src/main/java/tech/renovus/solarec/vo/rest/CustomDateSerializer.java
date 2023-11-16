package tech.renovus.solarec.vo.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<Date> {

	//--- Private constants ---------------------
	private static final long serialVersionUID = -7747973233610442961L;
	
	//--- Private properties --------------------
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public CustomDateSerializer() {
		this(null);
	}

	public CustomDateSerializer(Class<Date> t) {
		super(t);
	}

	//--- Overridden methods --------------------
	@Override public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {
		gen.writeString(formatter.format(value));
	}
}
