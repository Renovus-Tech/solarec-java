package tech.renovus.solarec.util;

public class NumberUtil {

	//--- Public constants ----------------------

	
	//--- Utils methods -------------------------
	public static Double truncate(Double number, int precision) {
		if (number == null) {
			return null;
		}
		return Double.valueOf(NumberUtil.truncate(number.doubleValue(), precision));
	}
	
	public static double truncate(double number, int precision) {
	    double prec = Math.pow(10, precision);
	    return Math.round(number * prec) / prec;
	}
	
}
