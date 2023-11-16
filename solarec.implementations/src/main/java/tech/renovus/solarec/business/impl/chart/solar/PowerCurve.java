package tech.renovus.solarec.business.impl.chart.solar;

import com.fasterxml.jackson.databind.JsonNode;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renvous.solarec.util.CollectionUtil;

public class PowerCurve extends AbstractChart {

	//--- Resources -----------------------------

	//--- Overridden methods --------------------
	@Override public Object execute() {
		
		if (CollectionUtil.isEmpty(this.chartFilter.getGenerators())) this.setAllGeneratorsToChartFilter();
		this.setAllStationsToFilter();
		
		this.chartFilter.setGroupBy(null);
		
		try {
			JsonNode jsonNode = this.retrieveChartInformation(this.config.getSolar().getChartPowerCurveUrl());
			
			return jsonNode.toPrettyString();
		} catch (Exception e) {
			//return ChartFactory.getInstance().getChartExceptionInformation(this.statDefVo.getStatDefName(), e);
			return this.generateChartResultErrorAsString("power_curve", e);
		}
	}
	
//	@Override public Object getChartJs(Object executeResult) { return executeResult; }
}
