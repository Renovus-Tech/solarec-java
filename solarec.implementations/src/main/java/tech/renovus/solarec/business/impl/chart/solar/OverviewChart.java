package tech.renovus.solarec.business.impl.chart.solar;

import com.fasterxml.jackson.databind.JsonNode;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.util.CollectionUtil;

public class OverviewChart extends AbstractChart {
	
	//--- Resources -----------------------------

	//--- Overridden methods --------------------
	@Override public Object execute() {
		this.setAllGeneratorsToChartFilter();
		
		if (CollectionUtil.isEmpty(this.chartFilter.getStations())) this.setAllStationsToFilter();

		this.chartFilter.setGroupBy(null);
		
		try {
			JsonNode jsonNode = this.retrieveChartInformation(this.config.getChartOverviewUrl());
			
			return jsonNode.toPrettyString();
		} catch (Exception e) {
			//return ChartFactory.getInstance().getChartExceptionInformation(this.statDefVo.getStatDefName(), e);
			return this.generateChartResultErrorAsString("overview", e);
		}
	}

}
