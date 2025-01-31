package tech.renovus.solarec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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

public class BasicPojoTest {

	@Test public void testDrecs()			throws Exception { this.testPackage(false, "tech.renovus.solarec.certificate.drecs.api"); }
	@Test public void testIrecBr()			throws Exception { this.testPackage(false, "tech.renovus.solarec.certificate.irec.br.api"); }
	@Test public void testSurentis()		throws Exception { this.testPackage(true, "tech.renovus.solarec.certificate.surentis.api"); }
	
	@Test public void testElectricMaps()	throws Exception { this.testPackage(true, "tech.renovus.solarec.grid.electricMaps.api"); }
	
	@Test public void testAiswei()			throws Exception { this.testPackage(true, "tech.renovus.solarec.inverters.brand.aiswei.api"); }
	@Test public void testEnphase()			throws Exception { this.testPackage(true, "tech.renovus.solarec.inverters.brand.enphase.api"); }
	@Test public void testFimer()			throws Exception { this.testPackage(true, "tech.renovus.solarec.inverters.brand.fimer.api"); }
	@Test public void testFronius()			throws Exception { this.testPackage(true, "tech.renovus.solarec.inverters.brand.fronius.api"); }
	@Test public void testGrowatt()			throws Exception { this.testPackage(true, "tech.renovus.solarec.inverters.brand.growatt.api"); }
	@Test public void testHuawei()			throws Exception { this.testPackage(true, "tech.renovus.solarec.inverters.brand.huawei.api"); }
	@Test public void testSma()				throws Exception { this.testPackage(true, "tech.renovus.solarec.inverters.brand.sma.api"); }
	@Test public void testSofar()			throws Exception { this.testPackage(true, "tech.renovus.solarec.inverters.brand.sofar.api"); }
	@Test public void testSolarEdge()		throws Exception { this.testPackage(true, "tech.renovus.solarec.inverters.brand.solarEdge.api"); }
	@Test public void testSolis()			throws Exception { this.testPackage(true, "tech.renovus.solarec.inverters.brand.solis.api"); }
	
	@Test public void testMeteoblue()		throws Exception { this.testPackage(true, "tech.renovus.solarec.weather.meteoblue.api"); }
	@Test public void testOpenmeteo()		throws Exception { this.testPackage(true, "tech.renovus.solarec.weather.openmeteo.api"); }
	
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
	
	private void testGettersSetters(Class<?> clazz) throws Exception {
		if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
			return;
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

		Object obj					= clazz.getConstructor().newInstance();
		Object objEmpty				= clazz.getConstructor().newInstance();
		String testingClass			= clazz.getCanonicalName();
		
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
