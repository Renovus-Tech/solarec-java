package tech.renovus.solarec.vo.comparator;

import java.util.Comparator;

import tech.renovus.solarec.vo.db.data.GeneratorVo;

public class GeneratorGenCodeAsNumberComparator implements Comparator<GeneratorVo> {

	//--- Constructors --------------------------
	private GeneratorGenCodeAsNumberComparator() {}
	private static GeneratorGenCodeAsNumberComparator instance = new GeneratorGenCodeAsNumberComparator();
	public static GeneratorGenCodeAsNumberComparator getInstance() { return GeneratorGenCodeAsNumberComparator.instance; }
	
	//--- Implemented methods -------------------
	@Override public int compare(GeneratorVo gen1, GeneratorVo gen2) {
		Integer num1 = null;
		Integer num2 = null;
		
		try { num1 = Integer.valueOf(gen1.getGenCode()); } catch (NumberFormatException e) { /* do nothing */ }
		try { num2 = Integer.valueOf(gen2.getGenCode()); } catch (NumberFormatException e) { /* do nothing */ }
		
		return num1 == null || num2 == null ? gen1.getGenCode().compareTo(gen2.getGenCode()) : num1.compareTo(num2);
	}

}
