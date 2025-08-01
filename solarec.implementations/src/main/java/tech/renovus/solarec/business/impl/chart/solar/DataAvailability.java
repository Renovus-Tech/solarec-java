package tech.renovus.solarec.business.impl.chart.solar;

import com.fasterxml.jackson.databind.JsonNode;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

public class DataAvailability extends AbstractChart {

	//--- Resources -----------------------------

	//--- Overridden methods --------------------
	@Override public Object execute() {
		
		if (CollectionUtil.isEmpty(this.chartFilter.getGenerators())) this.setAllGeneratorsToChartFilter();
		this.setAllStationsToFilter();
		this.setFrequency();
		
		if (StringUtil.isEmpty(this.chartFilter.getGroupBy())) this.chartFilter.setGroupBy(ChartFilter.GROUP_BY_HOUR);
		
		try {
			JsonNode jsonNode = this.retrieveChartInformation(this.config.getDataAvailability());
			
			return jsonNode.toPrettyString();
		} catch (Exception e) {
			return this.generateChartResultErrorAsString("data_availability", e);
		}
	}
	
}
