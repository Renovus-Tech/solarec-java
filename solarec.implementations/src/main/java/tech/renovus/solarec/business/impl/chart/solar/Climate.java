package tech.renovus.solarec.business.impl.chart.solar;

import com.fasterxml.jackson.databind.JsonNode;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.util.CollectionUtil;

public class Climate extends AbstractChart {

	//--- Resources -----------------------------
	
	//--- Overridden methods --------------------
	@Override public Object execute() {
//		this.filter.setGenerators(null);
		
		if (CollectionUtil.isEmpty(this.chartFilter.getStations())) this.setAllStationsToFilter();
		
		try {
			JsonNode jsonNode = this.retrieveChartInformation(this.config.getChartClimateUrl());
			
			return jsonNode.toPrettyString();
		} catch (Exception e) {
			//return ChartFactory.getInstance().getChartExceptionInformation(this.statDefVo.getStatDefName(), e);
			return this.generateChartResultErrorAsString("climate", e);
		}
	}
	
//	@Override public Object getChartJs(Object executeResult) { return executeResult; }
}
