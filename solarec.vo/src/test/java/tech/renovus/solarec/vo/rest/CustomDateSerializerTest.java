package tech.renovus.solarec.vo.rest;

import java.util.Date;

import org.junit.Test;

public class CustomDateSerializerTest {

	@Test public void test() {
		CustomDateSerializer obj1 = new CustomDateSerializer();
		CustomDateSerializer obj2 = new CustomDateSerializer(Date.class);

//		JsonGenerator generator = new JsonGenerator();
//		obj1.serialize(new Date(), generator, null);
	}
}
