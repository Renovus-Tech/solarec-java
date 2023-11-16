package tech.renovus.solarec.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * The <code>ClassUtil</code> class contains useful methods to work with classes and retrieve information from them.
 */
public final class ClassUtil {

	//--- Public constants ---------------------
	/** Class <code>String</code> name constant. */
	private static final String TYPE_STRING				= String.class.getName();
	
	/** Class <code>Integer</code> name constant. */
	private static final String TYPE_INTEGER			= Integer.class.getName();
	
	/** Primitive <code>int</code> name constant. */
	private static final String TYPE_PRIMITIVE_INT		= "int";
	
	/** Class <code>Long</code> name constant. */
	private static final String TYPE_LONG				= Long.class.getName();
	
	/** Class <code>Float</code> name constant. */
	private static final String TYPE_FLOAT				= Float.class.getName();
	
	/** Class <code>Double</code> name constant. */
	private static final String TYPE_DOUBLE				= Double.class.getName();
	
	/** Class <code>Boolean</code> name constant. */
	private static final String TYPE_BOOLEAN			= Boolean.class.getName();
	
	/** Primitive <code>boolean</code> name constant. */
	private static final String TYPE_PRIMITIVE_BOOLEAN	= "boolean";
	
	/** Class <code>Character</code> name constant. */
	private static final String TYPE_CHARACTER			= Character.class.getName();
	
	/** Class <code>java.util.Date</code> name constant. */
	private static final String TYPE_DATE				= java.util.Date.class.getName();;
	
	public static final String TYPE_CLASS				= "class ";

	//--- Public inner classes ------------------
	public static class ClassLoadError extends java.lang.Error {
		private static final long serialVersionUID = 2049532138523293516L; 
	}
	
	//--- Constructor ---------------------------
	private ClassUtil() {
	}
	
	//--- Private methods -----------------------
	private static String classUrlToFileSystem(String aClassFile, URL url, String encoding) throws UnsupportedEncodingException {
		String location = url.toString();
		location = URLDecoder.decode(location, encoding); 
		if (location.indexOf(aClassFile) != -1) location = location.substring(0,location.indexOf(aClassFile));
		if (location.startsWith("file:/")) location = location.substring("file:/".length());
		return location;
	}
	
	//--- Public methods ------------------------
	/**
	 * Returns all the declared fields non private of a <code>Class</code>. All the <code>Field</code>
	 * that are not private neither statics are return in the <code>Collection</code>.
	 * 
	 * @param aClass	The <code>Class</code> from where retrieve the fields.
	 * @return			A <code>Collection</code> of <code>Field</code> non private and non static.
	 */
	public static Collection<Field> getNonPrivateStaticFields(Class<?> aClass) {
		Field[] possibleFields = aClass.getDeclaredFields();
		Collection<Field> result = new ArrayList<Field>();
		
		for (Field field : possibleFields) {
			field.setAccessible(true);
			int fieldModifiers = field.getModifiers();
			if (Modifier.isPrivate(fieldModifiers) || !Modifier.isStatic(fieldModifiers)) continue;
			result.add(field);
		}
		
		return result;
	}
	
	/**
	 * Determinate the package where the <code>Class</code> is located. 
	 * 
	 * @param aClass	A <code>Class</code>
	 * @return			The package name of the class.
	 */
	public static String getPackageName(Class<?> aClass) {
		return aClass.getPackage().getName();
	}
	
	/**
	 * Returns the name of the <code>Class</code> without the package name in int.
	 * 
	 * @param aClass	A <code>Class</code>
	 * @return			The class name
	 */
	public static String getClassName(Class<?> aClass) {
		int pos = aClass.getName().lastIndexOf(".");
		if (pos != -1) return aClass.getName().substring(pos + 1);
		
		return aClass.getName();
	}
	
	/**
	 * Determinate if two instances of different objects are equals. If both instances are not <code>null</code> then
	 * the the <b>obj1.equals()</b> method is call and return its value. If both instances are <code>null</code> then 
	 * <code>true</code> is return, otherwise, <code>false</code> is return.
	 * 
	 * @param obj1	The first instance object to compare
	 * @param obj2	The second instance object to compare
	 * @return		<code>true</code> if both instances are equals according to the <b>obj1</b>.<code>equals()</code> method or both are <code>null</code>, <code>false</code> otherwise
	 * 
	 * @see #equalsNotNull(Object, Object)
	 */
	public static boolean equals(Object obj1, Object obj2) {
		if (obj1 != null && obj2 != null) {
			return obj1.equals(obj2);
		
		} else if (obj1 == null && obj2 == null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Determinates if two instances of differente objects are equals, or if the hay <code>Dobuel</code>
	 * and <code>Integer</code> represents the same number.
	 * 
	 * @param obj1	The first instance object to compare
	 * @param obj2	The second instance object to compare
	 * @return		<code>true</code> if both instances are equals according to the <b>obj1</b>.<code>equals()</code> method or both are <code>null</code> or both represent the same number, <code>false</code> otherwise
	 */
	public static boolean equalsOrEqualsNumber(Object obj1, Object obj2) {
		if (ClassUtil.equals(obj1, obj2)) return true;
		
		if (obj1 instanceof Double && obj2 instanceof Integer) {
			return ((Double) obj1).intValue() == ((Integer) obj2).intValue();
		} else if (obj1 instanceof Integer && obj2 instanceof Double) {
			return ((Integer) obj1).intValue() == ((Double) obj2).intValue();
		}
		
		return false;
	}
	
	/**
	 * Determinate if two instances of different objects are equals. If both instances are not <code>null</code> then
	 * the the <b>obj1.equals()</b> method is call and return its value. If both instances are <code>null</code> then 
	 * <code>false</code> is return, otherwise, <code>false</code> is return.
	 * 
	 * @param obj1	The first instance object to compare
	 * @param obj2	The second instance object to compare
	 * @return		<code>true</code> if both instances are equals according to the <b>obj1</b>.<code>equals()</code> method, <code>false</code> otherwise
	 * 
	 * @see #equals(Object, Object)
	 */
	public static boolean equalsNotNull(Object obj1, Object obj2) {
		if (obj1 != null && obj2 != null) return obj1.equals(obj2);
		
		return false;
	}
	
	/**
	 * Returns the location of a class in the file system. In order to retrieve the real location and folders name
	 * the default file encoding (<code>System.getProperty("file.encoding")</code>) is use. If the class is located
	 * in a package, the path will not include the packages folders.
	 * 
	 * @param aClass						The <code>Class</code> to determinate the location.
	 * @return								The file system path of the class, without package and class name.
	 * @throws UnsupportedEncodingException	If an error occurs while converting the location
	 * 
	 * @see #getLocation(Class, String)
	 */
	public static String getLocation(Class<?> aClass) throws UnsupportedEncodingException {
		return ClassUtil.getLocation(aClass, System.getProperty("file.encoding"));
	}
	
	/**
	 * Returns the location of a class in the file system. In order to retrieve the real location and folders name
	 * the <b>encoding</b> is use. If the class is located in a package, the path will not include the packages folders.
	 * 
	 * @param aClass							The <code>Class</code> to determinate the location.
	 * @param encoding							The encoding to use to convert the location.
	 * @return									The file system path of the class, without package and class name.
	 * @throws UnsupportedEncodingException		If an error occurs while converting the location
	 * 
	 * @see #getLocation(Class)
	 */
	public static String getLocation(Class<?> aClass, String encoding) throws UnsupportedEncodingException {
		String aClassFile = aClass.getName().replace('.', File.separator.charAt(0));
		URL url = aClass.getProtectionDomain().getCodeSource().getLocation();
		
		String location = ClassUtil.classUrlToFileSystem(aClassFile, url, encoding);
		
		return location;
	}

	/**
	 * Returns the location of a class property file in the file system. In order to retrieve the real location and 
	 * folders name the default file encoding (<code>System.getProperty("file.encoding")</code>) is use. If the class 
	 * is located in a package, the path will not include the packages folders.
	 * 
	 * @param aClass							The <code>Class</code> to determinate the properties location.
	 * @return									The file system path of the property class, without package and class name.
	 * @throws UnsupportedEncodingException		If an error occurs while converting the location
	 * 
	 * @see #getLocationProperties(String)
	 * @see #getLocationProperties(String, String)
	 */
	public static String getLocationProperties(Class<?> aClass) throws UnsupportedEncodingException {
		return ClassUtil.getLocationProperties(aClass.getName());
	}
	
	/**
	 * Returns the location of a class property file in the file system. In order to retrieve the real location and 
	 * folders name the default file encoding (<code>System.getProperty("file.encoding")</code>) is use. If the class 
	 * is located in a package, the path will not include the packages folders.
	 * 
	 * @param aClass							The class package a name definition to determinate the properties location.
	 * @return									The file system path of the property class, without package and class name.
	 * @throws UnsupportedEncodingException		If an error occurs while converting the location
	 * 
	 * @see #getLocationProperties(Class)
	 * @see #getLocationProperties(String, String)
	 */
	public static String getLocationProperties(String aClass) throws UnsupportedEncodingException {
		return ClassUtil.getLocationProperties(aClass, System.getProperty("file.encoding"));
	}
	
	/**
	 * Returns the location of a class property file in the file system. In order to retrieve the real location and 
	 * folders name the <b>encoding</b> is use. If the class is located in a package, the path will not include the 
	 * packages folders.
	 * 
	 * @param aClass							The <code>Class</code> to determinate the location.
	 * @param encoding							The encoding to use to convert the location.
	 * @return									The file system path of the class, without package and class name.
	 * @throws UnsupportedEncodingException		If an error occurs while converting the location
	 * 
	 * @see #getLocationProperties(Class)
	 * @see #getLocationProperties(String)
	 */
	public static String getLocationProperties(String aClass, String encoding) throws UnsupportedEncodingException {
		String aClassFile = aClass.replace('.', File.separator.charAt(0));
		ClassLoader clsLoader = ClassUtil.class.getClassLoader();
		if (! aClassFile.endsWith(".properties")) aClassFile += ".properties";
		URL url = clsLoader.getResource(aClassFile);

		if (url == null) return null;
		
		String location = ClassUtil.classUrlToFileSystem(aClassFile, url, encoding);
		
		return location;
	}
	
	/**
	 * Returns the class name, method and line of the caller the method.
	 * 
	 * @return	The class name, method and line of the caller.
	 */
	public static String getParentCaller() {
		Throwable caller = new Throwable();
		StackTraceElement[] tracer = caller.getStackTrace();
		StackTraceElement parentCaller = tracer[3];
		return parentCaller.getClassName() + "." + parentCaller.getMethodName() + " (at line " + parentCaller.getLineNumber() + ")";
	}
	
	/**
	 * Converts a primitive value into its corresponding <code>Object</code> value. The possible values of <b>type</b> are:
	 * <ul>
	 * 	<li>ClassUtil.TYPE_STRING</li>
	 * 	<li>ClassUtil.TYPE_INTEGER</li>
	 * 	<li>ClassUtil.TYPE_PRIMITIVE_INT</li>
	 * 	<li>ClassUtil.TYPE_LONG</li>
	 * 	<li>ClassUtil.TYPE_FLOAT</li>
	 * 	<li>ClassUtil.TYPE_DOUBLE</li>
	 * 	<li>ClassUtil.TYPE_BOOLEAN</li>
	 * 	<li>ClassUtil.TYPE_PRIMITIVE_BOOLEAN</li>
	 * 	<li>ClassUtil.TYPE_CHARACTER</li>
	 * 	<li>ClassUtil.TYPE_DATE: in this case <b>value</b> must be the time in milliseconds.</li>
	 * </ul>
	 * <p>If <b>value</b> is <code>null</code>, then <code>null</code> is return.
	 * 
	 * @param type	The type of object to return
	 * @param value	The value to convert
	 * @return		An instance of <b>type</b> representing <b>value</b>.
	 */
	public static Object castPrimitiveToValueType(String type, String value) {
		if (type != null) {
			if (type.startsWith(ClassUtil.TYPE_CLASS)) type = type.substring(ClassUtil.TYPE_CLASS.length());
			
			if (ClassUtil.TYPE_STRING.equals(type)) {
				return value;
			
			} else if (ClassUtil.TYPE_INTEGER.equals(type) || ClassUtil.TYPE_PRIMITIVE_INT.equals(type)) {
				if (value == null || StringUtil.EMPTY_STRING.equals(value)) {
					return null;
				} else {
					return Integer.valueOf(value);
				}
			
			} else if (ClassUtil.TYPE_LONG.equals(type)) {
				if (value == null || StringUtil.EMPTY_STRING.equals(value)) {
					return null;
				} else {
					return new Long(value);
				}
			
			} else if (ClassUtil.TYPE_FLOAT.equals(type)) {
				if (value == null || StringUtil.EMPTY_STRING.equals(value)) {
					return null;
				} else {
					return new Float(value);
				}
			
			} else if (ClassUtil.TYPE_DOUBLE.equals(type)) {
				if (value == null || StringUtil.EMPTY_STRING.equals(value)) {
					return null;
				} else {
					return Double.valueOf(value);
				}
			
			} else if (ClassUtil.TYPE_BOOLEAN.equals(type) || ClassUtil.TYPE_PRIMITIVE_BOOLEAN.equals(type)) {
				return Boolean.valueOf(BooleanUtils.isTrue(value));
			
			} else if (ClassUtil.TYPE_CHARACTER.equals(type)) {
				if (value == null || StringUtil.EMPTY_STRING.equals(value)) {
					return null;
				} else {
					return Character.valueOf(value.toCharArray()[0]);
				}
			} else if (ClassUtil.TYPE_DATE.equals(type)) {
				if (value == null || StringUtil.EMPTY_STRING.equals(value)) {
					return null;
				} else {
					long time = Long.parseLong(value);
					return new java.util.Date(time);
				}
			}
		}
		
		return null;
	}

	/**
	 * Returns the a unique id of the instance object.
	 * 
	 * @param obj	The instance object.
	 * @return		An id for the instance.
	 */
	public static String getId(Object obj) {
		if (obj == null) return null;
		
		String id = obj.toString();
		if (id.indexOf(".") != -1) {
			id = id.substring(id.lastIndexOf(".") + 1);
		} else {
			id = obj.getClass().getSimpleName() + "@" + obj.hashCode();
		}

		return id;
	}

	/**
	 * Check if the instance of <code>Object</code> is really a instance of <code>Collection</code> and is not 
	 * <code>null</code>.
	 * 
	 * @param obj	The instance to check 
	 * @return		<code>true</code> if <b>obj</b> is not <code>null</code> and is a instance of <code>Collection</code>
	 */
	public static boolean isCollection(Object obj) {
		return obj != null && obj instanceof Collection;
	}

	/**
	 * Verify if the instance of <code>Object</code> is empty or not. If <b>value</b> is instance of <code>String</code> then
	 * <code>StringUtil.notEmpty(String)</code> is call.
	 * 
	 * @param value	The value to check
	 * @return		<code>true</code> if value is not empty, <code>false</code> otherwise.
	 * 
	 * @see uy.com.pf.sdk.util.StringUtil#notEmpty(String...)
	 */
	public static boolean notEmpty(Object value) {
		if (value == null) return false;
		
		if (value instanceof String) return StringUtil.notEmpty((String) value);
		
		return true;
	}
	
	/**
	 * Returns <code>value</code> only is not <code>null</code>, else will return <code>defultValue</code>
	 * 
	 * @param value to check.
	 * @param defaultValue to return if null.
	 * @return
	 */
	public static <T extends Object> T noNull(T value, T defaultValue) {
		return value == null ? defaultValue : value;
	}

	/**
	 * Check if the instance <b>toVerify</b> implements the interface <b>mustImplement</b>. If <b>toVerify</b> or <b>mustImplement</b>
	 * are <code>null</code> then <code>false</code> is return.
	 * 
	 * @param toVerify		The class to verify
	 * @param mustImplement	The interface that must implement
	 * @return				<code>true</code> if <b>toVerify</b> implements <b>mustImplement</b>
	 */
	public static boolean implementsClass(Class<?> toVerify, Class<?> mustImplement) {
		if (mustImplement == null)	return false;
		if (toVerify == null)		return false;
		
		Class<?>[] isImplementing = toVerify.getInterfaces();
		if (isImplementing != null && isImplementing.length > 0) {
			for (Class<?> isWhatIsLookingFor : isImplementing) {
				if (isWhatIsLookingFor.equals(mustImplement)) return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Check if the instance <b>toVerify</b> extends the class <b>mustExtends</b>. If <b>toVerify</b> or <b>mustExtends</b>
	 * are <code>null</code> then <code>false</code> is return.
	 * 
	 * @param toVerify		The class to verify
	 * @param mustExtends	The class that must extend
	 * @return				<code>true</code> if <b>toVerify</b> extends <b>mustExtends</b>
	 */
	public static boolean extendsClass(Class<?> toVerify, Class<?> mustExtends) {
		if (mustExtends == null)	return false;
		if (toVerify == null)		return false;
		
		Class<?> isExtending = toVerify.getSuperclass();
		if (isExtending == null) return false;
		if (isExtending.equals(mustExtends)) return true;
		
		if (! isExtending.equals(Object.class)) return ClassUtil.extendsClass(isExtending, mustExtends);
		return false;
	}
	
	/**
	 * Returns a <code>List</code> with all the <code>Class</code>es that are in the package <b>pckgname</b>.
	 * 
	 * @param pckgname					The package where to search for the classes
	 * @return							A <code>List</code> of classes in the package <b>pckgname</b>
	 * @throws ClassNotFoundException	If an error occurs while retrieving the class information
	 */
	public static List<Class<?>> getClassesForPackage(String pckgname) throws ClassNotFoundException {
		// This will hold a list of directories matching the pckgname.
		// There may be more than one if a package is split over multiple
		// jars/paths
		List<Class<?>> classes = new ArrayList<Class<?>>();
		ArrayList<File> directories = new ArrayList<File>();
		try {
			ClassLoader cld = Thread.currentThread().getContextClassLoader();
			if (cld == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}
			// Ask for all resources for the path
			Enumeration<URL> resources = cld.getResources(pckgname.replace('.', '/'));
			while (resources.hasMoreElements()) {
				URL res = resources.nextElement();
				if (res.getProtocol().equalsIgnoreCase("jar") || res.getProtocol().equalsIgnoreCase("vfszip")) {
					JarFile jar = null;
					if (res.getProtocol().equalsIgnoreCase("jar")) {
						jar = ((JarURLConnection) res.openConnection()).getJarFile();
					
					} else if (res.getProtocol().equalsIgnoreCase("vfszip")) { //Especificamente desarrollado para JBOSS
						String resource = res.toString();
						resource = resource.substring(0, resource.toLowerCase().lastIndexOf(".jar") + 4);
						resource = resource.substring(resource.indexOf(":") + 1);
						jar = new JarFile(resource);
					}
					
					for (JarEntry e : Collections.list(jar.entries())) {

						if (e.getName().startsWith(pckgname.replace('.', '/')) && e.getName().endsWith(".class") && !e.getName().contains("$")) {
							String className = e.getName().replace("/", ".").substring(0, e.getName().length() - 6);
							classes.add(Class.forName(className));
						}
					}
				} else {
					directories.add(new File(URLDecoder.decode(res.getPath(), "UTF-8")));
				}
			}
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(pckgname + " does not appear to be " + "a valid package (Null pointer exception)");
		} catch (UnsupportedEncodingException encex) {
			throw new ClassNotFoundException(pckgname + " does not appear to be " + "a valid package (Unsupported encoding)");
		} catch (IOException ioex) {
			throw new ClassNotFoundException("IOException was thrown when trying " + "to get all resources for " + pckgname);
		}

		// For every directory identified capture all the .class files
		for (File directory : directories) {
			if (directory.exists()) {
				// Get the list of the files contained in the package
				String[] files = directory.list();
				if (files != null) {
					for (String file : files) {
						// we are only interested in .class files
						if (file.endsWith(".class")) {
							// removes the .class extension
							String className = pckgname + '.' + file.substring(0, file.length() - 6);
							try {
								classes.add(Class.forName(className));
							} catch (java.lang.Error e) {
								System.out.println("Unable to load class " + file + ". Error: " + e.getMessage() + " (" + e.getLocalizedMessage() + ")");
								System.out.println("Complete stacktrace:");
								System.out.println(StringUtil.toString(e));
								
								classes.add(ClassUtil.ClassLoadError.class);
							}
						}
					}
				}
			} else {
				throw new ClassNotFoundException(pckgname + " (" + directory.getPath() + ") does not appear to be a valid package");
			}
		}
		return classes;
	}

	/**
	 * Returns the absolute path of the class.
	 * 
	 * @param aClass
	 * @return
	 */
	public static String getFileClassLocation(Class<?> aClass) {
		URL url = aClass.getProtectionDomain().getCodeSource().getLocation();
		String path = new java.io.File(url.getPath()).getAbsolutePath();

		int index = path.indexOf("file:");
		if (index > -1) path = path.substring(index + 5);

		index = path.indexOf("jar:");
		if (index > -1) path = path.substring(index + 4);
		
		return path;
	}
	
	/**
	 * Returns the URL of the location of the class.
	 * 
	 * @param aClass
	 * @return
	 */
	public static String getClassLocation(Class<?> aClass) {
		URL url = aClass.getProtectionDomain().getCodeSource().getLocation();
		return url.getPath();
	}

	/**
	 * Returns the URL (decoded) of the location of the class.
	 * 
	 * @param aClass
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getClassLocation(Class<?> aClass, String encoding) throws UnsupportedEncodingException {
		URL url = aClass.getProtectionDomain().getCodeSource().getLocation();
		String result = URLDecoder.decode(url.getPath(),encoding);
		if (result.startsWith("/")) result = result.substring(1);
		return result;
	}

	/**
	 * Check if a specific <code>Class</code> (<b>aClass</b>) is contained in an <code>Array</code> of <code>Class</code>.
	 * 
	 * @param classes	The <code>Array</code> where to check.
	 * @param aClass	The <code>Class</code> to look for.
	 * @return			<code>true</code> if <b>aClass</b> is in <b>classes</b>, <code>false</code> otherwise.
	 */
	public static boolean contains(Class<?>[] classes, Class<?> aClass) {
		if (classes == null) return false;
		for (Class<?> theClass : classes) if (theClass.equals(aClass)) return true;
		return false;
	}

	/**
	 * Returns a new instances of the class defined in the package/class <b>classLocation</b> and
	 * checks that the class is an instances of the <code>Class&lt;T&gt;</code>.
	 * 
	 * @param classLocation	The class location
	 * @param aClass		The class to check the new instance with
	 * @return				A new instance of <b>classLocation</b> implementing/extending <code>Class&lt;T&gt;</code>, <code>null</code> otherwise.
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Object> T getInstance(String classLocation, Class<T> aClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> theClass = Class.forName(classLocation);

		if (! ClassUtil.implementsClass(theClass, aClass) && ! ClassUtil.extendsClass(theClass, aClass) ) return null;
		
		Object instance = theClass.newInstance();
		return (T) instance;
	}

	public static <T extends Object> int compare(Comparable<T> o1, T o2)  {
		if (o1 == null && o2 == null) return 0;
		if (o1 == null) return -1;
		if (o2 == null) return 1;
		
		return o1.compareTo(o2);
	}
	
	public static <T extends Object> T getNoNull(T o1, T o2) {
		return o1 != null ? o1 : o2;
	}

	//--- Reflection methods --------------------
	public static Method getMethod(Class<?> aClass, String method) {
		Method[] methods = aClass.getMethods();
		
		for (Method aMethod : methods) {
			if (aMethod.getName().equals(method)) {
				aMethod.setAccessible(true);
				return aMethod;
			}
		}
		
		return null;
	}
}
