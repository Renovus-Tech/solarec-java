package tech.renovus.solarec.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StringUtil {

	//--- Public constants ----------------------
	private static final String START_TOKEN			= "<TOK";
	private static final String END_TOKEN			= ">";
	public static final String NUMBERS				= "0123456789";
	public static final String EMPTY_STRING			= "";
	public static final String SPACE_STRING			= " ";
	public static final String NEW_LINE				= "\r\n";
	public static final String TRUE					= BooleanUtils.TRUE_STRING;
	public static final String THREE_DOTS			= "...";
	public static final String BAR_SPARATOR			= " - ";
	public static final String UNDERBAR_SEPARATOR	= "_";
	public static final String COMA_SEPARATOR		= ",";
	public static final String BACK_SLASH			= "/";
	public static final String AMPERSANG			= "&";
	public static final String NA					= "n/a";

	public static final String KNOW_STRINGS = " ãõáéíóúäëïöüàèìòùâêîôûçabcdefghijklmnñopqrstuvwxyz1234567890,.;:-_`+^*¡'¿?ºª!\"·$%&/()=\\|@#~¬<>[]{}";

	//--- Private constants ---------------------
	private static String[][] specialXml = {
		{"&","&amp;"},
		{"<","&lt;"},
		{">","&gt;"},
		{"\"","&quot;"},
		{"&amp;#","&#"},
		{"'", "&#39;"},
	};
	
	private static String[][] specialHtmlCode = {
		{"&", "&amp;"},
		{"\"", "&quot;"},
		{"Á", "&Aacute;"},
		{"À", "&Agrave;"},
		{"É", "&Eacute;"},
		{"È", "&Egrave;"},
		{"Í", "&Iacute;"},
		{"Ì", "&Igrave;"},
		{"Ó", "&Oacute;"},
		{"Ò", "&Ograve;"},
		{"Ú", "&Uacute;"},
		{"Ù", "&Ugrave;"},
		{"á", "&aacute;"},
		{"à", "&agrave;"},
		{"é", "&eacute;"},
		{"è", "&egrave;"},
		{"í", "&iacute;"},
		{"ì", "&igrave;"},
		{"ó", "&oacute;"},
		{"ò", "&ograve;"},
		{"ú", "&uacute;"},
		{"ù", "&ugrave;"},
		{"Ä", "&Auml;"},
		{"Â", "&Acirc;"},
		{"Ë", "&Euml;"},
		{"Ê", "&Ecirc;"},
		{"Ï", "&Iuml;"},
		{"Î", "&Icirc;"},
		{"Ö", "&Ouml;"},
		{"Ô", "&Ocirc;"},
		{"Ü", "&Uuml;"},
		{"Û", "&Ucirc;"},
		{"ä", "&auml;"},
		{"â", "&acirc;"},
		{"ë", "&euml;"},
		{"ê", "&ecirc;"},
		{"ï", "&iuml;"},
		{"î", "&icirc;"},
		{"ö", "&ouml;"},
		{"ô", "&ocirc;"},
		{"ü", "&uuml;"},
		{"û", "&ucirc;"},
		{"Ã", "&Atilde;"},
		{"å", "&aring;"},
		{"Ñ", "&Ntilde;"},
		{"Å", "&Aring;"},
		{"Õ", "&Otilde;"},
		{"Ç", "&Ccedil;"},
		{"ã", "&atilde;"},
		{"ç", "&ccedil;"},
		{"ñ", "&ntilde;"},
		{"Ý", "&Yacute;"},
		{"õ", "&otilde;"},
		{"ý", "&yacute;"},
		{"Ø", "&Oslash;"},
		{"ÿ", "&yuml;"},
		{"ø", "&oslash;"},
		{"Þ", "&THORN;"},
		{"Ð", "&ETH;"},
		{"þ", "&thorn;"},
		{"ð", "&eth;"},
		{"Æ", "&AElig;"},
		{"ß", "&szlig;"},
		{"æ", "&aelig;"},
		{"¼", "&frac14;"},
		{" ", "&nbsp;"},
		{"½", "&frac12;"},
		{"¡", "&iexcl;"},
		{"¾", "&frac34;"},
		{"£", "&pound;"},
		{"©", "&copy;"},
		{"¥", "&yen;"},
		{"®", "&reg;"},
		{"§", "&sect;"},
		{"ª", "&ordf;"},
		{"¤", "&curren;"},
		{"²", "&sup2;"},
		{"¦", "&brvbar;"},
		{"³", "&sup3;"},
		{"«", "&laquo;"},
		{"¹", "&sup1;"},
		{"¬", "&not;"},
		{"¯", "&macr;"},
		{"µ", "&micro;"},
		{"º", "&ordm;"},
		{"¶", "&para;"},
		{"´", "&acute;"},
		{"·", "&middot;"},
		{"¨", "&uml;"},
		{"°", "&deg;"},
		{"±", "&plusmn;"},
		{"¸", "&cedil;"},
		{"»", "&raquo;"},
		{"×", "&times;"},
		{"¢", "&cent;"},
		{"÷", "&divide;"},
		{"", "&euro;"},
		{"", "&#147;"},
		{"", "&#153;"},
		{"", "&#148;"},
		{"", "&#137;"},
		{"", "&#140;"},
		{"", "&#131;"},
		{"", "&#135;"},
		{"", "&#134;"}
	};
	
	private static String[][] specialHtml = {
		{"¡",	"&#161;"},
		{"¢",	"&#162;"},
		{"£",	"&#163;"},
		{"¤",	"&#164;"},
		{"¥",	"&#165;"},
		{"¦",	"&#166;"},
		{"§",	"&#167;"},
		{"¨",	"&#168;"},
		{"©",	"&#169;"},
		{"ª",	"&#170;"},
		{"«",	"&#171;"},
		{"¬",	"&#172;"},
		{"­",	"&#173;"},
		{"®",	"&#174;"},
		{"¯",	"&#175;"},
		{"°",	"&#176;"},
		{"±",	"&#177;"},
		{"²",	"&#178;"},
		{"³",	"&#179;"},
		{"´",	"&#180;"},
		{"µ",	"&#181;"},
		{"¶",	"&#182;"},
		{"·",	"&#183;"},
		{"¸",	"&#184;"},
		{"¹",	"&#185;"},
		{"º",	"&#186;"},
		{"»",	"&#187;"},
		{"¼",	"&#188;"},
		{"½",	"&#189;"},
		{"¾",	"&#190;"},
//			{"¿",	"&#191;"},
		{"À",	"&#192;"},
		{"Á",	"&#193;"},
		{"Â",	"&#194;"},
		{"Ã",	"&#195;"},
		{"Ä",	"&#196;"},
		{"Å",	"&#197;"},
		{"Æ",	"&#198;"},
		{"Ç",	"&#199;"},
		{"È",	"&#200;"},
		{"É",	"&#201;"},
		{"Ê",	"&#202;"},
		{"Ë",	"&#203;"},
		{"Ì",	"&#204;"},
		{"Í",	"&#205;"},
		{"Î",	"&#206;"},
		{"Ï",	"&#207;"},
		{"Ð",	"&#208;"},
		{"Ñ",	"&#209;"},
		{"Ò",	"&#210;"},
		{"Ó",	"&#211;"},
		{"Ô",	"&#212;"},
		{"Õ",	"&#213;"},
		{"Ö",	"&#214;"},
		{"×",	"&#215;"},
		{"Ø",	"&#216;"},
		{"Ù",	"&#217;"},
		{"Ú",	"&#218;"},
		{"Û",	"&#219;"},
		{"Ü",	"&#220;"},
		{"Ý",	"&#221;"},
		{"Þ",	"&#222;"},
		{"ß",	"&#223;"},
		{"à",	"&#224;"},
		{"á",	"&#225;"},
		{"â",	"&#226;"},
		{"ã",	"&#227;"},
		{"ä",	"&#228;"},
		{"å",	"&#229;"},
		{"æ",	"&#230;"},
		{"ç",	"&#231;"},
		{"è",	"&#232;"},
		{"é",	"&#233;"},
		{"ê",	"&#234;"},
		{"ë",	"&#235;"},
		{"ì",	"&#236;"},
		{"í",	"&#237;"},
		{"î",	"&#238;"},
		{"ï",	"&#239;"},
		{"ð",	"&#240;"},
		{"ñ",	"&#241;"},
		{"ò",	"&#242;"},
		{"ó",	"&#243;"},
		{"ô",	"&#244;"},
		{"õ",	"&#245;"},
		{"ö",	"&#246;"},
		{"÷",	"&#247;"},
		{"ø",	"&#248;"},
		{"ù",	"&#249;"},
		{"ú",	"&#250;"},
		{"û",	"&#251;"},
		{"ü",	"&#252;"},
		{"ý",	"&#253;"},
		{"þ",	"&#254;"},
		{"ÿ",	"&#255;"}
	};
	
	//--- Utils methods -------------------------
	public static String replace(String source, String iniStr, String finStr) {
		if (source == null) return null;

		int x = 0;
		int y = 0;
		x = source.indexOf(iniStr, x);
		StringBuffer out = new StringBuffer();
		while (x != -1) {
			out.append(source.substring(y, x));
			out.append(finStr);
			x += iniStr.length();
			y = x;
			x = source.indexOf(iniStr, x);
		}
		out.append(source.substring(y, source.length()));
		
		return out.toString();
	}

	public static String replaceAll(String strData, Map<String, String> params) {
		
		if (CollectionUtil.notEmpty(params)) {
			for (Map.Entry<String, String> param : params.entrySet()) {
				strData = StringUtil.replaceAll(strData, param.getKey(), param.getValue());
			}
		}
		
		return strData;
	}
	
	/**
	 * Reemplaza el primer string por el segundo en el string dado
	 */
	public static String replaceAll(String strdata, String source, String target) {
		// To force a correct split if the source string is at the start or end
		strdata = " " + strdata + " ";
		
		String[] strDataAux = split(strdata, source, true);
		if (strDataAux == null) {
			return strdata;
		} else {
			StringBuffer strBuffAux =  new StringBuffer();
			for (int i = 0; i < strDataAux.length - 1; i++) {
				strBuffAux.append(strDataAux[i]);
				strBuffAux.append(target);
			}
			if (strDataAux.length > 0) {
				if (strDataAux[strDataAux.length - 1] == null || strDataAux[strDataAux.length - 1].equals("")) {
					strBuffAux.append(target);
				}
				strBuffAux.append(strDataAux[strDataAux.length - 1]);
			} else {
				strBuffAux.append(target);
			}
			
			return strBuffAux.toString().trim();
		}
	}

	public static boolean isEmptyTrim(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	public static boolean isEmpty(String... str) {
		if (str == null) return true;
		for (String value : str) {
			if (value == null || value.length() == 0) return true;
		}
		return false;
	}

	public static boolean notEmpty(String... str) {
		return !StringUtil.isEmpty(str);
	}
	
	public static String toString(int value) {
		return Integer.toString(value);
	}
	
	public static String toString(Object value) {
		return StringUtil.toString(value,null);
	}
	
	public static String toString(Object value, String defValue) {
		if (value != null) return value instanceof String ? (String) value : value.toString();
		return defValue;
	}

	public static String noNull(String str) {
		return (str == null)?StringUtil.EMPTY_STRING:str;
	}
	
	public static Integer toInteger(String value, Integer ifError) {
		try {
			if (StringUtil.isEmpty(value)) return ifError;
			return Integer.valueOf(value);
		} catch (NumberFormatException e) {
			return ifError;
		}
	}
	
	public static int firstNumberPosition(String str) {
		if (str != null) for (int i = 0; i < str.length(); i++) {
			if (StringUtil.NUMBERS.indexOf(str.charAt(i)) != -1) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static int lastNumberPosition(String str) {
		if (str != null) for (int i = (str.length() - 1); i >= 0; i--) {
			if (StringUtil.NUMBERS.indexOf(str.charAt(i)) == -1) {
				return i+1;
			}
		}
		
		return -1;
	}

	public static String toXml(String str) {
		return StringUtil.toXml(str,true);
	}
	
	public static String fromXml(String str) {
		return StringUtil.toXml(str,false);
	}
	
	public static String toXml(String str, boolean replaceHtmlAcents) {
		if (replaceHtmlAcents) str = StringUtil.replaceHtmlAcents(str);
		
		if (str != null) {
			String auxStr = str;
			for (int i = 0; i < StringUtil.specialXml.length; i++) {
				auxStr = StringUtil.replace(auxStr,StringUtil.specialXml[i][0], StringUtil.specialXml[i][1]);
			}

			return auxStr;
		} else {
			return "";
		}
	}

	public static String fromXml(String str, boolean replaceHtmlAcents) {
		if (replaceHtmlAcents) str = StringUtil.replaceHtmlAcents(str);
		
		if (str != null) {
			String auxStr = str;
			for (int i = 0; i < StringUtil.specialXml.length; i++) {
				auxStr = StringUtil.replace(auxStr,StringUtil.specialXml[i][1], StringUtil.specialXml[i][0]);
			}

			return auxStr;
		} else {
			return "";
		}
	}

	public static String replaceHtmlAcents(String str) {
		return StringUtil.replaceHtmlAcents(str, true);
	}
	
	public static String replaceHtmlAcents(String str, boolean useNumbers) {
		if (str != null) {
			String auxStr = str;
			String[][] codes = useNumbers ? StringUtil.specialHtml : StringUtil.specialHtmlCode;
			for (int i = 0; i < codes.length; i++) {
				auxStr = StringUtil.replace(auxStr,codes[i][0], codes[i][1]);
			}

			return auxStr;
		} else {
			return "";
		}
	}
	
	public static String addHtmlAcents(String str) {
		return StringUtil.addHtmlAcents(str, true);
	}
	
	public static String addHtmlAcents(String str, boolean useNumbers) {
		if (str != null) {
			String auxStr = str;
			String[][] codes = useNumbers ? StringUtil.specialHtml : StringUtil.specialHtmlCode;
			for (int i = 0; i < codes.length; i++) {
				auxStr = StringUtil.replace(auxStr,codes[i][1], codes[i][0]);
			}
			
			return auxStr;
		} else {
			return "";
		}
	}
	
	public static String parseMessage(String message, Collection<String> params) {
		String returnMessage = null;

		try {
			if (params != null) {
				Object[] args = params.toArray();
				int size = args.length;
				returnMessage = message;
				for (int i = 1; i <= size; i++) {
					returnMessage = StringUtil.replace(returnMessage, StringUtil.START_TOKEN + i + StringUtil.END_TOKEN, args[i - 1].toString());
				}
			} else {
				returnMessage = message;
			}
		} catch (Exception e) {
		}
		return returnMessage;
	}

	public static String[] clone(String[] values) {
		if (values == null) return null;
		
		String[] result = new String[values.length];
		int i = 0;
		for (String value : values) result[i++] = value;
		
		return result;
	}

	public static String creteVoName(String tableName) {
		if (tableName != null) {
			String[] partes = tableName.split("_");
			StringBuffer buffer = new StringBuffer();
	
			for (int i = 0; i < partes.length; i++) {
				char[] chars = partes[i].toCharArray();
				buffer.append(String.valueOf(chars[0]).toUpperCase());
				buffer.append(String.valueOf(chars, 1, chars.length - 1));
			}
			
			return buffer.toString();
		}
		
		return null;
	}

	public static String join(String separator, Object... values) {
		StringBuffer buffer = new StringBuffer();
		for(Object value : values) {
			if (buffer.length() > 0 && separator != null) buffer.append(separator);
			buffer.append(value);
		}
		return buffer.toString();
	}
	
	public static String joinNotNull(String separator, Object... values) {
		StringBuffer buffer = new StringBuffer();
		for(Object value : values) {
			if (value != null) {
				if (buffer.length() > 0 && separator != null) buffer.append(separator);
				buffer.append(value);
			}
		}
		return buffer.toString();
	}
	
	public static String joinNotNull(Object[] data, String joinStr) {
		if (data != null && data.length > 0) {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < data.length; i++) {
				if (data[i] != null) {
					if (buffer.length() > 0) buffer.append(joinStr);
					buffer.append(data[i].toString());
				}
			}
			return buffer.toString();
		}
		
		return null;
	}
	
	public static String join(String separator, Collection<?> values) {
		StringBuffer buffer = new StringBuffer();
		if (values != null) {
			for(Object value : values) {
				if (buffer.length() > 0 && separator != null) buffer.append(separator);
				buffer.append(value);
			}
		}
		return buffer.toString();
	}
	
	public static String[] split(String str, String separator) {
		return StringUtil.split(str,separator,false);
	}

	public static String[] split(String str, String separator, boolean addNull) {
		if (str == null || str.equals("")) {
			return null;
		} else if (separator == null || separator.equals("")) {
			return new String[] { str };
		} else {
			ArrayList<String> arraux = new ArrayList<String>();
			String straux = str;

			while (str.indexOf(separator) != -1) {
				straux = str.substring(0, str.indexOf(separator));
				str = str.substring(str.indexOf(separator) + separator.length());
				arraux.add(straux);
			}
			if (addNull || (str != null && !str.equals(""))) {
				arraux.add(str);
			}

			String[] result = new String[arraux.size()];
			int i = 0;
			for (Object obj : arraux) result[i++] = (String) obj;
			
			return result;
		}
	}
	
	public String[] split(String str, int maxLenght) {
		int origLen = str.length();
		int splitNum = origLen / maxLenght;

		if (origLen % maxLenght > 0) splitNum += 1;

		String[] splits = new String[splitNum];

		for (int i = 0; i < splitNum; i++) {
			int startPos = i * maxLenght;
			int endPos = startPos + maxLenght;
			if (endPos > origLen)
				endPos = origLen;

			String substr = str.substring(startPos, endPos);

			splits[i] = substr;
		}

		return splits;
	}

	public static String toString(Throwable t) {
		return StringUtil.toString(t, false);
	}
	
	public static String toString(Throwable t, boolean forHtml) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	    PrintWriter writer = new PrintWriter(bytes,true);
	    t.printStackTrace(writer);
	    return forHtml ? StringUtil.replace(bytes.toString(), StringUtil.NEW_LINE, "<br>") : bytes.toString();
	}
	
	public static Collection<String> toCollection(String[] values) {
		if (values == null) return null;
		
		Collection<String> result = new ArrayList<String>(values.length);
		
		for(String value : values) result.add(value);
		
		return result;
	}

	public static boolean endsWidth(String text, String[] values) {
		if (text != null && values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				if (text.endsWith(values[i])) {
					return true;
				}
			}
		}
		
		return false;
	}

	public static String removeUnknownChars(String text) {
		StringBuffer buffer = new StringBuffer(text);
		text = text.toLowerCase();
		
		int i = 0;
		int removed = 0;
		
		while (i < text.length()) {
			if (StringUtil.KNOW_STRINGS.indexOf(text.charAt(i)) == -1) {
				int toRemove = i - removed++;
				buffer.delete(toRemove, toRemove + 1);
			}
			i++;
		}
		
		return buffer.toString();
	}
	
	/**
	 * For the command "test command param1:value1 value12 param2:value3 value4" returns a HashMap of:
	 * null - test command
	 * param1 - value1 value2
	 * param2 - value3 value4
	 * 
	 * @param value
	 * @param separator
	 * @return
	 */
	public static HashMap<String,String> decompileCommand(String value, String separator, Collection<String> validCommands, String validStart) {
		HashMap<String,String> result = new HashMap<String,String>();

		if (StringUtil.notEmpty(value)) {
			String[] values = StringUtil.split(value, separator, true);
			if (values.length == 1) {
				result.put(null,value);
			} else { //Tengo al menos un par�metro
				String command = null;
				for (int i = 0; i < values.length; i++) {
					String nextCommand = null;
					
					if (command == null) {
						nextCommand = values[i];
						if (nextCommand.lastIndexOf(' ') != -1) result.put(null, nextCommand.substring(0,nextCommand.lastIndexOf(' ')).trim());
						nextCommand = nextCommand.substring(nextCommand.lastIndexOf(' ') + 1);
					} else if ((i + 1) < (values.length)) {//El valor tiene el inicio del siguiente par�metro
						value = values[i];
						nextCommand = value.substring(value.lastIndexOf(' ') + 1);
						value = value.substring(0,value.lastIndexOf(' ')).trim();
					} else { //Este es el valor
						value = values[i].trim();
					}
					
					if (command != null) {
						if (validCommands.contains(command) || (validStart != null && command.startsWith(validStart))) {
							result.put(command, value);
						} else {
							if (result.containsKey(null)) {
								result.put(null, result.get(null) + StringUtil.SPACE_STRING + command + StringUtil.SPACE_STRING + value);
							} else {
								result.put(null,command + StringUtil.SPACE_STRING + value);
							}
							command = null;
						}
					}
					
					if (nextCommand != null) nextCommand = nextCommand.trim();
					command = nextCommand;
				}
			}
		}

		return result;
	}

	public static String append(String str1, String str2, String appender) {
		StringBuffer buffer = new StringBuffer();
		
		if (StringUtil.notEmpty(str1)) buffer.append(str1);
		if (StringUtil.notEmpty(str1) && StringUtil.notEmpty(str2)) buffer.append(appender);
		if (StringUtil.notEmpty(str2)) buffer.append(str2); 
		
		return buffer.toString();
	}
	
	public static String get(String value, String ifEmpty) {
		if (StringUtil.isEmpty(value)) {
			return ifEmpty;
		}
		
		return value;
	}
	
	public static String get(String[] values, int position) {
		return StringUtil.get(values, position, null);
	}
	
	public static String get(String[] values, int position, String defValue) {
		if (values == null) return defValue;
		if (values.length <= position) return defValue;
		
		return values[position];
	}
	
	public static int positionOf(String[] values, String value) {
		if (values == null || values.length == 0) return -1;
		for (int i = 0; i < values.length; i++) {
			if (ClassUtil.equals(values[i], value)) return i;
		}
		
		return -1;
	}
	
	public static String firstUp(String str) {
		return String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
	}

	public static Integer toInteger(String str) {
		return StringUtil.isEmpty(str) ? null : Integer.valueOf(str);
	}

	public static Double toDouble(String str) {
		return StringUtil.isEmpty(str) ? null : Double.valueOf(str);
	}
	
	public static Float toFloat(String str) {
		return StringUtil.isEmpty(str) ? null : Float.valueOf(str);
	}

	public static Long toLong(String str) {
		return StringUtil.isEmpty(str) ? null : Long.valueOf(str);
	}
	
	public static Boolean toBoolean(String str) {
		return StringUtil.isEmpty(str) ? null : BooleanUtils.isTrue(str) ? Boolean.TRUE : Boolean.FALSE;
	}

	public static Collection<String> toUpperCase(Collection<String> values) {
		Collection<String> result = new ArrayList<String>(CollectionUtil.size(values));
		
		if (CollectionUtil.notEmpty(values)) {
			for (String aValue : values) {
				if (aValue != null) aValue = aValue.toUpperCase();
				result.add(aValue);
			}
		}
		
		return result;
	}
	
	public static void toUpperCase(String[] values) {
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] == null) continue;
				values[i] = values[i].toUpperCase();
			}
		}
	}

	public static String sizeLimite(String text, int size, boolean addThreeDots) {
		if (StringUtil.notEmpty(text) && text.length() > size) {
			text = text.substring(0, size);
			if (addThreeDots) text += StringUtil.THREE_DOTS;
		}
		
		return text;
	}

	public static String formatCreditCard(String number) {
		if (StringUtil.isEmpty(number)) return number;
		
		if (number.length() > 4) {
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < number.length() - 4; i++) result.append('x');
			result.append(number.substring(number.length() - 4));
			return result.toString();
		}
		
		return number;
	}
	
	public static String formatCreditCardLast4(String number) {
		if (StringUtil.isEmpty(number)) return number;
		
		if (number.length() > 4) {
			StringBuffer result = new StringBuffer();
			int length = number.length() - 4;
			if (length > 2) {
				result.append("...");
				length = 2;
			}
			for (int i = 0; i < length; i++) result.append('x');
			result.append(number.substring(number.length() - 4));
			return result.toString();
		}
		
		return number;
	}
	
	/* http://programacion.jias.es/2012/09/comprobar-la-codificacion-de-una-entrada-convertir-de-utf-8-iso-8859-1/ */
	public static String reencodeUtf8ToIso88591(String uft8) throws Exception {
		Charset utf8charset = Charset.forName("UTF-8");
		Charset iso88591charset = Charset.forName("ISO-8859-1");
	 
		// Decode UTF-8
		ByteBuffer bb = ByteBuffer.wrap(uft8.getBytes());
		CharBuffer data = utf8charset.decode(bb);
	 
		// Encode ISO-8559-1
		ByteBuffer outputBuffer = iso88591charset.encode(data);
		byte[] outputData = outputBuffer.array();
		
		String iso = new String(outputData);
		return iso;
	}
}
