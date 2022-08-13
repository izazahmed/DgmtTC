/**
 * Created Date: 03/05/10 2:15 PM
 * Class Name  : LogFactory.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Description:
 * 
 * @version 1.0 03/05/10 2:28 PM
 * @author 
 * @since DGMT 1.0
 */
public class LogFactory
{
	/**
	 * DOCUMENT ME!
	 */
	static Map<String, Log> logs = new TreeMap<String, Log>();

	private static Set<Level> availableLevels;

	/**
	 * DOCUMENT ME!
	 */
	public static final String ROOT_LOG_NAME = "_root_";

	// -------------------------------------------------- Static initialization
	/**
	 * DOCUMENT ME!
	 */
	public static final String DEFAULT_LEVEL_PROPERTY = "logging.defaultLevel";

	/**
	 * DOCUMENT ME!
	 */
	public static final String SHOW_LOG_NAME_PROPERTY = "logging.showLogName";

	/**
	 * DOCUMENT ME!
	 */
	public static final String SHOW_SHORT_NAME_PROPERTY = "logging.showShortName";

	/**
	 * DOCUMENT ME!
	 */
	public static final String SHOW_METHOD_NAME_PROPERTY = "logging.showMethodName";

	/**
	 * DOCUMENT ME!
	 */
	public static int DEFAULT_LEVEL = Level.INFO_INT;

	/** Include the instance name in the log message? */
	private static volatile boolean showLogName = false;

	/**
	 * Include the short name (last component) of the logger in the log message.
	 * Default to true - otherwise we'll be lost in a flood of messages without
	 * knowing who sends them.
	 */
	private static volatile boolean showShortName = false;

	/** Include the class/method name in the log message? */
	private static volatile boolean showMethodName = true;

	static
	{
		try
		{
			Set<Level> levels = new HashSet<Level>();

			levels.add(Level.ALL);
			levels.add(Level.DEBUG);
			levels.add(Level.INFO);
			levels.add(Level.WARN);
			levels.add(Level.TRACE);
			levels.add(Level.FATAL);
			levels.add(Level.ERROR);
			levels.add(Level.OFF);
			availableLevels = Collections.unmodifiableSet(levels);
		
			DEFAULT_LEVEL = Level.ALL_INT;

			String psaPackage = "com.lfob.oval";
			Logger logger = Logger.getLogger(psaPackage);

			// Do not raise level if it is already set to DEBUG in
			// log4j.properties
			if ((logger.getLevel() == null)
					|| (DEFAULT_LEVEL < logger.getLevel().toInt()))
			{
				logger.setLevel(Level.toLevel(DEFAULT_LEVEL));
				LogFactory.getLog(psaPackage).setLevel(DEFAULT_LEVEL);
			}

			showLogName = false; // props.getProperty(SHOW_LOG_NAME_PROPERTY,// false);

			showShortName = false; //props.getProperty(SHOW_SHORT_NAME_PROPERTY,// false);			
		}
		catch (Exception ex)
		{
		} // ignore all
	}

	/**
	 * Creates a new LogFactory object.
	 */
	private LogFactory() // utility class private constructor
	{
	}

	/**
	 * @return
	 */
	public static Set<Level> getAvailableLevels()
	{
		return availableLevels;
	}

	/**
	 * Factory method to return a named logger.
	 * 
	 * @param clazz
	 *            Class for which a log name will be derived
	 */
	public static Log getLog(Class clazz)
	{
		return getLog(clazz.getName());
	}

	/**
	 * Factory method to return a named logger.
	 * 
	 * @param name
	 *            Logical name of the <code>Log</code> instance to be returned
	 */
	public static Log getLog(String name)
	{
		return Log.getLog(name);
	}

	/**
	 * @return
	 */
	public static boolean isShowLogName()
	{
		return showLogName;
	}

	/**
	 * @param showLogName
	 */
	public static void setShowLogName(boolean showLogName)
	{
		LogFactory.showLogName = showLogName;
	}

	/**
	 * @return
	 */
	public static boolean isShowMethodName()
	{
		return showMethodName;
	}

	/**
	 * @param showMethodName
	 */
	public static void setShowMethodName(boolean showMethodName)
	{
		LogFactory.showMethodName = showMethodName;
	}

	/**
	 * @return
	 */
	public static boolean isShowShortName()
	{
		return showShortName;
	}

	/**
	 * @param showShortName
	 */
	public static void setShowShortName(boolean showShortName)
	{
		LogFactory.showShortName = showShortName;
	}

	/**
	 * @return
	 */
	public static Map<String, Log> getCategoryMap()
	{
		return Collections.unmodifiableMap(logs);
	}

	/**
	 * @return
	 */
	public static Log getRootLog()
	{
		return Log.getRootLog();
	}
}
