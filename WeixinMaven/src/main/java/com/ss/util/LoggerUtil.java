package com.ss.util;

import org.apache.log4j.Logger;

public class LoggerUtil {
	public static Logger Logger = null;
	
	public static Logger getInstance() {
		if(Logger == null) {
			Logger = org.apache.log4j.Logger.getLogger(LoggerUtil.class);
		}
		return Logger;
	}
}
