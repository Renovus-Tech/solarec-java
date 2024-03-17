package tech.renovus.solarec.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	public class LoggerService {

	//--- Constructors --------------------------
	private LoggerService() {}
	
	//--- Util methods --------------------------
	public static Logger schedulerLogger() { return LoggerFactory.getLogger("schedule"); }
	public static Logger inverterLogger() { return LoggerFactory.getLogger("inverter"); }
	public static Logger certificateLogger() { return LoggerFactory.getLogger("certificate"); }
	public static Logger rootLogger() { return LoggerFactory.getLogger("roor"); }
}
