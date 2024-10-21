package testing.tech.renovus.solarec;

import tech.renovus.solarec.business.impl.calculation.base.AbstractDataCalculation;
import tech.renovus.solarec.exceptions.ProcessingException;

public class DataCalculationTest extends AbstractDataCalculation {

	//--- Public properties ---------------------
	public boolean calledCalculate;
	
	//--- Overridden methods --------------------
	@Override
	public void calculate() throws ProcessingException {
		this.calledCalculate = true;
	}

}
