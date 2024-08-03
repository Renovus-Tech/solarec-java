package tech.renovus.solarec.util;

public class NumberUtil {

	//--- Public constants ----------------------

	
	//--- Utils methods -------------------------
	public static Double truncate(Double number, int precision) {
		if (number == null) return null;
		return Double.valueOf(NumberUtil.truncate(number.doubleValue(), precision));
	}
	
	public static double truncate(double number, int precision) {
	    double prec = Math.pow(10, precision);
	    return Math.round(number * prec) / (double) prec;
	}

	
	//--- Test methods --------------------------
	public static void main(String[] args)  {
		System.out.println(NumberUtil.truncate(1, 2));
		System.out.println(NumberUtil.truncate(10, 2));
		System.out.println(NumberUtil.truncate(100, 2));
		System.out.println(NumberUtil.truncate(7.6899999999999995, 2));
		System.out.println(NumberUtil.truncate(7.6844444, 2));
		System.out.println(NumberUtil.truncate(7.6899999999999995, 2));
	}
}
