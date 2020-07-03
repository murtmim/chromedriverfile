package com.tcs.saf.utilities;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author TCS
 *
 */
public class Log {
	
	/**
	 * This function configures the Log4j xml
	 * @param className
	 * @return
	 */
	public static Logger getInstance(String className)
	{
		String path = new File("").getAbsolutePath();
	    DOMConfigurator.configure(path+"\\log4j.xml");
		return Logger.getLogger(className);
	}

}
