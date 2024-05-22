package tech.renovus.solarec.vo.db.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import tech.renovus.solarec.util.db.BaseDbVo;

public class BasicVoTester {

	@Test
	public void fastTest() throws Exception {
		try (
			ScanResult scanResult = 
				new ClassGraph()
					.acceptPackages("tech.renovus.solarec.vo.db.data")
					.scan();
		) {
			for (ClassInfo classInfo : scanResult.getAllClasses()) {
				Class<?> clazz = Class.forName(classInfo.getName());
				System.out.println("Testing: " + clazz.getCanonicalName());
				testGettersSetters(clazz);
			}
		}
	}

	private void testGettersSetters(Class<?> clazz) throws Exception {
		Object obj = clazz.getConstructor().newInstance();
		String testingClass = clazz.getCanonicalName();
		Map<String, Method> setters = new HashMap<>();
		Map<String, Method> getters = new HashMap<>();

		// Collect all methods from the class hierarchy
		while (clazz != null) {
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				if (methodName.startsWith("set") && method.getParameterCount() == 1) {
					String property = methodName.substring(3);
					setters.put(property, method);
				} else if ((methodName.startsWith("get") && method.getParameterCount() == 0)
						|| (methodName.startsWith("is") && method.getParameterCount() == 0)) {
					String property = methodName.startsWith("get") ? methodName.substring(3) : methodName.substring(2);
					getters.put(property, method);
				}
			}
			clazz = clazz.getSuperclass();
		}

		// Iterate over setter methods and test them
		for (Map.Entry<String, Method> entry : setters.entrySet()) {
			String property = entry.getKey();
			Method setter = entry.getValue();
			Method getter = getters.get(property);

			if (getter != null) {
				Class<?> paramType = setter.getParameterTypes()[0];
				Object testValue = getTestValue(paramType);

				setter.invoke(obj, testValue);
				Object returnValue = getter.invoke(obj);

				assertEquals(testValue, returnValue);
			}
		}
		
		assertEquals(obj, obj);
		assertEquals(obj.hashCode(), obj.hashCode());
		if (obj instanceof BaseDbVo) {
			assertTrue("Fail isSame for: " + testingClass,((BaseDbVo) obj).isSame(obj));
			assertTrue("Fail validData for: " + testingClass, ((BaseDbVo) obj).validData());
		}
		
	}

	private Object getTestValue(Class<?> paramType) {
		if (paramType == String.class) {
			return "testValue";
		} else if (paramType == int.class || paramType == Integer.class) {
			return 123;
		} else if (paramType == boolean.class || paramType == Boolean.class) {
			return true;
		} else if (paramType == double.class || paramType == Double.class) {
			return 123.45;
		} else if (paramType == Date.class) {
			return new Date();
		}
		// Add more types as needed
		return null;
	}
}
