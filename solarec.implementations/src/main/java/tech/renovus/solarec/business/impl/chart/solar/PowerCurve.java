package tech.renovus.solarec.business.impl.chart.solar;

import com.fasterxml.jackson.databind.JsonNode;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.util.CollectionUtil;

public class PowerCurve extends AbstractChart {

	//--- Resources -----------------------------

	//--- Overridden methods --------------------
	@Override public Object execute() {
		
		if (CollectionUtil.isEmpty(this.chartFilter.getGenerators())) this.setAllGeneratorsToChartFilter();
		this.setAllStationsToFilter();
		this.setFrequency();
		
		this.chartFilter.setGroupBy(null);
		
		try {
			JsonNode jsonNode = this.retrieveChartInformation(this.config.getChartPowerCurveUrl());
			
			return jsonNode.toPrettyString();
		} catch (Exception e) {
			return this.generateChartResultErrorAsString("power_curve", e);
		}
	}
	
}
