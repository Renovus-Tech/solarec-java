package tech.renovus.solarec.vo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.db.BaseDbVo;
import tech.renovus.solarec.util.interfaces.ISynchronizable;

public class BasicVoTester {

	@Test public void fastTestVo()			throws Exception { this.testPackage(false, "tech.renovus.solarec.vo.db.data"); }
	@Test public void fastTestBackground()	throws Exception { this.testPackage(false, "tech.renovus.solarec.vo.rest.background"); }
	@Test public void fastTestChart()		throws Exception { this.testPackage(true, "tech.renovus.solarec.vo.rest.chart"); }
	@Test public void fastTestEntity()		throws Exception { this.testPackage(true, "tech.renovus.solarec.vo.rest.entity"); }
	@Test public void fastTestHistory()		throws Exception { this.testPackage(true, "tech.renovus.solarec.vo.rest.history"); }
	@Test public void fastTestSecurity()	throws Exception { this.testPackage(true, "tech.renovus.solarec.vo.rest.security"); }
	@Test public void fastTestWeather()		throws Exception { this.testPackage(true, "tech.renovus.solarec.vo.rest.weather"); }
	@Test public void fastTestCustom()		throws Exception { this.testPackage(true, "tech.renovus.solarec.vo.custom.chart"); }
	
	private void testPackage(boolean isRest, String packageName) throws Exception {
		try (
			ScanResult scanResult = 
				new ClassGraph()
					.acceptPackages(packageName)
					.scan();
		) {
			for (ClassInfo classInfo : scanResult.getAllClasses()) {
				Class<?> clazz = Class.forName(classInfo.getName());
				System.out.println("Testing: " + clazz.getCanonicalName());
				this.testGettersSetters(clazz);
				if (isRest) {
					this.testWithParameters(clazz);
				}
			}
		}
	}

	private void testWithParameters(Class<?> clazz) throws Exception {
		if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
			return;
		}
		
		Method[] methods = clazz.getDeclaredMethods();
		
		Method getAdditionalProperties = null;
		Method setAdditionalProperty = null;
		Method withAdditionalProperty = null;
		
		for (Method method : methods) {
			if ("getAdditionalProperties".equals(method.getName())) {
				getAdditionalProperties = method;
			} else if ("setAdditionalProperty".equals(method.getName())) {
				setAdditionalProperty = method;
			} else if ("withAdditionalProperty".equals(method.getName())) {
				withAdditionalProperty = method;
			}
		}

		Object obj					= clazz.getConstructor().newInstance();
		
		if (setAdditionalProperty != null) {
			setAdditionalProperty.invoke(obj, "prop", "value");
		}
		if (withAdditionalProperty != null) {
			Object objWith = withAdditionalProperty.invoke(obj, "prop2", "value2");
			assertNotNull(objWith);
		}
		if (getAdditionalProperties != null) {
			Object objReturn = getAdditionalProperties.invoke(obj);
			assertNotNull(objReturn);
			assertEquals(2, CollectionUtil.size((Map<?,?>) objReturn));
			assertEquals("value", ((Map<String,String>) objReturn).get("prop"));
			assertEquals("value2", ((Map<String,String>) objReturn).get("prop2"));
		}
	}
	
	public <T> T testGettersSetters(Class<T> clazz) throws Exception {
		if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
			return null;
		}
		
		Map<String, Method> withs	= new HashMap<>();
		Map<String, Method> setters = new HashMap<>();
		Map<String, Method> getters = new HashMap<>();

		// Collect all methods from the class hierarchy
		Class<?> parentClazz = clazz;
		while (parentClazz != null) {
			Method[] methods = parentClazz.getDeclaredMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				if ((methodName.startsWith("set") || methodName.startsWith("with")) && method.getParameterCount() == 1) {
					boolean isSet = methodName.startsWith("set");
					String property = methodName.substring(isSet ? 3 : 4);
					(isSet ? setters : withs).put(property, method);
				} else if ((methodName.startsWith("get") && method.getParameterCount() == 0)
						|| (methodName.startsWith("is") && method.getParameterCount() == 0)) {
					String property = methodName.startsWith("get") ? methodName.substring(3) : methodName.substring(2);
					getters.put(property, method);
				}
			}
			parentClazz = parentClazz.getSuperclass();
		}

		T obj					= clazz.getConstructor().newInstance();
		T objEmpty				= clazz.getConstructor().newInstance();
		String testingClass		= clazz.getCanonicalName();
		
		if (obj instanceof BaseDbVo) {
			BaseDbVo dbVo = (BaseDbVo) obj;
			assertFalse("Fail invalidData for: " + testingClass, dbVo.validData());
		}
		
		// Iterate over withs methods and test them
		for (Map.Entry<String, Method> entry : withs.entrySet()) {
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
		
		assertNotEquals(obj, objEmpty);
		assertNotEquals(obj.hashCode(), objEmpty.hashCode());
		if (obj instanceof BaseDbVo) {
			BaseDbVo dbVo = (BaseDbVo) obj;
			assertTrue("Fail isSame for: " + testingClass, dbVo.isSame(obj));
			assertTrue("Fail validData for: " + testingClass, dbVo.validData());
		}
		if (obj instanceof ISynchronizable) {
			ISynchronizable sync = (ISynchronizable) obj;
			if (obj instanceof BaseDbVo) {
				sync.synchronize((BaseDbVo) objEmpty);
			}
			sync.setChildrensId();
			sync.synchronizeForce(BaseDbVo.SYNC_INIT);
		}
		
		return obj;
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
