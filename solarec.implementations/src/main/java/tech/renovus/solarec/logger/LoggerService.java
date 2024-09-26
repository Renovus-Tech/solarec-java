package tech.renovus.solarec.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerService {

	//--- Constructors --------------------------
	public LoggerService() { /* Required for testing */ }
	
	//--- Util methods --------------------------
	public static Logger schedulerLogger() { return LoggerFactory.getLogger("schedule"); }
	public static Logger inverterLogger() { return LoggerFactory.getLogger("inverter"); }
	public static Logger dataGridLogger() { return LoggerFactory.getLogger("datagrid"); }
	public static Logger certificateLogger() { return LoggerFactory.getLogger("certificate"); }
	public static Logger weatherLogger() { return LoggerFactory.getLogger("weather"); }
	public static Logger emailLogger() { return LoggerFactory.getLogger("email"); }
	public static Logger reportLogger() { return LoggerFactory.getLogger("report"); }
	public static Logger systemLogger() { return LoggerFactory.getLogger("roor"); }
	public static Logger rootLogger() { return LoggerFactory.getLogger("roor"); }
}
