package tech.renovus.solarec.business.impl.chart.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.impl.chart.ChartFactory;
import tech.renovus.solarec.configuration.RenovusConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.GeneratorDao;
import tech.renovus.solarec.db.data.dao.interfaces.StationDao;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.FileUtil;
import tech.renovus.solarec.util.JsonUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.comparator.GeneratorGenCodeAsNumberComparator;
import tech.renovus.solarec.vo.db.data.GeneratorVo;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.db.data.StationVo;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

public abstract class AbstractChart {

	//--- Protected properties ------------------
//	@Autowired private Environment environment;
	@Autowired protected ChartFactory factory;
	@Resource GeneratorDao genDao;
	@Resource StationDao staDao;
	
	protected RenovusConfiguration config;
	protected UserData userData;

	protected StatDefinitionVo statDefVo;
	
	protected ChartFilter chartFilter;
	
	//--- Abstract methods ----------------------
	public abstract Object execute();
//	public abstract Object getChartJs(Object executeResult);
	
	//--- Protected methods ---------------------
	protected void setAllGeneratorsToChartFilter() {
		Collection<GeneratorVo> generators = new TreeSet<>(GeneratorGenCodeAsNumberComparator.getInstance());
		CollectionUtil.addAll(generators, this.genDao.findAll(this.userData.getCliId(), this.chartFilter.getLocation()));
		this.chartFilter.setGenerators(generators.stream().map(GeneratorVo::getGenId).collect(Collectors.toList()));
	}
	
	protected void setAllStationsToFilter() {
		Collection<StationVo> allStations = this.staDao.findAll(this.userData.getCliId(), this.chartFilter.getLocation());
		this.chartFilter.setStations(allStations.stream().map(StationVo::getStaId).collect(Collectors.toList()));
	}
	
	protected JsonNode retrieveChartInformation(String url) throws JsonProcessingException, IOException { return this.retrieveInformation(url, this.generateChartFilter()); }
	
	protected JsonNode retrieveInformation(String url, Object data) throws JsonProcessingException, IOException {
		if (data == null) data = new Object();
		
		String jsonData = JsonUtil.toString(data);
		String response = null;
		boolean atDev	= false; //Arrays.stream(this.environment.getActiveProfiles()).anyMatch("dev"::equals);
		String dataKey	= url + "-" + jsonData;
		File dataToLoad	= atDev ? new File(this.config.getPathLog() + "/jsons/" + dataKey.hashCode() + ".json") : null;
		
		System.out.println("Calling (" + this.config.getChartMethod() + " - " + new Date() + "): " + url + " - with param_json: " + jsonData);

		if (atDev && dataToLoad != null && dataToLoad.exists()) {
			System.out.println("Loading from file: " + dataToLoad.getAbsolutePath());
			response = FileUtil.readFile(dataToLoad);
		} else {
	
			if ("post".equals(this.config.getChartMethod())) response = JsonUtil.post(url, jsonData);
			else {
				Map<String, Object> params = new HashMap<>(1);
				params.put("param_json", jsonData);
				response = JsonUtil.get(url, params);
			}
			
			if (atDev) {
				System.out.println("Saving to file: " + dataToLoad.getAbsolutePath());
				System.out.println("File exists: " + dataToLoad.exists());
				FileUtil.appendToFile(dataKey.hashCode() + " --> " + dataKey + StringUtil.NEW_LINE, new File(this.config.getPathLog() + "/jsons/mapping.txt"));
				FileUtil.saveToFile(response, dataToLoad);
			}
		}
		System.out.println("Response: " + response);

		return JsonUtil.toNode(response);
	}
	
	protected Chart generateChartFilter() {
		Chart result = new Chart();
		
		result.setClient(this.chartFilter.getClient());
		result.setLocation(this.chartFilter.getLocation());
		result.setFrom(this.chartFilter.getFrom());
		result.setTo(this.chartFilter.getTo());
		result.setGroupBy(this.chartFilter.getGroupBy());

		result.setGenerators(this.chartFilter.getGenerators());
		result.setStations(this.chartFilter.getStations());
		
		if (CollectionUtil.notEmpty(this.chartFilter.getAngles())) {
			result.setAngles(new ArrayList<>());
			this.chartFilter.getAngles().stream().forEach(x -> result.getAngles().add(new Angle(x.getFrom(), x.getTo())));
		}
		
		result.setSendAverage(this.chartFilter.getSendAverage());
		
		return result;
	}

	//--- Public methods ------------------------
	public final void prepareForExecution(StatDefinitionVo statDefVo, ChartFilter chartFilter, UserData userData, RenovusConfiguration config) {
		this.statDefVo		= statDefVo;
		this.chartFilter	= chartFilter;
		this.userData		= userData;
		this.config			= config;
	}
	
	public Object generateChartResultErrorAsString(String chartName, Exception e) {
		System.out.println("Error found: " + e.getLocalizedMessage());
		return this.factory.generateChartResultErrorAsString(chartName, e.getLocalizedMessage(), this.chartFilter);
	}
}
