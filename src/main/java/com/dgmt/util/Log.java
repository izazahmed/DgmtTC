/**
 * Created Date: 3/5/10 2:15 PM
 * Class Name  : Log.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.util;

import java.text.NumberFormat;

import java.util.Locale;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Description: A utility class to acquire Log instances. Emulates the behavior of the
 * org.apache.commons.logging.LogFactory.getLog(), but simply delegates it to
 * theLog.getLog() factory method.
 * 
 * @version 1.0 03/05/10 2:27 PM
 * @author
 * @since DGMT 1.0
 */
public class Log
{
	private static Log rootLog = new Log("");

	private static final NumberFormat US_NUMBER_FORMAT = NumberFormat
			.getIntegerInstance(Locale.US);

	/** The name of this log instance */
	private final String logName;

	private Logger delegate = null;

	// ------------------------------------------------------------ Constructor
	/**
	 * Construct a simple log with given name. Adds it to the static map along
	 * with all sub-categories.
	 * 
	 * @param name
	 *            log name
	 */
	private Log(String name)
	{
		logName = name;

		if ("".equals(name))
		{
			delegate = Logger.getRootLogger();
		}
		else
		{
			delegate = Logger.getLogger(name);
			LogFactory.logs.put(name, this);
		}
	}

	/**
	 * Factory method to return a named logger.
	 * 
	 * @param name
	 *            Logical name of the <code>Log</code> instance to be returned
	 * @return SimpleLog
	 */
	public static Log getLog(String name)
	{
		Log log = LogFactory.logs.get(name);

		if (log != null)
		{
			return log;
		}

		return new Log(name);
	}

	/**
	 * @return
	 */
	public static Log getRootLog()
	{
		return rootLog;
	}

	// -------------------------------------------------------- Properties
	/**
	 * <p>
	 * Set logging level for this logger and all sub-categories.
	 * </p>
	 * 
	 * @param level
	 *            new logging level
	 */
	public void setLevel(int level)
	{
		delegate.setLevel(Level.toLevel(level));
	}

	/**
	 * <p>
	 * Set logging level for this logger and all sub-categories.
	 * </p>
	 * 
	 * @param levelName
	 *            new logging level
	 */
	public void setLevel(String levelName)
	{
		delegate.setLevel(Level.toLevel(levelName));
	}

	/**
	 * <p>
	 * Get logging level.
	 * </p>
	 * 
	 * @return log level
	 */
	public int getLevel()
	{
		return delegate.getEffectiveLevel().toInt();
	}

	// -------------------------------------------------------- Logging Methods
	/**
	 * @param type
	 * @param message
	 */
	protected void log(int type, Object message)
	{
		if (!isLevelEnabled(type))
		{
			return;
		}

		delegate.log(Level.toLevel(type), addSpecInfo(message));
	}

	/**
	 * <p>
	 * Do the actual logging. This method assembles the message
	 */
	protected void log(int type, Object message, Throwable t)
	{
		if (!isLevelEnabled(type))
		{
			return;
		}

		delegate.log(Level.toLevel(type), addSpecInfo(message), t);
	}

	/**
	 * @param message
	 * @return
	 */
	private String addSpecInfo(Object message)
	{
		StringBuffer buf = new StringBuffer();

		// append a readable representation of the log level

		// append the name of the log instance if so configured
		if (LogFactory.isShowShortName())
		{
			// cut all but the last component of the name for both styles
			String prefix = logName.substring(logName.lastIndexOf(".") + 1)
					+ " - ";

			prefix = prefix.substring(prefix.lastIndexOf("/") + 1) + "-";
			buf.append(prefix);
		}
		else if (LogFactory.isShowLogName())
		{
			buf.append(String.valueOf(logName)).append(" - ");
		}

		// Append "<ClassName>.<method> :: "
		// NB! this may be expensive!
		if (LogFactory.isShowMethodName())
		{
			Throwable ex = new Throwable();
			StackTraceElement[] st = ex.getStackTrace();

			if (st.length > 3)
			{
				String clsName = st[3].getClassName();
				int pos = clsName.lastIndexOf('.');

				if (pos > -1)
				{
					clsName = clsName.substring(pos + 1);
				}

				buf.append(clsName + "." + st[3].getMethodName());
			}
			else
			{
				buf.append("???");
			}

			buf.append(" :: ");
		}

		// append the message
		buf.append(String.valueOf(message));

		return buf.toString();
	}

	/**
	 * Is the given log level currently enabled?
	 * 
	 * @param logLevel
	 *            is this level enabled?
	 */
	protected boolean isLevelEnabled(int logLevel)
	{
		// log level are numerically ordered so can use simple numeric
		// comparison
		return (logLevel >= getLevel());
	}

	// -------------------------------------------------------- Log
	// Implementation
	/**
	 * <p>
	 * Log a message with debug log level.
	 * </p>
	 */
	public final void debug(Object message)
	{
		log(Level.DEBUG_INT, message);
	}

	/**
	 * <p>
	 * Log an error with debug log level.
	 * </p>
	 */
	public final void debug(Object message, Throwable t)
	{
		log(Level.DEBUG_INT, message, t);
	}

	/**
	 * <p>
	 * Log a message with debug log level.
	 * </p>
	 */
	public final void trace(Object message)
	{
		log(Level.TRACE_INT, message);
	}

	/**
	 * <p>
	 * Log an error with debug log level.
	 * </p>
	 */
	public final void trace(Object message, Throwable t)
	{
		log(Level.TRACE_INT, message, t);
	}

	/**
	 * <p>
	 * Log a message with info log level.
	 * </p>
	 */
	public final void info(Object message)
	{
		log(Level.INFO_INT, message);
	}

	/**
	 * <p>
	 * Log an error with info log level.
	 * </p>
	 */
	public final void info(Object message, Throwable t)
	{
		log(Level.INFO_INT, message, t);
	}

	/**
	 * <p>
	 * Log a message with warn log level.
	 * </p>
	 */
	public final void warn(Object message)
	{
		log(Level.WARN_INT, message);
	}

	/**
	 * <p>
	 * Log an error with warn log level.
	 * </p>
	 */
	public final void warn(Object message, Throwable t)
	{
		log(Level.WARN_INT, message, t);
	}

	/**
	 * <p>
	 * Log a message with error log level.
	 * </p>
	 */
	public final void error(Object message)
	{
		log(Level.ERROR_INT, message);
	}

	/**
	 * <p>
	 * Log an error with error log level.
	 * </p>
	 */
	public final void error(Object message, Throwable t)
	{
		log(Level.ERROR_INT, message, t);
	}

	/**
	 * @param message
	 * @param t
	 * @param SQLCode
	 */
	public final void error(Object message, Throwable t, int SQLCode)
	{
		if (SQLCode == -803)
		{
			log(Level.ERROR_INT, "Record already exists.");
		}
		else if (SQLCode == -532)
		{
			log(Level.ERROR_INT, "Delete prevented by referential constraint.");
		}

		log(Level.ERROR_INT, message, t);
	}

	/**
	 * <p>
	 * Log a message with fatal log level.
	 * </p>
	 */
	public final void fatal(Object message)
	{
		log(Level.FATAL_INT, message);
	}

	/**
	 * <p>
	 * Log an error with fatal log level.
	 * </p>
	 */
	public final void fatal(Object message, Throwable t)
	{
		log(Level.FATAL_INT, message, t);
	}

	/**
	 * <p>
	 * Are debug messages currently enabled?
	 * </p>
	 * <p>
	 * This allows expensive operations such as <code>String</code>
	 * concatenation to be avoided when the message will be ignored by the
	 * logger.
	 * </p>
	 */
	public final boolean isDebugEnabled()
	{
		return isLevelEnabled(Level.DEBUG_INT);
	}

	/**
	 * <p>
	 * Are error messages currently enabled?
	 * </p>
	 * <p>
	 * This allows expensive operations such as <code>String</code>
	 * concatenation to be avoided when the message will be ignored by the
	 * logger.
	 * </p>
	 */
	public final boolean isErrorEnabled()
	{
		return isLevelEnabled(Level.ERROR_INT);
	}

	/**
	 * <p>
	 * Are fatal messages currently enabled?
	 * </p>
	 * <p>
	 * This allows expensive operations such as <code>String</code>
	 * concatenation to be avoided when the message will be ignored by the
	 * logger.
	 * </p>
	 */
	public final boolean isFatalEnabled()
	{
		return isLevelEnabled(Level.FATAL_INT);
	}

	/**
	 * <p>
	 * Are info messages currently enabled?
	 * </p>
	 * <p>
	 * This allows expensive operations such as <code>String</code>
	 * concatenation to be avoided when the message will be ignored by the
	 * logger.
	 * </p>
	 */
	public final boolean isInfoEnabled()
	{
		return isLevelEnabled(Level.INFO_INT);
	}

	/**
	 * <p>
	 * Are trace messages currently enabled?
	 * </p>
	 * <p>
	 * This allows expensive operations such as <code>String</code>
	 * concatenation to be avoided when the message will be ignored by the
	 * logger.
	 * </p>
	 */
	public final boolean isTraceEnabled()
	{
		return isLevelEnabled(Level.TRACE_INT);
	}

	/**
	 * <p>
	 * Are warn messages currently enabled?
	 * </p>
	 * <p>
	 * This allows expensive operations such as <code>String</code>
	 * concatenation to be avoided when the message will be ignored by the
	 * logger.
	 * </p>
	 */
	public final boolean isWarnEnabled()
	{
		return isLevelEnabled(Level.WARN_INT);
	}
}
