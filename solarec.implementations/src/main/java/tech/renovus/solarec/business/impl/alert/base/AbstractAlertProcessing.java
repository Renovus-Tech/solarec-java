package tech.renovus.solarec.business.impl.alert.base;

import java.util.Date;

import tech.renovus.solarec.business.ProcessingException;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.DataProcessingVo;
import tech.renvous.solarec.util.DateUtil;

public abstract class AbstractAlertProcessing {

	private StringBuilder log;
	
	//--- Abstract methods ----------------------
	public abstract void prepareFor(DataProcessingVo dataProVo);
	public abstract void process() throws ProcessingException;
	public abstract ClientVo generateAlertToSave();
	
	//--- Protected methods ---------------------
	protected void logInfo(String msg) {
		this.log.append("[ ");
		this.log.append(System.currentTimeMillis());
		this.log.append("] [ INFO ] ");
		this.log.append(msg);
		this.log.append("\r\n");
	}
	
	protected void logWarning(String msg) {
		this.log.append("[ ");
		this.log.append(System.currentTimeMillis());
		this.log.append("] [ WARN ] ");
		this.log.append(msg);
		this.log.append("\r\n");
	}
	
	protected void logError(String msg) {
		this.log.append("[ ");
		this.log.append(System.currentTimeMillis());
		this.log.append("] [ ERROR ] ");
		this.log.append(msg);
		this.log.append("\r\n");
	}
	
	protected void initLog() {
		this.log = new StringBuilder();
		this.logInfo("Start at: " + DateUtil.formatDateTime(new Date(), DateUtil.FMT_MILITAR));
	}
	
	protected void endLog() {
		this.logInfo("Ended at: " + DateUtil.formatDateTime(new Date(), DateUtil.FMT_MILITAR));
	}
	//--- Public methods ------------------------
	public String getLog() { return this.log.toString(); }
}
