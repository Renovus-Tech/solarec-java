package tech.renovus.solarec.vo.comparator;

import java.util.Comparator;

public class StringGenCodeAsNumberComparator implements Comparator<String> {

	//--- Constructors --------------------------
	private StringGenCodeAsNumberComparator() {}
	private static StringGenCodeAsNumberComparator instance = new StringGenCodeAsNumberComparator();
	public static StringGenCodeAsNumberComparator getInstance() { return StringGenCodeAsNumberComparator.instance; }
	
	//--- Implemented methods -------------------
	@Override public int compare(String gen1, String gen2) {
		Integer num1 = null;
		Integer num2 = null;
		
		try { num1 = Integer.valueOf(gen1); } catch (NumberFormatException e) { /* do nothing */ }
		try { num2 = Integer.valueOf(gen2); } catch (NumberFormatException e) { /* do nothing */ }
		
		return num1 == null || num2 == null ? gen1.compareTo(gen2) : num1.compareTo(num2);
	}

}
