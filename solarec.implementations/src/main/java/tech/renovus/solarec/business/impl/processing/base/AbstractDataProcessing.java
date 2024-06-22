package tech.renovus.solarec.business.impl.processing.base;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import tech.renovus.solarec.db.data.dao.interfaces.LocDataWeatherDao;
import tech.renovus.solarec.exceptions.ProcessingException;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;

public abstract class AbstractDataProcessing {

	//--- Public inner class --------------------
	public static final class DateRange {
		private long from	= -1;
		private long to		= -1;

		public void set(Date aDate) {
			if (aDate == null) {
				return;
			}
			this.from = this.from == -1 ? aDate.getTime() : Math.min(this.from, aDate.getTime());
			this.to = this.to == -1 ? aDate.getTime() : Math.max(this.to, aDate.getTime());
		}
		
		public Date getDateFrom() { return this.from == -1 ? null : new Date(this.from); }
		public Date getDateTo() { return this.to == -1 ? null : new Date(this.to); }
	}
	
	//--- Private constants ---------------------
	private static final int POPULATE_TIME_INCREMENT_MINUTES = 10;
	
	//--- Private properties --------------------
	private StringBuilder log;
	@Resource LocDataWeatherDao weatherDataDao;
	
	//--- Abstract methods ----------------------
	public abstract Collection<File> validateFile(ClientVo client, InputStream stream) throws ProcessingException;
	public abstract void prepareFor(ClientVo cliVo, InputStream stream);
	public abstract void process() throws ProcessingException;
	public abstract ClientVo generateDataToSave();
	public abstract boolean continueWithSatistics();
	
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
