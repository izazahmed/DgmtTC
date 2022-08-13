/**
 * Created Date: 4/15/10 7:33 PM
 * Class Name  : DGMTLogger.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.util;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:33 PM
 * @author
 * @since DGMT 1.0
 */
public class DGMTLogger
{
	static
	{
		String path = "E:\\dgmt_config\\dgmtlog4j.xml";

		DOMConfigurator.configure(path);
		DGMTLogger.info(DGMTLogger.class, "-- Loaded the logger.............");
	}

	/**
	 * Description log the debug messages
	 * 
	 * @param classObj
	 *            class instance
	 * @param strMessage
	 *            error message
	 */
	public static void debug(Class classObj, String strMessage)
	{
		Logger.getLogger(classObj).debug(strMessage);
	}

	/**
	 * Description log the info messages
	 * 
	 * @param classObj
	 *            class instance
	 * @param strMessage
	 *            error message
	 */
	public static void info(Class classObj, String strMessage)
	{
		Logger.getLogger(classObj).info(strMessage);
	}

	/**
	 * Description log the warn messages
	 * 
	 * @param classObj
	 *            class instance
	 * @param strMessage
	 *            error message
	 */
	public static void warn(Class classObj, String strMessage)
	{
		Logger.getLogger(classObj).warn(strMessage);
	}

	/**
	 * Description log the error messages
	 * 
	 * @param classObj
	 *            class instance
	 * @param strMessage
	 *            error message
	 */
	public static void error(Class classObj, String strMessage)
	{
		Logger.getLogger(classObj).error(strMessage);
	}

	/**
	 * Description log the fatal messages
	 * 
	 * @param classObj
	 *            class instance
	 * @param strMessage
	 *            error message
	 */
	public static void fatal(Class classObj, String strMessage)
	{
		Logger.getLogger(classObj).fatal(strMessage);
	}

	/**
	 * Description log the stack trace
	 * 
	 * @param classObj
	 *            class instance
	 * @param strMessage
	 *            error message
	 */
	public static void logStackTrace(Class currentClass, Exception exception)
	{
		String exceptionDesc = "";

		for (int i = 0; i < exception.getStackTrace().length; i++)
		{
			exceptionDesc = exceptionDesc
					+ exception.getStackTrace()[i].toString();
		}

		DGMTLogger.info(currentClass, "STRACK TRACE:" + exceptionDesc);
	}
}
