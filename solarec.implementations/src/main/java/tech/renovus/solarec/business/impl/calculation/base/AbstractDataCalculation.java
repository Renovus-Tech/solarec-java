package tech.renovus.solarec.business.impl.calculation.base;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import tech.renovus.solarec.business.ProcessingException;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renovus.solarec.vo.db.data.StatDefinitionVo;
import tech.renovus.solarec.vo.db.data.StatProcessingVo;
import tech.renvous.solarec.util.DateUtil;
import tech.renvous.solarec.util.JsonUtil;

public abstract class AbstractDataCalculation {

	//--- Private properties --------------------
	private StringBuilder log;

	//--- Protected properties --------------------
	protected StatDefinitionVo statDefVo;
	protected DataProcessingVo dataProVo;
	protected StatProcessingVo statProVo;
	
	//--- Abstract methods ----------------------
	public abstract void calculate() throws ProcessingException;
	
	//--- Protected methods ---------------------
	protected void logInfo(String msg) {
		this.log.append("[ ");
		this.log.append(System.currentTimeMillis());
		this.log.append(" ] [ INFO ] ");
		this.log.append(msg);
		this.log.append("\r\n");
	}
	
	protected void logWarning(String msg) {
		this.log.append("[ ");
		this.log.append(System.currentTimeMillis());
		this.log.append(" ] [ WARN ] ");
		this.log.append(msg);
		this.log.append("\r\n");
	}
	
	protected void logError(String msg) {
		this.log.append("[ ");
		this.log.append(System.currentTimeMillis());
		this.log.append(" ] [ ERROR ] ");
		this.log.append(msg);
		this.log.append("\r\n");
	}
	
	protected void initLog() {
		this.log = new StringBuilder();
		this.logInfo("Start at: " + DateUtil.formatDateTime(new Date(), DateUtil.FMT_MILITAR));
	}
	
	protected void endLog() {
		this.logInfo("End at: " + DateUtil.formatDateTime(new Date(), DateUtil.FMT_MILITAR));
	}
	
	protected JsonNode retrieveInformation(String url, Object data) throws JsonProcessingException {
		if (data == null) data = new Object();
		
		Map<String, Object> params = new HashMap<>(1);
		params.put("param_json", JsonUtil.toString(data));
		
		String response			= JsonUtil.get(url, params);
		return JsonUtil.toNode(response);
	}
	
	protected JsonNode retrieveInformationAsPost(String url, Object data) throws JsonProcessingException {
		if (data == null) data = new Object();
		
		String response			= JsonUtil.post(url, data);
		return JsonUtil.toNode(response);
	}
	
	//--- Public methods ------------------------
	public void prepareFor(StatDefinitionVo statDefVo, DataProcessingVo dataProVo, StatProcessingVo statProVo) {
		this.statDefVo		= statDefVo;
		this.dataProVo		= dataProVo;
		this.statProVo		= statProVo;
	}

	public String getLog() { return this.log.toString(); }
}
