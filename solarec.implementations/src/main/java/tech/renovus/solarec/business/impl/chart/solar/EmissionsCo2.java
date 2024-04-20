package tech.renovus.solarec.business.impl.chart.solar;

import com.fasterxml.jackson.databind.JsonNode;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.util.CollectionUtil;

public class EmissionsCo2 extends AbstractChart {

	//--- Resources -----------------------------

	//--- Overridden methods --------------------
	@Override public Object execute() {
		
		if (CollectionUtil.isEmpty(this.chartFilter.getGenerators())) this.setAllGeneratorsToChartFilter();
		
		try {
			JsonNode jsonNode = this.retrieveChartInformation(this.config.getChartEmissionsUrl());
			
			return jsonNode.toPrettyString();
		} catch (Exception e) {
			return this.generateChartResultErrorAsString("emissions_co2", e);
		}
	}
	
}
