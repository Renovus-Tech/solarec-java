package tech.renovus.solarec.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	public class LoggerService {

	//--- Constructors --------------------------
	private LoggerService() {}
	
	//--- Util methods --------------------------
	public static Logger schedulesLogger() { return LoggerFactory.getLogger("schedule"); }
	public static Logger inverterLogger() { return LoggerFactory.getLogger("inverter"); }
	public static Logger drecLogger() { return LoggerFactory.getLogger("drec"); }
}
