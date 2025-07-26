package tech.renovus.solarec.business.impl.chart.solar;

import com.fasterxml.jackson.databind.JsonNode;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

public class ExpectedPower extends AbstractChart {

	//--- Resources -----------------------------

	//--- Overridden methods --------------------
	@Override public Object execute() {
		
		if (CollectionUtil.isEmpty(this.chartFilter.getGenerators())) this.setAllGeneratorsToChartFilter();
		this.setAllStationsToFilter();
		this.setFrequency();
		
		if (StringUtil.isEmpty(this.chartFilter.getGroupBy())) this.chartFilter.setGroupBy(ChartFilter.GROUP_BY_YEAR);
		
		try {
			JsonNode jsonNode = this.retrieveChartInformation(this.config.getExpectedPower());
			
			return jsonNode.toPrettyString();
		} catch (Exception e) {
			return this.generateChartResultErrorAsString("expected_power", e);
		}
	}
	
}
