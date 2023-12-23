package tech.renovus.solarec.business.impl.chart;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.renovus.solarec.business.impl.chart.base.AbstractChart;
import tech.renovus.solarec.exceptions.CoreException;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.vo.custom.chart.Chart;
import tech.renovus.solarec.vo.custom.chart.ChartResult;
import tech.renovus.solarec.vo.custom.chart.Error;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

@Service
public class ChartFactory {

	//--- Resources -----------------------------
	@Autowired private  AutowireCapableBeanFactory autowireCapableBeanFactory;
	
	//--- Private methods -----------------------
	private AbstractChart get(Class<? extends AbstractChart> aClass) throws CoreException {
		try {
			AbstractChart anObject = aClass.getConstructor().newInstance();
			this.autowireCapableBeanFactory.autowireBean(anObject);
			return anObject;
		} catch (NoSuchMethodException | InstantiationException| IllegalAccessException| IllegalArgumentException| InvocationTargetException e) {
			throw new CoreException(e);
		}
	}
	
	//--- Public methods ------------------------
	public AbstractChart get(StatDefinitionVo vo) throws CoreException { return this.get(vo.getStatDefExecutable()); }
	public AbstractChart get(String className) throws CoreException {
		try {
			return this.get((Class<? extends AbstractChart>) Class.forName(className));
		} catch (ClassNotFoundException | CoreException e) {
			throw new CoreException(e);
		}
	}
	
	public final String generateChartResultErrorAsString(String chartName, String error, ChartFilter charFilter) {
		return generateChartResultErrorAsString(chartName, error, false, charFilter);
	}
	
	public final String generateChartResultErrorAsString(String chartName, String error, boolean noData, ChartFilter charFilter) {
		if (chartName == null) chartName = "n/a";
		ChartResult result = new ChartResult();
		
		result.setChart(new Chart(chartName));
		result.setError(new Error(noData ? Error.CODE_NO_DATA : Error.CODE_SYSTEM, error, noData));
		
		result.getChart().populate(charFilter);
		
		try {
			return JsonUtil.toString(result);
		} catch (JsonProcessingException e) {
			StringBuilder json = new StringBuilder();
			
			json.append("{");
			json.append("\"chart\": {");
			json.append("\"type\": \"");
			json.append(chartName);
			json.append("\"");
			json.append("},");
			json.append("\"error\": {");
			json.append("\"time\": ");
			json.append(DateUtil.formatDateTime(new Date(), DateUtil.FMT_MILITAR));
			json.append("\", \"message\": ");
			json.append(error);
			json.append("\"");
			json.append("}");
			json.append("}");
			
			return json.toString();
		}
	}
}
