package testing.tech.renovus.solarec;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;

public class ChartTest extends AbstractChart {

	//--- Public properties ---------------------
	public boolean calledExecute;
	
	//--- Overridden methods --------------------
	@Override
	public Object execute() {
		this.calledExecute = true;
		return null;
	}

}
