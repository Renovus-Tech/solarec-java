package tech.renovus.solarec.business.impl.chart.solar;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

public class OverviewChart extends AbstractChart {
	
	//--- Resources -----------------------------

	//--- Overridden methods --------------------
	@Override public Object execute() {
		if (! ClassUtil.equals(ChartFilter.PERIOD_FILTER_YEAR, this.chartFilter.getPeriod())) {
			Date maxFromDate = DateUtil.addUnit(this.chartFilter.getTo(), Calendar.DAY_OF_YEAR, -31);
			if (maxFromDate.after(this.chartFilter.getFrom())) this.chartFilter.setFrom(maxFromDate);
		}
		
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
